package com.epam.labs.massacre.statistics;

import com.epam.labs.massacre.util.UserInterfaceMessages;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.val;
import scala.Tuple2;

public final class WeaponStatistics {

  public static void getAfterbattleStatistics(Map<Tuple2<String, String>, Integer> weaponMap) {
    val sortedWeaponMap = weaponMap
        .entrySet()
        .stream()
        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
        .collect(Collectors.toMap(
            Map.Entry::getKey,
            Map.Entry::getValue,
            (oldValue, newValue) -> oldValue, LinkedHashMap::new));

    val weaponSum = sortedWeaponMap
        .values()
        .stream()
        .reduce(0, Integer::sum);

    sortedWeaponMap.forEach((key, value) ->
        UserInterfaceMessages.showWeaponStatisticsMessage(key._1, key._2, value, weaponSum));
  }
}
