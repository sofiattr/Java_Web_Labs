package Practice_1;

public class Account {
    private double balance;
    private String accountNumber;

    // Метод поповнення
    public void deposit(double amount) {
        balance += amount;
    }

    // Метод зняття
    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
        } else {
            System.out.println("Недостатньо коштів");
        }
    }

    // Метод перевірки балансу
    public double getBalance() {
        return balance;
    }
}