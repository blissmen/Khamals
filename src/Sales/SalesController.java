/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sales;

import CustomersAndDebtors.Functions;
import General.ControlledScreen;
import General.DatabaseHelper;
import General.ScreensController;
import Login.Users;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Formatter;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import org.controlsfx.dialog.Dialogs;

/**
 * FXML Controller class
 *
 * @author blissmen
 */
public class SalesController implements Initializable, ControlledScreen {

    ArrayList time = new ArrayList();
    @FXML
    private TextField cash;
    @FXML
    private TextField disc2;
    @FXML
    private Button print;
    @FXML
    private Button submit;
    @FXML
    private TextField CustomerName;
    @FXML
    private Label CashierName;
    @FXML
    private ComboBox ProductName;
    @FXML
    private TextField Quantity;
    @FXML
    private TextField QuantityAvailable;
    @FXML
    private TextField Price;
    @FXML
    private TextField TotalPrice;
    @FXML
    private TextField Discount;
    @FXML
    private Button AddCart;
    @FXML
    private Label CPrice;
    @FXML
    private DatePicker date;
    @FXML
    private Label RecieptId;
    String sql = "";
    DatabaseHelper database;
    private ArrayList PName;
    private ArrayList PCode;
    private ArrayList Description;
    private ArrayList Cprice;
    private ArrayList SellingP;
    private ArrayList Quantit;
    private ObservableList itemName;
    @FXML
    private TextArea remark;
    @FXML
    private Label bal;
    @FXML
    private Label Stotal;
    @FXML
    private Label tot;
    @FXML
    private TableView Cart;
    @FXML
    private TableColumn idex;
    @FXML
    private TableColumn cd;
    @FXML
    private TableColumn nm;
    @FXML
    private TableColumn dsc;
    @FXML
    private TableColumn qt;
    @FXML
    private TableColumn p;
    @FXML
    private TableColumn disc;
    @FXML
    private TableColumn tp;
    ObservableList items;
    private int ip = 0;
    private MenuItem n;
    public int SUBTOTAL = 0;
    @FXML
    private Button emptycart;
    private SimpleDateFormat formatter;
    @FXML
    private Button remove;

    //Functions   
    public void handle(KeyEvent event) {
        KeyCode k = event.getCode();
        char c = event.getCharacter().charAt(0);
        if (!((c >= '0') && (c <= '9'))) {
                  // Dialogs.create().title("Wrong Input").
            //       message("You must enter only numbers here.").
            //     showInformation();
            event.consume();

        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            database = new DatabaseHelper();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        fillComboProduct(ProductName);
        this.date.setValue(LocalDate.now());
        emptycart.setDisable(true);
        this.AddCart.setDisable(true);
        submit.setDisable(true);
        print.setDisable(true);
    }

    @FXML
    private void PrintReciept(MouseEvent event) {
        try {
            Image im = Image.getInstance("khamals.png");
            FileOutputStream sa = new FileOutputStream("Reciept.pdf");
            im.scaleAbsolute(150f, 150f);
            // im.setAbsolutePosition(25,50);
            im.setBackgroundColor(BaseColor.PINK);
            Document doc = new Document(PageSize.A4, 50, 50, 50, 50);
            PdfWriter writer = PdfWriter.getInstance(doc, sa);
            PdfPTable table = new PdfPTable(6);
            String sh = "                                     Date and Time Of Sale : " + Calendar.getInstance().getTime().toGMTString() + "\n\n";
            String header = "                                                                 KHAMALS \n"
                    + "                                              DESIGNER AND SHOWROOM EXPERT \n "
                    + "                                  MOLYKO BUEA  LAST FLOOR ECKO BANK BUILDING\n ";
            String metda = "    INVOICE NUMBER :" + this.RecieptId.getText() + " \n "
                    + "  SALES PERSON ID :" + this.CashierName.getText()
                    + " \n   CUSTOMER NAME :" + this.CustomerName.getText() + "\n\n";

            PdfPCell cell = new PdfPCell(new Phrase("\nRECIEPT"));
            cell.setColspan(6);
            cell.setHorizontalAlignment(2);
            doc.open();
            doc.add(im);
            doc.add(cell);
            System.out.println(doc.leftMargin());
            doc.add(new Paragraph(header));
            doc.add(new Paragraph(sh));
            doc.add(new Paragraph(metda));
            table.addCell("S/N");
            table.addCell("PRODUCT NAME");
            table.addCell("DESCRIPTION");
            table.addCell("QUANTITY");
            table.addCell("UNIT PRICE");
            table.addCell("TOTAL PRICE");
            table.setTotalWidth(1000000);
            for (int i = 0; i < Cart.getItems().size(); i++) {
                table.addCell(this.idex.getCellData(i).toString());
                table.addCell(this.p.getCellData(i).toString());
                table.addCell(this.dsc.getCellData(i).toString());
                table.addCell(this.qt.getCellData(i).toString());
                table.addCell(this.p.getCellData(i).toString());
                table.addCell(this.tp.getCellData(i).toString());
            }

            String footer = " \n                                                                                 Total Amount:         " + tot.getText() + ""
                    + "                                                                                   SIGNATURES \n"
                    + "\nCUSTOMER                                      SALES PERSON ";

            doc.add(table);
            doc.add(new Paragraph(footer));
            doc.addCreationDate();
            doc.addAuthor(this.CashierName.getText());
            doc.close();

            Desktop.getDesktop().open(new File("Reciept.pdf"));

        } catch (Exception sd) {
            System.out.println("Error Message :" + sd.getMessage());
        } finally {

        }

    }

    @FXML
    private void ValidateSale(MouseEvent event) throws SQLException {
        if(Users.getName()!="")
        {
        Formatter format = new Formatter();
        int Tid = Integer.parseInt(RecieptId.getText());
        String named = this.CustomerName.getText();
        int dis = Integer.parseInt(disc2.getText());
        String cname = Users.getName();
        this.CashierName.setText(cname);
        String tdate = format.format("%tF", date.getValue()).toString();
        System.out.println(tot.getText());
        int amnt = Integer.parseInt(tot.getText());
        String re = remark.getText();
        tdate
                = sql = "Insert into SALES_GENERAL("
                + "remark,"
                + "transaction_id,"
                + "	DISCOUNT ,"
                + "total_amount"
                + ",Cashier_name"
                + ",CUSTOMER_NAME"
                + ",TRANSACTION_DATE"
                + ") Values('" + re + "'," + Tid + "," + dis + "," + amnt + ",'" + cname + "','" + named + "','" + tdate + "')";
        if (database.Query(sql) == 1) {
            for (int f = 0; f < this.Cart.getItems().size(); f++) {
                sql = "Insert into SALES_DETAILED("
                        + "transaction_id,"
                        + "PRODUCT_NAME,"
                        + "PRODUCT_CODE ,"
                        + "SELLING_PRICE,"
                        + "Date"
                        + ",QUANTITY"
                        + ",PRICE"
                        + ") "
                        + "Values(" + Tid + ",'" + this.nm.getCellData(f) + "','" + this.cd.getCellData(f) + "'," + this.p.getCellData(f) + ",'" + time.get(f) + "'," + this.qt.getCellData(f) + "," + this.tp.getCellData(f) + ")";
                database.Query(sql);
                // Cart.getItems().clear();
                print.setDisable(false);
                String sql2 = "select QUANTITY_AVAILABLE from Product where name ='" + this.ProductName.getSelectionModel().getSelectedItem().toString() + "'";
                String sql1 = "UPDATE KHAMALS.PRODUCT set QUANTITY_AVAILABLE =" + (Integer.parseInt(database.ExecuteQuery(sql2).get(0).toString())
                        - Integer.parseInt((String) this.qt.getCellData(f))) + " WHERE NAME='" + this.nm.getCellData(f) + "'";
                try {
                    database.Query(sql1);
                    System.out.println("Succese");

                } catch (Exception sd) {
                    System.out.println(sd.getMessage());
                }
            }
        }
    }
        else
        {
         
        Login.LoginFX.launch(Login.LoginFX.class);
        }
    }

    @FXML
    private void fetchdata(ActionEvent event) {
        if (ProductName.getSelectionModel().getSelectedIndex() != -1) {
            int Index = ProductName.getSelectionModel().getSelectedIndex();
            QuantityAvailable.setText(Quantit.get(Index).toString());
            CPrice.setText(SellingP.get(Index).toString());

        }

    }

    @FXML
    private void ValidateInput(KeyEvent event) {
        AddCart.setDisable(false);
        int Total = 0;
        handle(event);

        if ((Price.getText() != null) && (Quantity.getText() != null) || (Price.getText().length() > 0) || (Quantity.getText().length() > 0)) {

            try {
                if ((Discount.getText() == null)) {
                    Discount.setText("0");
                }

                Total = Integer.parseInt(Price.getText()) * Integer.parseInt(Quantity.getText()) - Integer.parseInt(Discount.getText()) * Integer.parseInt(Quantity.getText());

                this.TotalPrice.setText(String.valueOf(Total));
            } catch (NumberFormatException ss) {
                System.out.println(ss.getMessage());
            }
        } else {
            try {
                if ((Discount.getText() == null)) {
                    Discount.setText("0");

                }
                Price.setText("0");
                Quantity.setText("0");
                Total = Integer.parseInt(Price.getText()) * Integer.parseInt(Quantity.getText()) - Integer.parseInt(Discount.getText()) * Integer.parseInt(Quantity.getText());

                this.TotalPrice.setText(String.valueOf(Total));
            } catch (NumberFormatException ss) {
                System.out.println(ss.getMessage());
            }

        }
    }

    @FXML
    private void addCart(MouseEvent event) {
        emptycart.setDisable(false);
        submit.setDisable(false);
        idex.setCellValueFactory(new PropertyValueFactory("Inx"));
        nm.setCellValueFactory(new PropertyValueFactory("PName"));
        cd.setCellValueFactory(new PropertyValueFactory("Codes"));
        qt.setCellValueFactory(new PropertyValueFactory("Qty"));
        tp.setCellValueFactory(new PropertyValueFactory("Total"));
        p.setCellValueFactory(new PropertyValueFactory("Up"));
        disc.setCellValueFactory(new PropertyValueFactory("Disc"));
        dsc.setCellValueFactory(new PropertyValueFactory("Desc"));
        Calendar nn = Calendar.getInstance();

        if (Integer.parseInt(QuantityAvailable.getText()) < Integer.parseInt(Quantity.getText())) {
            Dialogs.create().title("Invalid amount").message("Quantity Entered Is greater than the amount in stck").showError();

        } else {

            Timestamp jh = new Timestamp(nn.getTime().getTime());
            time.add(jh);
            ip = ip + 1;
            items = FXCollections.observableArrayList();
            int index = ProductName.getSelectionModel().getSelectedIndex();
            ProductForSales data = new ProductForSales();
            data.PName.setValue(ProductName.getSelectionModel().getSelectedItem().toString());
            data.Codes.setValue(PCode.get(index).toString());
            data.Desc.setValue(this.Description.get(index).toString());

            data.Disc.setValue(this.Discount.getText());

            data.Qty.setValue(this.Quantity.getText());
            data.Up.setValue(Price.getText());
            data.Inx.setValue(String.valueOf(ip));
            n = new Menu("Delet transaction");

            dsc.setContextMenu(new ContextMenu(n));
            data.Total.setValue(TotalPrice.getText());
            //  System.out.println(TotalPrice.getText());
            items.add(data);
                 //System.out.println();  

            try {
              // Cart.getItems().setAll(items);

                Cart.getItems().addAll(items);
                try {
                    QuantityAvailable.setText(String.valueOf(Integer.parseInt(QuantityAvailable.getText()) - Integer.parseInt(Quantity.getText())));
                } catch (Exception bas) {
                    System.out.println("Error " + bas.getMessage());
                }
            } catch (Exception np) {
                System.out.println(np.getMessage());
            }
       // System.out.println(tloc.toString());

            SUBTOTAL = 0;

            //  System.out.println();
            for (int d = 0; d < Cart.getItems().size(); d++) {
                //  System.out.println(SUBTOTAL+" : "+tp.getCellData(d+1));
                SUBTOTAL = SUBTOTAL + Integer.parseInt(tp.getCellData(d).toString());
            }
            this.Stotal.setText(String.valueOf(SUBTOTAL));
            tot.setText(String.valueOf(SUBTOTAL));
            Quantity.setText("0");
            Discount.setText("0");
            Price.setText("0");
            TotalPrice.setText("0");
        }

    }

    private void fillComboProduct(ComboBox ProductName) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Functions function = new Functions();

        PName = function.getAllDData("NAME", "PRODUCT");
        PCode = function.getAllDData("Code", "PRODUCT");
        Description = function.getAllDData("DESCRIPTION", "PRODUCT");
        Cprice = function.getAllDData("COST_PRICE", "PRODUCT");
        SellingP = function.getAllDData("MIN_SELLING_PRICE", "PRODUCT");
        Quantit = function.getAllDData("QUANTITY_AVAILABLE", "PRODUCT");

        int TI = function.getdata(" MAX(TRANSACTION_ID)", "SALES_GENERAL");
        this.RecieptId.setText(String.valueOf(TI + 1));
        itemName = FXCollections.observableArrayList(PName);

        ProductName.setItems(itemName);

    }

    @FXML
    public void CalculateDisc(KeyEvent key) {
        handle(key);
        int res = 0;
        if (this.disc2.getText() != null) {
            tot.setText(String.valueOf(Integer.parseInt(Stotal.getText()) - Integer.parseInt(disc2.getText())));
        }

    }

    @FXML
    public void CalculateChange(KeyEvent key2) {
        handle(key2);
        if (this.cash.getText() != null && this.tot.getText() != null) {
            try {
                bal.setText(String.valueOf(-Integer.parseInt(tot.getText()) + Integer.parseInt(cash.getText())));
            } catch (Exception ld) {
                System.out.println(ld.getCause());
            }
        } else {
            bal.setText(String.valueOf(0));
        }
    }

    @FXML
    private void EmptyCart(MouseEvent event) {
        this.Cart.getItems().clear();

    }

    @FXML
    private void removeItem(MouseEvent event)
    {
               
           List item =  new ArrayList (Cart.getSelectionModel().getSelectedItems());  
                    items.removeAll(item);
                    Cart.getSelectionModel().clearSelection();

        
    }

    @Override
    public void setScreenParent(ScreensController pane) {
    }

}
