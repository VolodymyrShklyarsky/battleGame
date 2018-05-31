package com.epam.labs.massacre.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum WeaponType {

  CHOPPA(10, 1, 100, "Choppa", Faction.ORKZ),
  SHOOTA(15, 2, 80, "Shoota", Faction.ORKZ),
  BIG_SHOOTA(25, 2, 80, "Big Shoota", Faction.ORKZ),
  ROKKIT_LAUNCHA(30, 4, 70, "Rokkit Launcha", Faction.ORKZ),

  HYPERPHASE_SWORD(13, 1, 100, "Hyperphase Sword", Faction.NECRONS),
  GAUSS_FLAYER(17, 2, 90, "Gauss Flayer", Faction.NECRONS),
  TESLA_CARBINE(20, 3, 90, "Tesla Carbine", Faction.NECRONS),
  HEAT_RAY(25, 1, 80, "Heat Ray", Faction.NECRONS);

  private int damage;
  private int range;
  private int accuracyInPercents;
  private String name;
  private Faction faction;
}
