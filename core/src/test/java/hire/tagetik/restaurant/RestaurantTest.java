package hire.tagetik.restaurant;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.Test;

public class RestaurantTest {

  @Test
  void testReservation() {

    Date reservationDate = new Date();

    Reservation reservation = new Reservation("customer", 4, reservationDate);

    assertEquals("customer", reservation.customer());
    assertEquals(4, reservation.people());
    assertEquals(reservationDate, reservation.date());
  }

  @Test
  void testSortedReservation() {
    Reservation reservation = new Reservation("customer1", 4, new Date());
    Reservation reservation1 = new Reservation("customer2", 4, new Date());
    Reservation reservation2 = new Reservation("customer3", 4, new Date());

    List<Reservation> r = new ArrayList<>();
    r.add(reservation2);
    r.add(reservation);
    r.add(reservation1);

    Restaurant restaurant = new Restaurant(r);

    List<Reservation> reservations = restaurant.getReservations();

    assertEquals(List.of(reservation, reservation1, reservation2), reservations);
  }

  @Test
  void testTables() {

    Table table = new Table("tableA", 5);

    assertEquals(5, table.getSeats());
    assertEquals("tableA", table.getName());
  }

  @Test
  void testRestaurant() {
    Table tableA = new Table("tableA", 5);
    Table tableB = new Table("tableB", 3);
    List<Table> tables = new ArrayList<>();
    tables.add(tableA);
    tables.add(tableB);

    Restaurant restaurant = new Restaurant(List.of(), tables);

    assertEquals(restaurant.getTables(), tables);
  }

  @Test
  void testNewReservation() {
    Table tableA = new Table("tableA", 5);
    Table tableB = new Table("tableB", 3);
    List<Table> tables = new ArrayList<>();
    tables.add(tableA);
    tables.add(tableB);

    Reservation reservation = new Reservation("customer", 3, new Date());
    List<Reservation> r = new ArrayList<>();
    r.add(reservation);

    Restaurant restaurant = new Restaurant(r, tables);

    restaurant.addReservation(new Reservation("customer1", 3, new Date()));
    assertEquals(2, restaurant.getReservations().size());
  }

  @Test
  void testOnCustomerArrive() {
    Table tableA = new Table("tableA", 5);
    Table tableB = new Table("tableB", 3);
    List<Table> tables = new ArrayList<>();
    tables.add(tableA);
    tables.add(tableB);

    Reservation reservation = new Reservation("customer", 3, new Date());
    List<Reservation> r = new ArrayList<>();
    r.add(reservation);

    Restaurant restaurant = new Restaurant(r, tables);

    restaurant.onArrive(new Customer("customer", 3));
    assertEquals(0, restaurant.getReservations().size());
  }
}
