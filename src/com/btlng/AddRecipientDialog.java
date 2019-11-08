package com.btlng;

import config.SMSConfig;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

public class AddRecipientDialog extends JDialog implements ActionListener {

    private JPanel mainPanel,centerPanel,southPanel,northPanel;
    private JComboBox comboSentToOption;
    private JLabel labelSentToOption;
    private JButton buttonAddRecipient;
    private GridBagConstraints gbc;
    private GridBagLayout gbl;
    int position;

    public AddRecipientDialog(Dialog owner, String title, boolean modal) {
        super(owner, title, modal);

        mainPanel = new JPanel();
        centerPanel = new JPanel();
        northPanel = new JPanel();
        southPanel = new JPanel();

        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(northPanel,BorderLayout.NORTH);
        mainPanel.add(centerPanel,BorderLayout.CENTER);
        mainPanel.add(southPanel,BorderLayout.SOUTH);

        centerPanel.setBorder(new BevelBorder(BevelBorder.RAISED));

        gbc = new GridBagConstraints();
        gbl = new GridBagLayout();

//        labelSentToOption = new JLabel("Select Recipient Group");

        String customer[] = {"Send to All Customers"};

        comboSentToOption = new JComboBox(customer);

        buttonAddRecipient = new JButton("Add Recipient");
        buttonAddRecipient.addActionListener(this);

        centerPanel.setLayout(gbl);

//        addComponent(labelSentToOption, 1, 1, 1);
        addComponent(comboSentToOption, 1, 1, 1);

        southPanel.add(buttonAddRecipient);

        getContentPane().add(mainPanel);
        setSize(400,200);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(!true);
    }

    public void addComponent(Component component,int xValue,int yValue,int position){
        this.position = position;
        if(position == 0){
            gbc.anchor = GridBagConstraints.NORTHEAST;
        }else{
            gbc.anchor = GridBagConstraints.NORTHWEST;
        }

        gbc.gridx = xValue;
        gbc.gridy = yValue;
        gbc.insets = new Insets(4,4,4,4);
        gbl.setConstraints(component, gbc);
        centerPanel.add(component);
    }

    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();

        if(obj == buttonAddRecipient){
            getPhoneNumbers();
            dispose();
        }

    }

    public static String getPhoneNumbers(){

        String numbers = "";
        Vector vector = new Vector();
        vector = SMSConfig.getCustomersNumber();
        
        for(Enumeration e = vector.elements();e.hasMoreElements();){
            numbers = numbers+"234"+e.nextElement().toString().trim().substring(1);
            if(e.hasMoreElements()){
                numbers = numbers + ",";
            }
            
        }        

        return numbers;

    }
}
