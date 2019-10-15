
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
public class member extends javax.swing.JFrame {
 Connection conn;
 PreparedStatement pst;
 ResultSet rs;
 String groupid,name,idno,telno;
 String temp;
 int number;
 
 
    /**
     * Creates new form member
     */
    public member() {
        initComponents();
        conn=Dbconnection.Dbconnection();
        showmembership();
        cmbgroupid();
         Date d=new Date();
        SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd");
        jDateChoosermember.setDate(d);
    }
    private void showmembership(){
        try{
          String sql="  select (CONCAT(groups.Group_Name,', ',groups.Rec_Id))ff,membership.Name,membership.Id_No,membership.Tel_No,membership.Date,membership.Rec_Id FROM groups,membership WHERE groups.Rec_Id=membership.Group_Id";
            pst=conn.prepareStatement(sql);
          rs=pst.executeQuery();
          jTable1.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }
    }
    private void cmbgroupid(){
    
        try{
            //String sql="select group_name from membershipgroups where Rec_Id='"+cmbgroupid.getSelectedItem()+"'";
          String sql="select CONCAT(Group_Name,', ',Rec_Id)AS ff from groups";
            pst=conn.prepareStatement(sql);
            pst.execute();
            rs=pst.executeQuery();
            while(rs.next()){
                String j=rs.getString("ff");
                cmbgroupid.addItem(j);
//                 String j=rs.getString("Rec_Id");
//                cmbgroupid.addItem(j);
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }
          try{
        Scanner fromstr=new Scanner(cmbgroupid.getSelectedItem().toString());
        fromstr.useDelimiter("[^0-9]+ ");
//        System.out.println("the words in the string are:");
     if(fromstr.hasNext()){
//         String temp=fromstr.next();
//            System.out.println(temp);
         number=Integer.parseInt(fromstr.next());
//            System.out.println(fromstr.next());
  
     }
                    }catch(Exception ex){
                        JOptionPane.showMessageDialog(null,ex);
                    }
                   
               
       
    }
    private void enter(){
//      
        try{
  //  groupid=cmbgroupid.getSelectedItem().toString();
            cmbgroupid();                
            name=txtname.getText();
            idno=txtidno.getText();
            telno=txttelno.getText();
          String d=((JTextField)jDateChoosermember.getDateEditor().getUiComponent()).getText();
            String sql="Insert into membership(Group_Id,Name,Id_No,Tel_No,Date) values('"+number+"','"+name+"','"+idno+"','"+telno+"','"+d+"')";
            pst=conn.prepareStatement(sql);
            pst.execute();
            showmembership();
            JOptionPane.showMessageDialog(null,"record inserted");
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }
    } 
    private void mouseclicked(){
            try{
       int ro=jTable1.getSelectedRow();
        String va=jTable1.getModel().getValueAt(ro,5).toString(); 
     String sql="  select (CONCAT(groups.Group_Name,', ',groups.Rec_Id))ff,membership.Name,membership.Id_No,membership.Tel_No,membership.Date,membership.Rec_Id FROM groups,membership WHERE groups.Rec_Id=membership.Group_Id AND membership.Rec_Id='"+Integer.parseInt(va)+"'";   
        pst=conn.prepareStatement(sql);
        rs=pst.executeQuery();
        while(rs.next()){
            groupid=rs.getString("ff");
            cmbgroupid.setSelectedItem(groupid);
             name=rs.getString("name");
             txtname.setText(name);
              idno=rs.getString("Id_No");
             txtidno.setText(idno);
              telno=rs.getString("Tel_No");
             txttelno.setText(telno);
             Date date=rs.getDate("Date");
             jDateChoosermember.setDate(date);
        }
            
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }
    }
    private void update(){
          int ro=jTable1.getSelectedRow();
          String va=jTable1.getValueAt(jTable1.getSelectedRow(),5).toString();
//           groupid=cmbgroupid.getSelectedItem().toString();
          cmbgroupid();
            name=txtname.getText();
            idno=txtidno.getText();
            telno=txttelno.getText();
          String d=((JTextField)jDateChoosermember.getDateEditor().getUiComponent()).getText();
   String sql="update membership set Group_Id='"+number+"',Name='"+name+"',Id_No='"+idno+"',Tel_No='"+telno+"',Date='"+d+"' where Rec_Id='"+Integer.parseInt(va)+"'";
        try{
            pst=conn.prepareStatement(sql);
            pst.execute();
            showmembership();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }
    }
    private void regularexpressions(){
        
        
        
//        try{
////                    String regex = "[a-zA-z0-9]*";
//
//          String reg="[a-zA-z0-9]*"; 
//          Pattern pattern=Pattern.compile(reg);
//          Matcher regexMatcher=pattern.matcher(cmbgroupid.getSelectedItem().toString());
//          if(!regexMatcher.matches())
//          {
//              JOptionPane.showMessageDialog(null,"cant insert!");
//          }
//          else{
//              JOptionPane.showMessageDialog(null,"correct");
//          }
//        }catch(Exception ex){
//            JOptionPane.showMessageDialog(null,ex);
//        }
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
        cmbgroupid = new javax.swing.JComboBox();
        txtname = new javax.swing.JTextField();
        txtidno = new javax.swing.JTextField();
        txttelno = new javax.swing.JTextField();
        jDateChoosermember = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Group Id");

        jLabel2.setText("Name");

        jLabel3.setText("Tel no");

        jLabel4.setText("Id number");

        jLabel5.setText("Date");

        cmbgroupid.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbgroupidItemStateChanged(evt);
            }
        });
        cmbgroupid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbgroupidActionPerformed(evt);
            }
        });

        jDateChoosermember.setDateFormatString("yyyy-MM-dd");

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

        jButton1.setText("Save");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Reset");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Edit");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Delete");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("jButton5");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5))
                        .addGap(38, 38, 38)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton5)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txttelno, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(cmbgroupid, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtname, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtidno, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                                .addComponent(jDateChoosermember, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jButton1)
                .addGap(64, 64, 64)
                .addComponent(jButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addGap(41, 41, 41)
                .addComponent(jButton4)
                .addGap(160, 160, 160))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jButton5)
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cmbgroupid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel4))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(txtidno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txttelno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(jDateChoosermember, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jButton3))
                .addGap(23, 23, 23))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbgroupidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbgroupidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbgroupidActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        enter();
     
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
         mouseclicked();
     
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        update();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void cmbgroupidItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbgroupidItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbgroupidItemStateChanged

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        cmbgroupid();
  Scanner fromstr=new Scanner(cmbgroupid.getSelectedItem().toString());
        fromstr.useDelimiter("[^0-9]+ ");
//        System.out.println("the words in the string are:");
     if(fromstr.hasNext()){
//         String temp=fromstr.next();
//            System.out.println(temp);
            System.out.println(fromstr.next());
  
     }
    }//GEN-LAST:event_jButton5ActionPerformed

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
            java.util.logging.Logger.getLogger(member.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(member.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(member.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(member.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new member().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmbgroupid;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private com.toedter.calendar.JDateChooser jDateChoosermember;
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
