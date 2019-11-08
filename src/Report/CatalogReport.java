
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


public class CatalogReport {



    public static JasperPrint getReport(String styleType){

        String url = "Report/catalogReport.jasper";
        Connection connect = FConnection.getConnection();
        HashMap param = new HashMap();
        param.put("styleType",styleType);
        JasperPrint jp = null;
        try {
             jp = JasperFillManager.fillReport(url, param, connect);
            
        } catch (JRException ex) {
            Logger.getLogger(CatalogReport.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jp;
    }
    //
}
