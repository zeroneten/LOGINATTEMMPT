/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentsrecords;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author ndirituedwin
 */
public class dbconnection {
     Connection conn;
   public static Connection dbconnection(){
       try{
           Class.forName("com.mysql.jdbc.Driver");
           Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/students_records_management_system","root","");
          return conn;
       }catch(Exception ex){
           JOptionPane.showMessageDialog(null,ex);
           return null;
       }
    
}
}
