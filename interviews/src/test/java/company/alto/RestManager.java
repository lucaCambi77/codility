package company.alto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class RestManager {
  private final List<Table> tables = new ArrayList<>();
  private final PriorityQueue<ClientsGroup> queue =
      new PriorityQueue<>(); // always serve smaller groups
  private final Map<ClientsGroup, Table> groupTableMap = new HashMap<>();

  public RestManager(List<Table> tables) {
    clear();
    this.tables.addAll(tables);
    Collections.sort(
        this.tables); // sort table to iterate in ascending order, so we can find the next available
    // table with nearest group size
  }

  // new client(s) show up
  public void onArrive(ClientsGroup group) {

    if (null == group) {
      return;
    }

    Table table;

    // try to seat the client(s) in the first empty table
    int j = 0;
    while (j < this.tables.size()) {
      table = this.tables.get(j);
      if (group.getSize() <= table.getSize() && table.isEmpty()) {
        if (table.addGroup(group)) {
          this.groupTableMap.put(group, table);
          System.out.printf("%s can seat at %s%n", group, table);
          return;
        }
      }
      j++;
    }

    // try to seat the client(s) in the first shared table
    j = 0;
    while (j < this.tables.size()) {
      table = this.tables.get(j);
      if (table.addGroup(group)) {
        this.groupTableMap.put(group, table);
        System.out.printf("%s can seat at %s%n", group, table);
        return;
      }
      j++;
    }

    // otherwise add to the queue
    queue.add(group);
  }

  // client(s) leave, either served or simply abandoning the queue
  public void onLeave(ClientsGroup group) {
    // client(s) served
    if (this.groupTableMap.containsKey(group)) {
      Table table = this.groupTableMap.get(group);
      table.removeGroup(group);
      this.groupTableMap.remove(group);
    } else {
      // client(s) leaves the queue
      for (ClientsGroup c : this.queue) {
        if (c.equals(group)) {
          this.queue.remove(c);
          break;
        }
      }
    }
    // seat client(s) from the queue
    onArrive(queue.poll());
  }

  // return table where a given client group is seated,
  // or null if it is still queueing or has already left
  public Table lookup(ClientsGroup group) {
    return this.groupTableMap.get(group);
  }

  public PriorityQueue<ClientsGroup> getQueue() {
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
