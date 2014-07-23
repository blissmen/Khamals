/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sales;

import Database.DatabaseHelper;
import java.sql.SQLException;
import java.util.LinkedList;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author sebs
 */
public class RevokeSale extends javax.swing.JPanel {

    private static LinkedList <TransactionGeneral> listOfGeneralTransactions;
    private static LinkedList <TransactionDetailed> listOfDetailedTransactionsWithSpecifiedId;
    private static LinkedList <String> listOfProductIdsTransactedByCustomer;
    
    DatabaseHelper transacetionGeneralDAta;
    
    private int selectedTransactionId =  0;
    /**
     * Creates new form RevokeSale
     */
    public RevokeSale() {
    
        try{
        transacetionGeneralDAta = new DatabaseHelper();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "There seems to ne an error with you database."
                    + "It cannot be started!",
                "Error Message", JOptionPane.ERROR_MESSAGE);
        }
        
        listOfGeneralTransactions = new LinkedList <TransactionGeneral>();
        listOfDetailedTransactionsWithSpecifiedId = new LinkedList <TransactionDetailed>();
        listOfProductIdsTransactedByCustomer = new LinkedList <String>();
        
        //Before initialising the components, get all transaction data from data  base, so we coul use
        //it to populate the customer name combo box;
        getAllTransactionGeneralData();        
        
        initComponents();
        this.populateComboxWithProductNames(jComboBox1);
    }

    
    public static void getAllTransactionGeneralData(){
    /**
     * Extract all transaction related data
     */    
        //Create a new one, so that all data in th old one is deleted
        listOfGeneralTransactions = new LinkedList <TransactionGeneral>();
        
        try{
                
            DatabaseHelper transacetionGeneralDAta = new DatabaseHelper();
        transacetionGeneralDAta.setQuery("Select * from KHAMALS.SALES_GENERAL");
            for (int i = 0; i < transacetionGeneralDAta.numberOfRows; i++) {
                
            TransactionGeneral transaction = new TransactionGeneral(
                    Integer.parseInt(transacetionGeneralDAta.getValueAt(i, 0).toString()),
                    transacetionGeneralDAta.getValueAt(i, 1).toString(),
                    transacetionGeneralDAta.getValueAt(i, 2).toString(),
                    transacetionGeneralDAta.getValueAt(i, 3).toString(),
                    Integer.parseInt(transacetionGeneralDAta.getValueAt(i, 4).toString()),
                    Integer.parseInt(transacetionGeneralDAta.getValueAt(i, 5).toString()),
                    Integer.parseInt(transacetionGeneralDAta.getValueAt(i, 6).toString()));            
            
            listOfGeneralTransactions.add(transaction);
                
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        
    }
        
        
    public TransactionGeneral getTransactionByItsCustomerName(String customerName){
     
        TransactionGeneral p = null;
        
        try{
        for(int i=0; i< listOfGeneralTransactions.size(); i++){
            
            if(listOfGeneralTransactions.get(i).getTransactionCustomer().matches(customerName)){
                p = listOfGeneralTransactions.get(i);
            }else{
                new ProductNotFoundException();
            }
        }
        }catch(NullPointerException e){
            
        }
        return p;
    }
 
    public int getTransactionByIts(String code){
     
        int ret = 0;
        
        try{
        for(int i=0; i< listOfDetailedTransactionsWithSpecifiedId.size(); i++){
            
            if(listOfDetailedTransactionsWithSpecifiedId.get(i).getProductCode().matches(code)){
                ret = listOfDetailedTransactionsWithSpecifiedId.get(i).getProductQuantityToBeSold();
            }else{
                new ProductNotFoundException();
            }
        }
        }catch(NullPointerException e){
            
        }
        return ret;
    }
 
    public static void populateComboxWithProductNames(JComboBox productNamesComboBox){
        
        getAllTransactionGeneralData();
       //Remove all existing items in the combo box
        productNamesComboBox.removeAllItems();
        
        //Now, add all the new elements
        for(TransactionGeneral a: listOfGeneralTransactions){
            productNamesComboBox.addItem(a.getTransactionCustomer());
        } 
       
    }
    
    private void getAllTransactionDetailedDataWithId(int transId){

        listOfDetailedTransactionsWithSpecifiedId = new LinkedList <TransactionDetailed>();
        
        try{
                        
        transacetionGeneralDAta.setQuery("Select * from KHAMALS.SALES_DETAILED WHERE TRANSACTION_ID ="
                + transId );
        
            for (int i = 0; i < transacetionGeneralDAta.numberOfRows; i++) {
                
            TransactionDetailed transaction = new TransactionDetailed(
                    Integer.parseInt(transacetionGeneralDAta.getValueAt(i, 0).toString()),
                    transacetionGeneralDAta.getValueAt(i, 1).toString(),
                    transacetionGeneralDAta.getValueAt(i, 2).toString(),
                    Integer.parseInt(transacetionGeneralDAta.getValueAt(i, 3).toString()),
                    Integer.parseInt(transacetionGeneralDAta.getValueAt(i, 4).toString()),
                    Integer.parseInt(transacetionGeneralDAta.getValueAt(i, 5).toString()),
                    Integer.parseInt(transacetionGeneralDAta.getValueAt(i, 6).toString()));            
            
            
            //Add the product code to the linked list containing the codes of all products sold in
            //this transaction            
            listOfProductIdsTransactedByCustomer.add(transacetionGeneralDAta.getValueAt(i, 2).toString());
            
            listOfDetailedTransactionsWithSpecifiedId.add(transaction);            
            //Now, populate the table with the data gotten
            
                for (int j = 0; j < listOfDetailedTransactionsWithSpecifiedId.size(); j++) {
                    jTable1.setValueAt(listOfDetailedTransactionsWithSpecifiedId.get(i).getProductname(), i, 0);
                    jTable1.setValueAt(listOfDetailedTransactionsWithSpecifiedId.get(i).getProductCode(), i, 1);
                    jTable1.setValueAt(listOfDetailedTransactionsWithSpecifiedId.get(i).getProductSellingPrice(), i, 2);
                    jTable1.setValueAt(listOfDetailedTransactionsWithSpecifiedId.get(i).getProductQuantityAvailable(), i, 3);
                    jTable1.setValueAt(listOfDetailedTransactionsWithSpecifiedId.get(i).getProductQuantityToBeSold(), i, 4);
                    jTable1.setValueAt(listOfDetailedTransactionsWithSpecifiedId.get(i).getPrice(), i, 5);
                    
                    
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
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
        jComboBox1 = new javax.swing.JComboBox();
        transactionIdLabel = new javax.swing.JLabel();
        transactionIdField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cashierNameField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        dateField = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        grandTotalField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        discountField = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        amountPaidField = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                formComponentHidden(evt);
            }
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)), "Sales General", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP));

        jLabel1.setText("Enter Customer Name");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Neba", "sebas", "test", "ana\\", "mabel", "seqj", "sdf", "asdfsdf" }));
            jComboBox1.addItemListener(new java.awt.event.ItemListener() {
                public void itemStateChanged(java.awt.event.ItemEvent evt) {
                    jComboBox1ItemStateChanged(evt);
                }
            });

            transactionIdLabel.setText("TransactionId");

            transactionIdField.setEditable(false);

            jLabel3.setText("Cashier Name");

            cashierNameField.setEditable(false);

            jLabel4.setText("Date");

            dateField.setEditable(false);

            javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
            jPanel1.setLayout(jPanel1Layout);
            jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(transactionIdField)
                .addComponent(cashierNameField)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1)
                        .addComponent(transactionIdLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)
                        .addComponent(jLabel4))
                    .addGap(0, 48, Short.MAX_VALUE))
                .addComponent(dateField)
            );
            jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addComponent(jLabel1)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(transactionIdLabel)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(transactionIdField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jLabel3)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(cashierNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel4)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(dateField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE))
            );

            jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel5.setText("Grand Total");

            jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel6.setText("Discount");

            jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel7.setText("AmountPaid");

            jButton1.setText("Revoke");
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
                    .addContainerGap()
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(grandTotalField, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(discountField, javax.swing.GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
                    .addGap(18, 18, 18)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(amountPaidField, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
            );
            jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                    .addGap(6, 6, 6)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(9, 9, 9)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(3, 3, 3)
                            .addComponent(grandTotalField))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(9, 9, 9)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(3, 3, 3)
                            .addComponent(discountField))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(9, 9, 9)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(3, 3, 3)
                            .addComponent(amountPaidField))
                        .addComponent(jButton1)))
            );

            jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255)), "Sales General", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP));

            jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null}
                },
                new String [] {
                    "Productt Name", "Product Code", "Product Selling Price", "Qunatity Available at time", "Quantity Sold", "Price"
                }
            ) {
                Class[] types = new Class [] {
                    java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
                };

                public Class getColumnClass(int columnIndex) {
                    return types [columnIndex];
                }
            });
            jScrollPane1.setViewportView(jTable1);
            jTable1.setRowHeight(30);

            javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
            jPanel3.setLayout(jPanel3Layout);
            jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 387, Short.MAX_VALUE))
            );
            jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 287, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE))
            );

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
            this.setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addContainerGap())
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );
        }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged

        try{
            String customerName = jComboBox1.getSelectedItem().toString();
            TransactionGeneral hold = this.getTransactionByItsCustomerName(customerName);

            this.selectedTransactionId = hold.getTransactionId();
            
            transactionIdField.setText(new Integer(hold.getTransactionId()).toString());
            cashierNameField.setText(hold.getTransactionCashier());
            dateField.setText(hold.getTransactionDate());

            grandTotalField.setText(new Integer(hold.getTransactionGrandTotal()).toString());
            discountField.setText(new Integer(hold.getTransactionDiscount()).toString());
            amountPaidField.setText(new Integer(hold.getTransactionAmountPaid()).toString());
            
            //Get all transactions with this ID, and use the detailed transactions to populate the 
            //jTable1
            getAllTransactionDetailedDataWithId(hold.getTransactionId());
        }catch(NullPointerException e){
            
        }
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //Revoke sale event
        
        String deleteRecordsFromKhamalsDetailed;
        
        //update the pruducts database to show that the stock has been revoked by increasing the quantity 
        //of this good available.
        try{
        for (int i = 0; i < listOfProductIdsTransactedByCustomer.size(); i++) {
            
            String query = "Select QUANTITY_AVAILABLE from KHAMALS.PRODUCT "
                + " WHERE CODE = '" 
                + jTable1.getValueAt(i, 1).toString() + "'";
        
                try{   
                    System.out.println("\nselect 1"+query);
                transacetionGeneralDAta.setQuery(query);
                }catch(SQLException e){
                e.printStackTrace();
                }    
                
            int quantityAvailableNow =  Integer.parseInt(transacetionGeneralDAta.getValueAt(0, 0).toString());
            
            int quantitySold =listOfDetailedTransactionsWithSpecifiedId.get(i).getProductQuantityToBeSold();
            System.out.println("\n quant " + quantitySold);
            int quant = quantityAvailableNow + quantitySold;
            
                System.out.printf(" \n sold %d ava %d",quantitySold,quantityAvailableNow);
           query = "UPDATE KHAMALS.PRODUCT set QUANTITY_AVAILABLE ="
                   +quant + " WHERE CODE ='" + jTable1.getValueAt(i, 1) + "'";
           
           
               System.out.println("\nselect 2"+query);
               transacetionGeneralDAta.setQuery(query);
        }
           }catch(SQLException  e){
               e.printStackTrace();
           }catch(NullPointerException e){
               
           }catch(IndexOutOfBoundsException e){
               
           }
        
                        
        
        //Remove all transaction detaile items with this id
        deleteRecordsFromKhamalsDetailed = "Delete from KHAMALS.SALES_DETAILED WHERE TRANSACTION_ID = "
                + this.selectedTransactionId;
        try{
        transacetionGeneralDAta.setQuery(deleteRecordsFromKhamalsDetailed);
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        //Remove all transaction general Items with this Id
        deleteRecordsFromKhamalsDetailed = "Delete from KHAMALS.SALES_GENERAL WHERE TRANSACTION_ID = "
                + this.selectedTransactionId;
        try{
        transacetionGeneralDAta.setQuery(deleteRecordsFromKhamalsDetailed);
        
        JOptionPane.showMessageDialog(null, "Transaction Revoked Successfully !",
                "Success Message", JOptionPane.PLAIN_MESSAGE);
   
        
        //Empty the table
        for (int i = 0; i < jTable1.getRowCount(); i++) {
                 for (int j = 0; j < jTable1.getColumnCount(); j++) {
                     jTable1.setValueAt(null, i, j);
                 }
                 
             }
           
        }catch(SQLException e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    public static void upDateMe(){
        
        
        getAllTransactionGeneralData();               
        populateComboxWithProductNames(jComboBox1);
        
    }
    
    private void formComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentHidden

    }//GEN-LAST:event_formComponentHidden

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        
    }//GEN-LAST:event_formComponentShown

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField amountPaidField;
    private javax.swing.JTextField cashierNameField;
    private javax.swing.JTextField dateField;
    private javax.swing.JTextField discountField;
    private javax.swing.JTextField grandTotalField;
    private javax.swing.JButton jButton1;
    private static javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField transactionIdField;
    private javax.swing.JLabel transactionIdLabel;
    // End of variables declaration//GEN-END:variables
}
