package company.alto;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Table implements Comparable<Table> {
  private final Set<Integer> allowedTables = Set.of(2, 3, 4, 5, 6); // allowed tables
  private final int size; // number of chairs
  private int freeSeats;
  private final List<ClientsGroup> clientsGroups = new ArrayList<>();

  public Table(int size) {
    if (!this.allowedTables.contains(size)) {
      throw new IllegalArgumentException(
          String.format(
              "Allowed Table sizes are : %s",
              this.allowedTables.stream().map(Object::toString).collect(Collectors.toSet())));
    }
    this.size = size;
    this.freeSeats = size;
  }

  public int getSize() {
    return this.size;
  }

  public List<ClientsGroup> getClientsGroups() {
    return this.clientsGroups;
  }

  public int getFreeSeats() {
    return this.freeSeats;
  }

  public boolean isEmpty() {
    return this.freeSeats - this.size == 0;
  }

  public boolean addGroup(ClientsGroup group) {
    if (this.freeSeats - group.getSize() >= 0) {
      this.freeSeats -= group.getSize();
      this.clientsGroups.add(group);
      return true;
    }
    return false;
  }

  public void removeGroup(ClientsGroup group) {
    this.clientsGroups.remove(group);
    this.freeSeats += group.getSize();
  }

  @Override
  public int compareTo(Table table) {
    return Integer.compare(this.size, table.size);
  }

  @Override
  public String toString() {
    return String.format("Table of %s with %s free seats", this.size, this.freeSeats);
  }
}
