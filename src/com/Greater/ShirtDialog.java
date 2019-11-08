package com.Greater;

import DAO.CustomerDAO;
import DAO.ShirtDAO;
import DAO.StyleDAO;
import Report.CatalogReport;
import Report.ShirtReport;
import btl.autoId.ShirtId;
import btl.sms.dao.SMSUserDAO;
import com.btl.BTLValidator;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import sms.gateway.GateWay;

public class ShirtDialog extends JDialog implements ActionListener {

    private JPanel mainPanel, centerPanel, northPanel, southPanel;
    private JLabel[] label;
    private JTextField[] textField;
    private JLabel labelLoadId;
    private JComboBox comboLoad;
    private JButton buttonLoad;
    private JButton buttonCatalogue;
    private JComboBox[] combo;
    private JButton[] button;
    private GridBagConstraints gbc = new GridBagConstraints();
    private GridBagLayout gbl = new GridBagLayout();
    private int position;

    public ShirtDialog(Frame owner, String title, boolean modal) {
        super(owner, title, modal);

        mainPanel = new JPanel();
        centerPanel = new JPanel();
        northPanel = new JPanel();
        southPanel = new JPanel();

        mainPanel.setLayout(new BorderLayout());
        centerPanel.setLayout(gbl);

        centerPanel.setBorder(new BevelBorder(BevelBorder.RAISED));

        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(northPanel, BorderLayout.NORTH);
        mainPanel.add(southPanel, BorderLayout.SOUTH);

        labelLoadId = new JLabel("Existing Id(s):");
        comboLoad = new JComboBox();
        buttonLoad = new JButton("Load");
        buttonLoad.addActionListener(this);

        northPanel.add(labelLoadId);
        northPanel.add(comboLoad);
        northPanel.add(buttonLoad);
        ShirtDAO.loadCombo(comboLoad);

        button = new JButton[6];
        button[0] = new JButton("Submit");
        button[1] = new JButton("Update");
        button[2] = new JButton("Delete");
        button[3] = new JButton("Reset");
        button[4] = new JButton("Close");
        button[5] = new JButton("Send SMS Report");
        button[5].setEnabled(false);

        for (int i = 0; i < button.length; i++) {
            button[i].addActionListener(this);
        }
        for (int i = 0; i < button.length; i++) {
            southPanel.add(button[i]);
        }

        label = new JLabel[18];
        label[0] = new JLabel("ShirtId ");
        label[1] = new JLabel("CustomerId ");
        label[2] = new JLabel("Length ");
        label[3] = new JLabel("Waist ");
        label[4] = new JLabel("Back ");
        label[5] = new JLabel("Sleeve ");
        label[6] = new JLabel("Neck ");
        label[7] = new JLabel("Hip ");
        label[8] = new JLabel("Burst ");
        label[9] = new JLabel("A/S ");
        label[10] = new JLabel("Armhole ");
        label[11] = new JLabel("StyleId ");
        label[12] = new JLabel("Number ");
        label[13] = new JLabel("Deposit (N) ");
        label[14] = new JLabel("Balance (N) ");
        label[15] = new JLabel("Total Amount (N) ");
        label[16] = new JLabel("Date ");
        label[17] = new JLabel("Date of Collection ");


        textField = new JTextField[16];
        textField[0] = new JTextField(16);
        textField[1] = new JTextField(16);
        textField[2] = new JTextField(16);
        textField[3] = new JTextField(16);
        textField[4] = new JTextField(16);
        textField[5] = new JTextField(16);
        textField[6] = new JTextField(16);
        textField[7] = new JTextField(16);
        textField[8] = new JTextField(16);
        textField[9] = new JTextField(16);
        textField[10] = new JTextField(16);
        textField[11] = new JTextField(16);
        textField[12] = new JTextField(16);
        textField[13] = new JTextField(16);
        textField[14] = new JTextField(16);
        textField[15] = new JTextField(16);

        combo = new JComboBox[2];
        String string[] = {""};
        combo[0] = new JComboBox(string);
        CustomerDAO.loadCombo(combo[0]);

        textField[0].setText(ShirtId.id());
        textField[0].setEditable(false);

        combo[1] = new JComboBox();
        StyleDAO.loadCombo(combo[1]);

        addComponents(label[0], 1, 1, 1);
        addComponents(textField[0], 2, 1, 1);
        addComponents(label[1], 3, 1, 1);
        addComponents(combo[0], 4, 1, 1);
        addComponents(label[2], 1, 2, 1);
        addComponents(textField[1], 2, 2, 1);
        addComponents(label[3], 3, 2, 1);
        addComponents(textField[2], 4, 2, 1);
        addComponents(label[4], 1, 3, 1);
        addComponents(textField[3], 2, 3, 1);
        addComponents(label[5], 3, 3, 1);
        addComponents(textField[4], 4, 3, 1);
        addComponents(label[6], 1, 4, 1);
        addComponents(textField[5], 2, 4, 1);
        addComponents(label[7], 3, 4, 1);
        addComponents(textField[6], 4, 4, 1);
        addComponents(label[8], 1, 5, 1);
        addComponents(textField[7], 2, 5, 1);
        addComponents(label[9], 3, 5, 1);
        addComponents(textField[8], 4, 5, 1);
        addComponents(label[10], 1, 6, 1);
        addComponents(textField[9], 2, 6, 1);
        addComponents(label[11], 3, 6, 1);
        addComponents(combo[1], 4, 6, 1);
        addComponents(label[12], 1, 7, 1);
        addComponents(textField[10], 2, 7, 1);
        addComponents(label[13], 3, 7, 1);
        addComponents(textField[11], 4, 7, 1);
        addComponents(label[14], 1, 8, 1);
        addComponents(textField[12], 2, 8, 1);
        addComponents(label[15], 3, 8, 1);
        addComponents(textField[13], 4, 8, 1);
        addComponents(label[16], 1, 9, 1);
        addComponents(textField[14], 2, 9, 1);
        addComponents(label[17], 3, 9, 1);
        addComponents(textField[15], 4, 9, 1);

        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        textField[14].setText(date);
        textField[14].setEditable(false);


        buttonCatalogue = new JButton("Catalogue");
        buttonCatalogue.addActionListener(this);
        centerPanel.add(buttonCatalogue);

        getContentPane().add(mainPanel);
        setSize(750, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);

    }

    public void addComponents(Component component, int xValue, int yValue, int position) {

        this.position = position;
        if (position == 0) {
            gbc.anchor = GridBagConstraints.NORTHWEST;
        } else {
            gbc.anchor = GridBagConstraints.NORTHWEST;
        }

        gbc.gridx = xValue;
        gbc.gridy = yValue;
        gbc.insets = new Insets(2, 2, 2, 2);
        gbl.setConstraints(component, gbc);
        centerPanel.add(component);
    }

    public void clearField() {
        textField[0].setText("");
        combo[0].setSelectedIndex(0);
        textField[1].setText("");
        textField[2].setText("");
        textField[3].setText("");
        textField[4].setText("");
        textField[5].setText("");
        textField[6].setText("");
        textField[7].setText("");
        textField[8].setText("");
        textField[9].setText("");
        combo[1].setSelectedIndex(0);
        textField[10].setText("");
        textField[11].setText("");
        textField[12].setText("");
        textField[13].setText("");
        textField[15].setText("");
        ShirtDAO.loadCombo(comboLoad);
        textField[0].setText(ShirtId.id());
        button[5].setEnabled(false);
    }

    private void loadRecord() {
        String comboId = comboLoad.getSelectedItem().toString().trim();
        String id = comboId.substring(0, 8);

        Vector vector = ShirtDAO.loadRecord(id);
        if (!vector.isEmpty()) {
            textField[0].setText(vector.elementAt(0).toString().trim());
            combo[0].setSelectedItem(CustomerDAO.getCustomerName(vector.elementAt(1).toString().trim()).trim());
            textField[1].setText(vector.elementAt(2).toString().trim());
            textField[2].setText(vector.elementAt(3).toString().trim());
            textField[3].setText(vector.elementAt(4).toString().trim());
            textField[4].setText(vector.elementAt(5).toString().trim());
            textField[5].setText(vector.elementAt(6).toString().trim());
            textField[6].setText(vector.elementAt(7).toString().trim());
            textField[7].setText(vector.elementAt(8).toString().trim());
            textField[8].setText(vector.elementAt(9).toString().trim());
            textField[9].setText(vector.elementAt(10).toString().trim());
            combo[1].setSelectedItem(StyleDAO.getStyleName(vector.elementAt(11).toString().trim()).trim());
            textField[10].setText(vector.elementAt(12).toString().trim());
            textField[11].setText(vector.elementAt(13).toString().trim());
            textField[12].setText(vector.elementAt(14).toString().trim());
            textField[13].setText(vector.elementAt(15).toString().trim());
            textField[14].setText(vector.elementAt(16).toString().trim());
            textField[15].setText(vector.elementAt(17).toString().trim());

        } else {
            return;
        }
    }

    private void insertRecord() {
        String shirtId = textField[0].getText().trim();
        String customerId = combo[0].getSelectedItem().toString().trim().substring(0, 8);
        String length = textField[1].getText().trim();
        String waist = textField[2].getText().trim();
        String back = textField[3].getText().trim();
        String sleeve = textField[4].getText().trim();
        String neck = textField[5].getText().trim();
        String hip = textField[6].getText().trim();
        String burst = textField[7].getText().trim();
        String aS = textField[8].getText().trim();
        String armhole = textField[9].getText().trim();
        String styleId = combo[1].getSelectedItem().toString().trim().substring(0, 8);
        String number = textField[10].getText().trim();
        String deposit = textField[11].getText().trim();
        String balance = textField[12].getText().trim();
        String totalAmount = textField[13].getText().trim();
        String date = textField[14].getText().trim();
        String dateofCollection = textField[15].getText().trim();

        int status = ShirtDAO.insertRecord(shirtId, customerId, length, waist, back, sleeve, neck, hip, burst, aS, armhole, styleId, number, deposit, balance, totalAmount, date, dateofCollection);
        if (status == 0) {
            ShirtId.updateId();
            clearField();
            JOptionPane.showMessageDialog(null, "Record Successfully Saved!");
//            dispose();
            JasperPrint jp = ShirtReport.getReport(shirtId);
            JasperViewer.viewReport(jp,false);
            
        } else {
            JOptionPane.showMessageDialog(null, "Record Not Saved!");
        }
    }

    public void validateInsert(){

        if(BTLValidator.validateText(textField[0],"Please Enter Your Shirt Id !") == false){
            return;
        }
        else if(BTLValidator.validateText(textField[1],"Please Enter Length Measurement !") == false){
            return;
        }
        else if(BTLValidator.validateText(textField[2],"Please Enter Waist Measurement !") == false){
            return;
        }
        else if(BTLValidator.validateText(textField[3],"Please Enter Back Measurement !") == false){
            return;
        }
        else if(BTLValidator.validateText(textField[4],"Please Enter Sleeve Measurement !") == false){
            return;
        }
        else if(BTLValidator.validateText(textField[5],"Please Enter Neck Measurement !") == false){
            return;
        }
        else if(BTLValidator.validateText(textField[6],"Please Enter Hip Measurement !") == false){
            return;
        }
        else if(BTLValidator.validateText(textField[7],"Please Enter Burst Measurement !") == false){
            return;
        }
        else if(BTLValidator.validateText(textField[8],"Please Enter A/S Measurement !") == false){
            return;
        }
        else if(BTLValidator.validateText(textField[9],"Please Enter Armhole Measurement !") == false){
            return;
        }
        else if(BTLValidator.validateText(textField[10],"Please Enter Number of Native !") == false){
            return;
        }
        else if(BTLValidator.validateText(textField[11],"Please Enter Deposit  !") == false){
            return;
        }
        else if(BTLValidator.validateText(textField[12],"Please Enter Balance  !") == false){
            return;
        }
        else if(BTLValidator.validateText(textField[13],"Please Enter Total Amount !") == false){
            return;
        }
        else if(BTLValidator.validateText(textField[14],"Please Enter Date !") == false){
            return;
        }
        else if(BTLValidator.validateText(textField[15],"Please Enter Date of Collection !") == false){
            return;
        }
        else{
            insertRecord();
        }
    }

    private void updateRecord() {

        String shirtId = textField[0].getText().trim();
        String customerId = combo[0].getSelectedItem().toString().trim().substring(0, 8);
        String length = textField[1].getText().trim();
        String waist = textField[2].getText().trim();
        String back = textField[3].getText().trim();
        String sleeve = textField[4].getText().trim();
        String neck = textField[5].getText().trim();
        String hip = textField[6].getText().trim();
        String burst = textField[7].getText().trim();
        String aS = textField[8].getText().trim();
        String armhole = textField[9].getText().trim();
        String styleId = combo[1].getSelectedItem().toString().trim().substring(0, 8);
        String number = textField[10].getText().trim();
        String deposit = textField[11].getText().trim();
        String balance = textField[12].getText().trim();
        String totalAmount = textField[13].getText().trim();
        String date = textField[14].getText().trim();
        String dateofCollection = textField[15].getText().trim();

        int status = ShirtDAO.updateRecord(shirtId, customerId, length, waist, back, sleeve, neck, hip, burst, aS, armhole, styleId, number, deposit, balance, totalAmount, date, dateofCollection);
        if(status == 0){
            clearField();
            JOptionPane.showMessageDialog(null,"Record Updated Successfully!");
        }else{
            JOptionPane.showMessageDialog(null,"Record Not Updated!");
        }
    }

        public void validateUpdate(){

        if(BTLValidator.validateText(textField[0],"Please Enter Your Shirt Id !") == false){
            return;
        }
        else if(BTLValidator.validateText(textField[1],"Please Enter Length Measurement !") == false){
            return;
        }
        else if(BTLValidator.validateText(textField[2],"Please Enter Waist Measurement !") == false){
            return;
        }
        else if(BTLValidator.validateText(textField[3],"Please Enter Back Measurement !") == false){
            return;
        }
        else if(BTLValidator.validateText(textField[4],"Please Enter Sleeve Measurement !") == false){
            return;
        }
        else if(BTLValidator.validateText(textField[5],"Please Enter Neck Measurement !") == false){
            return;
        }
        else if(BTLValidator.validateText(textField[6],"Please Enter Hip Measurement !") == false){
            return;
        }
        else if(BTLValidator.validateText(textField[7],"Please Enter Burst Measurement !") == false){
            return;
        }
        else if(BTLValidator.validateText(textField[8],"Please Enter A/S Measurement !") == false){
            return;
        }
        else if(BTLValidator.validateText(textField[9],"Please Enter Armhole Measurement !") == false){
            return;
        }
        else if(BTLValidator.validateText(textField[10],"Please Enter Number of Native !") == false){
            return;
        }
        else if(BTLValidator.validateText(textField[11],"Please Enter Deposit  !") == false){
            return;
        }
        else if(BTLValidator.validateText(textField[12],"Please Enter Balance  !") == false){
            return;
        }
        else if(BTLValidator.validateText(textField[13],"Please Enter Total Amount !") == false){
            return;
        }
        else if(BTLValidator.validateText(textField[14],"Please Enter Date !") == false){
            return;
        }
        else if(BTLValidator.validateText(textField[15],"Please Enter Date of Collection !") == false){
            return;
        }
        else{
            updateRecord();
        }
    }

    private void deleteRecord() {

        String shirtId = textField[0].getText().trim();

        int status = ShirtDAO.deleteRecord(shirtId);
        if(status == 0){
            clearField();
            JOptionPane.showMessageDialog(null,"Record Deleted!");
        }else{
            JOptionPane.showMessageDialog(null,"Record Not Deleted!");
        }
    }

    public void validateDelete(){

        if(BTLValidator.validateText(textField[0],"Please Enter Your Shirt Id !") == false){
            return;
        }
          else{
            deleteRecord();
        }
    }

    public void actionPerformed(ActionEvent ae) {

        Object obj = ae.getSource();

        if (obj == buttonLoad) {
            loadRecord();
            button[5].setEnabled(true);
        }

        if (obj == button[0]) {
            validateInsert();
        }

        if (obj == button[1]) {
            validateUpdate();
        }

        if (obj == button[2]) {
            validateDelete();
        }

        if (obj == button[3]) {
            clearField();
        }

        if (obj == button[4]) {
            dispose();
        }

        if(obj == button[5]){
           ShirtDAO.sendShirtSMS(textField[0].getText().trim());
        }


        if ((obj == buttonCatalogue)) {
            JasperPrint jp = CatalogReport.getReport("Shirt");
            JasperViewer.viewReport(jp,false);
        }
    }
    
}
