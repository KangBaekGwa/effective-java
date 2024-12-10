# Topic content
---
## Content summary
### 객체의 인스턴스를 생성하는 방법
#### - Public 생성자를 이용하기
    ```java
    public class User {
        private final String name;
        private final Long age;
        public User(String name, Long age) {
                this.name = name;
                this.age = age;
            }
        }
        
        //psvm
        public class Main {
        
            public static void main(String[] args) {
            //public 생성자 사용
                User newUser = new User("강성욱", 28L);
            }
        }
    ```

#### - 정적 팩터리 메서드를 만들어, 사용하기
    ```java
      public class User {
        
                  private final String name;
                  private final Long age;
        
                  public static User of(String name, long age){
                      return new User(name, age);
                  }
              }
        
              //psvm
              public class Main {
        
                  public static void main(String[] args) {
                      //정적 팩터리 메서드
                      User newUser = User.of("강성욱", 28L);
                  }
              }
      ```

    - 팩터리 메서드..? `팩터리 메서드` ≠ `정적 펙터리 메서드`
    - 정적 팩터리 메서드란? [참고링크](https://bcp0109.tistory.com/367)

- 객체의 인스턴스를 생성하는 방법
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
정적 팩터리 메서드를 사용하는 것이 유리한 경우가 많으므로, 생성자만 제공하는 습관을 바꿀 필요가 있습니다.`정적 팩터리 메서드` 의 장점
  ### 1) 이름을 가질 수 있다.

  - `생성자`로 인스턴스를 생성시, 반환될 객체의 특성을 묘사하기 어렵다.
  - 만약, 위에서 사용된 `User` 객체를 예시로 `기본 권한`이 추가되었습니다.
  - 이 때, 생성된 `User`는 최초에 `String : None` 만 가질 수 있습니다.
  - 만약 `생성자`를 통해 진행한다면 다음과 같이 만들 수 있고, 단점이 존재 합니다.

    ```java
      public class User {
      
          private final String name;
          private final Long age;
          private final String role; //회원의 권한 default : "
      
          public User(String name, Long age) {
              this.name = name;
              this.age = age;
              this.role = "NONE";
          }
      }
      
      //psvm
      public class Main {
      
          public static void main(String[] args) {
              //public 생성자 사용
              User newUser = new User("강성욱", 28L);
          }
      }
    ```

    - 생성자를 통해 인스턴스를 만든다면, 생성된 `newUser`가 어떤 권한을 통해 생성되는지 알 수 없습니다.
    - 물론, `NONE`을 매개변수로 받아와 사용할 수 있겠지만, 코드 가독성을 떨어트리고, 사실상 쓸모가 없습니다.
    - 만약, 정적 팩토리 메서드를 사용한다면, `NONE` 권한을 가지고 있는 새로운 `User` 인스턴스를 생성한다는 내용을 메서드명에 녹일 수 있습니다.

    ```java
        public class Main {
      
            public static void main(String[] args) {
                //정적 펙터리 메서드 장점1
                //1. 이름을 가질 수 있다.
                User noneNewUser = User.ofNoneRole("강성욱", 28L);
            }
        }
    ```

    - 이름을 통해, `ofNoneRole`이 None 권한을 가지고 있는 `User` 인스턴스를 만들어 준다는 것을 확인할 수 있습니다.
    ### 2) 호출될 때마다 인스턴스를 새로 생성하지는 않아도 된다.
    - `정적 팩터리 메서드`로 생성 시, 인스턴스를 미리 만들어 놓거나, 새로 생성한 인스턴스를 캐싱하여 재활용 하는 식으로 불필요한 객체 생성을 막을 수 있다.

    ---

    - 첫번째로, `Boolean.valueOf(boolean b)` 메서드의 경우, 객체를 아에 생성하지 않는다.
    - 이미 static 으로 정의 된, Boolean을 경우에 맞춰 반환하는 식으로 처리를 진행한다.

      ```java
        public final class Boolean implements java.io.Serializable,
                                              Comparable<Boolean>, Constable
        {
            /**
             * The {@code Boolean} object corresponding to the primitive
             * value {@code true}.
             */
            public static final Boolean TRUE = new Boolean(true);
      
            /**
             * The {@code Boolean} object corresponding to the primitive
             * value {@code false}.
             */
            public static final Boolean FALSE = new Boolean(false);
          
            ~~~
          
            @IntrinsicCandidate
            public static Boolean valueOf(boolean b) {
                return (b ? TRUE : FALSE);
            }
          
            ~~~
        }
      ```

      ---

      - 이미 생성된, 인스턴스를 캐싱해두어, 이후에 불러와서 사용할 수도 있다.
      - 만약, 반복되는 요청에 같은 객체를 반환하는 경우가 많을 경우, 생성 비용이 최초 1회만 생기게 되어 성능을 상당히 끌어올려줄 수 있다.

      ```java
        public class User {
      
            private final String name;
            private final Long age;
            private final String role; //회원의 권한 default : "
      
            public User(String name, Long age) {
                this.name = name;
                this.age = age;
                this.role = "NONE";
            }
          
            ~~~
          
            //캐싱처리
            private static final Map<String, User> cache = new HashMap<>();
            public static User getInstance(String name, long age) {
                String key = name + ":" + age;
                if(!cache.containsKey(key)) {
                    cache.put(key, new User(name, age));
                }
                return cache.get(key);
            }
        }
      
        //-----
      
        //psvm
        User newUser2_1 = User.getInstance("강성욱", 28L);
        User newUser2_2 = User.getInstance("강성욱", 28L); //이때, 이미 생성되어있는, instance 를 반환해줌
        System.out.println(newUser2_1.equals(newUser2_2)); //결과는 true; 즉 같은 인스턴스다!
      
        //만약, public 생성자를 사용해서 같은 걸 생성했다면, 새로운 인스턴스를 만들었을 것이고, 이는 equals false가 될 것 이다.
        User newUser2_3 = new User("강성욱", 28L);
        User newUser2_4 = new User("강성욱", 28L);
        System.out.println(newUser2_3.equals(newUser2_4)); //결과는 false; 즉 값은 같으나, 다른 인스턴스다!
        ```

      ### 3) 반환 타입의 하위 타입 객체를 반환할 수 있는 능력이 있다.
      - 이를 통해서, 반환할 객체의 클래스를 자유롭게 선택할 수 있어, `유연성`이 생긴다.
      - API를 만들 때, 이 유연성을 응용하면 실제 구현 클래스를 공개하지 않아도, 그 객체를 반환할 수 있어, API를 작게 유지하는데 도움이 된다.
      ---

       - 실제 Java 에서 사용하고 있는 `List` 인터페이스 중, `of()` 메서드가 이러한 형태를 띄고 있다.
       - `of()` 메서드는 `List` 인터페이스를 반환 하지만, 구체적인 구현은, `ImmutableCollections.List12` 입니다.
       - `ImmutableCollections.List12` 는, 구체적인 클래스 (`ArrayList`, `LinkedList` ) 를 사용하지 않고, 불변 리스트를 반환해 줍니다.
       - 덕분에, 해당 API의 클라이언트는, `List` 인터페이스만 사용하게 되고, 내부 구현(`ImmutableCollections.List12`)이 변경되더 라도, 이를 알 필요가 없습니다.

        ```java
        public interface List<E> extends SequencedCollection<E> {
            ~~
            static <E> List<E> of(E e1) {
                return new ImmutableCollections.List12<>(e1);
          }
            ~~
        }
      
        ```

      ---

      - 간단한 예제로, `AnimalFactory` 에서는, 여러 동물의 울음 소리를 확인 할 수 있습니다.
        - 정적 팩토리 메서드로 제공되는 `AnimalFactory` 에서는, `Animal` 이라는 `인터페이스` 만을 반환하게 됩니다.
        - 클라이언트는, `Animal`의 기능중 `Sound()` 메서드의 기능만 활용하면 될 것이고, 구체적인 `Dog` 나 `Cat` 의 구현체에 대해 전혀 알 필요가 없습니다.
        - 이는, `캡슐화` 를 통해, 클라이언트가 코드를 작성할 때, 내부 구현에 의존하지 않도록 만드는 장점이 생깁니다.
        - 또한, `AnimalFactory.createAnmial()` 에서, 문자열 타입에 따라 반환하도록 설정되어 있어, 나중에 새로운 동물이 추가되더라도, 유연하게 확장할 수 있습니다.

        ```java
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
      
        /**
         * 동물
         */
        public interface Animal {
      
            /**
             * 동물의 울음 소리를 콘솔에 출력합니다.
             */
            void sound();
        }
      
        //----구현체----
        //클라이언트 코드에서는 해당 코드에 접근하지 않아도 됨
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
      
        ```

        ```java
        //psvm
        //Animal API를 사용하는 클라이언트의 코드.
        //dog 나, cat 에 대해서 전혀 참조하지 않음.
        Animal animal1 = AnimalFactory.createAnimal("cat", "미야옹");
        Animal animal2 = AnimalFactory.createAnimal("dog", "월월");
        animal1.sound();
        animal2.sound();
        ```

      ### 4) 입력 매개 변수에 따라, 매번 다른 클래스의 객체를 반환할 수 있다.
      - 반환 타입의 하위 타입이기만 하면, 어떤 클래스의 객체를 반환하든 상관없다.
      ---
      - 예시로, EnumSet 클래스는 정적 팩터리만 제공하고있다.
      - 내부적으로, 원소의 갯수에 따라, 다른 구현체를 반환하고 있으나, 사용자는 이를 알 필요 없이, `EnumSet` 인터페이스를 사용하면 된다.
        ```java
        //java.util
        public abstract sealed class EnumSet<E extends Enum<E>> extends AbstractSet<E>
            implements Cloneable, java.io.Serializable permits JumboEnumSet, RegularEnumSet
        {
                ~~
                //내부적으로, 64보다 작으면 RegularEnumSet, 크면 JumboEnumSet 구현체 사용
                public static <E extends Enum<E>> EnumSet<E> noneOf(Class<E> elementType) {
                Enum<?>[] universe = getUniverse(elementType);
                if (universe == null)
                    throw new ClassCastException(elementType + " not an enum");
      
                if (universe.length <= 64)
                    return new RegularEnumSet<>(elementType, universe);
                else
                    return new JumboEnumSet<>(elementType, universe);
            }
            ~~
        }
        ```

        ```java
            //psvm
        EnumSet<SmallEnum> smallEnumSet = EnumSet.of(SmallEnum.VALUE1, SmallEnum.VALUE2,
                        SmallEnum.VALUE3);
        EnumSet<LargeEnum> largeEnumSet = EnumSet.of(LargeEnum.VALUE1, LargeEnum.VALUE2,
                        LargeEnum.VALUE3);
        System.out.println("smallEnumSet과 largeEnumSet 은 둘다 EnumSet을 가지고 있지만, 실제로는, 다른 구현체입니다.");
      
        System.out.println("smallEnumSet = " + smallEnumSet.getClass().getName());
        System.out.println("largeEnumSet = " + largeEnumSet.getClass().getName());
        System.out.println("smallEnumSet == largeEnumSet ? : " + smallEnumSet.getClass().getName()
                        .equalsIgnoreCase(largeEnumSet.getClass().getName()));
        ```
- 46개 이상 enum과 미만 enum을 `of()` 를 통해 `정적 팩터리 메서드`로 생성 시, 둘은 다른 구현체를 가지게 된다.

  ### 5) 정적 팩터리 메서드를 작성하는 시점에는 반환할 객체의 클래스가 존재하지 않아도 된다.
      - 더 정확하게, 정적 팩터리 메서드를 작성하는 시점에는, 구체적인 구현 클래스가 존재하지 않아도 된다. 라고 해석 하는게 더 이해가 잘 될 듯 하다.

      ---

  - 대표적으로, `JDBC` 가 이러한 형태를 띈다. (정확히 JDBC가 정적 팩터리 메서드는 아님!)
  - JDBC는, `java.sql` 패키지에 정의된 인터페이스 (`Connection`, `Driver`, `Statement`, `ResultSet` 등)을 중심으로 설계되어있습니다.
  - 실제 이를 동작시키는 구현체는, (`MySQL`, `Oracle`, `PostgreSQL`) 등에서 제공하지만, 클라이언트는 구현체를 뭔지 몰라도 상관이 없다.
  - 심지어, 클라이언트가 `DriverManger` 를 통해, DB에 커넥션을 획득하려고 할 때, `mysql-connector-java`와 같은 JDBC 드라이버 라이브러리가 추가 되어 있지 않아도, 컴파일이 통과되고 실행이 가능은 하다. (물론, 오류는 발생)
   - 아래는 실행은 되었으나, `driver`를 찾을 수 없어, `Exception` 이 발생한 모습.

      ![image.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/89fde35f-d786-48b7-a620-56fc17eb00d5/c54e0cb3-efe0-4a16-b097-f5075044f1e9/image.png)

- `정적 팩터리 메서드` 의 단점
  -
        1) 상속을 하려면 `public` 이나, `protected` 생성자가 필요하니, 정적 팩터리 메서드만 제공하면 하위 클래스를 만들 수 없다.

  더 정확한 표현으로는, `정적 팩터리 메서드`만 제공하는 `객체`는, `private`로 생성자가 막혀 있거나, `final` 클래스로 지정되어 있는 등, 상속 받을 수 없도록
  막혀있다.( == 직접 인스턴스를 만들어 사용할 수 없다)
      
  ---

        - 예시로, 위에서 살펴본 대로, `EnumSet.of()`를 통해 `RegularEnumSet` 을 사용할 수는 있으나, `RegularEnumSet` 을 직접 할당하여 사용할 수는 없다.

  ![image.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/89fde35f-d786-48b7-a620-56fc17eb00d5/77df95ce-480c-4afb-a4db-6043e8922258/image.png)
      
  ---

  하지만, 상속하여(생성하여) 사용하는 것이 아닌, 컴포지션을 사용하도록 유도하고, 불변 타입으로 만들려면, 이 제약을 지켜야 한다는 점에서 오히려 장점이 될 수 있다.

    -
        2) 정적 팩터리 메서드는 프로그래머가 찾기 어렵다.

  말 그대로, API 문서를 잘 써놓거나, `JavaDoc` 처리를 잘 해놓지 않으면, 클라이언트는 해당 API(기능)을 사용하기 위해, 클래스를 인스턴스화 할 방법을 알아내야
  한다.

        - 따라서, `정적 팩터리 메서드`를 통해, 인스턴스를 제공하려고 하는 개발자는, 잘 알려진 규약을 따라 짓는 식으로 문제를 완화해줘야 한다.

      ---

  | 명칭 | 설명 | 예시 |
                | --- | --- | --- |
  | from | 매개변수 하나를 받아서 해당 타입의 인스턴스를 반환하는 형변환 메서드 | Date d = Date.from(instant); |
  | of | 여러 매개 변수를 받아 적합한 타입의 인스턴스를 반환하는 집계 메서드 | EnumSet.of(JACK, QUEEN, KING); |
  | valueOf | from과 of의 더 자세한 버젼 | BigInteger.valueOf(Integer.MAX_VALUE); |
  | instance `or` getInstance | 매개변수로 명시한 인스턴스를 반화하지만, 같은 인스턴스임을 보장하지는 않음. | StackWalker.getInstance(oiptions); |
  | create `or` newInstance | instance 혹은 getInstance와 같지만, 매번 새로운 인스턴스를 생성해 반환함을 보장한다. | Array.newInstance(classObject, arrayLen); |
  | getType | getInstance와 같으나, 생성할 클래스가 아닌, 다른 클래스에 팩터리 메서드를 정의할 때 쓴다. `Type` 은 팩터리 메서드가 반환할 객체의 타입이다. | Files.getFileStore(path) |
  | newType | newInstance와 같으나, 생성할 클래스가 아닌, 다른 클래스에 팩터리 메서드를 정의할 때 쓴다. `Type` 은 팩터리 메서드가 반환할 객체의 타입이다. | Files.newBufferedReader(path); |
  | type | getType과 newType의 간결한 버젼 | Collections.list(legacyLitany); |
- 핵심 정리
    - 정적 팩터리 메서드와 public 생성자는 각자의 쓰임새가 있으니, 상대적인 장단점을 이해하고 사용하는 것이 좋다.
    - 정적 팩터리를 사용하는게 유리한 경우가 더 많으므로, 무작정 public 생성자를 제공하던 습관이 있다면 고치자!

---

### Content Application

-

<aside>
<img src="/icons/question-mark_gray.svg" alt="/icons/question-mark_gray.svg" width="40px" />

## About topic

</aside>

-

<aside>
<img src="/icons/link_gray.svg" alt="/icons/link_gray.svg" width="40px" />

## Reference

</aside>

-
