package pl.jedrik94.demo1;

class Runner extends Thread {

    @Override
    public void run() {
        for(int i = 0; i < 10; i++) {
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

        Runner runner = new Runner();

        runner.start();

        Runner runner2 = new Runner();

        runner2.start();

    }
}
