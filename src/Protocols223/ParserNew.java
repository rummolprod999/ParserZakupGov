package Protocols223;

import PurchaseProtocols.Application;
import PurchaseProtocols.Document;
import PurchaseProtocols.ProtocolLotApplications;
import PurchaseProtocols.ProtocolVKLotApplications;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.internal.LinkedTreeMap;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static java.lang.Thread.sleep;

public class ParserNew implements IParser {

    static final String Ftp223Login = "fz223free";
    static final String Ftp223Pass = "VNIMANIE!_otkluchenie_FTP_s_01_01_2025_podrobnee_v_ATFF";
    private static final int BUFFER_SIZE = 4_096;
    public static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    public String[] types = {"purchaseProtocol",
        "purchaseProtocolRZ1AE94FZ",
        "purchaseProtocolPAAE94FZ",
        "purchaseProtocolVK",
        "purchaseProtocolRZOK",
        "purchaseProtocolRZOA",
        "purchaseProtocolRZAE",
        "purchaseProtocolRZ2AE94FZ",
        "purchaseProtocolPAEP",
        "purchaseProtocolPAOA",
        "purchaseProtocolPAAE",
        "purchaseProtocolOSZ",
        "purchaseProtocolZK",
        "purchaseProtocolRZ1KESMBO",
        "purchaseProtocolRZ2KESMBO",
        "purchaseProtocolRZ1AESMBO",
        "purchaseProtocolRZ2AESMBO",
        "purchaseProtocolRZ1ZPESMBO",
        "purchaseProtocolRZ2ZPESMBO",
        "purchaseProtocolFCDKESMBO",
        "purchaseProtocolFKVOKESMBO",
        "purchaseProtocolFCODKESMBO",
        "purchaseProtocolSummingupKESMBO",
        "purchaseProtocolSummingupAESMBO",
        "purchaseProtocolSummingupZKESMBO",
        "purchaseProtocolCCKESMBO",
        "purchaseProtocolCCAESMBO",
        "purchaseProtocolCCZKESMBO",
        "purchaseProtocolCCZPESMBO",
        "purchaseProtocolEvasionKESMBO",
        "purchaseProtocolEvasionAESMBO",
        "purchaseProtocolEvasionZKESMBO",
        "purchaseProtocolEvasionZPESMBO",
        "purchaseProtocolZRPZKESMBO",
        "purchaseProtocolZRPZAESMBO",
        "purchaseProtocolRejectionKESMBO",
        "purchaseProtocolRejectionAESMBO",
        "purchaseProtocolRejectionZKESMBO",
        "purchaseProtocolRejectionZPESMBO",
        "purchaseProtocolZRPZZKESMBO",
        "purchaseProtocolZRPZZPESMBO",
        "purchaseProtocolCollationAESMBO",
        "purchaseProtocolRZZKESMBO",
        "purchaseProtocolSummingupZPESMBO",
        "purchaseProtocolCancellation"};
    public static String ClearString(File s) {
        String res = "";
        try {
            res = new String(Files.readAllBytes(s.toPath()), StandardCharsets.UTF_8);
            res = res.replace("ns2:", "");
            res = res.replace("oos:", "");
            res = res.replace("\u001B", "");
            res = res.replaceAll("ns\\d{1,2}:", "");
        } catch (Exception ignored) {

        }

        return res;
    }

    public static Date GetDate(String dt) {
        Date d = new Date(0L);
        try {
            d = (Date) formatter.parseObject(dt);
        } catch (ParseException ignored) {

        }
        return d;
    }

    @SuppressWarnings("unchecked")
    public static ArrayList<Document> GetDocuments(Object o) {
        ArrayList<Document> a = new ArrayList<>();
        try {
            if (o instanceof ArrayList<?>) {
                ArrayList<LinkedTreeMap> alist = (ArrayList<LinkedTreeMap>) o;
                for (LinkedTreeMap l : alist) {
                    JsonObject jsonObject = new Gson().toJsonTree(l).getAsJsonObject();
                    Document d = new Gson().fromJson(jsonObject.toString(), Document.class);
                    a.add(d);
                }

            } else if (o instanceof LinkedTreeMap) {
                LinkedTreeMap l = (LinkedTreeMap) o;
                JsonObject jsonObject = new Gson().toJsonTree(l).getAsJsonObject();
                Document d = new Gson().fromJson(jsonObject.toString(), Document.class);
                a.add(d);
            }
        } catch (Exception e) {
            Log.Logger("Ошибка получения attachments", e.getStackTrace(), e);
        }
        return a;
    }

    @SuppressWarnings("unchecked")
    public static ArrayList<Application> GetApplications(Object o) {
        ArrayList<Application> a = new ArrayList<>();
        try {
            if (o instanceof ArrayList<?>) {
                ArrayList<LinkedTreeMap> alist = (ArrayList<LinkedTreeMap>) o;
                for (LinkedTreeMap l : alist) {
                    JsonObject jsonObject = new Gson().toJsonTree(l).getAsJsonObject();
                    //Object inn = jsonObject.getAsJsonObject("supplierInfo.inn");
                    Application d = new Gson().fromJson(jsonObject.toString(), Application.class);
                    a.add(d);
                }

            } else if (o instanceof LinkedTreeMap) {
                LinkedTreeMap l = (LinkedTreeMap) o;
                JsonObject jsonObject = new Gson().toJsonTree(l).getAsJsonObject();
                Application d = new Gson().fromJson(jsonObject.toString(), Application.class);
                a.add(d);
            }
        } catch (Exception e) {
            Log.Logger("Ошибка получения applications", e.getStackTrace(), e);
        }
        return a;
    }

    @SuppressWarnings("unchecked")
    public static ArrayList<ProtocolLotApplications> GetProtocolLotApplications(Object o) {
        ArrayList<ProtocolLotApplications> a = new ArrayList<>();
        try {
            if (o instanceof ArrayList<?>) {
                ArrayList<LinkedTreeMap> alist = (ArrayList<LinkedTreeMap>) o;
                for (LinkedTreeMap l : alist) {
                    JsonObject jsonObject = new Gson().toJsonTree(l).getAsJsonObject();
                    ProtocolLotApplications d = new Gson().fromJson(jsonObject.toString(), ProtocolLotApplications.class);
                    a.add(d);
                }

            } else if (o instanceof LinkedTreeMap) {
                LinkedTreeMap l = (LinkedTreeMap) o;
                JsonObject jsonObject = new Gson().toJsonTree(l).getAsJsonObject();
                ProtocolLotApplications d = new Gson().fromJson(jsonObject.toString(), ProtocolLotApplications.class);
                a.add(d);
            }
        } catch (Exception e) {
            Log.Logger("Ошибка получения ProtocolLotApplications", e.getStackTrace(), e);
        }
        return a;
    }


    @SuppressWarnings("unchecked")
    public static ArrayList<ProtocolVKLotApplications> GetProtocolVKLotApplications(Object o) {
        ArrayList<ProtocolVKLotApplications> a = new ArrayList<>();
        try {
            if (o instanceof ArrayList<?>) {
                ArrayList<LinkedTreeMap> alist = (ArrayList<LinkedTreeMap>) o;
                for (LinkedTreeMap l : alist) {
                    JsonObject jsonObject = new Gson().toJsonTree(l).getAsJsonObject();
                    ProtocolVKLotApplications d = new Gson().fromJson(jsonObject.toString(), ProtocolVKLotApplications.class);
                    a.add(d);
                }

            } else if (o instanceof LinkedTreeMap) {
                LinkedTreeMap l = (LinkedTreeMap) o;
                JsonObject jsonObject = new Gson().toJsonTree(l).getAsJsonObject();
                ProtocolVKLotApplications d = new Gson().fromJson(jsonObject.toString(), ProtocolVKLotApplications.class);
                a.add(d);
            }
        } catch (Exception e) {
            Log.Logger("Ошибка получения ProtocolVKLotApplications", e.getStackTrace(), e);
        }
        return a;
    }

    @Override
    public void Parsing() {

    }

    ArrayList<String> GetListFtp(String pathParse, String login, String pass) {
        ArrayList<String> arr = new ArrayList<>();
        int count = 1;
        while (true) {
            try {

                arr = FtpLst(pathParse, login, pass);
                break;
            } catch (Exception e) {
                if (count > 3) {
                    Log.Logger(String.format("Не смогли найти директорию после попытки %d", count), pathParse, e);
                    break;
                }
                count++;
                try {
                    sleep(5_000);
                } catch (InterruptedException ignored) {

                }
            }
        }

        return arr;
    }

    private ArrayList<String> FtpLst(String pathParse, String login, String pass) throws IOException {
        ArrayList<String> s = new ArrayList<>();
        FTPClient ftpClient = new FTPClient();
        ftpClient.setDefaultTimeout(30_000);
        ftpClient.setConnectTimeout(30_000);
        ftpClient.setDataTimeout(30_000);
        ftpClient.setControlKeepAliveReplyTimeout(30_000);
        ftpClient.connect("ftp.zakupki.gov.ru", 21);
        ftpClient.login(login, pass);
        ftpClient.enterLocalPassiveMode();
        ftpClient.changeWorkingDirectory(pathParse);
        s = new ArrayList<>(Arrays.asList(ftpClient.listNames()));
        ftpClient.logout();
        return s;
    }

    public String GetArchThreaded(String arch, String PathParse, String login, String pass) {
        String file = "";
        int count = 1;
        while (true) {
            try {
                file = String.format("%s%s%s", Main.tempDirProtocols, File.separator, arch);
                ExecutorService executor = Executors.newSingleThreadExecutor();
                String finalFile = file;
                Callable<Object> task = () -> GetArchWait(arch, PathParse, login, pass, finalFile);
                Future<Object> future = executor.submit(task);
                try {
                    Object result = future.get(200, TimeUnit.SECONDS);
                } catch (Exception ex) {
                    throw ex;
                } finally {
                    future.cancel(true);
                }
                if (count > 1) {
                    Log.Logger("Удалось скачать архив после попытки", count, PathParse);
                }
                break;

            } catch (Exception e) {

                if (count > 100) {
                    Log.Logger("Не удалось скачать файл после попытки ", count, arch, e.getStackTrace());
                    break;
                }

                count++;
                try {
                    sleep(5_000);
                } catch (InterruptedException ignored) {

                }
            }
        }
        return file;
    }

    public String GetArch(String arch, String PathParse) {
        String file = "";
        int count = 1;
        while (true) {
            try {
                file = String.format("%s%s%s", Main.tempDirProtocols, File.separator, "array.zip");
                OutputStream out = null;
                URLConnection conn = null;
                InputStream in = null;
                try {

                    URL url = new URL(arch);
                    out = new BufferedOutputStream(new FileOutputStream(file));
                    conn = url.openConnection();
                    conn.addRequestProperty("individualPerson_token", Main.Token);
                    in = conn.getInputStream();
                    byte[] buffer = new byte[1024];

                    int numRead;
                    long numWritten = 0;

                    while ((numRead = in.read(buffer)) != -1) {
                        out.write(buffer, 0, numRead);
                        numWritten += numRead;
                    }

                    System.out.println(file + "\t" + numWritten);

                } catch (Exception ex) {

                    throw ex;
                }
                finally {
                    try {
                        if (in != null) {
                            in.close();
                        }
                        if (out != null) {
                            out.close();
                        }
                    } catch (IOException ioe) {
                    }
                }
                if (count > 1) {
                    Log.Logger("Удалось скачать архив после попытки", count, PathParse);
                }
                break;

            } catch (Exception e) {

                if (count > 5) {
                    Log.Logger("Не удалось скачать файл после попытки ", count, arch, e.getStackTrace());
                    break;
                }

                count++;
                try {
                    sleep(5_000);
                } catch (InterruptedException ignored) {

                }
            }
        }
        return file;
    }

    private Object GetArchWait(String arch, String PathParse, String login, String pass, String file) throws Exception {
        FTPClient ftpClient = new FTPClient();
        ftpClient.setConnectTimeout(160_000);
        ftpClient.connect("ftp.zakupki.gov.ru", 21);
        ftpClient.login(login, pass);
        ftpClient.enterLocalPassiveMode();
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        ftpClient.setDataTimeout(150_000);
        ftpClient.setConnectTimeout(30_000);
        ftpClient.setDefaultTimeout(30_000);
        ftpClient.changeWorkingDirectory(PathParse);
        ftpClient.setControlKeepAliveReplyTimeout(160_000);
        ftpClient.setDataTimeout(300_000);
        ftpClient.setControlKeepAliveTimeout(30_000);
        ftpClient.setSoTimeout(30_000);
        boolean success;
        try (OutputStream outputStream1 = new BufferedOutputStream(new FileOutputStream(file))) {
            success = ftpClient.retrieveFile(arch, outputStream1);
        }
        ftpClient.logout();
        if (!success) {
            throw new Exception("errrrrrrrrrrrrrrr");
        }
        return null;
    }

    public String Unzip(String filea) {
        String lDir = "";
        File f = new File(filea);
        if (f.exists()) {
            int p = filea.lastIndexOf('.');
            String dt = filea.substring(0, p);
            File dr = new File(dt);
            dr.mkdir();
            try (ZipInputStream zip = new ZipInputStream(new FileInputStream(filea))) {
                //throw new Exception("ewrwe");
                ZipEntry entry;
                while ((entry = zip.getNextEntry()) != null) {
                    String filePath = String.format("%s%s%s", dt, File.separator, entry.getName());
                    extractFile(zip, filePath);
                }
                lDir = dt;
                //throw new Exception("ewrwe");
            } catch (Exception ex) {

                Log.Logger(ex.getStackTrace(), "Не удалось извлечь файл", ex);
                try {
                    Process pr = Runtime.getRuntime().exec(new String[]{"unzip", "-B", filea, "-d", dt});
                    pr.waitFor();
                    lDir = dt;
                    Log.Logger("Извлекли файл альтернативным методом", filea);
                } catch (Exception e) {
                    Log.Logger("Не удалось извлечь файл альтернативным методом", e, filea);
                }
            }
            f.delete();
        }
        return lDir;
    }

    private void extractFile(ZipInputStream zipIn, String filePath) throws IOException {
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
        byte[] bytesIn = new byte[BUFFER_SIZE];
        int read = 0;
        while ((read = zipIn.read(bytesIn)) != -1) {
            bos.write(bytesIn, 0, read);
        }
        bos.close();
    }
}
