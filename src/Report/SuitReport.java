
package Report;

import Connection.FConnection;
import java.sql.Connection;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;


public class SuitReport {



    public static JasperPrint getReport(String suitId){

        String url = "Report/Suit.jasper";
        Connection connect = FConnection.getConnection();
        HashMap param = new HashMap();
        param.put("suitId",suitId);
        JasperPrint jp = null;
        try {
             jp = JasperFillManager.fillReport(url, param, connect);
            
        } catch (JRException ex) {
            Logger.getLogger(SuitReport.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jp;
    }
    //
}
