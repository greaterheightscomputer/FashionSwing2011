
package DAO;

import Connection.FConnection;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JComboBox;


public class CustomerDAO {
    private static Connection connect;
    private static PreparedStatement ps;
    private static Statement st;
    private static ResultSet rs;

    public static Connection getConnection(){
        connect = FConnection.getConnection();
        return connect;
    }

        public static int insertRecord(String customerId, String firstName, String lastName, String address, String gender, String phone, String email, String state, String country){
        int status = -1;
        String insertquery = "INSERT INTO Customers VALUES(?,?,?,?,?,?,?,?,?)";
        try {
            ps = getConnection().prepareStatement(insertquery);
            ps.setString(1, customerId);
            ps.setString(2, firstName);
            ps.setString(3, lastName);
            ps.setString(4, address);
            ps.setString(5, gender);
            ps.setString(6, phone);
            ps.setString(7, email);
            ps.setString(8, state);
            ps.setString(9, country);

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


    public static int updateRecord(String customerId, String firstName, String lastName, String address, String gender, String phone, String email, String state, String country){
        int status = -1;
    	String updatequery = "update Customers set vFirstName=?, vLastName=?, vAddress=?, cGender=?, cPhone=?, vEmail=?, vState=?, vCountry=? where cCustomerId=?";
        try {
            ps = getConnection().prepareStatement(updatequery);
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ps.setString(3, address);
            ps.setString(4, gender);
            ps.setString(5, phone);
            ps.setString(6, email);
            ps.setString(7, state);
            ps.setString(8, country);
            ps.setString(9, customerId);
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

    public static int deleteRecord(String customerId){

            int status = -1;
            String deleteQuery = "delete from Customers where cCustomerId=?";
            try {
            ps = getConnection().prepareStatement(deleteQuery);
            ps.setString(1, customerId);
            status = ps.executeUpdate();
            if (status > -1) {
                status = 0;
            }
        } catch (SQLException ex) {
             return status;
        }
            finally{
                try{
            if(ps!=null){
            ps.close();
            }
            if(connect!=null){
            connect.close();
            }
                }catch(SQLException ex){}

                return status;
            }
    }

     public static void loadCombo(JComboBox combo){
        String query = "SELECT * FROM Customers";
        combo.removeAllItems();
        combo.addItem("");
        try {
            st = getConnection().createStatement();
            rs = st.executeQuery(query);

            while(rs.next()){
                combo.addItem(rs.getString("cCustomerId").trim()+":"+rs.getString("vFirstName").trim()+" "+rs.getString("vLastName").trim());
            }

        } catch (SQLException ex) {

        }

    }
              
    public static Vector loadRecord(String customerId){
        Vector vector = new Vector();
        String query = "SELECT * FROM Customers WHERE cCustomerId=?";
        try {
            ps = getConnection().prepareStatement(query);
            ps.setString(1, customerId.trim());

            rs = ps.executeQuery();
            if(rs.next()){
                vector.addElement(rs.getString("cCustomerId").trim());
                vector.addElement(rs.getString("vFirstName").trim());
                vector.addElement(rs.getString("vLastName").trim());
                vector.addElement(rs.getString("vAddress").trim());
                vector.addElement(rs.getString("cGender").trim());
                vector.addElement(rs.getString("cPhone").trim());
                vector.addElement(rs.getString("vEmail").trim());
                vector.addElement(rs.getString("vState").trim());
                vector.addElement(rs.getString("vCountry").trim());
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

    public static Vector loadCustomersNumbers(){
        Vector vector = new Vector();
        String query = "SELECT * FROM Customers";
        try {
            st = getConnection().createStatement();

            rs = st.executeQuery(query);
            while(rs.next()){
                vector.addElement(rs.getString("cPhone").trim());
            }
        } catch (SQLException ex) {
            return vector;
        }
        finally{
            try{
                if(rs != null){
                    rs.close();
                }
                if(st != null){
                    st.close();
                }
                if(connect != null){
                    connect.close();
                }
            }catch(Exception ex){
            }
        }

        return vector;
    }

     public static String getCustomerName(String customerId){
        String returnValue = "";
        String query = "SELECT * FROM Customers WHERE cCustomerId = ?";
        try {
            ps = getConnection().prepareStatement(query);
            ps.setString(1, customerId.trim());

            rs = ps.executeQuery();
            if(rs.next()){
                returnValue = rs.getString("cCustomerId").trim()+":"+rs.getString("vFirstName").trim()+" "+rs.getString("vLastName").trim();
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

     public static String getCustomerNumber(String customerId){
        String returnValue = "";
        String query = "SELECT * FROM Customers WHERE cCustomerId = ?";
        try {
            ps = getConnection().prepareStatement(query);
            ps.setString(1, customerId.trim());

            rs = ps.executeQuery();
            if(rs.next()){
                returnValue = rs.getString("cPhone").trim();
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








