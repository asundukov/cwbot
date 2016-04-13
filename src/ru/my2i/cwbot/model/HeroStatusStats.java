package ru.my2i.cwbot.model;

import ru.my2i.cwbot.hero.action.ActionType;

public class HeroStatusStats {
  private String name;
  private Integer level;
  private Castle castle;
  private Integer exp;
  private Integer nextLevelExp;
  private Integer gold;
  private Integer stamina;
  private Integer maxStamina;
  private ActionType actionType;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getLevel() {
    return level;
  }

  public void setLevel(Integer level) {
    this.level = level;
  }

  public Castle getCastle() {
    return castle;
  }

  public void setCastle(Castle castle) {
    this.castle = castle;
  }

  public Integer getExp() {
    return exp;
  }

  public void setExp(Integer exp) {
    this.exp = exp;
  }

  public Integer getNextLevelExp() {
    return nextLevelExp;
  }

  public void setNextLevelExp(Integer nextLevelExp) {
    this.nextLevelExp = nextLevelExp;
  }

  public Integer getGold() {
    return gold;
  }

  public void setGold(Integer gold) {
    this.gold = gold;
  }

  public Integer getStamina() {
    return stamina;
  }

  public void setStamina(Integer stamina) {
    this.stamina = stamina;
  }

  public Integer getMaxStamina() {
    return maxStamina;
  }

  public void setMaxStamina(Integer maxStamina) {
    this.maxStamina = maxStamina;
  }

  public ActionType getCurrentAction() {
    return actionType;
  }

  public void setCurrentAction(ActionType actionType) {
    this.actionType = actionType;
  }

  @Override
  public String toString() {
    return "HeroStatusStats{" +
        "name='" + name + '\'' +
        ", level=" + level +
        ", castle=" + castle +
        ", exp=" + exp +
        ", nextLevelExp=" + nextLevelExp +
        ", gold=" + gold +
        ", stamina=" + stamina +
        ", maxStamina=" + maxStamina +
        ", actionType=" + actionType +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    HeroStatusStats that = (HeroStatusStats) o;

    if (name != null ? !name.equals(that.name) : that.name != null) return false;
    if (level != null ? !level.equals(that.level) : that.level != null) return false;
    if (castle != that.castle) return false;
    if (exp != null ? !exp.equals(that.exp) : that.exp != null) return false;
    if (nextLevelExp != null ? !nextLevelExp.equals(that.nextLevelExp) : that.nextLevelExp != null) return false;
    if (gold != null ? !gold.equals(that.gold) : that.gold != null) return false;
    if (stamina != null ? !stamina.equals(that.stamina) : that.stamina != null) return false;
    if (maxStamina != null ? !maxStamina.equals(that.maxStamina) : that.maxStamina != null) return false;
    return actionType.toString().equals(that.actionType.toString());
  }

  @Override
  public int hashCode() {
    int result = name != null ? name.hashCode() : 0;
    result = 31 * result + (level != null ? level.hashCode() : 0);
    result = 31 * result + (castle != null ? castle.hashCode() : 0);
    result = 31 * result + (exp != null ? exp.hashCode() : 0);
    result = 31 * result + (nextLevelExp != null ? nextLevelExp.hashCode() : 0);
    result = 31 * result + (gold != null ? gold.hashCode() : 0);
    result = 31 * result + (stamina != null ? stamina.hashCode() : 0);
    result = 31 * result + (maxStamina != null ? maxStamina.hashCode() : 0);
    result = 31 * result + (actionType != null ? actionType.hashCode() : 0);
    return result;
  }
}
