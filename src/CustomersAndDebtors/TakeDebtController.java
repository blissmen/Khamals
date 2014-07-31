/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomersAndDebtors;

import static CustomersAndDebtors.AddAccountController.helper;
import General.ControlledScreen;
import General.ScreensController;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Formatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import static khamals.mainInterfaceController.MAIN_CUSTOMER_ID;
import org.controlsfx.dialog.Dialogs;

/**
 * FXML Controller class
 *
 * @author Harvey
 */
public class TakeDebtController implements Initializable, ControlledScreen {
    
    @FXML
    private TextField id;
    @FXML
    private TextArea desc;
    @FXML
    private ComboBox<String> customer;
    @FXML
    private DatePicker deadline;
    @FXML
    private TextField amount;
    
    private ScreensController screen;
    String query;
    private Date date;
    String[] names;
    public static TakeDebt reload;

    /**
     * initialises the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        reload = new TakeDebt();
        try {
            reload.reload();
        } catch (SQLException | IllegalStateException e) {
            e.printStackTrace();
        }
        
        customer.valueProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (newValue != null) {
                try {
                    names = newValue.split("[ ]");
                    query = "SELECT ID FROM CUSTOMERS WHERE FIRST_NAME = '" + names[0]
                            + "' AND LAST_NAME = '" + names[names.length - 1] + "'";
                    helper.setQuery(query);
                    if (helper.resultSet.first()) {
                        id.setText(String.valueOf(helper.resultSet.getInt("ID")));
                    }
                    
                } catch (SQLException | IllegalStateException ex) {
                    Logger.getLogger(TakeDebtController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        });
    }
    
    @FXML
    public void takeDebt() {
        try {
            Calendar dateTime = Calendar.getInstance();
            Formatter formattedDate = new Formatter();
            formattedDate = formattedDate.format("%tF", dateTime);
            LocalDate date = deadline.getValue();
            System.out.println(date);
            query = "INSERT INTO DEBTORS (NAME, DATE_TACKEN, LATEST_DATE_OF_PAYMENT, "
                    + "AMOUNT, DESCRIPTION, ID) VALUES ('"
                    + customer.getSelectionModel().getSelectedItem() + "', '"
                    + formattedDate.toString() + "', '"
                    + date + "', "
                    + amount.getText() + ", '"
                    + desc.getText() + "', "
                    + id.getText() + ")";
            helper.setQuery(query);
            gotoMain();
        } catch (SQLException | IllegalStateException ex) {
            Dialogs.create().title("Empty Fields").
                    message("Please, fill all the required fields.").
                    showInformation();
            ex.printStackTrace();
        }
    }
    
    @FXML
    public void gotoMain() {
        screen.setScreen(MAIN_CUSTOMER_ID);
    }
    
    @Override
    public void setScreenParent(ScreensController pane) {
        screen = pane;
    }
    
    public class TakeDebt {
        
        public void reload() throws SQLException {
            query = "SELECT FIRST_NAME, LAST_NAME, ID FROM CUSTOMERS";
            helper.setQuery(query);
            if (helper.resultSet.first()) {
                id.setText(String.valueOf(helper.resultSet.getInt("ID")));
                ArrayList<String> customers = new ArrayList<>();
                do {
                    customers.add(helper.resultSet.getString("FIRST_NAME").
                            concat(" " + helper.resultSet.getString("LAST_NAME")));
                    int i = 0;
                    System.out.println(customers.get(i++));
                } while (helper.resultSet.next());
                customer.getItems().clear();
                customer.getItems().addAll(customers);
                customer.getSelectionModel().selectFirst();
            }
        }
    }
}
