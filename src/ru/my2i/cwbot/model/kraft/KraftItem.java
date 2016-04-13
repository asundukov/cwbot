package ru.my2i.cwbot.model.kraft;

import java.util.HashMap;
import java.util.Map;

public enum KraftItem {

  THREAD(101, "Нитки"),
  BRANCH(102, "Ветки"),
  ANIMAL_SKIN(103, "Шкура животного"),
  ANIMAL_BONE(104, "Кость животного"),
  COAL(105, "Уголь"),
  CHARCOAL(106, "Древесный уголь"),
  POWDER(107, "Порошок"),
  IRON_ORE(108, "Железная руда"),
  DENSE_TEXTURE(109, "Плотная ткань"),
  SILVER_COAL(110, "Серебряная руда"),
  ALUMINIUM_COAL(111, "Алюминиевая руда"),
  MITHRIL_COAL(112, "Мифриловая руда"),
  PHILOSOPHER_STONE(113, "Филосовский камень"),
  ADAMANTITE_COAL(114,"Адамантитовая руда"),
  BLUE_CHRISTAL(115, "Синий кристалл"),
  SOLVENT(116, "Растворитель"),
  RED_CHRISTAL(117, "Красный кристалл"),
  THICKENER(118, "Загуститель");

  private int id;
  private String name;

  KraftItem(int id, String name) {
    this.id = id;
    this.name = name;
  }

  private static final Map<String, KraftItem> map;
  static {
    map = new HashMap<>();
    for (KraftItem v : KraftItem.values()) {
      map.put(v.name, v);
    }
  }

  public static KraftItem findByName(String name) {
    return map.getOrDefault(name, null);
  }
}
