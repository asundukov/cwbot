package ru.my2i.cwbot.model.caravan;

import ru.my2i.cwbot.hero.OtherHero;

public class CaravanLooting {
  private CaravanLootingStatus status;
  private OtherHero enemy;
  private int goldProfit = 0;
  private int expProfit = 0;

  public CaravanLootingStatus getStatus() {
    return status;
  }

  public void setStatus(CaravanLootingStatus status) {
    this.status = status;
  }

  public OtherHero getEnemy() {
    return enemy;
  }

  public void setEnemy(OtherHero enemy) {
    this.enemy = enemy;
  }

  public int getGoldProfit() {
    return goldProfit;
  }

  public void setGoldProfit(int goldProfit) {
    this.goldProfit = goldProfit;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    CaravanLooting looting = (CaravanLooting) o;

    if (goldProfit != looting.goldProfit) return false;
    if (status != looting.status) return false;
    return enemy != null ? enemy.equals(looting.enemy) : looting.enemy == null;
  }

  @Override
  public int hashCode() {
    int result = status != null ? status.hashCode() : 0;
    result = 31 * result + (enemy != null ? enemy.hashCode() : 0);
    result = 31 * result + goldProfit;
    return result;
  }

  public void setExpProfit(int expProfit) {
    this.expProfit = expProfit;
  }
}
