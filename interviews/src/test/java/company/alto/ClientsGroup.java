package company.alto;

import java.util.Date;

public class ClientsGroup implements Comparable<ClientsGroup> {
  private final int size; // number of clients
  private final Date arrivalDate;

  public ClientsGroup(int size) {
    if (size > 6) {
      throw new IllegalArgumentException("Group size must be less than or equal to 6");
    }
    this.size = size;
    this.arrivalDate = new Date();
  }

  public ClientsGroup(int size, Date arrivalDate) {
    if (size > 6) {
      throw new IllegalArgumentException("Group size must be less than or equal to 6");
    }
    this.size = size;
    this.arrivalDate = arrivalDate;
  }

  @Override
  public int compareTo(ClientsGroup clientsGroup) {
    int compareSize = Integer.compare(this.size, clientsGroup.size);

    if (compareSize != 0) {
      return compareSize;
    }

    return Long.compare(this.arrivalDate.getTime(), clientsGroup.getArrivalDate().getTime());
  }

  public int getSize() {
    return this.size;
  }

  public Date getArrivalDate() {
    return this.arrivalDate;
  }

  @Override
  public String toString() {
    return String.format("Group of %s clients", this.size);
  }
}
