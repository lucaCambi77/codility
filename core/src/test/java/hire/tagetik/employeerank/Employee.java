package hire.tagetik.employeerank;

public abstract class Employee {
  private final double baseRank;

  public Employee(double baseRank) {
    this.baseRank = baseRank;
  }

  abstract double getRankMultiplier();

  double getRank() {
    return getRankMultiplier() * baseRank;
  }
}
