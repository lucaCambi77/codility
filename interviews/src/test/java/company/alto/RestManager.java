package company.alto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeSet;

public class RestManager {
  private final List<Table> tables = new ArrayList<>();
  private final TreeSet<ClientsGroup> queue =
      new TreeSet<>(); // always serve smaller groups
  private final Map<ClientsGroup, Table> groupTableMap = new HashMap<>();

  public RestManager(List<Table> tables) {
    clear();
    this.tables.addAll(tables);
    Collections.sort(
        this.tables); // sort table to iterate in ascending order, so we can find the next available
    // table with nearest group size
  }

  /**
   * Group should be seated according to the rule:
   *
   * <p>- Seat first in the free tables even if the group is less than the total seats
   *
   * <p>- Share a table with other guest if possible
   *
   * <p>- Queue if no seats are available
   *
   * @param group New guests
   */
  public void onArrive(ClientsGroup group) {

    if (null == group) {
      return;
    }

    if (customerHasSeat(group)) return;

    // otherwise add to the queue
    queue.add(group);
  }

  private boolean customerHasSeat(ClientsGroup group) {
    Table potentialSharedTable = null;
    boolean foundSharedTable = false;

    for (Table table : this.tables) {
      if (!foundSharedTable && !table.isEmpty() && table.canSeat(group)) {
        potentialSharedTable = table;
        foundSharedTable = true;
      }

      if (table.isEmpty() && table.canSeat(group)) {
        this.groupTableMap.put(group, table);
        System.out.printf("%s can seat at %s%n", group, table);
        table.addGroup(group);
        return true;
      }
    }

    if (potentialSharedTable != null) {
      potentialSharedTable.addGroup(group);
      this.groupTableMap.put(group, potentialSharedTable);
      System.out.printf("%s can seat at %s%n", group, potentialSharedTable);
      return true;
    }

    return false;
  }

  /**
   * Clients leaving, remove them from the queue in case they were waiting. Finally, process waiting
   * costumers
   *
   * @param group Leaving guests
   */
  public void onLeave(ClientsGroup group) {
    // client(s) served
    if (this.groupTableMap.containsKey(group)) {
      Table table = this.groupTableMap.get(group);
      table.removeGroup(group);
      this.groupTableMap.remove(group);
    } else {
      this.queue.remove(group);
    }

    // seat client(s) from the queue
    for (ClientsGroup c : this.queue) {
      if (customerHasSeat(c)) {
        this.queue.remove(c);
        return;
      }
    }
  }

  // return table where a given client group is seated,
  // or null if it is still queueing or has already left
  public Table lookup(ClientsGroup group) {
    return this.groupTableMap.get(group);
  }

  public TreeSet<ClientsGroup> getQueue() {
    return this.queue;
  }

  public List<Table> getTables() {
    return this.tables;
  }

  public Table getTable(Table table) {
    return this.tables.stream().filter(t -> t.equals(table)).findFirst().orElse(null);
  }

  private void clear() {
    this.tables.clear();
    this.queue.clear();
  }
}
