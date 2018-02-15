
package com.PayRoll;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;


public class MainMenu extends JFrame implements ActionListener {
    JDesktopPane desktop = new JDesktopPane();
    String smsGbox_title = "Payroll System v1";
    
    //menubar variables
    JMenuBar menubar = new JMenuBar();
    JMenu menuFile = new JMenu("File");
    JMenu menuEmployee = new JMenu("Employee");
    JMenu menuTools = new JMenu("Tools");
    JMenu menuReports = new JMenu("Reports");
    JMenu menuHelp = new JMenu("Help");
    
    //menuItem variables
    JMenuItem itemExit = new JMenuItem();
    
    JMenuItem itemAdd = new JMenuItem();
    JMenuItem itemEdit = new JMenuItem();
    JMenuItem itemDelete = new JMenuItem();
    
    JMenuItem itemSettings = new JMenuItem();
    JMenuItem itemCalculator = new JMenuItem();
    JMenuItem itemNotepad = new JMenuItem();
    
    JMenuItem itemEmprpt = new JMenuItem();
    
    JMenuItem itemAuthor = new JMenuItem();
    JMenuItem itemHelp = new JMenuItem();
    
    //JPanel variables
    JPanel panel_Bottom = new JPanel();
    JPanel panel_Top = new JPanel();
    
    //Label variables
    JLabel lblUsername = new JLabel("UserName: ");
    JLabel lblLogDetails = new JLabel("Time Login: ");
    JLabel lblTimeNow = new JLabel();
    
    //TextField variables
    JTextField username;
    JTextField logtime;
    
    //JInternalFrame variables
      AddWindow FormAddWindow;
      EditWindow FormEditWindow;
      DeleteWindow FormDeleteWindow;
      
    //SettingsWindow FormsettingsWindow
      EmprptWindow FormEmprptWindow;
      SettingsWindow FormSettingsWindow;
//      AuthorWindow FormAuthorWindow;
//      HelpWindow FormHelpWindow;
    
    
    
    
    
    //Date variables
    static Date td = new Date();
    
    //String variables
    static Statement stmtLogin;
    
    //class variables
    ClsSettings settings = new ClsSettings();
    //user Details
    static String sUser = "";
    static String sLogin = DateFormat.getDateTimeInstance().format(td);
    
    
    public MainMenu(String user, Date date){
        super("PayRoll Accounting system [v1.00]");
        
        sUser = user;
        td = date;
        
        JTextField username = new JTextField();
        username.setEditable(false);
        
        JTextField logtime = new JTextField();
        logtime.setEditable(false);
        username.setText(sUser);
        logtime.setText(sLogin);
        
        panel_Bottom.setLayout(new FlowLayout());
        panel_Bottom.setPreferredSize(new Dimension(10,25));
        panel_Bottom.add(lblUsername);
        panel_Bottom.add(username);
        panel_Bottom.add(lblLogDetails);
        panel_Bottom.add(logtime);
        
        panel_Top.setLayout(new BorderLayout());
        panel_Top.setPreferredSize(new Dimension(10,65));
        panel_Top.add(CreateJToolBar(),BorderLayout.PAGE_START);
        
        desktop.setBackground(Color.WHITE);
        desktop.setAutoscrolls(true);
        desktop.setBorder(BorderFactory.createLoweredBevelBorder());
        desktop.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
        
        getContentPane().add(panel_Top,BorderLayout.PAGE_START);
        getContentPane().add(desktop,BorderLayout.CENTER);
        getContentPane().add(panel_Bottom,BorderLayout.PAGE_END);
        
        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                unloadWindow();
            }        
        });
        
        setJMenuBar(CreateJMenuBar());
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setIconImage(new ImageIcon("C:\\Users\\MCOPHEMMZ\\Documents\\NetBeansProjects\\PayRoll\\images\\Business.png").getImage());
        setSize(700,700);
        setLocation(2,2);
        show();    
    }
    
    protected JMenuBar CreateJMenuBar(){
        //menu FIle
        menuFile.add(settings.setJMenuItem(itemExit, "Quit", "C:\\Users\\MCOPHEMMZ\\Documents\\NetBeansProjects\\PayRoll\\images\\exit.png"));
        itemExit.addActionListener(this);
        
        //menu employee
        menuEmployee.add(settings.setJMenuItem(itemAdd, "Add Employee", "C:\\Users\\MCOPHEMMZ\\Documents\\NetBeansProjects\\PayRoll\\images\\employee.png"));
        menuEmployee.add(settings.setJMenuItem(itemEdit, "Edit Employee", "C:\\Users\\MCOPHEMMZ\\Documents\\NetBeansProjects\\PayRoll\\images\\edit.png"));
        menuEmployee.addSeparator();
        menuEmployee.add(settings.setJMenuItem(itemDelete, "Delete Employee", "C:\\Users\\MCOPHEMMZ\\Documents\\NetBeansProjects\\PayRoll\\images\\delete.png"));
        
        itemAdd.addActionListener(this);
        itemEdit.addActionListener(this);
        itemDelete.addActionListener(this);
        
        //menu Tools
        menuTools.add(settings.setJMenuItem(itemSettings, "Settings", "C:\\Users\\MCOPHEMMZ\\Documents\\NetBeansProjects\\PayRoll\\images\\setting.png"));
        menuTools.add(settings.setJMenuItem(itemCalculator, "Calculator", "C:\\Users\\MCOPHEMMZ\\Documents\\NetBeansProjects\\PayRoll\\images\\calc.png"));
        menuTools.addSeparator();
        menuTools.add(settings.setJMenuItem(itemNotepad, "Notepad", "C:\\Users\\MCOPHEMMZ\\Documents\\NetBeansProjects\\PayRoll\\images\\notepad.png"));
        
        itemSettings.addActionListener(this);
        itemCalculator.addActionListener(this);
        itemNotepad.addActionListener(this);
        
        //menu Reports
        menuReports.add(settings.setJMenuItem(itemEmprpt, "Employee Report", "C:\\Users\\MCOPHEMMZ\\Documents\\NetBeansProjects\\PayRoll\\images\\emp_rpt.png"));
        menuReports.addSeparator();
        menuReports.addSeparator();
        itemEmprpt.addActionListener(this);
        
        //menu Help
        menuHelp.add(settings.setJMenuItem(itemAuthor, "About Author", "C:\\Users\\MCOPHEMMZ\\Documents\\NetBeansProjects\\PayRoll\\images\\xp.png"));
        menuHelp.add(settings.setJMenuItem(itemHelp, "Help", "C:\\Users\\MCOPHEMMZ\\Documents\\NetBeansProjects\\PayRoll\\images\\help.png"));
        itemAuthor.addActionListener(this);
        itemHelp.addActionListener(this);
        
        //to add menuitem to menubar
        menubar.add(settings.setJMenu(menuFile));
        menubar.add(settings.setJMenu(menuEmployee));
        menubar.add(settings.setJMenu(menuTools));
        menubar.add(settings.setJMenu(menuReports));
        menubar.add(settings.setJMenu(menuHelp));
        
        return menubar;
    }
    
    protected JToolBar CreateJToolBar(){
        JToolBar toolbar = new JToolBar("Toolbar");
        toolbar.add(settings.createJToolbarButton("Exit", "C:\\Users\\MCOPHEMMZ\\Documents\\NetBeansProjects\\PayRoll\\images\\exit.png", "File_Exit", JToolBarActionListener));
        toolbar.addSeparator();
        toolbar.addSeparator();
        
        toolbar.add(settings.createJToolbarButton("Add - Employee", "C:\\Users\\MCOPHEMMZ\\Documents\\NetBeansProjects\\PayRoll\\images\\employee.png", "Emp_Add", JToolBarActionListener));
        
        toolbar.add(settings.createJToolbarButton("Edit - Employee", "C:\\Users\\MCOPHEMMZ\\Documents\\NetBeansProjects\\PayRoll\\images\\edit.png", "Emp_Edit", JToolBarActionListener));
        toolbar.addSeparator();
        
        toolbar.add(settings.createJToolbarButton("Delete - Employee", "C:\\Users\\MCOPHEMMZ\\Documents\\NetBeansProjects\\PayRoll\\images\\delete.png", "Emp_Delete", JToolBarActionListener));
        toolbar.addSeparator();
        toolbar.addSeparator();
        
        toolbar.add(settings.createJToolbarButton("Employee Position Settings", "C:\\Users\\MCOPHEMMZ\\Documents\\NetBeansProjects\\PayRoll\\images\\setting.png", "Settings", JToolBarActionListener));
        toolbar.add(settings.createJToolbarButton("Calculator", "C:\\Users\\MCOPHEMMZ\\Documents\\NetBeansProjects\\PayRoll\\images\\calc.png", "Tools_Calculator", JToolBarActionListener));
        toolbar.add(settings.createJToolbarButton("NotePad", "C:\\Users\\MCOPHEMMZ\\Documents\\NetBeansProjects\\PayRoll\\images\\notepad.png", "Tools_NotePad", JToolBarActionListener));
        toolbar.addSeparator();
        toolbar.addSeparator();
        
        toolbar.add(settings.createJToolbarButton("Employee - Report", "C:\\Users\\MCOPHEMMZ\\Documents\\NetBeansProjects\\PayRoll\\images\\emp_rpt.png", "Reports_Employee", JToolBarActionListener));
        
        toolbar.add(settings.createJToolbarButton("Help - Author", "C:\\Users\\MCOPHEMMZ\\Documents\\NetBeansProjects\\PayRoll\\images\\xp.png", "Help_Author", JToolBarActionListener));
        
        toolbar.add(settings.createJToolbarButton("Help - Help", "C:\\Users\\MCOPHEMMZ\\Documents\\NetBeansProjects\\PayRoll\\images\\help.png", "Help_Help", JToolBarActionListener));
        return toolbar;    
    }
    
    ActionListener JToolBarActionListener = new ActionListener(){
        public void actionPerformed(ActionEvent e){
            String source = e.getActionCommand();
            if(source == "File_Exit"){
                loadJInternalFrame(2);            
            }
            else if(source == "Emp_Add"){
                loadJInternalFrame(3);
            }
            else if(source == "Emp_Edit"){
                loadJInternalFrame(4);
            }
            else if(source =="Emp_Delete"){
                loadJInternalFrame(5);
            }
            else if(source == "Settings"){
                loadJInternalFrame(6);
            }
            else if (source == "Tools_Calculator"){
                loadJInternalFrame(7);
            }
            else if(source == "Tools_NotePad"){
                loadJInternalFrame(8);
            }
            else if(source == "Reports_Employee"){
                loadJInternalFrame(9);
            }
            else if(source == "Help_Author"){
                loadJInternalFrame(11);
            }
            else if(source == "Help_Help"){
                loadJInternalFrame(12);
            }
        }
    };
    
    public void actionPerformed(ActionEvent event){
        Object object = event.getSource();
        if(object == itemExit){
            loadJInternalFrame(2);
        }
        else if(object == itemAdd){
            loadJInternalFrame(3);
        }
        else if(object == itemEdit){
            loadJInternalFrame(4);
        }
        else if(object == itemDelete){
            loadJInternalFrame(5);
        }
        else if(object == itemSettings){
            loadJInternalFrame(6);
        }
        else if(object == itemCalculator){
            loadJInternalFrame(7);
        }
        else if(object == itemNotepad){
            loadJInternalFrame(8);
        }
        else if(object == itemEmprpt){
            loadJInternalFrame(9);
        }
        else if(object == itemAuthor){
            loadJInternalFrame(12);
        }
        else if(object == itemHelp){
            loadJInternalFrame(13);
        }
    }
    
    private void loadJInternalFrame(int intWhich){
        switch(intWhich){
            case 2:
                System.exit(0);
                break;
                
            case 3:
                try{
                    FormAddWindow = new AddWindow(this);
                    loadForm("Add Employee", FormAddWindow);                
                }
                catch(Exception e){
                    System.out.println("\nError");
                }
                break;
            
            case 4:
                try{
                    FormEditWindow = new EditWindow(this);
                    loadForm("Edit Employee", FormEditWindow);
                }
                catch(Exception e){
                    System.out.println("\nError");
                }
                break;
            
            case 5:
                try{
                    FormDeleteWindow = new DeleteWindow(this);
                    loadForm("Delete Employee", FormDeleteWindow);
                }
                catch(Exception e){
                    System.out.println("\nError");
                }
                break;
            
            case 6:
                try{
                    FormSettingsWindow = new SettingsWindow(this);
                    loadForm("Settings of Employee", FormSettingsWindow);
                }
                catch(Exception e){
                    System.out.println("\nError");
                }
                break;
                
            case 7:
                runComponents("Calc.exe");
                break;
                
            case 8:
                runComponents("Notepad.exe");
                break;
                
            case 9:
                try{
                    FormEmprptWindow = new EmprptWindow(this);
                    loadForm("Employee Payslip", FormEmprptWindow);
                
                }
                catch(Exception e){
                    System.out.println("\nError");
                }
                break;
                
            case 12:
                
                break;
            
            case 13:
                
                break;
                
        }
    
    }
    
    protected void runComponents(String sComponents){
        Runtime rt = Runtime.getRuntime();
        try{
            rt.exec(sComponents);
        }
        catch(IOException evt){
            JOptionPane.showMessageDialog(null, evt.getMessage(), "Error Found", JOptionPane.ERROR_MESSAGE);
        }
    
    }
    
    protected void loadForm(String Title, JInternalFrame clsForm){
        boolean xForm = isLoaded(Title);
        if(xForm == false){
            desktop.add(clsForm);
            clsForm.setVisible(true);
            clsForm.show();
        }
        else{
            try{
                clsForm.setIcon(false);
                clsForm.setSelected(true);            
            }
            catch(PropertyVetoException e){
            
            }
        }      
    }
    
    protected boolean isLoaded(String FormTitle){
        JInternalFrame Form[] =desktop.getAllFrames();
        for(int i =0; i<Form.length; i++){
            if(Form[i].getTitle().equalsIgnoreCase(FormTitle)){
                Form[i].show();
                try{
                    Form[i].setIcon(false);
                    Form[i].setSelected(true);
                }
                catch(PropertyVetoException e){
                    
                }
                return true;
            }
        
        }
        return false;    
    }
    
    protected void unloadWindow(){
        try{
            int reply = JOptionPane.showConfirmDialog(this, "Are you sure to exit?", smsGbox_title, JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
            if(reply==JOptionPane.YES_OPTION){
                setVisible(false);
                System.exit(0);            
            }
        }
        catch(Exception e){
        }
    }
    
    public static void setLogin(String sUsername, Date sDate){
        sUser = sUsername;
        td = sDate;
    }
    
//    public static void main(String[]args){
//        Date td =new Date();    
//        MainMenu men = new MainMenu("phemmz",td);
//        
//    }
    
    
    
}
