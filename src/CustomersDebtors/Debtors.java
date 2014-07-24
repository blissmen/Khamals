package CustomersDebtors;
import Database.DatabaseHelper;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Formatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
public class Debtors extends javax.swing.JFrame {
// DatabaseHelper Database.DatabaseObject.Db ;
    
    public Debtors() throws SQLException {
       
        initComponents();
       // populateTable();
          populateComboBox2();
            populateComboBox4();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane6 = new javax.swing.JTabbedPane();
        jInternalFrame2 = new javax.swing.JInternalFrame();
        jComboBox1 = new javax.swing.JComboBox();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jDesktopPane2 = new javax.swing.JDesktopPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        id = new javax.swing.JTextField();
        amount = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        description = new javax.swing.JTextArea();
        feed = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        jLabel13 = new javax.swing.JLabel();
        date = new datechooser.beans.DateChooserCombo();
        jButton1 = new javax.swing.JButton();
        jDesktopPane3 = new javax.swing.JDesktopPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox();
        jComboBox4 = new javax.swing.JComboBox();
        amountpaid = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        res = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        ld = new javax.swing.JTextField();
        dd = new datechooser.beans.DateChooserCombo();
        jButton2 = new javax.swing.JButton();

        jInternalFrame2.setVisible(true);

        javax.swing.GroupLayout jInternalFrame2Layout = new javax.swing.GroupLayout(jInternalFrame2.getContentPane());
        jInternalFrame2.getContentPane().setLayout(jInternalFrame2Layout);
        jInternalFrame2Layout.setHorizontalGroup(
            jInternalFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jInternalFrame2Layout.setVerticalGroup(
            jInternalFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("DEBTS AND DEBTORS");
        setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        setForeground(java.awt.Color.white);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                open(evt);
            }
        });

        jTabbedPane1.setForeground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        jTabbedPane1.setName("JAVA"); // NOI18N
        jTabbedPane1.setOpaque(true);

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setOpaque(false);

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("CUSTOMER");

        jLabel4.setText("Total Amount");

        jLabel6.setText("Identity Card #");

        jLabel8.setText("Description");

        id.setEditable(false);
        id.setBackground(new java.awt.Color(255, 255, 255));

        amount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                amountActionPerformed(evt);
            }
        });
        amount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                amountKeyTyped(evt);
            }
        });

        description.setColumns(20);
        description.setRows(5);
        jScrollPane1.setViewportView(description);

        feed.setToolTipText("");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jLabel13.setText("Latest Date");

        date.setCurrentView(new datechooser.view.appearance.AppearancesList("Bordered",
            new datechooser.view.appearance.ViewAppearance("custom",
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Dialog", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(222, 222, 222),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Dialog", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(222, 222, 222),
                    new java.awt.Color(0, 0, 255),
                    true,
                    true,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Dialog", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(0, 0, 255),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Dialog", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(128, 128, 128),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.LabelPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Dialog", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(222, 222, 222),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.LabelPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Dialog", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(222, 222, 222),
                    new java.awt.Color(255, 0, 0),
                    false,
                    false,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                (datechooser.view.BackRenderer)null,
                false,
                true)));
    date.setCalendarBackground(new java.awt.Color(255, 255, 255));
    date.setNothingAllowed(false);
    date.setFormat(0);
    date.setWeekStyle(datechooser.view.WeekDaysStyle.FULL);
    date.setBehavior(datechooser.model.multiple.MultyModelBehavior.SELECT_SINGLE);

    jButton1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
    jButton1.setText("SUBMIT");
    jButton1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton1ActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
    jPanel2.setLayout(jPanel2Layout);
    jPanel2Layout.setHorizontalGroup(
        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel2Layout.createSequentialGroup()
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(455, 455, 455)
                    .addComponent(feed, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel6)
                                .addComponent(jLabel8)
                                .addComponent(jLabel2))
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(id, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGap(256, 256, 256)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(amount, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(289, 289, 289)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addContainerGap(83, Short.MAX_VALUE))
    );
    jPanel2Layout.setVerticalGroup(
        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel2Layout.createSequentialGroup()
            .addGap(50, 50, 50)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel2))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(44, 44, 44)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel6)
                        .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(32, 32, 32)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(124, 124, 124)
                    .addComponent(jLabel8)))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel4)
                .addComponent(amount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(49, 49, 49)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel13)
                .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(11, 11, 11)
            .addComponent(feed, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(104, 104, 104)
            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(37, 37, 37))
    );

    date.getAccessibleContext().setAccessibleName("");

    javax.swing.GroupLayout jDesktopPane2Layout = new javax.swing.GroupLayout(jDesktopPane2);
    jDesktopPane2.setLayout(jDesktopPane2Layout);
    jDesktopPane2Layout.setHorizontalGroup(
        jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane2Layout.createSequentialGroup()
            .addGap(0, 0, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
    );
    jDesktopPane2Layout.setVerticalGroup(
        jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jDesktopPane2Layout.createSequentialGroup()
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(0, 20, Short.MAX_VALUE))
    );
    jDesktopPane2.setLayer(jPanel2, javax.swing.JLayeredPane.DRAG_LAYER);

    jTabbedPane1.addTab("TAKE A DEBT", jDesktopPane2);

    jDesktopPane3.setForeground(new java.awt.Color(255, 255, 255));

    jPanel3.setOpaque(false);

    jLabel3.setText("SELECT CUSTOMER NAME");

    jLabel5.setText("SELECT DEBT DATE");

    jLabel7.setText("AMOUNT TO BE PAID ");

    jLabel10.setText("AMOUNT PAID");

    jLabel11.setText("AMOUNT LEFT");

    jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
    jComboBox3.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jComboBox3ActionPerformed(evt);
        }
    });

    jComboBox4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
    jComboBox4.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jComboBox4ActionPerformed(evt);
        }
    });

    amountpaid.setEditable(false);
    amountpaid.setBackground(new java.awt.Color(255, 255, 255));

    jTextField2.addFocusListener(new java.awt.event.FocusAdapter() {
        public void focusLost(java.awt.event.FocusEvent evt) {
            jTextField2FocusLost(evt);
        }
    });
    jTextField2.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jTextField2ActionPerformed(evt);
        }
    });
    jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyTyped(java.awt.event.KeyEvent evt) {
            jTextField2KeyTyped(evt);
        }
    });

    res.setEditable(false);
    res.setBackground(new java.awt.Color(255, 255, 255));

    jLabel14.setText("LATEST DATE ");

    ld.setEditable(false);
    ld.setBackground(new java.awt.Color(255, 255, 255));

    dd.setNothingAllowed(false);
    dd.setEnabled(false);

    jButton2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
    jButton2.setText("SUBMIT");
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
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(255, 255, 255)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(46, 46, 46)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel3)
                        .addComponent(jLabel7)
                        .addComponent(jLabel5)
                        .addComponent(jLabel14)
                        .addComponent(jLabel10)
                        .addComponent(jLabel11))
                    .addGap(140, 140, 140)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(dd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jComboBox3, 0, 276, Short.MAX_VALUE)
                        .addComponent(amountpaid)
                        .addComponent(jTextField2)
                        .addComponent(res)
                        .addComponent(ld)
                        .addComponent(jComboBox4, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(299, 299, 299)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addContainerGap(249, Short.MAX_VALUE))
    );
    jPanel3Layout.setVerticalGroup(
        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel3Layout.createSequentialGroup()
            .addGap(50, 50, 50)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel3)
                .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(37, 37, 37)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(jLabel5)
                .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(26, 26, 26)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel14)
                .addComponent(ld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(27, 27, 27)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel7)
                .addComponent(amountpaid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(41, 41, 41)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel10)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(32, 32, 32)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(res, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(38, 38, 38)
            .addComponent(dd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jLabel12)
            .addGap(97, 97, 97)
            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(107, Short.MAX_VALUE))
    );

    javax.swing.GroupLayout jDesktopPane3Layout = new javax.swing.GroupLayout(jDesktopPane3);
    jDesktopPane3.setLayout(jDesktopPane3Layout);
    jDesktopPane3Layout.setHorizontalGroup(
        jDesktopPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane3Layout.createSequentialGroup()
            .addGap(0, 0, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
    );
    jDesktopPane3Layout.setVerticalGroup(
        jDesktopPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane3Layout.createSequentialGroup()
            .addGap(0, 22, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
    );
    jDesktopPane3.setLayer(jPanel3, javax.swing.JLayeredPane.POPUP_LAYER);

    jTabbedPane1.addTab("PAY DEBT", jDesktopPane3);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jTabbedPane1)
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jTabbedPane1)
    );

    pack();
    }// </editor-fold>//GEN-END:initComponents
 private void populateComboBox2(){
   ArrayList    dname = new ArrayList();
    Functions function = new Functions();
    dname = function.getAllUData("LAST_NAME","CUSTOMERS"); 
    ArrayList fname = function.getAllUData("FIRST_NAME","CUSTOMERS"); 
    
    jComboBox2.removeAllItems();
 try{        for (int i=0;i<fname.size();i++) {
            jComboBox2.addItem(dname.get(i)+""+fname.get(i));
            System.out.println(dname.toString());
        }
    
    }
 catch(Exception ed)
    {}
 }
 
    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed

        if(jComboBox2.getSelectedIndex()!=-1){
            try {
                String name = jComboBox2.getSelectedItem().toString();
                String sql = " select ID from DEBTORS where Name ='"+name+"'";
                try {
                    id.setText(db.ExecuteQuery(sql).get(0).toString());
                }
                catch(Exception nn)
                {
                    System.out.println(nn.getClass()+" "+nn.toString() );
                }
                
            }
            catch(Exception ex)
            {
                Logger.getLogger(Debtors.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void amountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_amountActionPerformed
   
        
// TODO add your handling code here:
    }//GEN-LAST:event_amountActionPerformed

    private void amountKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_amountKeyTyped
    filterInput(evt);
  
// TODO add your handling code here:
    }//GEN-LAST:event_amountKeyTyped

    private void jTextField2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyTyped
        this.filterInput(evt);
    }//GEN-LAST:event_jTextField2KeyTyped

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
     int result;
        if(Integer.parseInt(amountpaid.getText())>0)
         result =Integer.parseInt(jTextField2.getText())-Integer.parseInt(amountpaid.getText());
        else 
            result= Integer.parseInt(jTextField2.getText())+Integer.parseInt(amountpaid.getText());
        res.setText(""+result);
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jComboBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox4ActionPerformed
        if(jComboBox4.getItemCount()>0)
        {
            try {
                populatejcomboBox3((String) jComboBox4.getSelectedItem());
            } catch (Exception ex) {
                Logger.getLogger(Debtors.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_jComboBox4ActionPerformed

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
     try{
        if(jComboBox3.getItemCount()>0)
        {
        String dat = jComboBox3.getSelectedItem().toString();
        Functions f = new Functions();
        ArrayList re = new ArrayList();
            try {
              re =  f.fetchDebt(jComboBox4.getSelectedItem().toString(),jComboBox3.getSelectedItem().toString());
             } 
            catch (Exception ex) {
                Logger.getLogger(Debtors.class.getName()).log(Level.SEVERE, null, ex);
            }
            ld.setText(re.get(0).toString());
            amountpaid.setText(re.get(1).toString());
        
        
        }
     }
     catch(Exception fe)

     {}
    }//GEN-LAST:event_jComboBox3ActionPerformed

    private void jTextField2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField2FocusLost

int result;
        if(Integer.parseInt(amountpaid.getText())>0)
         result =Integer.parseInt(jTextField2.getText())-Integer.parseInt(amountpaid.getText());
        else 
            result= Integer.parseInt(jTextField2.getText())+Integer.parseInt(amountpaid.getText());
        res.setText(""+result);        

// TODO add your handling code here:
    }//GEN-LAST:event_jTextField2FocusLost

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       
        try {
            String name = this.jComboBox2.getSelectedItem().toString();
            String id1 =id.getText() ;
            int amountt;
            amountt = Integer.parseInt(this.amount.getText());
            String descrip = this.description.getText();
            Calendar ldate = this.date.getCurrent();
            Functions fn = new Functions();
            if(fn.addDebt(name,id1, ldate, amountt, descrip)==0)
            {
                feed.setText("Succesfully Added Debt");
                description.setText("");
                this.amount.setText("");
            }
            else
            {
                feed.setText("Faillure to  Add Debt Ensure That Amount Contained Numbers not Characters");
            }
        }
        catch(Exception bb)
        {
        
        }

        
// TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

    try
    {
        Functions Fn = new Functions();
        Formatter bn = new Formatter();
        String Status;
        if(Integer.parseInt(res.getText())==0)
            Status="paid";
        else
            Status = "Partial";
        Fn.payAPDebt(jComboBox4.getSelectedItem().toString(),Integer.parseInt(res.getText()),jComboBox3.getSelectedItem().toString() ,Status);
      //  populateTable();
    }
  catch(Exception nn)
  {}


    }//GEN-LAST:event_jButton2ActionPerformed

    private void open(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_open
       this.toFront();
     this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
     this.setLocationRelativeTo(null);
    }//GEN-LAST:event_open
public static  DatabaseHelper db;
public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
   // Database.DatabaseObject.CloseConnection();
             try{
                 db = new DatabaseHelper();
             }
             catch(Exception pp)
             {
                 System.out.println(pp.toString());
             }
                
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Debtors.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Debtors.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Debtors.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Debtors.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            try {
                new Debtors().setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(Debtors.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField amount;
    private javax.swing.JTextField amountpaid;
    private datechooser.beans.DateChooserCombo date;
    private datechooser.beans.DateChooserCombo dd;
    private javax.swing.JTextArea description;
    private javax.swing.JLabel feed;
    private javax.swing.JTextField id;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JComboBox jComboBox4;
    private javax.swing.JDesktopPane jDesktopPane2;
    private javax.swing.JDesktopPane jDesktopPane3;
    private javax.swing.JInternalFrame jInternalFrame2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane6;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField ld;
    private javax.swing.JTextField res;
    // End of variables declaration//GEN-END:variables

private void populateTable() throws SQLException {
    ArrayList name = new ArrayList();
    ArrayList ID = new ArrayList();
    ArrayList amount2 = new ArrayList();
    ArrayList ldate = new ArrayList();
    ArrayList tdate = new ArrayList();
    ArrayList stat = new ArrayList();
    Functions function = new Functions();
    ldate = function.getAllDData("LATEST_DATE_OF_PAYMENT", "DEBTORS");
    tdate = function.getAllDData("DATE_TACKEN", "DEBTORS");
     stat = function.getAllDData("STATUS", "DEBTORS");
    
    name = function.getAllDData("NAME","DEBTORS");
    ID = function.getAllDData("ID","DEBTORS");
    amount2 = function.getAllDData("Amount","DEBTORS");
    
   
      }

   

private void populateComboBox4() {
  try{  
      ArrayList    sname = new ArrayList();
    Functions function = new Functions();
    
    ArrayList fname = new ArrayList();
    fname = function.getAllUData("NAME","DEBTORS"); 
      System.out.println(fname.toString());
   jComboBox4.removeAllItems();
   jComboBox3.removeAllItems();
     jComboBox2.addItem("--New--");
        for (Object fname1 : fname) {
            jComboBox4.addItem(fname1);
        } 
    
    }
  catch(Exception sd)
  {
      System.out.println(sd.toString());
  }

}

private void populatejcomboBox3(String selectedItem) {
  
   try{   ArrayList    sname = new ArrayList();
    Functions function = new Functions();
    jComboBox3.removeAllItems();
    sname = function.getSpecificData("DATE_TACKEN","DEBTORS",selectedItem); 
    System.out.println(sname.toString());
       System.out.println("Hello");
       jComboBox3.removeAllItems();
        for (Object sname1 : sname) {
            jComboBox3.addItem(sname1);
        } 
        jComboBox3.add(amount);
     payloan(jComboBox3.getSelectedItem(),selectedItem);
     
   }
   catch(Exception wqa)
   {
     System.out.println(wqa.toString());
   }
        
    }

private void payloan(Object selectedItem,String name) throws SQLException {
     String sql = "select sum(amount) from debtors where name ='"+name+"' and date_tacken ='"+selectedItem+"' ";
     DatabaseHelper bn = new DatabaseHelper();
    try {
            amountpaid.setText( ""+bn.ExecuteQuery(sql).get(0));
        } catch (SQLException ex) {
            Logger.getLogger(Debtors.class.getName()).log(Level.SEVERE, null, ex);
            amountpaid.setText(""+0);
        }
    
    }

   private void filterInput(KeyEvent evt) {
    
         int k = evt.getKeyCode();
       char c = evt.getKeyChar();
      if (!((c >= '0') && (c <= '9') ||
         (c == KeyEvent.VK_ENTER)||(c==KeyEvent.VK_BACK_SPACE))) {
          JOptionPane.showMessageDialog(null,"Number Required For this field");
        evt.consume();
      } 
    
    }
}
