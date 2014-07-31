/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Login;

import General.ControlledScreen;
import General.DatabaseHelper;
import General.ScreensController;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Formatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import org.controlsfx.dialog.Dialogs;

/**
 * FXML Controller class
 *
 * @author Wilma
 */
public class ManageAccountController implements Initializable, ControlledScreen {
    @FXML
    private TextField fname;
    @FXML
    private TextField lname;
    @FXML
    private TextField tel;
    @FXML
    private TextField add;
    @FXML
    private TextField uname;
    @FXML
    private PasswordField passwd;
    @FXML
    private PasswordField passwd1;
    @FXML
    private RadioButton normal;
    @FXML
    private RadioButton admin;
    @FXML
    private Button createuser;
    @FXML
    private Button cancel;
    @FXML
    private Label fields;
    
    int status = 2;
    public static ScreensController manageAccountScreen;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       ToggleGroup group = new ToggleGroup();
       normal.setToggleGroup(group);
       admin.setToggleGroup(group);
    } 
    
     private void filterInput(javafx.scene.input.KeyEvent evt) {
      //  int k = evt.getKeyCode();
        KeyCode g = evt.getCode();
        String k = evt.getCharacter();
        char c = k.charAt(0);
            if (!((c >= '0') && (c <= '9') ||(c == KeyEvent.VK_ENTER)|| c == KeyEvent.VK_BACK_SPACE))
       {
          //JOptionPane.showMessageDialog(null,"Phone Number Required Please");
          Dialogs.create().title("Wrong Input").message("Phone Number Required Please").showInformation();
          
          evt.consume();
       }
           
    }
     
     
     public boolean CheckState(){
        if (fname.getText().toString().toCharArray().length==0 || lname.getText().toString().toCharArray().length==0 || tel.getText().toString().toCharArray().length==0 || add.getText().toString().toCharArray().length==0 || uname.getText().toString().toCharArray().length==0 || passwd.getText().toString().toCharArray().length==0 || passwd1.getText().toString().toCharArray().length==0){
        return true;
    }
        else return false;
    }

    @FXML
    private void CreateUser(MouseEvent event) {
          if (normal.isSelected()){
              status = 0;
         }
         else if(admin.isSelected()){
             status = 1;
         }
         
           if (CheckState()){
            fields.setText("PLEASE FILL ALL THE FIELDS");
            
        }
        else 
        {
            fields.setText("Done");
           
                 
        // TODO add your handling code here:
        String firstname = fname.getText();
        String lastname = lname.getText();
        int telephone = Integer.parseInt(tel.getText());
        String address = add.getText();
        String username = uname.getText();
        String pwd = passwd.getText();
        String pwd1 = passwd1.getText();
        
        if (pwd.equals(pwd1)){
        Calendar cal = Calendar.getInstance();
       Formatter pp = new Formatter();
        
       try {
           DatabaseHelper db;
           db = new DatabaseHelper();
            try{
                db.setQuery("Insert into Login(First_name,LAST_NAME,TELEPHONE,ADDRESS,USER_ID,PD,STATUS,LAST_LOGIN) values('"+firstname+"','"+lastname+"',"+telephone+",'"+address+"','"+username+"','"+pwd+"',"+status+",'"+pp.format("%tF", cal.getTime())+"')");
                Dialogs.create().title("Successful").message("Thish User has succesfully been added with Username "+username+" and Password "+pwd+"").showInformation();
                pp.close();
                fname.setText("");
                lname.setText("");
                tel.setText("");
                add.setText("");
                uname.setText("");
                passwd.setText("");
                passwd1.setText("");
                normal.setSelected(true);
            } catch (SQLException e){
                Dialogs.create().title("Failed").message("This username is already taken").showInformation();
            }
          
       
       }catch(SQLException e){
         //  JOptionPane.showMessageDialog(null, e);
          e.printStackTrace();
       }
        }
        else
        {
            //JOptionPane.showMessageDialog(null, "The passwords entered do not match");
            Dialogs.create().title("Failed").message("The passwords entered do not match").showInformation();

        }
        
        
        }  
        
    }

    @FXML
    private void handleCancel(MouseEvent event) {
//        Stage stage = (Stage) cancel.getScene().getWindow();
//         stage.close();   
    }

    @FXML
    private void PhoneNumber(javafx.scene.input.KeyEvent event) {
        filterInput(event);
    }

    @Override
    public void setScreenParent(ScreensController pane) {
        manageAccountScreen = pane;
    }
    
}
