package Protocols223;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;

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
                        ParsingDaily223(pathParse, prot, r);
                        break;
                }
            }


        }
    }

    public void GetListFileArch(String pathParse, String prot, Region region, String file) {
        String filea = "";
        String pathUnzip = "";
        filea = GetArch(file, pathParse, Ftp223Login, Ftp223Pass);
        if (!Objects.equals(filea, "")) {
            pathUnzip = Unzip(filea);
            if (!Objects.equals(pathUnzip, "")) {
                File unzp = new File(pathUnzip);
                File[] filelist = unzp.listFiles();
                try (Connection con = DriverManager.getConnection(Main.UrlConnect, Main.UserDb, Main.PassDb)) {
                    for (File f : filelist != null ? filelist : new File[0]) {
                        switch (prot) {
                            case "purchaseProtocol":
                                Bolter(f, pathParse, prot, region, TypeProt223.purchaseProtocol, con);
                                break;
                            case "purchaseProtocolIP":
                                Bolter(f, pathParse, prot, region, TypeProt223.purchaseProtocolIP, con);
                                break;
                            case "PurchaseProtocolOSZ":
                                Bolter(f, pathParse, prot, region, TypeProt223.purchaseProtocolOSZ, con);
                                break;
                            case "purchaseProtocolPA_AE":
                                Bolter(f, pathParse, prot, region, TypeProt223.purchaseProtocolPA_AE, con);
                                break;
                            case "purchaseProtocolPA_OA":
                                Bolter(f, pathParse, prot, region, TypeProt223.purchaseProtocolPA_OA, con);
                                break;
                            case "purchaseProtocolPAAE":
                                Bolter(f, pathParse, prot, region, TypeProt223.purchaseProtocolPAAE, con);
                                break;
                            case "purchaseProtocolPAAE94":
                                Bolter(f, pathParse, prot, region, TypeProt223.purchaseProtocolPAAE94, con);
                                break;
                            case "purchaseProtocolPAEP":
                                Bolter(f, pathParse, prot, region, TypeProt223.purchaseProtocolPAEP, con);
                                break;
                            case "purchaseProtocolPAOA":
                                Bolter(f, pathParse, prot, region, TypeProt223.purchaseProtocolPAOA, con);
                                break;
                            case "purchaseProtocolRKZ":
                                Bolter(f, pathParse, prot, region, TypeProt223.purchaseProtocolRKZ, con);
                                break;
                            case "purchaseProtocolRZ1AE":
                                Bolter(f, pathParse, prot, region, TypeProt223.purchaseProtocolRZ1AE, con);
                                break;
                            case "purchaseProtocolRZ2AE":
                                Bolter(f, pathParse, prot, region, TypeProt223.purchaseProtocolRZ2AE, con);
                                break;
                            case "purchaseProtocolRZ_AE":
                                Bolter(f, pathParse, prot, region, TypeProt223.purchaseProtocolRZ_AE, con);
                                break;
                            case "purchaseProtocolRZ_OA":
                                Bolter(f, pathParse, prot, region, TypeProt223.purchaseProtocolRZ_OA, con);
                                break;
                            case "purchaseProtocolRZ_OK":
                                Bolter(f, pathParse, prot, region, TypeProt223.purchaseProtocolRZ_OK, con);
                                break;
                            case "purchaseProtocolRZAE":
                                Bolter(f, pathParse, prot, region, TypeProt223.purchaseProtocolRZAE, con);
                                break;
                            case "purchaseProtocolRZOA":
                                Bolter(f, pathParse, prot, region, TypeProt223.purchaseProtocolRZOA, con);
                                break;
                            case "purchaseProtocolRZOK":
                                Bolter(f, pathParse, prot, region, TypeProt223.purchaseProtocolRZOK, con);
                                break;
                            case "purchaseProtocolVK":
                                Bolter(f, pathParse, prot, region, TypeProt223.purchaseProtocolVK, con);
                                break;
                            case "purchaseProtocolZK":
                                Bolter(f, pathParse, prot, region, TypeProt223.purchaseProtocolZK, con);
                                break;
                        }

                    }
                } catch (Exception e) {
                    Log.Logger("Error parsing list protocol", e.getStackTrace());
                }
                try {
                    FileUtils.deleteDirectory(unzp);
                } catch (Exception ignored) {

                }
            }

        }
    }

    public void Bolter(File f, String pathParse, String prot, Region region, TypeProt223 type, Connection con) {
        if (!f.getName().toLowerCase().endsWith(".xml") || f.length() == 0) {
            return;
        }
        try {
            ParsingXml(f, pathParse, prot, region, type, con);
        } catch (Exception e) {
            Log.Logger("Ошибка при парсинге xml", e.getStackTrace(), f.getName());
        }

    }

    public void ParsingXml(File f, String pathParse, String prot, Region region, TypeProt223 type, Connection con) {
        String ftext = ClearString(f);
        ProtocolType223 p = new ProtocolType223(f, pathParse, prot, region, type, ftext);
        p.ParserType223(con);
    }

    public void ParsingLast223(String pathParse, String prot, Region region) {
        ArrayList<String> s = GetListArch(pathParse, prot, region);
        if (s.isEmpty()) {
            Log.Logger("Получен пустой список архивов", pathParse);
            return;
        }
        for (String st : s) {
            try {
                GetListFileArch(pathParse, prot, region, st);
            } catch (Exception e) {
                Log.Logger("Error on parsing list from ftp", e.getStackTrace(), e);
            }
        }

    }

    public void ParsingDaily223(String pathParse, String prot, Region region) {
        ArrayList<String> s = GetListArch(pathParse, prot, region);
        s = FilterListMysql(s);
        if (s.isEmpty()) {
            Log.Logger("Получен пустой список архивов", pathParse);
            return;
        }
        for (String st : s) {
            try {
                GetListFileArch(pathParse, prot, region, st);
                InsertArrMysql(st);
            } catch (Exception e) {
                Log.Logger("Error on parsing list from ftp", e.getStackTrace(), e);
            }
        }

    }

    public ArrayList<String> GetListArch(String pathParse, String prot, Region region) {
        ArrayList<String> arr = GetListFtp(pathParse, Ftp223Login, Ftp223Pass);
        ArrayList<String> arrYears = Main.Years.stream().map((String y) -> String.format("%s_%s_%s", prot, region.Path223, y)).collect(Collectors.toCollection(ArrayList::new));

        return arr.stream().filter(s -> arrYears.stream().anyMatch(s::contains)).collect(Collectors.toCollection(ArrayList::new));

    }

    public ArrayList<String> FilterListMysql(ArrayList<String> s) {
        ArrayList<String> temp = new ArrayList<>();
        if (!s.isEmpty()) {
            try (Connection con = DriverManager.getConnection(Main.UrlConnect, Main.UserDb, Main.PassDb)) {
                for (String st : s) {
                    PreparedStatement stmt0 = con.prepareStatement(String.format("SELECT id FROM %sarhiv_protocols223 WHERE arhiv = ?", Main.Prefix));
                    stmt0.setString(1, String.valueOf(st));
                    ResultSet r = stmt0.executeQuery();
                    if (r.next()) {
                        r.close();
                        stmt0.close();
                        continue;
                    }
                    r.close();
                    stmt0.close();
                    temp.add(st);
                }

            } catch (Exception e) {
                Log.Logger("Error Search", e.getStackTrace());
            }
        }
        return temp;
    }

    public void InsertArrMysql(String s) {
        try (Connection con = DriverManager.getConnection(Main.UrlConnect, Main.UserDb, Main.PassDb)) {
            PreparedStatement stmtins = con.prepareStatement(String.format("INSERT INTO %sarhiv_protocols223 SET arhiv = ?", Main.Prefix));
            stmtins.setString(1, s);
            stmtins.executeUpdate();
            stmtins.close();
        } catch (Exception e) {
            Log.Logger("Error Insert", e.getStackTrace());
        }
    }

}
