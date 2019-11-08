package com.btlng;

import btl.sms.dao.SMSUserDAO;
import com.btl.BTLValidator;
import config.SMSConfig;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.StringTokenizer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import sms.gateway.GateWay;

public class MessageDialog extends JDialog implements ActionListener{

    private static JFrame frame;
    private static JPanel mainPanel,northPanel,upWestPanel,downWestPanel,upCenterPanel,centerDownCenterPanel,northDownCenterPanel,southDownCenterPanel,downCenterPanel,eastPanel,southPanel,westPanel,centerPanel;
    private static JLabel labelReport,labelHelp,labelGroup,labelMessageCounter,labelTitle,label6,labelRecipient;
    private static JTextArea messageTextArea,textRecipient;
    private static JTextField textTitle;
    private static JButton button[];
    private static JButton buttonAddRecipient,buttonCount;
    private static JComboBox combo[];
    private static GridBagConstraints gbc;
    private GridBagLayout gbl;
    private static int position;
    private static String optionId= "";
    private static String optionName= "";
    public static int messageType;
    static int num;
    static JLabel numLabel;
    private static String HOST;

    private ProgressBar bar = new ProgressBar("         connecting to btl sms gateway           ");

    public MessageDialog(JFrame frame,String title,boolean modal) {
        super(frame,title,modal);


        Properties prop = new Properties();
        try {
             prop.load(new FileInputStream("config\\config.properties"));
             HOST = prop.getProperty("host").trim();
        } catch (IOException ex) {

        }

        MessageDialog.frame = frame;
        mainPanel = new JPanel();
        northPanel = new JPanel();
        eastPanel = new JPanel();
        southPanel = new JPanel();
        westPanel = new JPanel();
        centerPanel = new JPanel();

        upWestPanel = new JPanel();
        downWestPanel = new JPanel();

        upCenterPanel = new JPanel();
        downCenterPanel = new JPanel();

        centerDownCenterPanel = new JPanel();
        southDownCenterPanel = new JPanel();
        northDownCenterPanel = new JPanel();

        gbc = new GridBagConstraints();
        gbl = new GridBagLayout();
        
        mainPanel.setLayout(new BorderLayout());
        southPanel.setLayout(new BorderLayout());
        westPanel.setLayout(new BorderLayout());
        centerPanel.setLayout(new BorderLayout());

        upWestPanel.setLayout(new GridLayout(2,1,2,6));
        downWestPanel.setLayout(new BorderLayout());

        upCenterPanel.setLayout(gbl);
        downCenterPanel.setLayout(new BorderLayout());

        southDownCenterPanel.setLayout(new FlowLayout());
        centerDownCenterPanel.setLayout(new BorderLayout());
        northDownCenterPanel.setLayout(gbl);

        mainPanel.add(westPanel,BorderLayout.WEST);
        mainPanel.add(centerPanel,BorderLayout.CENTER);
        mainPanel.add(southPanel,BorderLayout.SOUTH);


        westPanel.add(upWestPanel,BorderLayout.NORTH);
        westPanel.add(downWestPanel,BorderLayout.CENTER);
        centerPanel.add(upCenterPanel,BorderLayout.NORTH);
        centerPanel.add(downCenterPanel,BorderLayout.CENTER);

        downCenterPanel.add(centerDownCenterPanel,BorderLayout.CENTER);
        downCenterPanel.add(southDownCenterPanel,BorderLayout.SOUTH);
        downCenterPanel.add(northDownCenterPanel,BorderLayout.NORTH);



        centerDownCenterPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
        upCenterPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));

        num = 300;
        numLabel = new JLabel(String.valueOf(num));

        downCenterPanel.setBorder(new TitledBorder("Message Editor"));
        southDownCenterPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));

        labelReport = new JLabel("");
        labelReport.setVisible(false);
        labelGroup = new JLabel("Message Type");        
        labelTitle = new JLabel("Message Header");
        label6 = new JLabel(" ");
       
        textTitle = new JTextField(40);
        messageTextArea = new JTextArea();
        
        buttonCount = new JButton("Send Message",new ImageIcon("icons\\sms.png"));
        buttonCount.addActionListener(this);

        button = new JButton[5];

        button[0] = new JButton("Send",new ImageIcon("icons\\sms.png"));
        button[1] = new JButton("Refresh",new ImageIcon("icons\\reset.png"));
        button[2] = new JButton("Close",new ImageIcon("icons\\exit.png"));
        button[3] = new JButton("Manage Category",new ImageIcon("icons\\messageGroup.png"));
        button[4] = new JButton("Manage Message",new ImageIcon("icons\\message.png"));
        for(int i =0;i<button.length;i++){
            button[i].addActionListener(this);
        }

        labelMessageCounter = new JLabel();
        labelMessageCounter.setToolTipText("NOTE: special characters like(;? etc) are counted as two or three characters!");


        String[] CenterGroup = {"Text Message"};


        combo = new JComboBox[3];
        combo[0] = new JComboBox(CenterGroup);

        labelRecipient = new JLabel("  Recipient(s)");
        textRecipient = new JTextArea(5,30);

        buttonAddRecipient = new JButton("Add Group",new ImageIcon("icons\\message.png"));
        buttonAddRecipient.setToolTipText("Load Recipient(s)");
        buttonAddRecipient.addActionListener(this);

        labelHelp = new JLabel(new ImageIcon("icons\\help.png"));
        labelHelp.setToolTipText("Example: 2348030234547 only numbers, no + sign,\nand no zero before the area code.");

        northPanel.add(labelReport);

        addComponent(labelGroup, 1, 1, 1);
        addComponent(combo[0], 2, 1, 1);
        addComponent(labelRecipient, 3, 1, 1);
        addComponent(labelHelp, 4, 1, 1);
        addComponent(new JScrollPane(textRecipient), 5, 1, 1);
        addComponent(buttonAddRecipient,6, 1, 1);

        centerDownCenterPanel.add(new JScrollPane(messageTextArea),BorderLayout.CENTER);

        southDownCenterPanel.add(bar);

        southDownCenterPanel.add(labelMessageCounter);
        southDownCenterPanel.add(buttonCount);
        southDownCenterPanel.add(button[0]);
        southDownCenterPanel.add(button[1]);
        southDownCenterPanel.add(button[2]);        

        setBar(true);
        button[0].setVisible(false);

        addTitleComponent(labelTitle, 2, 2, 1);
        addTitleComponent(textTitle, 3, 2, 0);
        addTitleComponent(labelReport, 3, 4, 1);
        
        getContentPane().add(mainPanel);
        setSize(850,450);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        
    }  
    
    public void addComponent(Component component,int xValue,int yValue,int position){
        MessageDialog.position = position;
        if(position == 0){
            gbc.anchor = GridBagConstraints.NORTHEAST;
        }else{
            gbc.anchor = GridBagConstraints.NORTHWEST;
        }

        gbc.gridx = xValue;
        gbc.gridy = yValue;
        gbc.insets = new Insets(4,6,4,6);
        gbl.setConstraints(component,gbc);
        upCenterPanel.add(component);
    }

    public void addTitleComponent(Component component,int xValue,int yValue,int position){
        MessageDialog.position = position;
        if(position == 0){
            gbc.anchor = GridBagConstraints.NORTHEAST;
        }else{
            gbc.anchor = GridBagConstraints.NORTHWEST;
        }

        gbc.gridx = xValue;
        gbc.gridy = yValue;
        gbc.insets = new Insets(2,6,2,6);
        gbl.setConstraints(component, gbc);
        northDownCenterPanel.add(component);
    }

    public void actionPerformed(ActionEvent ae) {

        Object buttonClicked = ae.getSource();

        if(buttonClicked == button[0]){
            validateMessageContent();
        }
        else if(buttonClicked == button[1]){
            refresh();
        }
        else if(buttonClicked == button[2]){
            dispose();
        }
        else if(buttonClicked == buttonCount){
            validateMessageContent();
        }
        else if(buttonClicked == buttonAddRecipient){            

            AddRecipientDialog recip = new AddRecipientDialog(this,"", true);
            recip.setVisible(true);
            textRecipient.setText(AddRecipientDialog.getPhoneNumbers());
        }

    }

    private void refresh(){
        combo[0].setSelectedIndex(0);
        textTitle.setText("");
        textRecipient.setText("");
        messageTextArea.setText("");
    }
   
    private void validateMessageContent(){

        if(BTLValidator.validateTextArea(textRecipient,"Message Recipient cannot be empty!") == false){
            return;
        }        
        else if(BTLValidator.validateText(textTitle,"Message Header cannot be empty!") == false){
            return;
        }
        else if(BTLValidator.validateTextLength(textTitle,11,0,"Message Header field cannot exceed 11 Characters.") == false){
            return;
        }
        else if(BTLValidator.validateTextArea(messageTextArea,"Message Body cannot be empty!") == false){
            return;
        }        
        else if(BTLValidator.validateTextAreaLength(messageTextArea,160,"Message Body cannot exceed 160 Characters.") == false){
            return;
        }else{

            Runnable run = new Runnable() {

            public void run() {
                sendMessage();
            }
            };

            run.run();
            
        }
    }

    private void validateMessageCounter(){

        if(BTLValidator.validateTextArea(textRecipient,"Message Recipient cannot be empty!") == false){
            return;
        }
        else if(BTLValidator.validateText(textTitle,"Message Header cannot be empty!") == false){
            return;
        }
        else if(BTLValidator.validateTextArea(messageTextArea,"Message Body cannot be empty!") == false){
            return;
        }
        else if(BTLValidator.validateTextLength(textTitle,11,0,"Message Header field cannot exceed 11 Characters.") == false){
            return;
        }
        else if(BTLValidator.validateTextAreaLength(messageTextArea,160,"Message Body cannot exceed 160 Characters.") == false){
            return;
        }else{
            labelMessageCounter.setText("No. of Characters = "+countMessageText());
        }
    }

    private static void sendMessage(){

        int checkUser = SMSUserDAO.getStatus();
        if(checkUser == 0){


            String username = SMSUserDAO.getUsername().trim();
            String password = SMSUserDAO.getPassword().trim();
            
            String sender = textTitle.getText().trim().replaceAll(" ","%20");

            String number = textRecipient.getText().trim().replaceAll(" ","%20");


            String message = messageTextArea.getText().trim().replaceAll(" ","%20");

            String value = GateWay.sendSMS(HOST,username,password,sender,number,message);
            if(!value.equals("") || value != null){
//                value.substring(2);
                JOptionPane.showMessageDialog(null,"Message Successfully Delivered to "+value.substring(2).trim()+" Number(s)", "SUCCESS TRANACTION", JOptionPane.INFORMATION_MESSAGE);

            }else{
                JOptionPane.showMessageDialog(null,"Error in Connection, Check your Connection and try Again!", "ERROR IN CONNECTION", JOptionPane.ERROR_MESSAGE);
            }

        }
        else{
            JOptionPane.showMessageDialog(null,"Please You have Register for our SMS before using it!");
            new RegisterDialog(frame,"", true);
        }
        
    }

    private static String countMessageText(){

        String value = "";

        int checkUser = SMSUserDAO.getStatus();
        if(checkUser == 0){

            String username = SMSUserDAO.getUsername().trim();
            String password = SMSUserDAO.getPassword().trim();

            String message = messageTextArea.getText().trim().replaceAll(" ","%20");

//            if(message.contains(";")){
//                message.replaceAll(";","%3B");
//            }
//            if(message.contains("?")){
//                message.replaceAll("?","%3F");
//            }
//            if(message.contains("/")){
//                message.replaceAll("/","%2F");
//            }
//            if(message.contains(":")){
//                message.replaceAll(":","%3A");
//            }
//            if(message.contains("#")){
//                message.replaceAll("#","%23");
//            }
//            if(message.contains("&")){
//                message.replaceAll("&","%26");
//            }
//            if(message.contains("=")){
//                message.replaceAll("=","%3D");
//            }
//            if(message.contains("+")){
//                message.replaceAll("+","%2B");
//            }
//            if(message.contains("$")){
//                message.replaceAll("$","%24");
//            }
//            if(message.contains(",")){
//                message.replaceAll(",","%2C");
//            }
//            if(message.contains("%")){
//                message.replaceAll("%","%25");
//            }
//            if(message.contains("<")){
//                message.replaceAll("<","%3C");
//            }
//            if(message.contains(">")){
//                message.replaceAll(">","%3E");
//            }
//            if(message.contains("~")){
//                message.replaceAll("~","%7E");
//            }

            String status = GateWay.countMessageText(HOST,username,password,message);
            value = status.substring(2).trim();


        }
        else{
            JOptionPane.showMessageDialog(null,"Please Register to Continue!");
            new RegisterDialog(frame,"", true);
        }



        return value.trim();
    }

    

    public static int countNumbers(){

        StringTokenizer st = new StringTokenizer(textRecipient.getText().trim(),",");

        return st.countTokens();
    }

//    public static void main(String[] java){
//        new MessageDialog(null,"", true);
//    }

   
    private void setBar(boolean b){
        button[0].setVisible(b);
        button[1].setVisible(b);
        button[2].setVisible(b);
        bar.setVisible(!b);
    }
}
