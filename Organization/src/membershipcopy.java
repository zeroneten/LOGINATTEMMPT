
import com.mysql.jdbc.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
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
public class membershipcopy extends javax.swing.JFrame {
    Connection conn=null;
    Statement stmt=null;
    PreparedStatement pst=null;
    ResultSet rs=null;
    DefaultTableModel dm;
   String groupid,name,idno,telno,date,activity="";
   int ro;
    /**
     * Creates new form membershipcopy
     */
    public membershipcopy() {
        initComponents();
        conn=Dbconnection.Dbconnection();
        selectmemberid();
        showTableMembership();
    }
    private void showTableMembership(){
      try{
        String sql="select*from membership";
        pst=conn.prepareStatement(sql);
        rs=pst.executeQuery();
        jTable1.setModel(DbUtils.resultSetToTableModel(rs));
      }catch(Exception ex){
          JOptionPane.showMessageDialog(null,ex);
      }
    }
    private void selectmemberid(){
        try{
            String sql="select Rec_Id from groups";
            pst=conn.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                String Rec_Id=rs.getString("Rec_Id");
                cmbgroupid.addItem(Rec_Id);
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }
    }
    private void enter(){
        try{
//            String sql="insert into membership(`Group_Id`,`Name`,`Id_No`,`Tel_No`,`Date`)values (?,?,?,?,?)";
            String insert="insert into membership(Group_Id,Name,Id_No,Tel_No,Date)values(?,?,?,?,?)";
            pst=conn.prepareStatement(insert);
            String value=cmbgroupid.getSelectedItem().toString();
           int Id1=Integer.parseInt(value);
            pst.setString(1,value);
            pst.setString(2, txtname.getText());
            pst.setString(3,txtidno.getText());
            pst.setString(4, txttelno.getText());
            pst.setString(5,((JTextField)jDateChoosermembership.getDateEditor().getUiComponent()).getText());
            
            pst.execute();
            showTableMembership();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }
     
    }
    private void delete(){
        try{
            int ro=jTable1.getSelectedRow();
            String va=jTable1.getModel().getValueAt(ro, 4).toString();
            int confirmdelete=JOptionPane.showConfirmDialog(null,"Doyou really want to delete this row?","Delete",JOptionPane.YES_NO_OPTION);
            String delete="delete from membership where Rec_Id='"+ro+"'";
            pst=conn.prepareStatement(delete);
            pst.execute();
            showTableMembership();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }
    }
    private void resetfields(){
        try{
            cmbgroupid.setSelectedIndex(0);
            txtname.setText(null);
            txtidno.setText(null);
            txttelno.setText(null);
            jDateChoosermembership.setDate(null);
            
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }
    }
    private void mouseclicked(){
        try{
            int ro=jTable1.getSelectedRow();
            String va=jTable1.getModel().getValueAt(ro,5).toString();
            String sql="select*from membership where Rec_Id='"+va+"'";
            pst=conn.prepareStatement(sql);
            rs=pst.executeQuery();
            if(rs.next()){
                String add1=rs.getString("Group_Id");
                cmbgroupid.setSelectedItem(add1);
                String add2=rs.getString("Name");
                txtname.setText(add2);
                String add3=rs.getString("Id_No");
                txtidno.setText(add3);
                String add4=rs.getString("Tel_No");
                txttelno.setText(add4);
                Date add5=rs.getDate("Date");
                jDateChoosermembership.setDate(add5);
            }
            
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }
    }
    private void editorupdate(){
        try{
            int ro=jTable1.getSelectedRow();
            String va=jTable1.getModel().getValueAt(ro,5).toString();
       
            groupid=cmbgroupid.getSelectedItem().toString();
            name=txtname.getText();
            idno=txtidno.getText();
            telno=txttelno.getText();
//            jDateChoosermembership.getDate();
            String edit="update membership set Group_Id='"+groupid+"',Name='"+name+"',Id_No='"+idno+"',Tel_No='"+telno+"'where Rec_Id='"+Integer.parseInt(va)+"'";
            pst=conn.prepareStatement(edit);
            pst.execute();
            JOptionPane.showMessageDialog(null,"updated");
            
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtsearch = new javax.swing.JTextField();
        cmbgroupid = new javax.swing.JComboBox<>();
        txtname = new javax.swing.JTextField();
        txttelno = new javax.swing.JTextField();
        txtidno = new javax.swing.JTextField();
        jDateChoosermembership = new com.toedter.calendar.JDateChooser();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Membership");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(297, 16, 690, 180));

        jLabel1.setText("filter");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 14, 47, -1));

        jLabel2.setText("Group_Id");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 49, -1, -1));

        jLabel3.setText("Name");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 93, -1, -1));

        jLabel4.setText("Id_No");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 148, -1, -1));

        jLabel5.setText("Tel_No");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 192, -1, -1));

        jLabel6.setText("Date");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 238, -1, -1));
        getContentPane().add(txtsearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 11, 190, -1));

        cmbgroupid.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selectmemberid" }));
        getContentPane().add(cmbgroupid, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 49, 190, -1));
        getContentPane().add(txtname, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 93, 190, -1));
        getContentPane().add(txttelno, new org.netbeans.lib.awtextra.AbsoluteConstraints(74, 189, 200, -1));
        getContentPane().add(txtidno, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 145, 200, -1));

        jDateChoosermembership.setDateFormatString("yyyy-MM-dd");
        getContentPane().add(jDateChoosermembership, new org.netbeans.lib.awtextra.AbsoluteConstraints(64, 232, 210, -1));

        jButton1.setText("Save");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 280, -1, -1));

        jButton2.setText("Reset");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(131, 279, -1, -1));

        jButton3.setText("Edit");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 230, -1, -1));

        jButton4.setText("Delete");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 230, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        try{
            enter();
  
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        try{
              resetfields();
        }catch(Exception ex){
        JOptionPane.showMessageDialog(null,ex);
    }
      
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        try{
            delete();
            showTableMembership();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        try{
            mouseclicked();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        editorupdate();
        showTableMembership();
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
            java.util.logging.Logger.getLogger(membershipcopy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(membershipcopy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(membershipcopy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(membershipcopy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new membershipcopy().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cmbgroupid;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private com.toedter.calendar.JDateChooser jDateChoosermembership;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtidno;
    private javax.swing.JTextField txtname;
    private javax.swing.JTextField txtsearch;
    private javax.swing.JTextField txttelno;
    // End of variables declaration//GEN-END:variables
}
