package io.noties.prism4j.languages;

import androidx.annotation.NonNull;
import io.noties.prism4j.Prism4j;
import io.noties.prism4j.Prism4j.Grammar;
import io.noties.prism4j.Prism4j.Token;
import java.util.regex.Pattern;

public class Prism_fix {
    @NonNull
    public static Grammar create(@NonNull Prism4j prism4j) {
　　　Token[] tokenArr = new Token[1];
　　　tokenArr[0] = Prism4j.token("fixtext", Prism4j.pattern(Pattern.compile("[\s\S]", 8)));
　　　Prism4j.Pattern[] patternArr = new Prism4j.Pattern[1];
    　Pattern compile = Pattern.compile("^.*\\$ fix .*$", 8);
　　　return Prism4j.grammar("fix", tokenArr);
    }
}
