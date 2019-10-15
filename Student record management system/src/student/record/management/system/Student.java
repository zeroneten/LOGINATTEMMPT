/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student.record.management.system;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.print.PrinterException;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import net.proteanit.sql.DbUtils;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author ndirituedwin
 */
public class Student extends javax.swing.JFrame {

    private ImageIcon format = null;
    byte[] person_image = null;
    int s = 0;
    Connection conn;
    PreparedStatement pst;
    String gender;
    ResultSet rs;
    String firstname, surname, coursecode, Dob, adres, math, english, BIO, comp, chem, physics, busine, total, average;

    /**
     * Creates new form Student
     */
    public Student() {
        initComponents();
        conn = dbconnectionclass.dbconnection();
        Date d = new Date();
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        jDateChooserstudents.setDate(d);
        showstudents();
        txtfirstname.requestFocus();

    }

    private void showstudents() {
        try {
            String sql = "select*from Students";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            jTable1.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    private void enter() {
        try {
            String insert = "INSERT INTO `Students`(`Firstname`, `Surname`, `Coursecode`, `Gender`, `DoB`, `Address`,`Image`,`Maths`, `English`, `Biology`, `Computing`, `Chemistry`, `Physics`, `Business`, `Total_score`, `Average`,`Grade`, `Ranking`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            pst = conn.prepareStatement(insert);
            pst.setString(1, txtfirstname.getText());
            pst.setString(2, txtsurname.getText());
            pst.setString(3, cmbcoursecode.getSelectedItem().toString());
            pst.setString(4, gender);
            pst.setString(5, ((JTextField) jDateChooserstudents.getDateEditor().getUiComponent()).getText());

//             pst.setString(6,txtdob.getText());
            pst.setString(6, txtadress.getText());
            pst.setBytes(7, person_image);

            pst.setString(8, txtmath.getText());
            pst.setString(9, txtenglish.getText());
            pst.setString(10, txtbiology.getText());
            pst.setString(11, txtcomputing.getText());
            pst.setString(12, txtchemistry.getText());
            pst.setString(13, txtphysics.getText());
            pst.setString(14, txtbusiness.getText());
            pst.setString(15, txtscore.getText());
            pst.setString(16, txtaverage.getText());
            pst.setString(17, txtgrade.getText());
            pst.setString(18, txtranking.getText());
            pst.execute();
            showstudents();
            JOptionPane.showMessageDialog(null, "record inserted");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,"you must fill the fields");
        }
    }

    private void delete() {
        int row = jTable1.getSelectedRow();
        String ro = jTable1.getModel().getValueAt(row, 0).toString();
        int confirmdelete = JOptionPane.showConfirmDialog(null, "Do you really want to delete this row?", "Delete", JOptionPane.YES_NO_OPTION);
        if (confirmdelete == 0) {
            String delete = "delete from Students where Student_Id='" + Integer.parseInt(ro) + "'";
            try {
                pst = conn.prepareStatement(delete);
                pst.execute();
                showstudents();
//                  JOptionPane.showMessageDialog(null,"deleted successfully");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
    }

    private void mouseclicked() {
        int ro = jTable1.getSelectedRow();
//        String va=jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString();
        String va = (jTable1.getModel().getValueAt(ro, 0)).toString();
        try {
            // TODO add your handling code here:
            String sql = "select Image from students where Student_Id='" + va + "'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                byte[] imagedata = rs.getBytes("Image");
                format = new ImageIcon(imagedata);
                jimageicon.setIcon(format);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            String sql = "select*from Students where Student_Id='" + Integer.parseInt(va) + "'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                String v1 = rs.getString("Student_Id");
                txtstudentid.setText(v1);
                String v2 = rs.getString("Firstname");
                txtfirstname.setText(v2);
                String v3 = rs.getString("Surname");
                txtsurname.setText(v3);
                String v4 = rs.getString("Coursecode");
                cmbcoursecode.setSelectedItem(v4);
                String sex = rs.getString("Gender");
                if (sex.equals("male")) {
                    jmale.setSelected(true);
                } else {
                    jfemale.setSelected(true);
                }

                Date d = rs.getDate("DoB");
                jDateChooserstudents.setDate(d);
                String v5 = rs.getString("Address");
                txtadress.setText(v5);
                String v6 = rs.getString("Maths");
                txtmath.setText(v6);
                String v7 = rs.getString("English");
                txtenglish.setText(v7);
                String v8 = rs.getString("Biology");
                txtbiology.setText(v8);
                String v9 = rs.getString("Computing");
                txtcomputing.setText(v9);
                String v10 = rs.getString("Chemistry");
                txtchemistry.setText(v10);
                String v11 = rs.getString("Physics");
                txtphysics.setText(v11);
                String v12 = rs.getString("Business");
                txtbusiness.setText(v12);
                String v13 = rs.getString("Total_score");
                txtscore.setText(v13);
                String v14 = rs.getString("Average");
                txtaverage.setText(v14);
                String v15 = rs.getString("Grade");
                txtgrade.setText(v15);
                String v16 = rs.getString("Ranking");
                txtranking.setText(v16);
                pst.execute();

            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

    }

    private void reset() {
        txtstudentid.setText(null);
        txtfirstname.setText(null);
        txtsurname.setText(null);
        cmbcoursecode.setSelectedIndex(0);
        jDateChooserstudents.setDate(null);
        jimageicon.setIcon(null);
        txtadress.setText(null);
        buttonGroup1.clearSelection();
        txtmath.setText(null);
        txtenglish.setText(null);
        txtbiology.setText(null);
        txtcomputing.setText(null);
        txtchemistry.setText(null);
        txtphysics.setText(null);
        txtbusiness.setText(null);
        txtscore.setText(null);
        txtaverage.setText(null);
        txtgrade.setText(null);
        jtranscript.setText(null);
        txtranking.setText(null);

    }

    private void update() {
        int ro = jTable1.getSelectedRow();
        String va = jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString();
        try {
            String v1 = txtfirstname.getText();
            String v2 = txtsurname.getText();
            String v3 = cmbcoursecode.getSelectedItem().toString();
//         pst.setString(1, gender);
            String d = ((JTextField) jDateChooserstudents.getDateEditor().getUiComponent()).getText();
            String v4 = txtadress.getText();
            String v5 = txtmath.getText();
            String v6 = txtenglish.getText();
            String v7 = txtbiology.getText();
            String v8 = txtcomputing.getText();
            String v9 = txtchemistry.getText();
            String v10 = txtphysics.getText();
            String v11 = txtbusiness.getText();
            String v12 = txtscore.getText();
            String v13 = txtaverage.getText();
            String v14 = txtgrade.getText();
            String v15 = txtranking.getText();
            String sql = "update Students set Firstname='" + v1 + "',Surname='" + v2 + "',Coursecode='" + v3 + "',DoB='" + d + "',Address='" + v4 + "',Maths='" + v5 + "',English='" + v6 + "',Biology='" + v7 + "',Computing='" + v8 + "',Chemistry='" + v9 + "',Physics='" + v10 + "',Business='" + v11 + "',Total_score='" + v12 + "',Average='" + v13 + "',Grade='" + v14 + "',Ranking='" + v15 + "' where Student_Id='" + Integer.parseInt(va) + "'";
            pst = conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "row updated");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    private void jtextareatranscript() {
        try {

            jtranscript.append("Students Results recording system\n"
                    + "==============================\n"
                    + "Student_Id\t\t" + txtstudentid.getText()
                    + "\nStudent_Id\t\t" + txtstudentid.getText()
                    + "\nFirstname\t\t" + txtfirstname.getText()
                    + "\nSurname\t\t" + txtsurname.getText()
                    + "\nCoursecode\t\t" + cmbcoursecode.getSelectedItem().toString()
                    + "\nDoB\t\t" + ((JTextField) jDateChooserstudents.getDateEditor().getUiComponent()).getText()
                    + "\nAddress\t\t" + txtadress.getText()
                    + "\n===========================\n"
                    + "\nMaths\t\t" + txtmath.getText()
                    + "\nEnglish\t\t" + txtenglish.getText()
                    + "\nBiology\t\t" + txtbiology.getText()
                    + "\nComputing\t\t" + txtcomputing.getText()
                    + "\nChemistry\t\t" + txtchemistry.getText()
                    + "\nPhysics\t\t" + txtphysics.getText()
                    + "\nBusiness\t\t" + txtbusiness.getText()
                    + "\n=================================\n"
                    + "\nTotal_score\t\t" + txtscore.getText()
                    + "\nAverage\t\t" + txtaverage.getText()
                    + "\nRanking\t\t" + txtgrade.getText()
            );

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    private void piechart() {
        try {
            DefaultPieDataset pieDataset = new DefaultPieDataset();
            pieDataset.setValue("math", new Integer(txtmath.getText()));
            pieDataset.setValue("english", new Integer(txtenglish.getText()));
            pieDataset.setValue("biology", new Integer(txtbiology.getText()));
            pieDataset.setValue("chemistry", new Integer(txtchemistry.getText()));
            pieDataset.setValue("computing", new Integer(txtcomputing.getText()));
            pieDataset.setValue("business", new Integer(txtbusiness.getText()));
            pieDataset.setValue("physics", new Integer(txtphysics.getText()));
            JFreeChart chart = ChartFactory.createPieChart3D("piechart", pieDataset, true, true, true);
            PiePlot3D p = (PiePlot3D) chart.getPlot();
//             p.setForegroundAlpha(TOP_ALIGNMENT);
            ChartFrame frame = new ChartFrame("pie chart", chart);
            frame.setVisible(true);
            frame.setSize(1000, 500);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    private void barchart() {
        try {
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            dataset.setValue(new Integer(txtmath.getText()), "marks", "maths");
            dataset.setValue(new Integer(txtenglish.getText()), "marks", "English");
            dataset.setValue(new Integer(txtbiology.getText()), "marks", "biology");
            dataset.setValue(new Integer(txtchemistry.getText()), "marks", "chemistry");
            dataset.setValue(new Integer(txtcomputing.getText()), "marks", "computing");
            dataset.setValue(new Integer(txtbusiness.getText()), "marks", "business");
            dataset.setValue(new Integer(txtphysics.getText()), "marks", "physics");
            JFreeChart chart = ChartFactory.createBarChart3D("Students score", "name", "marks", dataset, PlotOrientation.VERTICAL, false, true, false);
            chart.setBackgroundPaint(Color.yellow);
            chart.getTitle().setPaint(Color.red);
            CategoryPlot p = chart.getCategoryPlot();
            p.setRangeGridlinePaint(Color.blue);
            ChartFrame frame = new ChartFrame("students bar chart", chart);
            frame.setVisible(true);
            frame.setSize(450, 350);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

    }

    private void linegraph() {
        try {
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            dataset.setValue(new Integer(txtmath.getText()), "marks", "maths");
            dataset.setValue(new Integer(txtenglish.getText()), "marks", "English");
            dataset.setValue(new Integer(txtbiology.getText()), "marks", "biology");
            dataset.setValue(new Integer(txtchemistry.getText()), "marks", "chemistry");
            dataset.setValue(new Integer(txtcomputing.getText()), "marks", "computing");
            dataset.setValue(new Integer(txtbusiness.getText()), "marks", "business");
            dataset.setValue(new Integer(txtphysics.getText()), "marks", "physics");
            JFreeChart chart = ChartFactory.createLineChart3D("Students score", "name", "marks", dataset, PlotOrientation.VERTICAL, false, true, false);
            chart.setBackgroundPaint(Color.yellow);
            chart.getTitle().setPaint(Color.red);
            CategoryPlot p = chart.getCategoryPlot();
            p.setRangeGridlinePaint(Color.blue);
            ChartFrame frame = new ChartFrame("students bar chart", chart);
            frame.setVisible(true);
            frame.setSize(450, 350);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        txtcomputing = new javax.swing.JTextField();
        jfemale = new javax.swing.JRadioButton();
        txtbiology = new javax.swing.JTextField();
        txtenglish = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtstudentid = new javax.swing.JLabel();
        cmbcoursecode = new javax.swing.JComboBox();
        txtchemistry = new javax.swing.JTextField();
        txtphysics = new javax.swing.JTextField();
        txtbusiness = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtmath = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtadress = new javax.swing.JTextField();
        jmale = new javax.swing.JRadioButton();
        jDateChooserstudents = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        txtfirstname = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtsurname = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jPanel5 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        txtaverage = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        txtscore = new javax.swing.JTextField();
        txtgrade = new javax.swing.JTextField();
        jButton10 = new javax.swing.JButton();
        txtranking = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jtranscript = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        txtranscript = new javax.swing.JButton();
        txtprint = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jimageicon = new javax.swing.JLabel();
        path = new javax.swing.JTextField();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jButton9 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 153, 255));

        txtcomputing.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtcomputingKeyPressed(evt);
            }
        });

        buttonGroup1.add(jfemale);
        jfemale.setText("female");
        jfemale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jfemaleActionPerformed(evt);
            }
        });

        txtbiology.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtbiologyKeyPressed(evt);
            }
        });

        txtenglish.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtenglishKeyPressed(evt);
            }
        });

        jLabel14.setText("Business");

        jLabel8.setText("English");

        jLabel13.setText("Physics");

        txtstudentid.setText("jLabel9");

        cmbcoursecode.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "select course code", "01247", "012455", "120145", "478945", "120214", "457891", "555555", " " }));

        txtchemistry.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtchemistryKeyPressed(evt);
            }
        });

        txtphysics.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtphysicsKeyPressed(evt);
            }
        });

        jLabel1.setText("Studentid");

        jLabel4.setText("Surname");

        txtmath.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtmathActionPerformed(evt);
            }
        });
        txtmath.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtmathKeyPressed(evt);
            }
        });

        jLabel12.setText("Chemistry");

        jLabel18.setText("Maths");

        jLabel11.setText("Computing");

        jLabel5.setText("Gender");

        jLabel3.setText("Coursecode");

        txtadress.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtadressKeyPressed(evt);
            }
        });

        buttonGroup1.add(jmale);
        jmale.setText("male");
        jmale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmaleActionPerformed(evt);
            }
        });

        jDateChooserstudents.setDateFormatString("yyyy-MM-dd");

        jLabel7.setText("Address");

        txtfirstname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtfirstnameActionPerformed(evt);
            }
        });
        txtfirstname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtfirstnameKeyPressed(evt);
            }
        });

        jLabel10.setText("Biology");

        txtsurname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtsurnameActionPerformed(evt);
            }
        });
        txtsurname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtsurnameKeyPressed(evt);
            }
        });

        jLabel2.setText("Firstname");

        jLabel6.setText("DoB");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                            .addComponent(jLabel4)
                                            .addGap(204, 204, 204))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                            .addComponent(jLabel1)
                                            .addGap(14, 14, 14)
                                            .addComponent(txtstudentid, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                            .addComponent(jLabel2)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(txtfirstname, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(36, 36, 36)))
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                            .addComponent(jLabel10)
                                            .addGap(18, 18, 18)
                                            .addComponent(txtbiology, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                            .addComponent(jLabel8)
                                            .addGap(18, 18, 18)
                                            .addComponent(txtenglish, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                            .addGap(3, 3, 3)
                                            .addComponent(jLabel18)
                                            .addGap(18, 18, 18)
                                            .addComponent(txtmath, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addGap(13, 13, 13)
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                            .addComponent(cmbcoursecode, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(21, 21, 21)
                                            .addComponent(jLabel11)
                                            .addGap(18, 18, 18)
                                            .addComponent(txtcomputing, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(txtsurname, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addGap(18, 18, 18)
                                .addComponent(txtbusiness, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGap(12, 12, 12)
                                        .addComponent(jLabel5)
                                        .addGap(25, 25, 25))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                                .addComponent(jLabel7)
                                                .addGap(23, 23, 23)))))
                                .addGap(10, 10, 10)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jmale)
                                        .addGap(3, 3, 3)
                                        .addComponent(jfemale, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel12))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jDateChooserstudents, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                                            .addComponent(txtadress))
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel13)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtchemistry, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtphysics, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 36, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtmath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtenglish, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtbiology, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txtcomputing, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(txtchemistry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtphysics, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addGap(17, 17, 17)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(txtbusiness, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(txtstudentid))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtfirstname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtsurname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(cmbcoursecode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jmale)
                            .addComponent(jfemale)
                            .addComponent(jLabel5))
                        .addGap(7, 7, 7)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jDateChooserstudents, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtadress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane2.setViewportView(jPanel4);

        jButton7.setText("Average");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setText("Grade");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton6.setText("TotalScore");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        txtgrade.setEnabled(false);
        txtgrade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtgradeActionPerformed(evt);
            }
        });

        jButton10.setText("Ranking");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        txtranking.setEnabled(false);
        txtranking.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtrankingActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                        .addComponent(txtaverage, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jButton6)
                                .addGap(17, 17, 17)
                                .addComponent(txtscore, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jButton8)
                                .addGap(39, 39, 39)
                                .addComponent(txtgrade, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jButton10)
                        .addGap(33, 33, 33)
                        .addComponent(txtranking)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton6)
                    .addComponent(txtscore, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton7)
                    .addComponent(txtaverage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jButton8))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(txtgrade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton10)
                    .addComponent(txtranking, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(88, Short.MAX_VALUE))
        );

        jScrollPane5.setViewportView(jPanel5);

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Transcript", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 2, 24), new java.awt.Color(102, 0, 102))); // NOI18N

        jtranscript.setColumns(20);
        jtranscript.setRows(5);
        jScrollPane4.setViewportView(jtranscript);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 170, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE))
        );

        jScrollPane6.setViewportView(jPanel6);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
        );

        jPanel3.setBackground(new java.awt.Color(255, 204, 204));

        jButton1.setText("Save");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setText("Reset");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton5.setText("Update");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton4.setText("Delete");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        txtranscript.setText("Transcript");
        txtranscript.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtranscriptActionPerformed(evt);
            }
        });

        txtprint.setText("Print");
        txtprint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtprintActionPerformed(evt);
            }
        });

        jButton2.setText("Exit");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3)
                .addGap(18, 18, 18)
                .addComponent(jButton5)
                .addGap(18, 18, 18)
                .addComponent(jButton4)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addGap(18, 18, 18)
                .addComponent(txtprint)
                .addGap(18, 18, 18)
                .addComponent(txtranscript)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtranscript)
                    .addComponent(jButton5)
                    .addComponent(jButton4)
                    .addComponent(jButton2)
                    .addComponent(txtprint)
                    .addComponent(jButton3)
                    .addComponent(jButton1))
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(204, 255, 204));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        jTable1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 689, 251));

        jimageicon.setText("jLabel9");

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jimageicon, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jimageicon, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                .addContainerGap())
        );
        jDesktopPane1.setLayer(jimageicon, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jPanel2.add(jDesktopPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 66, -1, -1));
        jPanel2.add(path, new org.netbeans.lib.awtextra.AbsoluteConstraints(705, 40, 231, -1));

        jButton14.setText("Attach image");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton14, new org.netbeans.lib.awtextra.AbsoluteConstraints(773, 11, -1, -1));

        jButton15.setText("table print");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 273, -1, -1));

        jScrollPane3.setViewportView(jPanel2);

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Charts and graphs buttons"));

        jButton9.setText("Piechart");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton11.setText("Bar chart");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton12.setText("Line graph");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jButton13.setText("Image");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton12)
                .addGap(18, 18, 18)
                .addComponent(jButton13)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton9)
                    .addComponent(jButton11)
                    .addComponent(jButton12)
                    .addComponent(jButton13))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 980, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jmaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmaleActionPerformed
        // TODO add your handling code here:
        gender = "male";
        jmale.setSelected(true);
        jfemale.setSelected(false);
    }//GEN-LAST:event_jmaleActionPerformed

    private void jfemaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jfemaleActionPerformed
        // TODO add your handling code here:
        gender = "female";
        jfemale.setSelected(true);
        jmale.setSelected(false);
    }//GEN-LAST:event_jfemaleActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        try{
            enter();
 
        }catch(Exception ex){
                        JOptionPane.showMessageDialog(null,"you must fill the fields");

        }
            
       
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        reset();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        delete();
        showstudents();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        update();
        showstudents();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        mouseclicked();
    }//GEN-LAST:event_jTable1MouseClicked

    private void txtmathActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtmathActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtmathActionPerformed

    private void txtgradeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtgradeActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
    }//GEN-LAST:event_txtgradeActionPerformed

    private void txtsurnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtsurnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsurnameActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here
        int confirmexit = JOptionPane.showConfirmDialog(null, "Do you really want to exit?", "Exit", JOptionPane.YES_NO_OPTION);
        if (confirmexit == 0) {
            System.exit(0);
        }


    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtprintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtprintActionPerformed
        try {
            // TODO add your handling code here:
            jtranscript.print();
        } catch (PrinterException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_txtprintActionPerformed

    private void txtranscriptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtranscriptActionPerformed
        // TODO add your handling code here:
        jtextareatranscript();
    }//GEN-LAST:event_txtranscriptActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
//            int sumtotal=Integer.parseInt(txtscore.getText(om));

        try {
            Double one = Double.parseDouble(txtmath.getText());
            Double two = Double.parseDouble(txtenglish.getText());
            Double three = Double.parseDouble(txtbiology.getText());
            Double four = Double.parseDouble(txtcomputing.getText());
            Double five = Double.parseDouble(txtchemistry.getText());
            Double six = Double.parseDouble(txtphysics.getText());
            Double seven = Double.parseDouble(txtbusiness.getText());
            Double sum = one + two + three + four + five + six + seven;
            String om = String.valueOf(sum);
            txtscore.setText(om);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "you have entered nothing");
        }


    }//GEN-LAST:event_jButton6ActionPerformed

    private void txtfirstnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtfirstnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtfirstnameActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        try {
            Double one = Double.parseDouble(txtmath.getText());
            Double two = Double.parseDouble(txtenglish.getText());
            Double three = Double.parseDouble(txtbiology.getText());
            Double four = Double.parseDouble(txtcomputing.getText());
            Double five = Double.parseDouble(txtchemistry.getText());
            Double six = Double.parseDouble(txtphysics.getText());
            Double seven = Double.parseDouble(txtbusiness.getText());
            Double sum = one + two + three + four + five + six + seven;
            String om = String.valueOf(sum);
            Double average = sum / 7;
            String ave = String.valueOf(average);
            txtaverage.setText(ave);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        piechart();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void txtrankingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtrankingActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtrankingActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        try {
            Double one = Double.parseDouble(txtmath.getText());
            Double two = Double.parseDouble(txtenglish.getText());
            Double three = Double.parseDouble(txtbiology.getText());
            Double four = Double.parseDouble(txtcomputing.getText());
            Double five = Double.parseDouble(txtchemistry.getText());
            Double six = Double.parseDouble(txtphysics.getText());
            Double seven = Double.parseDouble(txtbusiness.getText());
            Double sum = one + two + three + four + five + six + seven;
            String om = String.valueOf(sum);
            Double average = sum / 7;
            if (average > 80) {
                txtranking.setText("1st class honor upper division");

            } else if (average >= 70 && average <= 80) {
                txtranking.setText("1st class honor lower division");
            } else if (average >= 60 && average <= 70) {
                txtranking.setText("2st class honor upper division");
            } else if (average >= 50 && average <= 60) {
                txtranking.setText("2st class honor lower division");
            } else if (average >= 45 && average < 50) {
                txtranking.setText("pass");
            } else if (average < 40) {
                txtranking.setText("fail");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        try {
            Double one = Double.parseDouble(txtmath.getText());
            Double two = Double.parseDouble(txtenglish.getText());
            Double three = Double.parseDouble(txtbiology.getText());
            Double four = Double.parseDouble(txtcomputing.getText());
            Double five = Double.parseDouble(txtchemistry.getText());
            Double six = Double.parseDouble(txtphysics.getText());
            Double seven = Double.parseDouble(txtbusiness.getText());
            Double sum = one + two + three + four + five + six + seven;
            String om = String.valueOf(sum);
            Double average = sum / 7;
            if (average > 80) {
                txtgrade.setText("A");

            } else if (average >= 70 && average <= 80) {
                txtgrade.setText("B");
            } else if (average >= 60 && average <= 70) {
                txtgrade.setText("C");
            } else if (average >= 40 && average <= 60) {
                txtgrade.setText("D");
            } else if (average < 40) {
                txtgrade.setText("E  try harder");
            }

//      String ave=String.valueOf(average);
//      txtaverage.setText(ave);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

    }//GEN-LAST:event_jButton8ActionPerformed

    private void txtfirstnameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtfirstnameKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtsurname.requestFocus();
        }
    }//GEN-LAST:event_txtfirstnameKeyPressed

    private void txtsurnameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsurnameKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtadress.requestFocus();
        }
    }//GEN-LAST:event_txtsurnameKeyPressed

    private void txtmathKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtmathKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtenglish.requestFocus();
        }
    }//GEN-LAST:event_txtmathKeyPressed

    private void txtenglishKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtenglishKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtbiology.requestFocus();
        }
    }//GEN-LAST:event_txtenglishKeyPressed

    private void txtadressKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtadressKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtmath.requestFocus();
        }
    }//GEN-LAST:event_txtadressKeyPressed

    private void txtbiologyKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbiologyKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtcomputing.requestFocus();
        }
    }//GEN-LAST:event_txtbiologyKeyPressed

    private void txtcomputingKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcomputingKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtchemistry.requestFocus();
        }
    }//GEN-LAST:event_txtcomputingKeyPressed

    private void txtchemistryKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtchemistryKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtphysics.requestFocus();
        }
    }//GEN-LAST:event_txtchemistryKeyPressed

    private void txtphysicsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtphysicsKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtbusiness.requestFocus();
        }
    }//GEN-LAST:event_txtphysicsKeyPressed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        barchart();
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
        linegraph();

    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        try {
            // TODO add your handling code here:
            String sql = "select Image from students where Student_Id=1";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                byte[] imagedata = rs.getBytes("Image");
                format = new ImageIcon(imagedata);
                jimageicon.setIcon(format);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        String filename = f.getAbsolutePath();
        path.setText(filename);
        try {
            File image = new File(filename);
            FileInputStream fis = new FileInputStream(image);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            for (int number; (number = fis.read(buf)) != -1;) {

                bos.write(buf, 0, number);

            }
            person_image = bos.toByteArray();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        // TODO add your handling code here:
        MessageFormat Header=new MessageFormat("Report print");
        MessageFormat Footer=new MessageFormat("Page{0,number,integer}");
            try{
              jTable1.print(JTable.PrintMode.NORMAL,Header,Footer);
            }catch(java.awt.print.PrinterException e){
                System.err.format("cannot print %s%n", e.getMessage());              
            }
    }//GEN-LAST:event_jButton15ActionPerformed

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
            java.util.logging.Logger.getLogger(Student.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Student.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Student.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Student.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Student().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox cmbcoursecode;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private com.toedter.calendar.JDateChooser jDateChooserstudents;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTable jTable1;
    private javax.swing.JRadioButton jfemale;
    private javax.swing.JLabel jimageicon;
    private javax.swing.JRadioButton jmale;
    private javax.swing.JTextArea jtranscript;
    private javax.swing.JTextField path;
    private javax.swing.JTextField txtadress;
    private javax.swing.JTextField txtaverage;
    private javax.swing.JTextField txtbiology;
    private javax.swing.JTextField txtbusiness;
    private javax.swing.JTextField txtchemistry;
    private javax.swing.JTextField txtcomputing;
    private javax.swing.JTextField txtenglish;
    private javax.swing.JTextField txtfirstname;
    private javax.swing.JTextField txtgrade;
    private javax.swing.JTextField txtmath;
    private javax.swing.JTextField txtphysics;
    private javax.swing.JButton txtprint;
    private javax.swing.JTextField txtranking;
    private javax.swing.JButton txtranscript;
    private javax.swing.JTextField txtscore;
    private javax.swing.JLabel txtstudentid;
    private javax.swing.JTextField txtsurname;
    // End of variables declaration//GEN-END:variables
}
