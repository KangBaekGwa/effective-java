package baekgwa.chapter2.item5.spellchecker;

import java.util.List;

public class SpellCheckerDIP {
    private final Lexicon dictionary;

    public SpellCheckerDIP(Lexicon dictionary) {
        this.dictionary = dictionary;
    }

    public boolean isValid(String word) { return true; }
    public List<String> suggestions(String typo) { return List.of(); }
}
