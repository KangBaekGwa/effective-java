package baekgwa.chapter2.Item1.animal;

public class Dog implements Animal {

    private final String animalSound;

    private Dog(String animalSound) {
        this.animalSound = animalSound;
    }

    @Override
    public void sound() {
        System.out.println("Dog's sound : " + this.animalSound);
    }

    public static Animal createAnimal(String animalSound) {
        return new Dog(animalSound);
    }
}
