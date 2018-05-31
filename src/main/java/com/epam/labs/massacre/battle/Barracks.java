package com.epam.labs.massacre.battle;

import com.epam.labs.massacre.domain.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.val;

public final class Barracks {

  public List<Xenos> getArmy(Faction faction, int armySize) {
    val army = Stream
        .generate(() -> new Xenos(faction))
        .limit(armySize)
        .collect(Collectors.toCollection(ArrayList::new));
    return prepareArmyForBattle(faction, army);
  }

  private List<Xenos> prepareArmyForBattle(Faction faction, List<Xenos> army) {
    val random = new Random();
    val armorList = Arrays.asList(ArmorType.values());
    val weaponList = Arrays.asList(WeaponType.values());
    val factionWeapon = getFactionWeapon(weaponList, faction);

    army.forEach(xenos -> {
      buildArmor(xenos, armorList, random);
      buildWeapon(xenos, factionWeapon, random);
    });
    return army;
  }

  private List<WeaponType> getFactionWeapon(List<WeaponType> weaponList, Faction faction) {
    return weaponList
        .stream()
        .filter(weapon -> weapon.getFaction().equals(faction))
        .collect(Collectors.toCollection(ArrayList::new));
  }

  private void buildArmor(Xenos xenos, List<ArmorType> armorList, Random random) {
    val randomArmor = armorList.get(random.nextInt(armorList.size()));
    xenos.setArmor(Armor
        .builder()
        .name(randomArmor.getName())
        .defencePoints(randomArmor.getDefencePoints())
        .isBroken(false)
        .build());
  }

  private void buildWeapon(Xenos xenos, List<WeaponType> factionWeapon, Random random) {
    val randomWeapon = factionWeapon.get(random.nextInt(factionWeapon.size()));
    xenos.setWeapon(Weapon
        .builder()
        .damage(randomWeapon.getDamage())
        .range(randomWeapon.getRange())
        .accuracyInPercents(randomWeapon.getAccuracyInPercents())
        .name(randomWeapon.getName())
        .faction(randomWeapon.getFaction())
        .build());
  }
}
