package hire.tagetik.restaurant;

public class Table {

  private final String name;
  private final int seats;
  private boolean reserved;
  private Customer customer;

  Table(String name, int seats) {
    this.name = name;
    this.seats = seats;
  }

  public void addCustomer(Customer customer) {
    this.customer = customer;
  }

  public String getName() {
    return this.name;
  }

  public int getSeats() {
    return this.seats;
  }

  public boolean isReserved() {
    return this.reserved;
  }

  public boolean isFree() {
    return this.customer != null || !this.isReserved();
  }
}
