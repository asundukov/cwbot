package ru.my2i.cwbot.model;

public enum QuestType {
  FOREST(1),
  CAVE(2);

  private int cost;

  private QuestType(int cost) {
    this.cost = cost;
  }

  public int getCost() {
    return cost;
  }
}
