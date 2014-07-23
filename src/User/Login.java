/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package User;
import Database.DatabaseHelper;
import java.sql.SQLException;

/**
 *
 * @author Administrator
 */
public class Login {

   public static DatabaseHelper database;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException {
        
        try{
      database = new DatabaseHelper();
        }catch(SQLException e){
            System.out.println(e.getClass()+"nm: "+e.toString());
        }
       LoginType login = new LoginType();
       login.show();
       //ManageAccount account = new ManageAccount();
       //account.show();
       
    }
    
}
