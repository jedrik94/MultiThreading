package pl.jedrik94.thread12;

import java.util.Random;
import java.util.concurrent.*;

public class Main {
    public static void main(String... args) {
        ExecutorService executor = Executors.newCachedThreadPool();

        /*executor.submit(new Runnable() {
            @Override
            public void run() {
                Random random = new Random();

                System.out.println("Started.");

                try {
                    Thread.sleep(random.nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("Finshed.");
            }
        });*/

        Future<Integer> future = executor.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                Random random = new Random();
                int duration = random.nextInt(1000);

                if (duration >= 500) {
                    throw new Exception("Exception thrown!");
                }

                System.out.println("Started.");

                try {
                    Thread.sleep(duration);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("Finshed.");

                return duration;
            }
        });

        executor.shutdown();

        try {
            executor.awaitTermination(1, TimeUnit.MICROSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            System.out.println("Duration: " + future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            Exception ex = (Exception) e.getCause();
            System.out.println(ex);
        }
    }
}
