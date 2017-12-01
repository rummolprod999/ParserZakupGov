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
        UrlConnect = String.format("jdbc:mysql://%s:%d/%s?jdbcCompliantTruncation=false&useUnicode=true&characterEncoding=utf-8&useLegacyDatetimeCode=false&serverTimezone=Europe/Moscow", Server, Port, Database);

    }

    private static void ParserProtocols223() {
        Log.Logger("Начало парсинга");
        try {
            ParserProtocols223 p = new ParserProtocols223();
            p.Parsing();
            /*Region r = new Region();
            File f = new File(Main.executePath + File.separator + "purchaseProtocol_Moskva_20171128_000000_20171128_235959_daily_007.xml");
            String ftext = Parser.ClearString(f);
            ProtocolType223 p = new ProtocolType223(f, "ggg", "purchaseProtocol", r, TypeProt223.purchaseProtocol, ftext);
            p.ParserType223();*/
        } catch (Exception e) {
            Log.Logger("Error Main function", e.getStackTrace(), e);
        } finally {
            Log.Logger("Конец парсинга");
            Log.Logger(String.format("Добавлено PurchaseProtocol %d", CountPurchaseProtocol));

        }

    }
}

