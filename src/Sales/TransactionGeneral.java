/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sales;

/**
 *
 * @author sebs
 */
public class TransactionGeneral {
    
    private int transactionId;
    private String customerName;
    private String cashierName;
    private String date;
    private int grandTotal;
    private int  discount;
    private int amountPaid;
    
    public TransactionGeneral(){
        
    }
    
    public TransactionGeneral(int id, String cashier, String customer, String date
           , int grandTotal, int discount, int amountPaid){
        
        this.transactionId = id;
        this.customerName = customer;
        this.cashierName = cashier;
        this.date = date;
        this.grandTotal = grandTotal;
        this.discount = discount;
        this.amountPaid = amountPaid;
    }
    
    public int getTransactionId(){ return this.transactionId; }
    public String getTransactionCustomer(){ return this.customerName; }
    public String getTransactionCashier(){ return this.cashierName; }
    public String getTransactionDate(){ return this.date; }
    public int getTransactionGrandTotal(){ return this.grandTotal; }
    public int getTransactionDiscount(){return this.discount;}
    public int getTransactionAmountPaid(){ return this.amountPaid; }
}
