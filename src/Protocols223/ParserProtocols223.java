package Protocols223;

import java.sql.SQLException;
import java.util.ArrayList;

import static java.lang.System.out;

public class ParserProtocols223 extends Parser{

    @Override
    public void Parsing(){
        ArrayList<Region> reg = new ArrayList<>();
        try {
            reg = GetRegions();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (Region r : reg) {
            //out.println(r.Name);


        }
    }
}
