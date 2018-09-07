package Protocols223;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import static java.lang.System.out;

public class Main {
    public final static File executePath = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().getPath()).getParentFile();
    public static String Database;
    public static String tempDirProtocols;
    public static String logDirProtocols;
    public static String Prefix;
    public static String UserDb;
    public static String PassDb;
    public static String Server;
    public static int Port;
    public static String logPath;
    public static Date DateNow = new Date();
    public static String UrlConnect;
    public static Arguments arg;
    public static ArrayList<String> Years;
    public static int CountPurchaseProtocol;
    public static int UpCountPurchaseProtocol;
    public static int CountPurchaseProtocolOSZ;
    public static int UpCountPurchaseProtocolOSZ;
    public static int CountPurchaseProtocolPAAE;
    public static int UpCountPurchaseProtocolPAAE;
    public static int CountPurchaseProtocolPAAE94;
    public static int UpCountPurchaseProtocolPAAE94;
    public static int CountPurchaseProtocolPAEP;
    public static int UpCountPurchaseProtocolPAEP;
    public static int CountPurchaseProtocolPAOA;
    public static int UpCountPurchaseProtocolPAOA;
    public static int CountPurchaseProtocolRZ1AE;
    public static int UpCountPurchaseProtocolRZ1AE;
    public static int CountPurchaseProtocolRZ2AE;
    public static int UpCountPurchaseProtocolRZ2AE;
    public static int CountPurchaseProtocolRZAE;
    public static int UpCountPurchaseProtocolRZAE;
    public static int CountPurchaseProtocolRZOA;
    public static int UpCountPurchaseProtocolRZOA;
    public static int CountPurchaseProtocolRZOK;
    public static int UpCountPurchaseProtocolRZOK;
    public static int CountPurchaseProtocolVK;
    public static int UpCountPurchaseProtocolVK;
    public static int CountPurchaseProtocolZK;
    public static int UpCountPurchaseProtocolZK;
    public static int CountPurchaseProtocolCancel;
    public static int UpCountPurchaseProtocolCancel;

    public static void main(String[] args) {
        if (args.length == 0) {
            out.println("Недостаточно аргументов для запуска, используйте last223, daily223");
            System.exit(0);
        }
        String StrArg = args[0];
        switch (StrArg) {
            case "last223":
                Init(Arguments.Last223);
                break;
            case "daily223":
                Init(Arguments.Daily223);
                break;
            default:
                out.println("Неправильно указан аргумент, используйте last223, daily223");
                System.exit(0);
        }
        ParserProtocols223();
    }

    private static void Init(Arguments ar) {
        arg = ar;
        GetSettings set = new GetSettings();
        Database = set.Database;
        tempDirProtocols = set.tempDirProtocols;
        logDirProtocols = set.logDirProtocols;
        Prefix = set.Prefix;
        UserDb = set.UserDb;
        PassDb = set.PassDb;
        Server = set.Server;
        Port = set.Port;
        Years = new ArrayList<>(Arrays.asList(set.Years.split(", ")));
        if (tempDirProtocols.equals("") || tempDirProtocols.isEmpty()) {
            out.println("Не задана папка для временных файлов, выходим из программы");
            System.exit(0);
        }
        if (logDirProtocols.equals("") || logDirProtocols.isEmpty()) {
            out.println("Не задана папка для логов, выходим из программы");
            System.exit(0);
        }
        File tmp = new File(tempDirProtocols);
        if (tmp.exists()) {
            try {
                FileUtils.deleteDirectory(tmp);
            } catch (IOException e) {
                e.printStackTrace();
            }
            tmp.mkdir();
        } else {
            tmp.mkdir();
        }
        File log = new File(logDirProtocols);
        if (!log.exists()) {
            log.mkdir();
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        logPath = String.format("%s%slog_protocols_%s.log", logDirProtocols, File.separator, dateFormat.format(DateNow));
        UrlConnect = String.format("jdbc:mysql://%s:%d/%s?jdbcCompliantTruncation=false&useUnicode=true&characterEncoding=utf-8&useLegacyDatetimeCode=false&serverTimezone=Europe/Moscow&connectTimeout=5000&socketTimeout=30000", Server, Port, Database);

    }

    private static void ParserProtocols223() {
        Log.Logger("Начало парсинга");
        try {
            ParserProtocols223 p = new ParserProtocols223();
            p.Parsing();
            /*Region r = new Region();
            File f = new File(Main.executePath + File.separator + "purchaseProtocolZK_Moskva_20170701_000000_20170731_235959_006.xml");
            String ftext = Parser.ClearString(f);
            ProtocolType223 p = new ProtocolType223(f, "ggg", "purchaseProtocolZK", r, TypeProt223.purchaseProtocolZK, ftext);
            try (Connection con = DriverManager.getConnection(Main.UrlConnect, Main.UserDb, Main.PassDb)) {
                p.ParserType223(con);
            } catch (Exception e) {
                System.out.println(e);
            }*/
        } catch (Exception e) {
            Log.Logger("Error Main function", e.getStackTrace(), e);
        } finally {
            Log.Logger("Конец парсинга");
            Log.Logger(String.format("Добавлено PurchaseProtocol %d", CountPurchaseProtocol));
            Log.Logger(String.format("Обновлено PurchaseProtocol %d", UpCountPurchaseProtocol));
            Log.Logger(String.format("Добавлено PurchaseProtocolOSZ %d", CountPurchaseProtocolOSZ));
            Log.Logger(String.format("Обновлено PurchaseProtocolOSZ %d", UpCountPurchaseProtocolOSZ));
            Log.Logger(String.format("Добавлено PurchaseProtocolPAAE %d", CountPurchaseProtocolPAAE));
            Log.Logger(String.format("Обновлено PurchaseProtocolPAAE %d", UpCountPurchaseProtocolPAAE));
            Log.Logger(String.format("Добавлено PurchaseProtocolPAAE94 %d", CountPurchaseProtocolPAAE94));
            Log.Logger(String.format("Обновлено PurchaseProtocolPAAE94 %d", UpCountPurchaseProtocolPAAE94));
            Log.Logger(String.format("Добавлено PurchaseProtocolPAEP %d", CountPurchaseProtocolPAEP));
            Log.Logger(String.format("Обновлено PurchaseProtocolPAEP %d", UpCountPurchaseProtocolPAEP));
            Log.Logger(String.format("Добавлено PurchaseProtocolPAOA %d", CountPurchaseProtocolPAOA));
            Log.Logger(String.format("Обновлено PurchaseProtocolPAOA %d", UpCountPurchaseProtocolPAOA));
            Log.Logger(String.format("Добавлено PurchaseProtocolRZ1AE %d", CountPurchaseProtocolRZ1AE));
            Log.Logger(String.format("Обновлено PurchaseProtocolRZ1AE %d", UpCountPurchaseProtocolRZ1AE));
            Log.Logger(String.format("Добавлено PurchaseProtocolRZ2AE %d", CountPurchaseProtocolRZ2AE));
            Log.Logger(String.format("Обновлено PurchaseProtocolRZ2AE %d", UpCountPurchaseProtocolRZ2AE));
            Log.Logger(String.format("Добавлено PurchaseProtocolRZAE %d", CountPurchaseProtocolRZAE));
            Log.Logger(String.format("Обновлено PurchaseProtocolRZAE %d", UpCountPurchaseProtocolRZAE));
            Log.Logger(String.format("Добавлено PurchaseProtocolRZOA %d", CountPurchaseProtocolRZOA));
            Log.Logger(String.format("Обновлено PurchaseProtocolRZOA %d", UpCountPurchaseProtocolRZOA));
            Log.Logger(String.format("Добавлено PurchaseProtocolRZOK %d", CountPurchaseProtocolRZOK));
            Log.Logger(String.format("Обновлено PurchaseProtocolRZOK %d", UpCountPurchaseProtocolRZOK));
            Log.Logger(String.format("Добавлено PurchaseProtocolVK %d", CountPurchaseProtocolVK));
            Log.Logger(String.format("Обновлено PurchaseProtocolVK %d", UpCountPurchaseProtocolVK));
            Log.Logger(String.format("Добавлено ProtocolCancellation %d", CountPurchaseProtocolCancel));
            Log.Logger(String.format("Обновлено ProtocolCancellation %d", UpCountPurchaseProtocolCancel));

        }

    }
}

