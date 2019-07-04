/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.GUI;

import bookstore.BLL.Book;
import bookstore.BLL.ImportCoupon;
import bookstore.BLL.ImportCouponDetail;
import bookstore.DAL.BookDTO;
import bookstore.DAL.ImportCouponDTO;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Orics
 */
public class ImportWindownController implements Initializable {
    
    @FXML
    private Label ImportID_Label;
    
    @FXML
    private TextField ImportID_TextField;
    
    @FXML
    private TextField EmployeeID_TextField;
    
    @FXML
    private TextField Datetime_TextField;
    
    @FXML
    private TextField TotalAmount_TextField;
    
    @FXML
    private TableView ImportBook_TableView;
    
    @FXML
    private TableColumn BookID_TableColumn;
    
    @FXML
    private TableColumn Title_TableColumn;
    
    @FXML
    private TableColumn Cost_TableColumn;
    
    @FXML
    private TableColumn Count_TableColumn;
    
    @FXML
    private ComboBox<String> BookID_ComboBox;
    
    @FXML
    private TextField Cost_TextField;
    
    @FXML
    private Button Import_Button;
    
    @FXML
    private TextField Count_TextField;
    
    @FXML
    private Label NewBook_Label;
    
    @FXML
    private Button Save_Button;
    
    
    private ImportCoupon importCoupon;
    private ObservableList<ImportCouponDetail> ObsListBooksImported = FXCollections.observableArrayList( );

    
    public void setImportCoupon(ImportCoupon importCoupon) {
        this.importCoupon = importCoupon;
    }

    public ImportCoupon getImportCoupon() {
        return importCoupon;
    }

    public void SetNewMode(){        
        importCoupon = new ImportCoupon();     
        importCoupon.setImportID(Integer.toString((new ImportCouponDTO()).GetImportIdMax()+1));      
        importCoupon.setEmployeeID("  ");  
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");  
        Date date = new Date(System.currentTimeMillis()); 
        importCoupon.setDatetime(formatter.format(date));
        importCoupon.setTotalAmount(0);
        importCoupon.setListImportDetail( new ArrayList<ImportCouponDetail>());
            
        ImportID_TextField.setText(importCoupon.getImportID());
        EmployeeID_TextField.setText(importCoupon.getEmployeeID());
        Datetime_TextField.setText(importCoupon.getDatetime());
        TotalAmount_TextField.setText("0"); 
        LoadBookIDComboBox();
    }
    
    public void SetEditMode(){
        if(importCoupon != null){
            ImportID_TextField.setText(importCoupon.getImportID());
            EmployeeID_TextField.setText(importCoupon.getEmployeeID());
            Datetime_TextField.setText(importCoupon.getDatetime());
            TotalAmount_TextField.setText(Integer.toString(importCoupon.getTotalAmount()));
            LoadBookIDComboBox();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        BookID_TableColumn.setCellValueFactory(new PropertyValueFactory<>("BookID"));
        Title_TableColumn.setCellValueFactory(new PropertyValueFactory<>("BookTitle"));
        Cost_TableColumn.setCellValueFactory(new PropertyValueFactory<>("Price"));
        Count_TableColumn.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
        ImportBook_TableView.setItems(ObsListBooksImported);
        
        
        
        NewBook_Label.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                OpenNewBookWindown();
            }
        });
        
        Import_Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ImportCouponDetail icd = new ImportCouponDetail();
                icd.setImportCouponID(ImportID_TextField.getText());
                icd.setBookID(BookID_ComboBox.getValue());
                icd.setPrice(Integer.parseInt(Cost_TextField.getText()));
                icd.setQuantity(Integer.parseInt(Count_TextField.getText()));
                
                importCoupon.getListImportDetail().add(icd);
                LoadListImportBooks();
                LoadBookIDComboBox();
                Cost_TextField.setText("");
                Count_TextField.setText("");
            }
        });
    }    
    
    private void OpenNewBookWindown(){
        try {          
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/bookstore/GUI/BookWindown.fxml"));
            Parent BookWindown = loader.load();
            BookWindownController controller = (BookWindownController)loader.getController();
            controller.SetNewMode();
            Stage stage = new Stage(StageStyle.UNIFIED);
            Scene scene = new Scene(BookWindown);     
            stage.setScene(scene);    
            stage.show();
            
        } catch (IOException ex) {
            Logger.getLogger(LoginWindownController.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }

    private void LoadListImportBooks(){
        if(importCoupon != null){
            ObsListBooksImported.clear();
            for (ImportCouponDetail icd : importCoupon.getListImportDetail()) {
                ObsListBooksImported.add(icd);
            }
        }
    }
    
    private void LoadBookIDComboBox(){
        BookDTO bDTO = new BookDTO();
        BookID_ComboBox.getItems().clear();
        for (Book b : bDTO.GetBooksNeedImported()) {
            BookID_ComboBox.getItems().add(b.getBookID());
        }
        for (ImportCouponDetail icd: ObsListBooksImported) {
            BookID_ComboBox.getItems().remove(icd.getBookID());
        }
    }
    
}
