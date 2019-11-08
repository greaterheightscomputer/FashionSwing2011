package DAO;

import Connection.FConnection;
import btl.sms.dao.SMSUserDAO;
import com.btlng.RegisterDialog;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Vector;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import sms.gateway.GateWay;

public class NativeDAO {

    private static Connection connect;
    private static PreparedStatement ps;
    private static Statement st;
    private static ResultSet rs;

    public static Connection getConnection(){
        connect = FConnection.getConnection();
        return connect;
    }

    public static int insertRecord(String nativeId, String customerId, String length, String shoulder, String sleeve, String rS, String back, String thight, String bottom, String neck, String burst, String styleId, String number, String deposit, String balance, String totalAmount, String date, String dateofCollection){
            int status = -1;
            String insertQuery = "insert into Natives values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            try {
            ps = getConnection().prepareStatement(insertQuery);
            ps.setString(1, nativeId);
            ps.setString(2, customerId);
            ps.setString(3, length);
            ps.setString(4, shoulder); 
            ps.setString(5, sleeve);
            ps.setString(6, rS);
            ps.setString(7, back);
            ps.setString(8, thight);
            ps.setString(9, bottom);
            ps.setString(10, neck);
            ps.setString(11, burst);
            ps.setString(12, styleId);
            ps.setString(13, number);
            ps.setDouble(14, Double.parseDouble(deposit));
            ps.setDouble(15, Double.parseDouble(balance));
            ps.setDouble(16, Double.parseDouble(totalAmount));
            ps.setString(17, date);
            ps.setString(18, dateofCollection);
            status = ps.executeUpdate();
            if(status != -1){
                status = 0;
            }

        } catch (SQLException ex) {
            return status;
        }
        finally{
            try{
                if(ps != null){
                    ps.close();
                }
                if(connect != null){
                    connect.close();
                }
            }catch(Exception ex){

            }
        }

        return status;
    }


    public static int updateRecord(String nativeId, String customerId, String length, String shoulder, String sleeve, String rS, String back, String thight, String bottom, String neck, String burst, String styleId, String number, String deposit, String balance, String totalAmount, String date, String dateofCollection){
            int status = -1;
            String updateQuery = "update Natives set cCustomerId=?, cLength=?, cShoulder=?, cSleeve=?, cRS=?, cBack=?, cThight=?, cBottom=?, cNeck=?, cBurst=?, cStyleId=?, iNumber=?, dDeposit=?, dBalance=?, dTotalAmount=?, dDate=?, dDateofCollection=? where cNativeId=?";

            try {
            ps = getConnection().prepareStatement(updateQuery);            
            ps.setString(1, customerId);
            ps.setString(2, length);
            ps.setString(3, shoulder);
            ps.setString(4, sleeve);
            ps.setString(5, rS);
            ps.setString(6, back);
            ps.setString(7, thight);
            ps.setString(8, bottom);
            ps.setString(9, neck);
            ps.setString(10, burst);
            ps.setString(11, styleId);
            ps.setString(12, number);
            ps.setDouble(13, Double.parseDouble(deposit));
            ps.setDouble(14, Double.parseDouble(balance));
            ps.setDouble(15, Double.parseDouble(totalAmount));
            ps.setString(16, date);
            ps.setString(17, dateofCollection);
            ps.setString(18, nativeId);
            status = ps.executeUpdate();
            if(status != -1){
                status = 0;
            }

        } catch (SQLException ex) {
            return status;
        }
        finally{
            try{
                if(ps != null){
                    ps.close();
                }
                if(connect != null){
                    connect.close();
                }
            }catch(Exception ex){

            }
        }

        return status;
    }


    public static int deleteRecord(String nativeId){
            int status = -1;
            String deleteQuery = "delete from Natives where cNativeId=?";

            try {
            ps = getConnection().prepareStatement(deleteQuery);
            ps.setString(1, nativeId);

            status = ps.executeUpdate();
            if(status != -1){
                status = 0;
            }

        } catch (SQLException ex) {
            return status;
        }
        finally{
            try{
                if(ps != null){
                    ps.close();
                }
                if(connect != null){
                    connect.close();
                }
            }catch(Exception ex){

            }
        }

        return status;
    }


    public static void loadCombo(JComboBox combo){

        String query = "SELECT * FROM Natives";
        combo.removeAllItems();
        combo.addItem("");
        try {
            st = getConnection().createStatement();
            rs = st.executeQuery(query);

            while(rs.next()){
                combo.addItem(rs.getString("cNativeId").trim());
            }
        } catch (SQLException ex) {

        }
    }

     public static Vector loadRecord(String nativeId){
        Vector vector = new Vector();
        String query = "SELECT * FROM Natives WHERE cNativeId=?";
        try {
            ps = getConnection().prepareStatement(query);
            ps.setString(1, nativeId.trim());

            rs = ps.executeQuery();
            if(rs.next()){
                vector.addElement(rs.getString("cNativeId").trim());
                vector.addElement(rs.getString("cCustomerId").trim());
                vector.addElement(rs.getString("cLength").trim());
                vector.addElement(rs.getString("cShoulder").trim());
                vector.addElement(rs.getString("cSleeve").trim());
                vector.addElement(rs.getString("cRS").trim());
                vector.addElement(rs.getString("cBack").trim());
                vector.addElement(rs.getString("cThight").trim());
                vector.addElement(rs.getString("cBottom").trim());
                vector.addElement(rs.getString("cNeck").trim());
                vector.addElement(rs.getString("cBurst").trim());
                vector.addElement(rs.getString("cStyleId").trim());
                vector.addElement(rs.getString("iNumber").trim());
                vector.addElement(rs.getString("dDeposit").trim());
                vector.addElement(rs.getString("dBalance").trim());
                vector.addElement(rs.getString("dTotalAmount").trim());
                vector.addElement(rs.getString("dDate").trim());
                vector.addElement(rs.getString("dDateofCollection").trim());
                }
        } catch (SQLException ex) {
            return vector;
        }
        finally{
            try{
                if(rs != null){
                    rs.close();
                }
                if(ps != null){
                    ps.close();
                }
                if(connect != null){
                    connect.close();
                }
            }catch(Exception ex){
            }
        }

        return vector;
    }

     public static String loadSMSMessage(String nativeId){
        String returnValue = "";
        String query = "SELECT * FROM Natives WHERE cNativeId = ?";
        try {
            ps = getConnection().prepareStatement(query);
            ps.setString(1, nativeId.trim());

            rs = ps.executeQuery();
            if(rs.next()){

                String NId = rs.getString("cNativeId").trim();
                String l = rs.getString("cLength").trim();
                String sh = rs.getString("cShoulder").trim();
                String sl = rs.getString("cSleeve").trim();
                String r = rs.getString("cRS").trim();
                String ba = rs.getString("cBack").trim();
                String th = rs.getString("cThight").trim();
                String bo = rs.getString("cBottom").trim();
                String n = rs.getString("cNeck").trim();
                String bu = rs.getString("cBurst").trim();
                String num = rs.getString("iNumber").trim();
                String dept = rs.getString("dDeposit").trim();
                String bal = rs.getString("dBalance").trim();
                String total = rs.getString("dTotalAmount").trim();
                String dfc = rs.getString("dDateofCollection").trim();

                String message = "Measurement NId:"+NId+" L:"+l+" Sh:"+sh+" SL:"+sl+" RS:"+r+" Ba:"+ba+" Th:"+th+" Bo:"+bo+" Ne:"+n+" Bu:"+bu+" Num:"+num+" Dept:"+dept+" Bal:"+bal+" Total:"+total+" DFC:"+dfc;
                returnValue = message.trim().replaceAll(" ", "%20");
            }
        } catch (SQLException ex) {
            return returnValue;
        }
        finally{
            try{
                if(rs != null){
                    rs.close();
                }
                if(ps != null){
                    ps.close();
                }
                if(connect != null){
                    connect.close();
                }
            }catch(Exception ex){
            }
        }

        return returnValue;
    }

   public static String sendNativeSMS(String nativeId){

        String returnValue = "";
        String query = "SELECT * FROM Natives WHERE cNativeId = ?";
        try {
            ps = getConnection().prepareStatement(query);
            ps.setString(1, nativeId.trim());

            rs = ps.executeQuery();
            if(rs.next()){
                String customerId = rs.getString("cCustomerId").trim();
                returnValue = "234"+CustomerDAO.getCustomerNumber(customerId).trim().substring(1);

                Properties prop = new Properties();

                prop.load(new FileInputStream("config\\config.properties"));
                String host = prop.getProperty("host").trim();

                String message = loadSMSMessage(nativeId);
                sendMessage(host,returnValue, message);
            }
        } catch (IOException ex) {
            return returnValue;
        } catch (SQLException ex) {
            return returnValue;
        }
        finally{
            try{
                if(rs != null){
                    rs.close();
                }
                if(ps != null){
                    ps.close();
                }
                if(connect != null){
                    connect.close();
                }
            }catch(Exception ex){
            }
        }

        return returnValue;
    }

   private static void sendMessage(String host,String number,String message){

        int checkUser = SMSUserDAO.getStatus();
        if(checkUser == 0){

            String username = SMSUserDAO.getUsername().trim();
            String password = SMSUserDAO.getPassword().trim();

            String value = GateWay.sendSMS(host,username,password,"FashionPro",number,message);
            if(!value.equals("") || value != null){
                JOptionPane.showMessageDialog(null,"Message Successfully Delivered to "+value.substring(2).trim()+" Number(s)", "SUCCESS TRANACTION", JOptionPane.INFORMATION_MESSAGE);

            }else{
                JOptionPane.showMessageDialog(null,"Error in Connection, Check your Connection and try Again!", "ERROR IN CONNECTION", JOptionPane.ERROR_MESSAGE);
            }

        }
        else{
            JOptionPane.showMessageDialog(null,"Please You have Register for our SMS before using it!");
            new RegisterDialog(null,"", true);
        }

    }

}
