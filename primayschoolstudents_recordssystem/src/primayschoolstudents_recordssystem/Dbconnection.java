/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primayschoolstudents_recordssystem;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author ndirituedwin
 */
public class Dbconnection {
     Connection conn=null;
    public static Connection Dbconnection(){
        try{
             Class.forName("com.mysql.jdbc.Driver");
            Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/primary-school","root","");
//            JOptionPane.showMessageDialog(null,"connected");
            return conn;
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
            return null;
        }
    }
    
}
