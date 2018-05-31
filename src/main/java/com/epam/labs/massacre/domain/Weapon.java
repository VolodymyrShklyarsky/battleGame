package com.epam.labs.massacre.domain;

import java.util.Random;

import lombok.Builder;
import lombok.Getter;
import lombok.val;

@Builder
public class Weapon {

  @Getter
  private String name;
  @Getter
  private int range;
  private int damage;
  private int accuracyInPercents;
  private Faction faction;

  private static final Random RANDOM = new Random();

  public int shoot() {
    val hitRoll = RANDOM.nextInt(100) + 1;
    if (hitRoll <= accuracyInPercents) {
      return damage;
    } else {
      return 0;
    }
  }
}
