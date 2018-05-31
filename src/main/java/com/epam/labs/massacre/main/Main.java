package com.epam.labs.massacre.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import lombok.val;

public final class Main extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage stage) throws Exception {
    val loader = new FXMLLoader();
    Parent root = loader.load(Main.class.getClassLoader().getResourceAsStream("file.fxml"));
    stage.getIcons().add(new Image(Main.class.getClassLoader()
        .getResourceAsStream("images/icon.png")));
    stage.setTitle("Massacre on a Tomb World");
    stage.setScene(new Scene(root));
    stage.show();
  }
}
