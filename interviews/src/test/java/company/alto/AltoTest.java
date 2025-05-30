package company.alto;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class AltoTest {

  private RestManager restManager;

  @Test
  void shouldCreateTables() {
    List<Table> list = new ArrayList<>();
    Table table2 = new Table(2);
    Table table6 = new Table(6);
    list.add(table2);
    list.add(table6);

    restManager = new RestManager(list);

    assertEquals(list, restManager.getTables());
    assertEquals(table2, restManager.getTable(table2));
  }

  @Test
  void shouldNotAllowNotPreDefinedTables() {
    assertThrows(IllegalArgumentException.class, () -> new Table(7));
    assertThrows(IllegalArgumentException.class, () -> new Table(1));
  }

  @Test
  void shouldNotAllowNotPreDefinedClientsGroup() {
    assertThrows(IllegalArgumentException.class, () -> new ClientsGroup(7));
    assertDoesNotThrow(() -> new ClientsGroup(1));
  }

  @Test
  void givenTableNotAvailableThenClientGroupShouldWait() {
    List<Table> list = new ArrayList<>();
    list.add(new Table(2));

    restManager = new RestManager(list);
    ClientsGroup clientsGroup = new ClientsGroup(6);

    restManager.onArrive(clientsGroup);

    assertNull(restManager.lookup(clientsGroup));
  }

  @Test
  void givenTableAvailableThenClientGroupShouldBeServed() {
    List<Table> list = new ArrayList<>();
    Table table6 = new Table(6);
    list.add(table6);

    restManager = new RestManager(list);
    ClientsGroup clientsGroup = new ClientsGroup(6);

    restManager.onArrive(clientsGroup);

    assertEquals(table6, restManager.lookup(clientsGroup));
  }

  @Test
  void givenTableAvailableThenSmallerClientGroupsShouldBeServed() {
    List<Table> list = new ArrayList<>();
    Table table2 = new Table(2);
    Table table6 = new Table(6);
    list.add(table2);
    list.add(table6);

    restManager = new RestManager(list);
    ClientsGroup clientsGroup = new ClientsGroup(6);

    restManager.onArrive(clientsGroup);

    assertEquals(table6, restManager.lookup(clientsGroup));

    ClientsGroup secondClientsGroup = new ClientsGroup(6);
    restManager.onArrive(secondClientsGroup);

    assertNull(restManager.lookup(secondClientsGroup));

    ClientsGroup thirdClientsGroup = new ClientsGroup(2);
    restManager.onArrive(thirdClientsGroup);

    assertEquals(table2, restManager.lookup(thirdClientsGroup));
  }

  @Test
  void givenTableWithFreeSeatsThenClientsShouldShareTable() {
    List<Table> list = new ArrayList<>();
    Table table2 = new Table(2);
    list.add(table2);

    restManager = new RestManager(list);

    ClientsGroup clientsGroup = new ClientsGroup(1);

    restManager.onArrive(clientsGroup);

    assertEquals(table2, restManager.lookup(clientsGroup));

    ClientsGroup clientsGroup1 = new ClientsGroup(1);

    restManager.onArrive(clientsGroup1);

    assertEquals(table2, restManager.lookup(clientsGroup1));
  }

  @Test
  void givenTableWithFreeSeatsThenClientsShouldGoToFreeTable() {
    List<Table> list = new ArrayList<>();
    Table table2 = new Table(2);
    Table table3 = new Table(3);
    list.add(table2);
    list.add(table3);

    restManager = new RestManager(list);

    ClientsGroup clientsGroup = new ClientsGroup(1);

    restManager.onArrive(clientsGroup);

    assertEquals(table2, restManager.lookup(clientsGroup));

    ClientsGroup clientsGroup1 = new ClientsGroup(1);

    restManager.onArrive(clientsGroup1);

    assertEquals(table3, restManager.lookup(clientsGroup1));
  }

  @Test
  void givenTableWithFreeSeatsThenClientsShouldShareTableAfterQueueing() {
    List<Table> list = new ArrayList<>();
    Table table2 = new Table(2);
    Table table3 = new Table(3);
    list.add(table2);
    list.add(table3);

    restManager = new RestManager(list);

    // Group of 1 seats at table of 2 leaving 1 seat free
    ClientsGroup clientsGroup = new ClientsGroup(1);

    restManager.onArrive(clientsGroup);

    assertEquals(table2, restManager.lookup(clientsGroup));

    // Group of 2 seats at table of 3 leaving 1 seat free
    ClientsGroup clientsGroup1 = new ClientsGroup(2);

    restManager.onArrive(clientsGroup1);

    assertEquals(table3, restManager.lookup(clientsGroup1));

    // Group of 2 has to wait
    ClientsGroup clientsGroup2 = new ClientsGroup(2);

    restManager.onArrive(clientsGroup2);

    assertNull(restManager.lookup(clientsGroup2));

    Table t = restManager.getTable(table2);

    assertTrue(t.getClientsGroups().contains(clientsGroup));
    assertEquals(1, t.getFreeSeats());

    // Group of 1 seats at table of 2 leaving 0 seat free
    ClientsGroup clientsGroup4 = new ClientsGroup(1);

    restManager.onArrive(clientsGroup4);

    assertEquals(table2, restManager.lookup(clientsGroup4));

    t = restManager.getTable(table2);

    assertTrue(t.getClientsGroups().containsAll(List.of(clientsGroup, clientsGroup4)));
    assertEquals(0, t.getFreeSeats());

    // Group of 1 seats at table of 3 leaving 0 seat free
    ClientsGroup clientsGroup5 = new ClientsGroup(1);

    restManager.onArrive(clientsGroup5);

    assertEquals(table3, restManager.lookup(clientsGroup5));

    // Group of 2 waiting leaves the queue
    restManager.onLeave(clientsGroup2);

    assertNull(restManager.lookup(clientsGroup2));

    // Group of 2 has to wait
    ClientsGroup clientsGroup6 = new ClientsGroup(2);

    restManager.onArrive(clientsGroup6);

    assertNull(restManager.lookup(clientsGroup6));

    // Group of 2 sitting at table for 3 leaves the restaurant
    restManager.onLeave(clientsGroup1);

    // Group of 2 waiting seats at table of 3 sharing with 1 other person
    assertEquals(table3, restManager.lookup(clientsGroup6));
  }

  @Test
  void givenTableWithFreeSeatsThenClientsShouldGoToFreeTableAfterQueueing() {
    List<Table> list = new ArrayList<>();
    Table table2 = new Table(2);
    list.add(table2);

    restManager = new RestManager(list);

    ClientsGroup clientsGroup = new ClientsGroup(1);

    restManager.onArrive(clientsGroup);

    assertEquals(table2, restManager.lookup(clientsGroup));

    ClientsGroup clientsGroup1 = new ClientsGroup(2);

    restManager.onArrive(clientsGroup1);

    assertNull(restManager.lookup(clientsGroup1));

    restManager.onLeave(clientsGroup);

    assertEquals(table2, restManager.lookup(clientsGroup1));
  }

  @Test
  void givenTableWithFreeSeatsThenClientsShouldNotSeatIfSeatsAreNotAvailable() {
    List<Table> list = new ArrayList<>();
    Table table2 = new Table(3);
    list.add(table2);

    restManager = new RestManager(list);

    ClientsGroup clientsGroup = new ClientsGroup(2);

    restManager.onArrive(clientsGroup);

    assertEquals(table2, restManager.lookup(clientsGroup));

    ClientsGroup clientsGroup1 = new ClientsGroup(2);

    restManager.onArrive(clientsGroup1);

    assertNull(restManager.lookup(clientsGroup1));
  }

  @Test
  void shouldServeSmallerGroupsFirst() {
    List<Table> list = new ArrayList<>();
    Table table2 = new Table(3);
    list.add(table2);

    restManager = new RestManager(list);

    ClientsGroup clientsGroup = new ClientsGroup(2);

    restManager.onArrive(clientsGroup);

    assertEquals(table2, restManager.lookup(clientsGroup));

    ClientsGroup clientsGroup1 = new ClientsGroup(2);

    restManager.onArrive(clientsGroup1);

    assertNull(restManager.lookup(clientsGroup1));

    ClientsGroup clientsGroup2 = new ClientsGroup(1);

    restManager.onArrive(clientsGroup2);

    assertEquals(table2, restManager.lookup(clientsGroup2));

    ClientsGroup clientsGroup3 = new ClientsGroup(1);

    restManager.onArrive(clientsGroup3);

    assertNull(restManager.lookup(clientsGroup3));

    assertEquals(2, restManager.getQueue().size());

    restManager.onLeave(clientsGroup);

    assertEquals(1, restManager.getQueue().size());

    assertEquals(table2, restManager.lookup(clientsGroup3));

    restManager.onLeave(clientsGroup3);

    assertEquals(table2, restManager.lookup(clientsGroup1));

    assertEquals(0, restManager.getQueue().size());
  }
}
