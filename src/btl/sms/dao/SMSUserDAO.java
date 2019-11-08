package btl.sms.dao;

import Connection.FConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


public class SMSUserDAO {

    private static Connection connect;
    private static Statement st;
    private static PreparedStatement ps;
    private static ResultSet rs;


    private static Connection getConnection(){
        connect = FConnection.getConnection();
        return connect;
    }

    public static int insertRecord(String username,String password){
        int status = -1;
        String query = "INSERT INTO SMSUsers VALUES (?,?,0)";
        try {
            ps = getConnection().prepareStatement(query);
            ps.setString(1,username);
            ps.setString(2,password);

            status = ps.executeUpdate();
            if(status == 1){
                status = 0;
            }

        } catch (SQLException ex) {
            Logger.getLogger(SMSUserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try{
                if(ps != null){
                    ps.close();
                }
                if(connect != null){
                    connect.close();
                }
            }catch(SQLException ex){

            }
        }

        return status;
    }

    public static int updateRecord(String username,String password){
        int status = -1;
        String query = "UPDATE SMSUsers SET cUsername=?,cPassword=?,iStatus=0";
        try {
            ps = getConnection().prepareStatement(query);
            ps.setString(1,username);
            ps.setString(2,password);

            status = ps.executeUpdate();
            if(status == 1){
                status = 0;
            }

        } catch (SQLException ex) {
            Logger.getLogger(SMSUserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try{
                if(ps != null){
                    ps.close();
                }
                if(connect != null){
                    connect.close();
                }
            }catch(SQLException ex){

            }
        }

        return status;
    }

    public static String getUsername(){
        String status = "";
        String query = "SELECT cUsername FROM SMSUsers";
        try {
            st = getConnection().createStatement();
            rs = st.executeQuery(query);

            if(rs.next()){
                status = rs.getString("cUsername").trim();
            }

        } catch (SQLException ex) {
            return status;
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
            }catch(SQLException ex){

            }
        }

        return status;
    }

    public static String getPassword(){
        String status = "";
        String query = "SELECT cPassword FROM SMSUsers";
        try {
            st = getConnection().createStatement();
            rs = st.executeQuery(query);

            if(rs.next()){
                status = rs.getString("cPassword").trim();
            }

        } catch (SQLException ex) {
            return status;
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
            }catch(SQLException ex){

            }
        }

        return status;
    }

    public static int getStatus(){
        int status = -1;
        String query = "SELECT iStatus FROM SMSUsers";
        try {
            st = getConnection().createStatement();
            rs = st.executeQuery(query);

            if(rs.next()){
                status = rs.getInt("iStatus");
            }

        } catch (SQLException ex) {
            return status;
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
            }catch(SQLException ex){

            }
        }

        return status;
    }

    public static void main(String[] oo){
            String username = SMSUserDAO.getUsername().trim();
            String password = SMSUserDAO.getPassword().trim();

            System.out.println(username);
            System.out.println(password);
    }

}
