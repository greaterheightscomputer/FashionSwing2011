package com.Greater;

import DAO.StyleDAO;
import btl.autoId.StyleId;
import com.btl.BTLValidator;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.filechooser.FileNameExtensionFilter;


public class StyleDialog extends JDialog implements ActionListener{
    
    private JPanel mainPanel,centerPanel,northPanel,southPanel;
    private JLabel[] label;
    private JTextField[] textField;
    private JLabel labelLoadId;
    private JComboBox comboLoad;
    private JButton buttonLoad;
    private JButton buttonBrowse;
    private JComboBox[] combo;
    private JButton[] button;

    private GridBagConstraints gbc= new GridBagConstraints();
    private GridBagLayout gbl = new GridBagLayout();
    private int position;
    

    public StyleDialog(Frame owner, String title, boolean modal) {
        super(owner, title, modal);

        mainPanel = new JPanel();
        centerPanel = new JPanel();
        northPanel = new JPanel();
        southPanel = new JPanel();

        mainPanel.setLayout(new BorderLayout());
        centerPanel.setLayout(gbl);

        centerPanel.setBorder(new BevelBorder(BevelBorder.RAISED));

        mainPanel.add(centerPanel,BorderLayout.CENTER);
        mainPanel.add(northPanel,BorderLayout.NORTH);
        mainPanel.add(southPanel,BorderLayout.SOUTH);

        labelLoadId = new JLabel("Existing Id(s):");
        comboLoad = new JComboBox();
        buttonLoad = new JButton("Load");
        buttonLoad.addActionListener(this);

        northPanel.add(labelLoadId);
        northPanel.add(comboLoad);
        northPanel.add(buttonLoad);
        StyleDAO.loadCombo(comboLoad);        

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
        
        
        label = new JLabel[5];
        label[0] = new JLabel("StyleId ");
        label[1] = new JLabel("Style Name ");
        label[2] = new JLabel("Style Type ");
        label[3] = new JLabel("Price (N) ");
        label[4] = new JLabel("Image " );

        textField = new JTextField[4];
        textField[0] = new JTextField(16);
        textField[1] = new JTextField(16);
        textField[2] = new JTextField(16);
        textField[3] = new JTextField(16);

        textField[3].setEditable(false);

        combo = new JComboBox[1];
        String string[]={"select Style Type","Trouser","Shirt","Suit/Jacket","Native"};
        combo[0] = new JComboBox(string);

        buttonBrowse = new JButton("Browse");
        buttonBrowse.addActionListener(this);

        textField[0].setText(StyleId.id());
        textField[0].setEditable(false);

        addComponents(label[0], 1, 1, 1);
        addComponents(textField[0], 2, 1, 1);
        addComponents(label[1], 3, 1, 1);
        addComponents(textField[1], 4, 1, 1);
        addComponents(label[2], 1, 2, 1);
        addComponents(combo[0], 2, 2, 1);
        addComponents(label[3], 3, 2, 1);
        addComponents(textField[2], 4, 2, 1);
        addComponents(label[4], 1, 3, 1);
        addComponents(textField[3], 2, 3, 1);
        addComponents(buttonBrowse, 3, 3, 1);
       
        getContentPane().add(mainPanel);
        setSize(700,300);
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
        combo[0].setSelectedIndex(0);
        textField[2].setText("");
        textField[3].setText("");
        StyleDAO.loadCombo(comboLoad);
        textField[0].setText(StyleId.id());
       
        }
   
   public void setChooser(){

        File dir = new File("styles");

        JFileChooser fileChooser = new JFileChooser();

        FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Only","gif","png","jpg","psd");
        fileChooser.setFileFilter(filter);

        BufferedInputStream bin = null;
        BufferedOutputStream bout = null;

        int returnFile = fileChooser.showSaveDialog(this);
        if(returnFile == JFileChooser.APPROVE_OPTION){
            try {
                File file = fileChooser.getSelectedFile();
                File f = new File(file.getAbsoluteFile().toString());

                byte[] b = new byte[(int)f.length()];

                bin = new BufferedInputStream(new FileInputStream(f));
                bin.read(b,0, b.length);

//                BufferedImage originalImage = ImageIO.read(f);
//                BufferedImage resizedImage = new BufferedImage(100, 100,originalImage.getType());
//                Graphics2D g = resizedImage.createGraphics();
//                g.drawImage(originalImage, 0, 0, 100, 100, null);
//                g.dispose();
//
//                ImageIO.write(resizedImage,null, f);


                bout = new BufferedOutputStream(new FileOutputStream(dir+"\\"+f.getName()));
                bout.write(b, 0, b.length);
                if(bout != null){
                    bout.flush();
                    textField[3].setText(f.getName());
                }
            } catch (IOException ex) {

            }
            finally{
                try {
                    if(bout != null){
                        bout.close();
                    }
                    if(bin != null){
                        bin.close();
                    }

                } catch (IOException ex) {

                }
            }
        }
    }

    private void loadRecord(){
        String comboId = comboLoad.getSelectedItem().toString().trim();
        String id = comboId.substring(0,8);

        Vector vector = StyleDAO.loadRecord(id);
        if(!vector.isEmpty()){
            textField[0].setText(vector.elementAt(0).toString().trim());
            textField[1].setText(vector.elementAt(1).toString().trim());
            combo[0].setSelectedItem(vector.elementAt(2).toString().trim());
            textField[2].setText(vector.elementAt(3).toString().trim());
            textField[3].setText(vector.elementAt(4).toString().trim());            

        }else{
            return;
        }
    }

     private void insertRecord(){

        String styleId = textField[0].getText().trim();
        String styleName = textField[1].getText().trim();
        String styleType = combo[0].getSelectedItem().toString().trim();
        String price = textField[2].getText().trim();
        String image = textField[3].getText().trim();

        int status = StyleDAO.insertRecord(styleId, styleName, styleType, price, image);
        if(status == 0){
            StyleId.updateId();
            clearField();
            JOptionPane.showMessageDialog(null,"Record Successfully Saved!");
            
        }else{
            JOptionPane.showMessageDialog(null,"Record Not Saved!");
        }
    }

     public void validateInsert(){

        if(BTLValidator.validateText(textField[0],"Please Enter Your Style Id!") == false){
            return;
        }
        else if(BTLValidator.validateText(textField[1],"Please Enter Your Style Name!") == false){
            return;
        }
        else if(BTLValidator.validateCombo(combo[0],"Please Select a valid Style Type!") == false){
            return;
        }
        else if(BTLValidator.validateText(textField[2],"Please Enter Your Price!") == false){
            return;
        }
        else if(BTLValidator.validateText(textField[3],"Please Enter Your Image!") == false){
            return;
        }
        else{
            insertRecord();
        }
    }

     
     private void updateRecord(){

        String styleId = textField[0].getText().trim();
        String styleName = textField[1].getText().trim();
        String styleType = combo[0].getSelectedItem().toString().trim();
        String price = textField[2].getText().trim();
        String image = textField[3].getText().trim();

        int status = StyleDAO.updateRecord(styleId, styleName, styleType, price, image);
        if(status == 0){
            clearField();
            JOptionPane.showMessageDialog(null,"Record Updated Successfully!");
        }else{
            JOptionPane.showMessageDialog(null,"Record Not Updated!");
        }
    }

     public void validateUpdate(){

        if(BTLValidator.validateText(textField[0],"Please Enter Your Style Id!") == false){
            return;
        }
        else if(BTLValidator.validateText(textField[1],"Please Enter Your Style Name!") == false){
            return;
        }
        else if(BTLValidator.validateCombo(combo[0],"Please Select a valid Style Type!") == false){
            return;
        }
        else if(BTLValidator.validateText(textField[2],"Please Enter Your Price!") == false){
            return;
        }
        else if(BTLValidator.validateText(textField[3],"Please Enter Your Image!") == false){
            return;
        }
        else{
           updateRecord();
        }
    }
     
     private void deleteRecord(){

        String styleId = textField[0].getText().trim();
        
        int status = StyleDAO.deleteRecord(styleId);
        if(status == 0){
            clearField();
            JOptionPane.showMessageDialog(null,"Record Deleted!");
        }else{
            JOptionPane.showMessageDialog(null,"Record Not Deleted!");
        }
    }
      public void validateDelete(){

        if(BTLValidator.validateText(textField[0],"Please Enter Your Style Id!") == false){
            return;
        }
         else{
           deleteRecord();
        }
    }

     
      public void actionPerformed(ActionEvent ae) {

        Object obj = ae.getSource();
        if(obj == buttonBrowse){
            setChooser();
        }

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
