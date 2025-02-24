package hire.tagetik.employeerank;

public class Developer extends Employee {
  public Developer(double baseRank) {
    super(baseRank);
  }

  @Override
  double getRankMultiplier() {
    return 1.5;
  }
}
