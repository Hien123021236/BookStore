/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.GUI;

import bookstore.BLL.Book;
import bookstore.BLL.ImportCoupon;
import bookstore.DAL.BookDTO;
import bookstore.DAL.ImportCouponDTO;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Orics
 */
public class ImportManagementController implements Initializable {
    
    
    @FXML
    private AnchorPane ImportManagement;
    
    @FXML
    private TableView ImportCoupon_TableView;
    
    @FXML
    private TableView BookNeedImported_TableView;
    
    @FXML
    private TableColumn<ImportCoupon, String> ImportID_TableColunm;
    
    @FXML
    private TableColumn<ImportCoupon, String> EmployeeID_TableColunm;
    
    @FXML
    private TableColumn<ImportCoupon, String> Datetime_TableColunm;
    
    @FXML
    private TableColumn<ImportCoupon, String> TotalAmount_TableColunm;
    
    @FXML
    private TableColumn<Book, String> BookID_TableColunm;
    
    @FXML
    private TableColumn<Book, String> Title_TableColunm;
    
    @FXML
    private TableColumn<Book, String> Quatity_TableColunm;
    
    @FXML
    private Button Add_Button;
    
    @FXML
    private Button Edit_Button;
    
    @FXML
    private Button Refresh_Button;
    
    private ArrayList<ImportCoupon> ListImportCoupon;
    private ArrayList<Book> ListBooksNeedImported;
    
    
    private ObservableList<ImportCoupon> ObsListImportCoupon = FXCollections.observableArrayList( );
    private ObservableList<Book> ObsListBooksNeedImported = FXCollections.observableArrayList( );
    
    
    public void Load(){
        LoadImportCoupons();
        LoadBooksNeedImported();
    }
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ImportID_TableColunm.setCellValueFactory(new PropertyValueFactory<>("ImportID"));
        EmployeeID_TableColunm.setCellValueFactory(new PropertyValueFactory<>("EmployeeID"));
        Datetime_TableColunm.setCellValueFactory(new PropertyValueFactory<>("Datetime"));
        TotalAmount_TableColunm.setCellValueFactory(new PropertyValueFactory<>("TotalAmount"));
        ImportCoupon_TableView.setItems(ObsListImportCoupon);
        
        BookID_TableColunm.setCellValueFactory(new PropertyValueFactory<>("BookID"));
        Title_TableColunm.setCellValueFactory(new PropertyValueFactory<>("Title"));
        Quatity_TableColunm.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
        BookNeedImported_TableView.setItems(ObsListBooksNeedImported);
        
        Add_Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                OpenImportWindown("New");
            }
        });
        
        Edit_Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                OpenImportWindown("Edit");
            }
        });
    }
    
    private void LoadImportCoupons(){
        ImportCouponDTO icDTO = new ImportCouponDTO();
        ListImportCoupon = icDTO.GetAllsImportCoupons();
        ObsListImportCoupon.clear();
        if(ListImportCoupon != null){
            ObsListImportCoupon.clear();
            for (ImportCoupon ic : ListImportCoupon) {
                ObsListImportCoupon.add(ic);
            }
        }
    }    
    
    private void LoadBooksNeedImported(){
        BookDTO bDTO = new BookDTO();
        ListBooksNeedImported = bDTO.GetBooksNeedImported();
        ObsListBooksNeedImported.clear();
        if(ListBooksNeedImported != null){
            ObsListBooksNeedImported .clear();
            for (Book b : ListBooksNeedImported) {
                ObsListBooksNeedImported.add(b);
            }
        }
    }
    
    private void OpenImportWindown(String mode){
        try {          
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/bookstore/GUI/ImportWindown.fxml"));
            Parent ImportWindown = loader.load();
            ImportWindownController controller = (ImportWindownController)loader.getController();
            if(mode.compareTo("New") == 0)
                controller.SetNewMode();
            else
            if(mode.compareTo("Edit") == 0){
                ImportCoupon ic = (ImportCoupon) ImportCoupon_TableView.getSelectionModel().getSelectedItem();
                if(ic != null){
                    controller.setImportCoupon((ImportCoupon) ImportCoupon_TableView.getSelectionModel().getSelectedItem());
                    controller.SetEditMode();
                }
                else{
                    Alert alert = new Alert(AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setHeaderText("No Import coupon selected !");
                    alert.setContentText("Please select a import coupon.");
                    alert.showAndWait();
                    return;
                }
            }      
            Stage stage = new Stage(StageStyle.UNIFIED);
            Scene scene = new Scene(ImportWindown);     
            stage.setScene(scene);    
            stage.show();
            
        } catch (IOException ex) {
            Logger.getLogger(LoginWindownController.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }  
}
