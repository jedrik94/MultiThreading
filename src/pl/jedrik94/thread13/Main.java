package pl.jedrik94.thread13;

import java.util.Random;

public class Main {
    public static void main(String... args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                Random random = new Random();

                for (int i = 0; i < 1E8; i++) {
                    /*if (Thread.currentThread().isInterrupted()) {
                        System.out.println("Interrupted.");
                        break;
                    }*/

                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        System.out.println("Interrupted!");
                        break;
                    }
                    Math.signum(Math.sinh(random.nextDouble()));
                }
            }
        });

        t1.start();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t1.interrupt();

        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
