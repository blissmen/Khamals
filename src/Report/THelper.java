/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Report;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author blissmen
 */
public class THelper {
    
//    
//    nam = functions.getAllDData("NAME", "PRODUCT");
//        cat = functions.getAllDData("CATEGORY", "PRODUCT");
//        LDO = functions.getAllDData("LASTLY_ADDED_ON", "PRODUCT");
//        LSO = functions.getAllDData("QUANTITY_AVAILABLE", "PRODUCT");
//        QTA = functions.getAllDData("LASTLY_SOLD_ON", "PRODUCT");
//        summ = functions.getAllDData("SELLING_PRICE", "PRODUCT");
//        
    public SimpleStringProperty Index = new SimpleStringProperty(); 
    public SimpleStringProperty Date = new SimpleStringProperty(); 
    public SimpleStringProperty QuantityBrouth = new SimpleStringProperty(); 
    // public SimpleStringProperty Index = new SimpleStringProperty(); 
    public SimpleStringProperty UP = new SimpleStringProperty();
    public SimpleStringProperty Desc = new SimpleStringProperty();
    public SimpleStringProperty TAmount = new SimpleStringProperty();
   public SimpleStringProperty LastSolddate = new SimpleStringProperty();
     public SimpleStringProperty LastIndate = new SimpleStringProperty();
     
     
    public SimpleStringProperty   QUANTITY_AVAILABLE = new SimpleStringProperty();
   public SimpleStringProperty LASTLY_SOLD_ON = new SimpleStringProperty();
     public SimpleStringProperty SELLING_PRICE = new SimpleStringProperty();
    public SimpleStringProperty   CATEGORY = new SimpleStringProperty();
   //public SimpleStringProperty LastSolddate = new SimpleStringProperty();
    // public SimpleStringProperty SELLING_PRICE = new SimpleStringProperty();
    //public SimpleStringProperty   QUANTITY_AVAILABLE = new SimpleStringProperty();
   
    public String getQUANTITY_AVAILABLE(){
        return QUANTITY_AVAILABLE.get();
    }
    public String getSELLING_PRICE(){
        return SELLING_PRICE.get();
    }
    public String getCATEGORY(){
        return CATEGORY.get();
    }
    public String getLASTLY_SOLD_ON(){
        return LASTLY_SOLD_ON.get();
    }
   
    
    
    
    
    
    
    
    
    
    
    
    public String getDate(){
        return Date.get();
    }
    public String getQuantityBrouth(){
        return QuantityBrouth.get();
    }
    public String getUP(){
        return UP.get();
    }
    
    
     public String getDesc(){
        return Desc.get();
    }
    
    public String getTAmount(){
        return TAmount.get();
    }
    public String getLastSolddate(){
        return LastSolddate.get();
    }
    public String getLastIndate(){
        return LastIndate.get();        
    }
    public String getIndex(){
        return Index.get();        
    }
}

    

