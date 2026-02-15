package Practice_1;
class Vehicle {
    protected String make, model;
    public void displayInfo() { System.out.println(make + " " + model); }
}

class Motorcycle extends Vehicle {
    private double engineCapacity;

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Об'єм двигуна: " + engineCapacity);
    }
}