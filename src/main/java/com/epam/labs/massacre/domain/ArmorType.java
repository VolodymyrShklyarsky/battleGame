package com.epam.labs.massacre.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ArmorType {

  LIGHT_ARMOR(50, "Light Armor"),
  HEAVY_ARMOR(100, "Heavy Armor");

  private int defencePoints;
  private String name;
}
