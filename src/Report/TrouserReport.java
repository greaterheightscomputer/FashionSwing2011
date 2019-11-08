
package Report;

import Connection.FConnection;
import java.sql.Connection;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;


public class TrouserReport {



    public static JasperPrint getReport(String trouserId){

        String url = "Report/Trouser.jasper";
        Connection connect = FConnection.getConnection();
        HashMap param = new HashMap();
        param.put("trouserId",trouserId);
        JasperPrint jp = null;
        try {
             jp = JasperFillManager.fillReport(url, param, connect);
            
        } catch (JRException ex) {
            Logger.getLogger(TrouserReport.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jp;
    }
    //
}
