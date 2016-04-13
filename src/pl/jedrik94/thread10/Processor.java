package pl.jedrik94.thread10;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Processor {
    Account acc1 = new Account();
    Account acc2 = new Account();

    Lock lock1 = new ReentrantLock();
    Lock lock2 = new ReentrantLock();

    private void acquireLock(Lock lock1, Lock lock2) throws InterruptedException {
        for (; ; ) {

            boolean gotFirstLock = false;
            boolean gotSecondLock = false;

            try {
                gotFirstLock = lock1.tryLock();
                gotSecondLock = lock2.tryLock();
            } finally {
                if (gotFirstLock && gotSecondLock)
                    return;

                if (gotFirstLock) {
                    lock1.unlock();
                }

                if (gotSecondLock) {
                    lock2.unlock();
                }
            }

        }
    }

    public void fThread() throws InterruptedException {
        Random random = new Random();
        acquireLock(lock1, lock2);
        try {
            Account.transfer(acc1, acc2, random.nextInt(250));
        } finally {
            lock1.unlock();
            lock2.unlock();
        }
    }

    public void sThread() throws InterruptedException {
        Random random = new Random();
        acquireLock(lock1, lock2);
        try {
            Account.transfer(acc2, acc1, random.nextInt(250));
        } finally {
            lock1.unlock();
            lock2.unlock();
        }
    }

    public void finshed() {
        System.out.println("Balance of first acc: " + acc1.getBalanced());
        System.out.println("Balance of second acc: " + acc2.getBalanced());
        System.out.println("Balance of accs: " + (acc1.getBalanced() + acc2.getBalanced()));
    }
}
