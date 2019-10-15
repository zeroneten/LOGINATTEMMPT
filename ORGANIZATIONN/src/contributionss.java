
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import net.proteanit.sql.DbUtils;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ndirituedwin
 */
public class contributionss extends javax.swing.JFrame {
   Connection conn;
   ResultSet rs;
    PreparedStatement pst;
    int number;
    String act,ty, de, am;
    /**
     * Creates new form contributionss
     */
    public contributionss() {
        initComponents();
        conn=Dbconnection.Dbconnection();
         Date d=new Date();
        SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd");
        jDateChooser1.setDate(d);
       showcontributions();
        cmbmember();
     
    }
    private void showcontributions(){
        try{
            String sql="SELECT (CONCAT(membership.Name,', ',membership.Rec_Id))name,contributions.Type,contributions.Description,contributions.Amount,contributions.Date,contributions.Rec_Id FROM membership,contributions WHERE membership.Rec_Id=contributions.Member_Id ";
            pst=conn.prepareStatement(sql);
            rs=pst.executeQuery();
            jTable1.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }
    }
    private void cmbmember(){
        try{
        String sql="SELECT (CONCAT(membership.Name,', ',membership.Rec_Id))As name from membership";  
        pst=conn.prepareStatement(sql);
        rs=pst.executeQuery();
        while(rs.next()){
            String ab=rs.getString("name");
            cmbmmber.addItem(ab);
        }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }
        try{
        Scanner fromstr=new Scanner(cmbmmber.getSelectedItem().toString());
        fromstr.useDelimiter("[^0-9]+ ");
     if(fromstr.hasNext()){
     number=Integer.parseInt(fromstr.next());
     }
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,ex);
            }
    }
    private void save(){
        try{
          if((!(act=="JHgf"))){
                  cmbmember();                
           ty=cmbtype.getSelectedItem().toString();
             de=txtdescription.getText();
             am=txtamount.getText();
      String  da=((JTextField)jDateChooser1.getDateEditor().getUiComponent()).getText();
            String sql="Insert into contributions(Member_Id,Type,Description,Amount,Date) values('"+number+"','"+ty+"','"+de+"','"+am+"','"+da+"')";
            pst=conn.prepareStatement(sql);
            pst.execute();
            showcontributions();
            JOptionPane.showMessageDialog(null,"record inserted");
         
     }
     else {
            int ro=jTable1.getSelectedRow();
        String va=jTable1.getModel().getValueAt(ro, 5).toString();
        
                 cmbmember();                
           ty=cmbtype.getSelectedItem().toString();
             de=txtdescription.getText();
             am=txtamount.getText();
      String  da=((JTextField)jDateChooser1.getDateEditor().getUiComponent()).getText();
   String sql="update contributions set Member_Id='"+number+"',Type='"+ty+"',Description='"+de+"',Amount='"+am+"',Date='"+da+"' where Rec_Id='"+Integer.parseInt(va)+"'";
        try{
            pst=conn.prepareStatement(sql);
            pst.execute();
            showcontributions();
            JOptionPane.showMessageDialog(null,"updated");
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }
            
         
     }    
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }
    }
    private void mouseclicked(){
        act="JHgf";
        try{
             int ro=jTable1.getSelectedRow();
        String va=jTable1.getModel().getValueAt(ro, 5).toString();
 String sql="SELECT (CONCAT(membership.Name,', ',membership.Rec_Id))name,contributions.Type,contributions.Description,contributions.Amount,contributions.Date,contributions.Rec_Id FROM membership,contributions WHERE membership.Rec_Id=contributions.Member_Id AND contributions.Rec_Id='"+Integer.parseInt(va)+"' ";
        pst=conn.prepareStatement(sql);
   rs=pst.executeQuery();
   while(rs.next()){
     String ff=rs.getString("name");
      cmbmmber.setSelectedItem(ff);
      ty=rs.getString("Type");
      cmbtype.setSelectedItem(ty);
      de=rs.getString("Description");
      txtdescription.setText(de);
      am=rs.getString("Amount");
      txtamount.setText(am);              
      Date da=rs.getDate("Date");
      jDateChooser1.setDate(da);
       
   }
            
       disabled();
            
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }
    }
    private void delete(){
      int ro=jTable1.getSelectedRow();
      String va=jTable1.getModel().getValueAt(ro, 5).toString();
        try{
           int confirmdelete=JOptionPane.showConfirmDialog(null, "Do you really want to delete this row?","Delete",JOptionPane.YES_NO_OPTION);
       if(confirmdelete==0){    
      
            String sql="delete from contributions where Rec_Id='"+Integer.parseInt(va)+"'";
            pst=conn.prepareStatement(sql);
            pst.execute();
            showcontributions();
            JOptionPane.showMessageDialog(null,"deleted");
       }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }
    }
    private void reset(){
        try{
            cmbmmber.setSelectedIndex(0);
            cmbtype.setSelectedIndex(0);
            txtdescription.setText(null);
            txtamount.setText(null);
            jDateChooser1.setDate(null);
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }
    }
    private void enabled(){
        cmbmmber.setEnabled(true);
        cmbtype.setEnabled(true);
        txtdescription.setEnabled(true);
        txtamount.setEnabled(true);
        jDateChooser1.setEnabled(true);
        
    }
     private void disabled(){
        cmbmmber.setEnabled(false);
        cmbtype.setEnabled(false);
        txtdescription.setEnabled(false);
        txtamount.setEnabled(false);
        jDateChooser1.setEnabled(false);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cmbtype = new javax.swing.JComboBox();
        cmbmmber = new javax.swing.JComboBox();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        txtdescription = new javax.swing.JTextField();
        txtamount = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Member");

        jLabel2.setText("Type");

        jLabel3.setText("Description");

        jLabel4.setText("Amount");

        jLabel5.setText("Date");

        cmbtype.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Registration fee", "Monthly contribution", "Penalty fee" }));

        jDateChooser1.setDateFormatString("yyyy-MM-dd");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("Delete");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Edit");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Reset");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Save");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(45, 45, 45)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cmbtype, 0, 159, Short.MAX_VALUE)
                                    .addComponent(cmbmmber, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtdescription)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtamount, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(17, 17, 17)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addGap(94, 94, 94)
                .addComponent(jButton2)
                .addGap(63, 63, 63)
                .addComponent(jButton1)
                .addGap(177, 177, 177))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(cmbmmber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(cmbtype, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(42, 42, 42)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtdescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtamount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton2)
                            .addComponent(jButton3))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton4)
                        .addGap(20, 20, 20))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        save();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
         mouseclicked();
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        delete();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        reset();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        enabled();
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(contributionss.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(contributionss.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(contributionss.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(contributionss.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new contributionss().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmbmmber;
    private javax.swing.JComboBox cmbtype;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtamount;
    private javax.swing.JTextField txtdescription;
    // End of variables declaration//GEN-END:variables
}
