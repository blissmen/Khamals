/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Inventory;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Klexy
 */
public class Order {
    public SimpleStringProperty customerName = new SimpleStringProperty(); 
    public SimpleStringProperty productName = new SimpleStringProperty();
    public SimpleStringProperty numberRequested = new SimpleStringProperty();
    public SimpleStringProperty datePlaced = new SimpleStringProperty();
    public SimpleStringProperty dateDue = new SimpleStringProperty();
    public SimpleStringProperty price = new SimpleStringProperty();
    public SimpleStringProperty orderID = new SimpleStringProperty();
    
    public String getCustomerName(){
        return customerName.get();
    }
    
    public String getProductName(){
        return productName.get();
    }
    public String getNumberRequested(){
        return numberRequested.get();
    }
    public String getDatePlaced(){
        return datePlaced.get();        
    }
    public String getDateDue(){
        return dateDue.get();
    }
    public String getPrice(){
        return price.get();                
    }
    public String getOrderID(){
        return orderID.get();
    }  
    
    
}
