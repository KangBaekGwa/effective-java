객체의 인스턴스를 생성하는 방법
Public 생성자를 이용하기

객체를 생성하려면 클래스를 직접 인스턴스화하는 방식입니다.
java
코드 복사
public class User {
private final String name;
private final Long age;
public User(String name, Long age) {
this.name = name;
this.age = age;
}
}

public class Main {
public static void main(String[] args) {
// public 생성자 사용
User newUser = new User("강성욱", 28L);
}
}
정적 팩터리 메서드를 이용하기

클래스 내부에서 객체를 반환하는 메서드를 제공하여 인스턴스를 생성합니다.
java
코드 복사
public class User {
private final String name;
private final Long age;

    public static User of(String name, long age) {
        return new User(name, age);
    }
}

public class Main {
public static void main(String[] args) {
// 정적 팩터리 메서드 사용
User newUser = User.of("강성욱", 28L);
}
}
정적 팩터리 메서드의 장점
이름을 가질 수 있다.

생성자를 이용하면 객체의 특성을 묘사하기 어렵지만, 정적 팩터리 메서드를 사용하면 메서드명으로 객체의 특성을 명확히 표현할 수 있습니다.
java
코드 복사
public class User {
private final String name;
private final Long age;
private final String role; // 기본 권한: "NONE"

    public static User ofNoneRole(String name, long age) {
        return new User(name, age, "NONE");
    }
}

public class Main {
public static void main(String[] args) {
User noneRoleUser = User.ofNoneRole("강성욱", 28L);
}
}
인스턴스를 캐싱하여 재사용할 수 있다.

불필요한 객체 생성을 피하고, 인스턴스를 캐싱하여 성능을 최적화할 수 있습니다.
java
코드 복사
public class User {
private final String name;
private final Long age;

    private static final Map<String, User> cache = new HashMap<>();
    
    public static User getInstance(String name, long age) {
        String key = name + ":" + age;
        if (!cache.containsKey(key)) {
            cache.put(key, new User(name, age));
        }
        return cache.get(key);
    }
}

public class Main {
public static void main(String[] args) {
User user1 = User.getInstance("강성욱", 28L);
User user2 = User.getInstance("강성욱", 28L);
System.out.println(user1 == user2); // true, 같은 인스턴스
}
}
반환 타입의 하위 타입 객체를 반환할 수 있다.

정적 팩터리 메서드를 사용하면 반환 타입을 유연하게 설정할 수 있어, 클라이언트 코드에서 구체적인 클래스에 의존하지 않게 할 수 있습니다.
java
코드 복사
public interface Animal {
void sound();
}

public class Dog implements Animal {
@Override
public void sound() {
System.out.println("Woof");
}
}

public class AnimalFactory {
public static Animal createAnimal(String type) {
if (type.equals("Dog")) {
return new Dog();
}
throw new IllegalArgumentException("Unknown animal type");
}
}

public class Main {
public static void main(String[] args) {
Animal dog = AnimalFactory.createAnimal("Dog");
dog.sound(); // "Woof"
}
}
입력 매개변수에 따라 다른 클래스의 객체를 반환할 수 있다.

동일한 메서드가 다른 입력에 대해 다양한 객체를 반환할 수 있습니다.
java
코드 복사
public class AnimalFactory {
public static Animal createAnimal(String type) {
if ("Cat".equalsIgnoreCase(type)) {
return new Cat();
} else if ("Dog".equalsIgnoreCase(type)) {
return new Dog();
}
throw new IllegalArgumentException("Unknown animal type");
}
}
정적 팩터리 메서드를 작성하는 시점에 구체적인 구현이 없어도 된다.

구현체가 나중에 추가되어도, 클라이언트는 인터페이스만 사용할 수 있어 변경에 유연하게 대응할 수 있습니다.
정적 팩터리 메서드의 단점
상속이 불가능하다.

final 클래스로 설계된 경우, 상속이 불가능합니다. 상속을 허용하려면 public 생성자가 필요합니다.
정적 팩터리 메서드를 찾기 어려울 수 있다.

API 문서나 JavaDoc을 잘 작성하지 않으면, 클라이언트가 어떤 방식으로 인스턴스를 생성할지 파악하기 어렵습니다.
핵심 정리
정적 팩터리 메서드와 public 생성자는 각각의 쓰임새가 있으며, 장단점을 고려해 사용하는 것이 좋습니다.
정적 팩터리 메서드를 사용하는 것이 유리한 경우가 많으므로, 생성자만 제공하는 습관을 바꿀 필요가 있습니다.