package com.epam.labs.massacre.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode
public class Xenos {

  @Setter
  private Armor armor;
  @Setter
  private Weapon weapon;
  @Getter
  private Faction faction;
  @Getter
  private int healthPoints = 100;
  @Getter
  private int basicDamage = 5;
  @Getter
  private int stepSize = 1;

  private int id;
  private static int idMaker;

  public Xenos(Faction faction) {
    this.faction = faction;
    this.id = idMaker++;
  }

  public void attack(Xenos hostileXenos) {
    if (weapon != null) {
      hostileXenos.takeDamage(weapon.shoot());
    } else {
      hostileXenos.takeDamage(attackWithFists());
    }
  }

  public void takeDamage(int incomingDamage) {
    if (armor != null) {
      healthPoints -= armor.reduceDamage(incomingDamage);
    } else {
      healthPoints -= incomingDamage;
    }
  }

  private int attackWithFists() {
    return basicDamage;
  }

  public String getWeaponName() {
    return weapon.getName();
  }

  public int getWeaponRange() {
    return weapon.getRange();
  }

  public String getArmorName() {
    return armor.getName();
  }
}
