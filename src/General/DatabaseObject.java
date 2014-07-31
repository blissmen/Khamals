/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package General;

import java.sql.SQLException;
/**
 *
 * @author blissmen
 */
public class DatabaseObject {
  static public DatabaseHelper Db;
    public static Void OpenConnection()
    {   
      try {
          Db = new DatabaseHelper();
      } catch (SQLException ex) {
          System.out.println(ex.toString());
      }
        return null;
}
    public static Void CloseConnection()
    {  
      try {
          Db.connection.close();
          Db.statement.close();
          
          
      } catch (SQLException ex) {
          
          System.out.println(ex.getClass()+" Error "+ex.getCause());
      }
      return null;
}
    
}
