package PurchaseProtocols;

import Protocols223.Log;
import Protocols223.Main;
import Protocols223.Parser;
import Protocols223.ProtocolType223;

import java.sql.*;
import java.util.Date;
import java.util.Objects;

import static java.lang.System.out;

public class Prot {
    public Prot() {
    }

    public String guid;
    public String createDateTime;
    public String urlOOS;
    public LotApplicationsList lotApplicationsList;
    public PurchaseInfo purchaseInfo;

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
            String typeProtocol = (purchaseInfo != null && purchaseInfo.purchaseCodeName != null) ? purchaseInfo.purchaseCodeName : "";



        } catch (Exception e) {
            Log.Logger("Error parsing protocol", e.getStackTrace());
        }


    }
}
