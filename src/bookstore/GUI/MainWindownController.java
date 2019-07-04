/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.GUI;

import bookstore.BLL.Employee;
import bookstore.DAL.BookDTO;
import bookstore.DAL.EmployeeDTO;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Orics
 */
public class MainWindownController implements Initializable {
    
    
    @FXML
    private AnchorPane root;

    @FXML 
    private Label EmployeeName;
    
    @FXML 
    private Label EmployeeRole;
       
    @FXML
    private MenuItem AccountInfor_MenuItem;
    
    @FXML
    private MenuItem Logout_MenuItem;
    
    @FXML
    private Button Exit;
    
    @FXML
    private Button RoomBtn;
    
    @FXML
    private Button ImportBtn;
   
    @FXML
    private Button CustomerBtn;
        
    @FXML
    private Pane Needle;
    
    @FXML
    private AnchorPane MgmtPane;
       
  
    
    private boolean RoomMngmLoaded = false;
    
    private boolean ImportMngmLoaded = false;
    
    private boolean CustomerMngmLoaded = false;
    
    private boolean StatisticMngmLoaded = false;
   
    private boolean RegulationMngmLoaded = false;
    
    private boolean HelpMngmLoaded = false;
    
       
    private Stage stage;
    private Employee employee;

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    } 
    
    public Employee getEmployee() {
        return employee;
    }
    
    public void setEmployee(Employee employee) {
        this.employee = employee;
        LoadEmployeeShortInfor();
    }
      
    public void Load(){
        
    }
    
    
    
    
    
    
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {   
        
        AccountInfor_MenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                OpenEmployeeWindown();
            }
        });
        
        RoomBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               LoadRoomManagement();
            }
        });
        
        ImportBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               LoadImportManagement();
            }
        });   
        
        
        
        Exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });
    }
    
    
    private void LoadEmployeeShortInfor(){
        if(employee != null){
            EmployeeName.setText(employee.getName());
            EmployeeRole.setText(employee.getRole());
        }    
    }
    
    private void OpenEmployeeWindown(){
        System.out.println("open Employee");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/bookstore/GUI/EmployeeWindown.fxml"));
            Parent EmployeeWindow = loader.load();
            EmployeeWindownController controller = (EmployeeWindownController)loader.getController();
            controller.setEmployee(employee);
            controller.SetViewMode();
            Stage stage = new Stage(StageStyle.UNIFIED);
            Scene scene = new Scene(EmployeeWindow);     
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginWindownController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void  LoadRoomManagement(){
        if(RoomMngmLoaded == false){
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/bookstore/GUI/BookManagement.fxml"));
                Parent RoomManagement = loader.load();
                BookManagementController controller = (BookManagementController)loader.getController();
                BookDTO bDTO = new BookDTO();
                controller.setListBooks(bDTO.GetAllBooks());
                controller.setListCategores(bDTO.GetAllCategories());
                MgmtPane.getChildren().add(RoomManagement);
                RoomMngmLoaded = true;
            } catch (IOException ex) {
                System.out.println("LoadRoomManagement fail");
            }    
        }
        SwitchTab("BooKManagement");    
    }
    
     private void  LoadImportManagement(){
        if(ImportMngmLoaded == false){
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/bookstore/GUI/ImportManagement.fxml"));
                Parent ImportManagement = loader.load();
                ImportManagementController controller = (ImportManagementController)loader.getController();
                controller.Load();
                MgmtPane.getChildren().add(ImportManagement);
                ImportMngmLoaded = true; 
            } catch (IOException ex) {
                System.out.println("LoadRoomManagement fail");
            }
        }
        SwitchTab("ImportManagement");      
    }
    
    private void SwitchTab(String tab){
        for (Node pane : MgmtPane.getChildren()) {
            if(pane.getId().compareTo(tab) == 0){
                pane.setVisible(true);
            } 
            else{
                pane.setVisible(false);
            }     
        }
        
        
        
    }
    
}
