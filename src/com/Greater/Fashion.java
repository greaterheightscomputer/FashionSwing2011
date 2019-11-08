
package com.Greater;

import Connection.FConnection;
import Report.AllNativesReport;
import Report.AllShirtsReport;
import Report.AllSuitsReport;
import Report.AllTrousersReport;
import com.btlng.MessageDialog;
import com.btlng.RegisterDialog;
import com.sun.java.swing.plaf.motif.MotifLookAndFeel;
import com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel;
import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.multi.MultiLookAndFeel;

public class Fashion implements ActionListener{
    private static JFrame frame;
    private static JMenuBar menuBar;
    private static JMenu menuFile, menuUpdate, menuReport, menuSMS, menuHelp;
    private static JMenuItem miOpen, miSave, miPrint, miClose;
    private static JMenuItem miStyle, miCustomer, miTrouser, miShirt, miSuit, miNative;
    private static JMenuItem miStyleReport, miCustomerReport,miTrouserReport, miShirtReport, miSuitReport, miNativeReport;
    private static JMenuItem miRegister, miSendSMS, miCheckBalance, miBuyPoint;
    private static JMenuItem miAbout;
    private static JToolBar toolBar;
    private static JButton btnPrint, btnClose, btnTrouser, btnShirt, btnSuit, btnNative, btnOpen, btnSave;
    private static JPanel mainPanel, northPanel, centerPanel, southPanel;

    public Fashion(){
        frame = new JFrame("AmeenSub Fashion");
        menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        setLookAndFeel();

        menuFile = new JMenu("File");
        menuFile.setMnemonic('f');
        menuBar.add(menuFile);

        miOpen= new JMenuItem("Open");
        miSave= new JMenuItem("Save");
        miPrint = new JMenuItem("Print");
        miClose = new JMenuItem("Close");

        menuFile.add(miOpen);
        menuFile.addSeparator();
//        menuFile.add(miSave);
//        menuFile.addSeparator();
//        menuFile.add(miPrint);
//        menuFile.addSeparator();
        menuFile.add(miClose);

        miOpen.addActionListener(this);      
        miSave.addActionListener(this);
        miPrint.addActionListener(this);
        miClose.addActionListener(this);
           
        menuUpdate = new JMenu("Update");
        menuUpdate.setMnemonic('u');
        menuBar.add(menuUpdate);

        miStyle= new JMenuItem("Style");
        miCustomer= new JMenuItem("Customer");
        miTrouser = new JMenuItem("Trouser");
        miShirt = new JMenuItem("Shirt");
        miSuit = new JMenuItem("Suit");
        miNative = new JMenuItem("Native");

             
        menuUpdate.add(miStyle);
        menuUpdate.addSeparator();
        menuUpdate.add(miCustomer);
        menuUpdate.addSeparator();
        menuUpdate.add(miTrouser);
        menuUpdate.addSeparator();
        menuUpdate.add(miShirt);
        menuUpdate.addSeparator();
        menuUpdate.add(miSuit);
        menuUpdate.addSeparator();
        menuUpdate.add(miNative);

        miStyle.addActionListener(this);
        miCustomer.addActionListener(this);
        miTrouser.addActionListener(this);
        miShirt.addActionListener(this);
        miSuit.addActionListener(this);
        miNative.addActionListener(this);

        menuReport = new JMenu("Report");
        menuReport.setMnemonic('r');
        menuBar.add(menuReport);

        miStyleReport = new JMenuItem("Style");
        miCustomerReport = new JMenuItem("Customer");
        miTrouserReport = new JMenuItem("Trouser");
        miShirtReport = new JMenuItem("Shirt");
        miSuitReport = new JMenuItem("Suit");
        miNativeReport = new JMenuItem("Native");

        miStyleReport.addActionListener(this);
        miCustomerReport.addActionListener(this);
        miTrouserReport.addActionListener(this);
        miShirtReport.addActionListener(this);
        miSuitReport.addActionListener(this);
        miNativeReport.addActionListener(this);

//        menuReport.add(miStyleReport);
//        menuReport.addSeparator();
//        menuReport.add(miCustomerReport);
//        menuReport.addSeparator();
        menuReport.add(miTrouserReport);
        menuReport.addSeparator();
        menuReport.add(miShirtReport);
        menuReport.addSeparator();
        menuReport.add(miSuitReport);
        menuReport.addSeparator();
        menuReport.add(miNativeReport);

        menuSMS = new JMenu("SMS");
        menuReport.setMnemonic('s');
        menuBar.add(menuSMS);

        miRegister = new JMenuItem("Register");
        miSendSMS = new JMenuItem("Send SMS");
        miCheckBalance = new JMenuItem("Check Balance");
        miBuyPoint = new JMenuItem("Buy Point");

        miRegister.addActionListener(this);
        miSendSMS.addActionListener(this);
        miCheckBalance.addActionListener(this);
        miBuyPoint.addActionListener(this);

        menuSMS.add(miRegister);
        menuSMS.addSeparator();
        menuSMS.add(miSendSMS);
        menuSMS.addSeparator();
        menuSMS.add(miCheckBalance);
        menuSMS.addSeparator();
        menuSMS.add(miBuyPoint);

        menuHelp = new JMenu("Help");
        menuHelp.setMnemonic('h');
        menuBar.add(menuHelp);

        miAbout= new JMenuItem("About");
        menuHelp.add(miAbout);
        miAbout.addActionListener(this);

        
        toolBar = new JToolBar();
        toolBar.setFloatable(false);

        btnOpen = new JButton(new ImageIcon("images\\Open Record.gif"));
        btnPrint = new JButton(new ImageIcon("images\\Print Data.gif"));
        btnClose = new JButton(new ImageIcon("images\\Dispose.gif"));
        btnTrouser = new JButton(new ImageIcon("images\\Trouser.gif"));
        btnShirt = new JButton(new ImageIcon("images\\Shirt.gif"));
        btnSuit = new JButton(new ImageIcon("images\\Suit.gif"));
        btnNative = new JButton(new ImageIcon("images\\Native.gif"));

        btnOpen.setToolTipText("Open");
        btnPrint.setToolTipText("Print");
        btnClose.setToolTipText("Close");
        btnTrouser.setToolTipText("Trouser");
        btnShirt.setToolTipText("Shirt");
        btnSuit.setToolTipText("Suit/Jacket");
        btnNative.setToolTipText("Native Outfit");

        btnOpen.addActionListener(this);
        btnPrint.addActionListener(this);
        btnClose.addActionListener(this);
        btnTrouser.addActionListener(this);
        btnShirt.addActionListener(this);
        btnSuit.addActionListener(this);
        btnNative.addActionListener(this);

        toolBar.add(btnOpen);
//        toolBar.add(btnPrint);
//        toolBar.add(btnClose);
        toolBar.add(btnTrouser);
        toolBar.add(btnShirt);
        toolBar.add(btnSuit);
        toolBar.add(btnNative);

        mainPanel = new JPanel();
        frame.getContentPane().add(mainPanel);
        mainPanel.setBackground(Color.CYAN);

        northPanel = new JPanel();
        centerPanel = new JPanel();
        southPanel = new JPanel();

        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(toolBar,BorderLayout.NORTH);
        mainPanel.add(centerPanel,BorderLayout.CENTER);
        mainPanel.add(southPanel,BorderLayout.SOUTH);

        centerPanel.setBackground(Color.DARK_GRAY);

        setIcon();
    }

    public static void main(String[] args) {
        new Fashion();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(frame.getToolkit().getScreenSize());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

            
        public void setIcon(){
        ImageIcon icon = new ImageIcon("images\\Trouse.gif");
        Image img = icon.getImage();
        frame.setIconImage(img);
    }

    public void actionPerformed(ActionEvent ae) {
         Object objectClicked = ae.getSource();
        
         if((objectClicked==miOpen || objectClicked == btnOpen)){
                open();
        }
         if((objectClicked==miStyle)){
                new StyleDialog(frame, "Register Style", true);
        }

         if((objectClicked==miCustomer)){
                new CustomerDialog(frame, "Customer Details", true);
        }

         if((objectClicked==miTrouser)||(objectClicked==btnTrouser)){

                new TrouserDialog(frame,"Trouser Measurement", !true);
        }

         if((objectClicked==miSuit)||(objectClicked==btnSuit)){

                new SuitDialog(frame,"Suit Measurement", !true);
        }

         if((objectClicked==miShirt)||(objectClicked==btnShirt)){

                new ShirtDialog(frame,"Shirt Measurement", !true);
        }

         if((objectClicked==miNative)||(objectClicked==btnNative)){

                new NativeDialog(frame,"Native Measurement", !true);
        }


         if(objectClicked==miTrouserReport){
//                new TrouserReport();
         }

          if((objectClicked==miRegister)){

                new RegisterDialog(frame,"SMS Registration", true);
        }

          if((objectClicked==miSendSMS)){

                new MessageDialog(frame, "Send SMS", true);
        }
         
          if((objectClicked == miCheckBalance)){

                checkPoint();
           }
          if((objectClicked == miBuyPoint)){

                buyPoint();
           }
          if((objectClicked == miAbout)){

                help();
          }
          if((objectClicked == miClose)){
               System.exit(0);
          }
          if((objectClicked == miSuitReport)){
              new AllSuitsReport(frame,"Generate Report", true);
          }
          if((objectClicked == miTrouserReport)){

              new AllTrousersReport(frame,"Generate Report", true);
           }
          if((objectClicked == miShirtReport)){

              new AllShirtsReport(frame,"Generate Report", true);
           }
          if((objectClicked == miNativeReport)){

              new AllNativesReport(frame,"Generate Report", true);
           }
         
    }

    public void setLookAndFeel(){
        try {
            UIManager.setLookAndFeel(new WindowsLookAndFeel());
        }catch (UnsupportedLookAndFeelException ex) {
            
        }
    }

    private void checkPoint(){
        JOptionPane.showMessageDialog(null, "Your SMS Credit Balance is "+FConnection.checkPoint()+" Point");
    }

    private void buyPoint(){
        String url = "http://www.btlng.com/Pricing.aspx";
        try {
            Desktop.getDesktop().browse(new URI(url));

        } catch (URISyntaxException ex) {
            JOptionPane.showMessageDialog(frame,"Address not found!");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(frame,"Invalid Address!");
        }

    }

    public void help(){
        JOptionPane.showMessageDialog(null,"Fashion Manager 1.1\nDeveloped by Khadijat\nPowered by JAVA");
    }

    private void open(){

        JFileChooser fileChooser = new JFileChooser();

        FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF,Doc","pdf","doc");
        fileChooser.setFileFilter(filter);

        int returnFile = fileChooser.showOpenDialog(frame);
        if(returnFile == JFileChooser.APPROVE_OPTION){

            File file = fileChooser.getSelectedFile();
            
            try {

                Desktop.getDesktop().open(file);

            } 
            catch (IOException ex) {
                JOptionPane.showMessageDialog(frame,file);
            }
        }

    }

}
