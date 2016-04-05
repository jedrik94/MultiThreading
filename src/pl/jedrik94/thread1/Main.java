package pl.jedrik94.thread1;

import java.util.Scanner;

class Processor extends Thread {

    private volatile boolean running = true;

    @Override
    public void run() {
        while (running) {
            System.out.println("Hello.");

            try {
                Thread.sleep(222);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void shutDown() {
        running = false;
    }
}

public class Main {
    public static void main(String[] args) {
        Processor processor = new Processor();

        processor.start();

        System.out.println("Press the button to stop.");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();


        processor.shutDown();

    }
}
