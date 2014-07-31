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
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.controlsfx.dialog.Dialog;
import org.controlsfx.dialog.Dialogs;

/**
 * FXML Controller class
 *
 * @author Wilma
 */
public class ChangePasswordController implements Initializable, ControlledScreen {
    @FXML
    private ComboBox<String> username;
    @FXML
    private PasswordField oldpwd;
    @FXML
    private PasswordField newpwd;
    @FXML
    private PasswordField confirmpd;
    @FXML
    private Button apply;
    @FXML
    private Button cancel;
    
     DatabaseHelper db;
     ArrayList<String> array = new ArrayList<>();
    public ScreensController changePasswordScreen;
     
     
    public ChangePasswordController() throws SQLException, ClassNotFoundException {
        this.db = new DatabaseHelper();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            loadCombo();
        } catch (SQLException ex) {
            Logger.getLogger(DeleteAccountController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
    public void loadCombo() throws SQLException {
        db.setQuery("Select USER_ID from KHAMALS.LOGIN");
        if (db.resultSet.first()) {
            array.clear();
            do {
                array.add(db.resultSet.getString("USER_ID"));
            } while (db.resultSet.next());
            username.getItems().clear();
            //userIDs.getItems().
            username.getItems().addAll(array);
            username.getSelectionModel().selectFirst();
        }
    }
    
    public void changePwd(){
     try {
         String user = (String) username.getSelectionModel().getSelectedItem();
         String oldPwd = oldpwd.getText();
         String newPwd = newpwd.getText();
         db.setQuery("Select PD from KHAMALS.LOGIN where USER_ID ='" + user + "'");
         String pwd = db.resultSet.getString("PD");
         
         if (oldPwd.equals(pwd)){
               if (newPwd.equals(confirmpd.getText()) && newpwd.getLength() != 0){
                   db.setQuery("update KHAMALS.LOGIN set PD='" +newPwd+"' where USER_ID='" +user+ "'");
                   //JOptionPane.showMessageDialog(null, "Password Changed Successfully!");
                   Dialogs.create().title("Successful").message("Password Changed Successfully!").showInformation();
                   oldpwd.setText("");
                   newpwd.setText("");
                   confirmpd.setText("");
             
         }else{
            // JOptionPane.showMessageDialog(null, "The new passwords entered do not match");
              Dialogs.create().title("Failure").message("Please verify the new passwords entered. \n The passwords entered do not match or no password was entered.").showInformation();
         }   
   
         }else{
            // JOptionPane.showMessageDialog(null, "The old password you entered is wrong. Please input the right password.");
             Dialogs.create().title("Failure").message("The old password you entered is wrong. Please input the right password.").showInformation();
         }
         
       
     } catch (SQLException ex) {
         Logger.getLogger(ChangePasswordController.class.getName()).log(Level.SEVERE, null, ex);
     } catch (IllegalStateException ex) {
         Logger.getLogger(ChangePasswordController.class.getName()).log(Level.SEVERE, null, ex);
     }
     }

    @FXML
    private void apply(MouseEvent event) {
         org.controlsfx.control.action.Action confirm = Dialogs.create().title("Confirm Delete").message("Are you sure you want to change your password?").showConfirm();
        // int j = JOptionPane.showConfirmDialog(null, "Are you sure you want to change your password?");
        if (confirm == Dialog.Actions.YES){
            changePwd();
    }
    }

    @FXML
    private void cancel(MouseEvent event) {
        Stage stage = (Stage) cancel.getScene().getWindow();
         stage.close();
    }

    @Override
    public void setScreenParent(ScreensController pane) {
        changePasswordScreen = pane;
    }
    
}
