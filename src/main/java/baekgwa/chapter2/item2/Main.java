package baekgwa.chapter2.item2;

import baekgwa.chapter2.item2.nutrition.NutritionFactsByBuilder;
import baekgwa.chapter2.item2.nutrition.NutritionFactsByConstructor;
import baekgwa.chapter2.item2.nutrition.NutritionFactsByJavaBeans;
import baekgwa.chapter2.item2.pizza.NyPizza;
import baekgwa.chapter2.item2.pizza.NyPizza.Builder;
import baekgwa.chapter2.item2.pizza.NyPizza.Size;
import baekgwa.chapter2.item2.pizza.Pizza;
import baekgwa.chapter2.item2.pizza.Pizza.Topping;

public class Main {
    public static void main(String[] args) {

        //1. 점진적 생성자 패턴
        NutritionFactsByConstructor a = new NutritionFactsByConstructor(
                100, 0, 0, 0, 0, 200);
        //나는, `servingSize`, `carbohydrate`만 사용하고 싶은데,
        //정확히 내 상황과 매칭되는 생성자가 없어, 쓸데없이 0으로 지정하는 매개변수가 많아짐.

        //2. JavaBeans 패턴
        NutritionFactsByJavaBeans b = new NutritionFactsByJavaBeans();
        b.setCalories(100);
        b.setCarbohydrate(200);
        b.setServingSize(160);

        //3. Builder 패턴
        NutritionFactsByBuilder c = new NutritionFactsByBuilder
                .Builder(100, 10)
                .calories(20)
                .build();

        //3-1. Builder, 계층형 구조에서의 장점
        NyPizza nyPizza = new NyPizza
                .Builder(Size.SMALL)
                .addTopping(Topping.SAUSAGE)
                .addTopping(Topping.ONION)
                .build();
    }
}
