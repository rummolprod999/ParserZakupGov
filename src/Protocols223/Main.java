package Protocols223;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
    public static int CountPurchaseProtocolCCAESMBO;
    public static int UpCountPurchaseProtocolCCAESMBO;
    public static int CountPurchaseProtocolCCKESMBO;
    public static int UpCountPurchaseProtocolCCKESMBO;
    public static int CountPurchaseProtocolCCZKESMBO;
    public static int UpCountPurchaseProtocolCCZKESMBO;
    public static int UpCountPurchaseProtocolCCZPESMBO;
    public static int CountPurchaseProtocolCCZPESMBO;
    public static int UpCountPurchaseProtocolCollationAESMBO;
    public static int CountPurchaseProtocolCollationAESMBO;
    public static int UpCountPurchaseProtocolEvasionAESMBO;
    public static int CountPurchaseProtocolEvasionAESMBO;
    public static int UpCountPurchaseProtocolEvasionKESMBO;
    public static int CountPurchaseProtocolEvasionKESMBO;
    public static int UpCountPurchaseProtocolEvasionZKESMBO;
    public static int CountPurchaseProtocolEvasionZKESMBO;
    public static int UpCountPurchaseProtocolEvasionZPESMBO;
    public static int CountPurchaseProtocolEvasionZPESMBO;
    public static int UpCountPurchaseProtocolFCDKESMBO;
    public static int CountPurchaseProtocolFCDKESMBO;
    public static int UpCountPurchaseProtocolFCODKESMBO;
    public static int CountPurchaseProtocolFCODKESMBO;
    public static int UpCountPurchaseProtocolFKVOKESMBO;
    public static int CountPurchaseProtocolFKVOKESMBO;
    public static int UpCountPurchaseProtocolRejectionAESMBO;
    public static int CountPurchaseProtocolRejectionAESMBO;
    public static int UpCountPurchaseProtocolRejectionKESMBO;
    public static int CountPurchaseProtocolRejectionKESMBO;
    public static int UpCountPurchaseProtocolRejectionZKESMBO;
    public static int CountPurchaseProtocolRejectionZKESMBO;
    public static int UpCountPurchaseProtocolRejectionZPESMBO;
    public static int CountPurchaseProtocolRejectionZPESMBO;
    public static int UpCountPurchaseProtocolRZ1AESMBO;
    public static int CountPurchaseProtocolRZ1AESMBO;
    public static int UpCountPurchaseProtocolRZ1KESMBO;
    public static int CountPurchaseProtocolRZ1KESMBO;
    public static int UpCountPurchaseProtocolRZ1ZPESMBO;
    public static int CountPurchaseProtocolRZ1ZPESMBO;
    public static int UpCountPurchaseProtocolRZ2AESMBO;
    public static int CountPurchaseProtocolRZ2AESMBO;
    public static int UpCountPurchaseProtocolRZ2KESMBO;
    public static int CountPurchaseProtocolRZ2KESMBO;
    public static int CountPurchaseProtocolRZ2ZPESMBO;
    public static int UpCountPurchaseProtocolRZ2ZPESMBO;
    public static int CountPurchaseProtocolRZZKESMBO;
    public static int UpCountPurchaseProtocolRZZKESMBO;
    public static int CountPurchaseProtocolSummingupAESMBO;
    public static int UpCountPurchaseProtocolSummingupAESMBO;
    public static int CountPurchaseProtocolSummingupKESMBO;
    public static int UpCountPurchaseProtocolSummingupKESMBO;
    public static int CountPurchaseProtocolSummingupZKESMBO;
    public static int UpCountPurchaseProtocolSummingupZKESMBO;
    public static int CountPurchaseProtocolSummingupZPESMBO;
    public static int UpCountPurchaseProtocolSummingupZPESMBO;
    public static int CountPurchaseProtocolZRPZAESMBO;
    public static int UpCountPurchaseProtocolZRPZAESMBO;
    public static int CountPurchaseProtocolZRPZKESMBO;
    public static int UpCountPurchaseProtocolZRPZKESMBO;
    public static int CountPurchaseProtocolZRPZZKESMBO;
    public static int UpCountPurchaseProtocolZRPZZKESMBO;
    public static int CountPurchaseProtocolZRPZZPESMBO;
    public static int UpCountPurchaseProtocolZRPZZPESMBO;
    public static int CountPurchaseProtocolCancel;
    public static int UpCountPurchaseProtocolCancel;
    public static int CountPurchaseRejection;
    public static int UpCountPurchaseRejection;

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
        List<String> list = StreamSupport.stream(Arrays.asList(set.Years.split(",")).spliterator(), false).map(String::trim).collect(Collectors.toList());
        Years = new ArrayList<>(list);
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
            File f = new File(Main.executePath + File.separator + "purchaseProtocolSummingupKESMBO_Moskva_20190901_000000_20190930_235959_001.xml");
            String ftext = Parser.ClearString(f);
            ProtocolType223 p = new ProtocolType223(f, "ggg", "purchaseProtocolSummingupKESMBO", r, TypeProt223.purchaseProtocolSummingupKESMBO, ftext);
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
            Log.Logger(String.format("Добавлено PurchaseProtocolCCAESMBO %d", CountPurchaseProtocolCCAESMBO));
            Log.Logger(String.format("Обновлено PurchaseProtocolCCAESMBO %d", UpCountPurchaseProtocolCCAESMBO));
            Log.Logger(String.format("Добавлено PurchaseProtocolCCKESMBO %d", CountPurchaseProtocolCCKESMBO));
            Log.Logger(String.format("Обновлено PurchaseProtocolCCKESMBO %d", UpCountPurchaseProtocolCCKESMBO));
            Log.Logger(String.format("Добавлено PurchaseProtocolCCZKESMBO %d", CountPurchaseProtocolCCZKESMBO));
            Log.Logger(String.format("Обновлено PurchaseProtocolCCZKESMBO %d", UpCountPurchaseProtocolCCZKESMBO));
            Log.Logger(String.format("Добавлено PurchaseProtocolCCZPESMBO %d", CountPurchaseProtocolCCZPESMBO));
            Log.Logger(String.format("Обновлено PurchaseProtocolCCZPESMBO %d", UpCountPurchaseProtocolCCZPESMBO));
            Log.Logger(String.format("Добавлено PurchaseProtocolCollationAESMBO %d", CountPurchaseProtocolCollationAESMBO));
            Log.Logger(String.format("Обновлено PurchaseProtocolCollationAESMBO %d", UpCountPurchaseProtocolCollationAESMBO));
            Log.Logger(String.format("Добавлено PurchaseProtocolEvasionAESMBO %d", CountPurchaseProtocolEvasionAESMBO));
            Log.Logger(String.format("Обновлено PurchaseProtocolEvasionAESMBO %d", UpCountPurchaseProtocolEvasionAESMBO));
            Log.Logger(String.format("Добавлено PurchaseProtocolEvasionKESMBO %d", CountPurchaseProtocolEvasionKESMBO));
            Log.Logger(String.format("Обновлено PurchaseProtocolEvasionKESMBO %d", UpCountPurchaseProtocolEvasionKESMBO));
            Log.Logger(String.format("Добавлено PurchaseProtocolEvasionZKESMBO %d", CountPurchaseProtocolEvasionZKESMBO));
            Log.Logger(String.format("Обновлено PurchaseProtocolEvasionZKESMBO %d", UpCountPurchaseProtocolEvasionZKESMBO));
            Log.Logger(String.format("Добавлено PurchaseProtocolEvasionZPESMBO %d", CountPurchaseProtocolEvasionZPESMBO));
            Log.Logger(String.format("Обновлено PurchaseProtocolEvasionZPESMBO %d", UpCountPurchaseProtocolEvasionZPESMBO));
            Log.Logger(String.format("Добавлено PurchaseProtocolFCDKESMBO %d", CountPurchaseProtocolFCDKESMBO));
            Log.Logger(String.format("Обновлено PurchaseProtocolFCDKESMBO %d", UpCountPurchaseProtocolFCDKESMBO));
            Log.Logger(String.format("Добавлено PurchaseProtocolFCODKESMBO %d", CountPurchaseProtocolFCODKESMBO));
            Log.Logger(String.format("Обновлено PurchaseProtocolFCODKESMBO %d", UpCountPurchaseProtocolFCODKESMBO));
            Log.Logger(String.format("Добавлено PurchaseProtocolFKVOKESMBO %d", CountPurchaseProtocolFKVOKESMBO));
            Log.Logger(String.format("Обновлено PurchaseProtocolFKVOKESMBO %d", UpCountPurchaseProtocolFKVOKESMBO));
            Log.Logger(String.format("Добавлено PurchaseProtocolRejectionAESMBO %d", CountPurchaseProtocolRejectionAESMBO));
            Log.Logger(String.format("Обновлено PurchaseProtocolRejectionAESMBO %d", UpCountPurchaseProtocolRejectionAESMBO));
            Log.Logger(String.format("Добавлено PurchaseProtocolRejectionKESMBO %d", CountPurchaseProtocolRejectionKESMBO));
            Log.Logger(String.format("Обновлено PurchaseProtocolRejectionKESMBO %d", UpCountPurchaseProtocolRejectionKESMBO));
            Log.Logger(String.format("Добавлено PurchaseProtocolRejectionZKESMBO %d", CountPurchaseProtocolRejectionZKESMBO));
            Log.Logger(String.format("Обновлено PurchaseProtocolRejectionZKESMBO %d", UpCountPurchaseProtocolRejectionZKESMBO));
            Log.Logger(String.format("Добавлено PurchaseProtocolRejectionZPESMBO %d", CountPurchaseProtocolRejectionZPESMBO));
            Log.Logger(String.format("Обновлено PurchaseProtocolRejectionZPESMBO %d", UpCountPurchaseProtocolRejectionZPESMBO));
            Log.Logger(String.format("Добавлено PurchaseProtocolRZ1AESMBO %d", CountPurchaseProtocolRZ1AESMBO));
            Log.Logger(String.format("Обновлено PurchaseProtocolRZ1AESMBO %d", UpCountPurchaseProtocolRZ1AESMBO));
            Log.Logger(String.format("Добавлено PurchaseProtocolRZ1KESMBO %d", CountPurchaseProtocolRZ1KESMBO));
            Log.Logger(String.format("Обновлено PurchaseProtocolRZ1KESMBO %d", UpCountPurchaseProtocolRZ1KESMBO));
            Log.Logger(String.format("Добавлено PurchaseProtocolRZ1ZPESMBO %d", CountPurchaseProtocolRZ1ZPESMBO));
            Log.Logger(String.format("Обновлено PurchaseProtocolRZ1ZPESMBO %d", UpCountPurchaseProtocolRZ1ZPESMBO));
            Log.Logger(String.format("Добавлено PurchaseProtocolRZ2AESMBO %d", CountPurchaseProtocolRZ2AESMBO));
            Log.Logger(String.format("Обновлено PurchaseProtocolRZ2AESMBO %d", UpCountPurchaseProtocolRZ2AESMBO));
            Log.Logger(String.format("Добавлено PurchaseProtocolRZ2KESMBO %d", CountPurchaseProtocolRZ2KESMBO));
            Log.Logger(String.format("Обновлено PurchaseProtocolRZ2KESMBO %d", UpCountPurchaseProtocolRZ2KESMBO));
            Log.Logger(String.format("Добавлено PurchaseProtocolRZ2ZPESMBO %d", CountPurchaseProtocolRZ2ZPESMBO));
            Log.Logger(String.format("Обновлено PurchaseProtocolRZ2ZPESMBO %d", UpCountPurchaseProtocolRZ2ZPESMBO));
            Log.Logger(String.format("Добавлено PurchaseProtocolRZZKESMBO %d", CountPurchaseProtocolRZZKESMBO));
            Log.Logger(String.format("Обновлено PurchaseProtocolRZZKESMBO %d", UpCountPurchaseProtocolRZZKESMBO));
            Log.Logger(String.format("Добавлено PurchaseProtocolSummingupAESMBO %d", CountPurchaseProtocolSummingupAESMBO));
            Log.Logger(String.format("Обновлено PurchaseProtocolSummingupAESMBO %d", UpCountPurchaseProtocolSummingupAESMBO));
            Log.Logger(String.format("Добавлено PurchaseProtocolSummingupKESMBO %d", CountPurchaseProtocolSummingupKESMBO));
            Log.Logger(String.format("Обновлено PurchaseProtocolSummingupKESMBO %d", UpCountPurchaseProtocolSummingupKESMBO));
            Log.Logger(String.format("Добавлено PurchaseProtocolSummingupZKESMBO %d", CountPurchaseProtocolSummingupZKESMBO));
            Log.Logger(String.format("Обновлено PurchaseProtocolSummingupZKESMBO %d", UpCountPurchaseProtocolSummingupZKESMBO));
            Log.Logger(String.format("Добавлено PurchaseProtocolSummingupZPESMBO %d", CountPurchaseProtocolSummingupZPESMBO));
            Log.Logger(String.format("Обновлено PurchaseProtocolSummingupZPESMBO %d", UpCountPurchaseProtocolSummingupZPESMBO));
            Log.Logger(String.format("Добавлено PurchaseProtocolZRPZAESMBO  %d", CountPurchaseProtocolZRPZAESMBO ));
            Log.Logger(String.format("Обновлено PurchaseProtocolZRPZAESMBO  %d", UpCountPurchaseProtocolZRPZAESMBO ));
            Log.Logger(String.format("Добавлено PurchaseProtocolZRPZKESMBO  %d", CountPurchaseProtocolZRPZKESMBO ));
            Log.Logger(String.format("Обновлено PurchaseProtocolZRPZKESMBO  %d", UpCountPurchaseProtocolZRPZKESMBO ));
            Log.Logger(String.format("Добавлено PurchaseProtocolZRPZZKESMBO  %d", CountPurchaseProtocolZRPZZKESMBO ));
            Log.Logger(String.format("Обновлено PurchaseProtocolZRPZZKESMBO  %d", UpCountPurchaseProtocolZRPZZKESMBO ));
            Log.Logger(String.format("Добавлено PurchaseProtocolZRPZZPESMBO  %d", CountPurchaseProtocolZRPZZKESMBO ));
            Log.Logger(String.format("Обновлено PurchaseProtocolZRPZZPESMBO  %d", UpCountPurchaseProtocolZRPZZPESMBO ));
            Log.Logger(String.format("Добавлено ProtocolCancellation %d", CountPurchaseProtocolCancel));
            Log.Logger(String.format("Обновлено ProtocolCancellation %d", UpCountPurchaseProtocolCancel));
            Log.Logger(String.format("Добавлено PurchaseRejection %d", CountPurchaseRejection));
            Log.Logger(String.format("Обновлено PurchaseRejection %d", UpCountPurchaseRejection));

        }

    }
}

