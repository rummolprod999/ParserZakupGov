package Protocols223;

import PurchaseProtocols.*;
import com.google.gson.Gson;
import org.json.JSONObject;
import org.json.XML;

import java.io.File;
import java.sql.Connection;

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

    public void ParserType223(Connection con) {
        JSONObject jsonObj = null;
        String JsonString = null;
        try {
            jsonObj = XML.toJSONObject(ftext, true);
            JsonString = jsonObj.toString();
            //System.out.println(JsonString);
        } catch (Exception e) {
            Log.Logger("JSON exception", e.getMessage());
            return;
        }
        Gson gson = new Gson();
        switch (type) {
            case purchaseProtocol:
                try {
                    Protocol p = gson.fromJson(JsonString, Protocol.class);
                    Prot prot = p.purchaseProtocol.body.item.purchaseProtocolData;
                    if (prot != null) {
                        prot.Parsing(new Settings(), con);

                    } else {
                        Log.Logger("Не смогли создать объект", f.getAbsolutePath());
                    }
                } catch (Exception e) {
                    Log.Logger(e, e.getStackTrace(), f.getAbsolutePath(),"Error get gson object purchaseProtocol");
                }
                break;

            case purchaseProtocolIP:
                break;
            case purchaseProtocolOSZ:
                try {
                    ProtocolOSZ p = gson.fromJson(JsonString, ProtocolOSZ.class);
                    Prot prot = p.purchaseProtocolOSZ.body.item.purchaseProtocolOSZData;
                    if (prot != null) {
                        prot.Parsing(new Settings(), con);

                    } else {
                        Log.Logger("Не смогли создать объект", f.getAbsolutePath());
                    }
                } catch (Exception e) {
                    Log.Logger(e, e.getStackTrace(), f.getAbsolutePath(),"Error get gson object purchaseProtocolOSZ");
                }
                break;
            case purchaseProtocolPA_AE:
                break;
            case purchaseProtocolPA_OA:
                break;
            case purchaseProtocolPAAE:
                try {
                    ProtocolPAAE p = gson.fromJson(JsonString, ProtocolPAAE.class);
                    Prot prot = p.purchaseProtocolPAAE.body.item.purchaseProtocolPAAEData;
                    if (prot != null) {
                        prot.Parsing(new Settings(), con);

                    } else {
                        Log.Logger("Не смогли создать объект", f.getAbsolutePath());
                    }
                } catch (Exception e) {
                    Log.Logger(e, e.getStackTrace(), f.getAbsolutePath(),"Error get gson object purchaseProtocolPAAE");
                }
                break;
            case purchaseProtocolPAAE94:
                try {
                    ProtocolPAAE94FZ p = gson.fromJson(JsonString, ProtocolPAAE94FZ.class);
                    Prot prot = p.purchaseProtocolPAAE94FZ.body.item.purchaseProtocolPAAE94FZData;
                    if (prot != null) {
                        prot.Parsing(new Settings(), con);

                    } else {
                        Log.Logger("Не смогли создать объект", f.getAbsolutePath());
                    }
                } catch (Exception e) {
                    Log.Logger(e, e.getStackTrace(), f.getAbsolutePath(),"Error get gson object purchaseProtocolPAAE94");
                }
                break;
            case purchaseProtocolPAEP:
                try {
                    ProtocolPAEP p = gson.fromJson(JsonString, ProtocolPAEP.class);
                    Prot prot = p.purchaseProtocolPAEP.body.item.purchaseProtocolPAEPData;
                    if (prot != null) {
                        prot.Parsing(new Settings(), con);

                    } else {
                        Log.Logger("Не смогли создать объект", f.getAbsolutePath());
                    }
                } catch (Exception e) {
                    Log.Logger(e, e.getStackTrace(), f.getAbsolutePath(),"Error get gson object purchaseProtocolPAEP");
                }
                break;
            case purchaseProtocolPAOA:
                try {
                    ProtocolPAOA p = gson.fromJson(JsonString, ProtocolPAOA.class);
                    Prot prot = p.purchaseProtocolPAOA.body.item.purchaseProtocolPAOAData;
                    if (prot != null) {
                        prot.Parsing(new Settings(), con);

                    } else {
                        Log.Logger("Не смогли создать объект", f.getAbsolutePath());
                    }
                } catch (Exception e) {
                    Log.Logger(e, e.getStackTrace(), f.getAbsolutePath(),"Error get gson object purchaseProtocolPAOA");
                }
                break;
            case purchaseProtocolRKZ:
                break;
            case purchaseProtocolRZ1AE:
                try {
                    ProtocolRZ1AE p = gson.fromJson(JsonString, ProtocolRZ1AE.class);
                    Prot prot = p.purchaseProtocolRZ1AE.body.item.purchaseProtocolRZ1AEData;
                    if (prot != null) {
                        prot.Parsing(new Settings(), con);

                    } else {
                        Log.Logger("Не смогли создать объект", f.getAbsolutePath());
                    }
                } catch (Exception e) {
                    Log.Logger(e, e.getStackTrace(), f.getAbsolutePath(),"Error get gson object purchaseProtocolRZ1AE");
                }
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
