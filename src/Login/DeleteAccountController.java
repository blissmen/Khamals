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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialog;
import org.controlsfx.dialog.Dialogs;

/**
 * FXML Controller class
 *
 * @author Wilma
 */
public class DeleteAccountController implements Initializable, ControlledScreen {
    @FXML
    private ComboBox<String> userIDs;
    @FXML
    private TextField name;
    @FXML
    private TextField number;
    @FXML
    private TextField address;
    @FXML
    private Button delete;
    @FXML
    private Button close;
     @FXML
    private AnchorPane deleteAccount;
     
    DatabaseHelper db;
     ArrayList<String> array = new ArrayList<>();
    public static ScreensController deleteAccountScreen;
     
     
    public DeleteAccountController() throws SQLException, ClassNotFoundException {
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
            userIDs.getItems().clear();
            //userIDs.getItems().
            userIDs.getItems().addAll(array);
            userIDs.getSelectionModel().selectFirst();
        }
    }
     
      public void UserInfo() {
        try {
            String user = (String) userIDs.getSelectionModel().getSelectedItem();
            db.setQuery("Select FIRST_NAME, LAST_NAME from KHAMALS.LOGIN where USER_ID ='" + user + "'");
            if (db.resultSet.first()) {
                name.setText(db.resultSet.getString(1));
            } else {
                name.setText("");
            }

            db.setQuery("Select TELEPHONE from KHAMALS.LOGIN where USER_ID ='" + user + "'");
            if (db.resultSet.first()) {
                number.setText(db.resultSet.getString(1));
            } else {
                number.setText("");
            }

            db.setQuery("Select ADDRESS from KHAMALS.LOGIN where USER_ID ='" + user + "'");
            if (db.resultSet.first()) {
                address.setText(db.resultSet.getString(1));
            } else {
                address.setText("");
            }

        } catch (SQLException ex) {
            Logger.getLogger(DeleteAccountController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalStateException ex) {
            Logger.getLogger(DeleteAccountController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void SelectUser(ActionEvent event) {
        UserInfo();
    }

    @FXML
    private void DeleteUser(MouseEvent event) {
//          int i = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete All \n"
//                + "of the selected user's data?");
          Action confirm = Dialogs.create().title("Confirm Delete").message("Are you sure you want to delete ALL of the selected user's data?").showConfirm();
        if (confirm == Dialog.Actions.YES) {
            try {
                db.setQuery("Delete from KHAMALS.LOGIN where USER_ID ='" + userIDs.getSelectionModel().getSelectedItem() + "'");
                Dialogs.create().title("Successful").message("The account has been deleted!").showInformation();
                loadCombo();
            } catch (SQLException ex) {
                Logger.getLogger(DeleteAccountController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalStateException ex) {
                Logger.getLogger(DeleteAccountController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
       
    }

    @FXML
    private void close(MouseEvent event) {
//         Stage stage = (Stage) close.getScene().getWindow();
//         stage.close();
    }

    @Override
    public void setScreenParent(ScreensController pane) {
        deleteAccountScreen = pane;
    }
    
}
