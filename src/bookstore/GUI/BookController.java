/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.GUI;

import bookstore.BLL.Book;
import bookstore.BLL.Employee;
import bookstore.DAL.EmployeeDTO;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Orics
 */
public class BookController implements Initializable {
    
    @FXML
    private AnchorPane root;
    
    @FXML
    private Button Image_Button;
    
    @FXML
    private Label Quantity_Label;
    
    @FXML
    private Label Title_Label;
    
    
    
    
    private Book book;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
        Load();
    }
    
    
    public void Load(){
        if(this.book != null){
            if(true){
                Image Img = new Image(getClass().getResourceAsStream("/bookstore/GUI/Images/Img_0001.jpg"));
                try {
                   Img = new Image("E:/NetBeans/Projects/BookStore/src/bookstore/GUI/Images/Img_0002.jpg");
                } catch (Exception e) {
                }    
                BackgroundImage bgImg = new BackgroundImage(Img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(150, 200, true, true, true, true));
                Background bg = new Background(bgImg);
                Image_Button.setBackground(bg);
            }
            if(book.getQuantity() < 20){
                Quantity_Label.setStyle("-fx-background-color: red; " +
                                        "-fx-background-radius : 20;" +
                                        "-fx-border-radius: 20; " +
                                        "-fx-border-color: black;" +
                                        "-fx-font-weight: bold;" +
                                        "-fx-text-fill: white;");
                Quantity_Label.setTextFill(Color.WHITE);
            }
            else
            {
                Quantity_Label.setStyle("-fx-background-color: yellow; " +
                                        "-fx-background-radius : 20;" +
                                        "-fx-border-radius: 20; " +
                                        "-fx-border-color: black;" +
                                        "-fx-font-weight: bold;" +
                                        "-fx-text-fill: black;");
            }
               
            Quantity_Label.setText(Integer.toString(book.getQuantity()));
            
            Title_Label.setText(book.getTitle());
            Title_Label.setWrapText(true);
        }
    }
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image_Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) { 
                System.out.println("event click book");
                OpenBookWindown();
            }
        });
    }    

    
    
    
    private void OpenBookWindown(){
        try {          
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/bookstore/GUI/BookWindown.fxml"));
            Parent BookWindown = loader.load();
            BookWindownController controller = (BookWindownController)loader.getController();
            controller.setBook(book);
            Stage stage = new Stage(StageStyle.UNIFIED);
            Scene scene = new Scene(BookWindown);     
            stage.setScene(scene);    
            stage.show();
            
        } catch (IOException ex) {
            Logger.getLogger(LoginWindownController.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    
    
}
