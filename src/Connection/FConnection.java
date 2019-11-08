

package Connection;

import btl.sms.dao.SMSUserDAO;
import com.btlng.RegisterDialog;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import sms.gateway.GateWay;


public class FConnection {

    public static String SMS_HOST;
    public static String CONNECT_DRIVER;
    public static String CONNECT_USERNAME;
    public static String CONNECT_PASSWORD;
    public static String CONNECT_URL;

    public static String initParam(){

        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream("config\\config.properties"));
            CONNECT_DRIVER = prop.getProperty("driver").trim();
            CONNECT_URL = prop.getProperty("url").trim();
            CONNECT_USERNAME = prop.getProperty("username").trim();
            CONNECT_PASSWORD = prop.getProperty("password").trim();
            
            SMS_HOST = prop.getProperty("host").trim();
        } catch (IOException ex) {
            Logger.getLogger(FConnection.class.getName()).log(Level.SEVERE, null, ex);
        }

        return SMS_HOST;
    }

     public static Connection getConnection(){
            initParam();
            Connection con = null;
       try {
            Class.forName(CONNECT_DRIVER);
            con = DriverManager.getConnection(CONNECT_URL,CONNECT_USERNAME, CONNECT_PASSWORD);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Application cannot Connect to your Server!","ERROR IN CONNECTION",JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FConnection.class.getName()).log(Level.SEVERE, null, ex);
        }

            return con;
    }

     public static Connection mysqlConnection(){
            Connection con = null;
       try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/FashionDB","root", "");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Application cannot Connect to your Server!","ERROR IN CONNECTION",JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FConnection.class.getName()).log(Level.SEVERE, null, ex);
        }

            return con;
    }
//
     public static void main(String[] ee){
        Connection cc = getConnection();

         System.out.println(cc);
     }

     public static String checkPoint(){
         String point = "";
         int checkUser = SMSUserDAO.getStatus();

         if(checkUser == 0){

            String username = SMSUserDAO.getUsername().trim();
            String password = SMSUserDAO.getPassword().trim();

            String status = GateWay.getBalance(initParam(),username,password);
            point = status.substring(2).trim();

         }
         else{
            JOptionPane.showMessageDialog(null,"Please Register to Continue!");
            new RegisterDialog(null,"", true);
         }

         return point;
     }
}
