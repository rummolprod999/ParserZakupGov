package Protocols223;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import static java.lang.System.out;
import static java.lang.Thread.sleep;

public class Parser implements IParser {

    @Override
    public void Parsing() {

    }

    public String[] protocols223Dir = {"purchaseProtocol", "purchaseProtocolIP", "purchaseProtocolOSZ", "purchaseProtocolPA_AE", "purchaseProtocolPA_OA", "purchaseProtocolPAAE", "purchaseProtocolPAAE94", "purchaseProtocolPAEP", "purchaseProtocolPAOA", "purchaseProtocolRKZ", "purchaseProtocolRZ1AE", "purchaseProtocolRZ2AE", "purchaseProtocolRZ_AE", "purchaseProtocolRZ_OA", "purchaseProtocolRZ_OK", "purchaseProtocolRZAE", "purchaseProtocolRZOA", "purchaseProtocolRZOK", "purchaseProtocolVK", "purchaseProtocolZK"};

    ArrayList<FTPFile> GetListFtp223(String pathParse){
        ArrayList<FTPFile> arr = new ArrayList<>();
        int count = 1;
        while (true){
            try{
                arr = FtpLst(pathParse, "fz223free", "fz223free");
            }
            catch (Exception e){
                if (count > 3)
                {
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

    private ArrayList<FTPFile> FtpLst(String pathParse, String login, String pass) throws IOException {
        FTPClient ftpClient = new FTPClient();
        ftpClient.connect("ftp.zakupki.gov.ru");
        ftpClient.login(login, pass);
        ftpClient.enterLocalPassiveMode();
        ftpClient.changeWorkingDirectory(pathParse);
        return new ArrayList<>(Arrays.asList(ftpClient.listDirectories()));
    }
}
