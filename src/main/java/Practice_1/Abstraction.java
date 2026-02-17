package Practice_1;
abstract class Appliance {
    abstract void turnOn();
}

class Microwave extends Appliance {
    @Override
    void turnOn() { System.out.println("Мікрохвильовка працює"); }
}

interface Playable {
    void play();
}

class Guitar implements Playable {
    public void play() { System.out.println("Гітара грає соло"); }
}