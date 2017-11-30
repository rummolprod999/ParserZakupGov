package Protocols223;

import PurchaseProtocols.Prot;
import PurchaseProtocols.Protocol;
import com.google.gson.Gson;
import org.json.JSONObject;
import org.json.XML;

import java.io.File;

public class ProtocolType223 {
    Region region;
    File f;
    String pathParse;
    String prot;
    TypeProt223 type;
    String ftext;

    public ProtocolType223(File f, String pathParse, String prot, Region region, TypeProt223 type, String ftext) {
        this.f = f;
        this.pathParse = pathParse;
        this.prot = prot;
        this.region = region;
        this.type = type;
        this.ftext = ftext;

    }

    public void ParserType223() {
        JSONObject jsonObj = null;
        String JsonString = null;
        try {
            jsonObj = XML.toJSONObject(ftext);
            JsonString = jsonObj.toString();
            //out.println(JsonString);
        } catch (Exception e) {
            Log.Logger("JSON exception", e.getMessage());
            return;
        }
        Gson gson = new Gson();
        switch (type) {
            case purchaseProtocol:
                Protocol p = gson.fromJson(JsonString, Protocol.class);
                Prot prot = null;
                prot = p.purchaseProtocol.body.item.purchaseProtocolData;
                if (prot != null) {
                    prot.Parsing(new Settings());

                } else {
                    Log.Logger("Не смогли создать объект", f);
                }
                break;

            case purchaseProtocolIP:
                break;
            case purchaseProtocolOSZ:
                break;
            case purchaseProtocolPA_AE:
                break;
            case purchaseProtocolPA_OA:
                break;
            case purchaseProtocolPAAE:
                break;
            case purchaseProtocolPAAE94:
                break;
            case purchaseProtocolPAEP:
                break;
            case purchaseProtocolPAOA:
                break;
            case purchaseProtocolRKZ:
                break;
            case purchaseProtocolRZ1AE:
                break;
            case purchaseProtocolRZ2AE:
                break;
            case purchaseProtocolRZ_AE:
                break;
            case purchaseProtocolRZ_OA:
                break;
            case purchaseProtocolRZ_OK:
                break;
            case purchaseProtocolRZAE:
                break;
            case purchaseProtocolRZOA:
                break;
            case purchaseProtocolRZOK:
                break;
            case purchaseProtocolVK:
                break;
            case purchaseProtocolZK:
                break;
        }
//        Protocol p = gson.fromJson(JsonString, Protocol.class);
//        System.out.println(p.purchaseProtocol.body.item.purchaseProtocolData.guid);
//        ArrayList<LinkedTreeMap> alist = null;
//        try {
//
//            alist = (ArrayList<LinkedTreeMap>) p.purchaseProtocol.body.item.purchaseProtocolData.lotApplicationsList.protocolLotApplications.application;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        for(LinkedTreeMap a: alist){
//
//            JsonObject jsonObject = gson.toJsonTree(a).getAsJsonObject();
//
//            Application ap = new Gson().fromJson(jsonObject.toString(), Application.class);
//            System.out.println(ap.applicationDate);
//        }

    }

    public class Settings {
        public Region Region;
        public File F;
        public String PathParse;
        public String Prot;
        public TypeProt223 Type;
        public String Ftext;

        public Settings() {
            Region = region;
            F = f;
            PathParse = pathParse;
            Prot = prot;
            Type = type;
            Ftext = ftext;
        }
    }

}
