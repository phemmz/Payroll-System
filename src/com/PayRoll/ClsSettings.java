
package com.PayRoll;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;


public class ClsSettings {
    
    public JLabel setJLabel(JLabel lbl, int sLeft, int sTop, int sWidth, int sHeight, boolean setBool){
        lbl.setBounds(sLeft, sTop, sWidth, sHeight);
        lbl.setFont(new Font("Dialog", Font.PLAIN,12));
        if(setBool == true){
            lbl.setForeground(new Color(166,0,0));
        }
        else{
            lbl.setForeground(java.awt.Color.BLACK);
        }
        return lbl;    
    }
    
    public JTextField setJTextField(JTextField txtField, int sLeft, int sTop, int sWidth, int sHeight){
        txtField.setBounds(sLeft, sTop, sWidth, sHeight);
        txtField.setFont(new Font("Dialog",Font.PLAIN,12));
        txtField.setSelectionColor(new Color(200,150,150));
        txtField.setSelectedTextColor(new Color(0,0,0));
        return txtField;       
    }
    
    public JMenu setJMenu(JMenu menu){
        menu.setFont(new Font("Dialog",Font.BOLD,12));
        menu.setCursor(new Cursor(Cursor.HAND_CURSOR));
        menu.setForeground(new Color(0,0,0));
        return menu;
    }
    
    public JMenuItem setJMenuItem(JMenuItem mnuitem, String sCaption, String imgLocation){
        mnuitem.setText(sCaption);
        mnuitem.setIcon(new ImageIcon(imgLocation));
        mnuitem.setCursor(new Cursor(Cursor.HAND_CURSOR));
        mnuitem.setFont(new Font("Dialog",Font.PLAIN,12));
        mnuitem.setForeground(new Color(0,0,0));
        return mnuitem;    
    }
    
    public JTabbedPane setJTabbedPane(JTabbedPane setTabbed, String setTitle, String setIcon, JPanel setPanel, int sLeft, int sTop, int sWidth, int sHeight){
        setTabbed.setBounds(sLeft, sTop, sWidth, sHeight);
        setTabbed.setCursor(new Cursor(Cursor.HAND_CURSOR));
        setTabbed.setFont(new Font("Dialog",Font.CENTER_BASELINE,12));
        setTabbed.setForeground(new Color(166,0,0));
        setTabbed.addTab(setTitle, new ImageIcon(setIcon), setPanel);
        return setTabbed;   
    }
    
    public JButton createJToolbarButton(String srcToolTipText, String srcImageLocation, String srcActionCommand, ActionListener JToolBarActionListener){
        JButton btnToolbar = new JButton(new ImageIcon(srcImageLocation));
        btnToolbar.setActionCommand(srcActionCommand);
        btnToolbar.setToolTipText(srcToolTipText);
        btnToolbar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnToolbar.setFont(new Font("Dialog",Font.PLAIN,12));
        btnToolbar.addActionListener(JToolBarActionListener);
        return btnToolbar;      
    }
    
    public void numValidator(JTextField txtField){
        txtField.addKeyListener(new KeyAdapter(){
            public void keyTyped(KeyEvent e){
                char c = e.getKeyChar();
                if(!(Character.isDigit(c) || (c ==KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))){
                    e.consume();
                }
                
            }
        });
    
    
    }
    
    
    
    
    
    
}
