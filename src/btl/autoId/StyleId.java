/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package btl.autoId;

import Connection.FConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JamesCal.Java
 */
public class StyleId {

    private static Statement st;
    private static ResultSet rs;
    private static Connection connect = null;

    public static Connection getConnection(){
        connect = FConnection.getConnection();
        return connect;
    }


    public static void updateId(){
        String query = "UPDATE AutoIds SET iStatus=iStatus + 1 WHERE cAutoId='A0001'";
        try {
            st = getConnection().createStatement();
            st.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(StyleId.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try{

                if(st != null){
                    st.close();
                }
                if(connect != null){
                    connect.close();
                }

            }catch(Exception ex){

            }
        }
    }

    private static int getId(){

        int status = 0;

        String query = "SELECT * FROM AutoIds WHERE cAutoId='A0001'";
        try {
            st = getConnection().createStatement();
            rs = st.executeQuery(query);
            if(rs.next()){
                status = rs.getInt("iStatus");
            }
        } catch (SQLException ex) {
            Logger.getLogger(StyleId.class.getName()).log(Level.SEVERE, null, ex);
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

        return status;
    }

    public static String id(){
        String value = "";
        int id = getId();

        if(id >= 0 && id <= 9){
            value = "ST00000"+id;
        }
        else if(id >= 10 && id <= 99){
            value = "ST0000"+id;
        }
        else if(id >= 100 && id <= 999){
            value = "ST000"+id;
        }
        else if(id >= 1000 && id <= 9999){
            value = "ST00"+id;
        }
        else if(id >= 10000 && id <= 99999){
            value = "ST0"+id;
        }
        else if(id >= 100000 && id <= 999999){
            value = "ST"+id;
        }
        else if(id >= 1000000 && id <= 9999999){
            value = "S"+id;
        }
        else if(id >= 10000000){
            value = ""+id;
        }

        return value;
    }


}
