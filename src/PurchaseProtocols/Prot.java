package PurchaseProtocols;

import Protocols223.ProtocolType223;

public class Prot {
    public Prot() {
    }

    public String guid;
    public  Object createDateTime;
    public LotApplicationsList lotApplicationsList;

    public void Parsing(ProtocolType223.Settings set){
        System.out.println(guid);
        System.out.println(set.F);
    }
}
