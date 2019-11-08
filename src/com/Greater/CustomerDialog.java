
package com.Greater;

import DAO.CustomerDAO;
import btl.autoId.CustomerId;
import com.btl.BTLValidator;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;


public class CustomerDialog extends JDialog implements ActionListener {
    private JPanel mainPanel,centerPanel,northPanel,southPanel, eastPanel;
    private JLabel[] label;
    private JTextField[] textField;
    private JLabel labelLoadId;
    private JComboBox comboLoad;
    private JButton buttonLoad;
    private JComboBox[] combo;
    private JButton[] button;

    private GridBagConstraints gbc= new GridBagConstraints();
    private GridBagLayout gbl = new GridBagLayout();
    private int position;

     public CustomerDialog(Frame owner, String title, boolean modal) {
        super(owner, title, modal);

        mainPanel = new JPanel();
        centerPanel = new JPanel();
        northPanel = new JPanel();
        southPanel = new JPanel();
        eastPanel = new JPanel();

        mainPanel.setLayout(new BorderLayout());
        centerPanel.setLayout(gbl);

        centerPanel.setBorder(new BevelBorder(BevelBorder.RAISED));

        mainPanel.add(centerPanel,BorderLayout.CENTER);
        mainPanel.add(northPanel,BorderLayout.NORTH);
        mainPanel.add(southPanel,BorderLayout.SOUTH);
        mainPanel.add(eastPanel,BorderLayout.EAST);

        labelLoadId = new JLabel("Existing Id(s):");
        comboLoad = new JComboBox();
        buttonLoad = new JButton("Load");
        buttonLoad.addActionListener(this);

        northPanel.add(labelLoadId);
        northPanel.add(comboLoad);
        northPanel.add(buttonLoad);
        CustomerDAO.loadCombo(comboLoad);

        button = new JButton[5];
        button[0] = new JButton("Submit");
        button[1] = new JButton("Update");
        button[2] = new JButton("Delete");
        button[3] = new JButton("Reset");
        button[4] = new JButton("Close");
        
        for(int i=0;i<button.length;i++){
            button[i].addActionListener(this);
        }
        for(int i=0;i<button.length;i++){
            southPanel.add(button[i]);
        }

        label = new JLabel[9];
        label[0] = new JLabel("CustomerId ");
        label[1] = new JLabel("First Name ");
        label[2] = new JLabel("Last Name ");
        label[3] = new JLabel("Address ");
        label[4] = new JLabel("Gender ");
        label[5] = new JLabel("Phone ");
        label[6] = new JLabel("Email ");
        label[7] = new JLabel("State ");
        label[8] = new JLabel("Country ");
        
        textField = new JTextField[6];
        textField[0] = new JTextField(16);
        textField[1] = new JTextField(16);
        textField[2] = new JTextField(16);
        textField[3] = new JTextField(16);
        textField[4] = new JTextField(16);
        textField[5] = new JTextField(16);

        textField[0].setText(CustomerId.id());
        textField[0].setEditable(false);
                
        combo = new JComboBox[3];
        String string[]={"==select sex==","Male","Female"};
        combo[0] = new JComboBox(string);

        String str[]={"==select State==","Abuja","Anambra","Enugu","Akwa Ibom","Adamawa","Abia","Bauchi","Bayelsa","Benue","Borno","Cross River","Delta","Ebonyi","Edo","Ekiti","Gombe","Imo","Jigawa","Kaduna","Kano","Katsina","Kebbi","Kogi","Kwara","Lagos","Nasarawa","Niger","Ogun","Ondo","Osun","Oyo","Plateau","Rivers","Sokoto","Taraba","Yobe","Zamfara"};
        combo[1] = new JComboBox(str);

        String st[]={"==Select Country==","Afghanistan","Akrotiri","Albania","Algeria","American Samoa","Andorra","Angola","Anguilla","Antarctica","Antigua and Barbuda","Argentina","Armenia","Aruba","Ashmore and Cartier Islands","Australia","Austria","Azerbaijan","Bahamas","Bahrain","Bangladesh","Barbados","Bassas da India","Belarus","Belgium","Belize","Benin","Bermuda","Bhutan","Bolivia","Bosnia and Herzegovina","Botswana",
        "Bouvet Island","Brazil","British Indian Ocean Territory","British Virgin Islands","Brunei","Bulgaria","Burkina Faso","Burma","Burundi","Cambodia","Cameroon","Canada","Cape Verde","Cayman Islands","Central African Republic","Chad","Chile","China","Christmas Island","Clipperton Island","Cocos (Keeling) Islands","Colombia","Comoros","Congo, Democratic Republic of the","Congo, Republic of the","Cook Islands","Coral Sea Islands",
        "Costa Rica","Cote d'Ivoire","Croatia","Cuba","Cyprus","Czech Republic","Denmark","Dhekelia","Djibouti","Dominica","Dominican Republic","Ecuador","Egypt","El Salvador","Equatorial Guinea","Eritrea","Estonia","Ethiopia","Europa Island","Falkland Islands (Islas Malvinas)","Faroe Islands","Fiji","Finland","France","French Guiana","French Polynesia","French Southern and Antarctic Lands","Gabon","Gambia The","Gaza Strip","Georgia",
        "Germany","Ghana","Gibraltar","Glorioso Islands","Greece","Greenland","Grenada","Guadeloupe","Guam","Guatemala","Guernsey","Guinea","Guinea-Bissau","Guyana","Haiti","Heard Island and McDonald Islands","Holy See (Vatican City)","Honduras","Hong Kong","Hungary","Iceland","India","Indonesia","Iran","Iraq","Ireland","Isle of Man","Israel","Italy","Jamaica","Jan Mayen","Japan","Jersey","Jordan","Juan de Nova Island","Kazakhstan",
        "Kenya","Kiribati","Korea, North","Korea, South","Kuwait","Kyrgyzstan","Laos","Latvia","Lebanon","Lesotho","Liberia","Libya","Liechtenstein","Lithuania","Luxembourg","Macau","Macedonia","Madagascar","Malawi","Malaysia","Maldives","Mali","Malta","Marshall Islands","Martinique","Mauritania","Mauritius","Mayotte","Mexico","Micronesia, Federated States of","Moldova","Monaco","Mongolia","Montserrat","Morocco","Mozambique","Namibia",
        "Nauru","Navassa Island","Nepal","Netherlands","Netherlands Antilles","New Caledonia","New Zealand","Nicaragua","Niger","Nigeria","Niue","Norfolk Island","Northern Mariana Islands","Norway","Oman","Pakistan","Palau","Panama","Papua New Guinea","Paracel Islands","Paraguay","Peru","Philippines","Pitcairn Islands","Poland","Portugal","Puerto Rico","Qatar","Reunion","Romania","Russia","Rwanda","Saint Helena","Saint Kitts and Nevis",
        "Saint Lucia","Saint Pierre and Miquelon","Saint Vincent and the Grenadines","Samoa","San Marino","Sao Tome and Principe","Saudi Arabia","Senegal","Serbia and Montenegro","Seychelles","Sierra Leone","Singapore","Slovakia","Slovenia","Solomon Islands","Somalia","South Africa","South Georgia and the South Sandwich Islands","Spain","Spratly Islands","Sri Lanka","Sudan","Suriname","Svalbard","Swaziland","Sweden","Switzerland","Syria",
        "Taiwan","Tajikistan","Tanzania","Thailand","Timor-Leste","Togo","Tokelau","Tonga","Trinidad and Tobago","Tromelin Island","Tunisia","Turkey","Turkmenistan","Turks and Caicos Islands","Tuvalu","Uganda","Ukraine","United Arab Emirates","United Kingdom","United States","Uruguay","Uzbekistan","Vanuatu","Venezuela","Vietnam","Virgin Islands","Wake Island","Wallis and Futuna","West Bank","Western Sahara","Yemen","Zambia","Zimbabwe"};
        combo[2] = new JComboBox(st);
  
        addComponents(label[0], 1, 1, 1);
        addComponents(textField[0], 2, 1, 1);
        addComponents(label[1], 3, 1, 1);
        addComponents(textField[1], 4, 1, 1);
        addComponents(label[2], 1, 2, 1);
        addComponents(textField[2], 2, 2, 1);
        addComponents(label[3], 3, 2, 1);
        addComponents(textField[3], 4, 2, 1);
        addComponents(label[4], 1, 3, 1);
        addComponents(combo[0], 2, 3, 1);
        addComponents(label[5], 3, 3, 1);
        addComponents(textField[4], 4, 3, 1);
        addComponents(label[6], 1, 4, 1);
        addComponents(textField[5], 2, 4, 1);
        addComponents(label[7], 3, 4, 1);
        addComponents(combo[1], 4, 4, 1);
        addComponents(label[8], 1, 5, 1);
        addComponents(combo[2], 2, 5, 1);
                
       
        getContentPane().add(mainPanel);
        setSize(750,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);

    }


    public void addComponents(Component component,int xValue,int yValue,int position){

        this.position = position;
        if(position == 0){
            gbc.anchor = GridBagConstraints.NORTHWEST;
        }else{
            gbc.anchor = GridBagConstraints.NORTHWEST;
        }

        gbc.gridx = xValue;
        gbc.gridy = yValue;
        gbc.insets = new Insets(2,2,2,2);
        gbl.setConstraints(component, gbc);
        centerPanel.add(component);
    }

    public void clearField(){
        textField[0].setText("");
        textField[1].setText("");
        textField[2].setText("");
        textField[3].setText("");
        combo[0].setSelectedIndex(0);
        textField[4].setText("");
        textField[5].setText("");
        combo[1].setSelectedIndex(0);
        combo[2].setSelectedIndex(0);
        CustomerDAO.loadCombo(comboLoad);
        textField[0].setText(CustomerId.id());
        
    }


    private void loadRecord(){
        String comboId = comboLoad.getSelectedItem().toString().trim();
        String id = comboId.substring(0,8);

        Vector vector = CustomerDAO.loadRecord(id);
        if(!vector.isEmpty()){
            textField[0].setText(vector.elementAt(0).toString().trim());
            textField[1].setText(vector.elementAt(1).toString().trim());
            textField[2].setText(vector.elementAt(2).toString().trim());
            textField[3].setText(vector.elementAt(3).toString().trim());
            combo[0].setSelectedItem(vector.elementAt(4).toString().trim());
            textField[4].setText(vector.elementAt(5).toString().trim());
            textField[5].setText(vector.elementAt(6).toString().trim());
            combo[1].setSelectedItem(vector.elementAt(7).toString().trim());
            combo[2].setSelectedItem(vector.elementAt(8).toString().trim());

        }else{
            return;
        }
    }

        private void insertRecord(){
        String customerId= textField[0].getText().trim();
        String firstName = textField[1].getText().trim();
        String lastName = textField[2].getText().trim();
        String address = textField[3].getText().trim();
        String gender = combo[0].getSelectedItem().toString().trim();
        String phone = textField[4].getText().trim();
        String email = textField[5].getText().trim();
        String state = combo[1].getSelectedItem().toString().trim();
        String country = combo[2].getSelectedItem().toString().trim();

        int status = CustomerDAO.insertRecord(customerId, firstName, lastName, address, gender, phone, email, state, country);
        if(status == 0){
            CustomerId.updateId();
            clearField();
            JOptionPane.showMessageDialog(null,"Record Successfully Saved!");
            
        }else{
            JOptionPane.showMessageDialog(null,"Record Not Saved!");
        }
    }

         public void validateInsert(){

        if(BTLValidator.validateText(textField[0],"Please Enter Your Customer Id!") == false){
            return;
        }
        else if(BTLValidator.validateText(textField[1],"Please Enter Your First Name!") == false){
            return;
        }
        else if(BTLValidator.validateText(textField[2],"Please Enter Your Last Name!") == false){
            return;
        }
        else if(BTLValidator.validateText(textField[3],"Please Enter Your Address!") == false){
            return;
        }
        else if(BTLValidator.validateText(textField[4],"Please Enter Your Phone Number!") == false){
            return;
        }
        else if(BTLValidator.validateText(textField[5],"Please Enter Your Email Id!") == false){
            return;
        }
        else{
            insertRecord();
        }
    }

     private void updateRecord(){

        String customerId= textField[0].getText().trim();
        String firstName = textField[1].getText().trim();
        String lastName = textField[2].getText().trim();
        String address = textField[3].getText().trim();
        String gender = combo[0].getSelectedItem().toString().trim();
        String phone = textField[4].getText().trim();
        String email = textField[5].getText().trim();
        String state = combo[1].getSelectedItem().toString().trim();
        String country = combo[2].getSelectedItem().toString().trim();

        int status = CustomerDAO.updateRecord(customerId, firstName, lastName, address, gender, phone, email, state, country);
        if(status == 0){
            clearField();
            JOptionPane.showMessageDialog(null,"Record Updated Successfully!");
        }else{
            JOptionPane.showMessageDialog(null,"Record Not Updated!");
        }
    }

     public void validateUpdate(){

         if(BTLValidator.validateText(textField[0],"Please Enter Your Customer Id!") == false){
            return;
        }
        else if(BTLValidator.validateText(textField[1],"Please Enter Your First Name!") == false){
            return;
        }
        else if(BTLValidator.validateText(textField[2],"Please Enter Your Last Name!") == false){
            return;
        }
        else if(BTLValidator.validateText(textField[3],"Please Enter Your Address!") == false){
            return;
        }
        else if(BTLValidator.validateText(textField[4],"Please Enter Your Phone Number!") == false){
            return;
        }
        else if(BTLValidator.validateText(textField[5],"Please Enter Your Email Id!") == false){
            return;
        }
        else{
            updateRecord();
        }
    }


     private void deleteRecord(){

        String customerId = textField[0].getText();

        int status = CustomerDAO.deleteRecord(customerId);
        if(status == 0){
            clearField();
            JOptionPane.showMessageDialog(null,"Record Deleted!");
        }else{
            JOptionPane.showMessageDialog(null,"Record Not Deleted!");
        }
    }

     public void validateDelete(){

        if(BTLValidator.validateText(textField[0],"Please Enter Your Customer Id!") == false){
            return;
        }
        else{
            deleteRecord();
        }
    }


      public void actionPerformed(ActionEvent ae) {

        Object obj = ae.getSource();
        
        if(obj == buttonLoad){
            loadRecord();
         }

        if(obj == button[0]){
            validateInsert();
        }

        if(obj == button[1]){
          validateUpdate();
        }

        if(obj == button[2]){
            validateDelete();
        }

        if(obj == button[3]){
            clearField();
        }

        if(obj == button[4]){
           dispose();
        }

   }


    }
