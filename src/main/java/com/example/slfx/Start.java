package com.example.slfx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Start extends Application {
    Stage menu;
    public static void main(String args[]){
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        menu = stage;
        Scene scene = new Scene(root);
        Image img =new Image(getClass().getResourceAsStream("icon.png"));
        stage.getIcons().add(img);
        stage.setScene(scene);
        stage.show();
    }
    public void playgame(ActionEvent e) throws IOException {
        Parent y =FXMLLoader.load(getClass().getResource("First layout.fxml"));
        menu = (Stage) ((Node)e.getSource()).getScene().getWindow();
        Scene sc = new Scene(y);
        menu.setScene(sc);
        menu.show();
    }

}
