/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Inventory;

import General.ControlledScreen;
import General.ScreensController;
import static Inventory.InventoryController.ADDING;
import static Inventory.InventoryController.action;
import static Inventory.InventoryController.helper;
import static Inventory.InventoryController.selectedCategory;
import static Inventory.InventoryController.selectedItem;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Formatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import static khamals.mainInterfaceController.INVENTORY_SCREEN_ID;
import org.controlsfx.dialog.Dialogs;

/**
 * FXML Controller class
 *
 * @author Harvey
 */
public class AddModifyController implements Initializable, ControlledScreen {
    @FXML
    private TextField nameField;
    @FXML
    private TextField productCode;
    @FXML
    private TextArea description;
    @FXML
    private TextField costField;
    @FXML
    private TextField minPriceField;
    @FXML
    private TextField qtyField;
    @FXML
    private ComboBox<String> categoryBox;
    
    //manually declared variables
    private String name;
    private String code;
    private String newCategory;
    private String desc;
    private int cost;
    private int minPrice;
    private int quantity;
    private String query;
    private String prevName;
    public static ModifyProduct modObject;
    private ScreensController screen;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       modObject = new AddModifyController.ModifyProduct();
        try {
            modObject.fillComboBox();
        } catch (SQLException ex) {
            Logger.getLogger(AddModifyController.class.getName()).log(Level.SEVERE, null, ex);
        }

        categoryBox.valueProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            selectedCategory = newValue;
        });
    }

    @FXML
    private void goHome(ActionEvent event) {
        screen.setScreen(INVENTORY_SCREEN_ID);
    }

    @FXML
    private void save(ActionEvent event) {
        try {
            addModifyProduct();
        } catch (SQLException ex) {
            Logger.getLogger(AddModifyController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void setScreenParent(ScreensController pane) {
        screen = pane;
    }

    private void addModifyProduct() throws SQLException {
        try {
            //read the values from the fields that are common to both adding and modifying
            name = nameField.getText();
            name = toSentenceCase(name);
            code = productCode.getText();
            newCategory = getCategory();
            if (newCategory != null) {//you dont compare to null with ==
            }else{
                return;
            }
            newCategory = toSentenceCase(newCategory);
            desc = description.getText();
            cost = Integer.parseInt(costField.getText());
            minPrice = Integer.parseInt(minPriceField.getText());

            if (action == ADDING) {
                //read the quantity of adding a new product
                quantity = Integer.parseInt(qtyField.getText());
                Calendar calendar = Calendar.getInstance();
                Timestamp timestamp = new Timestamp(calendar.getTimeInMillis());
                Formatter orm = new Formatter();
                String date = orm.format("%tF", timestamp).toString();
                query = "INSERT INTO PRODUCT (CODE, NAME, CATEGORY, DESCRIPTION,"
                        + "QUANTITY_AVAILABLE, COST_PRICE, MIN_SELLING_PRICE, "
                        + "LASTLY_ADDED_ON, QUANTITY_ADDED) VALUES("
                        + "'" + code + "', "
                        + "'" + name + "', "
                        + "'" + newCategory + "', "
                        + "'" + desc + "', "
                        + quantity + ", "
                        + cost + ", "
                        + minPrice + ", "
                        + "'" + date + "', "
                        + quantity + ")";
            } else {

                query = "UPDATE PRODUCT SET CODE = '" + code + "', "
                        + "NAME = '" + name + "', "
                        + "CATEGORY = '" + newCategory + "', "
                        + "DESCRIPTION = '" + desc + "', "
                        + "COST_PRICE = " + cost + ", "
                        + "MIN_SELLING_PRICE = " + minPrice
                        + " WHERE NAME = '" + (prevName.equals(name)
                        ? name : prevName) + "'";
            }
        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
            Dialogs.create().title("Empty fields").
                    masthead("Optional field - Description").
                    message("Please fill in all the required fields").showError();
            return;
        }

        try {
            helper.setQuery(query);
            if (action == ADDING) {
                Dialogs.create().title("Product Added").
                        message(name + " was added successfully to the "
                                + "list of products").showInformation();
            } else {
                Dialogs.create().title("Product Modified").
                        message("The product was modified successfully").
                        showInformation();
                action = ADDING;
            }
            //Reinitialize all inventory gui components
            InventoryController.manager.getAll();
            //clear all the text fields
            modObject.clear();
            //Return to the inventory
            screen.setScreen(INVENTORY_SCREEN_ID);
        } catch (Exception e) {
            //if this catch doesnot create a duplicate entry error (primary key voilation)
            //then the error is something else
            if (InventoryController.products.size() > 0) {
                switch (action) {
                    case ADDING:
                        query = "SELECT CODE, NAME FROM PRODUCT";
                        helper.setQuery(query);
//                      if not adding the first product...; This may seems redundant at first
                        if (helper.resultSet.first()) {
                            do {
                                Dialogs.create().title("Duplicate name").
                                        masthead(helper.resultSet.getString("CODE") + " - "
                                                + helper.resultSet.getString("NAME")).
                                        message("Product code "
                                                + code + " already used for another product. Try "
                                                + "entering a different code").showError();
                                break;//do while break
                            } while (helper.resultSet.next());
                            break;//case break
                        }
                    default:
                        query = "SELECT CODE, NAME FROM PRODUCT WHERE CODE <> '" + code + "'";
                        helper.setQuery(query);

                        if (helper.resultSet.first()) {
                            do {
                                if (code.equals(helper.resultSet.getString("CODE"))) {
                                    Dialogs.create().title("Duplicate name").
                                            masthead(helper.resultSet.getString("CODE") + " - "
                                                    + helper.resultSet.getString("NAME")).
                                            message("Product code "
                                                    + code + " already used for another product. Try "
                                                    + "entering a different code").showError();
                                    break;//do while break
                                }
                            } while (helper.resultSet.next());
                        }
                }
            }
            e.printStackTrace();
        }
    }

    @FXML
    public void handle(javafx.scene.input.KeyEvent event) {
        KeyCode k = event.getCode();
        char c = event.getCharacter().charAt(0);
        if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_TAB
                || c == KeyEvent.VK_ENTER || c == KeyEvent.VK_BACK_SPACE))) {
            Dialogs.create().title("Wrong Input").
                    message("You must enter only numbers here.").
                    showInformation();
            event.consume();
        }
    }

    public String getCategory() {
        //if a value was selecte or a value was typed return that value
        if (categoryBox.getValue() != null
                && !categoryBox.getValue().isEmpty()) {
            return categoryBox.getValue();
        }
        Dialogs.create().title("Empty category").
                message("You did not choose a category").showInformation();
        return null;
    }

    public static String toSentenceCase(String string) {
        //return a string that starts with an uppercase letter: sentence case that is.
        String[] temp;
        temp = string.toLowerCase().split("[ ]");
        String retrn = "";
        for (String next : temp) {
            retrn = retrn.concat(next.substring(0, 1).toUpperCase().
                    concat(next.substring(1))).concat(" ");
        }
        retrn = retrn.trim();
        return retrn;
    }

    protected final class ModifyProduct {

        public void modify() {

            try {
                query = "SELECT CODE, NAME, CATEGORY, DESCRIPTION, COST_PRICE, "
                        + "MIN_SELLING_PRICE, QUANTITY_AVAILABLE "
                        + "FROM PRODUCT WHERE NAME = '" + selectedItem + "'";
                helper.setQuery(query);
                nameField.setText(helper.resultSet.getString("NAME"));
                productCode.setText(helper.resultSet.getString("CODE"));
                description.setText(helper.resultSet.getString("DESCRIPTION"));
                costField.setText(helper.resultSet.getString("COST_PRICE"));
                minPriceField.setText(helper.resultSet.getString("MIN_SELLING_PRICE"));
                qtyField.setText(helper.resultSet.getString("QUANTITY_AVAILABLE"));
                qtyField.setEditable(false);
                prevName = nameField.getText();
            } catch (SQLException ex) {
                Logger.getLogger(AddModifyController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalStateException ex) {
                Logger.getLogger(AddModifyController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        public void fillComboBox() throws SQLException {
//get the different categories
            ArrayList<String> cats = new ArrayList<>();
            String tempCategory = selectedCategory;
            query = "SELECT DISTINCT CATEGORY FROM PRODUCT";
            helper.setQuery(query);
            //selectedCategory becomes null after the executing this
            //if there were items in the combobox
            //so store its value in a temporary variable
            categoryBox.getItems().clear();
            if (helper.resultSet.first()) {
                do {
                    cats.add(helper.resultSet.getString("CATEGORY"));
                } while (helper.resultSet.next());
                categoryBox.getItems().addAll(cats);
                selectedCategory = tempCategory;
                categoryBox.getSelectionModel().select(selectedCategory);
//                System.out.println("selected: " + selectedCategory);
                selectedCategory = newCategory;
            }

        }

        public void clear() {
            nameField.setText(null);
            productCode.setText(null);
            description.setText(null);
            costField.setText(null);
            minPriceField.setText(null);
            qtyField.setText(null);
            categoryBox.setValue(null);
        }

    }

}
