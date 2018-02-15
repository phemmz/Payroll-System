
package com.PayRoll;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class clsConnection {
    String url="";
    String username="";
    String password="";
    
    public Connection setConnection(Connection conn, String username, String password){
        try{
            Properties props = new Properties();
            String filename = "MakeDB.ini";
            FileInputStream in = new FileInputStream(filename);
            props.load(in);
            String drivers = props.getProperty("jdbc.drivers");
            if(drivers!=null){
                System.setProperty("jdbc.drivers", drivers);
                url= props.getProperty("jdbc.url");
                username = props.getProperty("jdbc.username");
                password = props.getProperty("jdbc.password");
                conn = DriverManager.getConnection(url,username,password);

            }
        }
        catch(SQLException e){
            System.err.println("SQL Exception");
            e.printStackTrace();
        }
        catch(IOException e){
            System.out.println("\nIO Exception");
        }
        catch(Exception e){
            System.out.println("\nAnother Error");
        }
        return conn;        
    }
}
