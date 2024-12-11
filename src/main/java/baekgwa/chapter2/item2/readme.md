- 선택적 매개변수가 많을 때, 대응하는 다양한 방법
    - 이 경우에, 생성자와 정적 팩터리와 동일하게 적절히 대응하기 어렵다.
        - 간단한 예시로, `식품 포장`의 `영양정보` 를 클래스를 만든다면, 여기에는 `1회 내용량`, `총 n회 제공량`, `1회 제공량당 칼로리`, `총 지방`, `트랜스지방`, `포화지방`, `콜레스테롤`, `나트륨` 등, 20개가 넘는 선택항목이 주어지게 됩니다.
        - 하지만 대부분, 지정될 값이 `0` 인 경우가 많습니다.
    - 1) 점진적 생성자 패턴
        - 매개변수를 1개만 받는 생성자, 2개.. n개… 이런식 으로 여러가지 상황에 대응하기 위한 다양한 생성자를 만들어 줍니다.

      ```java
      public class NutritionFacts {
          private final int servingSize;
          private final int servings;
          private final int calories;
          private final int fat;
          private final int sodium;
          private final int carbohydrate;
      
          public NutritionFacts(int calories, int servings, int servingSize) {
              this(servingSize, servings, calories, 0, 0, 0);
          }
      
          public NutritionFacts(int fat, int calories, int servings, int servingSize) {
              this(servingSize, servings, calories, fat, 0, 0);
          }
      
          public NutritionFacts(int sodium, int fat, int calories, int servings, int servingSize) {
              this(servingSize, servings, calories, fat, sodium, 0);
          }
      
          public NutritionFacts(int servingSize, int servings, int calories, int fat, int sodium,
                  int carbohydrate) {
              this.servingSize = servingSize;
              this.servings = servings;
              this.calories = calories;
              this.fat = fat;
              this.sodium = sodium;
              this.carbohydrate = carbohydrate;
          }
      }
      
      ```

        - 다양한 매개 변수를 제공하는 생성자를 만들었지만, 여전히 정확히 사용하고 싶은 매개 변수만 쓰기에는 자율성이 너무 부족하다.
            - 예시로, 나는 `servingSize`, `carbohydrate` 만 적고 싶은데, 그 경우에 해당하는 생성자가 없어, 마지막에 모든 매개변수를 받는 생성자를 사용해야만 한다.

      ```java
      //1. 점진적 생성자 패턴
      NutritionFactsByConstructor a = new NutritionFactsByConstructor(100, 0, 0, 0, 0, 200);
      //나는, `servingSize`, `carbohydrate`만 사용하고 싶은데,
      //정확히 내 상황과 매칭되는 생성자가 없어, 쓸데없이 0으로 지정하는 매개변수가 많아짐.
      ```

        - 점층적 생성자 패턴의 큰 단점은, 매개변수가 많아지면 클라이언트 코드를 작성하거나 읽기 어려워 짐에 있다.
        - 또한, 실수로 매개변수의 순서를 바꿔 건네줘도 컴파일러는 알아차리지 못하고, 런타임에서 엉뚱한 동작을 하게 된다.
    - 2) 자바빈즈 패턴 (JavaBeans pattern)
        - 자바빈즈 패턴은, 매개변수가 없는 생성자를 객체로 만들고, `setter`를 통해 매개변수의 값을 설정하는 방식이다.

      ```java
      public class NutritionFactsByJavaBeans {
          private int servingSize;
          private int servings;
          private int calories;
          private int fat;
          private int sodium;
          private int carbohydrate;
      
          public NutritionFactsByJavaBeans() {
          }
      
          public void setServingSize(int servingSize) {
              this.servingSize = servingSize;
          }
      
          public void setServings(int servings) {
              this.servings = servings;
          }
      
          public void setCalories(int calories) {
              this.calories = calories;
          }
      
          public void setFat(int fat) {
              this.fat = fat;
          }
      
          public void setSodium(int sodium) {
              this.sodium = sodium;
          }
      
          public void setCarbohydrate(int carbohydrate) {
              this.carbohydrate = carbohydrate;
          }
      }
      
      //----psvm
      
      //2. JavaBeans 패턴
      NutritionFactsByJavaBeans b = new NutritionFactsByJavaBeans();
      b.setCalories(100);
      b.setCarbohydrate(200);
      b.setServingSize(160);
      ```

        - 자바 빈즈를 사용하면, 생성자 패턴의 단점인 `유연성` 이 해소됩니다.
        - 물론 클라이언트 코드가 길어지긴 했지만, 인스턴스를 만들기 쉽고, 더 읽기 쉬운 코드가 되었습니다.
        - 단, 다음과 같은 심각한 단점이 존재합니다.
        - 이 패턴에서는, **객체가 완전히 생성되기 전까지는 일관성이 무너진 상태 입니다.**
        - 심지어 추가적으로, 객체가 완전히 생성 되고 난 후, `버그성 코드` 때문에 객체의 상태가 변경되어 원하는 동작이 이뤄지지 않을 수 있습니다.

      ```java
      //2. JavaBeans 패턴
      NutritionFactsByJavaBeans b = new NutritionFactsByJavaBeans();
      b.setCalories(100);
      b.setCarbohydrate(200);
      b.setServingSize(160);
              
      //~~복잡한 서비스 코드 진행~~
              
      b.setServingSize(10);
      return b;
      ```

        - 이와 같이, 중간에 `b` 인스턴스의 `servingsize` 값이 중간에 변경되어도 컴파일러는 당연히 잡아내지 못하고, 버그성 코드의 위치와 실제 런타임 문제의 연관 관계의 거리가 멀어 디버깅 또한 쉽지 않을 수 있습니다.
        - 또한 setter를 사용해야 하므로, 클래스를 불변으로 만들 수 없으며, 스레드 안전성을 얻으려면 개발자가 추가 작업을 진행 해 줘야 합니다.
    - 3) 빌더 패턴 (Builder Pattern)
        - 빌터 패턴은, 클라이언트가 필요한 객체를 직접 만드는 대신, 필수 매개변수를 사용해 `Builder` 객체를 얻고, 이 `Builder` 객체를 통해, 선택 매개 변수를 추가하여 사용합니다.
        - 기본적으로, 체이닝을 통한 구현이 되어있으며, 마지막에 `Build()` 를 통해 객체를 생성하게 됩니다.

      ```java
      public class NutritionFactsByBuilder {
          private final int servingSize;
          private final int servings;
          private final int calories;
          private final int fat;
          private final int sodium;
          private final int carbohydrate;
      
          public static class Builder {
              private final int servingSize;
              private final int servings;
              private int calories = 0;
              private int fat = 0;
              private int sodium = 0;
              private int carbohydrate = 0;
      
              public Builder(int servingSize, int servings) { this.servingSize = servingSize; this.servings = servings; }
              public Builder calories(int val) { calories = val; return this; }
              public Builder fat(int val) { fat = val; return this; }
              public Builder sodium(int val) { sodium = val; return this; }
              public Builder carbohydrate(int val) { carbohydrate = val; return this; }
              public NutritionFactsByBuilder build() { return new NutritionFactsByBuilder(this); }
          }
      
          public NutritionFactsByBuilder(Builder builder) {
              servingSize = builder.servingSize;
              servings = builder.servings;
              calories = builder.calories;
              fat = builder.fat;
              sodium = builder.sodium;
              carbohydrate = builder.carbohydrate;
          }
      }
      ```

      ```java
      //실제 사용할때에는 다음과 같이, Builder()를 호출하여 필수 매개변수를 등록
      //Builder()를 통해, Builder 객체를 받고, 이를 내부 `setter`역할 메서드로
      //체이닝을 구성하여 선택 값을 설정
      //마지막으로 build()를 진행하여, 값을 반환
      NutritionFactsByBuilder c = new NutritionFactsByBuilder
                      .Builder(100, 10)
                      .calories(20)
                      .build();
      ```

        - 앞에서 본 무엇보다 사용하기 간편하며, 읽기 쉬워집니다.
        - 만약, 유효성 검사 코드가 필요하다면, `build()` 메서드가 호출하는 생성자에서 검사를 진행하면 된다.
- 빌더 패턴의 장점
    - 계층적으로 설계된 클래스와 함께 쓰기에 좋다.
        - Pizza 라는 추상 클래스가 있습니다.
        - 이를 상속하는, NyPizza 라는 구현 클래스가 있습니다.
        - `Pizza` 는 기본적으로, Topping 을 추가할 수 있는 기능이 있습니다.
        - `NyPizza` 에는, Size를 설정할 수 있는 기능이 있습니다. 이 기능은, 필수 값 입니다.
        - 이를, 빌더 패턴을 사용하면 다음과 같은 코드로 풀어낼 수 있습니다.

        ```java
        public abstract class Pizza {
            public enum Topping { HAM, MUSHROOM, ONION, PEPPER, SAUSAGE}
            public final Set<Topping> toppings;
        
            abstract static class Builder<T extends Builder<T>> {
                EnumSet<Topping> toppings = EnumSet.noneOf(Topping.class);
        
                public T addTopping(Topping topping) {
                    toppings.add(Objects.requireNonNull(topping));
                    return self();
                }
        
                abstract Pizza build();
        
                protected abstract T self();
            }
        
            Pizza(Builder<?> builder) {
                toppings = builder.toppings.clone();
            }
        }
        ```

        ```java
        public class NyPizza extends Pizza {
        
            public enum Size { SMALL, MEDIUM, LARGE }
            private final Size size;
        
            public static class Builder extends Pizza.Builder<Builder> {
                private final Size size;
        
                public Builder(Size size) {
                    this.size = Objects.requireNonNull(size);
                }
        
                @Override
                public NyPizza build() {
                    return new NyPizza(this);
                }
        
                @Override
                protected Builder self() {
                    return this;
                }
            }
        
            private NyPizza(Builder builder) {
                super(builder);
                size = builder.size;
            }
        }
        ```

        ```java
        //3-1. Builder, 계층형 구조에서의 장점
        NyPizza nyPizza = new NyPizza
                        .Builder(Size.SMALL)
                        .addTopping(Topping.SAUSAGE)
                        .addTopping(Topping.ONION)
                        .build();
        ```

        - 상속받고 있는, `NyPizza` 에서는, 부모의 기능인 토핑 추가 기능을, 전혀 신경 쓰지 않아도 생성이 가능합니다.
        - 또한, `NyPizza` 의 고유 기능인, 사이즈 선택 또한 간단하게 가능합니다.
        - 만약, 생성자를 통해 이를 풀어낸다면, `NyPizza` 에서, `Pizza`의 기능인, 토핑 추가를 사용하려면, 매개변수로 전달해줘야 하는 불편함이 생깁니다.
        - 또한, 필수 매개변수와, 선택 매개변수를 다루기 위해 무수히 많은 생성자가 생성 될 것 입니다.
    - 초기화를 위해, 메서드를 여러번 호출하도록 할 수 있다.
        - 위의 예제에서, `addTopping()` 메서드가 이러한 형태를 지니고 있습니다.
        - 이 메서드는 여러번 호출 할 수 있어, 유연하게 객체를 생성할 수 있습니다.
- 빌더 패턴의 단점
    - 객체를 만들려면, 빌더 부터 만들어야 한다.
        - 빌더패턴 코드만 보아도, 생성자 보다는 코드가 장황하다.
    - 빌더 생성 비용이 크지는 않지만 성능에 민감한 경우에는 문제가 될 수 있다.
        - 이론상, 실제 동작은 `Builder` 객체를 만들고, 체이닝을 통해 `메서드` 들을 호출하는 등, 객체를 생성하기 위해서 다양한 추가 작업이 진행되어야 한다.
        - 정말 대규모, 성능 최적화가 필요한 상황에서는 고려해볼 대상이 될 수도 있다.
- 핵심 정리
    - 생성자나 정적 팩터리가 처리해야 할 매개 변수가 많다면, 빌더 패턴을 선택하는게 더 낫다.
    - 매개변수 중, 다수가 필수가 아니거나 같은 타입이면 특히 그렇다.
    - 클라이언트 사용/가독성 : `Builder` > `생성자`
    - 안전성 : `Builder` > `자바빈즈`