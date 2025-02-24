package company.alto;

public class ClientsGroup implements Comparable<ClientsGroup> {
  private final int size; // number of clients

  public ClientsGroup(int size) {
    if (size > 6) {
      throw new IllegalArgumentException("Group size must be less than or equal to 6");
    }
    this.size = size;
  }

  @Override
  public int compareTo(ClientsGroup clientsGroup) {
    return Integer.compare(this.size, clientsGroup.size);
  }

  public int getSize() {
    return this.size;
  }

  @Override
  public String toString() {
    return String.format("Group of %s clients", this.size);
  }
}
