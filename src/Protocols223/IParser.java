package Protocols223;

import java.sql.*;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public interface IParser {
    void Parsing();

    default ArrayList<Region> GetRegions() throws SQLException {
        ArrayList<Region> arr = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(Main.UrlConnect, Main.UserDb, Main.PassDb)) {
            PreparedStatement reg = con.prepareStatement("SELECT * FROM region");
            ResultSet r = reg.executeQuery();
            while (r.next()) {
                Region region = new Region();
                region.Id = r.getInt("id");
                region.Name = r.getString("name");
                region.Path = r.getString("path");
                region.Conf = r.getString("conf");
                region.Path223 = r.getString("path223");
                arr.add(region);
            }
            reg.close();
            return arr;
        }
    }

}
