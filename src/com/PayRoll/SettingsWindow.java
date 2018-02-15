
package com.PayRoll;

import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class SettingsWindow extends JInternalFrame implements ActionListener, ItemListener {
    JFrame JFParentFrame;
    JDesktopPane desktop;
    
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;
    private JPanel panel5;
    private JPanel panel6;
    private JPanel panel7;
    private JPanel panel8;
    
    private JButton addBtn;
    private JButton changeBtn;
    private JButton exitBtn;
    private JButton deleteBtn;
    
    private JLabel lblHeading, lbl_BasicSalary, lblAllowance, lblPercent1, lblRs1;
    private JLabel lblDeduction, lblPercent2, lblRs2;
    private JLabel lblDA, lblHRA, lblWA, lblGPF, lblIT, lblGIS, lblPF, lblLIC;
    private JLabel emp_Type, select1, select2;
    
    private JTextField txtBasic, txtDA1, txtHRA1, txtWA1, txtGPF1, txtIT1, txtGIS1, txtPF1, txtLIC1;
    private JTextField txtCategory_Name, txtDA2, txtHRA2, txtWA2;
    private JTextField txtGPF2, txtIT2, txtGIS2, txtPF2, txtLIC2;
    private JComboBox cat_Name;
    private JCheckBox chDA, chHRA, chWA, chGPF, chIT, chGIS, chPF, chLIC;
    
    String dialogMessage;
    String dialogs;
    int dialogType = JOptionPane.PLAIN_MESSAGE;
    public static int record;
    
    //class variables
    ClsSettings settings = new ClsSettings();
    
    //connection
    PreparedStatement pst = null;
    ResultSet rs = null;
    Connection conn = null;
    
    private String sCategory_Type = "";
    private String sCategory_Name = "";
    private String sBasic_Pay = "";
    private String sDA = "false";
    private String sHRA = "false";
    private String sWA = "false";
    private String sGPF = "false";
    private String sIT = "false";
    private String sGIS = "false";
    private String sPF = "false";
    private String sLIC = "false";
    private String sDA_Allow = "";
    private String sHRA_Allow = "";
    private String sWA_Allow = "";
    private String sGPF_Dedu = "";
    private String sIT_Dedu = "";
    private String sGIS_Dedu = "";
    private String sPF_Dedu = "";
    private String sLIC_Dedu = "";
    public static boolean s;
        
    
    public SettingsWindow(JFrame getParentFrame){        
        super("Employee - Settings", true,true,true,true);
        conn=sqlConnect.ConnecrDb();
        setSize(800,850);
        JFParentFrame = getParentFrame;
        
        panel2 = new JPanel();
            panel2.setLayout(new FlowLayout());
                emp_Type = new JLabel("Employee Type : ");
                cat_Name = new JComboBox();
                cat_Name.addActionListener(this);
                cat_Name.setEditable(false);
                add_Cat_Name(cat_Name);
                txtCategory_Name = new JTextField(10);
                    txtCategory_Name.setText(null);
                String cat_getName = (String)cat_Name.getSelectedItem();
                
            panel2.add(emp_Type, "LEFT");
            panel2.add(cat_Name, "CENTER");
            panel2.add(txtCategory_Name, "RIGHT");
        
        panel3 = new JPanel();
        panel3.setLayout(new FlowLayout());
        lbl_BasicSalary = new JLabel("Basic Salary : ");
        txtBasic = new JTextField(10);
        
        panel3.add(lbl_BasicSalary, "LEFT");
        panel3.add(txtBasic, "RIGHT");
        
        panel4 = new JPanel();
        panel4.setLayout(new GridLayout(1,4,5,5));
        select1 = new JLabel("Check for %");
        lblAllowance = new JLabel("Allowance");
        lblPercent1 = new JLabel("Allowance Value");
        lblRs1 = new JLabel("Information");
        
        panel4.add(select1, "CENTER");
        panel4.add(lblAllowance, "CENTER");
        panel4.add(lblPercent1, "CENTER");
        panel4.add(lblRs1, "CENTER");
        
        panel5 = new JPanel();
        panel5.setLayout(new GridLayout(3,4,5,5));
        
        lblDA = new JLabel("DA Allowance : ");
        lblHRA = new JLabel("HRA Allowance: ");
        lblWA = new JLabel("WA Allowance");
        
        txtDA1 = new JTextField();
        txtDA2 = new JTextField("Enter in Naira");
        
        txtHRA1 = new JTextField();
        txtHRA2 = new JTextField("Enter in Naira");
        
        txtWA1 = new JTextField();
        txtWA2 = new JTextField("Enter in Naira");
        
        chDA = new JCheckBox("DA",false);
        chDA.addItemListener(this);
        
        chHRA = new JCheckBox("HRA",false);
        chHRA.addItemListener(this);
        
        chWA = new JCheckBox("WA",false);
        chWA.addItemListener(this);
        
        panel5.add(lblDA);
        panel5.add(chDA);
        panel5.add(txtDA1);
        panel5.add(txtDA2);
        
        panel5.add(lblHRA);
        panel5.add(chHRA);
        panel5.add(txtHRA1);
        panel5.add(txtHRA2);
        
        panel5.add(lblWA);
        panel5.add(chWA);
        panel5.add(txtWA1);
        panel5.add(txtWA2);
        
        panel6 = new JPanel();
            panel6.setLayout(new GridLayout(1,4,5,5));
            select2 = new JLabel("Check for %");
            lblDeduction = new JLabel("Deduction : ");
            lblPercent2 = new JLabel("Deduction Value");
            lblRs2 = new JLabel("Information");
            
            panel6.add(select2, "CENTER");
            panel6.add(lblDeduction, "CENTER");
            panel6.add(lblPercent2, "CENTER");
            panel6.add(lblRs2, "CENTER");
            
            panel7 = new JPanel();
            panel7.setLayout(new GridLayout(6,4,2,2));
            
            lblGPF = new JLabel("GPF Deduction : ");
            lblIT = new JLabel("I. T Deduction : ");
            lblGIS = new JLabel("GIS Deduction : ");
            lblPF = new JLabel("PF Deduction : ");
            lblLIC = new JLabel("LIC Deduction : ");

            txtGPF1 = new JTextField();
            txtGPF2 = new JTextField("Enter in Naira");
            txtIT1 = new JTextField();
            txtIT2 = new JTextField("Enter in Naira");
            txtGIS1 = new JTextField();
            txtGIS2 = new JTextField("Enter in Naira");
            txtPF1 = new JTextField();
            txtPF2 = new JTextField("Enter in Naira");
            txtLIC1 = new JTextField();
            txtLIC2 = new JTextField("Enter in Naira");
            
            chGPF = new JCheckBox("GPF", false);
            chGPF.addItemListener(this);
            chIT = new JCheckBox("IT", false);
            chIT.addItemListener(this);
            chGIS = new JCheckBox("GIS", false);
            chGIS.addItemListener(this);
            chPF = new JCheckBox("PF", false);
            chPF.addItemListener(this);
            chLIC = new JCheckBox("LIC", false);
            chLIC.addItemListener(this);
                   
            panel7.add(lblGPF);
            panel7.add(chGPF);
            panel7.add(txtGPF1);
            panel7.add(txtGPF2);
            
            panel7.add(lblIT);
            panel7.add(chIT);
            panel7.add(txtIT1);
            panel7.add(txtIT2);
            
            panel7.add(lblGIS);
            panel7.add(chGIS);
            panel7.add(txtGIS1);
            panel7.add(txtGIS2);
            
            panel7.add(lblPF);
            panel7.add(chPF);
            panel7.add(txtPF1);
            panel7.add(txtPF2);
            
            panel7.add(lblLIC);
            panel7.add(chLIC);
            panel7.add(txtLIC1);
            panel7.add(txtLIC2);
            panel7.setOpaque(true);
            
            panel8 = new JPanel();
            panel8.setLayout(new FlowLayout(FlowLayout.CENTER));
            addBtn = new JButton("Add");
            addBtn.addActionListener(this);
            changeBtn = new JButton("Edit");
            changeBtn.addActionListener(this);
            deleteBtn = new JButton("Delete");
            deleteBtn.addActionListener(this);
            exitBtn = new JButton("Exit");
            exitBtn.addActionListener(this);
            
            panel8.add(addBtn);
            panel8.add(changeBtn);
            panel8.add(deleteBtn);
            panel8.add(exitBtn);
            panel8.setOpaque(true);
            
//        check_false();
//        uncheck_true();            
                         
        Container pane = getContentPane();
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
        pane.add(panel2);
        pane.add(panel3);
        pane.add(panel4);
        pane.add(panel5);
        pane.add(panel6);
        pane.add(panel7);
        pane.add(panel8);
        
        setFrameIcon(new ImageIcon("C:\\Users\\MCOPHEMMZ\\Desktop\\tech\\Advanced_Payroll_System\\PayRoll\\images\\settings.gif"));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        
        settings.numValidator(txtBasic);
        settings.numValidator(txtDA1);
        settings.numValidator(txtDA2);
        settings.numValidator(txtHRA1);
        settings.numValidator(txtHRA2);
        settings.numValidator(txtWA1);
        settings.numValidator(txtWA2);
        settings.numValidator(txtGPF1);
        settings.numValidator(txtIT1);
        settings.numValidator(txtIT2);
        settings.numValidator(txtGIS1);
        settings.numValidator(txtGIS2);
        settings.numValidator(txtPF1);
        settings.numValidator(txtPF2);
        settings.numValidator(txtLIC1);
        settings.numValidator(txtLIC2);
        
        fill_Form(cat_getName);
        
    }
    
    public void actionPerformed(ActionEvent event){
        Object source = event.getSource();
        if(source == cat_Name){
            String cat_getName = (String)cat_Name.getSelectedItem();
            fill_Form(cat_getName);    
            
        }
        
        if(source == addBtn){
            add_Record();        
        }
        
        if(source == changeBtn){
            edit_Record();
        }
        
        if(source == deleteBtn){
            delete_Record();
        }
    
        
    
    
    
    }
    
    public void itemStateChanged(ItemEvent event){
    
    
    
    }
    
    public void checkBox_State(JCheckBox chBox, String opt){
        s = Boolean.valueOf(opt);
        chBox.setSelected(s);    
    }
    
    public void txtBox_Fill(JTextField txt1, String value){
        txt1.setText(value);    
    }
    
    
    
    public void add_Cat_Name(JComboBox cmb){
        try{
            Statement stmt = conn.createStatement();
            String query = "select * from Settings";
            ResultSet rs = stmt.executeQuery(query);
            
            while(rs.next()){
                String txtCmb = rs.getString(2).trim();
                record = rs.getInt("Category_Type");
                cmb.addItem(txtCmb);            
            }
            conn.close();
        }
        catch(Exception e){}
    
    
    }
    
    public void add_Record(){
        try{
            record = record+1;
            sCategory_Type = "" +record;
            sCategory_Name = txtCategory_Name.getText().trim();
            sBasic_Pay = txtBasic.getText().trim();
            sDA_Allow = txtDA1.getText().trim();
            sHRA_Allow = txtHRA1.getText().trim();
            sWA_Allow = txtWA1.getText().trim();
            sGPF_Dedu = txtGPF1.getText().trim();
            sIT_Dedu = txtIT1.getText().trim();
            sGIS_Dedu = txtGIS1.getText().trim();
            sPF_Dedu = txtPF1.getText().trim();
            sLIC_Dedu = txtLIC1.getText().trim();
            
            if(!sCategory_Type.equals("")&&
                    !sCategory_Name.equals("")&&
                    !sBasic_Pay.equals("")&&
                    !sDA_Allow.equals("")&&
                    !sHRA_Allow.equals("")&&
                    !sWA_Allow.equals("")&&
                    !sGPF_Dedu.equals("")&&
                    !sIT_Dedu.equals("")&&
                    !sGIS_Dedu.equals("")&&
                    !sPF_Dedu.equals("")&&
                    !sLIC_Dedu.equals("")){
            
                System.out.println("Category Name : " + sCategory_Name);
                Statement st = conn.createStatement();
                String query = "select * from Settings where Category_Name ='" +sCategory_Name+ "'";
                ResultSet rs = st.executeQuery(query);
                int foundrec = 0;
                while(rs.next()){
                    dialogMessage = "Record Already Exists in Database";
                    dialogType = JOptionPane.WARNING_MESSAGE;
                    JOptionPane.showMessageDialog((Component)null, dialogMessage, dialogs, dialogType);
                    foundrec = 1;
                }
                if(foundrec == 0){
                    String temp = "insert into Settings values ("+ sCategory_Type + ",'" +
                            sCategory_Name + "',"+
                            sBasic_Pay + ",'"+
                            sDA + "','"+
                            sHRA + "','"+
                            sWA + "','" +
                            sGPF + "','"+
                            sIT + "','"+
                            sGIS + "','"+
                            sPF + "','"+
                            sLIC + "','"+
                            sDA_Allow + ","+
                            sHRA_Allow + ","+
                            sWA_Allow + ","+
                            sGPF_Dedu + ","+
                            sIT_Dedu + ","+
                            sGIS_Dedu + ","+
                            sPF_Dedu + ","+
                            sLIC_Dedu + ")";
                    
                    
                    int result = st.executeUpdate(temp);
                    if(result == 1){
                        dialogMessage = "New Position Added";
                        dialogType = JOptionPane.INFORMATION_MESSAGE;
                        JOptionPane.showMessageDialog((Component)null, dialogMessage, dialogs, dialogType);
                        cat_Name.addItem(sCategory_Name);                    
                    }else{
                        dialogMessage = "Failed to Insert";
                        JOptionPane.showMessageDialog(null, dialogMessage, "Warning!!", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
            else{
                dialogMessage = "Empty value found";
                JOptionPane.showMessageDialog(null, dialogMessage, "Warning!!", JOptionPane.WARNING_MESSAGE);            
            }
            conn.close();            
        }
        catch(Exception ex){
            System.out.println("Unknown error "+ex);        
        }
    
    
    }
    
    public void fill_Form(String name){
        try{
            Statement stmt = conn.createStatement();
            String query = "select * from Settings where Category_Name = '" +name+ "'";
            ResultSet rs = stmt.executeQuery(query);
            
            while(rs.next()){
                sCategory_Type = "";
                sCategory_Name = "";
                sBasic_Pay = "";
                sDA = "";
                sHRA = "";
                sWA = "";
                sGPF = "";
                sIT = "";
                sGIS = "";
                sPF = "";
                sLIC = "";
                sDA_Allow = "";
                sHRA_Allow = "";
                sWA_Allow = "";
                sGPF_Dedu = "";
                sIT_Dedu = "";
                sGIS_Dedu = "";
                sPF_Dedu = "";
                sLIC_Dedu = "";
                
                sCategory_Type = rs.getString(1).trim();
                sCategory_Name = rs.getString(2).trim();
                sBasic_Pay = rs.getString(3).trim();
                sDA = rs.getString(4).trim();
                sHRA = rs.getString(5).trim();
                sWA = rs.getString(6).trim();
                sGPF = rs.getString(7).trim();
                sIT = rs.getString(8).trim();
                sGIS = rs.getString(9).trim();
                sPF = rs.getString(10).trim();
                sLIC = rs.getString(11).trim();
                sDA_Allow = rs.getString(12).trim();
                sHRA_Allow = rs.getString(13).trim();
                sWA_Allow = rs.getString(14).trim();
                sGPF_Dedu = rs.getString(15).trim();
                sIT_Dedu = rs.getString(16).trim();
                sGIS_Dedu = rs.getString(17).trim();
                sPF_Dedu = rs.getString(18).trim();
                sLIC_Dedu = rs.getString(19).trim();
                
                txtBasic.setText(sBasic_Pay);
                
                checkBox_State(chDA, sDA);
                checkBox_State(chHRA, sHRA);
                checkBox_State(chWA, sWA);
                checkBox_State(chGPF, sGPF);
                checkBox_State(chIT, sIT);
                checkBox_State(chGIS, sGIS);
                checkBox_State(chPF, sPF);
                checkBox_State(chLIC, sLIC);
                
                txtBox_Fill(txtDA1, sDA_Allow);
                txtBox_Fill(txtHRA1, sHRA_Allow);
                txtBox_Fill(txtWA1, sWA_Allow);
                txtBox_Fill(txtGPF1, sGPF_Dedu);
                txtBox_Fill(txtIT1, sIT_Dedu);
                txtBox_Fill(txtGIS1, sGIS_Dedu);
                txtBox_Fill(txtPF1, sPF_Dedu);
                txtBox_Fill(txtLIC1, sLIC_Dedu);
                
                rs = null;
                
            }
            conn.close();       
        }
        catch(Exception ex){
        
        }
    
    
    }
    
    public void edit_Record(){
        try{
            sCategory_Name = (String)cat_Name.getSelectedItem();
            sBasic_Pay = txtBasic.getText().trim();
            sDA_Allow = txtDA1.getText().trim();
            sHRA_Allow = txtHRA1.getText().trim();
            sWA_Allow = txtWA1.getText().trim();
            sGPF_Dedu = txtGPF1.getText().trim();
            sIT_Dedu = txtIT1.getText().trim();
            sGIS_Dedu = txtGIS1.getText().trim();
            sPF_Dedu = txtPF1.getText().trim();
            sLIC_Dedu = txtLIC1.getText().trim();
            
            if(!sCategory_Name.equals("") &&
                    !sBasic_Pay.equals("") &&
                    !sDA_Allow.equals("") &&
                    !sHRA_Allow.equals("") &&
                    !sWA_Allow.equals("") &&
                    !sGPF_Dedu.equals("") &&
                    !sIT_Dedu.equals("") &&
                    !sGIS_Dedu.equals("") &&
                    !sPF_Dedu.equals("") &&
                    !sLIC_Dedu.equals("")){
                Statement stmt = conn.createStatement();
                String temp = "update Settings SET " +
                        "Category_Type= " +sCategory_Type+
                        ", Category_Name = '"+ sCategory_Name +
                        "',Basic_Pay= " + sBasic_Pay +
                        ", DA='" + sDA +
					"',HRA= '" + sHRA +
					"',WA= '" + sWA +
					"',GPF = '" + sGPF +
					"',IT = '" + sIT +
					"',GIS = '" + sGIS +		
					"',PF = '" + sPF +	
					"',LIC = '" + sLIC +
					"',DA_Allow = " + sDA_Allow +
					",HRA_Allow = " + sHRA_Allow +
					",WA_Allow = " + sWA_Allow +
					",GPF_Dedu = " + sGPF_Dedu +
					",IT_Dedu = " + sIT_Dedu +
					",GIS_Dedu = " + sGIS_Dedu +
					",PF_Dedu = " + sPF_Dedu +
					",LIC_Dedu = " + sLIC_Dedu +
					" WHERE Category_Type= " + sCategory_Type;
                int result = stmt.executeUpdate(temp);
                if(result == 1){
                    dialogMessage = "Record Altered into the Database";
                    dialogType = JOptionPane.INFORMATION_MESSAGE;
                    JOptionPane.showMessageDialog((Component)null, dialogMessage, dialogs, dialogType);
                }
                else{
                    dialogMessage = "No such position found";
                    dialogType = JOptionPane.WARNING_MESSAGE;
                    JOptionPane.showMessageDialog((Component)null, dialogMessage, dialogs, dialogType);
                }
                                              
            
            }
            else{
                dialogMessage = "Null Values in TextField Occured";
                dialogType = JOptionPane.WARNING_MESSAGE;
                JOptionPane.showMessageDialog((Component)null, dialogMessage, dialogs, dialogType);
            }
            conn.close();      
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, "General Exception", "WARNING!!", JOptionPane.INFORMATION_MESSAGE);
                    
        }
    
    
    }
    
    public void delete_Record(){
        sCategory_Name = (String)cat_Name.getSelectedItem();
        int DResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete record?");
        
        if(DResult == JOptionPane.NO_OPTION || DResult == JOptionPane.CANCEL_OPTION){
            dialogMessage = "Operation Cancelled by user";
            dialogType = JOptionPane.INFORMATION_MESSAGE;
            JOptionPane.showMessageDialog((Component)null, dialogMessage, dialogs, dialogType);
                    
        }
    
    }
    
}
