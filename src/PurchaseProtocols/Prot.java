package PurchaseProtocols;

import Protocols223.Log;
import Protocols223.Main;
import Protocols223.Parser;
import Protocols223.ProtocolType223;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

import static java.lang.System.out;

public class Prot {
    public String guid;
    public String createDateTime;
    public String urlOOS;
    public String typeName;
    public String missedContest;
    public String missedReason;
    public LotApplicationsList lotApplicationsList;
    public PurchaseInfo purchaseInfo;
    public Attachments attachments;
    public Prot() {
    }

    public String getMissedContest() {
        return (missedContest != null) ? missedContest : "";
    }

    public void setMissedContest(String missedContest) {
        this.missedContest = missedContest;
    }

    public void Parsing(ProtocolType223.Settings set) {

        String purchaseNumber = "";
        if (purchaseInfo != null && purchaseInfo.purchaseNoticeNumber != null) {
            purchaseNumber = purchaseInfo.purchaseNoticeNumber;
        }
        if (Objects.equals(purchaseNumber, "")) {
            Log.Logger("У протокола нет purchaseNumber", set.F);
            return;
        }
        String idProtocol = guid != null ? guid : "";
        String url = urlOOS != null ? urlOOS : "";
        Date protocolDate = (createDateTime != null) ? Parser.GetDate(createDateTime) : new Date(0L);
        //out.println("this data " + protocolDate);
        try (Connection con = DriverManager.getConnection(Main.UrlConnect, Main.UserDb, Main.PassDb)) {
            PreparedStatement ps1 = con.prepareStatement(String.format("SELECT id FROM %sprotocols223 WHERE purchase_number = ? AND protocol_date = ? AND guid = ?", Main.Prefix));
            ps1.setString(1, purchaseNumber);
            ps1.setTimestamp(2, new Timestamp(protocolDate.getTime()));
            ps1.setString(3, idProtocol);
            ResultSet r = ps1.executeQuery();
            if (r.next()) {
                r.close();
                ps1.close();
                //Log.Logger("Такой протокол уже есть в базе", String.valueOf(guid));
                return;
            }
            r.close();
            ps1.close();
            int cancel = 0;
            PreparedStatement ps2 = con.prepareStatement(String.format("SELECT id, protocol_date FROM %sprotocols223 WHERE purchase_number = ?", Main.Prefix));
            ps2.setString(1, purchaseNumber);
            ResultSet r1 = ps2.executeQuery();
            while (r1.next()) {
                int idP = r1.getInt(1);
                Date dateB = r1.getTimestamp(2);
                out.println((dateB.equals(new Timestamp(protocolDate.getTime()))));
                if (protocolDate.after(dateB) || (dateB.equals(new Timestamp(protocolDate.getTime())))) {
                    PreparedStatement ps3 = con.prepareStatement(String.format("UPDATE %sprotocols223 SET cancel=1 WHERE id = ?", Main.Prefix));
                    ps3.setInt(1, idP);
                    ps3.executeUpdate();
                    ps3.close();
                } else {
                    cancel = 1;
                }
            }
            r1.close();
            ps2.close();
            int idProt = 0;
            String typeProtocol = (typeName != null) ? typeName : "";
            String missedR = (missedReason != null) ? missedReason : "";
            PreparedStatement ps4 = con.prepareStatement(String.format("INSERT INTO %sprotocols223 SET guid = ?, protocol_date = ?, url = ?, purchase_number = ?, type_protocol = ?, cancel = ?, missed_contest = ?, missed_reason = ?", Main.Prefix), Statement.RETURN_GENERATED_KEYS);
            ps4.setString(1, idProtocol);
            ps4.setTimestamp(2, new Timestamp(protocolDate.getTime()));
            ps4.setString(3, url);
            ps4.setString(4, purchaseNumber);
            ps4.setString(5, typeProtocol);
            ps4.setInt(6, cancel);
            ps4.setString(7, getMissedContest());
            ps4.setString(8, missedR);
            ps4.executeUpdate();
            ResultSet rt = ps4.getGeneratedKeys();
            if (rt.next()) {
                idProt = rt.getInt(1);
            }
            rt.close();
            ps4.close();
            Main.CountPurchaseProtocol++;
            if (attachments != null && attachments.document != null) {
                ArrayList<Document> att = Parser.GetDocuments(attachments.document);
                for(Document doc: att){
                    String fileName = (doc.fileName != null)?doc.fileName:"";
                    String description = (doc.description != null)?doc.description:"";
                    String urlAtt = (doc.url != null)?doc.url:"";
                    PreparedStatement ps5 = con.prepareStatement(String.format("INSERT INTO %sprotocols223_attach SET id_protocol = ?, filename = ?, description = ?, url = ?", Main.Prefix));
                    ps5.setInt(1, idProt);
                    ps5.setString(2, fileName);
                    ps5.setString(3, description);
                    ps5.setString(4, urlAtt);
                    ps5.executeUpdate();
                    ps5.close();
                }
            }
            if(lotApplicationsList != null && lotApplicationsList.protocolLotApplications != null && lotApplicationsList.protocolLotApplications.application != null){
                ArrayList<Application> app = Parser.GetApplications(lotApplicationsList.protocolLotApplications.application);
                for(Application appl: app){
                    int idSup = 0;
                    if(appl.supplierInfo != null && !appl.supplierInfo.getInn().equals("")){
                        PreparedStatement ps6 = con.prepareStatement(String.format("SELECT id FROM %sprotocols223_supp WHERE inn = ? AND kpp = ?", Main.Prefix));
                        ps6.setString(1, appl.supplierInfo.getInn());
                        ps6.setString(2, appl.supplierInfo.getKpp());
                        ResultSet rs = ps6.executeQuery();
                        if(rs.next()){
                            idSup = rs.getInt(1);
                            rs.close();
                            ps6.close();
                        } else{
                            rs.close();
                            ps6.close();
                            PreparedStatement ps7 = con.prepareStatement(String.format("INSERT INTO %sprotocols223_supp SET inn = ?, kpp = ?, address = ?, ogrn = ?, name = ?", Main.Prefix), Statement.RETURN_GENERATED_KEYS);
                            ps7.setString(1, appl.supplierInfo.getInn());
                            ps7.setString(2, appl.supplierInfo.getKpp());
                            ps7.setString(3, appl.supplierInfo.getAddress());
                            ps7.setString(4, appl.supplierInfo.getOgrn());
                            ps7.setString(5, appl.supplierInfo.getName());
                            ps7.executeUpdate();
                            ResultSet rsoi = ps7.getGeneratedKeys();
                            if (rsoi.next()) {
                                idSup = rsoi.getInt(1);
                            }
                            rsoi.close();
                            ps7.close();

                        }

                    }
                    String curCode = (appl.currency != null && !appl.currency.getCode().equals(""))?appl.currency.getCode():"";
                    PreparedStatement ps8 = con.prepareStatement(String.format("INSERT INTO %sptotocols223_appl SET id_protocol = ?, app_number = ?, id_supplier = ?, price = ?, price_info = ?, accepted = ?, winner_indication = ?, currency_code = ?", Main.Prefix));
                    ps8.setInt(1, idProt);
                    ps8.setString(2, appl.getApplicationNumber());
                    ps8.setInt(3, idSup);
                    ps8.setString(4, appl.getPrice());
                    ps8.setString(5, appl.getPriceInfo());
                    ps8.setString(6, appl.getAccepted());
                    ps8.setString(7, appl.getWinnerIndication());
                    ps8.executeUpdate();
                    ps8.close();




                }
            }



        } catch (Exception e) {
            Log.Logger("Error parsing protocol", e.getStackTrace());
        }


    }
}
