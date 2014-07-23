/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sales;

/**
 *
 * @author sebs
 */
public class TransactionDetailed {
    
    private int transactionId;
    private String productName;
    private String productCode;    
    private int sellingPrice;
    private int  quantityAvailable;
    private int quantityToBeSold;
    private int price;
    
    public TransactionDetailed(){
        
    }
    
    public TransactionDetailed(int id, String pName, String pCode,int sellingPrice
           , int quantAvailable, int quantToBeSold, int price){
        
        this.transactionId  =   id;
        this.productName    =   pName;
        this.productCode    =   pCode;        
        this.sellingPrice   =   sellingPrice;        
        this.quantityAvailable  =   quantAvailable;
        this.quantityToBeSold   =   quantToBeSold;
        this.price          =   price;
    }
    
    public int getTransactionId(){ return this.transactionId; }
    public String getProductname(){ return this.productName; }
    public String getProductCode(){ return this.productCode; }
    public int getProductSellingPrice(){ return this.sellingPrice; }
    public int getProductQuantityAvailable(){return this.quantityAvailable;}
    public int getProductQuantityToBeSold(){ return this.quantityToBeSold; }
    public int getPrice(){ return this.price; }
}
