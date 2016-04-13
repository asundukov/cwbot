package ru.my2i.cwbot.model.arena;

import java.util.ArrayList;
import java.util.List;

public class BattleState {
  private List<Competitor> competitors = new ArrayList<>();

  private BattleStatus status;

  private int expProfit;

  public BattleState(BattleStatus status) {
    this.status = status;
  }

  public List<Competitor> getCompetitors() {
    return competitors;
  }

  public void setCompetitors(List<Competitor> competitors) {
    this.competitors = competitors;
  }

  public BattleStatus getStatus() {
    return status;
  }

  public void setStatus(BattleStatus status) {
    this.status = status;
  }

  public int getExpProfit() {
    return expProfit;
  }

  public void setExpProfit(int expProfit) {
    this.expProfit = expProfit;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    BattleState that = (BattleState) o;

    if (competitors != null ? !competitors.equals(that.competitors) : that.competitors != null) return false;
    return status == that.status;
  }

  @Override
  public int hashCode() {
    int result = competitors != null ? competitors.hashCode() : 0;
    result = 31 * result + (status != null ? status.hashCode() : 0);
    return result;
  }
}
