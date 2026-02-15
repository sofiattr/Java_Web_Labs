package Practice_1;

public class Main {
    public static void main(String[] args) {
        // Створення об'єктів за допомогою конструкторів
        Shape shape1 = new Triangle(10, 5);
        Shape shape2 = new Square(4);

        // Виведення результатів у консоль
        System.out.println("Triangle Area: " + shape1.calculateArea());
        System.out.println("Square Area: " + shape2.calculateArea());
    }
}