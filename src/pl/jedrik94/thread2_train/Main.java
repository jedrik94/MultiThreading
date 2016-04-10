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

    public void addCount() {
        synchronized(Main.class){
            // To powinno też dzialac
            count++;
        }

    }

    @Override
    public String toString() {
        return getName();
    }
}

public class Main {
    public static void main(String[] args) {

        Job job = new Job();
        Job job1 = new Job();
        Job job2 = new Job();
        Job job3 = new Job();

        job.start();
        job1.start();
        job2.start();
        job3.start();

        try {
            job.join();
            job1.join();
            job2.join();
            job3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Value of count: " + Job.showCount());


    }
}
