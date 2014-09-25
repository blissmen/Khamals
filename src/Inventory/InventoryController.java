/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Inventory;

import CustomersAndDebtors.Functions;
import General.ControlledScreen;
import General.DatabaseHelper;
import General.ScreensController;
import Sales.SalesController;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialog;
import org.controlsfx.dialog.Dialogs;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.net.URL;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static General.GeneralFunctions.toSentenceCase;
import static khamals.mainInterfaceController.ADD_MODIFY_ID;

/**
 * FXML Controller class
 *
 * @author Harvey
 */
public class InventoryController implements Initializable, ControlledScreen {

    protected static final int ADDING = 1, MODIFYING = 2, FIRST = 1, SECOND = 2;
    public static DatabaseHelper helper;
    public static InventoryManager manager;
    protected static ObservableList<String> categories = FXCollections.observableArrayList();
    protected static ObservableList<String> products = FXCollections.observableArrayList();
    @Nullable
    static String selectedItem, selectedCategory;
    static int action;
    public String query;
    public Object source;
    @FXML
    private ListView<String> categoryList;
    @FXML
    private ListView<String> productList;
    @FXML
    private Label nameLabel;
    @FXML
    private Label costLabel;
    @FXML
    private Label addedOnLabel;
    @FXML
    private Label qtyAddedLabel;
    private Label soldOnLabel;
    @FXML
    private Label qtySoldLabel;
    @FXML
    private Label minPriceLabel;
    @FXML
    private Label stockLabel;
    private String misc;
    private MenuItemsListener menuItemsListener;
    private ScreensController screen;
    private ArrayList<String> array;
    private ContextMenu categoryMenu, productMenu;
    private ArrayList<MenuItem> categoryItems, productItems;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            helper = new DatabaseHelper();
            manager = new InventoryManager();
            categoryList.setItems(categories);
            productList.setItems(products);
            action = ADDING;
            array = new ArrayList<>();

            menuItemsListener = new MenuItemsListener();

            //Instantiate menu
            categoryMenu = new ContextMenu();
            categoryItems = new ArrayList<>();
            productMenu = new ContextMenu();
            productItems = new ArrayList<>();

            //create menu items
            categoryItems.add(new MenuItem("Rename"));
            categoryItems.add(new MenuItem("Delete"));
            productItems.add(new MenuItem("Edit"));
            productItems.add(new MenuItem("Remove"));

            //Add them to the menu
            categoryMenu.getItems().addAll(categoryItems);
            productMenu.getItems().addAll(productItems);

            for (int i = 0; i < categoryMenu.getItems().size(); i++) {
                categoryMenu.getItems().get(i).setOnAction(menuItemsListener);
                productMenu.getItems().get(i).setOnAction(menuItemsListener);
            }
            manager.getAll();

        } catch (SQLException ex) {
            Logger.getLogger(InventoryController.class.getName()).log(Level.SEVERE, null, ex);
        }

        categoryList.setOnMouseClicked((MouseEvent event) -> {
            //dont show the menu when the list is empty andno itemwas selected
            if (event.getButton().equals(MouseButton.SECONDARY) && !categories.isEmpty()
                    && selectedCategory != null) {
                categoryMenu.show(categoryList, event.getScreenX(), event.getScreenY());
            }
        });

        categoryList.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
                    try {
                        manager.category = selectedCategory = newValue;
                        System.out.println("category new value = " + newValue);
                        selectedItem = manager.getProducts();
                        manager.getProductDetails();
                    } catch (SQLException ex) {
                        Logger.getLogger(InventoryController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
        );

        productList.setOnMouseClicked((MouseEvent event) -> {
            if (event.getButton().equals(MouseButton.SECONDARY) && !products.isEmpty()
                    && selectedItem != null) {
                productMenu.show(productList, event.getScreenX(), event.getScreenY());
            }
        });

        productList.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
                    try {//if the listener was triggered due to a change in the categories list
                        //selectedItem wil be null
                        if (newValue != null) {
                            selectedItem = newValue;
                            System.out.println("productold value " + oldValue + " new value " + newValue);
                        } else {//triggered by the categoriesList change
                            //Selected Item is null, so get the first tiem in the database
                            query = "SELECT NAME FROM PRODUCT WHERE CATEGORY = '" + selectedCategory + "'";
                            helper.setQuery(query);
                            helper.resultSet.first();
                            selectedItem = helper.resultSet.getString("NAME");
                            System.out.println("product old value " + oldValue + " selected item " + selectedItem);
                        }

                        manager.product = selectedItem;
                        manager.getProductDetails();
                    } catch (SQLException ex) {
                        Logger.getLogger(InventoryController.class.getName()).
                                log(Level.SEVERE, null, ex);
                    }
                }
        );
    }

    @FXML
    private void addProduct(ActionEvent event) {
        action = ADDING;
        AddModifyController.modObject.clear();
        screen.setScreen(ADD_MODIFY_ID);
    }

    @Override
    public void setScreenParent(ScreensController pane) {
        screen = pane;
    }

    @FXML
    private void addQuantity(ActionEvent event) {
        if (categories.isEmpty()) {
            Dialogs.create().title("Empty Inventory").
                    message("There are no products in the inventory.").
                    showInformation();
        } else {
            try {
                manager.addQuantity();
                SalesController.df.update();
            } catch (SQLException ex) {
                Logger.getLogger(InventoryController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void reduceQuantity(ActionEvent event) {
        if (categories.isEmpty()) {
            Dialogs.create().title("Empty Inventory").
                    message("There are no products in the inventory.").
                    showInformation();
        } else {
            try {
                manager.reduceQuantity();
                SalesController.df.update();
            } catch (SQLException ex) {
                Logger.getLogger(InventoryController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void editProduct(ActionEvent event) throws SQLException {
        if (categories.isEmpty()) {
            Dialogs.create().title("Empty Inventory").
                    message("There are no products in the inventory.").
                    showInformation();
        } else {
            editSelectedProduct();
            SalesController.df.update();
        }

    }

    @FXML
    private void removeProduct(ActionEvent event) throws SQLException {
        if (categories.isEmpty()) {
            //noinspection RedundantStringToString,RedundantStringToString
            Dialogs.create().title("Empty Inventory").masthead("The Selected Product is " + productList.getSelectionModel().getSelectedItem().toString()).
                    message("There are no products in the inventory." + productList.getSelectionModel().getSelectedItem().toString()).
                    showInformation();

        } else {

            manager.removeProduct();
            SalesController.df.update();
        }

    }

    @FXML
    private void search(@NotNull KeyEvent event) {
        //if a backspace is typed remove the last character from the arrayList

        //if the key typed is a backspace and the arraylist has some items
        //remove the lastly added
        if (event.getCharacter().equals("\b")) {
            if (array.size() > 0) {
                array.remove(array.size() - 1);
            }
        } else {//add it to the arrayList
            array.add(event.getCharacter());
        }

        if (array.size() > 0) {//make sure the searchbox is not empty

            try {
                misc = array.get(0);//we cant concatenate directly cuz misc is null at first
                for (int i = 1; i < array.size(); i++) {
                    misc = misc.concat(array.get(i));
                }

                misc = toSentenceCase(misc);
                System.out.println(misc);
                query = "SELECT * FROM PRODUCT WHERE NAME LIKE '%" + misc
                        + "%' OR CATEGORY LIKE '%" + misc + "%'";
                helper.setQuery(query);
                //System.out.println(query);

                if (helper.resultSet.first()) {
                    //set these values for a call to getProductdetails below
                    manager.category = helper.resultSet.getString("CATEGORY");
                    manager.product = misc = helper.resultSet.getString("NAME");
                    //System.out.println(misc);
                    //as usual set this in case an addition or subtraction is made
                    //System.out.println(helper.resultSet.isClosed());
                    ArrayList<String> arrayList = new ArrayList<>();
                    do {
                        arrayList.add(helper.resultSet.getString("NAME"));
                    } while (helper.resultSet.next());
                    products.clear();
                    products.setAll(arrayList);
                    productList.setItems(products);
                    manager.getProductDetails();
                }
            } catch (@NotNull IllegalStateException | SQLException ex) {
                Logger.getLogger(InventoryController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    private void editSelectedProduct() throws SQLException {
        action = MODIFYING;
        AddModifyController.modObject.modify();
        selectedCategory = manager.category;
        AddModifyController.modObject.fillComboBox();
        SalesController.df.update();
        screen.setScreen(ADD_MODIFY_ID);
    }

    private void renameCategory() throws SQLException {

        Optional<String> newName = Dialogs.create().title("Rename Category").
                masthead("You are about to rename " + selectedCategory).
                message("Enter the new name: ").showTextInput();

        if (newName.isPresent()) {
            query = "UPDATE PRODUCT SET CATEGORY = '" + toSentenceCase(newName.get())
                    + "' WHERE CATEGORY = '" + selectedCategory + "'";
            helper.setQuery(query);
            manager.getAll();
        }

    }

    protected final class InventoryManager {

        @Nullable
        private String category, product;

        public InventoryManager() throws SQLException {
            getAll();//populate the inventory
        }

        protected void getAll() throws SQLException {
            query = "SELECT DISTINCT CATEGORY FROM PRODUCT";
            helper.setQuery(query);
            if (helper.resultSet.first()) {
                selectedCategory = category = helper.resultSet.getString("CATEGORY");
                ArrayList<String> items = new ArrayList<>();
                do {
                    items.add(helper.resultSet.getString("CATEGORY"));
                } while (helper.resultSet.next());
                categories.clear();
                categories.addAll(items);
                categoryList.setItems(categories);

                product = selectedItem = getProducts();
                getProductDetails();
            } else {
                clear();
            }
        }

        @Nullable
        private String getProducts() throws SQLException {
            query = "SELECT * FROM PRODUCT WHERE CATEGORY = '" + category + "'";
            System.out.println(query);
            helper.setQuery(query);
            if (helper.resultSet.first()) {
                String prod;
                prod = helper.resultSet.getString("NAME");
                int col = helper.resultSet.findColumn("NAME");
                System.out.println(col);
                ArrayList<String> items = new ArrayList<>();
                do {
                    items.add(helper.resultSet.getString(col));
                } while (helper.resultSet.next());
                products.clear();
                products.addAll(items);
                productList.setItems(products);
                product = prod;
                return prod;
            }
            return null;
        }

        private void getProductDetails() throws SQLException {
            Functions jj = new Functions();
            query = "SELECT * FROM PRODUCT WHERE NAME = '" + product + "'";
            //System.out.println(query);
            helper.setQuery(query);
            if (helper.resultSet.first()) {
                //set the different values for the selected product
                nameLabel.setText(helper.resultSet.getString("NAME"));
                costLabel.setText(helper.resultSet.getString("COST_PRICE"));//int will be converted to string
                minPriceLabel.setText(helper.resultSet.getString("MIN_SELLING_PRICE"));//automatically says javadoc
                stockLabel.setText(helper.resultSet.getString("QUANTITY_AVAILABLE"));
                qtyAddedLabel.setText(helper.resultSet.getString("QUANTITY_ADDED"));

                //GET THE DATES on which the produt was added and sold
                query = "SELECT date(LASTLY_ADDED_ON) AS LASTLY_ADDED_ON, "
                        + "date(LASTLY_SOLD_ON) AS LASTLY_SOLD_ON "
                        + "FROM PRODUCT WHERE NAME = '" + product + "'";
                helper.setQuery(query);
                helper.resultSet.first();
                addedOnLabel.setText(helper.resultSet.getString("LASTLY_ADDED_ON"));

                if (helper.resultSet.getString("LASTLY_SOLD_ON") != null) {//never == null a string

                    //  soldOnLabel.setText(jj.getAllDData("date(Date)", "SALES_DETAILED").get(0).toString());
                    qtySoldLabel.setText(helper.resultSet.getString("LAST_QUANTITY_SOLD"));
                } else {
                    //  soldOnLabel.setText("Not yet sold");
                    qtySoldLabel.setText("None");
                }
            }
        }

        public void addQuantity() throws SQLException {
            try {
                int qty;
                Optional<String> quantity = Dialogs.create().masthead("You are about "
                        + "to manually add the quantity of ." + selectedItem)
                        .message("Enter quantity: ").showTextInput();
                if (quantity.isPresent()) {
                    qty = Integer.parseInt(quantity.get());
                    Calendar cal = Calendar.getInstance();
                    Formatter nn = new Formatter();
                    String date = nn.format("%tF", cal.getTime()).toString();

                    query = ("SELECT QUANTITY_AVAILABLE FROM PRODUCT WHERE NAME = '"
                            + selectedItem + "'");
                    helper.setQuery(query);
                    if (helper.resultSet.first()) {
                        qty = qty + helper.resultSet.getInt("QUANTITY_AVAILABLE");

                        //Previous quantity + new quantity; update the new time and quantity
                        query = "UPDATE PRODUCT SET QUANTITY_AVAILABLE = "
                                + qty + ", LASTLY_ADDED_ON = '" + date
                                + "', QUANTITY_ADDED = " + qty + "WHERE NAME = '"
                                + selectedItem + "'";
                        helper.setQuery(query);
                        SalesController.df.update();
                        manager.getProductDetails();
                    }
                }
            } catch (NumberFormatException e) {

            }
        }

        public void reduceQuantity() throws SQLException {
            try {
                int qty;
                Optional<String> quantity = Dialogs.create().
                        masthead("You are about to manually reduce the quantity of a product.")
                        .message("Enter quantity: ").showTextInput();
                if (quantity.isPresent()) {
                    qty = Integer.parseInt(quantity.get());

                    query = ("SELECT QUANTITY_AVAILABLE FROM PRODUCT WHERE NAME = '"
                            + selectedItem + "'");
                    helper.setQuery(query);
                    //make sure we dont get a negative quantity
                    if (helper.resultSet.first()) {
                        int qtyInDb = helper.resultSet.getInt("QUANTITY_AVAILABLE");
                        if (qtyInDb <= qty) {
                            qty = 0;
                        } else {
                            qty = qtyInDb - qty;
                        }
                        query = "UPDATE PRODUCT SET QUANTITY_AVAILABLE = "
                                + qty + "WHERE NAME = '"
                                + selectedItem + "'";
                        helper.setQuery(query);
                        SalesController.df.update();
                        manager.getProductDetails();
                    }
                }

            } catch (NumberFormatException e) {

            }
        }

        private void removeProduct() throws SQLException {
            Action response = Dialogs.create().title("Delete Product").masthead(" Here is the Item you are Deleting " + selectedItem).
                    message("Are you sure you want to delete this product?").
                    actions(Dialog.Actions.YES, Dialog.Actions.NO).
                    showConfirm();
            if (response.equals(Dialog.Actions.YES)) {
                query = "DELETE FROM PRODUCT WHERE NAME = '" + selectedItem + "'";
                helper.setQuery(query);
                SalesController.df.update();
                manager.category = selectedCategory;
                selectedItem = manager.getProducts();
                manager.product = selectedItem;
                manager.getAll();
            }
        }

        private void clear() {
            categories.clear();
            categoryList.setItems(categories);
            products.clear();
            productList.setItems(products);
            minPriceLabel.setText("-");
            nameLabel.setText("-");
            costLabel.setText("-");
            stockLabel.setText("-");
            addedOnLabel.setText("-");
            qtyAddedLabel.setText("-");
//            soldOnLabel.setText("-");
            qtySoldLabel.setText("-");
        }
    }

    private class MenuItemsListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(@NotNull ActionEvent event) {
            if (event.getSource().equals(categoryMenu.getItems().get(0))) {//Rename
                try {
                    renameCategory();
                } catch (SQLException ex) {
                    Logger.getLogger(InventoryController.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else if (event.getSource().equals(categoryMenu.getItems().get(1))) { //Delete
                Action response = Dialogs.create().title("Delete Category").
                        masthead("You are about to delete a "
                                + "whole category, perhaps having many products.").
                        message("Are you sure you want to do this?").
                        actions(Dialog.Actions.YES, Dialog.Actions.CANCEL).
                        showConfirm();
                if (response.equals(Dialog.Actions.YES)) {
                    try {
                        query = "DELETE FROM PRODUCT WHERE CATEGORY = '"
                                + selectedCategory + "'";
                        System.out.println(query);
                        helper.setQuery(query);
                        SalesController.df.update();
                        manager.getAll();
                    } catch (SQLException ex) {
                        Logger.getLogger(InventoryController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            } else if (event.getSource().equals(productMenu.getItems().get(0))) {//Edit
                try {
                    editSelectedProduct();
                    SalesController.df.update();
                } catch (SQLException ex) {
                    Logger.getLogger(InventoryController.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else if (event.getSource().equals(productMenu.getItems().get(1))) {//Remove
                try {
                    manager.removeProduct();
                    SalesController.df.update();
                } catch (SQLException ex) {
                    Logger.getLogger(InventoryController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }
}
