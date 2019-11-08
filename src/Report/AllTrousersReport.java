package Report;

import Connection.FConnection;
import com.btl.BTLValidator;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

public class AllTrousersReport extends JDialog implements ActionListener{

    private JPanel mainPanel,centerPanel,northPanel,southPanel;
    private ProgressBarPanel progressBar;
    private GridBagConstraints gbc;
    private GridBagLayout gbl;
    private JLabel[] label;
    private JLabel labelTitle;
    private JComboBox[] comboStart;
    private JComboBox[] comboEnd;
    private JButton[] button;

    public AllTrousersReport(Frame owner, String title, boolean modal) {
        super(owner, title, modal);

        mainPanel = new JPanel();
        centerPanel = new JPanel();
        northPanel = new JPanel();
        southPanel = new JPanel();

        gbc = new GridBagConstraints();
        gbl = new GridBagLayout();

        mainPanel.setLayout(new BorderLayout());
        centerPanel.setLayout(gbl);

        progressBar = new  ProgressBarPanel("...Loading Trousers Report");
        mainPanel.add(centerPanel,BorderLayout.CENTER);
        mainPanel.add(northPanel,BorderLayout.NORTH);
        mainPanel.add(southPanel,BorderLayout.SOUTH);

        initText();
        initButton();        

        getContentPane().add(mainPanel);

        setSize(400,300);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    private void initButton(){
        
        button = new JButton[2];
        button[0] = new JButton("Generate Report");
        button[1] = new JButton("Cancel");
        
        for(int i=0;i<button.length;i++){
            button[i].addActionListener(this);
            southPanel.add(button[i]);
        }
        
    }
    
    private void initText(){

        labelTitle = new JLabel("Generate Daily, Weekly, Monthly and Yearly Report");
        northPanel.add(labelTitle);
        
        label = new JLabel[2];
        label[0] = new JLabel("Start Date :");
        label[1] = new JLabel("End Date   :");
        
        String[] day = {"==Day==","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
        String[] month = {"==Month==","January","February","March","April","May","June","July","August","September","October","November","December"};
        String[] year = {"==Year==","2011","2012","2013","2014","2015","2016","2017","2018","2019","2020"};

        comboStart = new JComboBox[3];
        comboStart[0] = new JComboBox(day);
        comboStart[1] = new JComboBox(month);
        comboStart[2] = new JComboBox(year);

        comboEnd = new JComboBox[3];
        comboEnd[0] = new JComboBox(day);
        comboEnd[1] = new JComboBox(month);
        comboEnd[2] = new JComboBox(year);

        addComponent(label[0],1,1,1);
        addComponent(comboStart[0],2,1,1);
        addComponent(comboStart[1],3,1,1);
        addComponent(comboStart[2],4,1,1);

        addComponent(label[1],1,2,1);
        addComponent(comboEnd[0],2,2,1);
        addComponent(comboEnd[1],3,2,1);
        addComponent(comboEnd[2],4,2,1);
        
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

    public JasperPrint getReport(){

        String sDay = comboStart[0].getSelectedItem().toString().trim();
        String sMonth = getMonth(comboStart[1].getSelectedItem().toString().trim());
        String sYear = comboStart[2].getSelectedItem().toString().trim();

        String eDay = comboEnd[0].getSelectedItem().toString().trim();
        String eMonth = getMonth(comboEnd[1].getSelectedItem().toString().trim());
        String eYear = comboEnd[2].getSelectedItem().toString().trim();

        String startDate = sYear+"-"+sMonth+"-"+sDay;
        String endDate = eYear+"-"+eMonth+"-"+eDay;

        JasperPrint jp = null ;
        String reportURL = "Report/AllTrousers.jasper";
        HashMap map = new HashMap();
        map.put("startDate", startDate);
        map.put("endDate", endDate);

        Connection connect = FConnection.getConnection();
        try {
            
            jp = JasperFillManager.fillReport(reportURL, map,connect);
        } catch (JRException ex) {
        }
        return jp;
    }

    private void validateField(){

        if(BTLValidator.validateCombo(comboStart[0],"Please Select a valid Start Day!") == false){
            return;
        }
        else if(BTLValidator.validateCombo(comboStart[1],"Please Select a valid Start Month!") == false){
            return;
        }
        else if(BTLValidator.validateCombo(comboStart[2],"Please Select a valid Start Year!") == false){
            return;
        }
        else if(BTLValidator.validateCombo(comboEnd[0],"Please Select a valid End Day!") == false){
            return;
        }
        else if(BTLValidator.validateCombo(comboEnd[1],"Please Select a valid End Month!") == false){
            return;
        }
        else if(BTLValidator.validateCombo(comboEnd[2],"Please Select a valid End Year!") == false){
            return;
        }
        else{

           button[0].setVisible(false);
           button[1].setVisible(false);
           southPanel.add(progressBar);
           setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
           progressBar.StartWork(this,getReport());
        }
    }

    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();

        if(obj == button[0]){
           validateField();
        }

        if(obj == button[1]){
            dispose();
        }

    }

    private String getMonth(String month){
        String returnValue = "";

        if(month.equals("January")){
            returnValue = "01";
        }
        else if(month.equals("February")){
            returnValue = "02";
        }
        else if(month.equals("March")){
            returnValue = "03";
        }
        else if(month.equals("April")){
            returnValue = "04";
        }
        else if(month.equals("May")){
            returnValue = "05";
        }
        else if(month.equals("June")){
            returnValue = "06";
        }
        else if(month.equals("July")){
            returnValue = "07";
        }
        else if(month.equals("August")){
            returnValue = "08";
        }
        else if(month.equals("September")){
            returnValue = "09";
        }
        else if(month.equals("October")){
            returnValue = "10";
        }
        else if(month.equals("November")){
            returnValue = "11";
        }
        else if(month.equals("December")){
            returnValue = "11";
        }

        return returnValue;
    }

}
