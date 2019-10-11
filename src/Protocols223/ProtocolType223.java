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
            JsonString = jsonObj.toString().replace("\"attachments\":\"\"", "\"attachments\":{}");
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
                    Log.Logger(e, e.getStackTrace(), f.getAbsolutePath(), "Error get gson object purchaseProtocol");
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
                    Log.Logger(e, e.getStackTrace(), f.getAbsolutePath(), "Error get gson object purchaseProtocolOSZ");
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
                    Log.Logger(e, e.getStackTrace(), f.getAbsolutePath(), "Error get gson object purchaseProtocolPAAE");
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
                    Log.Logger(e, e.getStackTrace(), f.getAbsolutePath(), "Error get gson object purchaseProtocolPAAE94");
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
                    Log.Logger(e, e.getStackTrace(), f.getAbsolutePath(), "Error get gson object purchaseProtocolPAEP");
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
                    Log.Logger(e, e.getStackTrace(), f.getAbsolutePath(), "Error get gson object purchaseProtocolPAOA");
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
                    Log.Logger(e, e.getStackTrace(), f.getAbsolutePath(), "Error get gson object purchaseProtocolRZ1AE");
                }
                break;
            case purchaseProtocolRZ2AE:
                try {
                    ProtocolRZ2AE p = gson.fromJson(JsonString, ProtocolRZ2AE.class);
                    Prot prot = p.purchaseProtocolRZ2AE.body.item.purchaseProtocolRZ2AEData;
                    if (prot != null) {
                        prot.Parsing(new Settings(), con);

                    } else {
                        Log.Logger("Не смогли создать объект", f.getAbsolutePath());
                    }
                } catch (Exception e) {
                    Log.Logger(e, e.getStackTrace(), f.getAbsolutePath(), "Error get gson object purchaseProtocolRZ2AE");
                }
                break;
            case purchaseProtocolRZ_AE:
                break;
            case purchaseProtocolRZ_OA:
                break;
            case purchaseProtocolRZ_OK:
                break;
            case purchaseProtocolRZAE:

                try {
                    ProtocolRZAE p = gson.fromJson(JsonString, ProtocolRZAE.class);
                    Prot prot = p.purchaseProtocolRZAE.body.item.purchaseProtocolRZAEData;
                    if (prot != null) {
                        prot.Parsing(new Settings(), con);

                    } else {
                        Log.Logger("Не смогли создать объект", f.getAbsolutePath());
                    }
                } catch (Exception e) {
                    Log.Logger(e, e.getStackTrace(), f.getAbsolutePath(), "Error get gson object purchaseProtocolRZAE");
                }
                break;
            case purchaseProtocolRZOA:
                try {
                    ProtocolRZOA p = gson.fromJson(JsonString, ProtocolRZOA.class);
                    Prot prot = p.purchaseProtocolRZOA.body.item.purchaseProtocolRZOAData;
                    if (prot != null) {
                        prot.Parsing(new Settings(), con);

                    } else {
                        Log.Logger("Не смогли создать объект", f.getAbsolutePath());
                    }
                } catch (Exception e) {
                    Log.Logger(e, e.getStackTrace(), f.getAbsolutePath(), "Error get gson object purchaseProtocolRZOA");
                }
                break;
            case purchaseProtocolRZOK:
                try {
                    ProtocolRZOK p = gson.fromJson(JsonString, ProtocolRZOK.class);
                    Prot prot = p.purchaseProtocolRZOK.body.item.purchaseProtocolRZOKData;
                    if (prot != null) {
                        prot.Parsing(new Settings(), con);

                    } else {
                        Log.Logger("Не смогли создать объект", f.getAbsolutePath());
                    }
                } catch (Exception e) {
                    Log.Logger(e, e.getStackTrace(), f.getAbsolutePath(), "Error get gson object purchaseProtocolRZOK");
                }
                break;
            case purchaseProtocolVK:
                try {
                    ProtocolVK p = gson.fromJson(JsonString, ProtocolVK.class);
                    Prot prot = p.purchaseProtocolVK.body.item.purchaseProtocolVKData;
                    if (prot != null) {
                        prot.Parsing(new Settings(), con);

                    } else {
                        Log.Logger("Не смогли создать объект", f.getAbsolutePath());
                    }
                } catch (Exception e) {
                    Log.Logger(e, e.getStackTrace(), f.getAbsolutePath(), "Error get gson object purchaseProtocolVK");
                }
                break;
            case purchaseProtocolZK:
                try {
                    ProtocolZK p = gson.fromJson(JsonString, ProtocolZK.class);
                    Prot prot = p.purchaseProtocolZK.body.item.purchaseProtocolZKData;
                    if (prot != null) {
                        prot.Parsing(new Settings(), con);

                    } else {
                        Log.Logger("Не смогли создать объект", f.getAbsolutePath());
                    }
                } catch (Exception e) {
                    Log.Logger(e, e.getStackTrace(), f.getAbsolutePath(), "Error get gson object purchaseProtocolZK");
                }
                break;
            case purchaseProtocolCCAESMBO:
                try {
                    ProtocolCCAESMBO p = gson.fromJson(JsonString, ProtocolCCAESMBO.class);
                    Prot prot = p.purchaseProtocolCCAESMBO.body.item.purchaseProtocolCCAESMBOData;
                    if (prot != null) {
                        prot.Parsing(new Settings(), con);

                    } else {
                        Log.Logger("Не смогли создать объект", f.getAbsolutePath());
                    }
                } catch (Exception e) {
                    Log.Logger(e, e.getStackTrace(), f.getAbsolutePath(), "Error get gson object purchaseProtocolCCAESMBO");
                }
                break;
            case purchaseProtocolCCKESMBO:
                try {
                    ProtocolCCKESMBO p = gson.fromJson(JsonString, ProtocolCCKESMBO.class);
                    Prot prot = p.purchaseProtocolCCKESMBO.body.item.purchaseProtocolCCKESMBOData;
                    if (prot == null) {
                        prot = p.purchaseProtocolCCKESMBO.body.item.purchaseProtocolCCKESMBOOData;
                    }
                    if (prot != null) {
                        prot.Parsing(new Settings(), con);

                    } else {
                        Log.Logger("Не смогли создать объект", f.getAbsolutePath());
                    }
                } catch (Exception e) {
                    Log.Logger(e, e.getStackTrace(), f.getAbsolutePath(), "Error get gson object purchaseProtocolCCKESMBO");
                }
                break;
            case purchaseProtocolCCZKESMBO:
                try {
                    ProtocolCCZKESMBO p = gson.fromJson(JsonString, ProtocolCCZKESMBO.class);
                    Prot prot = p.purchaseProtocolCCZKESMBO.body.item.purchaseProtocolCCZKESMBOData;
                    if (prot != null) {
                        prot.Parsing(new Settings(), con);

                    } else {
                        Log.Logger("Не смогли создать объект", f.getAbsolutePath());
                    }
                } catch (Exception e) {
                    Log.Logger(e, e.getStackTrace(), f.getAbsolutePath(), "Error get gson object purchaseProtocolCCZKESMBO");
                }
                break;

            case purchaseProtocolCCZPESMBO:
                try {
                    ProtocolCCZPESMBO p = gson.fromJson(JsonString, ProtocolCCZPESMBO.class);
                    Prot prot = p.purchaseProtocolCCZPESMBO.body.item.purchaseProtocolCCZPESMBOData;
                    if (prot != null) {
                        prot.Parsing(new Settings(), con);

                    } else {
                        Log.Logger("Не смогли создать объект", f.getAbsolutePath());
                    }
                } catch (Exception e) {
                    Log.Logger(e, e.getStackTrace(), f.getAbsolutePath(), "Error get gson object purchaseProtocolCCZPESMBO");
                }
                break;
            case purchaseProtocolCollationAESMBO:
                try {
                    ProtocolCollationAESMBO p = gson.fromJson(JsonString, ProtocolCollationAESMBO.class);
                    Prot prot = p.purchaseProtocolCollationAESMBO.body.item.purchaseProtocolCollationAESMBOData;
                    if (prot != null) {
                        prot.Parsing(new Settings(), con);

                    } else {
                        Log.Logger("Не смогли создать объект", f.getAbsolutePath());
                    }
                } catch (Exception e) {
                    Log.Logger(e, e.getStackTrace(), f.getAbsolutePath(), "Error get gson object purchaseProtocolCollationAESMBO");
                }
                break;
            case purchaseProtocolEvasionAESMBO:
                try {
                    ProtocolEvasionAESMBO p = gson.fromJson(JsonString, ProtocolEvasionAESMBO.class);
                    Prot prot = p.purchaseProtocolEvasionAESMBO.body.item.purchaseProtocolEvasionAESMBOData;
                    if (prot != null) {
                        prot.Parsing(new Settings(), con);

                    } else {
                        Log.Logger("Не смогли создать объект", f.getAbsolutePath());
                    }
                } catch (Exception e) {
                    Log.Logger(e, e.getStackTrace(), f.getAbsolutePath(), "Error get gson object purchaseProtocolEvasionAESMBO");
                }
                break;
            case purchaseProtocolEvasionKESMBO:
                try {
                    ProtocolEvasionKESMBO p = gson.fromJson(JsonString, ProtocolEvasionKESMBO.class);
                    Prot prot = p.purchaseProtocolEvasionKESMBO.body.item.purchaseProtocolEvasionKESMBOData;
                    if (prot != null) {
                        prot.Parsing(new Settings(), con);

                    } else {
                        Log.Logger("Не смогли создать объект", f.getAbsolutePath());
                    }
                } catch (Exception e) {
                    Log.Logger(e, e.getStackTrace(), f.getAbsolutePath(), "Error get gson object purchaseProtocolEvasionKESMBO");
                }
                break;
            case purchaseProtocolEvasionZKESMBO:
                try {
                    ProtocolEvasionZKESMBO p = gson.fromJson(JsonString, ProtocolEvasionZKESMBO.class);
                    Prot prot = p.purchaseProtocolEvasionZKESMBO.body.item.purchaseProtocolEvasionZKESMBOData;
                    if (prot != null) {
                        prot.Parsing(new Settings(), con);

                    } else {
                        Log.Logger("Не смогли создать объект", f.getAbsolutePath());
                    }
                } catch (Exception e) {
                    Log.Logger(e, e.getStackTrace(), f.getAbsolutePath(), "Error get gson object purchaseProtocolEvasionZKESMBO");
                }
                break;
            case purchaseProtocolEvasionZPESMBO:
                try {
                    ProtocolEvasionZPESMBO p = gson.fromJson(JsonString, ProtocolEvasionZPESMBO.class);
                    Prot prot = p.purchaseProtocolEvasionZPESMBO.body.item.purchaseProtocolEvasionZPESMBOData;
                    if (prot != null) {
                        prot.Parsing(new Settings(), con);

                    } else {
                        Log.Logger("Не смогли создать объект", f.getAbsolutePath());
                    }
                } catch (Exception e) {
                    Log.Logger(e, e.getStackTrace(), f.getAbsolutePath(), "Error get gson object purchaseProtocolEvasionZPESMBO");
                }
                break;
            case purchaseProtocolFCDKESMBO:
                try {
                    ProtocolFCDKESMBO p = gson.fromJson(JsonString, ProtocolFCDKESMBO.class);
                    Prot prot = p.purchaseProtocolFCDKESMBO.body.item.purchaseProtocolFCDKESMBOData;
                    if (prot != null) {
                        prot.Parsing(new Settings(), con);

                    } else {
                        Log.Logger("Не смогли создать объект", f.getAbsolutePath());
                    }
                } catch (Exception e) {
                    Log.Logger(e, e.getStackTrace(), f.getAbsolutePath(), "Error get gson object purchaseProtocolFCDKESMBO");
                }
                break;
            case purchaseProtocolFCODKESMBO:
                try {
                    ProtocolFCODKESMBO p = gson.fromJson(JsonString, ProtocolFCODKESMBO.class);
                    Prot prot = p.purchaseProtocolFCODKESMBO.body.item.purchaseProtocolFCODKESMBOData;
                    if (prot != null) {
                        prot.Parsing(new Settings(), con);

                    } else {
                        Log.Logger("Не смогли создать объект", f.getAbsolutePath());
                    }
                } catch (Exception e) {
                    Log.Logger(e, e.getStackTrace(), f.getAbsolutePath(), "Error get gson object purchaseProtocolFCODKESMBO");
                }
                break;
            case purchaseProtocolFKVOKESMBO:
                try {
                    ProtocolFKVOKESMBO p = gson.fromJson(JsonString, ProtocolFKVOKESMBO.class);
                    Prot prot = p.purchaseProtocolFKVOKESMBO.body.item.purchaseProtocolFKVOKESMBOData;
                    if (prot != null) {
                        prot.Parsing(new Settings(), con);

                    } else {
                        Log.Logger("Не смогли создать объект", f.getAbsolutePath());
                    }
                } catch (Exception e) {
                    Log.Logger(e, e.getStackTrace(), f.getAbsolutePath(), "Error get gson object purchaseProtocolFKVOKESMBO");
                }
                break;
            case purchaseProtocolRejectionAESMBO:
                try {
                    Protocol p = gson.fromJson(JsonString, Protocol.class);
                    Prot prot = p.purchaseProtocol.body.item.purchaseProtocolData;
                    if (prot != null) {
                        prot.Parsing(new Settings(), con);

                    } else {
                        Log.Logger("Не смогли создать объект", f.getAbsolutePath());
                    }
                } catch (Exception e) {
                    Log.Logger(e, e.getStackTrace(), f.getAbsolutePath(), "Error get gson object purchaseProtocolRejectionAESMBO");
                    try {
                        ProtocolRejectionAESMBO p = gson.fromJson(JsonString, ProtocolRejectionAESMBO.class);
                        Prot prot = p.purchaseProtocolRejectionAESMBO.body.item.purchaseProtocolRejectionAESMBOData;
                        if (prot != null) {
                            prot.Parsing(new Settings(), con);

                        } else {
                            Log.Logger("Не смогли создать объект", f.getAbsolutePath());
                        }
                    } catch (Exception ff) {
                        Log.Logger(ff, ff.getStackTrace(), f.getAbsolutePath(), "Error 2 get gson object purchaseProtocolRejectionAESMBO");
                    }
                }
                break;
            case purchaseProtocolRejectionKESMBO:
                try {
                    ProtocolRejectionKESMBO p = gson.fromJson(JsonString, ProtocolRejectionKESMBO.class);
                    Prot prot = p.purchaseProtocolRejectionKESMBO.body.item.purchaseProtocolRejectionKESMBOData;
                    if (prot != null) {
                        prot.Parsing(new Settings(), con);

                    } else {
                        Log.Logger("Не смогли создать объект", f.getAbsolutePath());
                    }
                } catch (Exception e) {
                    Log.Logger(e, e.getStackTrace(), f.getAbsolutePath(), "Error get gson object purchaseProtocolRejectionKESMBO");
                }
                break;
            case purchaseProtocolRejectionZKESMBO:
                try {
                    ProtocolRejectionZKESMBO p = gson.fromJson(JsonString, ProtocolRejectionZKESMBO.class);
                    Prot prot = p.purchaseProtocolRejectionZKESMBO.body.item.purchaseProtocolRejectionZKESMBOData;
                    if (prot != null) {
                        prot.Parsing(new Settings(), con);

                    } else {
                        Log.Logger("Не смогли создать объект", f.getAbsolutePath());
                    }
                } catch (Exception e) {
                    Log.Logger(e, e.getStackTrace(), f.getAbsolutePath(), "Error get gson object purchaseProtocolRejectionZKESMBO");
                }
                break;
            case purchaseProtocolRejectionZPESMBO:
                try {
                    ProtocolRejectionZPESMBO p = gson.fromJson(JsonString, ProtocolRejectionZPESMBO.class);
                    Prot prot = p.purchaseProtocolRejectionZPESMBO.body.item.purchaseProtocolRejectionZPESMBOData;
                    if (prot != null) {
                        prot.Parsing(new Settings(), con);

                    } else {
                        Log.Logger("Не смогли создать объект", f.getAbsolutePath());
                    }
                } catch (Exception e) {
                    Log.Logger(e, e.getStackTrace(), f.getAbsolutePath(), "Error get gson object purchaseProtocolRejectionZPESMBO");
                }
                break;
            case purchaseProtocolRZ1AESMBO:
                try {
                    ProtocolRZ1AESMBO p = gson.fromJson(JsonString, ProtocolRZ1AESMBO.class);
                    Prot prot = p.purchaseProtocolRZ1AESMBO.body.item.purchaseProtocolRZ1AESMBOData;
                    if (prot != null) {
                        prot.Parsing(new Settings(), con);

                    } else {
                        Log.Logger("Не смогли создать объект", f.getAbsolutePath());
                    }
                } catch (Exception e) {
                    Log.Logger(e, e.getStackTrace(), f.getAbsolutePath(), "Error get gson object purchaseProtocolRZ1AESMBO");
                }
                break;
            case purchaseProtocolRZ1KESMBO:
                try {
                    ProtocolRZ1KESMBO p = gson.fromJson(JsonString, ProtocolRZ1KESMBO.class);
                    Prot prot = p.purchaseProtocolRZ1KESMBO.body.item.purchaseProtocolRZ1KESMBOData;
                    if (prot != null) {
                        prot.Parsing(new Settings(), con);

                    } else {
                        Log.Logger("Не смогли создать объект", f.getAbsolutePath());
                    }
                } catch (Exception e) {
                    Log.Logger(e, e.getStackTrace(), f.getAbsolutePath(), "Error get gson object purchaseProtocolRZ1KESMBO");
                }
                break;
            case purchaseProtocolRZ1ZPESMBO:
                try {
                    ProtocolRZ1ZPESMBO p = gson.fromJson(JsonString, ProtocolRZ1ZPESMBO.class);
                    Prot prot = p.purchaseProtocolRZ1ZPESMBO.body.item.purchaseProtocolRZ1ZPESMBOData;
                    if (prot != null) {
                        prot.Parsing(new Settings(), con);

                    } else {
                        Log.Logger("Не смогли создать объект", f.getAbsolutePath());
                    }
                } catch (Exception e) {
                    Log.Logger(e, e.getStackTrace(), f.getAbsolutePath(), "Error get gson object purchaseProtocolRZ1ZPESMBO");
                }
                break;
            case purchaseProtocolRZ2AESMBO:
                try {
                    ProtocolRZ2AESMBO p = gson.fromJson(JsonString, ProtocolRZ2AESMBO.class);
                    Prot prot = p.purchaseProtocolRZ2AESMBO.body.item.purchaseProtocolRZ2AESMBOData;
                    if (prot != null) {
                        prot.Parsing(new Settings(), con);

                    } else {
                        Log.Logger("Не смогли создать объект", f.getAbsolutePath());
                    }
                } catch (Exception e) {
                    Log.Logger(e, e.getStackTrace(), f.getAbsolutePath(), "Error get gson object purchaseProtocolRZ2AESMBO");
                }
                break;
            case purchaseProtocolRZ2KESMBO:
                try {
                    ProtocolRZ2KESMBO p = gson.fromJson(JsonString, ProtocolRZ2KESMBO.class);
                    Prot prot = p.purchaseProtocolRZ2KESMBO.body.item.purchaseProtocolRZ2KESMBOData;
                    if (prot != null) {
                        prot.Parsing(new Settings(), con);

                    } else {
                        Log.Logger("Не смогли создать объект", f.getAbsolutePath());
                    }
                } catch (Exception e) {
                    Log.Logger(e, e.getStackTrace(), f.getAbsolutePath(), "Error get gson object purchaseProtocolRZ2KESMBO");
                }
                break;
            case purchaseProtocolRZ2ZPESMBO:
                try {
                    ProtocolRZ2ZPESMBO p = gson.fromJson(JsonString, ProtocolRZ2ZPESMBO.class);
                    Prot prot = p.purchaseProtocolRZ2ZPESMBO.body.item.purchaseProtocolRZ2ZPESMBOData;
                    if (prot != null) {
                        prot.Parsing(new Settings(), con);

                    } else {
                        Log.Logger("Не смогли создать объект", f.getAbsolutePath());
                    }
                } catch (Exception e) {
                    Log.Logger(e, e.getStackTrace(), f.getAbsolutePath(), "Error get gson object purchaseProtocolRZ2ZPESMBO");
                }
                break;
            case purchaseProtocolRZZKESMBO:
                try {
                    ProtocolRZZKESMBO p = gson.fromJson(JsonString, ProtocolRZZKESMBO.class);
                    Prot prot = p.purchaseProtocolRZZKESMBO.body.item.purchaseProtocolRZZKESMBOData;
                    if (prot != null) {
                        prot.Parsing(new Settings(), con);

                    } else {
                        Log.Logger("Не смогли создать объект", f.getAbsolutePath());
                    }
                } catch (Exception e) {
                    Log.Logger(e, e.getStackTrace(), f.getAbsolutePath(), "Error get gson object purchaseProtocolRZZKESMBO");
                }
                break;
            case purchaseProtocolSummingupAESMBO:
                try {
                    ProtocolSummingupAESMBO p = gson.fromJson(JsonString, ProtocolSummingupAESMBO.class);
                    Prot prot = p.purchaseProtocolSummingupAESMBO.body.item.purchaseProtocolSummingupAESMBOData;
                    if (prot != null) {
                        prot.Parsing(new Settings(), con);

                    } else {
                        Log.Logger("Не смогли создать объект", f.getAbsolutePath());
                    }
                } catch (Exception e) {
                    Log.Logger(e, e.getStackTrace(), f.getAbsolutePath(), "Error get gson object purchaseProtocolSummingupAESMBO");
                }
                break;
            case purchaseProtocolSummingupKESMBO:
                try {
                    ProtocolSummingupKESMBO p = gson.fromJson(JsonString, ProtocolSummingupKESMBO.class);
                    Prot prot = p.purchaseProtocolSummingupKESMBO.body.item.purchaseProtocolSummingupKESMBOData;
                    if (prot != null) {
                        prot.Parsing(new Settings(), con);

                    } else {
                        Log.Logger("Не смогли создать объект", f.getAbsolutePath());
                    }
                } catch (Exception e) {
                    Log.Logger(e, e.getStackTrace(), f.getAbsolutePath(), "Error get gson object purchaseProtocolSummingupKESMBO");
                }
                break;
            case purchaseProtocolSummingupZKESMBO:
                try {
                    ProtocolSummingupZKESMBO p = gson.fromJson(JsonString, ProtocolSummingupZKESMBO.class);
                    Prot prot = p.purchaseProtocolSummingupZKESMBO.body.item.purchaseProtocolSummingupZKESMBOData;
                    if (prot != null) {
                        prot.Parsing(new Settings(), con);

                    } else {
                        Log.Logger("Не смогли создать объект", f.getAbsolutePath());
                    }
                } catch (Exception e) {
                    Log.Logger(e, e.getStackTrace(), f.getAbsolutePath(), "Error get gson object purchaseProtocolSummingupZKESMBO");
                }
                break;
            case purchaseProtocolSummingupZPESMBO:
                try {
                    ProtocolSummingupZPESMBO p = gson.fromJson(JsonString, ProtocolSummingupZPESMBO.class);
                    Prot prot = p.purchaseProtocolSummingupZPESMBO.body.item.purchaseProtocolSummingupZPESMBOData;
                    if (prot != null) {
                        prot.Parsing(new Settings(), con);

                    } else {
                        Log.Logger("Не смогли создать объект", f.getAbsolutePath());
                    }
                } catch (Exception e) {
                    Log.Logger(e, e.getStackTrace(), f.getAbsolutePath(), "Error get gson object purchaseProtocolSummingupZPESMBO");
                }
                break;
            case purchaseProtocolZRPZAESMBO:
                try {
                    ProtocolZRPZAESMBO p = gson.fromJson(JsonString, ProtocolZRPZAESMBO.class);
                    Prot prot = p.purchaseProtocolZRPZAESMBO.body.item.purchaseProtocolZRPZAESMBOData;
                    if (prot != null) {
                        prot.Parsing(new Settings(), con);

                    } else {
                        Log.Logger("Не смогли создать объект", f.getAbsolutePath());
                    }
                } catch (Exception e) {
                    Log.Logger(e, e.getStackTrace(), f.getAbsolutePath(), "Error get gson object purchaseProtocolZRPZAESMBO");
                }
                break;
            case purchaseProtocolZRPZKESMBO:
                try {
                    ProtocolZRPZKESMBO p = gson.fromJson(JsonString, ProtocolZRPZKESMBO.class);
                    Prot prot = p.purchaseProtocolZRPZKESMBO.body.item.purchaseProtocolZRPZKESMBOData;
                    if (prot != null) {
                        prot.Parsing(new Settings(), con);

                    } else {
                        Log.Logger("Не смогли создать объект", f.getAbsolutePath());
                    }
                } catch (Exception e) {
                    Log.Logger(e, e.getStackTrace(), f.getAbsolutePath(), "Error get gson object purchaseProtocolZRPZKESMBO");
                }
                break;
            case purchaseProtocolZRPZZKESMBO:
                try {
                    ProtocolZRPZZKESMBO p = gson.fromJson(JsonString, ProtocolZRPZZKESMBO.class);
                    Prot prot = p.purchaseProtocolZRPZZKESMBO.body.item.purchaseProtocolZRPZZKESMBOData;
                    if (prot != null) {
                        prot.Parsing(new Settings(), con);

                    } else {
                        Log.Logger("Не смогли создать объект", f.getAbsolutePath());
                    }
                } catch (Exception e) {
                    Log.Logger(e, e.getStackTrace(), f.getAbsolutePath(), "Error get gson object purchaseProtocolZRPZZKESMBO");
                }
                break;
            case purchaseProtocolZRPZZPESMBO:
                try {
                    ProtocolZRPZZPESMBO p = gson.fromJson(JsonString, ProtocolZRPZZPESMBO.class);
                    Prot prot = p.purchaseProtocolZRPZZPESMBO.body.item.purchaseProtocolZRPZZPESMBOData;
                    if (prot != null) {
                        prot.Parsing(new Settings(), con);

                    } else {
                        Log.Logger("Не смогли создать объект", f.getAbsolutePath());
                    }
                } catch (Exception e) {
                    Log.Logger(e, e.getStackTrace(), f.getAbsolutePath(), "Error get gson object purchaseProtocolZRPZZPESMBO");
                }
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
