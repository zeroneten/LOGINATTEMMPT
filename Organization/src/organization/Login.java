/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package organization;

import com.sun.glass.events.KeyEvent;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.Timer;

/**
 *
 * @author ndirituedwin
 */
public class Login extends javax.swing.JFrame {
// settersandgetters ss;
  String var;
  Statement stmt;
  PreparedStatement pst;
  Connection conn;
  ResultSet rs;
  String password;
  String name,pass;
  String pfolie;

    /**
     * Creates new form Login
     */
    public Login() {
        initComponents();
        conn=Dbconnection.Dbconnection();
        date();
        time();
        txtusername.requestFocus();
//         loginnnn();    
        
    }
    void date(){

        Date d=new Date();

        SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd");

        jdate.setText(s.format(d));

    }
private void loginnnn(){
//    String a=cmbprofile.getSelectedInde
    try{
        
        name=txtusername.getText();
      name = name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase();  

        pass=jPasswordField1.getText(); 
        String sql="select * from user_registration where username=? and password=?";
        pst=conn.prepareStatement(sql);
        pst.setString(1, txtusername.getText());
        pst.setString(2, jPasswordField1.getText());
        rs=pst.executeQuery();
        if(rs.next()){
            pfolie=rs.getString("Profile");
             this.setVisible(false);
            new Organizationn(pfolie,name,pass).setVisible(true);
//                new organizationnn(pfolie,name,pass).setVisible(true);

        }
           
            
         }catch(Exception ex){
             JOptionPane.showMessageDialog(null,ex);
         }
}
    private void time(){

        new Timer(0,new ActionListener(){

            @Override

            public void actionPerformed(ActionEvent e) {

                 Date d=new Date();

                 SimpleDateFormat s=new SimpleDateFormat("hh:mm:ss:a");

                 jtime.setText(s.format(d));

            }

            

        }).start();
//
    }
     private void login(){
         
              
                
        
               
                

        try{
            var=txtusername.getText();
            password=jPasswordField1.getText();
            String sq="select username,Password from user_registration where username=? and Password=?";
            pst=conn.prepareStatement(sq);
            pst.setString(1,txtusername.getText());
            pst.setString(2, jPasswordField1.getText());

           rs=pst.executeQuery();
                    if(rs.next()){
                        JOptionPane.showMessageDialog(null,"everything matched");
                        this.setVisible(false);
//                        ss.setStr(var);
//                        new organizationnn(var).setVisible(true);
                        
                   }

                    else{

                        JOptionPane.showMessageDialog(null,"something was wrong");

                    }

                                     

        }catch(Exception ex){

            JOptionPane.showMessageDialog(null,ex);

        }

    }
     private void loginn(){
         try{
            name=txtusername.getText();
//             var=txtusername.getText();
            password=jPasswordField1.getText();
//  String sql="select* from user_registration where profile=?,username=? and password=?";
           String sql="select username,Password from user_registration where username=? and Password=?";
            pst=conn.prepareStatement(sql);
            pst.setString(1,txtusername.getText());
            pst.setString(2, jPasswordField1.getText());

           rs=pst.executeQuery();
                    if(rs.next()){
                        JOptionPane.showMessageDialog(null,"everything matched");
                        this.setVisible(false);     
//                        new Organizationn(name).setVisible(true);
                           }

                    else{

                        JOptionPane.showMessageDialog(null,"something was wrong");

                    }
         }catch(Exception ex){
             JOptionPane.showMessageDialog(null,ex);
         }
     }

    private void cancel(){

        try{

            txtusername.setText(null);

            jPasswordField1.setText(null);

        }catch(Exception ex){

            JOptionPane.showMessageDialog(null,ex);

        }

    }
private void cliar(){
    try{
JTextField clear=null;
for(Component c:jPanel1.getComponents()){
    if(c.getClass().toString().contains("javax.swing.JTextField")){
        clear=(JTextField)c;
        clear.setText(null);
        jPasswordField1.setText(null);
    }
}
    }catch(Exception ex){
        JOptionPane.showMessageDialog(null,ex);
    }
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jdate = new javax.swing.JLabel();
        jtime = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtusername = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jCheckBox = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jdate.setText("jLabel3");

        jtime.setText("jLabel3");

        jPanel1.setBackground(new java.awt.Color(153, 0, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "User- login", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 3, 18), new java.awt.Color(255, 0, 204))); // NOI18N

        jLabel1.setText("Username");

        txtusername.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtusernameKeyPressed(evt);
            }
        });

        jLabel2.setText("Password");

        jPasswordField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPasswordField1KeyPressed(evt);
            }
        });

        jButton2.setText("Login");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jButton2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton2KeyPressed(evt);
            }
        });

        jButton1.setText("cancel");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(43, 43, 43)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPasswordField1, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addComponent(txtusername))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(jButton2)
                        .addGap(79, 79, 79)
                        .addComponent(jButton1)))
                .addContainerGap(10, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtusername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckBox)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton1))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jdate, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtime, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(125, 125, 125))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtime)
                    .addComponent(jdate, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        
//        loginnnn();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
//        cancel();
        cliar();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxActionPerformed
        // TODO add your handling code here:

        try{

            if(jCheckBox.isSelected()){

                jPasswordField1.setEchoChar((char)0);

            }

            else{

                jPasswordField1.setEchoChar('*');

            }

        }catch(Exception ex){

            JOptionPane.showMessageDialog(null,ex);

        }
    }//GEN-LAST:event_jCheckBoxActionPerformed

    private void jButton2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton2KeyPressed
        // TODO add your handling code here:
//        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
//            loginnnn();
//        }
    }//GEN-LAST:event_jButton2KeyPressed

    private void jPasswordField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPasswordField1KeyPressed
        // TODO add your handling code here:
         if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            loginnnn();
        }
    }//GEN-LAST:event_jPasswordField1KeyPressed

    private void txtusernameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtusernameKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){       
            jPasswordField1.requestFocus();
        }
    }//GEN-LAST:event_txtusernameKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox jCheckBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JLabel jdate;
    private javax.swing.JLabel jtime;
    private javax.swing.JTextField txtusername;
    // End of variables declaration//GEN-END:variables
}
