package com.epam.labs.massacre.battle;

import com.epam.labs.massacre.domain.Xenos;

import java.util.List;

import lombok.val;
import lombok.var;

public final class BattleGround {

  private Xenos[][] battleGround;

  public Xenos[][] initializeBattleGround(List<Xenos> army1, List<Xenos> army2) {
    setBattleGroundSize(army1.size());
    placeArmiesOnBattleGround(army1, army2);
    return battleGround;
  }

  private void setBattleGroundSize(int armySize) {
    val ySize = 10;         //hardcoded in version 1.0
    battleGround = new Xenos[armySize][ySize];
  }

  private void placeArmiesOnBattleGround(List<Xenos> army1, List<Xenos> army2) {
    for (var i = 0; i < battleGround.length; i++) {
      battleGround[i][0] = army1.get(i);
      battleGround[i][battleGround[0].length - 1] = army2.get(i);
    }
  }
}
