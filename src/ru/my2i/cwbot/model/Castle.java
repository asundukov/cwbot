package ru.my2i.cwbot.model;

import ru.my2i.cwbot.model.kraft.KraftItem;

import java.util.HashMap;
import java.util.Map;

public enum Castle {
  WHITE("Белого", "\uD83C\uDDE8\uD83C\uDDFE"),
  BLUE("Синего", "\uD83C\uDDEA\uD83C\uDDFA"),
  BLACK("Черного", "\uD83C\uDDEC\uD83C\uDDF5"),
  RED("Красного", "\uD83C\uDDEE\uD83C\uDDF2"),
  YELLOW("Желтого", "\uD83C\uDDFB\uD83C\uDDE6");

  private static final Map<String, Castle> mapByFlag = new HashMap<>();
  private static final Map<String, Castle> mapByName = new HashMap<>();

  static {
    for (Castle v : Castle.values()) {
      mapByFlag.put(v.flag, v);
      mapByName.put(v.name, v);
    }
  }

  private String name;
  private String flag;

  Castle(String name, String flag) {
    this.name = name;
    this.flag = flag;
  }

  public static Castle findByName(String colour) {
    return mapByName.getOrDefault(colour, null);
  }

  public static Castle findByFlag(String flag) {
    return mapByFlag.getOrDefault(flag, null);
  }

  public String getFlag() {
    return flag;
  }
}
