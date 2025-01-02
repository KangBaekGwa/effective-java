package baekgwa.chapter2.item5;

import baekgwa.chapter2.item5.spellchecker.Lexicon1;
import baekgwa.chapter2.item5.spellchecker.Lexicon2;
import baekgwa.chapter2.item5.spellchecker.SpellCheckerDIP;
import baekgwa.chapter2.item5.spellchecker.SpellCheckerSupplier;
import java.util.function.Supplier;

public class Main {

    public static void main(String[] args) {
        //클라이언트 A에서 사용하는 경우,
        Lexicon1 dictionary1 = new Lexicon1();
        SpellCheckerDIP spellCheckerDIP1 = new SpellCheckerDIP(dictionary1);

        //클라이언트 B에서 사용하는 경우,
        Lexicon2 dictionary2 = new Lexicon2();
        SpellCheckerDIP spellCheckerDIP2 = new SpellCheckerDIP(dictionary2);
    }

    public static void main2(String[] args) {
        // 클라이언트 A: Lexicon1 사용
        Supplier<Lexicon1> lexicon1Factory = Lexicon1::new; // Lexicon1 생성 팩토리
        SpellCheckerSupplier SpellCheckerSupplier1 = new SpellCheckerSupplier(lexicon1Factory);

        // 클라이언트 B: Lexicon2 사용
        Supplier<Lexicon2> lexicon2Factory = Lexicon2::new; // Lexicon2 생성 팩토리
        SpellCheckerSupplier SpellCheckerSupplier2 = new SpellCheckerSupplier(lexicon2Factory);
    }

}
