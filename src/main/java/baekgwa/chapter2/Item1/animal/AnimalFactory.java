package baekgwa.chapter2.Item1.animal;

public class AnimalFactory {
    public static Animal createAnimal(String type, String animalSound) {
        if("Cat".equalsIgnoreCase(type)) {
            return Cat.createAnimal(animalSound);
        } else if ("Dog".equalsIgnoreCase(type)) {
            return Dog.createAnimal(animalSound);
        }
        throw new IllegalArgumentException("사용할 수 없는 타입 : " + type);
    }
}
