/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Report;

import CustomersAndDebtors.Functions;
import General.ControlledScreen;
import General.ScreensController;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Formatter;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author blissmen
 */
public class ReportController implements Initializable, ControlledScreen {
    @FXML
    private BarChart<?, ?> chart;
    @FXML
    private RadioButton cas;
    @FXML
    private RadioButton sal;
    @FXML
    private Button plot;
    @FXML
    private ComboBox comb;
    @FXML
    private TableView tabsl;
    @FXML
    private RadioButton CashierReport;
    @FXML
    private Pane panelC;
    @FXML
    private CheckBox QuantitySold;
    @FXML
    private RadioButton SalesReport;
    @FXML
    private Pane panelS;
    @FXML
    private CheckBox SGood;
    @FXML
    private RadioButton InventoryReport;
    @FXML
    private Pane panelI;
    @FXML
    private DatePicker Stdate;
    @FXML
    private DatePicker Edate;
    @FXML
    private Pane PanelC1;
    @FXML
    private CheckBox QuantitySold1;
    @FXML
    private RadioButton CashieReport1;
    @FXML
    private DatePicker ToDate1;
    @FXML
    private DatePicker FromDate1;
    @FXML
    private Pane panelI1;
    @FXML
    private CheckBox IName1;
    @FXML
    private CheckBox IDate1;
    @FXML
    private CheckBox IQuantity1;
    @FXML
    private CheckBox IUPrice1;
    @FXML
    private CheckBox IDiscount1;
    @FXML
    private RadioButton InventoryReport1;
    @FXML
    private Pane panelS1;
    @FXML
    private CheckBox SGoodSold1;
    @FXML
    private CheckBox SAmount1;
    @FXML
    private CheckBox SID1;
    @FXML
    private CheckBox SDate1;
    @FXML
    private CheckBox SQuantity1;
    @FXML
    private CheckBox cusname;
    @FXML
    private RadioButton SalesReport1;
    @FXML
    private Button printer;
    @FXML
    private Pane panetST1;
    @FXML
    private CheckBox STAmountTSold1;
    @FXML
    private CheckBox STDate1;
    @FXML
    private CheckBox STIDate1;
    @FXML
    private CheckBox STUPrice1;
    @FXML
    private RadioButton StockReport1;
    @FXML
    private RadioButton fullReport1;
     ObservableList info;
     private ArrayList re = new ArrayList();
    private ArrayList employees = new ArrayList();
    private ArrayList AAttributes = new ArrayList();
    private ArrayList red;
    private ArrayList IAttributes = new ArrayList();
    private String amount;
    private ArrayList Result;
    private int index;
      private int index2=0; 
    private String Start;
    private String end;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       this.ToDate1.setValue(LocalDate.now());
        FromDate1.setValue(LocalDate.now());
        Stdate.setValue(LocalDate.now());
        Edate.setValue(LocalDate.now());
        ToggleGroup group = new ToggleGroup();
        StockReport1.setToggleGroup(group);
        CashieReport1.setToggleGroup(group);
        InventoryReport1.setToggleGroup(group);
        SalesReport1.setToggleGroup(group);
        ToggleGroup group0 = new ToggleGroup();
       
        SalesReport.setToggleGroup(group0);
        CashierReport.setToggleGroup(group0);
        InventoryReport.setToggleGroup(group0);
        ToggleGroup group2 = new ToggleGroup();
        cas.setToggleGroup(group2);
        sal.setToggleGroup(group2);
    }    

    @FXML
    private void fillComBo(MouseEvent event) {
    
       Functions fn = new Functions();
   RadioButton nn = (RadioButton) event.getSource();
   String idcas =cas.getId();
  if(nn.isSelected())
  {
   switch(nn.getId())
   {
       case "cas":
           ArrayList Cash = fn.getAllDData("USER_ID", "LOGIN");
           comb.getSelectionModel().clearAndSelect(0);
           comb.getItems().setAll(Cash);
                       comb.getSelectionModel().select(0);
                index2 =1;
           break;
       case "sal":
           ArrayList Casx = fn.getAllDData("Name", "Product");
           comb.getSelectionModel().clearAndSelect(0);
           comb.getItems().setAll(Casx);
           comb.getSelectionModel().select(0);
           index2 =2;
           break;
           default:
               break;
   }
   
   }
    
    }

    @FXML
    private void PlotGraph(MouseEvent event) {
          Functions fn = new Functions();
        if(index2!=0)
        {
            if(index2==2)
          salesMonthly(comb.getSelectionModel().getSelectedItem().toString());
            else
            {
                try {
                    //"+comb.getSelectionModel().getSelectedItem().toString()+"
                    String sql ="select TRANSACTION_ID from SALES_GENERAL where CASHIER_NAME ='"+comb.getSelectionModel().getSelectedItem().toString()+"'";
                    ArrayList results = Functions.database.ExecuteQuery(sql);
                    //System.out.println(results);
                    salesMonthly(results);
                } catch (SQLException ex) {
                    System.err.println(ex.getMessage());
                }
            }
        }
        
    
    }

    @FXML
    private void ActivatePanel(MouseEvent event) {
    activate(1);
         index = 10;
    }

    @FXML
    private void activatePanelS(MouseEvent event) {
        activate(2);
             index = 20;
    }

    @FXML
    private void ActivatePanelI(MouseEvent event) {
    index = 30;
    }

    @FXML
    private void populateTableR(MouseEvent event) {
          Formatter ss = new Formatter();
       Formatter cc = new Formatter();
//        java.sql.Date Start = new Date(Stdate.getValue().);
         Start =ss.format("%tF", Stdate.getValue()).toString();
         end= ss.format("%tF", Edate.getValue()).toString();
        ArrayList item = GetSelected(index);
        fetch2Info(index, item);
 
    }

    @FXML
    private void activatePanel1(MouseEvent event) {
       activate(5);
        index = 1;
 
    }

    @FXML
    private void activatePanelI1(MouseEvent event) {
       activate(7);
        index = 3;
 
    
    }

    @FXML
    private void activatePanelS1(MouseEvent event) {
           activate(6);
        index = 2;
    }

    @FXML
    private void PrintReport(MouseEvent event) {
    ArrayList Items = GetSelected(index);
            System.out.println(Items.toString());
            try {
             
                
                fetchInfo(index, Items);
            } catch (Exception d) {
                System.out.println(d.getMessage());
            }
        

    }

    @FXML
    private void activatePanelSt1(MouseEvent event) {
    if (StockReport1.isSelected()) {
            activate(8);
            index = 4;
    
    }
    }
    private void activate(int i) {

        switch (i) {
            case 1:
                panelC.setDisable(false);
                panelS.setDisable(true);
     //           panelST.setDisable(true);
                panelI.setDisable(true);

                break;

            case 2:

                panelC.setDisable(true);
                panelS.setDisable(false);
       //         panelST.setDisable(true);
                panelI.setDisable(true);

                break;

            case 3:
                panelI.setDisable(false);
                panelC.setDisable(true);
                panelS.setDisable(true);
         //       panelST.setDisable(true);

                break;

            case 4:
           //     panelST.setDisable(false);
                panelI.setDisable(true);
                panelC.setDisable(true);
                panelS.setDisable(true);

                break;

            case 5:
                PanelC1.setDisable(false);
                panelI1.setDisable(true);

                panelS1.setDisable(true);
                panetST1.setDisable(true);

                break;
            case 6:
                panelS1.setDisable(false);
                panelI1.setDisable(true);
                PanelC1.setDisable(true);

                panetST1.setDisable(true);

                break;
            case 7:
                panelI1.setDisable(false);

                PanelC1.setDisable(true);
                panelS1.setDisable(true);
                panetST1.setDisable(true);

                break;
            case 8:
                panetST1.setDisable(false);
                panelI1.setDisable(true);
                PanelC1.setDisable(true);
                panelS1.setDisable(true);

                break;
            default:
                break;

        }

    }
    
    
public ArrayList GetSelected(int index) {
        ArrayList Items = null;
        switch (index) {
            case 1:
                Items = new ArrayList(PanelC1.getChildren());
                break;
            case 2:
                Items = new ArrayList(panelS1.getChildren());
                break;

            case 3:
                Items = new ArrayList(panelI1.getChildren());
                break;
            case 4:
                Items = new ArrayList(panetST1.getChildren());
                break;
                 case 10:
                Items = new ArrayList(panelC.getChildren());
                break;
            case 20:
                Items = new ArrayList(panelS.getChildren());
                break;

            case 30:
                Items = new ArrayList(panelI.getChildren());
                break;
            case 40:
                //Items = new ArrayList(panelST.getChildren());
                break;

            default:
                break;

        }
        ArrayList Names = new ArrayList();
        for (int i = 0; i < Items.size(); i++) {
            CheckBox it = (CheckBox) Items.get(i);
            if (it.isSelected()) {
                Names.add(it.getId());

            }
        }
        return Names;

    }
   private void fetchInfo(int index, ArrayList Items) throws FileNotFoundException, DocumentException, IOException, SQLException {
        ArrayList Attributes = new ArrayList();
        Functions functions = new Functions();
        //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        switch (index) {
            case 1:
                System.out.println("Hello");
                for (int i = 0; i < Items.size(); i++) {
                    if (Items.get(i).toString().equalsIgnoreCase("QuantitySold1")) {
                        Attributes.add("SUM(Quantity)");
                    }

                }
                if (Attributes.size() == 0) {
                    FileOutputStream fs = new FileOutputStream("report.pdf");
                    Document doc = new Document();
                    PdfWriter writer = PdfWriter.getInstance(doc, fs);
                    String sh = "                                     Date and Time Drawn Up: " + Calendar.getInstance().getTime().toGMTString() + "\n\n";
                    String header = "                                                                 KHAMALS Cashier Report\n"
                            + "                                              DESIGNER AND SHOWROOM EXPERT \n "
                            + "                                  MOLYKO BUEA  LAST FLOOR ECKO BANK BUILDING\n ";
                    doc.open();
                    doc.add(new Paragraph(header));
                    doc.add(new Paragraph(sh));
                    PdfPTable table = new PdfPTable(4);

                    table.addCell("First name");
                    table.addCell("Last Name");
                    table.addCell("Telephone Number");
                    table.addCell("ADDRESS");
                    table.setHeaderRows(1);

                  //  Functions ds = new Functions();
                    employees = functions.getAllDData("USER_ID", "LOGIN");
                    if (employees.size() > 0) {
                        for (int t = 0; t < employees.size(); t++) {
                            String sql = "select FIRST_NAME,LAST_NAME,Telephone,ADDRESS from Login where USER_ID='" + employees.get(t).toString() + "'";
                            try {
                                re = Functions.database.ExecuteQuery(sql);
                                table.addCell(re.get(0).toString());
                                table.addCell(re.get(1).toString());
                                table.addCell(re.get(2).toString());
                                table.addCell(re.get(3).toString());
                            } catch (SQLException ex) {
                                System.out.println(ex.getMessage());
                            }
                        }
                    }
                    doc.add(table);
                    doc.addCreationDate();
                    doc.close();
                    Desktop.getDesktop().open(new File("report.pdf"));
                    System.out.println("Me" + re.toString());
                } else {

                    FileOutputStream fs = new FileOutputStream("report.pdf");
                    Document doc = new Document();
                    PdfWriter writer = PdfWriter.getInstance(doc, fs);
                    String sh = "                                     Date and Time Drawn Up: " + Calendar.getInstance().getTime().toGMTString() + "\n\n";
                    String header = "                                                                 KHAMALS Cashier Report\n"
                            + "                                              DESIGNER AND SHOWROOM EXPERT \n "
                            + "                                  MOLYKO BUEA  LAST FLOOR ECKO BANK BUILDING\n ";
                    doc.open();
                    doc.add(new Paragraph(header));
                    doc.add(new Paragraph(sh));
                    PdfPTable table = new PdfPTable(5);

                    table.addCell("First name");
                    table.addCell("Last Name");
                    table.addCell("Telephone Number");
                    table.addCell("ADDRESS");
                    table.addCell("Total Amount Sold");
                    table.setHeaderRows(1);

                    //Functions ds = new Functions();
                    employees = functions.getAllDData("USER_ID", "LOGIN");
                    if (employees.size() > 0) {
                        for (int t = 0; t < employees.size(); t++) {
                            String ss = "select SUM(TOTAL_AMOUNT) from SALES_GENERAL Where CASHIER_NAME='UNKNOWN'";
                            String sql = "select FIRST_NAME,LAST_NAME,Telephone,ADDRESS from Login where USER_ID='" + employees.get(t).toString() + "'";
                            try {
                                re = Functions.database.ExecuteQuery(sql);
                                table.addCell(re.get(0).toString());
                                table.addCell(re.get(1).toString());
                                table.addCell(re.get(2).toString());
                                table.addCell(re.get(3).toString());
                                table.addCell(Functions.database.ExecuteQuery(ss).get(0).toString());

                            } catch (SQLException ex) {
                                System.out.println(ex.getMessage());
                            }
                        }
                    }
                    doc.add(table);
                    doc.addCreationDate();
                    doc.close();
                    Desktop.getDesktop().open(new File("report.pdf"));
                }
                break;

            case 2:

                for (int i = 0; i < Items.size(); i++) {
                    if (Items.get(i).toString().equalsIgnoreCase("SGoodSold1")) {
                        Attributes.add("PRODUCT_NAME");
                    } else if (Items.get(i).toString().equalsIgnoreCase("SAmount1")) {
                        Attributes.add("PRICE");
                    } else if (Items.get(i).toString().equalsIgnoreCase("SID1")) {
                        Attributes.add("TRANSACTION_ID");
                    } else if (Items.get(i).toString().equalsIgnoreCase("SDate1")) {
                        Attributes.add("Date");
                    } else if (Items.get(i).toString().equalsIgnoreCase("SQuantity1")) {
                        Attributes.add("QUANTITY");
                    } else if (Items.get(i).toString().equalsIgnoreCase("SQuantity1")) {
                        Attributes.add("QUANTITY");
                    } else if (Items.get(i).toString().equalsIgnoreCase("cusname")) {
                        AAttributes.add("CUSTOMER_NAME");
                    }

                }

                if (Attributes.size() > 0) {
                    FileOutputStream fs = new FileOutputStream("report.pdf");
                    Document doc = new Document();
                    PdfWriter writer = PdfWriter.getInstance(doc, fs);
                    String sh = "                                     Date and Time Drawn Up: " + Calendar.getInstance().getTime().toGMTString() + "\n\n";
                    String header = "                                                                 KHAMALS Sales Report\n"
                            + "                                              DESIGNER AND SHOWROOM EXPERT \n "
                            + "                                  MOLYKO BUEA  LAST FLOOR ECKO BANK BUILDING\n ";
                    doc.open();
                    doc.add(new Paragraph(header));
                    doc.add(new Paragraph(sh));
                    PdfPTable table = new PdfPTable(Attributes.size());
                    for (int i = 0; i < Attributes.size(); i++) {
                        table.addCell(Attributes.get(i).toString());
                    }
                    //Functions ds = new Functions();
                    for(int f = 0;f<functions.getAllDData("QUANTITY", "SALES_DETAILED").size();f++)
                    for (int t = 0; t < Attributes.size(); t++) {
                        
                        try {
                            //System.out.println(f+":"+t);
                            re = functions.getAllData(Attributes.get(t).toString(), "SALES_DETAILED");
                          
                            table.addCell(re.get(f).toString());

                        } catch (Exception ex) {
                            System.out.println("try" + ex.getMessage());
                        }
                    }
                    doc.add(table);
                    doc.addCreationDate();
                    doc.close();
                    Desktop.getDesktop().open(new File("report.pdf"));
                    System.out.println("Me" + re.toString());
                }

                break;

            case 3:
                for (int i = 0; i < Items.size(); i++) {
                    if (Items.get(i).toString().equalsIgnoreCase("IName1")) {
                        Attributes.add("NAME");
                    } else if (Items.get(i).toString().equalsIgnoreCase("IDate1")) {
                        Attributes.add("LASTLY_ADDED_ON");
                    } else if (Items.get(i).toString().equalsIgnoreCase("IQuantity1")) {
                        Attributes.add("QUANTITY_ADDED");
                    } else if (Items.get(i).toString().equalsIgnoreCase("IUPrice1")) {
                        Attributes.add("COST_PRICE");
                    } else if (Items.get(i).toString().equalsIgnoreCase("IDiscount1")) {
                        Attributes.add("DESCRIPTION");
                    } 

                }

                if (Attributes.size() > 0) {
                    FileOutputStream fs = new FileOutputStream("report.pdf");
                    Document doc = new Document();
                    PdfWriter writer = PdfWriter.getInstance(doc, fs);
                    String sh = "                                     Date and Time Drawn Up: " + Calendar.getInstance().getTime().toGMTString() + "\n\n";
                    String header = "                                                                 KHAMALS Inventory Report\n"
                            + "                                              DESIGNER AND SHOWROOM EXPERT \n "
                            + "                                  MOLYKO BUEA  LAST FLOOR ECKO BANK BUILDING\n ";
                    doc.open();
                    doc.add(new Paragraph(header));
                    doc.add(new Paragraph(sh));
                    PdfPTable table = new PdfPTable(Attributes.size());
                    for (int i = 0; i < Attributes.size(); i++) {
                        table.addCell(Attributes.get(i).toString());
                    }
                    //Functions ds = new Functions();
                    for(int f = 0;f<functions.getAllDData("DESCRIPTION", "PRODUCT").size();f++)
                    for (int t = 0; t < Attributes.size(); t++) {
                        
                        try {
                            //System.out.println(f+":"+t);
                            re = functions.getAllData(Attributes.get(t).toString(), "PRODUCT");
                          
                            table.addCell(re.get(f).toString());

                        } catch (Exception ex) {
                            System.out.println("try" + ex.getMessage());
                        }
                    }
                    doc.add(table);
                    doc.addCreationDate();
                    doc.close();
                    Desktop.getDesktop().open(new File("report.pdf"));
                    System.out.println("Me" + re.toString());
                }

                break;

            case 4:
                
                for (int i = 0; i < Items.size(); i++) {
                    if (Items.get(i).toString().equalsIgnoreCase("STAmountTSold1")) {
                        AAttributes.add("SUM(QUANTITY)");
                    } else if (Items.get(i).toString().equalsIgnoreCase("STDate1")) {
                        Attributes.add("Date");
                    } else if (Items.get(i).toString().equalsIgnoreCase("STIDate1")) {
                        IAttributes.add("LASTLY_ADDED_ON");
                    } else if (Items.get(i).toString().equalsIgnoreCase("STUPrice1")) {
                        Attributes.add("SELLING_PRICE");
                    } 

                }

                if (Attributes.size() > 0||IAttributes.size()>0||AAttributes.size()>0) {
                    FileOutputStream fs = new FileOutputStream("report.pdf");
                    Document doc = new Document();
                    PdfWriter writer = PdfWriter.getInstance(doc, fs);
                    String sh = "                                     Date and Time Drawn Up: " + Calendar.getInstance().getTime().toGMTString() + "\n\n";
                    String header = "                                                                 KHAMALS Stoke Report\n"
                            + "                                              DESIGNER AND SHOWROOM EXPERT \n "
                            + "                                  MOLYKO BUEA  LAST FLOOR ECKO BANK BUILDING\n ";
                    doc.open();
                    
                    doc.add(new Paragraph(header));
                    doc.add(new Paragraph(sh));
                    PdfPTable table = null;
                    if(AAttributes.size()!=0||IAttributes.size()!=0)
                    {
                        if(IAttributes.size()!=0&&AAttributes.size()!=0){
                     table= new PdfPTable(Attributes.size()+3);
                     table.addCell("Product Name");
                     table.addCell("Last Inventory date");
                     table.addCell("Total Quantity Ever Sold");
                        }
                        else
                        {
                        table= new PdfPTable(Attributes.size()+2);
                       if(IAttributes.size()!=0){
                        table.addCell("Product Name");
                        table.addCell("Last Inventory date");}
                       else
                       {
                       table.addCell("Product Name");
                        table.addCell("Total Quantity Sold");
                       }
                     
                        }
                    }
                    
                    else
                    {
                    
                     table= new PdfPTable(Attributes.size()+1);
                     table.addCell("Product Name");
                    }
                    //Functions ds = new Functions();
                     red = functions.gellDData("NAME", "PRODUCT");
                    System.out.println(Attributes.toString());
            for (Object Attribute : Attributes) {
                table.addCell(Attribute.toString());
            }
                      
                    
                    for(int f = 0;f<red.size();f++){
                           
                             amount= Functions.database.ExecuteQuery("Select SUM(Quantity) from SALES_DETAILED where PRODUCT_NAME='"+red.get(f).toString()+"'").get(0).toString();
                        String invent = Functions.database.ExecuteQuery("select LASTLY_ADDED_ON from PRODUCT where NAME='"+red.get(f).toString()+"'").get(0).toString();
                         
                       if(AAttributes.size()!=0||IAttributes.size()!=0)
                    {
                        if(IAttributes.size()!=0&&AAttributes.size()!=0){
                     //table= new PdfPTable(Attributes.size()+3);
                     table.addCell(red.get(f).toString());
                     table.addCell(invent);
                     table.addCell(amount);
                            System.out.println(invent);
                        }
                        else
                        {
                       // table= new PdfPTable(Attributes.size()+2);
                   if(AAttributes.size()!=0)
                   {
                       table.addCell(red.get(f).toString());
                       table.addCell(amount);
                   }
                   else
                   {
                       table.addCell(red.get(f).toString());
                       table.addCell(invent);
                       
                   }
                     
                        }
                    }
                       else
                       {
                           table.addCell(red.get(f).toString());
                       }
                         
               for (int t = 0; t < Attributes.size(); t++) {
                        
                        try {
                            System.out.println(f+":"+t);
                            re = functions.gellDData(Attributes.get(t).toString(), "SALES_DETAILED");
                             System.out.println(re.toString());
                            table.addCell(re.get(f).toString());

                        } catch (Exception ex) {
                            System.out.println("try " + ex.getMessage());
                        }
                    }
                    }
                    doc.add(table);
                    doc.addCreationDate();
                    doc.close();
                    re.clear();
                    this.AAttributes.clear();
                    IAttributes.clear();
                    Items.clear();
                    Desktop.getDesktop().open(new File("report.pdf"));
                    System.out.println("Me" + re.toString());
                }
                break;
        }
    }
 private void plotChart(ArrayList months, ArrayList permont) {
    final CategoryAxis xAxis = new CategoryAxis();
    final NumberAxis yAxis = new NumberAxis();
        System.out.println(permont.toString());
        System.out.println(months.toString());
    xAxis.setLabel("Months");
    yAxis.setLabel("Amount Sold");
        XYChart.Series ser1 = new XYChart.Series();
     
        ser1.setName(comb.getSelectionModel().getSelectedItem().toString());
        for(int i =0;i<months.size();i++)
        ser1.getData().add(new XYChart.Data(months.get(i).toString(),permont.get(i)));
        chart.getData().add(ser1);
        chart.setTitle("MONTHS AGAINST AMOUNT SOLD");
    }

    private void salesMonthly(ArrayList results) {
        
            
            int m1 = 0, m2 = 0, m3 = 0, m4 = 0, m5 = 0, m6 = 0, m7 = 0, m8 = 0, m9 = 0, m10 = 0, m11 = 0, m12 = 0, m13 = 0;
            ArrayList permont = new ArrayList();
           //System.out.println(results.toString());
            
            m1 = Calculate(1, results);
            System.out.println(m1);
            permont.add(m1);
            m2 = Calculate(2, results);
            permont.add(m2);
            m3 = Calculate(3, results);
            permont.add(m3);
            m4 = Calculate(4, results);
            permont.add(m4);
            m5 = Calculate(5, results);
            permont.add(m5);
            m6 = Calculate(6, results);
            permont.add(m6);
            m7 = Calculate(7, results);
            permont.add(m7);
            m8 = Calculate(8, results);
            permont.add(m8);
            m9 = Calculate(9, results);
            permont.add(m9);
            m10 = Calculate(10, results);
            permont.add(m10);
            m11 = Calculate(11, results);
            permont.add(m11);
            m12 = Calculate(12, results);
            permont.add(m12);
            ArrayList months = new ArrayList();
            months.add("January");
            months.add("February");
            months.add("March");
            months.add("April");
            months.add("May");
            months.add("June");
            months.add("July");
            months.add("August");
            months.add("September");
            months.add("October");
            months.add("November");
            months.add("December");
            permont.add(m13);
            plotChart(months, permont);
            }

    
    
    
     private int Calculate(int i, ArrayList day1) {
         int y = 0 ;
         for(int ip =0;ip<day1.size();ip++){
    String query = "select sum(QUANTITY) from SALES_DETAILED where MONTH(Date) =" + i+" and TRANSACTION_ID= "+Integer.parseInt(day1.get(ip).toString());
         y=0;
          System.out.println(query);
    try{
        ArrayList x = Functions.database.ExecuteQuery(query);
            System.out.println(x.toString());
            if (x.size() != 0)
           
                for (Object item : x)
                    y = y + Integer.parseInt(item.toString());
          return y;
         }
           catch(Exception s)
           {
               
               System.out.println(s.toString());
               return y;
           }
            
    
    }
         return y;
     }

  public void salesMonthly(String name)
        {
        try {
            int m1 = 0, m2 = 0, m3 = 0, m4 = 0, m5 = 0, m6 = 0, m7 = 0, m8 = 0, m9 = 0, m10 = 0, m11 = 0, m12 = 0, m13 = 0;
            ArrayList permont = new ArrayList();
           
            
            m1 = Calculate(1, name);
            System.out.println(m1);
            permont.add(m1);
            m2 = Calculate(2, name);
            permont.add(m2);
            m3 = Calculate(3, name);
            permont.add(m3);
            m4 = Calculate(4, name);
            permont.add(m4);
            m5 = Calculate(5, name);
            permont.add(m5);
            m6 = Calculate(6, name);
            permont.add(m6);
            m7 = Calculate(7, name);
            permont.add(m7);
            m8 = Calculate(8, name);
            permont.add(m8);
            m9 = Calculate(9, name);
            permont.add(m9);
            m10 = Calculate(10, name);
            permont.add(m10);
            m11 = Calculate(11, name);
            permont.add(m11);
            m12 = Calculate(12, name);
            permont.add(m12);
            ArrayList months = new ArrayList();
            months.add("January");
            months.add("February");
            months.add("March");
            months.add("April");
            months.add("May");
            months.add("June");
            months.add("July");
            months.add("August");
            months.add("September");
            months.add("October");
            months.add("November");
            months.add("December");
            permont.add(m13);
            plotChart(months, permont);
        } catch (SQLException ex) {
                    System.err.println(ex.getMessage());
        }

        }
private int Calculate(int i, String day1) throws SQLException {
    String query = "select sum(QUANTITY) from SALES_DETAILED where MONTH(Date) =" + i+" and PRODUCT_NAME= '"+day1+"'";
          int y =0;
          System.out.println(query);
    try{ ArrayList x = Functions.database.ExecuteQuery(query);
            System.out.println(x.toString());
            if (x.size() != 0)
           
                for (Object item : x)
                    y = y + Integer.parseInt(item.toString());
            return y;
           }
           catch(Exception s)
           {
               
               System.out.println(s.toString());
               return y;
           }
    

}

  private void fetch2Info(int index, ArrayList Items)
               {
        ArrayList Attributes = new ArrayList();
                   info =FXCollections.observableArrayList();
        //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   Functions functions = new Functions();
                   switch (index) {
            case 10:
              //  System.out.println("Hello");
                                TableColumn fname = new TableColumn("First Name");
                                TableColumn lname = new TableColumn("Last Name");
                                TableColumn telephone = new TableColumn("Telephone Number");
                                TableColumn address = new TableColumn("Address");
                                 TableColumn famount = new TableColumn("Total Amount Sold");
                                 TableColumn INDEX = new TableColumn("Index");
                                tabsl.getColumns().clear();
                                 tabsl.getColumns().clear();
                                 tabsl.getColumns().add(INDEX);
                                tabsl.getColumns().add(fname); 
                                tabsl.getColumns().add(lname);
                                tabsl.getColumns().add(telephone);
                                tabsl.getColumns().add(address);
                                INDEX.setPrefWidth(50);
                                fname.setPrefWidth(200);
                                lname.setPrefWidth(180);
                                telephone.setPrefWidth(180);
                                address.setPrefWidth(200);
                                famount.setPrefWidth(230);
                                INDEX.setCellValueFactory(new PropertyValueFactory("Index"));
                                fname.setCellValueFactory(new PropertyValueFactory("Date"));
                                 lname.setCellValueFactory(new PropertyValueFactory("Desc"));
                                 telephone.setCellValueFactory(new PropertyValueFactory("LastIndate"));
                                 address.setCellValueFactory(new PropertyValueFactory("LastSolddate"));
                                 famount.setCellValueFactory(new PropertyValueFactory("TAmount"));
                                 for (int i = 0; i < Items.size(); i++) {
                    if (Items.get(i).toString().equalsIgnoreCase("QuantitySold")) {
                        Attributes.add("SUM(Quantity)");
                    }

                }
                if (Attributes.size() == 0) {
                
                    //Functions ds = new Functions();
                    employees = functions.getAllDData("USER_ID", "LOGIN");
                    System.out.println("sad");
                    if (employees.size() > 0) {
                        System.out.println("Ke");
                         THelper data = new THelper();
                        for (int t = 0; t < employees.size(); t++) {
                            String sql = "select FIRST_NAME,LAST_NAME,Telephone,ADDRESS from Login where USER_ID='" + employees.get(t).toString() + "'";
                            try {
                                re = Functions.database.ExecuteQuery(sql);
                               
                                data.Date.setValue(re.get(0).toString());
                                System.out.println(data.Date.toString());
                                data.Desc.setValue(re.get(1).toString());
                                data.LastIndate.setValue(re.get(2).toString());
                                data.LastSolddate.setValue(re.get(3).toString());
                                data.Index.setValue(String.valueOf(t+1));
                                info.add(data);
                            }
                            catch (SQLException ex) {
                                System.out.println(ex.getMessage());
                            }
                        }
                         tabsl.getItems().setAll(info);
                         //tabsl.setItems(info);
                         //System.out.println(info.get(0));
                    }
                    System.out.println("Me" + re.toString());
                } else {
                            tabsl.getColumns().add(famount);
                    //Functions ds = new Functions();
                    employees = functions.getAllDData("USER_ID", "LOGIN");
                    if (employees.size() > 0) {
                        String tam="0";
                        
                        for (int t = 0; t < employees.size(); t++) {
                            String ss = "select SUM(TOTAL_AMOUNT) from SALES_GENERAL Where CASHIER_NAME='"+employees.get(t)+"' and TRANSACTION_DATE >='"+this.Start+"' and TRANSACTION_DATE <= '"+this.end+"'";
                            String sql = "select FIRST_NAME,LAST_NAME,Telephone,ADDRESS from Login where USER_ID='" + employees.get(t).toString() + "'";
                            try {
                                    if(Functions.database.ExecuteQuery(ss).get(0)!=null)
                           tam= Functions.database.ExecuteQuery(ss).get(0).toString();
                        else
                            tam="0";
                                re = Functions.database.ExecuteQuery(sql);
                                THelper data = new THelper();
                                data.Date.setValue(re.get(0).toString());
                                data.Desc.setValue(re.get(1).toString());
                                data.LastIndate.setValue(re.get(2).toString());
                                data.LastSolddate.setValue(re.get(3).toString());
                                data.TAmount.setValue(tam);
                                data.Index.setValue(String.valueOf(t+1));
                                info.add(data);
                            } catch (SQLException ex) {
                                System.out.println(ex.getMessage());
                            }
                        }
                        
                    }
                    tabsl.getItems().setAll(info);
                 }
               
            break;

            case 20:
                TableColumn GName = new TableColumn("Product Name");
                TableColumn Amt = new TableColumn("Total Amount");
                TableColumn DateS = new TableColumn("Date Of Sale");
                TableColumn TID = new TableColumn("Transaction ID");
                TableColumn QTY = new TableColumn("Quantity");
                TableColumn IDEX = new TableColumn("Index");
                GName.setPrefWidth(180);
                Amt.setPrefWidth(120);
                DateS.setPrefWidth(150);
                TID.setPrefWidth(10);
                QTY.setPrefWidth(120);
                IDEX.setPrefWidth(50);
                GName.setCellValueFactory(new PropertyValueFactory("Date"));
                Amt.setCellValueFactory(new PropertyValueFactory("TAmount"));
                DateS.setCellValueFactory(new PropertyValueFactory("LastSolddate"));
                TID.setCellValueFactory(new PropertyValueFactory("Desc"));
                QTY.setCellValueFactory(new PropertyValueFactory("LastIndate"));
                IDEX.setCellValueFactory(new PropertyValueFactory("Index"));
                for (int i = 0; i < Items.size(); i++) {
                    if (Items.get(i).toString().equalsIgnoreCase("SGoodSold")) {
                        Attributes.add("PRODUCT_NAME");
                    } else if (Items.get(i).toString().equalsIgnoreCase("SAmount")) {
                        Attributes.add("PRICE");
                    } else if (Items.get(i).toString().equalsIgnoreCase("SID")) {
                        Attributes.add("TRANSACTION_ID");
                    } else if (Items.get(i).toString().equalsIgnoreCase("SDate")) {
                        Attributes.add("Date");
                    } else if (Items.get(i).toString().equalsIgnoreCase("SQuantity")) {
                        Attributes.add("QUANTITY");
                    } else if (Items.get(i).toString().equalsIgnoreCase("SQuantity")) {
                        Attributes.add("QUANTITY");
                    } else if (Items.get(i).toString().equalsIgnoreCase("cusname")) {
                        AAttributes.add("CUSTOMER_NAME");
                    }
                      
                }
               // Functions ds = new Functions();
              info=  FXCollections.observableArrayList();
     String sql ="Select PRODUCT_NAME,Date,Price,QUANTITY,TRANSACTION_ID from SALES_DETAILED ";
        try {
            re = Functions.database.ExecuteQuery(sql);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());    
        }
        info.clear();;
                if (Attributes.size() > 0) 
                {
                    tabsl.getColumns().setAll(IDEX,GName,DateS,QTY,Amt,TID);
                
                    
                    for(int f = 0;f<functions.getAllDData("QUANTITY", "SALES_DETAILED").size();f++)
                    for (int t = 0; t < re.size(); t++) {
                        
                        try {
                            THelper data = new THelper();
//                             GName.setCellValueFactory(new PropertyValueFactory("Date"));
//                Amt.setCellValueFactory(new PropertyValueFactory("TAmount"));
//                DateS.setCellValueFactory(new PropertyValueFactory("LastSolddate"));
//                TID.setCellValueFactory(new PropertyValueFactory("Desc"));
//                QTY.setCellValueFactory(new PropertyValueFactory("LastIndate"));
//                IDEX.setCellValueFactory(new PropertyValueFactory("Index"));
                           
                                data.Date.setValue(re.get(0).toString());
                                data.LastSolddate.setValue(re.get(1).toString());
                                data.TAmount.setValue(re.get(2).toString());
                                data.LastIndate.setValue(re.get(3).toString());
                                data.Desc.setValue(re.get(4).toString());
                                data.Index.setValue(String.valueOf(t+1));
                                info.add(data);
                        } catch (Exception ex) {
                            System.out.println("try" + ex.getMessage());
                        }
                    }
                      tabsl.getColumns().clear();
                       tabsl.getItems().setAll(info);
                }
                else
                {
                tabsl.getColumns().setAll(IDEX,GName,DateS,QTY,Amt);
            try {
                
                re = Functions.database.ExecuteQuery(sql);
            } catch (SQLException ex) {
                System.out.println(ex.toString());
            }
                 for(int f = 0;f<functions.getAllDData("QUANTITY", "SALES_DETAILED").size();f++)
                    for (int t = 0; t < re.size(); t++) {
                  
                THelper data = new THelper();
                
                                data.Date.setValue(re.get(0).toString());
                                data.LastSolddate.setValue(re.get(1).toString());
                                data.TAmount.setValue(re.get(2).toString());
                                data.LastIndate.setValue(re.get(3).toString());
                                data.Desc.setValue(re.get(4).toString());
                                data.Index.setValue(String.valueOf(t+1));
                                info.add(data);
                        } 
                tabsl.getItems().setAll(info);

                }
                    
                
                
               
                break;

                 case 30:
                TableColumn Name = new TableColumn("Product Name");
                //TableColumn Amt = new TableColumn("Total Amount");
                TableColumn LastDS = new TableColumn("Last Date Of Sale");
               
                 TableColumn LastID = new TableColumn("Inventory Date");
                TableColumn LastIQ = new TableColumn("inventory Quantity");
                TableColumn QTYL = new TableColumn("Quantity Left");
                TableColumn SP = new TableColumn("Unit Cost Price");
                TableColumn Category = new TableColumn("Category");
                Name.setPrefWidth(200);
                LastDS.setPrefWidth(100);
                LastID.setPrefWidth(120);
                LastIQ.setPrefWidth(120);
                QTYL.setPrefWidth(120);
                SP.setPrefWidth(100);
                Category.setPrefWidth(150);
               
                tabsl.getColumns().setAll(Name,SP,Category,QTYL,LastIQ,LastID,LastDS);
               THelper asa = new THelper();
                //Assigning Values
                Name.setCellValueFactory(new PropertyValueFactory("Date"));
                LastDS.setCellValueFactory(new PropertyValueFactory("LASTLY_SOLD_ON"));
                QTYL.setCellValueFactory(new PropertyValueFactory("QUANTITY_AVAILABLE"));
                LastID.setCellValueFactory(new PropertyValueFactory("LastIndate"));
                LastIQ.setCellValueFactory(new PropertyValueFactory("QuantityBrouth"));
                Category.setCellValueFactory(new PropertyValueFactory("CATEGORY"));
                SP.setCellValueFactory(new PropertyValueFactory("SELLING_PRICE"));
             
              info =  FXCollections.observableArrayList();
     //String sql ="Select NAME,CATEGORY,LASTLY_ADDED_ON,QUANTITY_AVAILABLE from SALES_DETAILED,LASTLY_SOLD_ON, ";
        try {
            //re = Functions.database.ExecuteQuery(sql);
        ArrayList nam = new ArrayList();
        ArrayList cat = new ArrayList();
        ArrayList LDO = new ArrayList();
        ArrayList LSO = new ArrayList();
        ArrayList QTA = new ArrayList();
        ArrayList summ = new ArrayList();
        ArrayList QTYAD = new ArrayList();
     
        nam = functions.getAllDData("NAME", "PRODUCT");
        cat = functions.getAllDData("CATEGORY", "PRODUCT");
        LDO = functions.getAllDData("LASTLY_ADDED_ON", "PRODUCT");
        LSO = functions.getAllDData("LASTLY_SOLD_ON", "PRODUCT");
        QTA = functions.getAllDData("QUANTITY_AVAILABLE", "PRODUCT");
        summ = functions.getAllDData("MIN_SELLING_PRICE", "PRODUCT");
        QTYAD = functions.getAllDData("QUANTITY_ADDED", "PRODUCT");
                                try {
                            THelper data = new THelper();
                            for(int t =0;t<nam.size();t++)
                            {
                               
                             data.Date.setValue(nam.get(t).toString());
                             data.CATEGORY.setValue(cat.get(t).toString());
                           
                             try{
                                 data.LASTLY_SOLD_ON.setValue(LSO.get(t).toString());
                             
                             }
                             catch(Exception asd)
                             {
                             data.LASTLY_SOLD_ON.setValue("Not Yet Sold");
                             }
                                 data.LastIndate.setValue(LDO.get(t).toString());
                             data.SELLING_PRICE.setValue(summ.get(t).toString());
                             data.QUANTITY_AVAILABLE.setValue(QTA.get(t).toString());
                             data.QuantityBrouth.setValue(QTYAD.get(t).toString());
                                System.out.println(data.toString());   
                             info.add(data);  
                            }  
                                tabsl.getItems().setAll(info);
                        } catch (Exception ex) {
                            System.out.println("try" + ex.toString());
                            ex.printStackTrace();
                        }
                    }
            catch(Exception sa)
            {
                System.out.println(sa.getMessage());
                
            }
                break;

        }
    }

    @Override
    public void setScreenParent(ScreensController pane) {
    }
    
}
