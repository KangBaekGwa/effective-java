package baekgwa.chapter2.item5.spellchecker;

import java.util.List;

public class SpellCheckerSingleton {
    private final Lexicon dictionary = new Lexicon1();
    private SpellCheckerSingleton() {}

    public static SpellCheckerSingleton INSTANCE = new SpellCheckerSingleton();

    public static boolean isValid(String word) { return true; }
    public static List<String> suggestions(String typo) { return List.of(); }
}
