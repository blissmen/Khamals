/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sales;

import java.sql.SQLException;
import java.util.LinkedList;
import javax.swing.JComboBox;
import Database.DatabaseHelper;

//p.getProductIdsComboBox()
/**
 *
 * @author sebs
 */
public class ProductsForSales  {
    
/*    public static String selectedItemName;
    public static String selectedItemCode;
    public static int    selectedItemPrice;
    public static int selectedItemQuantity;
  */  
    public static LinkedList <ProductForSales> listOfProducts;
    //JComboBox productIdsComboBox;// = new JComboBox();   
    
    private void productIdsComboBoxActionPerformed (java.awt.event.ActionEvent evt) {

/*
        try{
            
        System.out.println(productIdsComboBox.getSelectedItem().toString());
        int selectedItemIndex = productIdsComboBox.getSelectedIndex();
        this.selectedItemName = productIdsComboBox.getSelectedItem().toString();

          
        this.getDetailsProductByName(selectedItemName);

        
/*        ProductForSales hold = listOfProducts.get(selectedItemIndex );
            this.selectedItemCode = hold.getCode();
            this.selectedItemPrice = hold.getSellingPrice();
            this.selectedItemQuantity = hold.getQuantityInStock();
            
  */          
        /*}catch(NullPointerException e){
            
        }catch(ArrayIndexOutOfBoundsException e){
            
        } */
    }

    
    public ProductsForSales( ){               
        

      /*roductIdsComboBox = new JComboBox();
      /productIdsComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                productIdsComboBoxActionPerformed(evt);
            }
        });
        */ 
        listOfProducts = new LinkedList <ProductForSales>();
                
    }
    
    private static void getProductsFromDb(){
        
        //Create a new one, so that all data in th old one is deleted
         ProductsForSales.listOfProducts = new LinkedList <ProductForSales>();
        
             //Get all products from PRODUCTS table in data base.
        //Select PRODUCT_CODE, PRODUCT_NAME,SELLING_PRICE_QUANTITY   
         
        try{
        
        
            DatabaseHelper transacetionGeneralDAta = new DatabaseHelper();
        transacetionGeneralDAta.setQuery("Select NAME, CODE, SELLING_PRICE, QUANTITY_AVAILABLE"
                + " from KHAMALS.PRODUCT where QUANTITY_AVAILABLE > 0");
            for (int i = 0; i < transacetionGeneralDAta.numberOfRows; i++) {
                
            ProductForSales product = new ProductForSales(
                    transacetionGeneralDAta.getValueAt(i, 0).toString(),
                    transacetionGeneralDAta.getValueAt(i, 1).toString(),
                    new Integer(transacetionGeneralDAta.getValueAt(i, 2).toString()),
                    new Integer(transacetionGeneralDAta.getValueAt(i, 3).toString()));            
            
            listOfProducts.add(product);
          //  productIdsComboBox.addItem(product.getName());
            /*    for (int j = 0; j < transacetionGeneralDAta.getColumnCount(); j++) {
                    System.out.println("" 
                    + transacetionGeneralDAta.getValueAt(i, j).toString());
                }*/
                
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        
    }
    
    private void getProductByName(String productName){
        
        
        int i = 0;
        if(listOfProducts.listIterator().next().productCode.matches(productName)){
            
        }
        
    }
    
    public ProductForSales getProductById(String id){
     

        ProductForSales p = new ProductForSales();
        
        for(int i=0; i< listOfProducts.size(); i++){
            
            if(listOfProducts.get(i).productCode.matches(id))p= listOfProducts.get(i);
            else p =  listOfProducts.get(0);
    }
        
        return p;
    }
  
    /*
    public void getDetailsProductByName(String name){
     
        ProductForSales p = new ProductForSales();
        
        for(int i=0; i< listOfProducts.size(); i++){
            
            if(listOfProducts.get(i).productName.matches(name)){
                this.selectedItemCode =  listOfProducts.get(i).getCode();
                this.selectedItemPrice = listOfProducts.get(i).getSellingPrice();
                this.selectedItemQuantity = listOfProducts.get(i).getQuantityInStock();
                
                
        try{
            int editingRow = SalesTransactionInterface.jTable1.getEditingRow() ;
            int editingColumn = SalesTransactionInterface.jTable1.getEditingColumn();
                
            SalesTransactionInterface.myEditingRow = editingRow;
            
                    SalesTransactionInterface.jTable1.setValueAt(ProductsForSales.selectedItemCode,
                            SalesTransactionInterface.myEditingRow, 1);
                    
                    SalesTransactionInterface.jTable1.setValueAt(ProductsForSales.selectedItemPrice, 
                            SalesTransactionInterface.myEditingRow, 2);
                    
                    SalesTransactionInterface.jTable1.setValueAt(ProductsForSales.selectedItemQuantity,
                            SalesTransactionInterface.myEditingRow, 3);
        }catch(ArrayIndexOutOfBoundsException e){
            
        }catch(NullPointerException e){}
            }
        }
        
    }
    */
    public static ProductForSales getProductByItsName(String name){
     
        ProductForSales p = null;
        
        try{
        for(int i=0; i< listOfProducts.size(); i++){
            
            if(listOfProducts.get(i).productName.matches(name)){
                p = listOfProducts.get(i);
            }else{
                new ProductNotFoundException();
            }
        }
        }catch(NullPointerException e){
            
        }
        return p;
    }
 
    public static void populateComboxWithProductNames(JComboBox productNamesComboBox){
        
        getProductsFromDb();
       //Remove all existing items in the combo box
        productNamesComboBox.removeAllItems();
        
        //Now, add all the new elements
        for(ProductForSales a: listOfProducts){
            productNamesComboBox.addItem(a.getName());
        } 
       
    }
}
