/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MAIN;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Klexy
 */
public class Debtors {
    public SimpleStringProperty Name = new SimpleStringProperty(); 
    public SimpleStringProperty Name1 = new SimpleStringProperty(); 
    public SimpleStringProperty Name2 = new SimpleStringProperty(); 
     public SimpleStringProperty Index = new SimpleStringProperty(); 
    public SimpleStringProperty ld = new SimpleStringProperty();
    public SimpleStringProperty td = new SimpleStringProperty();
    public SimpleStringProperty Amount = new SimpleStringProperty();
   public SimpleStringProperty status = new SimpleStringProperty();
    
    public String getName(){
        return Name.get();
    }
    public String getName1(){
        return Name1.get();
    }
    public String getName2(){
        return Name2.get();
    }
    
    
     public String getIndex(){
        return Index.get();
    }
    
    public String getAmount(){
        return Amount.get();
    }
    public String getld(){
        return ld.get();
    }
    public String gettd(){
        return td.get();        
    }
    public String getStatus(){
        return status.get();        
    }
}
