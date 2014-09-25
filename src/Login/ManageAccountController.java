/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Login;

import General.ControlledScreen;
import General.DatabaseHelper;
import General.ScreensController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import khamals.mainInterfaceController;
import org.controlsfx.dialog.Dialogs;
import org.jetbrains.annotations.NotNull;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Formatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FXML Controller class
 *
 * @author Wilma
 */
public class ManageAccountController implements Initializable, ControlledScreen {
    public static ScreensController manageAccountScreen;
    static FileOutputStream fp;
    int status = 2;
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
    private File ds;

    public static void Write(@NotNull File ff, String data) {
        try {

            fp = new FileOutputStream(ff);
            String newline = System.getProperty("line.separator");
            data = data + newline;
            byte[] output = data.getBytes();
            fp.write(output, 0, output.length);

        } catch (IOException ex) {
            Logger.getLogger(ManageAccountController.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            ds = new File("log.lst");
            fp = new FileOutputStream(ds);
            ToggleGroup group = new ToggleGroup();
            normal.setToggleGroup(group);
            admin.setToggleGroup(group);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ManageAccountController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void filterInput(@NotNull javafx.scene.input.KeyEvent evt) {
        //  int k = evt.getKeyCode();
        KeyCode g = evt.getCode();
        String k = evt.getCharacter();
        char c = k.charAt(0);
        if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_ENTER) || c == KeyEvent.VK_BACK_SPACE)) {
            //JOptionPane.showMessageDialog(null,"Phone Number Required Please");
            Dialogs.create().title("Wrong Input").message("Phone Number Required Please").showInformation();

            evt.consume();
        }

    }

    @SuppressWarnings("RedundantStringToString")
    public boolean CheckState() {
        if (fname.getText().toCharArray().length == 0 || lname.getText().toCharArray().length == 0 || tel.getText().toString().toCharArray().length == 0 || add.getText().toString().toCharArray().length == 0 || uname.getText().toString().toCharArray().length == 0 || passwd.getText().toString().toCharArray().length == 0 || passwd1.getText().toString().toCharArray().length == 0) {
            return true;
        } else return false;
    }

    @FXML
    private void CreateUser(MouseEvent event) {
        if (normal.isSelected()) {
            status = 0;
        } else if (admin.isSelected()) {
            status = 1;
        }

        if (CheckState()) {
            fields.setText("PLEASE FILL ALL THE FIELDS");

        } else {
            // fields.setText("Done");


            // TODO add your handling code here:
            String firstname = fname.getText();
            String lastname = lname.getText();
            int telephone = Integer.parseInt(tel.getText());
            String address = add.getText();
            String username = uname.getText();
            String pwd = passwd.getText();
            String pwd1 = passwd1.getText();

            if (pwd.equals(pwd1)) {
                Calendar cal = Calendar.getInstance();
                Formatter pp = new Formatter();

                try {
                    System.out.println(Users.getStatus());
                    if (Users.getStatus().equalsIgnoreCase("1")) {
                        if (Users.username.length() < 1)
                            mainInterfaceController.tan.changeTab();
                        else {
                            System.out.println(Users.getStatus());
                            DatabaseHelper db;
                            db = new DatabaseHelper();
                            try {

                                db.setQuery("Insert into Login(First_name,LAST_NAME,TELEPHONE,ADDRESS,USER_ID,PD,STATUS,LAST_LOGIN) values('" + firstname + "','" + lastname + "'," + telephone + ",'" + address + "','" + username + "','" + pwd + "'," + status + ",'" + pp.format("%tF", cal.getTime()) + "')");
                                Dialogs.create().title("Successful").message("This User has succesfully been added with Username " + username + " and Password " + pwd + "").showInformation();

                                Write(ds, "User :" + firstname + " " + lastname + " was created By " + Users.username + " At :" + Calendar.getInstance().getTime().toGMTString());
                                pp.close();
                                fname.setText("");
                                lname.setText("");
                                tel.setText("");
                                add.setText("");
                                uname.setText("");
                                passwd.setText("");
                                passwd1.setText("");
                                normal.setSelected(true);
                            } catch (SQLException e) {
                                Dialogs.create().title("Failed").message("This username is already taken").showError();
                                e.printStackTrace();
                            }
                        }
                    } else {
                        Login.ManageAccountController.Write(new File(Users.getName()), "Attempted to Create a new User account at on :" + Calendar.getInstance().getTime().toGMTString());
                        Dialogs.create().title("Security Voilation").masthead("Administrator only").message("Only an Adminisrator Can Perform this Function Login As One and Try").showError();


// setScreenParent(FXMLDocumentController.loginScreen);
                    }

                } catch (SQLException e) {
                    //  JOptionPane.showMessageDialog(null, e);
                    e.printStackTrace();
                }
            } else {
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
    private void PhoneNumber(@NotNull javafx.scene.input.KeyEvent event) {
        filterInput(event);
    }

    @Override
    public void setScreenParent(ScreensController pane) {
        manageAccountScreen = pane;
    }

}
