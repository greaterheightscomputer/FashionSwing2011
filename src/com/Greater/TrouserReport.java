//
//package com.Greater;
//
//import Connection.FConnection;
//import java.awt.Image;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.sql.Connection;
//import java.util.HashMap;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.swing.ImageIcon;
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//import javax.swing.JTextField;
//import net.sf.jasperreports.engine.JRException;
//import net.sf.jasperreports.engine.JasperFillManager;
//import net.sf.jasperreports.view.JasperViewer;
//
//
//public class TrouserReport extends JFrame implements ActionListener {
//    static JTextField textCustomerId;
//    static JButton button;
//    static JPanel panel;
//
//    public TrouserReport(){
//        panel = new JPanel();
//        textCustomerId = new JTextField(20);
//        panel.add(textCustomerId);
//        button = new JButton("Generate Report");
//        panel.add(button);
//        button.addActionListener(this);
//
//        setIcon();
//
//        getContentPane().add(panel);
//        setSize(getToolkit().getScreenSize());
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setVisible(true);
//
//    }
//
//    public static void getTrouserReport(String value){
//
//    try {
//            String url = "Report\\Trouser.jasper";
//            Connection con = FConnection.getConnection();
//            HashMap param = new HashMap();
//
//            //String value = textCustomerId.getText().trim();
//
//            param.put("customerId",value);
//
//            String pr;
//
//            pr = JasperFillManager.fillReportToFile(url, param, con);
//            JasperViewer.viewReport(pr, false,false);
//        } catch (JRException ex) {
//            Logger.getLogger(TrouserReport.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//    }
//
//
//     public void setIcon(){
//        ImageIcon icon = new ImageIcon("images\\Trouse.gif");
//        Image img = icon.getImage();
//        this.setIconImage(img);
//    }
//
//    public void actionPerformed(ActionEvent e) {
//        Object obj = e.getSource();
//        if(obj == button){
//          getTrouserReport(textCustomerId.getText().trim());
//        }
//
//    }
//
//    public static void main(String[] args) {
//        new TrouserReport();
//
//    }
//
//
//    
//}
