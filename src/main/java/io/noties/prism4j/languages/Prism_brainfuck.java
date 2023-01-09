package io.noties.prism4j.languages;

import androidx.annotation.NonNull;
import io.noties.prism4j.Prism4j;
import io.noties.prism4j.Prism4j.Grammar;
import io.noties.prism4j.Prism4j.Token;
import java.util.regex.Pattern;

public class Prism_brainfuck {
    @NonNull
    public static Grammar create(@NonNull Prism4j prism4j) {
        r0 = new Token[6];
        r0[0] = Prism4j.token("pointer", Prism4j.pattern(Pattern.compile("<|>"), false, false, "keyword"));
        r0[1] = Prism4j.token("increment", Prism4j.pattern(Pattern.compile("\\+"), false, false, "inserted"));
        r0[2] = Prism4j.token("decrement", Prism4j.pattern(Pattern.compile("-"), false, false, "deleted"));
        r0[3] = Prism4j.token("branching", Prism4j.pattern(Pattern.compile("\\[|\\]"), false, false, "important"));
        r0[4] = Prism4j.token("operator", Prism4j.pattern(Pattern.compile("[.,]")));
        r0[5] = Prism4j.token("comment", Prism4j.pattern(Pattern.compile("\\S+")));
        return Prism4j.grammar("brainfuck", r0);
    }
}
