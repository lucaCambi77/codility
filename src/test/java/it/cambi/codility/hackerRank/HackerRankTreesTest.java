/**
 * 
 */
package it.cambi.codility.hackerRank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import it.cambi.codility.model.Node;

/**
 * @author luca
 *
 */
public class HackerRankTreesTest
{
    private PrintStream out;

    @BeforeEach
    public void setUpStreams()
    {
        out = mock(PrintStream.class);
        System.setOut(out);
    }

    @Test
    public void levelOrderTrasv()
    {
        Node root = Node.getExampleNode();
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(root);

        levelOrderTrasv(queue);

        InOrder orderVerifier = Mockito.inOrder(out);

        String[] solution = new String[] { "0 ", "1 ", "2 ", "3 ", "4 ", "5 ", "6 ", "7 ", "8 ", "9 ", "10 ", "11 ", "12 ", "13 ", "14 " };

        for (String sol : solution)
            orderVerifier.verify(out, atLeastOnce()).print(sol);
    }

    public void levelOrderTrasv(Queue<Node> queue)
    {
        while (!queue.isEmpty())
        {
            int size = queue.size();

            for (Node root : queue)
            {
                System.out.print(root.data + " ");
            }

            List<Node> list = new ArrayList<Node>();

            for (Node root : queue)
            {
                if (null != root.left)
                    list.add(root.left);
                if (null != root.right)
                    list.add(root.right);
            }
            queue.addAll(list);
            for (int i = 0; i < size; i++)
            {
                queue.poll();
            }
            levelOrderTrasv(queue);
        }
    }

    @Test
    public void topTreeView() throws NumberFormatException, IOException
    {
        InputStream is = new FileInputStream("src/test/resources/tree/topTreeView.txt");
        BufferedReader buf = new BufferedReader(new InputStreamReader(is));

        Node root = null;

        String line = buf.readLine();

        List<Integer> list = Arrays.stream(line.split(" ")).map(i -> Integer.valueOf(i)).collect(Collectors.toCollection(LinkedList::new));

        for (Integer integer : list)
            root = insert(root, integer);

        buf.close();

        Queue<QueueObj> queue = new LinkedList<QueueObj>();
        queue.add(new QueueObj(root, 0));

        topTreeView(queue, new TreeMap<Integer, Node>());

        InOrder orderVerifier = Mockito.inOrder(out);

        String[] solution = new String[] { "1 ", "2 ", "4 ", "14 ", "23 ", "37 ", "108 ", "111 ", "115 ", "116 ", "83 ", "84 ", "85 " };

        for (String sol : solution)
            orderVerifier.verify(out, atLeastOnce()).print(sol);
    }

    public void topTreeView(Queue<QueueObj> queue, TreeMap<Integer, Node> map)
    {

        while (!queue.isEmpty())
        {

            QueueObj obj = queue.poll();

            if (!map.containsKey(obj.hd))
                map.put(obj.hd, obj.node);

            if (obj.node.left != null)
                queue.add(new QueueObj(obj.node.left, obj.hd - 1));

            if (obj.node.right != null)
                queue.add(new QueueObj(obj.node.right, obj.hd + 1));
        }

        map.entrySet().stream().forEach(queueObj -> System.out.print(queueObj.getValue().data + " "));
    }

    class QueueObj
    {
        Node node;
        int hd;

        /**
         * 
         */
        public QueueObj(Node node, int hd)
        {
            this.node = node;
            this.hd = hd;
        }
    }

    public static Node insert(Node root, int data)
    {
        if (root == null)
        {
            return new Node(data);
        }
        else
        {
            Node cur;
            if (data <= root.data)
            {
                cur = insert(root.left, data);
                root.left = cur;
            }
            else
            {
                cur = insert(root.right, data);
                root.right = cur;
            }
            return root;
        }
    }

    /**
     * least common ancestor
     */
    @Test
    public void lca()
    {

        Node root = new Node(4);

        Node left = new Node(2);

        left.left = new Node(1);
        left.right = new Node(3);

        root.left = left;

        Node right = new Node(7);
        right.left = new Node(6);
        root.right = right;

        List<Integer> listV1 = new ArrayList<Integer>();

        lca(root, 1, listV1);

        List<Integer> listV2 = new ArrayList<Integer>();

        lca(root, 7, listV2);

        int i = listV1.size() - 1;
        int j = listV2.size() - 1;
        int integer1 = listV1.get(i);
        int integer2 = listV2.get(j);

        int result = integer1;

        i--;
        j--;

        while (i >= 0 && j >= 0)
        {

            integer1 = listV1.get(i);
            integer2 = listV2.get(j);

            if (integer1 != integer2)
                break;

            i--;
            j--;
            result = integer1;
        }

        assertEquals(4, result);
    }

    public boolean lca(Node root, int v, List<Integer> list)
    {

        if (null == root)
            return false;

        if (root.data == v)
        {
            list.add(root.data);
            return true;
        }

        if (lca(root.left, v, list) || lca(root.right, v, list))
        {
            list.add(root.data);
            return true;
        }

        return false;
    }

    @Test
    public void heightOfBinaryTree()
    {
        Node root = Node.getExampleNode();
        assertEquals(3, getMaxHeight(root, 0) - 1);
    }

    private int getMaxHeight(Node root, int count)
    {

        if (root == null)
            return count;

        int tmp = count + 1;
        int left = getMaxHeight(root.left, tmp);
        int tmp1 = count + 1;
        int right = getMaxHeight(root.right, tmp1);
        return Math.max(left, right);

    }

}
