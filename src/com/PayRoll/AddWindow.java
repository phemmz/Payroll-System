
package com.PayRoll;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class AddWindow extends JInternalFrame implements ActionListener{
    JFrame JFParentFrame;
    JDesktopPane desktop;
    private JPanel panel1, panel2;
    private JButton addBtn, resetBtn, exitBtn;
    private JLabel lblEmp_Code, lblEmp_Name1, lblEmp_Name2, lblEmp_Desi, lblEmp_Add, lblEmp_No;
    private JTextField txtEmp_Code, txtEmp_Name1, txtEmp_Name2, txtEmp_Add, txtEmp_No;
    private JComboBox emp_Type;
    String dialogMessage;
    String dialogs;
    int dialogType = JOptionPane.PLAIN_MESSAGE;
    
    public static int record;
    
    String emp_Code = "";
    String emp_Name1 = "";
    String emp_Name2 = "";
    String emp_Desi = "";
    String emp_Add = "";
    String emp_No = "";
    
    //class variables
    ClsSettings settings = new ClsSettings();
    //connection variables

    PreparedStatement pst = null;
    ResultSet rs = null;
    Connection conn = null;
    Statement st = null;
    
        
    public AddWindow(JFrame getParentFrame){
        
        super("Add-Employee", true, true, true, true);
        conn=sqlConnect.ConnecrDb();
        setSize(400,800);
        JFParentFrame = getParentFrame;
        panel1 = new JPanel();
        panel1.setLayout(new GridLayout(7,7));
        lblEmp_Code = new JLabel(" Employee Code: ");
        lblEmp_Name1 =new JLabel(" First Name: ");
        lblEmp_Name2 = new JLabel(" Last Name: ");
        lblEmp_Desi = new JLabel(" Designation: ");
        lblEmp_Add = new JLabel(" Address: ");
        lblEmp_No = new JLabel(" Contact No");
        
        txtEmp_Code  = new JTextField(20);
        emp_Type = new JComboBox();
        emp_Type.addActionListener(this);
        emp_Type.setEditable(false);
        
        add_Cat_Combo(emp_Type);
        txtEmp_Name1 = new JTextField(20);
        txtEmp_Name2 = new JTextField(20);
        
        txtEmp_Add = new JTextField(20);
        txtEmp_No = new JTextField(20);
        
        panel1.add(lblEmp_Code);
        panel1.add(txtEmp_Code);
        
        panel1.add(lblEmp_Desi);
        panel1.add(emp_Type);
        
        panel1.add(lblEmp_Name1);
        panel1.add(txtEmp_Name1);
        
        panel1.add(lblEmp_Name2);
        panel1.add(txtEmp_Name2);
        
        panel1.add(lblEmp_Add);
        panel1.add(txtEmp_Add);
        
        panel1.add(lblEmp_No);
        panel1.add(txtEmp_No);
        
        panel1.setOpaque(true);
        
        panel2 = new JPanel();
        panel2.setLayout(new FlowLayout());
        addBtn = new JButton("Add");
        resetBtn = new JButton("Reset");
        exitBtn = new JButton("Exit");
        
        panel2.add(addBtn);
        addBtn.addActionListener(this);
        panel2.add(resetBtn);
        panel2.add(exitBtn);
        panel2.setOpaque(true);
        
        
        
        
        getContentPane().setLayout(new GridLayout(2,1));
        getContentPane().add(panel1, "CENTER");
        getContentPane().add(panel2, "CENTER");
        setFrameIcon(new ImageIcon("C:\\Users\\MCOPHEMMZ\\Documents\\NetBeansProjects\\PayRoll\\images\\backup.gif"));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        
        settings.numValidator(txtEmp_No);
    }
    
    public void actionPerformed(ActionEvent event){
        Object source = event.getSource();
        if(source.equals(emp_Type)){
            emp_Desi = (String)emp_Type.getSelectedItem();
        }
        
        if(source.equals(addBtn)){
            emp_Code = "";
			emp_Name1 = "";
			emp_Name2 = "";
			emp_Desi = "";
			emp_Add  = "";
			emp_No   = ""; 
				
		
		emp_Code = txtEmp_Code.getText().trim();
		emp_Name1 = txtEmp_Name1.getText().trim();
		emp_Name2 = txtEmp_Name2.getText().trim();
		emp_Desi = (String)emp_Type.getSelectedItem();
		emp_Add = txtEmp_Add.getText().trim();
		emp_No = txtEmp_No.getText().trim();
		
	try {
		
    		 Statement stmt = conn.createStatement();
         if (!emp_Code.equals("") &&
     		!emp_Name1.equals("")&&
     		!emp_Name2.equals("")&&
     		!emp_Desi.equals("") &&
     		!emp_Add.equals("") &&
     		!emp_No.equals("") )
     		
     		{
     			
     			
     		String query = "SELECT * FROM EMPLOYEE WHERE Emp_Code='" + emp_Code+"'";
                ResultSet rs = stmt.executeQuery(query);
                int foundrec = 0;
                while (rs.next())
                {
                    dialogMessage = "Record Already Exists in DataBase!!!";
                    dialogType = JOptionPane.WARNING_MESSAGE;
                    JOptionPane.showMessageDialog((Component)null, dialogMessage, dialogs, dialogType);
                    
                    foundrec = 1;
                    
                }
                if (foundrec == 0)
                {
                
                String temp = "INSERT INTO EMPLOYEE VALUES ('"+emp_Code +"','" 
     														+emp_Name1 +"','"  
     														+emp_Name2 +"','"	
     														+emp_Desi +"','"
     														+emp_Add + "','"	
     														+emp_No  + "')"	;
     			
     			  int result = stmt.executeUpdate( temp );
                                 if ( result == 1 )
                                 {
                           			System.out.println("Recorded Added");
                           			resetRecord();
                           		
                    
                                 }
                                 else {
                                 		dialogMessage = "Failed To Insert";
                    JOptionPane.showMessageDialog(null, "Failed To Insert in DataBase",
                            "WARNING!!",JOptionPane.WARNING_MESSAGE);
                            	
                    
                                 }
                }
                
               
                	
     		}
     		
     		else
     		{
     				 dialogMessage = "Empty Record !!!";
                    dialogType = JOptionPane.WARNING_MESSAGE;
                    JOptionPane.showMessageDialog((Component)null, dialogMessage, dialogs, dialogType);
     			
     		}
     		
     		 conn.close();	
     			
     		}
     		
     		catch(Exception ex)
                {
                    JOptionPane.showMessageDialog(null,"GENERAL EXCEPTION", "WARNING!!!",JOptionPane.INFORMATION_MESSAGE);
          	}		
     		
			 
				   
		 }
        
        }
        
     private void resetRecord(){
        txtEmp_Code.setText("");
        txtEmp_Name1.setText("");
        txtEmp_Name2.setText("");
        txtEmp_Add.setText("");
        txtEmp_No.setText("");    
    }  
     public void add_Cat_Combo(JComboBox cmb){
        try{
            Statement st = conn.createStatement();
            String query = "select * from Settings";
            ResultSet rs = st.executeQuery(query);
            
            while(rs.next()){
                String txtCmb = rs.getString(2).trim();
                record = rs.getInt("Category_Type");
                
                cmb.addItem(txtCmb);                
            }
            conn.close();        
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    
    
    }
        
    }
    
    
    
    
    
//    public static void main(String[]args){
//        JFrame getParentFrame = null;
//        
//        
//       AddWindow aw  = new AddWindow();
//       aw.setVisible(true);
//        
//    
//    }
    
