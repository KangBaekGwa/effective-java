package baekgwa.chapter2.Item1.animal;

public class Cat implements Animal {

    private final String animalSound;

    private Cat(String animalSound) {
        this.animalSound = animalSound;
    }

    @Override
    public void sound() {
        System.out.println("Cat's sound : " + this.animalSound);
    }

    public static Animal createAnimal(String animalSound) {
        return new Cat(animalSound);
    }
}
