package ru.my2i.cwbot.model;

import ru.my2i.cwbot.model.kraft.KraftItem;

import java.util.Map;

public class QuestResult {
  private int exp;
  private int gold;

  private Map<KraftItem, Integer> items;

  public int getExp() {
    return exp;
  }

  public void setExp(int exp) {
    this.exp = exp;
  }

  public int getGold() {
    return gold;
  }

  public void setGold(int gold) {
    this.gold = gold;
  }

  public Map<KraftItem, Integer> getItems() {
    return items;
  }

  public void setItems(Map<KraftItem, Integer> items) {
    this.items = items;
  }

  @Override
  public String toString() {
    return "QuestResult{" +
        "exp=" + exp +
        ", gold=" + gold +
        ", items=" + items +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    QuestResult that = (QuestResult) o;

    if (exp != that.exp) return false;
    if (gold != that.gold) return false;
    return items != null ? items.equals(that.items) : that.items == null;
  }

  @Override
  public int hashCode() {
    int result = exp;
    result = 31 * result + gold;
    result = 31 * result + (items != null ? items.hashCode() : 0);
    return result;
  }
}
