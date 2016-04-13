package ru.my2i.cwbot.hero;

import ru.my2i.cwbot.model.HeroStatusStats;

public class Hero {
  private HeroStatusStats stats;

  private long lastArenaTime = System.currentTimeMillis() - 1000*60*60;

  public HeroStatusStats getStats() {
    return stats;
  }

  public void setStats(HeroStatusStats stats) {
    this.stats = stats;
  }

  public long getLastArenaTime() {
    return lastArenaTime;
  }

  public void setLastArenaTime(long lastArenaTime) {
    this.lastArenaTime = lastArenaTime;
  }
}
