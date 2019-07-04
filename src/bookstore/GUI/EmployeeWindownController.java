/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.GUI;

import bookstore.BLL.Employee;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Orics
 */
public class EmployeeWindownController implements Initializable {

    
    @FXML
    private Label EmpID_Label;
    
    @FXML
    private TextField EmpID_TextField;
    
    @FXML
    private PasswordField Password_PasswordField;
    
    @FXML
    private TextField Role_TextField;
    
    @FXML
    private TextField Name_TextField;
    
    @FXML
    private ComboBox<String> Gender_ComboBox;
    
    @FXML
    private DatePicker DateOfBirth_DatePicker;
    
    @FXML
    private Button Attack_Button;
    
    private Stage stage;
    private Employee employee;
    private String mode = "View";

    
    public Stage getStage() {
        return stage;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
        LoadEmployeeInfor();
    }
    
    public void SetViewMode(){
        mode = "View";
        EmpID_TextField.setEditable(false);
        Password_PasswordField.setEditable(false);
        Role_TextField.setEditable(false);
        Name_TextField.setEditable(false);
        Gender_ComboBox.getEditor().setEditable(false);
        DateOfBirth_DatePicker.setEditable(false);
        Attack_Button.setVisible(false);
    }
    
    public void SetNewMode(){
        mode = "New";
        EmpID_Label.setVisible(false);
        EmpID_TextField.setVisible(false);
        Password_PasswordField.setEditable(true);
        Role_TextField.setEditable(true);
        Name_TextField.setEditable(true);
        Gender_ComboBox.getEditor().setEditable(true);
        DateOfBirth_DatePicker.setEditable(true);
        Attack_Button.setVisible(true);
        
        Password_PasswordField.setText("");
        Role_TextField.setText("");
        Name_TextField.setText("");
        Gender_ComboBox.getItems().addAll("Male","Femal");
        Gender_ComboBox.setValue("Male");
        DateOfBirth_DatePicker.setValue(LocalDate.now());
        Attack_Button.setText("Create");
        
    }
    
    public void SetEditMode(){
        mode = "Edit";
        EmpID_TextField.setEditable(true);
        Password_PasswordField.setEditable(true);
        Role_TextField.setEditable(true);
        Name_TextField.setEditable(true);
        Gender_ComboBox.getEditor().setEditable(true);
        DateOfBirth_DatePicker.setEditable(true);
        Attack_Button.setText("Save");
        
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    private void LoadEmployeeInfor(){
        if(employee != null){
            EmpID_TextField.setText(employee.getEmployeeID());
            Password_PasswordField.setText(employee.getPassword());
            Role_TextField.setText(employee.getRole());
            Name_TextField.setText(employee.getName());
            Gender_ComboBox.setValue(employee.getGender());
            DateOfBirth_DatePicker.setValue(LocalDate.MAX);
        }  
    }
    
}
