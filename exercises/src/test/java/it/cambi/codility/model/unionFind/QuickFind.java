/**
 * 
 */
package it.cambi.codility.model.unionFind;

/**
 * @author luca
 *
 */
public class QuickFind
{

    int[] elements;

    public QuickFind(int n)
    {
        elements = new int[n];
        for (int i = 0; i < elements.length; i++)
            elements[i] = i;
    }

    public boolean areConnected(int a, int b)
    {
        return elements[a] == elements[b];
    }

    public void union(int a, int b)
    {
        int aId = elements[a];
        int bId = elements[b];

        for (int i = 0; i < elements.length; i++)
            if (elements[i] == aId)
                elements[i] = bId;
    }
}
