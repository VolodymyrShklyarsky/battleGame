package com.epam.labs.massacre.domain;

import lombok.Builder;
import lombok.Getter;

@Builder
public class Armor {

  @Getter
  private String name;
  private int defencePoints;
  private Boolean isBroken = false;

  public int reduceDamage(int incomingDamage) {
    if (!isBroken) {
      defencePoints -= incomingDamage;
      if (defencePoints <= 0) {
        isBroken = true;
        return Math.abs(defencePoints);
      } else {
        return 0;
      }
    }
    return incomingDamage;
  }
}
