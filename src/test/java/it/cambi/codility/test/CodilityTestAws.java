package it.cambi.codility.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(OrderAnnotation.class)
public class CodilityTestAws
{

    /**
     * 
     */
    @Test
    @Order(1)
    public void test1()
    {

        int D = 4;

        String S = "one";

        switch (S)
        {
            case "one":

                break;

            case "two":

                break;

            case "three":

                break;

            case "four":

                break;

            case "five":

                break;

            default:
                break;
        }

    }

    /**
     * 
     */
    @Test
    @Order(2)
    public void test2()
    {

        int count = 0;

        /**
         * 1) Faccio un doppio loop e confronto ogni valore con gli altri che hanno differenza 1
         */
        int[] ranks = { 3, 4, 3, 0, 2, 2, 3, 0, 0 };

        for (int i = 0; i < ranks.length; i++)
        {
            for (int j = 0; j < ranks.length; j++)
            {

                if (j != i && ranks[j] > ranks[i] && Math.abs(ranks[j] - ranks[i]) == 1)
                {

                    count++;
                    break;
                }

            }
        }

        assertTrue(count == 5);

        /**
         * 2) Creo un'array di frequenze per ogni valore e conto dove non ci sono buchi di 0
         */

        count = 0;

        int[] arr = new int[ranks.length + 1];

        for (int i = 0; i < ranks.length; i++)
        {

            arr[ranks[i]] += 1;

        }

        for (int i = 1; i < arr.length; i++)
        {
            if (arr[i] == 0 || arr[i - 1] == 0)
                continue;

            count += arr[i - 1];

        }

        assertTrue(count == 5);
    }

    /**
     * 
     */
    @Test
    @Order(3)
    public void test3()
    {

        int[] A = { 7, 7, 7, 7, 7 };

        Integer diff = Integer.MAX_VALUE;
        int count = 0;

        for (int i = 1; i < A.length; i++)
        {
            if (null == diff)
                continue;

            int diffTmp = A[i] - A[i - 1];

            System.out.println(A[i] - A[i - 1]);

            if (diffTmp - diff == 0)
                continue;

            count++;

            diff = diffTmp;

            if (count > 1000000000)
                return;
        }

        System.out.println(count);
    }

    /**
     * 
     * Prova Amazon Milano 20/03/2019
     */

    /**
     * Mahnattan distance. Data una matrice, calcolare la minore distanza con la formula |x1 - x2| + |y1 - y2| dove p1 at (x1, y1) and p2 at (x2, y2)
     * .
     */
    @Test
    @Order(4)
    public void test4()
    {

        int[] X = new int[] { 2, 3, 4 };
        int[] Y = new int[] { 3, 4, 5 };

        int diff = 0;
        for (int i = 0; i < X.length; i++)
        {
            for (int j = i + 1; j < Y.length; j++)
            {
                diff = Math.min(diff, Math.abs(X[i] - X[j] + Math.abs(Y[i] - Y[j])));

                System.out.println("X -> " + X[i] + ", Y " + Y[j]);
            }
        }

        System.out.println(diff);
    }

    /**
     * Tree Node (ma anche no): Dato un array con coppie di nodi, verificare se c'è un solo parent
     */
    @Test
    @Order(5)
    public void test5()
    {

        // int[][] nodeList = new int[][] { { 40, 50 }, { 10, 60 }, { 30, 40 }, { 10, 30 }, { 60, 70 } };
        int[][] nodeList = new int[][] { { 30, 40 }, { 40, 50 }, { 10, 30 }, { 60, 70 } };

        /**
         * creo una lista di char e aggiungo p per il parent e c per il child. Quindi il parent verrò scritto una (o più volte), mentre i child anche
         * se temporaneamente sono parent, verranno sovrascritti come child
         */
        char[] a = new char[1000000];

        for (int i = 0; i < nodeList.length; i++)
        {
            a[nodeList[i][0]] = 'p';
            a[nodeList[i][1]] = 'c';

        }

        int count = 0;
        for (int i = 0; i < a.length; i++)
        {
            if (a[i] > 0 && a[i] == 'p')
                count++;

        }

        System.out.println("final parents count" + count);
        /**
         * con struttura Tree e Node TODO
         */
    }

}
