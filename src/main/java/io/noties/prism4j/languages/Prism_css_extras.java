package io.noties.prism4j.languages;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import io.noties.prism4j.GrammarUtils;
import io.noties.prism4j.Prism4j;
import io.noties.prism4j.Prism4j.Grammar;
import io.noties.prism4j.Prism4j.Pattern;
import io.noties.prism4j.Prism4j.Token;

public class Prism_css_extras {
    @Nullable
    public static Grammar create(@NonNull Prism4j prism4j) {
        Grammar css = prism4j.grammar("css");
        if (css != null) {
            Token selector = GrammarUtils.findToken(css, "selector");
            if (selector != null) {
                Pattern pattern = java.util.regex.Pattern.compile("[^{}\\s][^{}]*(?=\\s*\\{)");
                r8 = new Token[5];
                r8[0] = Prism4j.token("pseudo-element", Prism4j.pattern(java.util.regex.Pattern.compile(":(?:after|before|first-letter|first-line|selection)|::[-\\w]+")));
                r8[1] = Prism4j.token("pseudo-class", Prism4j.pattern(java.util.regex.Pattern.compile(":[-\\w]+(?:\\(.*\\))?")));
                r8[2] = Prism4j.token("class", Prism4j.pattern(java.util.regex.Pattern.compile("\\.[-:.\\w]+")));
                r8[3] = Prism4j.token("id", Prism4j.pattern(java.util.regex.Pattern.compile("#[-:.\\w]+")));
                r8[4] = Prism4j.token("attribute", Prism4j.pattern(java.util.regex.Pattern.compile("\\[[^\\]]+\\]")));
                pattern = Prism4j.pattern(pattern, false, false, null, Prism4j.grammar("inside", r8));
                selector.patterns().clear();
                selector.patterns().add(pattern);
            }
            r3 = new Token[3];
            r3[0] = Prism4j.token("hexcode", Prism4j.pattern(java.util.regex.Pattern.compile("#[\\da-f]{3,8}", 2)));
            r3[1] = Prism4j.token("entity", Prism4j.pattern(java.util.regex.Pattern.compile("\\\\[\\da-f]{1,8}", 2)));
            r3[2] = Prism4j.token("number", Prism4j.pattern(java.util.regex.Pattern.compile("[\\d%.]+")));
            GrammarUtils.insertBeforeToken(css, "function", r3);
        }
        return null;
    }
}
