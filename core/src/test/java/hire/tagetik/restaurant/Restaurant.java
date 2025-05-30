package hire.tagetik.restaurant;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Queue;

public class Restaurant {

  private final List<Reservation> reservations;
  private final List<Table> tables;
  private Queue<Customer> queue;

  Restaurant(List<Reservation> reservations, List<Table> tables) {
    this.reservations = reservations;
    this.tables = tables;
    this.tables.sort(Comparator.comparing(Table::getSeats));
  }

  Restaurant(List<Reservation> reservations) {
    this.reservations = reservations;
    this.tables = new ArrayList<>();
  }

  public List<Reservation> getReservations() {
    reservations.sort(Comparator.comparing(Reservation::customer));
    return reservations;
  }

  public void addReservation(Reservation reservations) {
    this.reservations.add(reservations);
  }

  public boolean onArrive(Customer customer) {
    for (Table table : tables) {
      if (customer.getPeople() <= table.getSeats()) {
        table.addCustomer(customer);
        return true;
      }
    }

    queue.add(customer);
    return false;
  }

  public void onLeave(Customer leavingCustomer) {

    for (Customer c : queue) {
      onArrive(c);
    }

  }

  public List<Table> getTables() {
    return this.tables;
  }

  public Table getTable(Table table) {
    return this.tables.stream()
        .filter(t -> t.getName().equals(table.getName()))
        .findFirst()
        .orElse(null);
  }

  public Queue<Customer> getQueue() {
    return queue;
  }
}
