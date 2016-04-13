package pl.jedrik94.thread8;

import java.util.LinkedList;

import java.util.Random;

public class Processor {

    private LinkedList<Integer> list = new LinkedList<Integer>();
    private final int LIMIT = 10;
    private Object lock = new Object();

    public void produce() throws InterruptedException {
        Integer value = 0;
        for (; ; ) {
            synchronized (lock) {
                while (list.size() == LIMIT) {
                    lock.wait();
                }
                list.add(value++);
                lock.notify();
            }
        }
    }

    public void consume() throws InterruptedException {
        Random random = new Random();
        for (; ; ) {
            synchronized (lock) {
                while (list.size() == 0) {
                    lock.wait();
                }
                System.out.print("List size: " + list.size());
                Integer value = list.removeFirst();
                System.out.print(". Taken value is: " + value + "\n");
                lock.notify();
            }
            Thread.sleep(random.nextInt(1000));
        }
    }
}
