package Practice_1;

class Book {
    private String title;
    private String author;
    private int year;

    public void setTitle(String title) { this.title = title; }
    public void setAuthor(String author) { this.author = author; }
    public void setYear(int year) { this.year = year; }

    public void displayInfo() {
        System.out.println("Книга: " + title + ", Автор: " + author + ", Рік: " + year);
    }
}

class Account {
    private double balance;
    private String accountNumber;

    public void deposit(double amount) { balance += amount; }
    public void withdraw(double amount) { if (amount <= balance) balance -= amount; }
    public double getBalance() { return balance; }
}