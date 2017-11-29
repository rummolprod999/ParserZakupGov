package PurchaseProtocols;

import Protocols223.Log;
import Protocols223.Parser;
import Protocols223.ProtocolType223;

import java.util.Date;
import java.util.Objects;

import static java.lang.System.out;

public class Prot {
    public Prot() {
    }

    public String guid;
    public  String createDateTime;
    public String urlOOS;
    public LotApplicationsList lotApplicationsList;
    public PurchaseInfo purchaseInfo;

    public void Parsing(ProtocolType223.Settings set){

        String purchase_number = "";
        if(purchaseInfo != null && purchaseInfo.purchaseNoticeNumber != null){
            purchase_number = purchaseInfo.purchaseNoticeNumber;
        }
        if(Objects.equals(purchase_number, "")){
            Log.Logger("У протокола нет purchase_number", set.F);
            return;
        }
        String id_protocol = guid != null ? guid : "";
        String url = urlOOS != null ? urlOOS : "";
        Date protocol_date = (createDateTime != null) ? Parser.GetDate(createDateTime) : new Date(0L);
        out.println(protocol_date);


    }
}
