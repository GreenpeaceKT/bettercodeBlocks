package io.noties.prism4j.languages;

import androidx.annotation.NonNull;
import io.noties.prism4j.Prism4j;
import io.noties.prism4j.Prism4j.Grammar;
import io.noties.prism4j.Prism4j.Token;
import java.util.regex.Pattern;

public class Prism_md {
    @NonNull
    public static Grammar create(@NonNull Prism4j prism4j) {
　　　Token[] tokenArr = new Token[1];
　　　tokenArr[0] = Prism4j.token("md-sharp", Prism4j.pattern(Pattern.compile("^\\#.*", 8)));
　　　Prism4j.Pattern[] patternArr = new Prism4j.Pattern[1];
    　Pattern compile = Pattern.compile("^.*\\$ md .*$", 8);
　　　return Prism4j.grammar("md", tokenArr);
    }
}
