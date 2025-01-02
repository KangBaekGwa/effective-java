package baekgwa.chapter2.item5.spellchecker;

import java.util.List;

public class SpellCheckerStatic {

    private static final Lexicon1 dictionary = new Lexicon1();

    public static boolean isValid(String word) { return true; }
    public static List<String> suggestions(String typo) { return List.of(); }
}
