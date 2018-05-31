package com.epam.labs.massacre.main;

import com.epam.labs.massacre.game.TheGame;
import com.epam.labs.massacre.util.UserInterfaceMessages;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public final class MainController implements Initializable {

  private TheGame game = new TheGame();

  @FXML
  public TextField armySize;

  @FXML
  public TextArea output;

  @FXML
  public TextField result;

  @FXML
  public TextArea statistics;

  @FXML
  public void start() {
    output.clear();
    statistics.clear();
    game.startGame(Integer.parseInt(armySize.getText()));

    UserInterfaceMessages.deathsFx.forEach(m -> output.appendText(m));
    result.setText(UserInterfaceMessages.endBattleFx);
    UserInterfaceMessages.statisticsFx.forEach(m -> statistics.appendText(m));

    UserInterfaceMessages.deathsFx.clear();
    UserInterfaceMessages.statisticsFx.clear();
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {

  }
}
