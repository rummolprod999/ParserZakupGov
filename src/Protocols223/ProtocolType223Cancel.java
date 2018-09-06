package Protocols223;

import PurchaseProtocols.Prot;
import PurchaseProtocols.ProtocolCancel;
import com.google.gson.Gson;
import org.json.JSONObject;
import org.json.XML;

import java.io.File;
import java.sql.Connection;

public class ProtocolType223Cancel {
    Region region;
    File f;
    String pathParse;
    String prot;
    TypeProt223 type;
    String ftext;

    public ProtocolType223Cancel(File f, String pathParse, String prot, Region region, TypeProt223 type, String ftext) {
        this.f = f;
        this.pathParse = pathParse;
        this.prot = prot;
        this.region = region;
        this.type = type;
        this.ftext = ftext;

    }

    public void ParserType223Cancel(Connection con) {
        JSONObject jsonObj = null;
        String JsonString = null;
        try {
            jsonObj = XML.toJSONObject(ftext, true);
            JsonString = jsonObj.toString();
            //System.out.println(JsonString);
        } catch (Exception e) {
            Log.Logger("JSON exception on Cancelation", e.getMessage());
            return;
        }
        Gson gson = new Gson();
        switch (type) {
            case purchaseProtocolCancellation:
                try {
                    ProtocolCancel p = gson.fromJson(JsonString, ProtocolCancel.class);
                    Prot prot = p.protocolCancellation.body.item.protocolCancellationData;
                    if (prot != null) {
                        prot.ParsingCancelation(new ProtocolType223Cancel.Settings(), con);

                    } else {
                        Log.Logger("Не смогли создать объект", f.getAbsolutePath());
                    }
                } catch (Exception e) {
                    Log.Logger(e, e.getStackTrace(), f.getAbsolutePath(), "Error get gson object ProtocolCancel");
                }
                break;
        }
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
