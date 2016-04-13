package ru.my2i.cwbot.hero;

import ru.my2i.cwbot.model.Castle;

public class OtherHero {
  private String name;
  private Castle castle;

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

  @Override
  public String toString() {
    return "OtherHero{" +
        "name='" + name + '\'' +
        ", castle=" + castle +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    OtherHero otherHero = (OtherHero) o;

    if (name != null ? !name.equals(otherHero.name) : otherHero.name != null) return false;
    return castle == otherHero.castle;
  }

  @Override
  public int hashCode() {
    int result = name != null ? name.hashCode() : 0;
    result = 31 * result + (castle != null ? castle.hashCode() : 0);
    return result;
  }
}
