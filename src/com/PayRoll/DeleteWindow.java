
package com.PayRoll;

import java.awt.GridLayout;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class DeleteWindow extends JInternalFrame {
    JFrame JFParentFrame;
    JDesktopPane desktop;
    private JPanel panel1;
    
    private JLabel lblEmp_Code, lblEmp_Name1, lblEmp_Name2, lblEmp_Desi, lblEmp_Add, lblEmp_No;
    private JTextField txtEmp_Code;
    
    
    public DeleteWindow(JFrame getParentFrame){
        super("Delete - Employee", true, true, true, true);
        setSize(400,800);
        JFParentFrame = getParentFrame;
        panel1 = new JPanel();
        panel1.setLayout(new GridLayout(7,7));
        
        lblEmp_Code = new JLabel("Employee Code : ");
        lblEmp_Name1 = new JLabel("First Name : ");
        lblEmp_Name2 = new JLabel("Last Name : ");
        lblEmp_Desi = new JLabel("Designation : ");
        lblEmp_Add = new JLabel("Address : ");
        lblEmp_No = new JLabel("Contact No : ");
        
        txtEmp_Code = new JTextField(20);
        
        
        
        
    
    }
    
}
