package Protocols223;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.commons.io.FileUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static java.lang.System.out;
import static java.lang.Thread.sleep;

public class Parser implements IParser {

    private static final int BUFFER_SIZE = 4096;
    static final String Ftp223Login = "fz223free";
    static final String Ftp223Pass = "fz223free";

    @Override
    public void Parsing() {

    }

    public String[] protocols223Dir = {"purchaseProtocol", "purchaseProtocolIP", "purchaseProtocolOSZ", "purchaseProtocolPA_AE", "purchaseProtocolPA_OA", "purchaseProtocolPAAE", "purchaseProtocolPAAE94", "purchaseProtocolPAEP", "purchaseProtocolPAOA", "purchaseProtocolRKZ", "purchaseProtocolRZ1AE", "purchaseProtocolRZ2AE", "purchaseProtocolRZ_AE", "purchaseProtocolRZ_OA", "purchaseProtocolRZ_OK", "purchaseProtocolRZAE", "purchaseProtocolRZOA", "purchaseProtocolRZOK", "purchaseProtocolVK", "purchaseProtocolZK"};

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
        ftpClient.abort();
        return s;
    }

    public String GetArch(String arch, String PathParse, String login, String pass) {
        String file = "";
        int count = 1;
        while (true) {
            try {
                file = String.format("%s%s%s", Main.tempDirProtocols, File.separator, arch);
                FTPClient ftpClient = new FTPClient();
                ftpClient.connect("ftp.zakupki.gov.ru", 21);
                ftpClient.login(login, pass);
                ftpClient.enterLocalPassiveMode();
                ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
                ftpClient.changeWorkingDirectory(PathParse);
                OutputStream outputStream1 = new BufferedOutputStream(new FileOutputStream(file));
                boolean success = ftpClient.retrieveFile(arch, outputStream1);
                outputStream1.close();
                if (!success) {
                    throw new Exception("errrrrrrrrrrrrrrr");
                }
                if (count > 1) {
                    Log.Logger("Удалось скачать архив после попытки", count, PathParse);
                }
                break;

            } catch (Exception e) {
                Log.Logger("Не удалось скачать файл", arch, e.getStackTrace());
                if (count > 50) {
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

    public String Unzip(String filea) {
        String lDir = "";
        File f = new File(filea);
        if (f.exists()) {
            int p = filea.lastIndexOf('.');
            String dt = filea.substring(0, p);
            File dr = new File(dt);
            dr.mkdir();
            try (ZipInputStream zip = new ZipInputStream(new FileInputStream(filea))) {
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
                    Process pr = Runtime.getRuntime().exec(new String[]{"unzip", String.format("-B %s -d %s", filea, dt)});
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
