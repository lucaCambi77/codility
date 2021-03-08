/** */
package it.cambi.codility.leetcode;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

/** @author luca */
@TestMethodOrder(MethodOrderer.Alphanumeric.class)
class LeetCodeDataStructTest {

  static class RecentCounter {

    Queue<Integer> q;

    public RecentCounter() {
      q = new LinkedList<>();
    }

    public int ping(int t) {
      q.add(t);

      while (q.peek() < t - 3000) q.poll();
      return q.size();
    }
  }

  @Test
  public void recentCalls() {
    RecentCounter recentCounter = new RecentCounter();

    int[] result = new int[4];

    result[0] = recentCounter.ping(1);
    result[1] = recentCounter.ping(100);
    result[2] = recentCounter.ping(3001);
    result[3] = recentCounter.ping(3002);

    assertTrue(Arrays.equals(new int[] {1, 2, 3, 3}, result));
  }

  @Test
  public void designHashMap() {
    MyHashMap hashMap = new MyHashMap();

    hashMap.put(1, 1);
    hashMap.put(2, 2);

    assertEquals(1, hashMap.get(1));
    assertEquals(-1, hashMap.get(3));

    hashMap.put(2, 1);
    assertEquals(1, hashMap.get(2));
    hashMap.remove(2);
    assertEquals(-1, hashMap.get(2));
  }

  static class MyHashMap {

    LinkedList<NodeHashMap> list = new LinkedList<>();

    /** Initialize your data structure here. */
    public MyHashMap() {}

    /** value will always be non-negative. */
    public void put(int key, int value) {
      NodeHashMap node = new NodeHashMap(key, value);
      list.remove(node);
      list.add(node);
    }

    /**
     * Returns the value to which the specified key is mapped, or -1 if this map contains no mapping
     * for the key
     */
    public int get(int key) {
      NodeHashMap node = list.stream().filter(n -> n.key == key).findFirst().orElse(null);
      return node == null ? -1 : node.value;
    }

    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
      list.remove(new NodeHashMap(key, 0));
    }
  }

  static class NodeHashMap {
    int key;
    int value;

    NodeHashMap(int key, int value) {
      this.key = key;
      this.value = value;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      NodeHashMap node = (NodeHashMap) o;
      return key == node.key;
    }

    @Override
    public int hashCode() {
      return Objects.hash(key);
    }
  }

  @Test
  public void designHashSet() {
    MyHashSet hashSet = new MyHashSet();

    hashSet.add(1);
    hashSet.add(2);

    assertTrue(hashSet.contains(1));
    assertFalse(hashSet.contains(3));

    hashSet.add(2);

    assertTrue(hashSet.contains(2));

    hashSet.remove(2);

    assertFalse(hashSet.contains(2));
  }

  static class NodeHashSet {
    int key;

    NodeHashSet(int key) {
      this.key = key;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      NodeHashSet node = (NodeHashSet) o;
      return key == node.key;
    }

    @Override
    public int hashCode() {
      return Objects.hash(key);
    }
  }

  static class MyHashSet {

    LinkedList<NodeHashSet> list = new LinkedList<>();

    /** Initialize your data structure here. */
    public MyHashSet() {}

    public void add(int key) {
      NodeHashSet node = new NodeHashSet(key);

      if (!list.contains(node)) list.add(node);
    }

    public void remove(int key) {
      list.remove(new NodeHashSet(key));
    }

    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
      return list.contains(new NodeHashSet(key));
    }
  }

  static class Employee {
    public int id;
    // the importance value of this employee
    public int importance;
    // the id of direct subordinates
    public List<Integer> subordinates;

    /**
     * @param id
     * @param importance
     * @param subordinates
     */
    public Employee(int id, int importance, List<Integer> subordinates) {
      super();
      this.id = id;
      this.importance = importance;
      this.subordinates = subordinates;
    }
  }

  AtomicInteger sum;

  @Test
  public void getImportance() {
    List<Employee> list =
        Arrays.asList(
            new Employee(1, 5, Arrays.asList(2, 3)),
            new Employee(2, 3, Collections.emptyList()),
            new Employee(3, 3, Collections.emptyList()));

    setImportance(list, 1);
    assertEquals(11, sum.get());

    // Depth-First Search
    assertEquals(11, getImportance(list, 1));
  }

  /**
   * @param list
   * @return
   */
  private AtomicInteger setImportance(List<Employee> list, int id) {
    Employee employee = list.stream().filter(e -> e.id == id).findAny().get();
    sum = new AtomicInteger(employee.importance);
    sumImportance(list, employee.subordinates, sum);

    return sum;
  }

  public void sumImportance(List<Employee> employees, List<Integer> sub, AtomicInteger sum) {
    for (Integer integer : sub) {
      Employee employee = employees.stream().filter(e -> e.id == integer).findAny().get();
      sum.getAndAdd(employee.importance);
      sumImportance(employees, employee.subordinates, sum);
    }
  }

  Map<Integer, Employee> emap = new HashMap<>();

  public int getImportance(List<Employee> employees, int queryid) {

    for (Employee e : employees) emap.put(e.id, e);

    return dfs(queryid);
  }

  public int dfs(int eid) {
    Employee employee = emap.get(eid);
    int ans = employee.importance;
    for (Integer subid : employee.subordinates) ans += dfs(subid);

    return ans;
  }
}
