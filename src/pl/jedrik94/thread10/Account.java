package pl.jedrik94.thread10;

public class Account {
    private int balanced = 10000;

    public void deposit (int amount) {
        balanced += amount;
    }

    public void withdraw (int amount) {
        balanced -= amount;
    }

    public int getBalanced () {
        return balanced;
    }

    public static void transfer (Account acc1, Account acc2, int amount) {
        acc1.withdraw(amount);
        acc2.deposit(amount);
    }
}
