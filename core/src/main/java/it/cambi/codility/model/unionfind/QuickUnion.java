package it.cambi.codility.model.unionfind;

import lombok.Getter;

/**
 * @author luca
 *
 */
@Getter
public class QuickUnion
{
    private int[] elements;

    public QuickUnion(int n)
    {
        elements = new int[n];
        for (int i = 0; i < elements.length; i++)
            elements[i] = i;
    }

    private int root(int i)
    {
        while (i != elements[i])
            i = elements[i];

        return i;
    }

    public boolean areConnected(int a, int b)
    {
        return root(a) == root(b);
    }

    public void union(int a, int b)
    {
        int rootA = root(a);
        int rootB = root(b);

        elements[rootA] = rootB;
    }
}
