/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.GUI;

import bookstore.BLL.Book;
import bookstore.DAL.BookDTO;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Orics
 */
public class BookWindownController implements Initializable {

    @FXML
    private TextField BookID_TextField;
    
    @FXML
    private TextField Title_TextField;
    
    @FXML
    private ComboBox<String> Category_ComboBox;
    
    @FXML
    private TextField Category_TextField;
    
    @FXML
    private Label AnotherCategory_Label;
    
    
    @FXML
    private TextField Author_TextField;
    
    @FXML
    private TextField Publisher_TextField;
    
    @FXML
    private TextField Price_TextField;
    
    @FXML
    private TextField Quantity_TextField;
    
    @FXML
    private Button Image_Button;
    
    @FXML
    private Label ChangeImage_Label;
    
    @FXML
    private Button Attack_Button;
    
    @FXML
    private Label Notif_Label;
    
    private ImportWindownController importWindownController;
    private BookManagementController bookManagementController;
    private ImportManagementController importManagementController;
    private Stage stage;
    private Book book;
    private String mode = "Edit";

    public BookManagementController getBookManagementController() {
        return bookManagementController;
    }

    public ImportManagementController getImportManagementController() {
        return importManagementController;
    }

    public void setBookManagementController(BookManagementController bookManagementController) {
        this.bookManagementController = bookManagementController;
    }

    public void setImportManagementController(ImportManagementController importManagementController) {
        this.importManagementController = importManagementController;
    }

    public ImportWindownController getImportWindownController() {
        return importWindownController;
    }

    public void setImportWindownController(ImportWindownController importWindownController) {
        this.importWindownController = importWindownController;
    }

    
        
    public Stage getStage() {
        return stage;
    }

    public Book getBook() {
        return book;
    }

    public void setStage(Stage stage) {
        this.stage = stage;    
    }

    public void setBook(Book book) {
        this.book = book;
        LoadEdit();
    }
    
    
    public void SetNewMode(){
        BookID_TextField.setDisable(true);
        Attack_Button.setVisible(true);
        Attack_Button.setText("Save");
        mode = "New";
        LoadNew();
    }
    
    public void SetEditMode(){
        BookID_TextField.setDisable(true);
        Attack_Button.setVisible(true);
        Attack_Button.setText("Save");
        mode = "Edit";
        LoadEdit();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AnotherCategory_Label.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(Category_ComboBox.isVisible()== true){
                    Category_ComboBox.setVisible(false);
                    Category_TextField.setVisible(true);
                   // Category_TextField.setText("");
                }
                else{
                    Category_ComboBox.setVisible(true);
                    Category_TextField.setVisible(false);  
                }
                
            }
        });
        
        Category_ComboBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Category_TextField.setText(Category_ComboBox.getValue());
            }
        });
        
        Attack_Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(mode.compareTo("New") == 0)
                    InsertToDatabase();
                else
                if(mode.compareTo("Edit") == 0){
                    UpdateBookToDatabase();
                }
                
                ReLoadAnotherWindown();
                
            }
        });
        
    }    
        
    
    private void  LoadEdit(){
        if(book != null){
            BookID_TextField.setDisable(true);
            Attack_Button.setVisible(true);
            Attack_Button.setText("Save");

            BookID_TextField.setText(book.getBookID());
            Title_TextField.setText(book.getTitle());
            Category_TextField.setText(book.getCategory());
            Author_TextField.setText(book.getAuthor());
            Publisher_TextField.setText(book.getPublisher());
            Price_TextField.setText(Integer.toString(book.getPrice()));
            Quantity_TextField.setText(Integer.toString(book.getQuantity()));

            BookDTO bDTO = new BookDTO();
            Category_ComboBox.getItems().clear();
            for (String category : bDTO.GetAllCategories()) {
                Category_ComboBox.getItems().add(category);
            }
            Category_ComboBox.setValue(book.getCategory());

            Image Img = new Image(getClass().getResourceAsStream("/bookstore/GUI/Images/Img_0001.jpg"));
            try {
               Img = new Image("E:/NetBeans/Projects/BookStore/src/bookstore/GUI/Images/Img_0002.jpg");
            } catch (Exception e) {
            }    
            BackgroundImage bgImg = new BackgroundImage(Img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(150, 200, true, true, true, true));
            Background bg = new Background(bgImg);
            Image_Button.setBackground(bg);
        }
    }
    
    private void LoadNew(){
        BookDTO bDTO = new BookDTO();
        Category_ComboBox.getItems().clear();
        for (String category : bDTO.GetAllCategories()) {
            Category_ComboBox.getItems().add(category);
        }
        Attack_Button.setText("Add");
    }
    
    private void InsertToDatabase(){
        book = new Book();
        book.setTitle(Title_TextField.getText());
        book.setCategory(Category_TextField.getText());
        book.setAuthor(Author_TextField.getText());
        book.setPublisher(Publisher_TextField.getText());
        try {
            book.setPrice(Integer.parseInt(Price_TextField.getText()));
            book.setQuantity(Integer.parseInt(Quantity_TextField.getText()));
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Input invalid value!");
            alert.setContentText("Please enter valid value");
            alert.showAndWait();
            return;
        }
        book.setImgUrl("");

        BookDTO bDTO = new BookDTO();
        if(bDTO.InsertData(book)==true){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("");
            alert.setContentText("Successful");
            alert.showAndWait();   

            if(stage != null)
                stage.close();
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("");
            alert.setHeaderText("Add a new book failed !");
            alert.showAndWait(); 
        }
    }
    
    private void UpdateBookToDatabase(){
        if(book != null){
            book.setTitle(Title_TextField.getText());
            book.setCategory(Category_TextField.getText());
            book.setAuthor(Author_TextField.getText());
            book.setPublisher(Publisher_TextField.getText());
            try {
                book.setPrice(Integer.parseInt(Price_TextField.getText()));
                book.setQuantity(Integer.parseInt(Quantity_TextField.getText()));
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText("Input invalid value!");
                alert.setContentText("Please enter valid value");
                alert.showAndWait();
                return;
            } 
            book.setImgUrl(book.getImgUrl());
            
            BookDTO bDTO = new BookDTO();
            if(bDTO.UpdateData(book)==true){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("");
                alert.setContentText("Successful");
                alert.showAndWait(); 
                
                if(stage != null)
                    stage.close();
            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("");
                alert.setHeaderText("Add a new book failed !");
                alert.showAndWait(); 
            }   
        } 
    }  
    
    private void ReLoadAnotherWindown(){
        if(bookManagementController != null){
            bookManagementController.Load();
        }
        if(importManagementController != null){
            importManagementController.Load();
        }
        if(importWindownController!=null){
            importWindownController.Load();
        }
    }
    
}
