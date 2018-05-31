package com.epam.labs.massacre.util;

import java.util.LinkedList;
import java.util.List;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserInterfaceMessages {

  public volatile List<String> deathsFx = new LinkedList<>();
  public String endBattleFx;
  public List<String> statisticsFx = new LinkedList<>();

  public synchronized void showXenosDeathMessage(String name, String armor, String weapon) {
    deathsFx.add(name + " in " + armor + " was killed from " + weapon + "\n");
  }

  public void showEndBattleMessage(String faction, int size) {
    endBattleFx = faction + " army killed more enemies and won!";
  }

  public void showWeaponStatisticsMessage(String weapon, String armor,
      int kills, int weaponSum) {
    statisticsFx.add(String.format("%s->%s - %.2f%%%n", weapon, armor,
        ((double) kills) / weaponSum * 100));
  }
}
