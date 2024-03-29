package PurchaseProtocols;

import Protocols223.*;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.internal.LinkedTreeMap;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class Prot {
    public String guid;
    public String createDateTime;
    public String urlOOS;
    public String urlEIS;
    public String typeName;
    public String cancelNoticeRegistrationNumber;
    public String missedContest;
    public String missedReason;
    public String сancellationReason;
    public String purchaseCodeName;
    public String cancellationReason;
    public String purchaseCancellationReason;
    public String causeForRejection;

    public LotApplicationsList getLotAppList() {
        if (lotApplicationsList instanceof String) {
            return null;
        } else if (lotApplicationsList instanceof LinkedTreeMap) {
            LinkedTreeMap l = (LinkedTreeMap) lotApplicationsList;
            JsonObject jsonObject = new Gson().toJsonTree(l).getAsJsonObject();
            return new Gson().fromJson(jsonObject.toString(), LotApplicationsList.class);

        } else {
            return null;
        }
    }


    public Object lotApplicationsList;
    public PurchaseInfo purchaseInfo;
    public Attachments attachments;
    public ProtocolInfo protocolInfo;
    public ProtocolEvasionDecisionInfo protocolEvasionDecisionInfo;

    public Prot() {
    }

    public String getMissedContest() {
        return (missedContest != null) ? missedContest : "";
    }

    public void setMissedContest(String missedContest) {
        this.missedContest = missedContest;
    }

    public void Parsing(ProtocolType223.Settings set, Connection con) throws Exception {

        String purchaseNumber = "";
        if (purchaseInfo != null && purchaseInfo.purchaseNoticeNumber != null) {
            purchaseNumber = purchaseInfo.purchaseNoticeNumber;
        }
        if (purchaseNumber.equals(""))
            purchaseNumber = cancelNoticeRegistrationNumber;
        if (Objects.equals(purchaseNumber, "")) {
            Log.Logger("У протокола нет purchaseNumber", set.F);
            return;
        }
        String idProtocol = guid != null ? guid : "";
        String url = urlOOS != null ? urlOOS : "";
        if (url.equals("") && urlEIS != null) {
            url = urlEIS;
        }
        Date protocolDate = (createDateTime != null) ? Parser.GetDate(createDateTime) : new Date(0L);
        //out.println("this data " + protocolDate);

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
        boolean updated = false;
        PreparedStatement ps2 = con.prepareStatement(String.format("SELECT id, protocol_date FROM %sprotocols223 WHERE purchase_number = ?", Main.Prefix));
        ps2.setString(1, purchaseNumber);
        ResultSet r1 = ps2.executeQuery();
        while (r1.next()) {
            updated = true;
            int idP = r1.getInt(1);
            Date dateB = r1.getTimestamp(2);
            //out.println((dateB.equals(new Timestamp(protocolDate.getTime()))));
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
        if (typeProtocol.equals("") && purchaseCodeName != null) {
            typeProtocol = purchaseCodeName;
        }
        String missedR = (missedReason != null) ? missedReason : "";
        if (missedR.equals("")) {
            if (protocolEvasionDecisionInfo != null && protocolEvasionDecisionInfo.confirmingDocsRequisites != null) {
                missedR = missedR + (String.format("%s", protocolEvasionDecisionInfo.confirmingDocsRequisites));
            }
            if (protocolEvasionDecisionInfo != null && protocolEvasionDecisionInfo.commissionDecisionReason != null) {
                missedR = missedR + (String.format(" %s", protocolEvasionDecisionInfo.commissionDecisionReason));
            }
            missedR = missedR.trim();
        }
        if (missedR.isEmpty() && (purchaseCancellationReason != null)) {
            missedR = purchaseCancellationReason;
        }
        if (missedR.isEmpty() && (causeForRejection != null)) {
            missedR = causeForRejection;
        }
        PreparedStatement ps4 = con.prepareStatement(String.format("INSERT INTO %sprotocols223 SET guid = ?, protocol_date = ?, url = ?, purchase_number = ?, type_protocol = ?, cancel = ?, missed_contest = ?, missed_reason = ?, xml = ?, type_ftp = ?", Main.Prefix), Statement.RETURN_GENERATED_KEYS);
        ps4.setString(1, idProtocol);
        ps4.setTimestamp(2, new Timestamp(protocolDate.getTime()));
        ps4.setString(3, url);
        ps4.setString(4, purchaseNumber);
        ps4.setString(5, typeProtocol);
        ps4.setInt(6, cancel);
        ps4.setString(7, getMissedContest());
        ps4.setString(8, missedR);
        ps4.setString(9, set.PathParse);
        ps4.setString(10, set.Type.toString());
        ps4.executeUpdate();
        ResultSet rt = ps4.getGeneratedKeys();
        if (rt.next()) {
            idProt = rt.getInt(1);
        }
        rt.close();
        ps4.close();
        if (updated) {
            switch (set.Type) {
                case purchaseProtocol:
                    Main.UpCountPurchaseProtocol++;
                    break;
                case purchaseProtocolIP:
                    break;
                case purchaseProtocolOSZ:
                    Main.UpCountPurchaseProtocolOSZ++;
                    break;
                case purchaseProtocolPA_AE:
                    break;
                case purchaseProtocolPA_OA:
                    break;
                case purchaseProtocolPAAE:
                    Main.UpCountPurchaseProtocolPAAE++;
                    break;
                case purchaseProtocolPAAE94:
                    Main.UpCountPurchaseProtocolPAAE94++;
                    break;
                case purchaseProtocolPAEP:
                    Main.UpCountPurchaseProtocolPAEP++;
                    break;
                case purchaseProtocolPAOA:
                    Main.UpCountPurchaseProtocolPAOA++;
                    break;
                case purchaseProtocolRKZ:
                    break;
                case purchaseProtocolRZ1AE:
                    Main.UpCountPurchaseProtocolRZ1AE++;
                    break;
                case purchaseProtocolRZ2AE:
                    Main.UpCountPurchaseProtocolRZ2AE++;
                    break;
                case purchaseProtocolRZ_AE:
                    break;
                case purchaseProtocolRZ_OA:
                    break;
                case purchaseProtocolRZ_OK:
                    break;
                case purchaseProtocolRZAE:
                    Main.UpCountPurchaseProtocolRZAE++;
                    break;
                case purchaseProtocolRZOA:
                    Main.UpCountPurchaseProtocolRZOA++;
                    break;
                case purchaseProtocolRZOK:
                    Main.UpCountPurchaseProtocolRZOK++;
                    break;
                case purchaseProtocolVK:
                    Main.UpCountPurchaseProtocolVK++;
                    break;
                case purchaseProtocolZK:
                    Main.UpCountPurchaseProtocolZK++;
                    break;
                case purchaseProtocolCCAESMBO:
                    Main.UpCountPurchaseProtocolCCAESMBO++;
                    break;
                case purchaseProtocolCCKESMBO:
                    Main.UpCountPurchaseProtocolCCKESMBO++;
                    break;
                case purchaseProtocolCCZKESMBO:
                    Main.UpCountPurchaseProtocolCCZKESMBO++;
                    break;
                case purchaseProtocolCCZPESMBO:
                    Main.UpCountPurchaseProtocolCCZPESMBO++;
                    break;
                case purchaseProtocolCollationAESMBO:
                    Main.UpCountPurchaseProtocolCollationAESMBO++;
                    break;
                case purchaseProtocolEvasionAESMBO:
                    Main.UpCountPurchaseProtocolEvasionAESMBO++;
                    break;
                case purchaseProtocolEvasionKESMBO:
                    Main.UpCountPurchaseProtocolEvasionKESMBO++;
                    break;
                case purchaseProtocolEvasionZKESMBO:
                    Main.UpCountPurchaseProtocolEvasionZKESMBO++;
                    break;
                case purchaseProtocolEvasionZPESMBO:
                    Main.UpCountPurchaseProtocolEvasionZPESMBO++;
                    break;
                case purchaseProtocolFCDKESMBO:
                    Main.UpCountPurchaseProtocolFCDKESMBO++;
                    break;
                case purchaseProtocolFCODKESMBO:
                    Main.UpCountPurchaseProtocolFCODKESMBO++;
                    break;
                case purchaseProtocolFKVOKESMBO:
                    Main.UpCountPurchaseProtocolFKVOKESMBO++;
                    break;
                case purchaseProtocolRejectionAESMBO:
                    Main.UpCountPurchaseProtocolRejectionAESMBO++;
                    break;
                case purchaseRejection:
                    Main.UpCountPurchaseRejection++;
                    break;
                case purchaseProtocolRejectionKESMBO:
                    Main.UpCountPurchaseProtocolRejectionKESMBO++;
                    break;
                case purchaseProtocolRejectionZKESMBO:
                    Main.UpCountPurchaseProtocolRejectionZKESMBO++;
                    break;
                case purchaseProtocolRejectionZPESMBO:
                    Main.UpCountPurchaseProtocolRejectionZPESMBO++;
                    break;
                case purchaseProtocolRZ1AESMBO:
                    Main.UpCountPurchaseProtocolRZ1AESMBO++;
                    break;
                case purchaseProtocolRZ1KESMBO:
                    Main.UpCountPurchaseProtocolRZ1KESMBO++;
                    break;
                case purchaseProtocolRZ1ZPESMBO:
                    Main.UpCountPurchaseProtocolRZ1ZPESMBO++;
                    break;
                case purchaseProtocolRZ2AESMBO:
                    Main.UpCountPurchaseProtocolRZ2AESMBO++;
                    break;
                case purchaseProtocolRZ2KESMBO:
                    Main.UpCountPurchaseProtocolRZ2KESMBO++;
                    break;
                case purchaseProtocolRZ2ZPESMBO:
                    Main.UpCountPurchaseProtocolRZ2ZPESMBO++;
                    break;
                case purchaseProtocolRZZKESMBO:
                    Main.UpCountPurchaseProtocolRZZKESMBO++;
                    break;
                case purchaseProtocolSummingupAESMBO:
                    Main.UpCountPurchaseProtocolSummingupAESMBO++;
                    break;
                case purchaseProtocolSummingupKESMBO:
                    Main.UpCountPurchaseProtocolSummingupKESMBO++;
                    break;
                case purchaseProtocolSummingupZKESMBO:
                    Main.UpCountPurchaseProtocolSummingupZKESMBO++;
                    break;
                case purchaseProtocolSummingupZPESMBO:
                    Main.UpCountPurchaseProtocolSummingupZPESMBO++;
                    break;
                case purchaseProtocolZRPZAESMBO:
                    Main.UpCountPurchaseProtocolZRPZAESMBO++;
                    break;
                case purchaseProtocolZRPZKESMBO:
                    Main.UpCountPurchaseProtocolZRPZKESMBO++;
                    break;
                case purchaseProtocolZRPZZKESMBO:
                    Main.UpCountPurchaseProtocolZRPZZKESMBO++;
                    break;
                case purchaseProtocolZRPZZPESMBO:
                    Main.UpCountPurchaseProtocolZRPZZPESMBO++;
                    break;

            }
        } else {
            switch (set.Type) {
                case purchaseProtocol:
                    Main.CountPurchaseProtocol++;
                    break;
                case purchaseProtocolIP:
                    break;
                case purchaseProtocolOSZ:
                    Main.CountPurchaseProtocolOSZ++;
                    break;
                case purchaseProtocolPA_AE:
                    break;
                case purchaseProtocolPA_OA:
                    break;
                case purchaseProtocolPAAE:
                    Main.CountPurchaseProtocolPAAE++;
                    break;
                case purchaseProtocolPAAE94:
                    Main.CountPurchaseProtocolPAAE94++;
                    break;
                case purchaseProtocolPAEP:
                    Main.CountPurchaseProtocolPAEP++;
                    break;
                case purchaseProtocolPAOA:
                    Main.CountPurchaseProtocolPAOA++;
                    break;
                case purchaseProtocolRKZ:
                    break;
                case purchaseProtocolRZ1AE:
                    Main.CountPurchaseProtocolRZ1AE++;
                    break;
                case purchaseProtocolRZ2AE:
                    Main.CountPurchaseProtocolRZ2AE++;
                    break;
                case purchaseProtocolRZ_AE:
                    break;
                case purchaseProtocolRZ_OA:
                    break;
                case purchaseProtocolRZ_OK:
                    break;
                case purchaseProtocolRZAE:
                    Main.CountPurchaseProtocolRZAE++;
                    break;
                case purchaseProtocolRZOA:
                    Main.CountPurchaseProtocolRZOA++;
                    break;
                case purchaseProtocolRZOK:
                    Main.CountPurchaseProtocolRZOK++;
                    break;
                case purchaseProtocolVK:
                    Main.CountPurchaseProtocolVK++;
                    break;
                case purchaseProtocolZK:
                    Main.CountPurchaseProtocolZK++;
                    break;
                case purchaseProtocolCCAESMBO:
                    Main.CountPurchaseProtocolCCAESMBO++;
                    break;
                case purchaseProtocolCCKESMBO:
                    Main.CountPurchaseProtocolCCKESMBO++;
                    break;
                case purchaseProtocolCCZKESMBO:
                    Main.CountPurchaseProtocolCCZKESMBO++;
                    break;
                case purchaseProtocolCCZPESMBO:
                    Main.CountPurchaseProtocolCCZPESMBO++;
                    break;
                case purchaseProtocolCollationAESMBO:
                    Main.CountPurchaseProtocolCollationAESMBO++;
                    break;
                case purchaseProtocolEvasionAESMBO:
                    Main.CountPurchaseProtocolEvasionAESMBO++;
                    break;
                case purchaseProtocolEvasionKESMBO:
                    Main.CountPurchaseProtocolEvasionKESMBO++;
                    break;
                case purchaseProtocolEvasionZKESMBO:
                    Main.CountPurchaseProtocolEvasionZKESMBO++;
                    break;
                case purchaseProtocolEvasionZPESMBO:
                    Main.CountPurchaseProtocolEvasionZPESMBO++;
                    break;
                case purchaseProtocolFCDKESMBO:
                    Main.CountPurchaseProtocolFCDKESMBO++;
                    break;
                case purchaseProtocolFCODKESMBO:
                    Main.CountPurchaseProtocolFCODKESMBO++;
                    break;
                case purchaseProtocolFKVOKESMBO:
                    Main.CountPurchaseProtocolFKVOKESMBO++;
                    break;
                case purchaseProtocolRejectionAESMBO:
                    Main.CountPurchaseProtocolRejectionAESMBO++;
                    break;
                case purchaseRejection:
                    Main.CountPurchaseRejection++;
                    break;
                case purchaseProtocolRejectionKESMBO:
                    Main.CountPurchaseProtocolRejectionKESMBO++;
                    break;
                case purchaseProtocolRejectionZKESMBO:
                    Main.CountPurchaseProtocolRejectionZKESMBO++;
                    break;
                case purchaseProtocolRejectionZPESMBO:
                    Main.CountPurchaseProtocolRejectionZPESMBO++;
                    break;
                case purchaseProtocolRZ1AESMBO:
                    Main.CountPurchaseProtocolRZ1AESMBO++;
                    break;
                case purchaseProtocolRZ1KESMBO:
                    Main.CountPurchaseProtocolRZ1KESMBO++;
                    break;
                case purchaseProtocolRZ1ZPESMBO:
                    Main.CountPurchaseProtocolRZ1ZPESMBO++;
                    break;
                case purchaseProtocolRZ2AESMBO:
                    Main.CountPurchaseProtocolRZ2AESMBO++;
                    break;
                case purchaseProtocolRZ2KESMBO:
                    Main.CountPurchaseProtocolRZ2KESMBO++;
                    break;
                case purchaseProtocolRZ2ZPESMBO:
                    Main.CountPurchaseProtocolRZ2ZPESMBO++;
                    break;
                case purchaseProtocolRZZKESMBO:
                    Main.CountPurchaseProtocolRZZKESMBO++;
                    break;
                case purchaseProtocolSummingupAESMBO:
                    Main.CountPurchaseProtocolSummingupAESMBO++;
                    break;
                case purchaseProtocolSummingupKESMBO:
                    Main.CountPurchaseProtocolSummingupKESMBO++;
                    break;
                case purchaseProtocolSummingupZKESMBO:
                    Main.CountPurchaseProtocolSummingupZKESMBO++;
                    break;
                case purchaseProtocolSummingupZPESMBO:
                    Main.CountPurchaseProtocolSummingupZPESMBO++;
                    break;
                case purchaseProtocolZRPZAESMBO:
                    Main.CountPurchaseProtocolZRPZAESMBO++;
                    break;
                case purchaseProtocolZRPZKESMBO:
                    Main.CountPurchaseProtocolZRPZKESMBO++;
                    break;
                case purchaseProtocolZRPZZKESMBO:
                    Main.CountPurchaseProtocolZRPZZKESMBO++;
                    break;
                case purchaseProtocolZRPZZPESMBO:
                    Main.CountPurchaseProtocolZRPZZPESMBO++;
                    break;
            }
        }

        if (attachments != null && attachments.document != null) {
            ArrayList<Document> att = Parser.GetDocuments(attachments.document);
            for (Document doc : att) {
                String fileName = (doc.fileName != null) ? doc.fileName : "";
                String description = (doc.description != null) ? doc.description : "";
                String urlAtt = (doc.url != null) ? doc.url : "";
                PreparedStatement ps5 = con.prepareStatement(String.format("INSERT INTO %sprotocols223_attach SET id_protocol = ?, filename = ?, description = ?, url = ?", Main.Prefix));
                ps5.setInt(1, idProt);
                ps5.setString(2, fileName);
                ps5.setString(3, description);
                ps5.setString(4, urlAtt);
                ps5.executeUpdate();
                ps5.close();
            }
        }
        LotApplicationsList la = getLotAppList();
        if (la != null && la.protocolLotApplications != null) {
            ArrayList<ProtocolLotApplications> protocolLotAppl = Parser.GetProtocolLotApplications(la.protocolLotApplications);
            for (ProtocolLotApplications pa : protocolLotAppl) {
                String lotNum = "";
                if (pa.lot != null) {
                    lotNum = pa.lot.getOrdinalNumber();
                }
                GetApp(con, idProt, pa, lotNum, pa.application != null);
            }
        } else if (la != null && la.protocolVKLotApplications != null) {
            ArrayList<ProtocolVKLotApplications> protocolLotAppl = Parser.GetProtocolVKLotApplications(la.protocolVKLotApplications);
            for (ProtocolVKLotApplications pa : protocolLotAppl) {
                String lotNum = "";
                if (pa.lot != null) {
                    lotNum = pa.lot.getOrdinalNumber();
                }
                GetApp(con, idProt, pa, lotNum, pa.application != null);
            }
        }
    }

    private void GetApp(Connection con, int idProt, LotApp pa, String lotNum, boolean b) throws SQLException {
        if (b) {
            ArrayList<Application> app = Parser.GetApplications(pa.application);
            for (Application appl : app) {
                int idSup = 0;
                if (appl.supplierInfo != null && !appl.supplierInfo.getInn().equals("")) {
                    PreparedStatement ps6 = con.prepareStatement(String.format("SELECT id FROM %sprotocols223_supp WHERE inn = ? AND kpp = ?", Main.Prefix));
                    ps6.setString(1, appl.supplierInfo.getInn());
                    ps6.setString(2, appl.supplierInfo.getKpp());
                    ResultSet rs = ps6.executeQuery();
                    if (rs.next()) {
                        idSup = rs.getInt(1);
                        rs.close();
                        ps6.close();
                    } else {
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
                String curCode = (appl.currency != null && !appl.currency.getCode().equals("")) ? appl.currency.getCode() : "";
                PreparedStatement ps8 = con.prepareStatement(String.format("INSERT INTO %sptotocols223_appl SET id_protocol = ?, app_number = ?, id_supplier = ?, price = ?, price_info = ?, accepted = ?, winner_indication = ?, currency_code = ?, rejection_reason = ?, last_price = ?, last_price_info = ?, application_rate = ?, ordinal_number = ?", Main.Prefix));
                ps8.setInt(1, idProt);
                ps8.setString(2, appl.getApplicationNumber());
                ps8.setInt(3, idSup);
                ps8.setString(4, appl.getPrice());
                ps8.setString(5, appl.getPriceInfo());
                ps8.setString(6, appl.getAccepted());
                ps8.setString(7, appl.getWinnerIndication());
                ps8.setString(8, curCode);
                ps8.setString(9, appl.getRejectionReason());
                ps8.setString(10, appl.getLastPrice());
                ps8.setString(11, appl.getLastPriceInfo());
                ps8.setString(12, appl.getApplicationRate());
                ps8.setString(13, lotNum);
                ps8.executeUpdate();
                ps8.close();

            }
        }
    }

    public void ParsingCancelation(ProtocolType223Cancel.Settings set, Connection con) throws Exception {
        String purchaseNumber = "";
        if (purchaseInfo != null && purchaseInfo.purchaseNoticeNumber != null) {
            purchaseNumber = purchaseInfo.purchaseNoticeNumber;
        }
        if (Objects.equals(purchaseNumber, "")) {
            Log.Logger("У протокола нет purchaseNumber", set.F);
            return;
        }
        String idProtocol = guid != null ? guid : "";
        String url = urlEIS != null ? urlEIS : "";
        if (url.equals("") && urlOOS != null) {
            url = urlOOS;
        }
        String RegistrationNumber = "";
        if (protocolInfo != null && protocolInfo.registrationNumber != null) {
            RegistrationNumber = protocolInfo.registrationNumber;
        }
        Date protocolDate = (createDateTime != null) ? Parser.GetDate(createDateTime) : new Date(0L);
        //out.println("this data " + protocolDate);

        PreparedStatement ps1 = con.prepareStatement(String.format("SELECT id FROM %sprotocols223_cancel WHERE purchase_number = ? AND protocol_date = ? AND guid = ?", Main.Prefix));
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
        boolean updated = false;
        PreparedStatement ps2 = con.prepareStatement(String.format("SELECT id, protocol_date FROM %sprotocols223_cancel WHERE purchase_number = ? AND registration_number = ?", Main.Prefix));
        ps2.setString(1, purchaseNumber);
        ps2.setString(2, RegistrationNumber);
        ResultSet r1 = ps2.executeQuery();
        while (r1.next()) {
            updated = true;
            int idP = r1.getInt(1);
            Date dateB = r1.getTimestamp(2);
            //out.println((dateB.equals(new Timestamp(protocolDate.getTime()))));
            if (protocolDate.after(dateB) || (dateB.equals(new Timestamp(protocolDate.getTime())))) {
                PreparedStatement ps3 = con.prepareStatement(String.format("UPDATE %sprotocols223_cancel SET cancel=1 WHERE id = ?", Main.Prefix));
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
        String typeProtocol = "";
        if (protocolInfo != null && protocolInfo.typeName != null) {
            typeProtocol = protocolInfo.typeName;
        }

        String CancellationReason = (сancellationReason != null) ? сancellationReason : "";
        if(CancellationReason.equals("")){
            CancellationReason =  (cancellationReason != null) ? cancellationReason : "";
        }
        PreparedStatement ps4 = con.prepareStatement(String.format("INSERT INTO %sprotocols223_cancel SET guid = ?, protocol_date = ?, url = ?, purchase_number = ?, type_protocol = ?, cancel = ?, xml = ?, registration_number = ?, cancellation_reason = ?", Main.Prefix), Statement.RETURN_GENERATED_KEYS);
        ps4.setString(1, idProtocol);
        ps4.setTimestamp(2, new Timestamp(protocolDate.getTime()));
        ps4.setString(3, url);
        ps4.setString(4, purchaseNumber);
        ps4.setString(5, typeProtocol);
        ps4.setInt(6, cancel);
        ps4.setString(7, set.PathParse);
        ps4.setString(8, RegistrationNumber);
        ps4.setString(9, CancellationReason);
        ps4.executeUpdate();
        ResultSet rt = ps4.getGeneratedKeys();
        if (rt.next()) {
            idProt = rt.getInt(1);
        }
        rt.close();
        ps4.close();
        if (updated) {
            Main.UpCountPurchaseProtocolCancel++;
        } else {
            Main.CountPurchaseProtocolCancel++;
        }
        if (attachments != null && attachments.document != null) {
            ArrayList<Document> att = Parser.GetDocuments(attachments.document);
            for (Document doc : att) {
                String fileName = (doc.fileName != null) ? doc.fileName : "";
                String description = (doc.description != null) ? doc.description : "";
                String urlAtt = (doc.url != null) ? doc.url : "";
                PreparedStatement ps5 = con.prepareStatement(String.format("INSERT INTO %sprotocols223_attach_cancel SET id_protocol_cancel = ?, filename = ?, description = ?, url = ?", Main.Prefix));
                ps5.setInt(1, idProt);
                ps5.setString(2, fileName);
                ps5.setString(3, description);
                ps5.setString(4, urlAtt);
                ps5.executeUpdate();
                ps5.close();
            }
        }
    }
}
