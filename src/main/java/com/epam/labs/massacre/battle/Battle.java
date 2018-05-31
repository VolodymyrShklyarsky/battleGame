package com.epam.labs.massacre.battle;

import com.epam.labs.massacre.domain.Xenos;
import com.epam.labs.massacre.util.UserInterfaceMessages;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;

import lombok.AllArgsConstructor;
import lombok.val;
import lombok.var;
import scala.Tuple2;

@AllArgsConstructor
public final class Battle {

  private final Map<Tuple2<String, String>, Integer> weaponMap = new ConcurrentHashMap<>();
  private List<Xenos> army1;
  private List<Xenos> army2;
  private Xenos[][] battleGround;

  public void startBattle() {
    val clash = new Clash();
    val executor = Executors.newFixedThreadPool(battleGround.length);
    while (true) {
      executor.submit(clash);
      if (army1.size() + army2.size() == battleGround.length) {
        executor.shutdownNow();
        if (army1.size() > army2.size()) {
          UserInterfaceMessages.showEndBattleMessage(army1.get(0).getFaction().getName(),
              army1.size());
          break;
        } else {
          UserInterfaceMessages.showEndBattleMessage(army2.get(0).getFaction().getName(),
              army2.size());
          break;
        }
      }
    }
  }

  private void movePair(Tuple2<Integer, Integer> xenos1Coordinates,
      Tuple2<Integer, Integer> xenos2Coordinates, int direction) {

    if (direction != 1 && direction != -1) {
      return;
    }
    val xenos1XAxis = xenos1Coordinates._1;
    var xenos1YAxis = xenos1Coordinates._2;
    val xenos1 = battleGround[xenos1XAxis][xenos1YAxis];
    val xenos2YAxis = xenos2Coordinates._2;

    if (xenos1.getWeaponRange() >= Math.abs(xenos2YAxis - xenos1YAxis)) {
      val isXenosDead = xenosFight(xenos1Coordinates, xenos2Coordinates);
      if (isXenosDead) {
        return;
      }
    } else {
      xenos1YAxis = moveToEmptySpace(xenos1XAxis, xenos1YAxis, xenos1, direction);
    }
    movePair(xenos2Coordinates, new Tuple2<>(xenos1XAxis, xenos1YAxis), direction - 2);
  }

  private synchronized int moveToEmptySpace(int xOld, int yOld, Xenos xenos, int direction) {
    val yNew = linearMove(yOld, xenos, direction);
    if ((battleGround[xOld][yNew] == null)) {
      battleGround[xOld][yNew] = battleGround[xOld][yOld];
      battleGround[xOld][yOld] = null;
    }
    return yNew;
  }

  private synchronized int linearMove(int y, Xenos xenos, int dir) {
    return y + dir * xenos.getStepSize();
  }

  private synchronized boolean xenosFight(Tuple2<Integer, Integer> xenos1Coordinates,
      Tuple2<Integer, Integer> xenos2Coordinates) {
    val xenos1 = battleGround[xenos1Coordinates._1][xenos1Coordinates._2];
    val xenos2 = battleGround[xenos2Coordinates._1][xenos2Coordinates._2];

    xenos1.attack(xenos2);

    if (xenos2.getHealthPoints() <= 0) {
      eliminateXenos(xenos2Coordinates);
      weaponMap.merge(new Tuple2<>(xenos1.getWeaponName(), xenos2.getArmorName()),
          1, (oldValue, newValue) -> oldValue + newValue);
      UserInterfaceMessages.showXenosDeathMessage(xenos2.getFaction().getName(),
          xenos2.getArmorName(), xenos1.getWeaponName());
      return true;
    } else {
      return false;
    }
  }

  private synchronized void eliminateXenos(Tuple2<Integer, Integer> xenosCoordinates) {
    val xenos = battleGround[xenosCoordinates._1][xenosCoordinates._2];
    army1.remove(xenos);
    army2.remove(xenos);
    battleGround[xenosCoordinates._1][xenosCoordinates._2] = null;
  }

  public Map<Tuple2<String, String>, Integer> getWeaponMap() {
    return weaponMap;
  }

  private class Clash implements Runnable {

    @Override
    public void run() {
      warIsGoing();
    }

    private void warIsGoing() {
      for (var i = 0; i < battleGround.length; i++) {
       val xenosPair = new ArrayList<Tuple2<Integer, Integer>>();
        synchronized (this) {
          for (var j = 0; j < battleGround[0].length; j++) {
            if (battleGround[i][j] != null) {
              xenosPair.add(new Tuple2<>(i, j));
            }
          }
        }
        if (xenosPair.size() == 2) {
          movePair(xenosPair.get(0), xenosPair.get(1), 1);
        }
      }
    }
  }
}
