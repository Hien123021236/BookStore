/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.GUI;

import bookstore.BLL.Book;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Orics
 */
public class BookManagementController implements Initializable {
    
    @FXML
    private AnchorPane BooKManagement;
    
    @FXML
    private Label ListBook_Label;
    
    @FXML
    private FlowPane ListBook_FlowPane;
    
    @FXML
    private Button Refresh_button;
    
    @FXML
    private VBox ListCategory_VBox;
    
    
    private ArrayList<Book> ListBooks;
    private ArrayList<String> ListCategores;

    public ArrayList<Book> getListBooks() {
        return ListBooks;
    }

    public ArrayList<String> getListCategores() {
        return ListCategores;
    }

    public void setListBooks(ArrayList<Book> ListBooks) {
        this.ListBooks = ListBooks;
        LoadBooks();
    }

    public void setListCategores(ArrayList<String> ListCategores) {
        this.ListCategores = ListCategores;
        LoadCategories(); 
    }
    
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Refresh_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Load();
            }
        });
    }    
    
    
    private void LoadBooks(){
        if(ListBooks != null){
            ListBook_FlowPane.getChildren().clear();
            for (int i = 0; i < ListBooks.size(); i++) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/bookstore/GUI/Book.fxml"));
                    Parent BookPane = loader.load();
                    BookController controller = (BookController)loader.getController();
                    controller.setBook(ListBooks.get(i));
                    controller.Load();
                    ListBook_FlowPane.getChildren().add(BookPane);
                    } catch (Exception e) {
                    }
            }
        }
    }
    
    private void LoadCategories(){
        if(ListCategores != null){
            ListCategory_VBox.getChildren().clear();
            RadioButton category = new RadioButton("All");
            ListCategory_VBox.getChildren().add(category);
            for (int i = 0; i < ListCategores.size(); i++) {
                category = new RadioButton(ListCategores.get(i));
                ListCategory_VBox.getChildren().add(category);
            }
        }
    }
    
    private void Load(){
        LoadBooks();
        LoadCategories(); 
    }
}
