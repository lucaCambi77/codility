/** */
package it.cambi.codility.hackerRank;

import it.cambi.codility.model.Node;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;

/** @author luca */
class HackerRankTreesTest {
  private PrintStream out;

  @BeforeEach
  public void setUpStreams() {
    out = mock(PrintStream.class);
    System.setOut(out);
  }

  @Test
  public void binaryTreeInsertion() {
    List<Integer> list = new ArrayList<Integer>();
    list.add(4);
    list.add(2);
    list.add(1);
    list.add(3);
    list.add(7);
    list.add(6);

    Node root = null;

    for (Integer node : list) {
      root = binaryTreeInsertion(root, node);
    }

    preOrderTrasvPrint(root);

    InOrder orderVerifier = Mockito.inOrder(out);

    String[] solution = new String[] {"4 ", "2 ", "1 ", "3 ", "7 ", "6 "};

    for (String sol : solution) orderVerifier.verify(out, atLeastOnce()).print(sol);
  }

  private void preOrderTrasvPrint(Node root) {

    if (root != null) {
      System.out.print(root.getData() + " ");
      preOrderTrasvPrint(root.getLeft());
      preOrderTrasvPrint(root.getRight());
    }
  }

  public Node binaryTreeInsertion(Node root, int data) {
    if (root == null) return new Node(data);

    if (data < root.getData()) root.setLeft(binaryTreeInsertion(root.getLeft(), data));

    if (data > root.getData()) root.setRight(binaryTreeInsertion(root.getRight(), data));

    return root;
  }

  @Test
  public void levelOrderTrasv() {
    Node root = Node.getExampleNode();
    Queue<Node> queue = new LinkedList<Node>();
    queue.add(root);

    levelOrderTrasv(queue);

    InOrder orderVerifier = Mockito.inOrder(out);

    String[] solution =
        new String[] {
          "0 ", "1 ", "2 ", "3 ", "4 ", "5 ", "6 ", "7 ", "8 ", "9 ", "10 ", "11 ", "12 ", "13 ",
          "14 "
        };

    for (String sol : solution) orderVerifier.verify(out, atLeastOnce()).print(sol);
  }

  public void levelOrderTrasv(Queue<Node> queue) {
    while (!queue.isEmpty()) {
      int size = queue.size();

      for (Node root : queue) System.out.print(root.getData() + " ");

      List<Node> list = new ArrayList<Node>();

      for (Node root : queue) {
        if (null != root.getLeft()) list.add(root.getLeft());
        if (null != root.getRight()) list.add(root.getRight());
      }

      queue.addAll(list);

      for (int i = 0; i < size; i++) queue.poll();

      levelOrderTrasv(queue);
    }
  }

  @Test
  public void topTreeView() throws NumberFormatException, IOException {
    InputStream is = new FileInputStream("src/test/resources/tree/topTreeView.txt");
    BufferedReader buf = new BufferedReader(new InputStreamReader(is));

    Node root = null;

    String line = buf.readLine();

    List<Integer> list =
        Arrays.stream(line.split(" "))
            .map(i -> Integer.valueOf(i))
            .collect(Collectors.toCollection(LinkedList::new));

    for (Integer integer : list) root = insert(root, integer);

    buf.close();

    Queue<QueueObj> queue = new LinkedList<QueueObj>();
    queue.add(new QueueObj(root, 0));

    topTreeView(queue, new TreeMap<Integer, Node>());

    InOrder orderVerifier = Mockito.inOrder(out);

    String[] solution =
        new String[] {
          "1 ", "2 ", "4 ", "14 ", "23 ", "37 ", "108 ", "111 ", "115 ", "116 ", "83 ", "84 ", "85 "
        };

    for (String sol : solution) orderVerifier.verify(out, atLeastOnce()).print(sol);
  }

  public void topTreeView(Queue<QueueObj> queue, TreeMap<Integer, Node> map) {

    while (!queue.isEmpty()) {

      QueueObj obj = queue.poll();

      if (!map.containsKey(obj.hd)) map.put(obj.hd, obj.node);

      if (obj.node.getLeft() != null) queue.add(new QueueObj(obj.node.getLeft(), obj.hd - 1));

      if (obj.node.getRight() != null) queue.add(new QueueObj(obj.node.getRight(), obj.hd + 1));
    }

    map.entrySet().stream()
        .forEach(queueObj -> System.out.print(queueObj.getValue().getData() + " "));
  }

  class QueueObj {
    Node node;
    int hd;

    /** */
    public QueueObj(Node node, int hd) {
      this.node = node;
      this.hd = hd;
    }
  }

  public static Node insert(Node root, int data) {
    if (root == null) {
      return new Node(data);
    } else {
      Node cur;
      if (data <= root.getData()) {
        cur = insert(root.getLeft(), data);
        root.setLeft(cur);
      } else {
        cur = insert(root.getRight(), data);
        root.setRight(cur);
      }
      return root;
    }
  }

  /** least common ancestor */
  @Test
  public void lca() {

    Node root = new Node(4);

    Node left = new Node(2);

    left.setLeft(new Node(1));
    left.setRight(new Node(3));

    root.setLeft(left);

    Node right = new Node(7);
    right.setLeft(new Node(6));
    root.setRight(right);

    Stack<Integer> listV1 = new Stack<>();

    lca(root, 1, listV1);

    Stack<Integer> listV2 = new Stack<>();

    lca(root, 7, listV2);

    while (!listV1.isEmpty() && !listV2.isEmpty() && !listV1.peek().equals(listV2.peek())) {
      if (listV1.size() > listV2.size()) listV1.pop();
      else if (listV1.size() < listV2.size()) listV2.pop();
      else {
        listV1.pop();
        listV2.pop();
      }
    }

    assertEquals(4, listV1.peek());
  }

  public void lca(Node root, int v, Stack<Integer> list) {

    if (null == root) return;

    if (v > root.getData()) {
      list.add(root.getData());
      lca(root.getRight(), v, list);
    } else if (v < root.getData()) {
      list.add(root.getData());
      lca(root.getLeft(), v, list);
    } else {
      return;
    }
  }

  /*
     Number of edges not number of nodes
  */
  @Test
  public void heightOfBinaryTree() {
    Node root = Node.getExampleNode();
    assertEquals(3, getMaxHeight(root, 0) - 1);
  }

  private int getMaxHeight(Node root, int count) {

    if (root == null) return count;

    int left = getMaxHeight(root.getLeft(), count + 1);

    int right = getMaxHeight(root.getRight(), count + 1);
    return Math.max(left, right);
  }
}
