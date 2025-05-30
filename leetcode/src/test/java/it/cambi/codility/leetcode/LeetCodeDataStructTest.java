/**
 *
 */
package it.cambi.codility.leetcode;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.Test;

/**
 * @author luca
 */
class LeetCodeDataStructTest {

    static class TwoSum {
        private final HashMap<Integer, Integer> num_counts;

        /**
         * Initialize your data structure here.
         */
        public TwoSum() {
            this.num_counts = new HashMap<>();
        }

        /**
         * Add the number to an internal data structure..
         */
        public void add(int number) {
            if (this.num_counts.containsKey(number))
                this.num_counts.replace(number, this.num_counts.get(number) + 1);
            else this.num_counts.put(number, 1);
        }

        /**
         * Find if there exists any pair of numbers which sum is equal to the value.
         */
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

    static class StringIteratorBean {
        private char c;
        private int freq;

        public StringIteratorBean(char c, int freq) {
            this.c = c;
            this.freq = freq;
        }
    }

    static class StringIterator {

        LinkedList<StringIteratorBean> characters = new LinkedList<>();

        public StringIterator(String compressedString) {

            for (int j = 0; j < compressedString.length(); j++) {
                char c = compressedString.charAt(j);
                StringBuilder s = new StringBuilder();
                while (j + 1 < compressedString.length()
                        && Character.isDigit(compressedString.charAt(j + 1)))
                    s.append(compressedString.charAt(++j));

                StringIteratorBean stringIteratorBean =
                        new StringIteratorBean(c, Integer.parseInt(s.toString()));
                characters.addLast(stringIteratorBean);
            }
        }

        public char next() {
            if (characters.isEmpty()) return ' ';
            StringIteratorBean stringIteratorBean = characters.peek();
            char c = stringIteratorBean.c;
            stringIteratorBean.freq--;
            if (stringIteratorBean.freq == 0) {
                characters.poll();
            } else {
                characters.set(0, stringIteratorBean);
            }

            return c;
        }

        public boolean hasNext() {
            return !characters.isEmpty();
        }
    }

    static class FindSumPairs {
        private int[] nums2;
        private HashMap<Integer, Integer> map = new HashMap<>();
        private HashMap<Integer, Integer> map2 = new HashMap<>();

        public FindSumPairs(int[] nums1, int[] nums2) {
            this.nums2 = nums2;
            for (int j : nums1) {
                map.put(j, map.getOrDefault(j, 0) + 1);
            }

            for (int j : nums2) {
                map2.put(j, map2.getOrDefault(j, 0) + 1);
            }
        }

        public void add(int index, int val) {
            int before = nums2[index];
            nums2[index] = nums2[index] + val;
            map2.put(before, map2.get(before) - 1);
            map2.put(nums2[index], map2.getOrDefault(nums2[index], 0) + 1);
        }

        public int count(int tot) {
            int count = 0;

            for (Map.Entry<Integer, Integer> entrySet : map.entrySet()) {
                if (map2.containsKey(tot - entrySet.getKey()))
                    count += map2.get(tot - entrySet.getKey()) * entrySet.getValue();
            }

            return count;
        }
    }

    class SparseVector {

        private int[] indexToNotZeroValue;

        SparseVector(int[] nums) {
            this.indexToNotZeroValue = nums;
        }

        // Return the dotProduct of two sparse vectors
        public int dotProduct(SparseVector vec) {

            int product = 0;

            for (int i = 0; i < vec.getIndexToNotZeroValue().length; i++) {
                if (vec.getIndexToNotZeroValue()[i] == 0 || this.indexToNotZeroValue[i] == 0) continue;

                product = product + (vec.getIndexToNotZeroValue()[i] * this.indexToNotZeroValue[i]);
            }

            return product;
        }

        public int[] getIndexToNotZeroValue() {
            return indexToNotZeroValue;
        }
    }

    class Row {
        int row;

        public int getRow() {
            return row;
        }

        public List<String> getColumns() {
            return columns;
        }

        List<String> columns;

        public Row(int i, List<String> columns) {
            this.row = i;
            this.columns = columns;
        }
    }

    class SQL {

        Map<String, List<Row>> map = new HashMap<>();

        public SQL(List<String> names, List<Integer> columns) {
        }

        public void insertRow(String name, List<String> row) {
            if (!map.containsKey(name)) {
                map.put(name, new ArrayList<>());
            }

            List<Row> rowList = map.get(name);

            rowList.add(rowList.size(), new Row(rowList.size() + 1, row));

            map.put(name, rowList);
        }

        public void deleteRow(String name, int rowId) {
        }

        public String selectCell(String name, int rowId, int columnId) {
            return map.get(name).stream()
                    .filter(r -> r.getRow() - rowId == 0)
                    .map(r -> r.getColumns().get(columnId - 1))
                    .findFirst()
                    .orElse(null);
        }
    }

    class LRUCache {

        Map<Integer, Integer> map = new HashMap<>();
        int capacity;
        Map<Integer, Integer> frequency = new HashMap<>();


        public LRUCache(int capacity) {
            this.capacity = capacity;
        }

        public int get(int key) {
            frequency.put(key, frequency.getOrDefault(key, 0) + 1);
            return map.getOrDefault(key, -1);
        }

        public void put(int key, int value) {
            if (map.size() == capacity) {
                Integer min = Collections.min(frequency.entrySet(), Map.Entry.comparingByValue()).getKey();
                map.remove(min);
                map.put(key, value);
            } else {
                map.put(key, value);
            }
        }
    }

    class BrowserHistory {
        Node node;

        public BrowserHistory(String homepage) {
            node = new Node(homepage);
        }

        public void visit(String url) {
            node.visit(url);
        }

        public String back(int steps) {
            return node.back(steps);
        }

        public String forward(int steps) {
            return node.forward(steps);
        }
    }

    static class Node {
        String val;
        Node next;
        Node prev;
        Node current;

        public Node(String val) {
            this.val = val;
            current = this;
        }

        public void visit(String val) {
            Node prev = current;
            current.next = new Node(val);
            current = current.next;
            current.prev = prev;
        }

        public String back(int steps) {
            if (steps <= 0) {
                return current.val;
            }

            Node node = current;
            while (--steps >= 0 && node.prev != null) {
                node = node.prev;
            }

            current = node;
            return current.val;
        }

        public String forward(int steps) {
            if (steps <= 0) {
                return current.val;
            }

            Node node = current;
            while (--steps >= 0 && node.next != null) {
                node = node.next;
            }

            current = node;
            return current.val;
        }
    }

    @Test
    void browserHistoryTest() {

        BrowserHistory browserHistory = new BrowserHistory("leetcode.com");
        browserHistory.visit("google.com");
        browserHistory.visit("facebook.com");
        browserHistory.visit("youtube.com");
        assertEquals("facebook.com", browserHistory.back(1));
        assertEquals("google.com", browserHistory.back(1));
        assertEquals("facebook.com", browserHistory.forward(1));
        browserHistory.visit("linkedin.com");
        assertEquals("linkedin.com", browserHistory.forward(2));
        assertEquals("google.com", browserHistory.back(2));
        assertEquals("leetcode.com", browserHistory.back(7));

    }

    @Test
    void lruCache() {
        testCase1();
        testCase2();
    }

    private void testCase1() {
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(1, 1); // cache is {1=1}
        lRUCache.put(2, 2); // cache is {1=1, 2=2}
        assertEquals(1, lRUCache.get(1));    // return 1
        lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
        assertEquals(-1, lRUCache.get(2));    // return 1
        lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
        assertEquals(-1, lRUCache.get(1));    // return 1
        assertEquals(3, lRUCache.get(3));    // return 1
        assertEquals(4, lRUCache.get(4));    // return 1
    }

    private void testCase2() {
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(2, 1); // cache is {2=1}
        lRUCache.put(2, 2); // cache is {2=2}
        assertEquals(2, lRUCache.get(2));    // return 2
        lRUCache.put(1, 1); // {2=2, 1=1}
        lRUCache.put(4, 1); // {2=2, 1=1, 4=1}
        assertEquals(-1, lRUCache.get(2));    // return 1
    }

    @Test
    void designSql() {
        first();
    }

    private void first() {
        SQL sql = new SQL(List.of("one", "two", "three"), List.of(2, 3, 1));
        sql.insertRow("two", List.of("first", "second", "third"));
        assertEquals("third", sql.selectCell("two", 1, 3));
        sql.insertRow("two", List.of("fourth", "fifth", "sixth"));
        sql.deleteRow("two", 1);
        assertEquals("fifth", sql.selectCell("two", 2, 2));
    }

    @Test
    public void DotProductTwoSparseVectors() {
        SparseVector v1 = new SparseVector(new int[]{1, 0, 0, 2, 3});
        SparseVector v2 = new SparseVector(new int[]{0, 3, 0, 4, 0});
        assertEquals(8, v1.dotProduct(v2));

        v1 = new SparseVector(new int[]{0, 1, 0, 0, 0});
        v2 = new SparseVector(new int[]{0, 0, 0, 0, 2});
        assertEquals(0, v1.dotProduct(v2));

        v1 = new SparseVector(new int[]{0, 0, 0, 0, 0, 0, 3, 0, 0, 3, 0, 0, 0});
        v2 = new SparseVector(new int[]{0, 0, 2, 0, 0, 4, 3, 0, 0, 2, 0, 0, 0});
        assertEquals(15, v1.dotProduct(v2));
    }

    @Test
    public void findSumPairs() {

        FindSumPairs findSumPairs =
                new FindSumPairs(new int[]{1, 1, 2, 2, 2, 3}, new int[]{1, 4, 5, 2, 5, 4});

        assertEquals(8, findSumPairs.count(7));

        findSumPairs.add(3, 2);

        assertEquals(2, findSumPairs.count(8));
        assertEquals(1, findSumPairs.count(4));

        findSumPairs.add(0, 1);
        findSumPairs.add(1, 1);

        assertEquals(11, findSumPairs.count(7));
    }

    @Test
    public void stringIterator() {
        StringIterator stringIterator = new StringIterator("L1e2t1C1o1d1e1");
        assertEquals('L', stringIterator.next());
        assertEquals('e', stringIterator.next());
        assertEquals('e', stringIterator.next());
        assertEquals('t', stringIterator.next());
        assertEquals('C', stringIterator.next());
        assertEquals('o', stringIterator.next());
        assertTrue(stringIterator.hasNext());
        assertEquals('d', stringIterator.next());
        assertTrue(stringIterator.hasNext());

        StringIterator stringIterator1 = new StringIterator("L10e2t1C1o1d1e11");
        assertEquals('L', stringIterator1.next());
        assertEquals('L', stringIterator1.next());
        assertEquals('L', stringIterator1.next());
        assertEquals('L', stringIterator1.next());
        assertEquals('L', stringIterator1.next());
        assertEquals('L', stringIterator1.next());
        assertTrue(stringIterator1.hasNext());
        assertEquals('L', stringIterator1.next());
        assertTrue(stringIterator1.hasNext());

        StringIterator stringIterator2 = new StringIterator("z82d333333333");
        assertEquals('z', stringIterator2.next());
        assertEquals('z', stringIterator2.next());
        assertEquals('z', stringIterator2.next());
        assertEquals('z', stringIterator2.next());
        assertEquals('z', stringIterator2.next());
        assertEquals('z', stringIterator2.next());
        assertTrue(stringIterator2.hasNext());
        assertEquals('z', stringIterator2.next());
        assertTrue(stringIterator2.hasNext());
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

    public static class ZigzagIterator {

        Queue<Iterator<Integer>> q;

        public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
            q = new LinkedList<>();
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

        assertArrayEquals(new int[]{1, 3, 2, 4, 5, 6}, sol);

        v1 = Collections.singletonList(1);
        v2 = Collections.emptyList();
        zigzagIterator = new ZigzagIterator(v1, v2);

        sol = new int[v1.size() + v2.size()];
        i = 0;
        while (zigzagIterator.hasNext()) sol[i++] = zigzagIterator.next();

        assertArrayEquals(new int[]{1}, sol);

        v1 = Collections.emptyList();
        v2 = Collections.singletonList(1);
        zigzagIterator = new ZigzagIterator(v1, v2);

        sol = new int[v1.size() + v2.size()];
        i = 0;
        while (zigzagIterator.hasNext()) sol[i++] = zigzagIterator.next();

        assertArrayEquals(new int[]{1}, sol);
    }

    static class MaxStack {

        private final Stack<Integer> stack;
        private Integer max = Integer.MIN_VALUE;

        /**
         * initialize your data structure here.
         */
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

        assertTrue(Arrays.equals(new int[]{1, 2, 3, 3}, result));
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

        /**
         * Initialize your data structure here.
         */
        public MyHashMap() {
        }

        /**
         * value will always be non-negative.
         */
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

        /**
         * Removes the mapping of the specified value key if this map contains a mapping for the key
         */
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

        /**
         * Initialize your data structure here.
         */
        public MyHashSet() {
        }

        public void add(int key) {
            NodeHashSet node = new NodeHashSet(key);

            if (!list.contains(node)) list.add(node);
        }

        public void remove(int key) {
            list.remove(new NodeHashSet(key));
        }

        /**
         * Returns true if this set contains the specified element
         */
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

    @Test
    public void getImportance() {
        List<Employee> list =
                Arrays.asList(
                        new Employee(1, 5, Arrays.asList(2, 3)),
                        new Employee(2, 3, Collections.emptyList()),
                        new Employee(3, 3, Collections.emptyList()));

        // Depth-First Search
        assertEquals(11, getImportance(list, 1));
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
