/** */
package it.cambi.codility.leetcode;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

/** @author luca */
@TestMethodOrder(MethodOrderer.MethodName.class)
class LeetCodeDataStructTest {

  static class TwoSum {
    private final HashMap<Integer, Integer> num_counts;

    /** Initialize your data structure here. */
    public TwoSum() {
      this.num_counts = new HashMap<Integer, Integer>();
    }

    /** Add the number to an internal data structure.. */
    public void add(int number) {
      if (this.num_counts.containsKey(number))
        this.num_counts.replace(number, this.num_counts.get(number) + 1);
      else this.num_counts.put(number, 1);
    }

    /** Find if there exists any pair of numbers which sum is equal to the value. */
    public boolean find(int value) {
      for (Map.Entry<Integer, Integer> entry : this.num_counts.entrySet()) {
        int complement = value - entry.getKey();
        if (complement != entry.getKey()) {
          return this.num_counts.containsKey(complement);
        } else {
          if (entry.getValue() > 1) return true;
        }
      }

      return false;
    }
  }

  @Test
  public void twoSumIII() {

    TwoSum twoSum = new TwoSum();
    twoSum.add(1);
    twoSum.add(3);
    twoSum.add(5);

    assertTrue(twoSum.find(4));
    assertFalse(twoSum.find(7));
  }

  public class ZigzagIterator {

    Queue<Iterator<Integer>> q;

    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
      q = new LinkedList();
      if (!v1.isEmpty()) q.offer(v1.iterator());
      if (!v2.isEmpty()) q.offer(v2.iterator());
    }

    public int next() {
      Iterator<Integer> cur = q.poll();
      int res = cur.next();
      if (cur.hasNext()) q.offer(cur);
      return res;
    }

    public boolean hasNext() {
      return q.peek() != null;
    }
  }

  @Test
  public void ZigzagIteratorTest() {
    List<Integer> v1 = Arrays.asList(1, 2);
    List<Integer> v2 = Arrays.asList(3, 4, 5, 6);
    ZigzagIterator zigzagIterator = new ZigzagIterator(v1, v2);

    int[] sol = new int[v1.size() + v2.size()];
    int i = 0;
    while (zigzagIterator.hasNext()) sol[i++] = zigzagIterator.next();

    assertArrayEquals(new int[] {1, 3, 2, 4, 5, 6}, sol);

    v1 = Collections.singletonList(1);
    v2 = Collections.emptyList();
    zigzagIterator = new ZigzagIterator(v1, v2);

    sol = new int[v1.size() + v2.size()];
    i = 0;
    while (zigzagIterator.hasNext()) sol[i++] = zigzagIterator.next();

    assertArrayEquals(new int[] {1}, sol);

    v1 = Collections.emptyList();
    v2 = Collections.singletonList(1);
    zigzagIterator = new ZigzagIterator(v1, v2);

    sol = new int[v1.size() + v2.size()];
    i = 0;
    while (zigzagIterator.hasNext()) sol[i++] = zigzagIterator.next();

    assertArrayEquals(new int[] {1}, sol);
  }

  static class MaxStack {

    private final Stack<Integer> stack;
    private Integer max = Integer.MIN_VALUE;
    /** initialize your data structure here. */
    public MaxStack() {
      stack = new Stack<>();
    }

    public void push(int x) {
      max = Math.max(x, max);
      stack.push(x);
    }

    public int pop() {
      int toRet = stack.pop();
      max =
          stack.stream().max(Comparator.naturalOrder()).stream()
              .findFirst()
              .orElse(Integer.MIN_VALUE);

      return toRet;
    }

    public int top() {
      return stack.peek();
    }

    public int peekMax() {
      return max;
    }

    public int popMax() {
      int toRet = 0;
      for (int i = stack.size() - 1; i >= 0; i--) {
        if (stack.get(i) - max == 0) {
          toRet = stack.remove(i);
          max =
              stack.stream().max(Comparator.naturalOrder()).stream()
                  .findFirst()
                  .orElse(Integer.MIN_VALUE);
          break;
        }
      }

      return toRet;
    }
  }

  @Test
  public void maxStack() {
    firstTest();
    secondTest();
    thirdTest();
  }

  private void thirdTest() {
    MaxStack stk = new MaxStack();

    stk.push(-2);
    assertEquals(-2, stk.popMax());
    stk.push(-45);
    stk.push(-82);
    stk.push(29);
    assertEquals(29, stk.pop());
    assertEquals(-45, stk.peekMax());
  }

  private void secondTest() {
    MaxStack stk = new MaxStack();

    stk.push(5);
    assertEquals(5, stk.peekMax());
    assertEquals(5, stk.popMax());
  }

  private void firstTest() {
    MaxStack stk = new MaxStack();

    stk.push(5);
    stk.push(1);
    stk.push(5);
    assertEquals(5, stk.top());
    assertEquals(5, stk.popMax());
    assertEquals(1, stk.top());
    assertEquals(5, stk.peekMax());
    assertEquals(1, stk.pop());
    assertEquals(5, stk.top());
  }

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
