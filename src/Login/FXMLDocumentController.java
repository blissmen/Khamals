/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Login;

import General.ControlledScreen;
import General.DatabaseHelper;
import General.ScreensController;
import java.net.URL;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.controlsfx.dialog.Dialogs;

/**
 * FXML Controller class
 *
 * @author Wilma
 */
public class FXMLDocumentController implements Initializable, ControlledScreen {
    @FXML
    private TextField username;
    @FXML
    private Group radioBtnGrp;
    @FXML
    private RadioButton temp;
    @FXML
    private RadioButton perm;
    @FXML
    private Button login;
    @FXML
    private Button cancel;
    @FXML
    private PasswordField pwd;
    int time;
    public static ScreensController loginScreen;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ToggleGroup group = new ToggleGroup();
        temp.setToggleGroup(group);
        perm.setToggleGroup(group);
    }    

    @FXML
    private void handl(MouseEvent event) throws SQLException, ClassNotFoundException {
        
        if (temp.isSelected()){
             time = 10;
        }
        else if (perm.isSelected()){
             time = 180;
        }
        Calendar lendar  = Calendar.getInstance();
//         System.out.println(""+lendar.getTime().toString().substring(11, 19));
     //    System.out.println(lendar.getTime().getMinutes());
        Date bb = lendar.getTime();
        bb.setMinutes(lendar.getTime().getMinutes()+time);
        System.out.println(lendar.getTime()+": "+bb);
        
        if (username.getLength() != 0){
        DatabaseHelper dat;
        String pass;
        dat = new DatabaseHelper();
        String cmd = "Select  FIRST_NAME, LAST_NAME, STATUS, PD FROM KHAMALS.LOGIN where USER_ID='"+username.getText()+"'";
        dat.setQuery(cmd);
        if(dat.getResultSet().first() && dat.getResultSet().getString(1) != null)
        {
            
            pass = dat.getResultSet().getString("PD");
            System.out.println(pass);
            if(pwd.getText().equals(pass))
            {
                Stage stage = (Stage) cancel.getScene().getWindow();
                stage.close();
                Dialogs.create().title("Login Successful").message("You have been logged in Succesfully!").showInformation();
                Users.setName(username.getText());
                Users.setPwdName(pass);
                Users.setFirstName(dat.getResultSet().getString(1));
                Users.setLastName(dat.getResultSet().getString(2));
                Users.setStatus(dat.getResultSet().getString(3));
                Users.setStartTime(lendar.getTime());
                Users.setStopTime(bb);
                System.out.println(time);
                System.out.println(Users.startTime);
                System.out.println(Users.stopTime);
                System.out.println(Users.Available(username.getText()));
            }
            else 
            {
                //JOptionPane.showMessageDialog(null, "The username or password entered is incorrect!");
                Dialogs.create().title("Login Failed").message("The username or password entered is incorrect!").showInformation();
                System.out.println("Login failure");
                System.out.println(pass+":"+pwd.getText());
            }
        }
        else{
            Dialogs.create().title("Login Failed").message("The username or password entered is incorrect!").showInformation();
        }
        }
        else
        {
           Dialogs.create().title("Login Failed").message("The username or password entered is incorrect!").showInformation(); 
        }
        
        String user = username.getText();
        String passwd = pwd.getText();
        
        
// TODO add your handling code here:
    }
    
    @FXML
    private void cancel(MouseEvent event) {
//        Stage stage = (Stage) cancel.getScene().getWindow();
//         stage.close();
    }

    @Override
    public void setScreenParent(ScreensController pane) {
        loginScreen = pane;
    }
    
}
