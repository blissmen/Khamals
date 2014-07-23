/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package CustomersDebtors;

//import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Formatter;
import Database.DatabaseHelper;
import javafx.scene.control.TextField;

public class Functions {
    static DatabaseHelper database;
     public static String amount="0";
      public static String sql="";
      public static int results=0;
      public static int status=0;

    public Functions()  {
     
             try
             {
                
                 this.database = new DatabaseHelper() ;
        }
    catch(Exception ws)
    {
        System.out.println(ws.getCause()+" of class "+ws.getClass());
    }
    }
   public  boolean updateAccount(String id,TextField bb)
    {
     
       sql="select amount from debtors where id='"+id+"'";
         try {
              amount= database.ExecuteQuery(sql).get(0).toString();
              results = Integer.parseInt(amount)+Integer.parseInt(bb.getText());
               sql="update debtors set amount ="+results+" where id='"+id+"'";
           
           status = database.Query(sql);
         } catch (SQLException ex) {
             System.out.println(""+ex.getClass()+": "+ex.toString());
         }
         return false;
    }
    
    /**
     *
     * @param id
     * @return
     */
    public ArrayList fetchDebt(String id) 
    {
     try{sql="select name,date_tacken,Date_Payment,Amount,Details, from debtors where id='"+id+"'"; 
      return database.ExecuteQuery(sql);
    }
    catch(Exception jh)
    {
        System.out.println(jh.toString());
    return null;
    }
    }
    public int addDebt(String name,String id,Calendar pay,int amount,String details)
    {
        Calendar dateTime = Calendar.getInstance();
        Formatter nn= new Formatter();
        Formatter c = nn.format("%tF", pay);
        nn.flush();
       Formatter bb = new Formatter();
        Formatter p = bb.format("%tF", dateTime);
        System.out.println(p.toString());
         System.out.println(c.toString());
    sql="insert into debtors(name,id,date_tacken,LATEST_DATE_OF_PAYMENT,Amount,Description) values ('"+name+"',"+id+",'"+ p.toString()+"','"+c.toString()+"',"+amount+",'"+details+"')"; 
    status =database.Query(sql);  
    return status;
    }
    
    public int addDebt(String id,int amount)
    {
        sql ="update debtors set amount="+amount+" where id= '"+id+"'";
        status=database.Query(sql);
         return status;
    
    }
    
    public boolean payAPDebt(String id,int amount,String date, String status)
    {
       sql ="update debtors set amount="+amount+" where name= '"+id+"' and date_tacken ='"+date+"'";
       database.Query(sql);
        sql ="update debtors set status= '"+status+"' where name= '"+id+"' and date_tacken ='"+date+"'";
       database.Query(sql);
       
         return false;
    }
    
   
    public ArrayList getAllData(String data,String table)
    {
         ArrayList re=new ArrayList();
        try{
       
        sql ="select "+data+" from "+table+" order by date";
      //  DatabaseHelper db = new DatabaseHelper();
            System.out.println(sql);
        re=database.ExecuteQuery(sql);
        
        }
        catch(Exception sh)
        {
            System.out.println(sh.toString());
            
        }
        return re;
    
    }
     public ArrayList getAllDData(String data,String table)
    {
        ArrayList re=new ArrayList();
         sql ="select "+data+" from "+table+" ";
       try{
           System.out.println(sql);
        re=database.ExecuteQuery(sql);
         return re;
       }
       catch(Exception shd)
       {
           System.out.println(shd.toString());    
       }
    return  re;
    }
    
    public boolean AddCustomer(int id,String fname,String sname,String location,String telephone,String code,String email,String stats) throws SQLException
            {
                int res=1;
               
                 Calendar dateTime = Calendar.getInstance();
                 System.out.println("Hello"+dateTime);
                 Formatter nn = new Formatter();
                   System.out.println("Hello"+dateTime);
                     System.out.println("Hello");
                // System.out.println("");
                 System.out.println(id+"HELLO");
                 System.out.println(fname);
                 System.out.println(sname);
                 System.out.println(location);
                 System.out.println(telephone);
                 System.out.println(email);
                 System.out.println(code);
                 System.out.println(stats);
                 Formatter c = new Formatter();
                 c = nn.format("%tF", dateTime);
                 String sql1 = "insert into customers(DATE,FIRST_NAME,LAST_NAME,LOCATION,CODE,ID,TELEPHONE,Type,email) values('"+c.toString()+"','"+fname+"','"+sname+"','"+location+"','"+code+"',"+id+
                         ",'"+telephone+"','"+stats+"','"+email+"')";
                 System.out.println(sql1);
                 try{
                     System.out.println(sql1);
                 res = database.Query(sql1);
                 }
                 catch(Exception non)
                 {
                     System.out.println(non.toString());
                     
                 }
         return res==1;
            }

    ArrayList getAllUData(String name, String debtors) {
  
         ArrayList re=new ArrayList();
         sql ="select distinct "+name+" from "+debtors+" ";
       
       try
       {
           re=database.ExecuteQuery(sql);
           return re;
       }
       catch(Exception pp)
       {
           System.out.println(""+pp.getClass()+" :"+pp.toString());
       return null;
       }
    
    }

    ArrayList getSpecificData(String first_name, String customers, String selectedItem) throws SQLException {
  
        
         ArrayList re=new ArrayList();
         sql ="select  "+first_name+" from "+customers+" where name='"+selectedItem+"' ";
        System.out.println(sql);
        
           re=database.ExecuteQuery(sql);
           return re;
       
    
    
    }

    static boolean DeleteAccount(String selectedItem) {
      sql = "delete from Customers where code = '"+selectedItem+"'";
     
         return Functions.database.Query(sql)==0;
    }

     ArrayList fetchDebt(String toString, String toString0) {
        String sql = "Select LATEST_DATE_OF_PAYMENT,amount from Debtors where name ='"+toString+"' and date_tacken='"+toString0+"'";
    
        try
        {return database.ExecuteQuery(sql);
        } 
        catch(Exception ews)
                    {
                        System.out.println(ews.toString());
                    }
    return null;
     }

    ArrayList getAllUData(String name, String debtors, String paid) {
        
     ArrayList re=new ArrayList();
         sql ="select distinct "+name+" from "+debtors+" where status != '"+paid+"'";
        
       try
       {
           re=database.ExecuteQuery(sql);
           return re;
       }
       catch(Exception pp)
       {
           System.out.println(""+pp.getClass()+" :"+pp.toString());
       return null;
       }    
   
    }

  
    
}
