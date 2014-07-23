/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MAIN;
import Database.DatabaseHelper;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.imageio.ImageIO;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import ordersAndMeasurements.*;

/**
 *
 * @author Klexy
 */
public class mainInterfaceController implements Initializable {
    
    
   private  ObservableList<Order> orders;
    private final ObservableList<Order> fakeObserverlist;;
    //Variables that will be used all through the program
    private File filesJpg[];
    New_Order addOrderaddOrder;
    ViewOrderHistory orderHistory;
    
    //The next set of variables are for precaution
    boolean ordersTableLoaded;
    boolean ordersTableEdited;
    boolean customerMeasurementsLoaded;
    //the above boolean vaiables help to know when to load the Table View or when to reload it
    
    DatabaseHelper db;
    @FXML
    private Pagination pagination;
    @FXML
    private Button orderHistoryBtn;
                
    private Label label;
    @FXML
    private Button closeApplicationBtn;
    @FXML
    private Button loadImagesBtn;
    
    @FXML    
    private Button newOrderBtn;
    @FXML
    private Button removeOrderBtn;
    @FXML
    private Button printOrderListBtn;
    @FXML
    private AnchorPane mainAchorPane;
    @FXML
    private TableView<Order> ordersTable;
    @FXML
    private TableColumn<Order, String> customerNameColumn;
    @FXML
    private TableColumn<Order, String> productNameColumn;
    @FXML
    private TableColumn<Order, String> numberRequestedColumn;
    @FXML
    private TableColumn<Order, String> orderDateColumn;
    @FXML
    private TableColumn<Order, String> deliveryDateColumn;
    @FXML
    private TableColumn<Order, String> priceColumn;
    @FXML
    private TableColumn<Order, String> orderIDColumn;
    @FXML
    private ComboBox<ComboItem> customersComboBox;
    @FXML
    private Button getSelectedCustomersMeasurement;

    public mainInterfaceController() {
        this.fakeObserverlist = FXCollections.observableArrayList();
    }
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       try {
           //TO DO
           this.db = new DatabaseHelper();
       } catch (SQLException ex) {
           Logger.getLogger(mainInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
       }
        System.out.println("Entered the initialize function");
        this.ordersTableEdited = false;
        this.ordersTableLoaded = false;
        this.customerMeasurementsLoaded = false;
        populateTable();      
        //poplulate the orders table so that there should be no delay when tabs are 
        try {
           this.loadOrderHistory(null);
           //setResizable(false);
       } catch (IOException ex) {
           Logger.getLogger(mainInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
       } catch (InstantiationException ex) {
           Logger.getLogger(mainInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
       } catch (IllegalAccessException ex) {
           Logger.getLogger(mainInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
       } catch (SQLException ex) {
           Logger.getLogger(mainInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
       }
    }    
    
    public void populateTable(){
        this.customerNameColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("customerName"));
        this.productNameColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("productName"));
        this.numberRequestedColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("numberRequested"));
        this.orderDateColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("datePlaced"));
        this.deliveryDateColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("dateDue"));
        this.priceColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("price"));
        this.orderIDColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("orderID"));
        
        orders = FXCollections.observableArrayList();
        ordersTable.getItems().setAll(orders);                
    }
    
    public void loadCustomers() throws SQLException{
        if(!this.customerMeasurementsLoaded){
            String name;
            String queryString = "select first_name, last_name, id from customers, measurements where "
                    + "customers.id = measurements.customer_id";
            db.setQuery(queryString);
            for(int i=0; i<db.getRowCount(); i++){
            
                String firstName;
                firstName = db.getValueAt(i, 0).toString();
                String lastName;
                lastName = db.getValueAt(i, 1).toString();
                String customer_id;
                customer_id = db.getValueAt(i, 2).toString();
                name = firstName + " " + lastName;
                this.customersComboBox.getItems().add(new ComboItem(name, customer_id));
            
                System.out.printf("%s %s\n", firstName, lastName);
            }    
            
                this.customerMeasurementsLoaded = true;
        }       
        
        System.out.println("Finished loading customers");
       
    }
    /****************************************************************************************/
    //The codes that will come after this section are mainly responding to event listeners
    //The first on is starts immediatedly after
    /****************************************************************************************/
    
    //OnActionPerformed listener for the show order history button on the markup
    @FXML
    private void loadOrderHistory(Event event) throws IOException, InstantiationException, IllegalAccessException, SQLException {
        System.out.println("Printing the Order History");
        boolean recordExists = false;
        //Query statement 
        String query = "select customers.first_name, customers.last_name, product.name, orders.number_of_units"
                + ", orders.date_placed, orders.date_due, orders.total_cost, orders.order_number from customers, product, orders where"
                + "(orders.customer_id = customers.id and product.code = orders.product_code)"
                + " order by orders.order_number";
        //Query the database for required information
        db.setQuery(query);
        String tempName;
        for(int i=0; i<db.getRowCount(); i++){  
                Order data = new Order();
                tempName =  db.getValueAt(i, 0).toString();
                tempName += " " + db.getValueAt(i, 1).toString(); 
                data.customerName.setValue(tempName);
                data.productName.setValue(db.getValueAt(i, 2).toString());
                data.numberRequested.setValue(db.getValueAt(i, 3).toString());
                data.datePlaced.setValue(db.getValueAt(i, 4).toString());
                data.dateDue.setValue(db.getValueAt(i, 5).toString());
                data.price.setValue(db.getValueAt(i, 6).toString());
                data.orderID.setValue(db.getValueAt(i, 7).toString());
                
            for (Order order : orders) {
                if (data.getOrderID().equalsIgnoreCase(order.getOrderID())) {
                    recordExists = true;
                }
            }  
            if(!recordExists)
                orders.add(data);                
        }        
        
            
        //One Order          
                      
        if(!this.ordersTableEdited && !this.ordersTableLoaded){
            this.ordersTable.getItems().clear();
            this.ordersTable.getItems().setAll(orders);
            this.ordersTableLoaded = true;
        }
         
        if(this.ordersTableEdited){ 
                System.out.println("Now trying to load the newly added enteries");
                this.ordersTable.setItems(orders); 
        }  
    } 
    
    @FXML
    private void addNewOrder(Event event) throws IOException, InstantiationException, IllegalAccessException, SQLException, InterruptedException {
        
        System.out.println("you clicked me ");
        addOrderaddOrder = new New_Order();
        addOrderaddOrder.setDefaultCloseOperation(DISPOSE_ON_CLOSE);        
        addOrderaddOrder.setVisible(true);        
        addOrderaddOrder.toFront();
        //ordersTable.setItems(data);        
    }
    
    @FXML 
    private void viewOrderHistoryActionPerformed(Event event) throws IOException, InstantiationException, IllegalAccessException, SQLException{
        orderHistory = new ViewOrderHistory();
        orderHistory.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        // 
        this.orderHistory.setVisible(true);
        this.orderHistory.repaint();
        this.orderHistory.toFront();
    }
    
    //Next three fucntions are to call the measurement forms
    @FXML
    private void loadMeasurements1(Event event) throws IOException, InstantiationException, IllegalAccessException, SQLException, InterruptedException {
        ComboItem combo = this.customersComboBox.getSelectionModel().getSelectedItem();
        System.out.println(combo.getKey() + "   " + combo.getValue());
        ViewMeasurement viewMeasurement = new ViewMeasurement(combo.getKey(), combo.getValue());
        viewMeasurement.setVisible(true);
        viewMeasurement.toFront();
    }
    
    @FXML
    private void loadMeasurements(Event event) throws IOException, InstantiationException, IllegalAccessException, SQLException, InterruptedException {
        ViewMeasurement viewMeasurement = new ViewMeasurement();
        viewMeasurement.setVisible(true);
        viewMeasurement.toFront();
    }
    
    @FXML
    private void newMeasurement(Event event) throws IOException, InstantiationException, IllegalAccessException, SQLException, InterruptedException {
        NewMeasurement newMeasurement = new NewMeasurement();
        newMeasurement.setVisible(true);
        newMeasurement.toFront();
    }
    
    
    @FXML
    private void updateMeasurement(Event event) throws IOException, InstantiationException, IllegalAccessException, SQLException, InterruptedException {
        UpdateMeasurement newMeasurement = new UpdateMeasurement();
        newMeasurement.setVisible(true);
        newMeasurement.toFront();
    }
    
            
    @FXML
    private void refreshOrdersTableBtnActionPerformed(Event event) throws IOException, InstantiationException, IllegalAccessException, InterruptedException, SQLException {
        //this.orders.removeAll(orders);
        this.ordersTableEdited = true;
        this.loadOrderHistory(event);    
        //System.out.println("The number of orders is: " + orders.size());
        //System.out.println("order at 2 is: " + orders.get(2).getCustomerName());
        /*Order data1 = new Order();
        data1.customerName.setValue("Jones Wilson");
                data1.productName.setValue("Joupe Babilise");
                data1.numberRequested.setValue("3");
                data1.datePlaced.setValue("21-10-02");
                data1.dateDue.setValue("28-10-02");
                data1.price.setValue("10000");
                data1.orderID.setValue("76");
        orders.add(data1);*/
        
    }
    
    
    //the code from here has tree functions that simply load images to a paination object
    //the end shall be indicated as due
    @FXML
    private void selectImages(Event event) throws IOException, InstantiationException, IllegalAccessException {
        System.out.println("you clicked me ");
        openDirectoryChooser(Stage.class.newInstance());
    } 
    
    //the open directory function is that which opens the directory from which the 
    //pagination images will be loaded.
    //this function is called by the button
    private void openDirectoryChooser(Stage parent) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory =
                directoryChooser.showDialog(parent);
 
        if (selectedDirectory != null) {
            FilenameFilter filterJpg = new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return name.toLowerCase().endsWith(".jpg");
                }
            };
 
            filesJpg = selectedDirectory.listFiles(filterJpg);
            putImagesToPagination();
            
        }
        
    }
    
    public void putImagesToPagination(){
         int numberOfPages = filesJpg.length;
         pagination.setPageCount(this.filesJpg.length);
         
         pagination.setPageFactory(new Callback<Integer, Node>() {
            @Override
            public Node call(Integer pageIndex) {
                return createPage(pageIndex);
            }
        });
    }
    
    public VBox createPage(int index) {
 
        ImageView imageView = new ImageView();
        //pagination.setMaxHeight(350);
        //pagination.set
        File file = filesJpg[index];
        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            WritableImage image = SwingFXUtils.toFXImage(bufferedImage, null);
            imageView.setImage(image);
            imageView.setFitWidth(400);
            //imageView.setFitHeight(500);
            imageView.setPreserveRatio(true);
            imageView.setSmooth(true);
            imageView.setCache(true);
             System.out.println("fgv");
        } catch (IOException ex) {
            System.out.println("fgv");
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        VBox pageBox = new VBox();
        
        pageBox.getChildren().add(imageView);
        return pageBox;
    }
    
    //End of sub-section to display images on paginaton
     
    private void loadOrderHistory() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    //Order class
    @FXML
    public void loadDebtors()
    {
    
    }
     @FXML
    public void loadUserManagement()
    {
    User.ManageAccount.main(null);
    }
   
     @FXML
    public void loadMakeSales()
    {
    Sales.MainInterface.main(null);
    }
   
     @FXML
    public void loadRevokeSales()
    {
    Sales.MainInterface.main(null);
    }
    
    
    
 @FXML
    public void loadReport()
    {
    com.khamals.reports.ui.ReportsJFrame.main(null);
    }

@FXML
    public void loadADebt()
    {
    CustomersDebtors.Debtors.main(null);
    }
    @FXML
    public void loadPDebt()
    {
    CustomersDebtors.Debtors.main(null);
    }
    
 @FXML
    public void loadCustomers2()
    {
    CustomersDebtors.Customer.main(null);
        System.out.println("Hello");
    }
    
    @FXML
    public void loadInventory()
    {
        Inventory.DisplayInventory.main(null);
        System.out.println("Hello");
    }
      @FXML
    public void loadUserManagement2()
    {
    CustomersDebtors.Customer.main(null);
        System.out.println("Hello");
    }
    
      @FXML
    public void loadSalesReport()
    {
    CustomersDebtors.Customer.main(null);
        System.out.println("Hello");
    }
    
      @FXML
    public void loadInvetoryReport()
    {
       try {
           Inventory.KHAMALS.main(null);
       } catch (SQLException ex) {
           Logger.getLogger(mainInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
       }
        System.out.println("Hello");
    }
    
    
     @FXML
    public void loadAcustomer()
    {
    CustomersDebtors.Customer.main(null);
        System.out.println("Hello");
    }
      @FXML
    public void loadMCustmer()
    {
    CustomersDebtors.Customer.main(null);
        System.out.println("Hello");
    }
      @FXML
     void LoadStockRepot()
      {
          com.khamals.reports.menu.stock.QuantitiesInStock.main(null);
      }
      
     @FXML
     void LoadCashierRepot()
      {
          com.khamals.reports.menu.sales.StaffSalesQuery.main(null);
      }

}


