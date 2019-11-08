package com.btlng;

import Connection.FConnection;
import btl.sms.dao.SMSUserDAO;
import com.btl.BTLValidator;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import sms.gateway.GateWay;

public class RegisterDialog extends JDialog implements ActionListener,ItemListener{

    private static JPanel mainPanel,northPanel,southPanel,centerPanel;
    private static JLabel[] label;
    private static JTextField[] textField;
    private static JPasswordField[] password;
    private static JButton[] button;
    private static JComboBox[] comboBox;
    private static JRadioButton[] radio;
    private static ButtonGroup buttonGroup;
    private static GridBagConstraints gbc;
    private static GridBagLayout gbl;
    private static int status = 0;

    private static String HOST = "A";

    public RegisterDialog(Frame owner, String title, boolean modal) {

        super(owner, title, modal);

        Properties prop = new Properties();
        try {
             prop.load(new FileInputStream("config\\config.properties"));
             HOST = prop.getProperty("host").trim();
        } catch (IOException ex) {

        }

        initPanels();
        initTextConponent();
        initButtons();

        setSize(700,400);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

    }

    private void initPanels(){
        mainPanel = new JPanel();
        northPanel = new JPanel();
        centerPanel = new JPanel();
        southPanel = new JPanel();

        gbc = new GridBagConstraints();
        gbl = new GridBagLayout();

        mainPanel.setLayout(new BorderLayout());
        centerPanel.setLayout(gbl);
        northPanel.setLayout(new FlowLayout());
        southPanel.setLayout(new FlowLayout());

        centerPanel.setBorder(new BevelBorder(BevelBorder.RAISED));

        mainPanel.add(northPanel,BorderLayout.NORTH);
        mainPanel.add(centerPanel,BorderLayout.CENTER);
        mainPanel.add(southPanel,BorderLayout.SOUTH);

        getContentPane().add(mainPanel);

    }

    private void initButtons(){
        button = new JButton[2];
        button[0] = new JButton("Submit",new ImageIcon("icons\\add.png"));
        button[1] = new JButton("Close",new ImageIcon("icons\\exit.png"));

        for(int i=0;i<button.length;i++){
            button[i].addActionListener(this);
            southPanel.add(button[i]);            
        }

    }

    private void initTextConponent(){

        label = new JLabel[12];
        label[0] = new JLabel("Username :");
        label[1] = new JLabel("Password :");
        label[2] = new JLabel("Re-enter Password :");
        label[3] = new JLabel("Company Name :");
        label[4] = new JLabel("First Name :");
        label[5] = new JLabel("Last Name :");
        label[6] = new JLabel("Cell Phone Number :");
        label[7] = new JLabel("Email :");
        label[8] = new JLabel("City :");
        label[9] = new JLabel("State :");
        label[10] = new JLabel("Country :");
        label[11] = new JLabel("You are adviced to change your default setting.");

        textField = new JTextField[9];
        textField[0] = new JTextField(17);
        textField[1] = new JTextField(17);
        textField[2] = new JTextField(17);
        textField[3] = new JTextField(17);
        textField[4] = new JTextField(17);
        textField[5] = new JTextField(17);
        textField[6] = new JTextField(17);
        textField[7] = new JTextField(17);
        textField[8] = new JTextField(17);

        textField[8].setText("Nigeria"); 

        password = new JPasswordField[2];
        password[0] = new JPasswordField(17);
        password[1] = new JPasswordField(17);

        buttonGroup = new ButtonGroup();
        radio = new JRadioButton[2];
        radio[0] = new JRadioButton("Sign Up");
        radio[1] = new JRadioButton("Already a User");

//        radio[0].setToolTipText("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");

        radio[0].addItemListener(this);
        radio[1].addItemListener(this);

        radio[0].setSelected(true);

        buttonGroup.add(radio[0]);
        buttonGroup.add(radio[1]);

        northPanel.add(radio[0]);
        northPanel.add(radio[1]);

//        northPanel.add(label[11]);

        addComponent(label[0],1,1,1);
        addComponent(textField[0],2,1,1);
        addComponent(label[1],3,1,1);
        addComponent(password[0],4,1,1);
        
        addComponent(label[2],3,2,1);
        addComponent(password[1],4,2,1);

        addComponent(label[3],1,3,1);
        addComponent(textField[1],2,3,1);
        addComponent(label[4],3,3,1);
        addComponent(textField[2],4,3,1);

        addComponent(label[5],1,4,1);
        addComponent(textField[3],2,4,1);
        addComponent(label[6],3,4,1);
        addComponent(textField[4],4,4,1);

        addComponent(label[7],1,5,1);
        addComponent(textField[5],2,5,1);
        addComponent(label[8],3,5,1);
        addComponent(textField[6],4,5,1);

        addComponent(label[9],1,6,1);
        addComponent(textField[7],2,6,1);
        addComponent(label[10],3,6,1);
        addComponent(textField[8],4,6,1);

    }

    private void addComponent(Component component,int gridX,int gridY,int position){

        if(position == 1){
            gbc.anchor = GridBagConstraints.NORTHWEST;
        }else{
            gbc.anchor = GridBagConstraints.NORTHEAST;
        }
        gbc.gridx = gridX;
        gbc.gridy = gridY;
        gbc.insets = new Insets(3,3,3,3);
        gbl.setConstraints(component,gbc);
        centerPanel.add(component);
    }

    public void actionPerformed(ActionEvent e) {

        Object obj = e.getSource();
        if(obj == button[0]){
            insertRecord();
        }
        else if(obj == button[1]){
            dispose();
        }
        
    }

    private void insertRecord(){

        if(status == 0){

            if(validateRegister() == true){

                String username = textField[0].getText().trim();
                String pwd = String.valueOf(password[0].getPassword()).trim();

                String companyName = textField[1].getText().trim();
                String firstName = textField[2].getText().trim();
                String lastName = textField[3].getText().trim();
                String cellPhoneNumber = textField[4].getText().trim();
                String email = textField[5].getText().trim();
                String city = textField[6].getText().trim();
                String state = textField[7].getText().trim();
                String country = textField[8].getText().trim();

                int respones = GateWay.registerUser(HOST,username, pwd, companyName, firstName, lastName, cellPhoneNumber, email, city, state, country);
                if(respones == 0){
                    int reg = SMSUserDAO.updateRecord(username,pwd);
                    if(reg == 0){
                        JOptionPane.showMessageDialog(this,"Account Successfully Created!", "SUCCESS TRANSACTION", JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                    }
                    
                    else{
                        JOptionPane.showMessageDialog(this,"Account Not Successfully Created, Try Again!", "FAILED TRANSACTION", JOptionPane.ERROR_MESSAGE);
                    }
                }
                else if(respones == -135){
                        JOptionPane.showMessageDialog(this,"Username Already Exist!", "FAILED TRANSACTION", JOptionPane.ERROR_MESSAGE);

                    }
                    else if(respones == -145){
                        JOptionPane.showMessageDialog(this,"Invalid Phone Number. Number Should be like 2348030092858!", "FAILED TRANSACTION", JOptionPane.ERROR_MESSAGE);
                    }
                    else if(respones == -150){
                        JOptionPane.showMessageDialog(this,"Invalid Username. Username should not contain alphanumeric only,\nUsername should be greater than 6 Character!", "FAILED TRANSACTION", JOptionPane.ERROR_MESSAGE);
                    }
                    else if(respones == -155){
                        JOptionPane.showMessageDialog(this,"Invalid Password. Password should contain alphanumeric only,\nUsername should be greater than 6 Character!", "FAILED TRANSACTION", JOptionPane.ERROR_MESSAGE);
                    }
                    else if(respones == -125){
                        JOptionPane.showMessageDialog(this,"Invalid Email Address!", "FAILED TRANSACTION", JOptionPane.ERROR_MESSAGE);
                    }
                else{
                    JOptionPane.showMessageDialog(this,"Error in Connection, Check your Connection and try again!", "ERROR IN CONNECTION", JOptionPane.ERROR_MESSAGE);
                }
            }

        }else{

            if(validateisAUser() == true){

                String username = textField[0].getText().trim();
                String pwd = String.valueOf(password[0].getPassword()).trim();

                int reg = SMSUserDAO.updateRecord(username,pwd);
                if(reg == 0){
                    dispose();
                }
            }

        }
    }

    private boolean  validateRegister(){
        boolean st = false;

        if(!BTLValidator.validateText(textField[0],"Username Field is required!")){
        }
        else if(!BTLValidator.validateText(password[0],"Password Field is required!")){
        }
        else if(!BTLValidator.validatePassword(password[0],password[1],"Incorrect Password!")){

        }
        else if(!BTLValidator.validateText(textField[1],"Compay Name Field is required!")){
        }
        else if(!BTLValidator.validateText(textField[2],"First Name Field is required!")){
        }
        else if(!BTLValidator.validateText(textField[3],"Last Name Field is required!")){
        }
        else if(!BTLValidator.validateText(textField[4],"Cell Phone Number Field is required!")){
        }
        else if(!BTLValidator.validateEmail(textField[5],"Invalid Email Address!")){
        }
        else if(!BTLValidator.validateText(textField[6],"City Field is required!")){
        }
        else if(!BTLValidator.validateText(textField[7],"State Field is required!")){
        }
        else if(!BTLValidator.validateText(textField[8],"Country Field is required!")){
        }
        else{
            st = true;
        }

        return st;
    }

    private boolean  validateisAUser(){
        boolean st = false;

        if(!BTLValidator.validateText(textField[0],"Username Field is required!")){

        }
        else if(!BTLValidator.validateText(password[0],"Password Field is required!")){

        }
        else if(!BTLValidator.validatePassword(password[0],password[1],"Incorrect Password!")){

        }
        else{
            st = true;
        }

        return st;
    }

    private void isAUser(boolean b){

        textField[1].setEditable(b);
        textField[2].setEditable(b);
        textField[3].setEditable(b);
        textField[4].setEditable(b);
        textField[5].setEditable(b);
        textField[6].setEditable(b);
        textField[7].setEditable(b);
        textField[8].setEditable(b);

    }

    public void itemStateChanged(ItemEvent e) {

        Object obj = e.getSource();
        if(obj == radio[0]){
            status = 0;
            isAUser(true);
        }
        else if(obj == radio[1]){
            status = 1;
            isAUser(!true);
        }
    }

    public static void main(String[] e){
        HOST = FConnection.initParam();
        new RegisterDialog(null,HOST,true);
    }

}
