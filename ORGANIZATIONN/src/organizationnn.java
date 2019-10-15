
import com.mysql.jdbc.Statement;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
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
public class organizationnn extends javax.swing.JFrame {
Connection conn;
String sql;
String ff;
Statement stmt;
String act;
String username,profile,password,confirmpassword,name;
    PreparedStatement pst;
    ResultSet rs;
    String groupname,location,date;
    String idno,telno ;
    String type,member,description,amount;
    int number;
    String pane1;
     String pane2;
      String pane3;
       String pane4;
       
               settersandgetters set1;
               String permision;
    
    /**
     * Creates new form organizationnn
     */
    public organizationnn() {
        initComponents();
        conn=Dbconnection.Dbconnection();
//        jTabbedPane1.setEnabledAt(0,false);jTabbedPane1.setEnabledAt(1, false);jTabbedPane1.setEnabledAt(2, false);
//        jTabbedPane1.setEnabledAt(3, false);
        cmbgroup(); cmbmember();showgroups();showmembership();showContributions();showusertable();
          Date d=new Date();
        SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd");
        jdateChoosergroups.setDate(d);jDateChoosermembership.setDate(d); jDateChoosercontributions.setDate(d);
        jDateChooseruserregistration.setDate(d);
          date();
                time();
//                try{
//                    
//                    
//                                    permision=set1.getStr();
//
//                }catch(Exception ex){
//                    JOptionPane.showMessageDialog(null,ex);
//                }
//                
//                if (permision=="Admin") {
//                      jTabbedPane1.setEnabledAt(0, true);
//                }
    }
    String usname;
    String pofile;
    String passsw;
     public organizationnn(String profilee,String una,String passwordd) {
        initComponents();
       conn=Dbconnection.Dbconnection();
        jTabbedPane1.setEnabledAt(0,false);jTabbedPane1.setEnabledAt(1, false);jTabbedPane1.setEnabledAt(2, false);
        jTabbedPane1.setEnabledAt(3, false);
        cmbgroup(); cmbmember();showgroups();showmembership();showContributions();showusertable();
          Date d=new Date();
        SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd");
        jdateChoosergroups.setDate(d);jDateChoosermembership.setDate(d); jDateChoosercontributions.setDate(d);
        jDateChooseruserregistration.setDate(d);
          date();
                time();
                  this.usname=una;
        this.pofile=profilee;
        
        if (pofile.equals("Admin")){
         jTabbedPane1.setEnabledAt(0,true);   
         jTabbedPane1.setEnabledAt(1,true);
         jTabbedPane1.setEnabledAt(2,true); 
             }
        else if(pofile.equals("User")){
          jTabbedPane1.setEnabledAt(0,true);   
         jTabbedPane1.setEnabledAt(1,true);
        }
        else if(pofile.equals("Super admin")){
              jTabbedPane1.setEnabledAt(0,true);   
         jTabbedPane1.setEnabledAt(1,true);
         jTabbedPane1.setEnabledAt(2,true); 
         jTabbedPane1.setEnabledAt(3,true); 
           
        }
                
         
         
        
     }
    private void date(){
        Date d=new Date();
        SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd");
        jdate.setText(s.format(d));
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
    }
    private void showgroups(){
        try{
           sql="select*from groups";
           pst=conn.prepareStatement(sql);
           rs=pst.executeQuery();
           jtablegroups.setModel(DbUtils.resultSetToTableModel(rs));    
            
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }
    }
    private void showmembership(){
          try{
         sql="  select (CONCAT(groups.Group_Name,', ',groups.Rec_Id))ff,membership.Name,membership.Id_No,membership.Tel_No,membership.Date,membership.Rec_Id FROM groups,membership WHERE groups.Rec_Id=membership.Group_Id";
            pst=conn.prepareStatement(sql);
            rs=pst.executeQuery();
            jmembership.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }
    }
    private void showContributions(){
        try{
           sql="SELECT (CONCAT(membership.Name,', ',membership.Rec_Id))name,contributions.Type,contributions.Description,contributions.Amount,contributions.Date,contributions.Rec_Id FROM membership,contributions WHERE membership.Rec_Id=contributions.Member_Id ";
            pst=conn.prepareStatement(sql);
            rs=pst.executeQuery();
            jcontributions.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }
    }
    private void showusertable(){
        try{
            sql="select*from user_registration";
            pst=conn.prepareStatement(sql);
            rs=pst.executeQuery();
            jTableuserregistration.setModel(DbUtils.resultSetToTableModel(rs));
            
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }
    }
    private void cmbgroup(){
                    cmbgroupid.removeAllItems();

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
    private void cmbmember(){
                           cmbmember.removeAllItems();

         try{
        String sql="SELECT (CONCAT(membership.Name,', ',membership.Rec_Id))As name from membership";  
        pst=conn.prepareStatement(sql);
        rs=pst.executeQuery();
        while(rs.next()){
            String ab=rs.getString("name");
            cmbmember.addItem(ab);
        }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }
        try{
        Scanner fromstr=new Scanner(cmbmember.getSelectedItem().toString());
        fromstr.useDelimiter("[^0-9]+ ");
     if(fromstr.hasNext()){
     number=Integer.parseInt(fromstr.next());
     }
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,ex);
            }
    }
    private void save(){
     if(jTabbedPane1.getSelectedIndex()==0){
                            try{
                                 if((!(act=="JHgf"))){
                             stmt = (Statement) conn.createStatement();
                             groupname=txtgroupname.getText();
                             location=txtlocation.getText();
                             String d=((JTextField)jdateChoosergroups.getDateEditor().getUiComponent()).getText();
                             String insert="insert into `groups`(`Group_Name`,`Location`,`Date`)values('"+groupname+"','"+location+"','"+d+"')";
                             stmt.executeUpdate(insert);
                             showgroups();
                             JOptionPane.showMessageDialog(null,"row inserted");
                         }
                         else {
                             int ro=jtablegroups.getSelectedRow();
                             String va=jtablegroups.getValueAt(jtablegroups.getSelectedRow(), 3).toString();
                             stmt = (Statement) conn.createStatement();
                             groupname=txtgroupname.getText();
                             location=txtlocation.getText();
                             String d=((JTextField)jdateChoosergroups.getDateEditor().getUiComponent()).getText();
                             String update="update  `groups`set`Group_Name`='"+groupname+"',`Location`='"+location+"',`Date`='"+d+"' where Rec_Id='"+Integer.parseInt(va)+"'";
                             stmt.executeUpdate(update);
                             showgroups();
                             JOptionPane.showMessageDialog(null,"row updated");

                         }

                              }catch(Exception ex){
                                JOptionPane.showMessageDialog(null,ex);
                            }  
            }
     else if(jTabbedPane1.getSelectedIndex()==1){
                            try{
                                  if((!(act=="JHgf"))){
                                     cmbgroup();                
                               name=txtname.getText();
                               idno=txtidno.getText();
                                int i=Integer.parseInt(idno);
                               telno=txttelno.getText();
                                  int y=Integer.parseInt(telno);
                           date=((JTextField)jDateChoosermembership.getDateEditor().getUiComponent()).getText();
                               String sql="Insert into membership(Group_Id,Name,Id_No,Tel_No,Date) values('"+number+"','"+name+"','"+idno+"','"+telno+"','"+date+"')";
                               pst=conn.prepareStatement(sql);
                               pst.execute();
                               showmembership();
                               JOptionPane.showMessageDialog(null,"record inserted");
                        }
                        else {
                               int ro=jmembership.getSelectedRow();
                           String va=jmembership.getModel().getValueAt(ro, 5).toString();

                              cmbgroup();                
                               name=txtname.getText();
                               idno=txtidno.getText();
                                  int i=Integer.parseInt(idno);
                               telno=txttelno.getText();
                                  int y=Integer.parseInt(telno);
                             date=((JTextField)jDateChoosermembership.getDateEditor().getUiComponent()).getText();
                      String sql="update membership set Group_Id='"+number+"',Name='"+name+"',Id_No='"+idno+"',Tel_No='"+telno+"',Date='"+date+"' where Rec_Id='"+Integer.parseInt(va)+"'";
                           try{
                               pst=conn.prepareStatement(sql);
                               pst.execute();
                               showmembership();
                               JOptionPane.showMessageDialog(null,"updated");
                           }catch(Exception ex){
                               JOptionPane.showMessageDialog(null,ex);
                           }
                        }                                 
                            }catch(Exception ex){
                                JOptionPane.showMessageDialog(null,ex);
                            }
                        }
     else if(jTabbedPane1.getSelectedIndex()==2){
                                try{
                                       if((!(act=="JHgf"))){
                                         cmbmember();                
                                  type=cmbtype.getSelectedItem().toString();
                                    description=txtdescription.getText();
                                    amount=txtamount.getText();
                              date=((JTextField)jDateChoosercontributions.getDateEditor().getUiComponent()).getText();
                                   String sql="Insert into contributions(Member_Id,Type,Description,Amount,Date) values('"+number+"','"+type+"','"+description+"','"+amount+"','"+date+"')";
                                   pst=conn.prepareStatement(sql);
                                   pst.execute();
                                   showContributions();
                                   JOptionPane.showMessageDialog(null,"record inserted");
                            }
                            else {
                                   int ro=jcontributions.getSelectedRow();
                               String va=jcontributions.getModel().getValueAt(ro, 5).toString();

                                        cmbmember();                
                                  type=cmbtype.getSelectedItem().toString();
                                    description=txtdescription.getText();
                                    amount=txtamount.getText();
                              date=((JTextField)jDateChoosercontributions.getDateEditor().getUiComponent()).getText();
                          String sql="update contributions set Member_Id='"+number+"',Type='"+type+"',Description='"+description+"',Amount='"+amount+"',Date='"+date+"' where Rec_Id='"+Integer.parseInt(va)+"'";
                               try{
                                   pst=conn.prepareStatement(sql);
                                   pst.execute();
                                   showContributions();
                                   JOptionPane.showMessageDialog(null,"updated");
                               }catch(Exception ex){
                                   JOptionPane.showMessageDialog(null,ex);
                               }
                            }    
                                }catch(Exception ex){
                                    JOptionPane.showMessageDialog(null,ex);
                                }
                            }
     else if(jTabbedPane1.getSelectedIndex()==3){
                                try{
                                  if((!(act=="JHgf"))){
                                  stmt = (Statement) conn.createStatement();
                                  name=txtname1.getText();
                                  username=txtusername.getText();
                                  profile=cmbprofile.getSelectedItem().toString();
                                  String d=((JTextField)jDateChooseruserregistration.getDateEditor().getUiComponent()).getText();
                                  password=jPasswordFieldconfirm.getText();
                                  confirmpassword=jPasswordFieldconfirm.getText();
                                  String sql="insert into user_registration(Name,Username,Profile,Date,Password,Confirm_password)values('"+name+"','"+username+"','"+profile+"','"+d+"','"+password+"','"+confirmpassword+"')";
                                stmt.executeUpdate(sql);
                                showusertable();
                                  } 
                                   else{
                                 int ro=jTableuserregistration.getSelectedRow();
                                String va=jTableuserregistration.getValueAt(jTableuserregistration.getSelectedRow(), 6).toString();
                                stmt = (Statement) conn.createStatement();
                                name=txtname1.getText();
                                username=txtusername.getText();
                                profile=cmbprofile.getSelectedItem().toString();
                                date=((JTextField)jDateChooseruserregistration.getDateEditor().getUiComponent()).getText();
                                password=jPasswordField.getText();
                                confirmpassword=jPasswordFieldconfirm.getText();
                                String update="update  `user_registration`set`Name`='"+name+"',`Username`='"+username+"',`Profile`='"+profile+"',`Date`='"+date+"',`Password`='"+password+"',`Confirm_password`='"+confirmpassword+"'where Rec_Id='"+Integer.parseInt(va)+"'";
                                stmt.executeUpdate(update);
                                showusertable();
                                JOptionPane.showMessageDialog(null,"row updated");


                                          }
                               }catch(Exception ex){
                                   JOptionPane.showMessageDialog(null,ex);
                               }
                            }
    }
    private void delete(){
          if(jTabbedPane1.getSelectedIndex()==0){
             try{
            int ro=jtablegroups.getSelectedRow();
                        String va=jtablegroups.getModel().getValueAt(ro,3).toString();
                        int confirmdelete=JOptionPane.showConfirmDialog(null,"Do you really want to delete this row","Delete",JOptionPane.YES_NO_OPTION);
                        if(confirmdelete==0){
                            String sql="Delete from groups where Rec_Id='"+Integer.parseInt(va)+"'";
                            pst=conn.prepareStatement(sql);
                            pst.execute();
                        }
                        showgroups();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }
        }
        else if(jTabbedPane1.getSelectedIndex()==1){
             try{
            int ro=jmembership.getSelectedRow();
            String va=jmembership.getModel().getValueAt(ro, 5).toString();
            int confirmdelete=JOptionPane.showConfirmDialog(null,"Doyou really want to delete this row?","Delete",JOptionPane.YES_NO_OPTION);
            if(confirmdelete==0){
             String delete="delete from memberships where Rec_Id='"+Integer.parseInt(va)+"'";
            pst=conn.prepareStatement(delete);
            pst.execute();
            }
          showmembership();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }
        }
        else if(jTabbedPane1.getSelectedIndex()==2){
               try{
          int ro=jcontributions.getSelectedRow();
        String va=jcontributions.getModel().getValueAt(ro,5).toString();
        int confirmdelete=JOptionPane.showConfirmDialog(null, "Do you really want to delete this row?","Delete",JOptionPane.YES_NO_OPTION);
    if(confirmdelete==0){
        String delete="delete from contributions where Rec_Id='"+Integer.parseInt(va)+"'";
            pst=conn.prepareStatement(delete);
            pst.execute();
            showContributions();
    }
          }catch(Exception ex){
              JOptionPane.showMessageDialog(null,ex);
          }
        }
        else if(jTabbedPane1.getSelectedIndex()==3){
            try{
            int ro=jTableuserregistration.getSelectedRow();
            String va=jTableuserregistration.getModel().getValueAt(ro,6).toString();
              int confirmdelete=JOptionPane.showConfirmDialog(null, "Do you really want to delete this row?","Delete",JOptionPane.YES_NO_OPTION);
          if(confirmdelete==0){
          String delete="delete from user_registration where Rec_Id='"+Integer.parseInt(va)+"'";
          pst=conn.prepareStatement(delete);
           pst.execute();
           showusertable();
          }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        } 
        }
    }
    private void setenabled(){
         if(jTabbedPane1.getSelectedIndex()==0){
              try{
             txtgroupname.setEnabled(true);
             txtlocation.setEnabled(true);
             jdateChoosergroups.setEnabled(true);
         }catch(Exception ex){
             JOptionPane.showMessageDialog(null,ex);
         }
        }
        else if(jTabbedPane1.getSelectedIndex()==1){
            try{
            cmbgroupid.setEnabled(true);
            txtname.setEnabled(true);
            txtidno.setEnabled(true);
            txttelno.setEnabled(true);
            jDateChoosermembership.setEnabled(true);
            
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }
        
        }
        else if(jTabbedPane1.getSelectedIndex()==2){
            try{
              cmbmember.setEnabled(true);
               cmbtype.setEnabled(true);
              txtdescription.setEnabled(true);
              txtamount.setEnabled(true);
          jDateChoosercontributions.setEnabled(true);
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,ex);
            }
        }
          else if(jTabbedPane1.getSelectedIndex()==3){
            try{
              txtname1.setEnabled(true);
               txtusername.setEnabled(true);
              cmbprofile.setEnabled(true);
              txtamount.setEnabled(true);
            jDateChooseruserregistration.setEnabled(true);
              jPasswordField.setEnabled(true);
              jPasswordFieldconfirm.setEnabled(true);
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,ex);
            }
        }
        
    }
    private void disabled(){
       if(jTabbedPane1.getSelectedIndex()==0){
              try{
             txtgroupname.setEnabled(false);
             txtlocation.setEnabled(false);
             jdateChoosergroups.setEnabled(false);
         }catch(Exception ex){
             JOptionPane.showMessageDialog(null,ex);
         }
        }
        else if(jTabbedPane1.getSelectedIndex()==1){
            try{
            cmbgroupid.setEnabled(false);
            txtname.setEnabled(false);
            txtidno.setEnabled(false);
            txttelno.setEnabled(false);
            jDateChoosermembership.setEnabled(false);
            
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }
        
        }
        else if(jTabbedPane1.getSelectedIndex()==2){
            try{
              cmbmember.setEnabled(false);
               cmbtype.setEnabled(false);
              txtdescription.setEnabled(false);
              txtamount.setEnabled(false);
          jDateChoosercontributions.setEnabled(false);
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,ex);
            }
        }
          else if(jTabbedPane1.getSelectedIndex()==3){
            try{
              txtname1.setEnabled(false);
               txtusername.setEnabled(false);
              cmbprofile.setEnabled(false);
              txtamount.setEnabled(false);
            jDateChooseruserregistration.setEnabled(false);
              jPasswordField.setEnabled(false);
              jPasswordFieldconfirm.setEnabled(false);
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,ex);
            }
        }
        
    }
    private void reset(){
            if(jTabbedPane1.getSelectedIndex()==0){
          try{
                txtgroupname.setText(null);
                txtlocation.setText(null);
                jdateChoosergroups.setDate(null);
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,ex);
            }
        }
        else if(jTabbedPane1.getSelectedIndex()==1){
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
        else if(jTabbedPane1.getSelectedIndex()==2){
             try{
            cmbmember.setSelectedIndex(0);
            cmbtype.setSelectedIndex(0);
            txtdescription.setText("");
            txtamount.setText("");
            jDateChoosercontributions.setDate(null);
            showContributions();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }
        }
        else if (jTabbedPane1.getSelectedIndex()==3){
               try{
            txtname1.setText(null);
            txtusername.setText(null);
            cmbprofile.setSelectedIndex(0);
            jDateChooseruserregistration.setDate(null);
            jPasswordField.setText(null);
            jPasswordFieldconfirm.setText(null);
            
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }
        }
    }
    private void mouseclickedgroups(){
        act="JHgf";
        int ro=jtablegroups.getSelectedRow();
        String va=(jtablegroups.getModel().getValueAt(ro,3).toString());
        String sql="select*from groups where Rec_Id='"+Integer.parseInt(va)+"'";
        try{
            pst=conn.prepareStatement(sql);
            rs=pst.executeQuery();
            if(rs.next()){
                String gro=rs.getString("Group_Name");
                txtgroupname.setText(gro);
                String loca=rs.getString("Location");
                txtlocation.setText(loca); 
                Date d=rs.getDate("Date");
                jdateChoosergroups.setDate(d);
                disabled();
            } 
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }
    }
    private void mouseclickedmembership(){
         act="JHgf";
        int ro=jmembership.getSelectedRow();
        String va=jmembership.getModel().getValueAt(ro, 5).toString();
        try{
 String sql="  select (CONCAT(groups.Group_Name,', ',groups.Rec_Id))ff,membership.Name,membership.Id_No,membership.Tel_No,membership.Date,membership.Rec_Id FROM groups,membership WHERE groups.Rec_Id=membership.Group_Id AND membership.Rec_Id='"+Integer.parseInt(va)+"'";   
   pst=conn.prepareStatement(sql);
   rs=pst.executeQuery();
   while(rs.next()){
  String   ff=rs.getString("ff");
      cmbgroupid.setSelectedItem(ff);
      name=rs.getString("Name");
      txtname.setText(name);
      idno=rs.getString("Id_No");
      txtidno.setText(idno);
      telno=rs.getString("Tel_No");
      txttelno.setText(telno);              
      Date da=rs.getDate("Date");
      jDateChoosermembership.setDate(da);
      disabled();
       
   }
            
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }
    }
    private void mouseclickedcontributions(){
         act="JHgf";
        try{
             int ro=jcontributions.getSelectedRow();
        String va=jcontributions.getModel().getValueAt(ro, 5).toString();
 String sql="SELECT (CONCAT(membership.Name,', ',membership.Rec_Id))name,contributions.Type,contributions.Description,contributions.Amount,contributions.Date,contributions.Rec_Id FROM membership,contributions WHERE membership.Rec_Id=contributions.Member_Id AND contributions.Rec_Id='"+Integer.parseInt(va)+"' ";
        pst=conn.prepareStatement(sql);
   rs=pst.executeQuery();
   while(rs.next()){
      ff=rs.getString("name");
      cmbmember.setSelectedItem(ff);
      type=rs.getString("Type");
      cmbtype.setSelectedItem(type);
      description=rs.getString("Description");
      txtdescription.setText(description);
      amount=rs.getString("Amount");
      txtamount.setText(amount);              
      Date da=rs.getDate("Date");
      jDateChoosercontributions.setDate(da);
       
   }
            
       disabled();
            
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }
    }
    private void mouseclickeduserregistration(){
           act="JHgf";
        try{
            int ro=jTableuserregistration.getSelectedRow();
            String va=jTableuserregistration.getModel().getValueAt(ro, 6).toString();
            String sql="select*from user_registration where Rec_Id='"+Integer.parseInt(va)+"'";
            pst=conn.prepareStatement(sql);
            rs=pst.executeQuery();
            if(rs.next()){
                String Name=rs.getString("Name");
                txtname1.setText(Name);
                String Username=rs.getString("Username");
                txtusername.setText(Username);
                String profile=rs.getString("Profile");
                  cmbprofile.setSelectedItem(profile);
                Date d=rs.getDate("Date");
                jDateChooseruserregistration.setDate(d);
                String p=rs.getString("Password");
                jPasswordField.setText(p);
                String cp=rs.getString("Confirm_password");
                jPasswordFieldconfirm.setText(p);
                pst.execute();
                disabled();
                
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }
    
    }
    private void refresh(){
        try{
       String sql="SELECT (CONCAT(groups.Group_Name, ' ,' ,groups.Rec_Id))groupname,membership.Name,membership.Id_No,membership.Tel_No,membership.Date,membership.Rec_Id FROM groups,membership WHERE groups.Rec_Id=membership.Group_Id ORDER BY Rec_Id ";
     pst=conn.prepareStatement(sql);
     rs=pst.executeQuery();
     jmembership.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }
        cmbgroup();
        cmbmember();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtablegroups = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        txtsearchgroups = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jdateChoosergroups = new com.toedter.calendar.JDateChooser();
        txtlocation = new javax.swing.JTextField();
        txtgroupname = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jmembership = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        txtsearchmembership = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        cmbgroupid = new javax.swing.JComboBox();
        jDateChoosermembership = new com.toedter.calendar.JDateChooser();
        txttelno = new javax.swing.JTextField();
        txtidno = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtname = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jcontributions = new javax.swing.JTable();
        txtsearchcontributions = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jPanel11 = new javax.swing.JPanel();
        jDateChoosercontributions = new com.toedter.calendar.JDateChooser();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtamount = new javax.swing.JTextField();
        cmbtype = new javax.swing.JComboBox();
        txtdescription = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        cmbmember = new javax.swing.JComboBox();
        jPanel4 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTableuserregistration = new javax.swing.JTable();
        txtsearchuserregistration = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jDateChooseruserregistration = new com.toedter.calendar.JDateChooser();
        cmbprofile = new javax.swing.JComboBox<String>();
        jPasswordFieldconfirm = new javax.swing.JPasswordField();
        jLabel16 = new javax.swing.JLabel();
        jPasswordField = new javax.swing.JPasswordField();
        txtusername = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txtname1 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        checkbox = new javax.swing.JCheckBox();
        jPanel13 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jtime = new javax.swing.JMenu();
        jdate = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel5.setBackground(new java.awt.Color(204, 0, 255));

        jtablegroups.setModel(new javax.swing.table.DefaultTableModel(
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
        jtablegroups.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtablegroupsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtablegroups);

        jLabel6.setText("filter/search");

        txtsearchgroups.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtsearchgroupsKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(35, 35, 35)
                        .addComponent(txtsearchgroups, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtsearchgroups, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel9.setBackground(new java.awt.Color(255, 204, 153));

        jdateChoosergroups.setDateFormatString("yyyy-MM-dd");

        jLabel3.setText("Date");

        jLabel1.setText("Group name");

        jLabel2.setText("Location");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel9Layout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addComponent(jLabel3))
                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtgroupname)
                    .addComponent(txtlocation)
                    .addComponent(jdateChoosergroups, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtgroupname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtlocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jdateChoosergroups, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(10, 10, 10)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("groups", jPanel1);

        jPanel6.setBackground(new java.awt.Color(255, 255, 0));

        jmembership.setModel(new javax.swing.table.DefaultTableModel(
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
        jmembership.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jmembershipMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jmembership);

        jLabel7.setText("filter/search");

        txtsearchmembership.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtsearchmembershipKeyReleased(evt);
            }
        });

        jButton5.setText("Refersh");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel7)
                        .addGap(32, 32, 32)
                        .addComponent(txtsearchmembership, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtsearchmembership, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jButton5)
                        .addGap(121, 121, 121))))
        );

        jPanel10.setBackground(new java.awt.Color(153, 102, 255));

        jLabel5.setText("Date");

        jLabel21.setText("Name");

        jDateChoosermembership.setDateFormatString("yyyy-MM-dd");

        jLabel4.setText("Tel no");

        jLabel20.setText("Group");

        jLabel22.setText("Id no");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addGap(38, 38, 38)
                        .addComponent(cmbgroupid, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addGap(40, 40, 40)
                        .addComponent(txtname, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addGap(42, 42, 42)
                        .addComponent(txtidno, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(38, 38, 38)
                        .addComponent(txttelno, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel5)
                        .addGap(38, 38, 38)
                        .addComponent(jDateChoosermembership, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel20))
                    .addComponent(cmbgroupid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel21))
                    .addComponent(txtname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel22))
                    .addComponent(txtidno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel4))
                    .addComponent(txttelno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel5))
                    .addComponent(jDateChoosermembership, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 54, Short.MAX_VALUE))
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 289, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("membership", jPanel2);

        jPanel7.setBackground(new java.awt.Color(255, 255, 204));

        jcontributions.setModel(new javax.swing.table.DefaultTableModel(
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
        jcontributions.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jcontributionsMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jcontributions);

        txtsearchcontributions.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtsearchcontributionsKeyReleased(evt);
            }
        });

        jLabel8.setText("filter/search");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(txtsearchcontributions, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtsearchcontributions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel11.setBackground(new java.awt.Color(0, 153, 153));

        jDateChoosercontributions.setDateFormatString("yyyy-MM-dd");

        jLabel10.setText("Type");

        jLabel12.setText("Amount");

        jLabel9.setText("Member");

        jLabel13.setText("Date");

        cmbtype.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Registration fee", "Monthly contribution", "Penalty fee" }));
        cmbtype.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbtypeActionPerformed(evt);
            }
        });

        jLabel11.setText("Description");

        cmbmember.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbmemberActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel11)
                                .addComponent(jLabel9)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmbmember, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtdescription)
                            .addComponent(cmbtype, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDateChoosercontributions, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtamount, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(cmbmember, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(cmbtype, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtdescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtamount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDateChoosercontributions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jLayeredPane1.setLayer(jPanel11, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(72, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("contributions", jPanel3);

        jPanel8.setBackground(new java.awt.Color(0, 153, 51));

        jTableuserregistration.setModel(new javax.swing.table.DefaultTableModel(
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
        jTableuserregistration.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableuserregistrationMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTableuserregistration);

        txtsearchuserregistration.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtsearchuserregistrationKeyReleased(evt);
            }
        });

        jLabel23.setText("filter/search");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addGap(34, 34, 34)
                        .addComponent(txtsearchuserregistration, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtsearchuserregistration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel12.setBackground(new java.awt.Color(153, 204, 255));

        jDateChooseruserregistration.setDateFormatString("yyyy-MM-dd");

        cmbprofile.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Admin", "Super admin", "User" }));

        jPasswordFieldconfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordFieldconfirmActionPerformed(evt);
            }
        });

        jLabel16.setText("Profile");

        jLabel19.setText("Confirm password");

        jLabel17.setText("Date");

        jLabel18.setText("Password");

        jLabel15.setText("Username");

        jLabel14.setText("Name");

        checkbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkboxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addComponent(jLabel19)
                    .addComponent(jLabel17)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jPasswordField)
                                .addComponent(jPasswordFieldconfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtname1)
                                    .addComponent(txtusername, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(cmbprofile, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(checkbox, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jDateChooseruserregistration, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel14)
                    .addComponent(txtname1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtusername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbprofile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16))
                        .addGap(18, 18, 18)
                        .addComponent(jDateChooseruserregistration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(checkbox))
                        .addGap(36, 36, 36))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel18)
                        .addGap(24, 24, 24)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(jPasswordFieldconfirm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("user registration", jPanel4);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edit-validated-icon (1).png"))); // NOI18N
        jButton2.setText("Edit");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Reset-icon.png"))); // NOI18N
        jButton3.setText("Reset");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Delete-2-icon.png"))); // NOI18N
        jButton4.setText("Delete");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Save-icon.png"))); // NOI18N
        jButton1.setText("Save");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 290, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(27, 27, 27)
                .addComponent(jButton4)
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton4)
                        .addComponent(jButton2))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1)
                        .addComponent(jButton3)))
                .addContainerGap())
        );

        jMenu1.setText("groups");
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu1MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu1);

        jMenu2.setText("membership");
        jMenu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu2MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu2);

        jMenu3.setText("contributions");
        jMenu3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu3MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu3);

        jMenu4.setText("user registarion");
        jMenu4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu4MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu4);

        jtime.setText("Time");
        jMenuBar1.add(jtime);

        jdate.setText("Date");
        jMenuBar1.add(jdate);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(111, 111, 111))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jTabbedPane1)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 355, Short.MAX_VALUE)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(74, Short.MAX_VALUE)))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jtablegroupsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtablegroupsMouseClicked
        // TODO add your handling code here:
        mouseclickedgroups();
    }//GEN-LAST:event_jtablegroupsMouseClicked

    private void txtsearchgroupsKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsearchgroupsKeyReleased
        // TODO add your handling code here:
         DefaultTableModel table=(DefaultTableModel)jtablegroups.getModel();
       String search=txtsearchgroups.getText();
        TableRowSorter<DefaultTableModel>tr= new TableRowSorter<DefaultTableModel>(table);
        jtablegroups.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(search));

         
       
    }//GEN-LAST:event_txtsearchgroupsKeyReleased

    private void jmembershipMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jmembershipMouseClicked
        // TODO add your handling code here:
        mouseclickedmembership();
    }//GEN-LAST:event_jmembershipMouseClicked

    private void txtsearchmembershipKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsearchmembershipKeyReleased
        // TODO add your handling code here:
         DefaultTableModel table=(DefaultTableModel)jmembership.getModel();
        String search=txtsearchmembership.getText();
        TableRowSorter<DefaultTableModel>tr= new TableRowSorter<DefaultTableModel>(table);
        jmembership.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(search));

    }//GEN-LAST:event_txtsearchmembershipKeyReleased

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        refresh();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jcontributionsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jcontributionsMouseClicked
        // TODO add your handling code here:
        mouseclickedcontributions();
    }//GEN-LAST:event_jcontributionsMouseClicked

    private void txtsearchcontributionsKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsearchcontributionsKeyReleased
        // TODO add your handling code here:
                 DefaultTableModel table=(DefaultTableModel)jcontributions.getModel();
        String search=txtsearchcontributions.getText();
        TableRowSorter<DefaultTableModel>tr= new TableRowSorter<DefaultTableModel>(table);
        jcontributions.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(search));
    }//GEN-LAST:event_txtsearchcontributionsKeyReleased

    private void cmbtypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbtypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbtypeActionPerformed

    private void cmbmemberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbmemberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbmemberActionPerformed

    private void jTableuserregistrationMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableuserregistrationMouseClicked
        // TODO add your handling code here:
        mouseclickeduserregistration();
    }//GEN-LAST:event_jTableuserregistrationMouseClicked

    private void txtsearchuserregistrationKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsearchuserregistrationKeyReleased
        // TODO add your handling code here:
         DefaultTableModel table=(DefaultTableModel)jTableuserregistration.getModel();
       String search=txtsearchuserregistration.getText();
        TableRowSorter<DefaultTableModel>tr= new TableRowSorter<DefaultTableModel>(table);
        jTableuserregistration.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(search));
    }//GEN-LAST:event_txtsearchuserregistrationKeyReleased

    private void jPasswordFieldconfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordFieldconfirmActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordFieldconfirmActionPerformed

    private void checkboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkboxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkboxActionPerformed

    private void jMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu1MouseClicked

    private void jMenu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu2MouseClicked

    private void jMenu3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu3MouseClicked

    private void jMenu4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu4MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu4MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        reset();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        save();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        delete();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        setenabled();
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
            java.util.logging.Logger.getLogger(organizationnn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(organizationnn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(organizationnn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(organizationnn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new organizationnn().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox checkbox;
    private javax.swing.JComboBox cmbgroupid;
    private javax.swing.JComboBox cmbmember;
    private javax.swing.JComboBox<String> cmbprofile;
    private javax.swing.JComboBox cmbtype;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private com.toedter.calendar.JDateChooser jDateChoosercontributions;
    private com.toedter.calendar.JDateChooser jDateChoosermembership;
    private com.toedter.calendar.JDateChooser jDateChooseruserregistration;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPasswordField jPasswordField;
    private javax.swing.JPasswordField jPasswordFieldconfirm;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTableuserregistration;
    private javax.swing.JTable jcontributions;
    private javax.swing.JMenu jdate;
    private com.toedter.calendar.JDateChooser jdateChoosergroups;
    private javax.swing.JTable jmembership;
    private javax.swing.JTable jtablegroups;
    private javax.swing.JMenu jtime;
    private javax.swing.JTextField txtamount;
    private javax.swing.JTextField txtdescription;
    private javax.swing.JTextField txtgroupname;
    private javax.swing.JTextField txtidno;
    private javax.swing.JTextField txtlocation;
    private javax.swing.JTextField txtname;
    private javax.swing.JTextField txtname1;
    private javax.swing.JTextField txtsearchcontributions;
    private javax.swing.JTextField txtsearchgroups;
    private javax.swing.JTextField txtsearchmembership;
    private javax.swing.JTextField txtsearchuserregistration;
    private javax.swing.JTextField txttelno;
    private javax.swing.JTextField txtusername;
    // End of variables declaration//GEN-END:variables
}
