package baekgwa.chapter2.Item1;

import baekgwa.chapter2.Item1.animal.Animal;
import baekgwa.chapter2.Item1.animal.AnimalFactory;
import baekgwa.chapter2.Item1.user.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.EnumSet;

enum SmallEnum { // Enum 값 64개 이하
    VALUE1, VALUE2, VALUE3, VALUE4, VALUE5
}

enum LargeEnum { // Enum 값 65개 이상
    VALUE1, VALUE2, VALUE3, VALUE4, VALUE5, VALUE6, VALUE7, VALUE8, VALUE9, VALUE10,
    VALUE11, VALUE12, VALUE13, VALUE14, VALUE15, VALUE16, VALUE17, VALUE18, VALUE19, VALUE20,
    VALUE21, VALUE22, VALUE23, VALUE24, VALUE25, VALUE26, VALUE27, VALUE28, VALUE29, VALUE30,
    VALUE31, VALUE32, VALUE33, VALUE34, VALUE35, VALUE36, VALUE37, VALUE38, VALUE39, VALUE40,
    VALUE41, VALUE42, VALUE43, VALUE44, VALUE45, VALUE46, VALUE47, VALUE48, VALUE49, VALUE50,
    VALUE51, VALUE52, VALUE53, VALUE54, VALUE55, VALUE56, VALUE57, VALUE58, VALUE59, VALUE60,
    VALUE61, VALUE62, VALUE63, VALUE64, VALUE65
}

public class Main {

    public static void main(String[] args) throws SQLException {
        //public 생성자 사용
        User newUser1 = new User("강성욱", 28L);

        //정적 팩터리 메서드
        User newUser2 = User.of("강성욱", 28L);

        //********************정적 펙터리 메서드 장점***********************/

        /*
         * 1. 이름을 가질 수 있다.
         */
        User noneNewUser = User.ofNoneRole("강성욱", 28L);


        /*
         * 2. 호출될 때마다 인스턴스를 새로 생성하지는 않아도 된다
         */
        User newUser2_1 = User.getInstance("강성욱", 28L);
        User newUser2_2 = User.getInstance("강성욱", 28L); //이때, 이미 생성되어있는, instance 를 반환해줌
        System.out.println(newUser2_1.equals(newUser2_2)); //결과는 true; 즉 같은 인스턴스다!

        //만약, public 생성자를 사용해서 같은 걸 생성했다면, 새로운 인스턴스를 만들었을 것이고, 이는 equals false가 될 것 이다.
        User newUser2_3 = new User("강성욱", 28L);
        User newUser2_4 = new User("강성욱", 28L);
        System.out.println(newUser2_3.equals(newUser2_4)); //결과는 false; 즉 값은 같으나, 다른 인스턴스다!

        /**
         * 3. 반환 타입의 하위 타입 객체를 반환할 수 있는 능력이 있다.
         * 이를 통해, 반환할 객체의 클래스를 자유롭게 선택할 수 있게 하는 '유연성'이 생긴다.
         */
        Animal animal1 = AnimalFactory.createAnimal("cat", "미야옹");
        Animal animal2 = AnimalFactory.createAnimal("dog", "월월");
        animal1.sound();
        animal2.sound();

        /**
         * 4. 입력 매개 변수에 따라, 매번 다른 클래스의 객체를 반환할 수 있다.
         */
        EnumSet<SmallEnum> smallEnumSet = EnumSet.of(SmallEnum.VALUE1, SmallEnum.VALUE2,
                SmallEnum.VALUE3);
        EnumSet<LargeEnum> largeEnumSet = EnumSet.of(LargeEnum.VALUE1, LargeEnum.VALUE2,
                LargeEnum.VALUE3);
        System.out.println("smallEnumSet과 largeEnumSet 은 둘다 EnumSet을 가지고 있지만, 실제로는, 다른 구현체입니다.");

        System.out.println("smallEnumSet = " + smallEnumSet.getClass().getName());
        System.out.println("largeEnumSet = " + largeEnumSet.getClass().getName());
        System.out.println("smallEnumSet == largeEnumSet ? : " + smallEnumSet.getClass().getName()
                .equalsIgnoreCase(largeEnumSet.getClass().getName()));

        /**
         * 5. 정적 팩터리 메서드를 작성하는 시점에는 반환할 객체의 클래스가 존재하지 않아도 된다.
         * 이러한 유연함은 다양한 서비스를 제공하는 프레임워크를 만드는 근간이 된다.
         */
        String url = "jdbc:mysql://localhost:3306/testdb"; // MySQL URL
        String user = "root";
        String password = "password";
        Connection connection = DriverManager.getConnection(url, user, password);


        //********************정적 펙터리 메서드 단점***********************/
        // 상속을 하려면 public 이나, protected 생성자가 필요하니, 정적 팩터리 메서드만 제공하면 하위 클래스를 만들 수 없다.
        EnumSet<LargeEnum> set = EnumSet.of(LargeEnum.VALUE1, LargeEnum.VALUE2, LargeEnum.VALUE3);
        //Impossible to Instance
//        RegularEnumSet regularEnumSet = new RegularEnumSet<>();
    }
}