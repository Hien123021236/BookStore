/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore;

import bookstore.DAL.DatabaseAccess;
import bookstore.GUI.LoginWindownController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Orics
 */
public class BookStore extends Application {
    
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("GUI/LoginWindown.fxml"));
            Parent login = loader.load();
            LoginWindownController controller = (LoginWindownController)loader.getController();
            controller.setStage(stage);
            stage.initStyle(StageStyle.TRANSPARENT);
            Scene scene = new Scene(login);
            scene.setFill(Color.TRANSPARENT);
            stage.setScene(scene);
            stage.show();    
        } catch (IOException ex) {
            System.out.println("Error: open LoginWindown");
        }
        
        DatabaseAccess da = new DatabaseAccess();
        da.Test();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
