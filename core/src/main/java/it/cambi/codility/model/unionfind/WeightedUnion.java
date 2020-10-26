package it.cambi.codility.model.unionfind;

import lombok.Getter;

import java.util.Arrays;

/**
 * @author luca
 *
 */
@Getter
public class WeightedUnion
{
    private int[] elements;
    int[] size;

    public WeightedUnion(int n)
    {
        elements = new int[n];
        size = new int[n];
        Arrays.fill(size, 1);

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

        if (rootA == rootB)
            return;

        if (size[rootA] < size[rootB])
        {
            elements[rootA] = rootB;
            size[rootB] += size[rootA];
        }
        else
        {
            elements[rootB] = rootA;
            size[rootA] += size[rootB];
        }
    }
}
