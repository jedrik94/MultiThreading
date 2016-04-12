package pl.jedrik94.thread6;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by Jędrzej Wojtkowiak on 2016-04-12.
 */
public class Main {

    private static BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(10);

    private static void producer() throws InterruptedException {
        Random random = new Random();

        for (; ; )
            queue.put(random.nextInt(99));
    }

    private static void consumer() throws InterruptedException {
        Random random = new Random();

        for (; ; ) {
            Thread.sleep(100);

            if (random.nextInt(10) == 0) {
                Integer integer = queue.take();

                System.out.println("Value taken: " + integer + ". Size od queue: " + queue.size());
            }
        }
    }

    public static void main(String[] args) {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    producer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    consumer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
