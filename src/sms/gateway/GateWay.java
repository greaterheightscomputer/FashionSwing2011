package sms.gateway;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GateWay {

    private static BufferedReader reader = null;

    public static String sendSMS(String host,String username,String password,String sender,String number,String message){
        String value = "";

        try {
            String url = host.trim()+"/smsapi/Send.aspx?UN=" + username.trim() + "&P=" + password.trim() + "&SA=" + sender.trim() + "&DA=" + number + "&M=" + message.trim();

            URL httpURL = new URL(url);
            
            HttpURLConnection connect = (HttpURLConnection) httpURL.openConnection();
            

            reader = new BufferedReader(new InputStreamReader(connect.getInputStream()));
            String respones;
            while((respones = reader.readLine()) != null){
                value = respones;
            }
            
        } catch (IOException ex) {
            Logger.getLogger(GateWay.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try{
                if(reader != null){
                    reader.close();
                }
            }catch(Exception ex){

            }
        }

        return value;

    }
    public static String getBalance(String host,String username,String password){

        String value = "";

        try {
            String url = host.trim()+"/smsapi/GetCreditBalance.aspx?UN=" + username.trim() + "&P=" + password.trim();

            URL httpURL = new URL(url);

            HttpURLConnection connect = (HttpURLConnection) httpURL.openConnection();
            connect.setRequestMethod("GET");
            connect.setReadTimeout(0);

            reader = new BufferedReader(new InputStreamReader(connect.getInputStream()));
            String respones;
            while((respones = reader.readLine()) != null){
                value = respones;
            }

        } catch (IOException ex) {
            Logger.getLogger(GateWay.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try{
                if(reader != null){
                    reader.close();
                }
            }catch(Exception ex){

            }
        }

        return value;
    }

    public static String countMessageText(String host,String username,String password,String message){

        String value = "";

        try {
            String url = host.trim()+"/smsapi/GetCharacterCount.aspx?UN=" + username.trim() + "&P=" + password.trim()+"&M="+message.trim();

            URL httpURL = new URL(url);

            HttpURLConnection connect = (HttpURLConnection) httpURL.openConnection();
            connect.setRequestMethod("GET");
            connect.setReadTimeout(0);

            reader = new BufferedReader(new InputStreamReader(connect.getInputStream()));
            String respones;
            while((respones = reader.readLine()) != null){
                value = respones;
            }

        } catch (IOException ex) {
            Logger.getLogger(GateWay.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try{
                if(reader != null){
                    reader.close();
                }
            }catch(Exception ex){

            }
        }

        return value;
    }

    public static int registerUser(String host,String username,String password,String company, String firstName,String lastName,String cellPhoneNumber,String email,String city,String state,String country){

        int status = -1;
        String value = "";

        try {
            String url = host.trim()+"/smsapi/CustomerSignUp?ResellerUN=Khadijat&CustomerUN="+username+"&isReseller=0&FN="+firstName+"&LN="+lastName+"&EMAIL="+email+"&COMPANY="+company+"&CELLPHONENUMBER="+cellPhoneNumber+"&CITY="+city+"&STATE="+state+"&COUNTRY="+country+"&EmailToSMS=0&PASSWORD="+password;

            URL httpURL = new URL(url);

            HttpURLConnection connect = (HttpURLConnection) httpURL.openConnection();
            connect.setRequestMethod("GET");
            connect.setReadTimeout(0);

            reader = new BufferedReader(new InputStreamReader(connect.getInputStream()));
            String respones;
            while((respones = reader.readLine()) != null){
                value = respones;
            }

            if(value.equals("OK")){
                status = 0;
            }
            else if(value.equals("-135")){
                status = -135;
            }
            else if(value.equals("-145")){
                status = -145;
            }
            else if(value.equals("-150")){
                status = -150;
            }
            else if(value.equals("-130")){
                status = -130;
            }
            else if(value.equals("-155")){
                status = -155;
            }
            else if(value.equals("-125")){
                status = -125;
            }

        } catch (IOException ex) {
            return status;
        }
        finally{
            try{
                if(reader != null){
                    reader.close();
                }
            }catch(Exception ex){

            }
        }

        return status;
    }
    

    public static void main(String[] ee){
        
        final String username = "Khadijat";
        final String password = "234ade";
        final String sender = "Khadijat".replaceAll(" ","%20");
        final String number = "2348037832554";
        final String message = "I Love U. Allah".replaceAll(" ", "%20");

        Runnable run = new Runnable() {

            public void run() {
                String cc = sendSMS("http://209.173.133.66",username,password,sender,number,message);
                System.out.println(cc.substring(2).trim());
            }
        };

        run.run();

    }

}
