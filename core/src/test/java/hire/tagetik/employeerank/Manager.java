package hire.tagetik.employeerank;

public class Manager extends Employee {
  public Manager(double baseRank) {
    super(baseRank);
  }

  @Override
  double getRankMultiplier() {
    return 2.0;
  }
}
