/**
 * 
 */
package it.cambi.codility.leetcode;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

import java.io.PrintStream;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

/**
 * @author luca
 *
 */
public class LeetCodeMultithreading
{
    private CountDownLatch countDownLatch = new CountDownLatch(2);
    private CountDownLatch countDownLatch1 = new CountDownLatch(1);

    private PrintStream out;

    @BeforeEach
    public void setUpStreams()
    {
        out = mock(PrintStream.class);
        System.setOut(out);
    }

    @Test
    @Order(1)
    public void testOrder() throws InterruptedException
    {
        Thread thread = new Thread()
        {

            @Override
            public void run()
            {

                try
                {
                    first(new Runnable()
                    {
                        public void run()
                        {
                            System.out.print("first");
                        }
                    });
                }
                catch (InterruptedException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        };
        Thread thread1 = new Thread()
        {

            @Override
            public void run()
            {

                try
                {
                    second(new Runnable()
                    {
                        public void run()
                        {
                            System.out.print("second");
                        }
                    });
                }
                catch (InterruptedException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        };
        Thread thread2 = new Thread()
        {

            @Override
            public void run()
            {

                try
                {
                    third(new Runnable()
                    {
                        public void run()
                        {
                            System.out.print("third");
                        }
                    });
                }
                catch (InterruptedException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        };

        thread.start();
        thread1.start();
        thread2.start();

        InOrder orderVerifier = Mockito.inOrder(out);
        orderVerifier.verify(out, times(1)).print("first");
        orderVerifier.verify(out,
                times(1)).print("second");
        orderVerifier.verify(out, times(1)).print("third");

    }

    public void first(Runnable printFirst) throws InterruptedException
    {

        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();

        countDownLatch1.countDown();
        countDownLatch.countDown();
    }

    public void second(Runnable printSecond) throws InterruptedException
    {
        countDownLatch1.await();

        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        countDownLatch.countDown();
    }

    public void third(Runnable printThird) throws InterruptedException
    {

        countDownLatch.await();

        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }

    private int n = 2;

    // have one permit
    private Semaphore runFoo = new Semaphore(1);
    // have no permit at start
    private Semaphore runBar = new Semaphore(0);

    @Test
    @Order(2)
    public void testOrder1() throws InterruptedException
    {
        out.flush();

        Thread thread = new Thread()
        {

            @Override
            public void run()
            {

                try
                {
                    foo(new Runnable()
                    {
                        public void run()
                        {
                            System.out.print("foo");
                        }
                    });
                }
                catch (InterruptedException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        };

        Thread thread1 = new Thread()
        {

            @Override
            public void run()
            {

                try
                {

                    bar(new Runnable()
                    {
                        public void run()
                        {

                            System.out.print("bar");
                        }
                    });

                }
                catch (InterruptedException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        };

        thread.start();
        thread1.start();

        Thread.sleep(2000);

        InOrder orderVerifier = Mockito.inOrder(out);
        orderVerifier.verify(out, times(1)).print("foo");
        orderVerifier.verify(out, times(1)).print("bar");
        orderVerifier.verify(out, times(1)).print("foo");
        orderVerifier.verify(out, times(1)).print("bar");
    }

    public void foo(Runnable printFoo) throws InterruptedException
    {

        for (int i = 0; i < n; i++)
        {
            runFoo.acquire();
            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
            runBar.release();

        }

    }

    public void bar(Runnable printBar) throws InterruptedException
    {

        for (int i = 0; i < n; i++)
        {
            runBar.acquire();
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
            runFoo.release();
        }

    }
}
