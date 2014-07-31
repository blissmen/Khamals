/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package CustomersAndDebtors;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Klexy
 */
public class Customer {
    public SimpleStringProperty Name = new SimpleStringProperty(); 
    public SimpleStringProperty Tel = new SimpleStringProperty();
    public SimpleStringProperty Location = new SimpleStringProperty();
    public SimpleStringProperty ID = new SimpleStringProperty();
   public SimpleStringProperty index = new SimpleStringProperty();
    
    public String getName(){
        return Name.get();
    }
     public String getIndex(){
        return index.get();
    }
    
    public String getTel(){
        return Tel.get();
    }
    public String getLocation(){
        return Location.get();
    }
    public String getID(){
        return ID.get();        
    }
}
