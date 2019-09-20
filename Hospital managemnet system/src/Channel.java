
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
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
public class Channel extends javax.swing.JFrame {
  Connection conn=null;
  PreparedStatement pst;
  ResultSet rs;
  String chno;
    /**
     * Creates new form Channel
     */
    public Channel() {
        initComponents();
        conn=Dbconnect.dbconnection();
        autoid();
        showchnnel();
        cmbdoctor();
        cmbpatient();
          Date d=new Date();
          SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd");
          jDateChooserchanneldate.setDate(d);   
    }
 public class Doctor{
     String id;
     String name;
     public Doctor(String Id,String Name){
         this.id=Id;
         this.name=Name;
         
     }
     public String toString(){
     return name;
 }
     
     
     
 }
 public class Patient{
     String id;
     String name;
     public Patient(String id,String name){
         this.id=id;
         this.name=name;
         
     }
     public String toString(){
     return name;
 }
     
     
     
 }
    
    
    private void showchnnel(){
        try{
//            String sql="Select channel.Channelno, doctorregistration.Doctor_No,patientregistration.Patient_No,channel.Roomno,channel.Date FROM channel,doctorregistration,patientregistration ";
            String sql="select*from Channel";
            pst=conn.prepareStatement(sql);
            rs=pst.executeQuery();
            jTable1.setModel(DbUtils.resultSetToTableModel(rs));
            
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }
    }
    private void autoid(){
        try{
          String sql=("select MAX(Channelno)from channel");
            Statement stmt=conn.createStatement();
            rs=stmt.executeQuery(sql);
            rs.next();
            rs.getString("MAX(Channelno)");
            if(rs.getString("MAX(Channelno)")==null){
                jchannelno.setText("CH001");
      }
            else{
                Long Id=Long.parseLong(rs.getString("MAX(Channelno)").substring(2,rs.getString("MAX(Channelno)").length()));
//                Long Id=Long.parseLong(rs.getString(rs.getString("MAX(Patient_No)").substring(2,rs.getString("MAX(Patient_No)").length())));
                  Id++;
               jchannelno.setText("CH"+String.format("%03d",Id));    
          }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }
    }
    private void cmbdoctor(){
        try{
           String sql="select*from doctorregistration" ;
           pst=conn.prepareStatement(sql);
           rs=pst.executeQuery();
           cmbdoctorname.removeAll();
           while(rs.next()){
              cmbdoctorname.addItem(new Doctor(rs.getString(1),rs.getString(2)));
           }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }
    }
    private void cmbpatient(){
        try{
            String sql="select* from patientregistration";
            pst=conn.prepareStatement(sql);
            rs=pst.executeQuery();
            cmbpatientname.removeAll();
            while(rs.next()){
                cmbpatientname.addItem(new Patient(rs.getString(1),rs.getString(2)));
            }
            
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }
    }
    private void enter(){
        try{
                  String dno=jchannelno.getText();
            Doctor d =(Doctor)cmbdoctorname.getSelectedItem();
            Patient p=(Patient)cmbpatientname.getSelectedItem();
            String dqua=jromno.getValue().toString();
            String b=((JTextField)jDateChooserchanneldate.getDateEditor().getUiComponent()).getText();      
            String sql="insert into Channel (Channelno,doctorname,Patientname,Roomno,Date)values(?,?,?,?,?)";
            pst=conn.prepareStatement(sql);
            pst.setString(1, dno);
            pst.setString(2,d.id);
            pst.setString(3,p.id);
            pst.setString(4, dqua);
            pst.setString(5,b);
           pst.execute();
            showchnnel();
            reset();
            autoid();
            JOptionPane.showMessageDialog(null,"channel created");
                        
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }
    }
    private void reset(){
        try{
            cmbdoctorname.setSelectedIndex(0);
            cmbpatientname.setSelectedIndex(0);
            jromno.setValue(0);
            jDateChooserchanneldate.setDate(null);
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jchannelno = new javax.swing.JLabel();
        cmbdoctorname = new javax.swing.JComboBox();
        cmbpatientname = new javax.swing.JComboBox();
        jromno = new javax.swing.JSpinner();
        jDateChooserchanneldate = new com.toedter.calendar.JDateChooser();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 153, 255));

        jLabel1.setText("Channel No");

        jLabel2.setText("Doctor Name");

        jLabel3.setText("Patient Name");

        jLabel4.setText("Room No");

        jLabel5.setText("Channel Date");

        jchannelno.setText("jLabel6");

        cmbdoctorname.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select doctor" }));
        cmbdoctorname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbdoctornameActionPerformed(evt);
            }
        });

        cmbpatientname.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "select patient" }));

        jDateChooserchanneldate.setDateFormatString("yyyy-MM-dd");

        jButton1.setText("Save");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Cancel");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

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

        jButton3.setText("Delete");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Update");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(36, 36, 36)
                                        .addComponent(jchannelno, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(27, 27, 27)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cmbdoctorname, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cmbpatientname, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jromno)
                                    .addComponent(jDateChooserchanneldate, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jchannelno))
                        .addGap(39, 39, 39)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(cmbdoctorname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(cmbpatientname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jromno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jDateChooserchanneldate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton2)
                            .addComponent(jButton3)
                            .addComponent(jButton4)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cmbdoctornameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbdoctornameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbdoctornameActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        enter();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        reset();
//////        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
           try{
         DefaultTableModel dm=(DefaultTableModel)jTable1.getModel();
            int ro=jTable1.getSelectedRow();
            chno=dm.getValueAt(ro, 0).toString();
            JOptionPane.showMessageDialog(null,chno);
//            jchannelno.setText(dm.getValueAt(ro, 0).toString());
//            cmbdoctorname.setSelectedItem(dm.getValueAt(ro, 1).toString());
//            cmbpatientname.setSelectedItem(dm.getValueAt(ro, 2).toString());
//            jromno.setValue(dm.getValueAt(ro, 3).toString());     
            
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }
// DefaultTableModel dm=(DefaultTableModel)jTable1.getModel();
//           int ro=jTable1.getSelectedRow();
////        String va=(jTable1.getModel().getValueAt(ro,3).toString());
//        String sql="select*from channel where Channelno=?";
//        try{
//            pst=conn.prepareStatement(sql);
//            rs=pst.executeQuery();
//            if(rs.next()){
//        jchannelno.setText(dm.getValueAt(ro, 0).toString());
//
////                String chno=rs.getString("Channelno");
////                jchannelno.setText(chno);
//                String loca=rs.getString("doctorname");
//                cmbdoctorname.setSelectedItem(loca); 
//                String pa=rs.getString("Patientname");
//                Date d=rs.getDate("Date");
//                jDateChooserchanneldate.setDate(d);
//            } 
//            
//            
//        }catch(Exception ex){
//            JOptionPane.showMessageDialog(null,ex);
//        }
           
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        try{
        String sql="delete from Channel where Channelno=?";
            pst=conn.prepareStatement(sql);
            pst.setString(1,chno);
             pst.execute();
            showchnnel();
            reset();
            autoid();
            JOptionPane.showMessageDialog(null,"channel deleted");
       
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }
                 
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
            java.util.logging.Logger.getLogger(Channel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Channel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Channel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Channel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Channel().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmbdoctorname;
    private javax.swing.JComboBox cmbpatientname;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private com.toedter.calendar.JDateChooser jDateChooserchanneldate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel jchannelno;
    private javax.swing.JSpinner jromno;
    // End of variables declaration//GEN-END:variables
}
