package Protocols223;

import org.apache.commons.net.ftp.FTPFile;

import java.sql.SQLException;
import java.util.ArrayList;

import static java.lang.System.out;

public class ParserProtocols223 extends Parser {

    @Override
    public void Parsing() {
        ArrayList<Region> reg = new ArrayList<>();
        try {
            reg = GetRegions();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (Region r : reg) {
            //out.println(r.Name);
            String pathParse = "";
            for (String prot : protocols223Dir) {
                switch (Main.arg) {
                    case Last223:
                        pathParse = String.format("/out/published/%s/%s/", r.Path223, prot);
                        ParsingLast223(pathParse, prot, r);
                        break;
                    case Daily223:
                        pathParse = String.format("/out/published/%s/%s/daily/", r.Path223, prot);
                        break;
                }
            }


        }
    }

    public void ParsingLast223(String pathParse, String prot, Region region) {
        GetListArchLast(pathParse, prot, region);

    }

    public ArrayList<FTPFile> GetListArchLast(String pathParse, String prot, Region region) {
        ArrayList<FTPFile> arr = new ArrayList<>();
        for (FTPFile ftpFile : arr = GetListFtp223(pathParse)) {
            out.println(ftpFile.getName());
        }


        return arr;

    }
}
