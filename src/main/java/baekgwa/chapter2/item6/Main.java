package baekgwa.chapter2.item6;

import java.util.regex.Pattern;

public class Main {

    private static final Pattern ROMAN = Pattern.compile("^(?=.)M*(C[MD]|D?C{0,3})"
            + "(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");

    public static void main2(String[] args) {
        for(int i=0; i<100; i++) {
            //100번 반복시, 총 101번 생성.
            String s = new String("test");
        }

        for(int i=0; i<100; i++) {
            //100번 반복 시, 최초 1회만 생성.
            String str = "test";
        }

        //매번 해당 함수를 호출할때마다, Pattern 객체를 새로 생성하여 사용함.
        //반복되어 사용될 경우, Pattern 객체를 만들고, GC 처리 대기 하고, 낭비됨
        boolean vi = isRomanNumeral("VI");

        //미리 Pattern을 compile 하여, Pattern 인스턴스를 만들어 둔뒤, 재사용
        boolean vi1 = isRomanNumeralStatic("VI");
    }

    public static void main(String[] args) {
        AutoBoxingTest();
    }

    public static void AutoBoxingTest() {

        //Wrapper Class 사용
        long startTime = System.currentTimeMillis();
        Long sum = 0L;
        for(long i = 0; i <= Integer.MAX_VALUE; i++) {
            sum += i;
        }
        long endTime = System.currentTimeMillis(); // 종료 시간 기록
        System.out.println("Use Auto Boxing : " + (endTime - startTime) + " ms");
        //5811ms

        //Primitive Type 사용
        startTime = System.currentTimeMillis();
        long sum2 = 0L;
        for(long i = 0; i <= Integer.MAX_VALUE; i++) {
            sum2 += i;
        }
        endTime = System.currentTimeMillis(); // 종료 시간 기록
        System.out.println("Not Use Auto Boxing : " + (endTime - startTime) + " ms");
        //651ms
    }

    public static boolean isRomanNumeral(String s) {
        return s.matches("^(?=.)M*(C[MD]|D?C{0,3})"
                + "(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");
    }

    public static boolean isRomanNumeralStatic(String s) {
        return ROMAN.matcher(s).matches();
    }
}
