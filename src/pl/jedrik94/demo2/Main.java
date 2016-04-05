package pl.jedrik94.demo2;

class Runner implements Runnable {

    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Value: " + i);

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class Main {

    public static void main(String[] args) {

        Thread thread = new Thread(new Runner());
        Thread thread1 = new Thread(new Runner());

        thread.start();
        thread1.start();
    }
}