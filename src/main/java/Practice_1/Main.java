package Practice_1;

public class Main {
    public static void main(String[] args) {
      
        // ТЕМА 1: ІНКАПСУЛЯЦІЯ 
       
        System.out.println("\n 1. ІНКАПСУЛЯЦІЯ");
        
        // Завдання 1: Книга
        // Використовуємо сеттери
        Book myBook = new Book();
        myBook.setTitle("Тіні забутих предків");
        myBook.setAuthor("Михайло Коцюбинський");
        myBook.setYear(1911);
        myBook.displayInfo();

        // Завдання 2: Рахунок
        Account myAccount = new Account();
        myAccount.deposit(1000);   // Поповнення
        myAccount.withdraw(250);   // Зняття
        System.out.println("Поточний баланс рахунку: " + myAccount.getBalance());


       
        // ТЕМА 2: НАСЛІДУВАННЯ (Файл Inheritance.java)
        
        System.out.println("\n 2. НАСЛІДУВАННЯ");
        
        Motorcycle myMoto = new Motorcycle();
        
       
        myMoto.make = "Yamaha";
        myMoto.model = "R1";
        myMoto.displayInfo();


       
        // ТЕМА 3: ПОЛІМОРФІЗМ (Файл Polymorphism.java)
       
        System.out.println("\n 3. ПОЛІМОРФІЗМ");
        
      
        Shape shape1 = new Triangle(10, 5); // base, height
        Shape shape2 = new Square(4);       // side

        System.out.println("Площа трикутника: " + shape1.calculateArea());
        System.out.println("Площа квадрата: " + shape2.calculateArea());


     
        // ТЕМА 4: АБСТРАКЦІЯ (Файл Abstraction.java)
      
        System.out.println("\n 4. АБСТРАКЦІЯ");
        
        // Завдання 1: Абстрактний клас
        Appliance myMicrowave = new Microwave();
        System.out.print("Прилад: ");
        myMicrowave.turnOn();

        // Завдання 2: Інтерфейс
        Playable myGuitar = new Guitar();
        System.out.print("Інструмент: ");
        myGuitar.play();
        

    }
}