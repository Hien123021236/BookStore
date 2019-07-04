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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
    private TextField Category_TextField;
    
    @FXML
    private TextField Author_TextField;
    
    @FXML
    private TextField Publisher_TextField;
    
    @FXML
    private TextField Price_TextField;
    
    @FXML
    private TextField Quantity_TextField;
    
    @FXML
    private Button Iamge_Button;
    
    @FXML
    private Label ChangeImage_Label;
    
    @FXML
    private Button Attack_Button;
    
    @FXML
    private Label Notif_Label;
    
    
    
    private Stage stage;
    private Book book;
    private String mode = "Edit";

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
        Load();
    }
    
    
    public void SetNewMode(){
        BookID_TextField.setDisable(true);
        Attack_Button.setVisible(true);
        Attack_Button.setText("Save");
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Attack_Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                UpdateBookToDatabase();
            }
        });
    }    
    
    
    
    private void  Load(){
        if(book != null){
            if(mode.compareTo("Edit")== 0){               
                BookID_TextField.setDisable(true);
                Attack_Button.setVisible(true);
                Attack_Button.setText("Save");                   
            }     
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
                Notif_Label.setText("Failed !!!");
                Notif_Label.setTextFill(Color.RED);
                return;
            } 
            book.setImgUrl(book.getImgUrl());
            
            BookDTO bDTO = new BookDTO();
            if(bDTO.UpdateData(book)==true){
                Notif_Label.setText("Successfull");
                Notif_Label.setTextFill(Color.GREEN);
            }
            else
            {
                Notif_Label.setText("Failed !!!");
                Notif_Label.setTextFill(Color.RED);
            }
            
        } 
    }  
    
    
}
