
import com.mysql.jdbc.Statement;
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
public class memberr extends javax.swing.JFrame {
Connection conn;
    PreparedStatement pst;
    ResultSet rs;
    String ff,name,id,te,da;
     int number;
     String act;
 
    /**
     * Creates new form memberr
     */
    public memberr() {
        initComponents();
          Date d=new Date();
        SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd");
        jDateChooser1.setDate(d);
        conn=Dbconnection.Dbconnection();
        cmbgroup();
        membership();
    }
    private void membership(){
        try{
          String sql="  select (CONCAT(groups.Group_Name,', ',groups.Rec_Id))ff,membership.Name,membership.Id_No,membership.Tel_No,membership.Date,membership.Rec_Id FROM groups,membership WHERE groups.Rec_Id=membership.Group_Id";
            pst=conn.prepareStatement(sql);
            rs=pst.executeQuery();
            jTable1.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }
    }
    private void cmbgroup(){
        try{
            String sql="select CONCAT(Group_Name,', ',Rec_Id)AS ff from groups";
            pst=conn.prepareStatement(sql);
            pst.execute();
            rs=pst.executeQuery();
            while(rs.next()){
            String j=rs.getString("ff");
            cmbgroupid.addItem(j);
            }
            
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }
           try{
        Scanner fromstr=new Scanner(cmbgroupid.getSelectedItem().toString());
        fromstr.useDelimiter("[^0-9]+ ");
     if(fromstr.hasNext()){
     number=Integer.parseInt(fromstr.next());
     }
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,ex);
            }

               
    }
    private void savee(){
        try{
           if((!(act=="JHgf"))){
                  cmbgroup();                
            name=txtname.getText();
            id=txtidno.getText();
            te=txttelno.getText();
        da=((JTextField)jDateChooser1.getDateEditor().getUiComponent()).getText();
            String sql="Insert into membership(Group_Id,Name,Id_No,Tel_No,Date) values('"+number+"','"+name+"','"+id+"','"+te+"','"+da+"')";
            pst=conn.prepareStatement(sql);
            pst.execute();
            membership();
            JOptionPane.showMessageDialog(null,"record inserted");
         
     }
     else {
            int ro=jTable1.getSelectedRow();
        String va=jTable1.getModel().getValueAt(ro, 5).toString();
        
           cmbgroup();                
            name=txtname.getText();
            id=txtidno.getText();
            te=txttelno.getText();
          da=((JTextField)jDateChooser1.getDateEditor().getUiComponent()).getText();
   String sql="update membership set Group_Id='"+number+"',Name='"+name+"',Id_No='"+id+"',Tel_No='"+te+"',Date='"+da+"' where Rec_Id='"+Integer.parseInt(va)+"'";
        try{
            pst=conn.prepareStatement(sql);
            pst.execute();
            membership();
            JOptionPane.showMessageDialog(null,"updated");
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }
            
         
     }  
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }
    }
    private void save(){
        try{
               cmbgroup();                
            name=txtname.getText();
            id=txtidno.getText();
            te=txttelno.getText();
        da=((JTextField)jDateChooser1.getDateEditor().getUiComponent()).getText();
            String sql="Insert into membership(Group_Id,Name,Id_No,Tel_No,Date) values('"+number+"','"+name+"','"+id+"','"+te+"','"+da+"')";
            pst=conn.prepareStatement(sql);
            pst.execute();
            membership();
            JOptionPane.showMessageDialog(null,"record inserted");
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }
    }
    private void mouse(){
        act="JHgf";
        int ro=jTable1.getSelectedRow();
        String va=jTable1.getModel().getValueAt(ro, 5).toString();
        try{
 String sql="  select (CONCAT(groups.Group_Name,', ',groups.Rec_Id))ff,membership.Name,membership.Id_No,membership.Tel_No,membership.Date,membership.Rec_Id FROM groups,membership WHERE groups.Rec_Id=membership.Group_Id AND membership.Rec_Id='"+Integer.parseInt(va)+"'";   
   pst=conn.prepareStatement(sql);
   rs=pst.executeQuery();
   while(rs.next()){
      ff=rs.getString("ff");
      cmbgroupid.setSelectedItem(ff);
      name=rs.getString("Name");
      txtname.setText(name);
      id=rs.getString("Id_No");
      txtidno.setText(id);
      te=rs.getString("Tel_No");
      txttelno.setText(te);              
      Date da=rs.getDate("Date");
      jDateChooser1.setDate(da);
       
   }
            
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
            String sql="delete from membership where Rec_Id='"+Integer.parseInt(va)+"'";
            pst=conn.prepareStatement(sql);
            pst.execute();
       membership();
       }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }
    }
    private void update(){
        int ro=jTable1.getSelectedRow();
        String va=jTable1.getModel().getValueAt(ro, 5).toString();
        try{ 
            cmbgroup();                
            name=txtname.getText();
            id=txtidno.getText();
            te=txttelno.getText();
          da=((JTextField)jDateChooser1.getDateEditor().getUiComponent()).getText();
   String sql="update membership set Group_Id='"+number+"',Name='"+name+"',Id_No='"+id+"',Tel_No='"+te+"',Date='"+da+"' where Rec_Id='"+Integer.parseInt(va)+"'";
        try{
            pst=conn.prepareStatement(sql);
            pst.execute();
            membership();
            JOptionPane.showMessageDialog(null,"updated");
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }
            
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }
    }
    private void reset(){
        try{
          cmbgroupid.setSelectedIndex(0);
          txtname.setText(null);
          txtidno.setText(null);
          txttelno.setText(null);
          jDateChooser1.setDate(null);
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtidno = new javax.swing.JTextField();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        cmbgroupid = new javax.swing.JComboBox();
        txtname = new javax.swing.JTextField();
        txttelno = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Group");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 28, -1, -1));

        jLabel2.setText("Name");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 66, -1, -1));

        jLabel3.setText("Id no");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 112, -1, -1));

        jLabel4.setText("Tel no");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 161, -1, -1));

        jLabel5.setText("Date");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 202, -1, -1));
        getContentPane().add(txtidno, new org.netbeans.lib.awtextra.AbsoluteConstraints(96, 109, 152, -1));

        jDateChooser1.setDateFormatString("yyyy-MM-dd");
        getContentPane().add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(96, 196, 155, -1));

        getContentPane().add(cmbgroupid, new org.netbeans.lib.awtextra.AbsoluteConstraints(96, 25, 152, -1));
        getContentPane().add(txtname, new org.netbeans.lib.awtextra.AbsoluteConstraints(96, 63, 152, -1));
        getContentPane().add(txttelno, new org.netbeans.lib.awtextra.AbsoluteConstraints(96, 158, 152, -1));

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

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(295, 0, -1, 207));

        jButton1.setText("Edit");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 251, -1, -1));

        jButton2.setText("Delete");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(479, 251, -1, -1));

        jButton3.setText("Reset");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(366, 251, -1, -1));

        jButton4.setText("Save");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 240, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        delete();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        mouse();
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        update();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        savee();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        reset();
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(memberr.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(memberr.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(memberr.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(memberr.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new memberr().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmbgroupid;
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
    private javax.swing.JTextField txtidno;
    private javax.swing.JTextField txtname;
    private javax.swing.JTextField txttelno;
    // End of variables declaration//GEN-END:variables
}
