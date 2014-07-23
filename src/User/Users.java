/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package User;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Wilma
 */
public class Users {
    public static String username;
    public static String password;
    public static String firstname;
    public static String lastname;
    public static String status;
    public static int time;
    public static Date startTime;
    public static Date stopTime;
    
    public static String getName(){
        return username;
    }
    
    public static void setName(String username){
        Users.username = username;
    }
    
    public static String getFirstName(){
        return firstname;
    }
    
    public static void setFirstName(String username){
        Users.firstname = username;
    }
    
    public static String getLastName(){
        return lastname;
    }
    
    public static void setLastName(String username){
        Users.lastname = username;
    }
    
    public static String getPwdName(){
        return password;
    }
    
    public static void setPwdName(String username){
        Users.password = username;
    }
    
    public static String getStatus(){
        return status;
    }
    
    public static void setStatus(String username){
        Users.status = username;
    }
    
     public static int getTime(){
        return time;
    }
    
    public static void setTime(int username){
        Users.time = username;
    }
    
    
    public static void setStartTime(Date username){
        Users.startTime = username;
    }
    
      public static void setStopTime(Date username){
        Users.stopTime = username;
    }
      
      public static boolean Available(String username){
         Calendar cal = Calendar.getInstance();
         
        return ((username == Users.username) || !cal.getTime().after(stopTime)) && !cal.getTime().before(startTime);
      }
}
