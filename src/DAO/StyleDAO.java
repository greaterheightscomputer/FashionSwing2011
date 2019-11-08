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


public class StyleDAO {

    private static Connection connect;
    private static PreparedStatement ps;
    private static Statement st;
    private static ResultSet rs;

    public static Connection getConnection(){
        connect = FConnection.getConnection();
        return connect;
    }

    public static int insertRecord(String styleId,String styleName,String styleType,String price,String image){
        int status = -1;
        String insertquery = "INSERT INTO Styles VALUES(?,?,?,?,?)";
        try {
            ps = getConnection().prepareStatement(insertquery);
            ps.setString(1, styleId);
            ps.setString(2, styleName);
            ps.setString(3, styleType);
            ps.setDouble(4, Double.parseDouble(price));
            ps.setString(5, image);

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
    
    public static int updateRecord(String styleId,String styleName,String styleType,String price,String image){
        int status = -1;        
    	String updatequery = "update Styles set vStyleName=?, vStyleType=?, dPrice=?, vImage=? where cStyleId=?";
        try {
            ps = getConnection().prepareStatement(updatequery);
            ps.setString(1, styleName);
            ps.setString(2, styleType);
            ps.setDouble(3, Double.parseDouble(price));
            ps.setString(4, image);
            ps.setString(5, styleId);

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

    public static int deleteRecord(String styleId){
            int status = -1;
            String deleteQuery = "delete from Styles where cStyleId=?";
            try {
            ps = getConnection().prepareStatement(deleteQuery);
            ps.setString(1, styleId);
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

        String query = "SELECT * FROM Styles";
        combo.removeAllItems();
        combo.addItem("");
        try {
            st = getConnection().createStatement();
            rs = st.executeQuery(query);

            while(rs.next()){
                combo.addItem(rs.getString("cStyleId").trim()+":"+rs.getString("vStyleName").trim());
            }

        } catch (SQLException ex) {
            
        }


    }

    public static Vector loadCatalogs(String styleType){
        Vector vector = new Vector();
        String query = "SELECT * FROM Styles WHERE vStyleType = ?";
        try {
            ps = getConnection().prepareStatement(query);
            ps.setString(1, styleType.trim());

            rs = ps.executeQuery();
            while(rs.next()){
                vector.addElement(rs.getString("vStyleName").trim());
                vector.addElement(rs.getString("vImage").trim());
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

    public static Vector loadRecord(String styleId){
        Vector vector = new Vector();
        String query = "SELECT * FROM Styles WHERE cStyleId = ?";
        try {
            ps = getConnection().prepareStatement(query);
            ps.setString(1, styleId.trim());

            rs = ps.executeQuery();
            if(rs.next()){
                vector.addElement(rs.getString("cStyleId").trim());
                vector.addElement(rs.getString("vStyleName").trim());
                vector.addElement(rs.getString("vStyleType").trim());
                vector.addElement(rs.getString("dPrice").trim());
                vector.addElement(rs.getString("vImage").trim());
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

    public static String getStyleName(String styleId){
        String returnValue = "";
        String query = "SELECT * FROM Styles WHERE cStyleId = ?";
        try {
            ps = getConnection().prepareStatement(query);
            ps.setString(1, styleId.trim());

            rs = ps.executeQuery();
            if(rs.next()){
                returnValue = rs.getString("cStyleId").trim()+":"+rs.getString("vStyleName").trim();
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

     
    


}


