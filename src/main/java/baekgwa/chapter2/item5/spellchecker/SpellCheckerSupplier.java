package baekgwa.chapter2.item5.spellchecker;

import java.util.List;
import java.util.function.Supplier;

public class SpellCheckerSupplier {
    private final Lexicon dictionary;

    public SpellCheckerSupplier(Supplier<? extends Lexicon> dictionarySupplier) {
        this.dictionary = dictionarySupplier.get();
    }

    public boolean isValid(String word) { return true; }
    public List<String> suggestions(String typo) { return List.of(); }
}
