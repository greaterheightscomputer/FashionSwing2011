

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

public class TrouserDAO {

    private static Connection connect;
    private static PreparedStatement ps;
    private static Statement st;
    private static ResultSet rs;

    public static Connection getConnection(){
        connect = FConnection.getConnection();
        return connect;
    }


    public static int insertRecord(String trouserId, String customerId, String length, String thight, String waist, String lap, String knee, String hip, String bottom, String flap, String styleId, String number, String deposit, String balance, String totalAmount, String date, String dateofCollection){
            int status = -1;
            String insertQuery = "insert into Trousers values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            
            try {

                ps = getConnection().prepareStatement(insertQuery);
                ps.setString(1, trouserId);
                ps.setString(2, customerId);
                ps.setString(3, length);
                ps.setString(4, thight);
                ps.setString(5, waist);
                ps.setString(6, lap);
                ps.setString(7, knee);
                ps.setString(8, hip);
                ps.setString(9, bottom);
                ps.setString(10, flap);
                ps.setString(11, styleId);
                ps.setString(12, number);
                ps.setDouble(13, Double.parseDouble(deposit));
                ps.setDouble(14, Double.parseDouble(balance));
                ps.setDouble(15, Double.parseDouble(totalAmount));
                ps.setString(16, date);
                ps.setString(17, dateofCollection);

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
                ex.printStackTrace();
            }
        }

        return status;
    }
        
    public static int updateRecord(String trouserId, String customerId, String length, String thight, String waist, String lap, String knee, String hip, String bottom, String flap, String styleId, String number, String deposit, String balance, String totalAmount, String date, String dateofCollection){
            int status = -1;
            String updateQuery = "update Trousers set cCustomerId=?, cLength=?, cThight=?, cWaist=?, cLap=?, cKnee=?, cHip=?, cBottom=?, cFlap=?, cStyleId=?, iNumber=?, dDeposit=?, dBalance=?, dTotalAmount=?, dDate=?, dDateofCollection=? where cTrouserId=?";
           
            try {
            ps = getConnection().prepareStatement(updateQuery);
            ps.setString(1, customerId);
            ps.setString(2, length);
            ps.setString(3, thight);
            ps.setString(4, waist);
            ps.setString(5, lap);
            ps.setString(6, knee);
            ps.setString(7, hip);
            ps.setString(8, bottom);
            ps.setString(9, flap);
            ps.setString(10, styleId);
            ps.setString(11, number);
            ps.setDouble(12, Double.parseDouble(deposit));
            ps.setDouble(13, Double.parseDouble(balance));
            ps.setDouble(14, Double.parseDouble(totalAmount));
            ps.setString(15, date);
            ps.setString(16, dateofCollection);
            ps.setString(17, trouserId);
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


    public static int deleteRecord(String trouserId){

            int status = -1;
            String deleteQuery = "delete from Trousers where cTrouserId=?";
            
            try {
            ps = getConnection().prepareStatement(deleteQuery);
            ps.setString(1, trouserId);

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

        String query = "SELECT * FROM Trousers";
        combo.removeAllItems();
        combo.addItem("");
        try {
            st = getConnection().createStatement();
            rs = st.executeQuery(query);

            while(rs.next()){
                combo.addItem(rs.getString("cTrouserId").trim());
            }

        } catch (SQLException ex) {
            
        }

    }
 
     public static Vector loadRecord(String trouserId){
        Vector vector = new Vector();
        String query = "SELECT * FROM Trousers WHERE cTrouserId=?";
        try {
            ps = getConnection().prepareStatement(query);
            ps.setString(1, trouserId.trim());

            rs = ps.executeQuery();
            if(rs.next()){
                vector.addElement(rs.getString("cTrouserId").trim());
                vector.addElement(rs.getString("cCustomerId").trim());
                vector.addElement(rs.getString("cLength").trim());
                vector.addElement(rs.getString("cThight").trim());
                vector.addElement(rs.getString("cWaist").trim());
                vector.addElement(rs.getString("cLap").trim());
                vector.addElement(rs.getString("cKnee").trim());
                vector.addElement(rs.getString("cHip").trim());
                vector.addElement(rs.getString("cBottom").trim());
                vector.addElement(rs.getString("cFlap").trim());
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

   public static String loadSMSMessage(String trouserId){
        String returnValue = "";
        String query = "SELECT * FROM Trousers WHERE cTrouserId = ?";
        try {
            ps = getConnection().prepareStatement(query);
            ps.setString(1, trouserId.trim());

            rs = ps.executeQuery();
            if(rs.next()){

                String TId = rs.getString("cTrouserId").trim();
                String l = rs.getString("cLength").trim();
                String ti = rs.getString("cThight").trim();
                String wa = rs.getString("cWaist").trim();
                String lap = rs.getString("cLap").trim();
                String knee = rs.getString("cKnee").trim();
                String hip = rs.getString("cHip").trim();
                String bottom = rs.getString("cBottom").trim();
                String flap = rs.getString("cFlap").trim();
                String num = rs.getString("iNumber").trim();
                String dept = rs.getString("dDeposit").trim();
                String bal = rs.getString("dBalance").trim();
                String total = rs.getString("dTotalAmount").trim();
                String dfc = rs.getString("dDateofCollection").trim();
                
                String message = "Measurement TId:"+TId+" L:"+l+" T:"+ti+" W:"+wa+" Lap:"+lap+" Kn:"+knee+" Hip:"+hip+" B:"+bottom+" Flap:"+flap+" Num:"+num+" Dept:"+dept+" Bal:"+bal+" Total:"+total+" DFC:"+dfc;
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

   public static String sendTrouserSMS(String trouserId){

        String returnValue = "";
        String query = "SELECT * FROM Trousers WHERE cTrouserId = ?";
        try {
            ps = getConnection().prepareStatement(query);
            ps.setString(1, trouserId.trim());

            rs = ps.executeQuery();
            if(rs.next()){
                String customerId = rs.getString("cCustomerId").trim();
                returnValue = "234"+CustomerDAO.getCustomerNumber(customerId).trim().substring(1);

                Properties prop = new Properties();

                prop.load(new FileInputStream("config\\config.properties"));
                String host = prop.getProperty("host").trim();

                String message = loadSMSMessage(trouserId);
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
