package ru.my2i.cwbot.model.arena;

import ru.my2i.cwbot.model.Castle;

public class Competitor {
  private String name;
  private Castle castle;
  private int hp;
  private int maxHp;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Castle getCastle() {
    return castle;
  }

  public void setCastle(Castle castle) {
    this.castle = castle;
  }

  public int getHp() {
    return hp;
  }

  public void setHp(int hp) {
    this.hp = hp;
  }

  public int getMaxHp() {
    return maxHp;
  }

  public void setMaxHp(int maxHp) {
    this.maxHp = maxHp;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Competitor that = (Competitor) o;

    if (hp != that.hp) return false;
    if (maxHp != that.maxHp) return false;
    if (name != null ? !name.equals(that.name) : that.name != null) return false;
    return castle == that.castle;
  }

  @Override
  public int hashCode() {
    int result = name != null ? name.hashCode() : 0;
    result = 31 * result + (castle != null ? castle.hashCode() : 0);
    result = 31 * result + hp;
    result = 31 * result + maxHp;
    return result;
  }
}
