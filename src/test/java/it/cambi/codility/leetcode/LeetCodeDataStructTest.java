/**
 *
 */
package it.cambi.codility.leetcode;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.MethodOrderer.Alphanumeric;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author luca
 *
 */
@TestMethodOrder(Alphanumeric.class)
public class LeetCodeDataStructTest {

    @Test
    public void desigHashSet() {
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


    class Node {
        int key;

        Node(int key) {
            this.key = key;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return key == node.key;
        }

        @Override
        public int hashCode() {
            return Objects.hash(key);
        }
    }

    class MyHashSet {

        List<Node> list = new ArrayList<>();

        /** Initialize your data structure here. */
        public MyHashSet() {

        }

        public void add(int key) {
            Node node = new Node(key);

            if (!list.contains(node))
                list.add(node);
        }

        public void remove(int key) {
            list.remove(new Node(key));
        }

        /** Returns true if this set contains the specified element */
        public boolean contains(int key) {
            return list.contains(new Node(key));
        }
    }


    class Employee {
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

    ;

    AtomicInteger sum;

    @SuppressWarnings("serial")
    @Test
    public void getImportance() {
        List<Employee> list = new ArrayList<Employee>() {
            {
                add(new Employee(1, 5, new ArrayList<Integer>() {
                    {
                        add(2);
                        add(3);
                    }
                }));

                add(new Employee(2, 3, Arrays.asList(new Integer[]{})));

                add(new Employee(3, 3, Arrays.asList(new Integer[]{})));
            }
        };

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

        for (Employee e : employees)
            emap.put(e.id, e);

        return dfs(queryid);
    }

    public int dfs(int eid) {
        Employee employee = emap.get(eid);
        int ans = employee.importance;
        for (Integer subid : employee.subordinates)
            ans += dfs(subid);

        return ans;
    }
}
