/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sales;

/**
 *
 * @author sebs
 */
public class ProductForSales {
    
   String productCode;  //Product id is a string of 4 characters
   String productName; //The name of the product
   int    quantityInStock;
   int    productSellingPrince;
   
   public ProductForSales(){}
   public ProductForSales(String name, String code,int sellingPrice, int quantityInStock){
       
       this.productName = name;
       this.productCode = code;
       this.quantityInStock = quantityInStock;
       this.productSellingPrince = sellingPrice;
   }
   
   public String getCode(){ return this.productCode; }
   public String getName() { return this.productName; }
   public int getQuantityInStock(){ return this.quantityInStock; }
   public int getSellingPrice(){ return this.productSellingPrince; }
   
   public void setCode(String code){ this.productCode = code; }
   public void setName(String name){ this.productName = name;}
   public void setCostPrice(int quantityInStock) { this.quantityInStock = quantityInStock; }
   public void setSellingPrice(int sellingPrice){ this.productSellingPrince = sellingPrice; }
    
}
