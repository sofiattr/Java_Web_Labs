package Practice_1;

class Book {
    private String title;
    private String author;
    private int year;

    // ... ваші геттери та сеттери ...
    public void setTitle(String title) { this.title = title; }
    public void setAuthor(String author) { this.author = author; }
    public void setYear(int year) { this.year = year; }

    public void displayInfo() {
        System.out.println("Книга: " + title + ", Автор: " + author + ", Рік: " + year);
    }

    public static void main(String[] args) {
        System.out.println("--- Перевірка Книги ---");
        Book myBook = new Book();
        myBook.setTitle("Тіні забутих предків");
        myBook.setAuthor("Михайло Коцюбинський");
        myBook.setYear(1911);
        myBook.displayInfo();
    }
    
}