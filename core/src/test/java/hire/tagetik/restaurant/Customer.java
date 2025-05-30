package hire.tagetik.restaurant;

public class Customer {

  private final String name;
  private final int people;

  Customer(String name, int people) {
    this.name = name;
    this.people = people;
  }

  public String getName() {
    return this.name;
  }

  public int getPeople() {
    return this.people;
  }
}
