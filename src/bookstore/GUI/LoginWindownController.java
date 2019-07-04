/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.GUI;


import bookstore.BLL.CheckDataType;
import bookstore.BLL.Employee;
import bookstore.DAL.EmployeeDTO;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Orics
 */
public class LoginWindownController implements Initializable {

    private Stage stage = null;
    private double xOffset = 0;
    private double yOffset = 0;   
    
    @FXML
    private AnchorPane root;
    
    @FXML
    private Pane LogoPane;
    
    @FXML
    private TextField LoginUsername_TextField;
    
    @FXML
    private TextField LoginPassword_TextField;
    
    @FXML
    private TextField ConnectString_Label;
    
    @FXML
    private TextField DatabaseUsername_TextField;
    
    @FXML
    private TextField DatabasePassword_TextField;
    
    @FXML
    private Button Login_Button;
    
    @FXML
    private Button Exit_Button;
    
    
    @FXML
    private Button Connect_Button;
    
    @FXML            
    private Label LoginNotif_Label;
    
    @FXML            
    private Label LoginMgmt_Label;
    
    @FXML   
    private Label DataMgmt_Label;
    
    @FXML
    private Label DatabaseNotif_Label;
    
    @FXML
    private CheckBox Remember_CheckBox;
   
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    public Stage getStage(){
        return stage;
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //set 
        LoginMgmt_Label.setVisible(false);
        LoginNotif_Label.setText("");
        DatabaseNotif_Label.setText("");
        
        
      
        LoginUsername_TextField.setOnMouseClicked((new EventHandler<MouseEvent>() {             
            @Override
            public void handle(MouseEvent event) {
              //  HideLoginNotification();
            }
        }));
        
        LoginPassword_TextField.setOnMouseClicked((new EventHandler<MouseEvent>() {             
            @Override
            public void handle(MouseEvent event) {
               // HideLoginNotification();
            }
        }));
        
        Login_Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {        
                OpenMainWindown();
            }
        });
        
        Exit_Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Envet exit");
                System.exit(0);
                System.out.println("Envet exityyyy");
            }
        });
        
        root.setOnMouseDragged((new EventHandler<MouseEvent>() {             
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX() - xOffset );
                stage.setY(event.getScreenY() - yOffset );
            }
        }));
        
        root.setOnMousePressed((new EventHandler<MouseEvent>() {             
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();  
                stage.setX(event.getScreenX() - xOffset );
                stage.setY(event.getScreenY() - yOffset );
            }
        }));
        
        DataMgmt_Label.setOnMouseClicked((new EventHandler<MouseEvent>() {             
            @Override
            public void handle(MouseEvent event) {
                SwitchToDatabaseManagement();
            }
        }));
        
        LoginMgmt_Label.setOnMouseClicked((new EventHandler<MouseEvent>() {             
            @Override
            public void handle(MouseEvent event) {     
               SwitchToHotelManagement();
            }
        }));
        
        Connect_Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               
            }
        });
    }      
         
    private boolean CheckInput(){
        //Kiem tra Username khac rong va la so tu nhien
        if(LoginUsername_TextField.getText().compareTo("") == 0)
            return false;   
        if((new CheckDataType()).isNumeric(LoginUsername_TextField.getText())== false)
            return false; 
        
        //Kiem tra Username khac rong
        if(LoginPassword_TextField.getText().compareTo("") == 0)
            return false;
        
        return true;
    }
    
    private void OpenMainWindown() {  
        if(CheckInput()== true){
            String user = LoginUsername_TextField.getText();
            String pass = LoginPassword_TextField.getText();
            if ((new EmployeeDTO()).CheckLogin(user, pass)==true){
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/bookstore/GUI/MainWindown.fxml"));
                    Parent mainwindown = loader.load();
                    MainWindownController controller = (MainWindownController)loader.getController();
                    Employee emp = (new EmployeeDTO()).GetNhanVien(user);
                    controller.setEmployee(emp);
                    Stage stage = new Stage(StageStyle.UNDECORATED);
                    Scene scene = new Scene(mainwindown);     
                    stage.setScene(scene);
                    this.stage.close();             
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(LoginWindownController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else {
                System.out.println("Not Correct");
            }
        }
        else
        {
             System.out.println("Input fail");
        }
    }
    
    private void SwitchToDatabaseManagement(){ 
        DataMgmt_Label.setVisible(false);
        LoginMgmt_Label.setVisible(true);
        LogoPane.setLayoutX(100);
    } 
    
    private void SwitchToHotelManagement() { 
        DataMgmt_Label.setVisible(true);
        LoginMgmt_Label.setVisible(false);
        LogoPane.setLayoutX(450);
    } 
    
    
}
