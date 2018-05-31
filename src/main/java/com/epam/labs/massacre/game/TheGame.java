package com.epam.labs.massacre.game;

import com.epam.labs.massacre.battle.Barracks;
import com.epam.labs.massacre.battle.Battle;
import com.epam.labs.massacre.battle.BattleGround;
import com.epam.labs.massacre.domain.Faction;
import com.epam.labs.massacre.statistics.WeaponStatistics;

import lombok.val;

public final class TheGame {

  public void startGame(int armySize) {
    val barracks = new Barracks();

    val necronArmy = barracks.getArmy(Faction.NECRONS, armySize);
    val orkArmy = barracks.getArmy(Faction.ORKZ, armySize);

    val battleGround = new BattleGround().initializeBattleGround(necronArmy, orkArmy);
    val battle = new Battle(necronArmy, orkArmy, battleGround);
    battle.startBattle();

    WeaponStatistics.getAfterbattleStatistics(battle.getWeaponMap());
  }
}
