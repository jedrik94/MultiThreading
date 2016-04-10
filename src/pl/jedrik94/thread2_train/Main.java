package pl.jedrik94.thread2_train;

class Job extends Thread {

    private static volatile int count;

    @Override
    public void run() {
        for (int i = 0; i < 30000; i++) {

            System.out.println("\'" + toString() + "\'");
            addCount();
        }
    }

    public static int showCount() {
        return count;
    }

    public static synchronized void addCount() {
        count++;
    }
}

public class Main {
    public static void main(String[] args) {

        Job job = new Job();
        Job job1 = new Job();

        job1.start();
        job.start();

        try {
            job.join();
            job1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Value of count: " + Job.showCount());


    }
}
