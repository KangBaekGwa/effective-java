package baekgwa.chapter2.item2.nutrition;

public class NutritionFactsByConstructor {
    private final int servingSize;
    private final int servings;
    private final int calories;
    private final int fat;
    private final int sodium;
    private final int carbohydrate;

    public NutritionFactsByConstructor(int calories, int servings, int servingSize) {
        this(servingSize, servings, calories, 0, 0, 0);
    }

    public NutritionFactsByConstructor(int fat, int calories, int servings, int servingSize) {
        this(servingSize, servings, calories, fat, 0, 0);
    }

    public NutritionFactsByConstructor(int sodium, int fat, int calories, int servings, int servingSize) {
        this(servingSize, servings, calories, fat, sodium, 0);
    }

    public NutritionFactsByConstructor(int servingSize, int servings, int calories, int fat, int sodium,
            int carbohydrate) {
        this.servingSize = servingSize;
        this.servings = servings;
        this.calories = calories;
        this.fat = fat;
        this.sodium = sodium;
        this.carbohydrate = carbohydrate;
    }
}
