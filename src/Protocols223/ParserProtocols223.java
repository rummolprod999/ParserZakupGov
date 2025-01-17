package Protocols223;

import org.xml.sax.InputSource;
import org.apache.commons.io.FileUtils;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;

public class ParserProtocols223 extends ParserNew {

    @Override
    public void Parsing() {
        ArrayList<Region> reg = new ArrayList<>();
        try {
            reg = GetRegions();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (int i = Main.Days; i >= 0; i--) {
            for (Region r : reg) {
                //out.println(r.Name);
                String pathParse = "";
                for (String type : types) {
                    switch (Main.arg) {
                        case Daily223:
                            try {
                                ParsingDaily223(type, r, i);
                            } catch (Exception e) {
                                Log.Logger("Ошибка", e, e.getStackTrace(), type);
                            }
                            break;
                    }
                }
            }

        }
    }


    public Boolean GetListFileArch(String pathParse, String prot, Region region, String file) {
        String filea = "";
        String pathUnzip = "";
        filea = GetArch(file, pathParse);
        if (filea.isEmpty()) {
            return false;
        }
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
                            case "purchaseProtocolOSZ":
                                Bolter(f, pathParse, prot, region, TypeProt223.purchaseProtocolOSZ, con);
                                break;
                            case "purchaseProtocolPA_AE":
                                Bolter(f, pathParse, prot, region, TypeProt223.purchaseProtocolPA_AE, con);
                                break;
                            case "purchaseProtocolPAAE":
                                Bolter(f, pathParse, prot, region, TypeProt223.purchaseProtocolPAAE, con);
                                break;
                            case "purchaseProtocolPAAE94FZ":
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
                            case "purchaseProtocolRZ1AE94FZ":
                                Bolter(f, pathParse, prot, region, TypeProt223.purchaseProtocolRZ1AE, con);
                                break;
                            case "purchaseProtocolRZ2AE94FZ":
                                Bolter(f, pathParse, prot, region, TypeProt223.purchaseProtocolRZ2AE, con);
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
                            case "purchaseProtocolCCAESMBO":
                                Bolter(f, pathParse, prot, region, TypeProt223.purchaseProtocolCCAESMBO, con);
                                break;
                            case "purchaseProtocolCCKESMBO":
                                Bolter(f, pathParse, prot, region, TypeProt223.purchaseProtocolCCKESMBO, con);
                                break;
                            case "purchaseProtocolCCZKESMBO":
                                Bolter(f, pathParse, prot, region, TypeProt223.purchaseProtocolCCZKESMBO, con);
                                break;
                            case "purchaseProtocolCCZPESMBO":
                                Bolter(f, pathParse, prot, region, TypeProt223.purchaseProtocolCCZPESMBO, con);
                                break;
                            case "purchaseProtocolCollationAESMBO":
                                Bolter(f, pathParse, prot, region, TypeProt223.purchaseProtocolCollationAESMBO, con);
                                break;
                            case "purchaseProtocolEvasionAESMBO":
                                Bolter(f, pathParse, prot, region, TypeProt223.purchaseProtocolEvasionAESMBO, con);
                                break;
                            case "purchaseProtocolEvasionKESMBO":
                                Bolter(f, pathParse, prot, region, TypeProt223.purchaseProtocolEvasionKESMBO, con);
                                break;
                            case "purchaseProtocolEvasionZKESMBO":
                                Bolter(f, pathParse, prot, region, TypeProt223.purchaseProtocolEvasionZKESMBO, con);
                                break;
                            case "purchaseProtocolEvasionZPESMBO":
                                Bolter(f, pathParse, prot, region, TypeProt223.purchaseProtocolEvasionZPESMBO, con);
                                break;
                            case "purchaseProtocolFCDKESMBO":
                                Bolter(f, pathParse, prot, region, TypeProt223.purchaseProtocolFCDKESMBO, con);
                                break;
                            case "purchaseProtocolFCODKESMBO":
                                Bolter(f, pathParse, prot, region, TypeProt223.purchaseProtocolFCODKESMBO, con);
                                break;
                            case "purchaseProtocolFKVOKESMBO":
                                Bolter(f, pathParse, prot, region, TypeProt223.purchaseProtocolFKVOKESMBO, con);
                                break;
                            case "purchaseProtocolRejectionAESMBO":
                                Bolter(f, pathParse, prot, region, TypeProt223.purchaseProtocolRejectionAESMBO, con);
                                break;
                            case "purchaseProtocolRejectionKESMBO":
                                Bolter(f, pathParse, prot, region, TypeProt223.purchaseProtocolRejectionKESMBO, con);
                                break;
                            case "purchaseProtocolRejectionZKESMBO":
                                Bolter(f, pathParse, prot, region, TypeProt223.purchaseProtocolRejectionZKESMBO, con);
                                break;
                            case "purchaseProtocolRejectionZPESMBO":
                                Bolter(f, pathParse, prot, region, TypeProt223.purchaseProtocolRejectionZPESMBO, con);
                                break;
                            case "purchaseProtocolRZ1AESMBO":
                                Bolter(f, pathParse, prot, region, TypeProt223.purchaseProtocolRZ1AESMBO, con);
                                break;
                            case "purchaseProtocolRZ1KESMBO":
                                Bolter(f, pathParse, prot, region, TypeProt223.purchaseProtocolRZ1KESMBO, con);
                                break;
                            case "purchaseProtocolRZ1ZPESMBO":
                                Bolter(f, pathParse, prot, region, TypeProt223.purchaseProtocolRZ1ZPESMBO, con);
                                break;
                            case "purchaseProtocolRZ2AESMBO":
                                Bolter(f, pathParse, prot, region, TypeProt223.purchaseProtocolRZ2AESMBO, con);
                                break;
                            case "purchaseProtocolRZ2KESMBO":
                                Bolter(f, pathParse, prot, region, TypeProt223.purchaseProtocolRZ2KESMBO, con);
                                break;
                            case "purchaseProtocolRZ2ZPESMBO":
                                Bolter(f, pathParse, prot, region, TypeProt223.purchaseProtocolRZ2ZPESMBO, con);
                                break;
                            case "purchaseProtocolRZZKESMBO":
                                Bolter(f, pathParse, prot, region, TypeProt223.purchaseProtocolRZZKESMBO, con);
                                break;
                            case "purchaseProtocolSummingupAESMBO":
                                Bolter(f, pathParse, prot, region, TypeProt223.purchaseProtocolSummingupAESMBO, con);
                                break;
                            case "purchaseProtocolSummingupKESMBO":
                                Bolter(f, pathParse, prot, region, TypeProt223.purchaseProtocolSummingupKESMBO, con);
                                break;
                            case "purchaseProtocolSummingupZKESMBO":
                                Bolter(f, pathParse, prot, region, TypeProt223.purchaseProtocolSummingupZKESMBO, con);
                                break;
                            case "purchaseProtocolSummingupZPESMBO":
                                Bolter(f, pathParse, prot, region, TypeProt223.purchaseProtocolSummingupZPESMBO, con);
                                break;
                            case "purchaseProtocolZRPZAESMBO":
                                Bolter(f, pathParse, prot, region, TypeProt223.purchaseProtocolZRPZAESMBO, con);
                                break;
                            case "purchaseProtocolZRPZKESMBO":
                                Bolter(f, pathParse, prot, region, TypeProt223.purchaseProtocolZRPZKESMBO, con);
                                break;
                            case "purchaseProtocolZRPZZKESMBO":
                                Bolter(f, pathParse, prot, region, TypeProt223.purchaseProtocolZRPZZKESMBO, con);
                                break;
                            case "purchaseProtocolZRPZZPESMBO":
                                Bolter(f, pathParse, prot, region, TypeProt223.purchaseProtocolZRPZZPESMBO, con);
                                break;
                            case "purchaseProtocolCancellation":
                                Bolter(f, pathParse, prot, region, TypeProt223.purchaseProtocolCancellation, con);
                                break;
                            case "purchaseRejection":
                                Bolter(f, pathParse, prot, region, TypeProt223.purchaseRejection, con);
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
        return true;
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
        switch (type) {
            case purchaseProtocolCancellation:
                ProtocolType223Cancel p = new ProtocolType223Cancel(f, pathParse, prot, region, type, ftext);
                p.ParserType223Cancel(con);
                break;
            default:
                ProtocolType223 b = new ProtocolType223(f, pathParse, prot, region, type, ftext);
                b.ParserType223(con);
                break;
        }

    }

    public void ParsingDaily223(String type, Region region, int i) throws Exception{
        ArrayList<String> s = GetListArch(type, region.Conf, i);
        if (s.isEmpty()) {
            Log.Logger(String.format("Получен пустой список архивов type %s region %s", type, region.Conf));
            return;
        }
        for (String st : s) {
            try {
                GetListFileArch(type, type, region, st);
            } catch (Exception e) {
                Log.Logger("Error on parsing list from api", e.getStackTrace(), e);
            }
        }

    }

    public ArrayList<String> GetListArch(String type, String region, int i) throws Exception {
        ArrayList<String> arr = new ArrayList<>();
        String response = soap44PriceReq(region, type, i);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputSource inputSource = new InputSource(new StringReader(response));
        Document document = builder.parse(inputSource);
        XPath xPath = XPathFactory.newInstance().newXPath();
        String expression = "//dataInfo/archiveUrl";
        NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(document, XPathConstants.NODESET);
        for (int k = 0; k < nodeList.getLength(); k++) {
            String l = nodeList.item(k).getFirstChild().getTextContent();
            arr.add(l);
        }
        return arr;
        //return arr.stream().filter(s -> arrYears.stream().anyMatch(s::contains)).collect(Collectors.toCollection(ArrayList::new));

    }

    public static String soap44PriceReq(String regionKladr, String type, int d) {
        int count = 5;
        int sleep = 2000;
        while (true) {
            try {
                String guid = java.util.UUID.randomUUID().toString();
                String currdate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(Calendar.getInstance().getTime());
                LocalDate prev =LocalDate.now().minusDays(d);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String prevDate = prev.format(formatter);
                String request = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ws=\"http://zakupki.gov.ru/fz44/get-docs-ip/ws\">\n" +
                    "<soapenv:Header>\n" +
                    "<individualPerson_token>"+Main.Token+"</individualPerson_token>\n" +
                    "</soapenv:Header>\n" +
                    "<soapenv:Body>\n" +
                    "<ws:getDocsByOrgRegionRequest>\n" +
                    "<index>\n" +
                    "<id>"+ guid +"</id>\n" +
                    "<createDateTime>"+currdate+"</createDateTime>\n" +
                    "<mode>PROD</mode>\n" +
                    "</index>\n" +
                    "<selectionParams>\n" +
                    "<orgRegion>"+regionKladr+"</orgRegion>\n" +
                    "<subsystemType>RI223</subsystemType>\n" +
                    "<documentType223>"+type+"</documentType223>\n" +
                    "<periodInfo>\n" +
                    "<exactDate>"+prevDate+"</exactDate>\n" +
                    "</periodInfo>\n" +
                    "</selectionParams>\n" +
                    "</ws:getDocsByOrgRegionRequest>\n" +
                    "</soapenv:Body>\n" +
                    "</soapenv:Envelope>";
                URL url = new URL("https://int44.zakupki.gov.ru/eis-integration/services/getDocsIP");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setConnectTimeout(20000);
                connection.setReadTimeout(20000);
                connection.setDoOutput(true);

                connection.setUseCaches(true);
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Accept", "*/xml");
                connection.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
                connection.setRequestProperty("User-Agent", "");
                connection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
                OutputStream outputStream = connection.getOutputStream();
                byte[] b = request.getBytes("UTF-8");
                outputStream.write(b);
                outputStream.flush();
                outputStream.close();
                InputStream inputStream = connection.getInputStream();
                byte[] res = new byte[2048];
                int i = 0;
                StringBuilder response = new StringBuilder();
                while ((i = inputStream.read(res)) != -1) {
                    response.append(new String(res, 0, i));
                }
                inputStream.close();
                String resp = new String(response.toString());
                return resp;

            } catch (Exception e) {
                if (count <= 0) {
                    throw new RuntimeException(e);
                }

                count--;
                try {
                    Thread.sleep(sleep);
                } catch (Exception t) {

                }
                sleep *= 2;
            }
        }
    }

}
