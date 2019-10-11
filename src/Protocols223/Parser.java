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

public class Parser implements IParser {

    static final String Ftp223Login = "fz223free";
    static final String Ftp223Pass = "fz223free";
    private static final int BUFFER_SIZE = 4096;
    public static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    public String[] __protocols223Dir = {"purchaseProtocol", "purchaseProtocolIP", "purchaseProtocolOSZ", "purchaseProtocolPA_AE", "purchaseProtocolPA_OA", "purchaseProtocolPAAE", "purchaseProtocolPAAE94", "purchaseProtocolPAEP", "purchaseProtocolPAOA", "purchaseProtocolRKZ", "purchaseProtocolRZ1AE", "purchaseProtocolRZ2AE", "purchaseProtocolRZ_AE", "purchaseProtocolRZ_OA", "purchaseProtocolRZ_OK", "purchaseProtocolRZAE", "purchaseProtocolRZOA", "purchaseProtocolRZOK", "purchaseProtocolVK", "purchaseProtocolZK"};
    public String[] protocols223Dir = {"purchasePpurchaseProtocolVKrotocolCancellation", "purchaseProtocol", "purchaseProtocolOSZ", "purchaseProtocolPAAE", "purchaseProtocolPAAE94", "purchaseProtocolPAEP", "purchaseProtocolPAOA", "purchaseProtocolRZ1AE", "purchaseProtocolRZ2AE", "purchaseProtocolRZAE", "purchaseProtocolRZOA", "purchaseProtocolRZOK", "purchaseProtocolVK", "purchaseProtocolZK", "purchaseProtocolCCAESMBO", "purchaseProtocolCCKESMBO", "purchaseProtocolCCZKESMBO", "purchaseProtocolCCZPESMBO", "purchaseProtocolCollationAESMBO", "purchaseProtocolEvasionAESMBO", "purchaseProtocolEvasionKESMBO", "purchaseProtocolEvasionZKESMBO", "purchaseProtocolEvasionZPESMBO", "purchaseProtocolFCDKESMBO", "purchaseProtocolFCODKESMBO", "purchaseProtocolFKVOKESMBO", "purchaseProtocolRejectionAESMBO", "purchaseProtocolRejectionKESMBO", "purchaseProtocolRejectionZKESMBO", "purchaseProtocolRejectionZPESMBO", "purchaseProtocolRZ1AESMBO", "purchaseProtocolRZ1KESMBO", "purchaseProtocolRZ1ZPESMBO", "purchaseProtocolRZ2AESMBO", "purchaseProtocolRZ2KESMBO", "purchaseProtocolRZ2ZPESMBO", "purchaseProtocolRZZKESMBO", "purchaseProtocolSummingupAESMBO", "purchaseProtocolSummingupKESMBO", "purchaseProtocolSummingupZKESMBO", "purchaseProtocolSummingupZPESMBO", "purchaseProtocolZRPZAESMBO", "purchaseProtocolZRPZKESMBO", "purchaseProtocolZRPZZKESMBO", "purchaseProtocolZRPZZPESMBO"};

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
                    sleep(5000);
                } catch (InterruptedException ignored) {

                }
            }
        }

        return arr;
    }

    private ArrayList<String> FtpLst(String pathParse, String login, String pass) throws IOException {
        ArrayList<String> s = new ArrayList<>();
        FTPClient ftpClient = new FTPClient();
        ftpClient.connect("ftp.zakupki.gov.ru", 21);
        ftpClient.login(login, pass);
        ftpClient.enterLocalPassiveMode();
        ftpClient.changeWorkingDirectory(pathParse);
        s = new ArrayList<>(Arrays.asList(ftpClient.listNames()));
        ftpClient.logout();
        return s;
    }

    public String GetArch(String arch, String PathParse, String login, String pass) {
        String file = "";
        int count = 1;
        while (true) {
            try {
                file = String.format("%s%s%s", Main.tempDirProtocols, File.separator, arch);
                ExecutorService executor = Executors.newCachedThreadPool();
                String finalFile = file;
                Callable<Object> task = () -> GetArchWait(arch, PathParse, login, pass, finalFile);
                Future<Object> future = executor.submit(task);
                try {
                    Object result = future.get(200, TimeUnit.SECONDS);
                } catch (TimeoutException | InterruptedException | ExecutionException ex) {
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
                    sleep(5000);
                } catch (InterruptedException ignored) {

                }
            }
        }
        return file;
    }

    private Object GetArchWait(String arch, String PathParse, String login, String pass, String file) throws Exception {
        FTPClient ftpClient = new FTPClient();
        ftpClient.setConnectTimeout(160000);
        ftpClient.connect("ftp.zakupki.gov.ru", 21);
        ftpClient.login(login, pass);
        ftpClient.enterLocalPassiveMode();
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        ftpClient.setDataTimeout(150000);
        ftpClient.changeWorkingDirectory(PathParse);
        OutputStream outputStream1 = new BufferedOutputStream(new FileOutputStream(file));
        boolean success = ftpClient.retrieveFile(arch, outputStream1);
        outputStream1.close();
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
