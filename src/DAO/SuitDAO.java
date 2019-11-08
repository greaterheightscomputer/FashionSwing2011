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

public class SuitDAO {

    private static Connection connect;
    private static PreparedStatement ps;
    private static Statement st;
    private static ResultSet rs;

    public static Connection getConnection(){
        connect = FConnection.getConnection();
        return connect;
    }


    public static int insertRecord(String jacketId, String customerId, String length, String elbow, String chest, String waist, String sleeve, String rS, String hip, String s, String back, String armhole, String styleId, String number, String deposit, String balance, String totalAmount, String date, String dateofCollection){
            int status = -1;
            String insertQuery = "insert into Jackets values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            try {
            ps = getConnection().prepareStatement(insertQuery);
            ps.setString(1, jacketId);
            ps.setString(2, customerId);
            ps.setString(3, length);
            ps.setString(4, elbow);
            ps.setString(5, chest);
            ps.setString(6, waist);
            ps.setString(7, sleeve);
            ps.setString(8, rS);
            ps.setString(9, hip);
            ps.setString(10, s);
            ps.setString(11, back);
            ps.setString(12, armhole);
            ps.setString(13, styleId);
            ps.setString(14, number);
            ps.setDouble(15, Double.parseDouble(deposit));
            ps.setDouble(16, Double.parseDouble(balance));
            ps.setDouble(17, Double.parseDouble(totalAmount));
            ps.setString(18, date);
            ps.setString(19, dateofCollection);
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
   

    public static int updateRecord(String jacketId, String customerId, String length, String elbow, String chest, String waist, String sleeve, String rS, String hip, String s, String back, String armhole, String styleId, String number, String deposit, String balance, String totalAmount, String date, String dateofCollection){
            int status = -1;
            String updateQuery = "update Jackets set cCustomerId=?, cLength=?, cElbow=?, cChest=?, cWaist=?, cSleeve=?, cRS=?, cHip=?, cS=?, cBack=?, cArmhole=?, cStyleId=?, iNumber=?, dDeposit=?, dBalance=?, dTotalAmount=?, dDate=?, dDateofCollection=? where cJacketId=?";

            try {
            ps = getConnection().prepareStatement(updateQuery);
            ps.setString(1, customerId);
            ps.setString(2, length);
            ps.setString(3, elbow);
            ps.setString(4, chest);
            ps.setString(5, waist);
            ps.setString(6, sleeve);
            ps.setString(7, rS);
            ps.setString(8, hip);
            ps.setString(9, s);
            ps.setString(10, back);
            ps.setString(11, armhole);
            ps.setString(12, styleId);
            ps.setString(13, number);
            ps.setDouble(14, Double.parseDouble(deposit));
            ps.setDouble(15, Double.parseDouble(balance));
            ps.setDouble(16, Double.parseDouble(totalAmount));
            ps.setString(17, date);
            ps.setString(18, dateofCollection);
            ps.setString(19, jacketId);
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


    public static int deleteRecord(String jacketId){
            int status = -1;
            String deleteQuery = "delete from Jackets where cJacketId=?";

            try {
            ps = getConnection().prepareStatement(deleteQuery);
            ps.setString(1, jacketId);

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

        String query = "SELECT * FROM Jackets";
        combo.removeAllItems();
        combo.addItem("");
        try {
            st = getConnection().createStatement();
            rs = st.executeQuery(query);

            while(rs.next()){
                combo.addItem(rs.getString("cJacketId").trim());
            }

        } catch (SQLException ex) {

        }
    }

     public static Vector loadRecord(String jacketId){
        Vector vector = new Vector();
        String query = "SELECT * FROM Jackets WHERE cJacketId=?";
        try {
            ps = getConnection().prepareStatement(query);
            ps.setString(1, jacketId.trim());
 
            rs = ps.executeQuery();
            if(rs.next()){
                vector.addElement(rs.getString("cJacketId").trim());
                vector.addElement(rs.getString("cCustomerId").trim());
                vector.addElement(rs.getString("cLength").trim());
                vector.addElement(rs.getString("cElbow").trim());
                vector.addElement(rs.getString("cChest").trim());
                vector.addElement(rs.getString("cWaist").trim());
                vector.addElement(rs.getString("cSleeve").trim());
                vector.addElement(rs.getString("cRS").trim());
                vector.addElement(rs.getString("cHip").trim());
                vector.addElement(rs.getString("cS").trim());
                vector.addElement(rs.getString("cBack").trim());
                vector.addElement(rs.getString("cArmhole").trim());
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
    }public static String loadSMSMessage(String jacketId){
        String returnValue = "";
        String query = "SELECT * FROM Jackets WHERE cJacketId = ?";
        try {
            ps = getConnection().prepareStatement(query);
            ps.setString(1, jacketId.trim());

            rs = ps.executeQuery();
            if(rs.next()){
                
                String JId = rs.getString("cJacketId").trim();
                String l = rs.getString("cLength").trim();
                String e = rs.getString("cElbow").trim();
                String c = rs.getString("cChest").trim();
                String w = rs.getString("cWaist").trim();
                String sl = rs.getString("cSleeve").trim();
                String r = rs.getString("cRS").trim();
                String h = rs.getString("cHip").trim();
                String s = rs.getString("cS").trim();
                String b = rs.getString("cBack").trim();
                String a = rs.getString("cArmhole").trim();
                String num = rs.getString("iNumber").trim();
                String dept = rs.getString("dDeposit").trim();
                String bal = rs.getString("dBalance").trim();
                String total = rs.getString("dTotalAmount").trim();
                String dfc = rs.getString("dDateofCollection").trim();
                
                String message = "JacketMea JId:"+JId+" L:"+l+" E:"+e+" C:"+c+" W:"+w+" Sl:"+sl+" RS:"+r+" Hip:"+h+" S:"+s+" B:"+b+" Ar:"+a+" Num:"+num+" Dept:"+dept+" Bal:"+bal+" Total:"+total+" DFC:"+dfc;
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

   public static String sendJacketSMS(String jacketId){

        String returnValue = "";
        String query = "SELECT * FROM Jackets WHERE cJacketId = ?";
        try {
            ps = getConnection().prepareStatement(query);
            ps.setString(1, jacketId.trim());

            rs = ps.executeQuery();
            if(rs.next()){
                String customerId = rs.getString("cCustomerId").trim();
                returnValue = "234"+CustomerDAO.getCustomerNumber(customerId).trim().substring(1);

                Properties prop = new Properties();

                prop.load(new FileInputStream("config\\config.properties"));
                String host = prop.getProperty("host").trim();

                String message = loadSMSMessage(jacketId);
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
