/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sales;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author sebs
 */
public class ProductForSales {
    
   String productCode;  //Product id is a string of 4 characters
   String productName; //The name of the product
   int    quantityInStock;
   int    productSellingPrince;
 public SimpleStringProperty PName= new SimpleStringProperty();
  public  SimpleStringProperty Codes = new SimpleStringProperty();
    public SimpleStringProperty Qty= new SimpleStringProperty();
  public  SimpleStringProperty Desc = new SimpleStringProperty();
   public SimpleStringProperty Disc= new SimpleStringProperty();
  public  SimpleStringProperty Up = new SimpleStringProperty();
   public SimpleStringProperty Total= new SimpleStringProperty();
  public  SimpleStringProperty Inx = new SimpleStringProperty();
  
      public String getPName(){
        return PName.get();
    }
    public String getCodes(){
        return Codes.get();
    }
    public String getQty(){
        return Qty.get();
    }
    
    
     public String getDesc(){
        return Desc.get();
    }
    
    public String getDisc(){
        return Disc.get();
    }
    public String getTotal(){
        return Total.get();
    }
    public String getUp(){
        return Up.get();        
    }
    public String getInx(){
        return Inx.get();        
    }
   
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
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
