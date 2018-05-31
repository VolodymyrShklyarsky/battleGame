package com.epam.labs.massacre.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Faction {

  ORKZ("Ork"),
  NECRONS("Necron");

  private String name;
}
