
package Report;

import Connection.FConnection;
import java.sql.Connection;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;


public class ShirtReport {



    public static JasperPrint getReport(String shirtId){

        String url = "Report/Shirt.jasper";
        Connection connect = FConnection.getConnection();
        HashMap param = new HashMap();
        param.put("shirtId",shirtId);
        JasperPrint jp = null;
        try {
             jp = JasperFillManager.fillReport(url, param, connect);
            
        } catch (JRException ex) {
            Logger.getLogger(ShirtReport.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jp;
    }
    //
}
