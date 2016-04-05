package pl.jedrik94.thread2;

/**
 * Created by Jędrzej Wojtkowiak on 2016-04-05.
 */
public class Main {

    // private static int count = 0;

    private int count = 0;

    public synchronized void increment() {
        count++;
    }

    public static void main(String[] args) {
        Main m = new Main();
        m.doWork();
    }

    public void doWork() {

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    increment();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    increment();
                }
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println("Value: " + count);

    }
}
