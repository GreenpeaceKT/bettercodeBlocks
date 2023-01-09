package io.noties.prism4j.languages;

import androidx.annotation.NonNull;
import io.noties.prism4j.GrammarUtils;
import io.noties.prism4j.GrammarUtils.TokenFilter;
import io.noties.prism4j.Prism4j;
import io.noties.prism4j.Prism4j.Grammar;
import io.noties.prism4j.Prism4j.Token;
import java.util.regex.Pattern;

public class Prism_go {
    @NonNull
    public static Grammar create(@NonNull Prism4j prism4j) {
        Grammar go = GrammarUtils.require(prism4j, "clike");
        AnonymousClass1 anonymousClass1 = new TokenFilter() {
            public boolean test(@NonNull Token token) {
                return "class-name".equals(token.name()) ^ 1;
            }
        };
        r2 = new Token[5];
        r2[0] = Prism4j.token("keyword", Prism4j.pattern(Pattern.compile("\\b(?:break|case|chan|const|continue|default|defer|else|fallthrough|for|func|go(?:to)?|if|import|interface|map|package|range|return|select|struct|switch|type|var)\\b")));
        String str = "boolean";
        r2[1] = Prism4j.token(str, Prism4j.pattern(Pattern.compile("\\b(?:_|iota|nil|true|false)\\b")));
        r2[2] = Prism4j.token("operator", Prism4j.pattern(Pattern.compile("[*\\/%^!=]=?|\\+[=+]?|-[=-]?|\\|[=|]?|&(?:=|&|\\^=?)?|>(?:>=?|=)?|<(?:<=?|=|-)?|:=|\\.\\.\\.")));
        r2[3] = Prism4j.token("number", Prism4j.pattern(Pattern.compile("(?:\\b0x[a-f\\d]+|(?:\\b\\d+\\.?\\d*|\\B\\.\\d+)(?:e[-+]?\\d+)?)i?", 2)));
        r2[4] = Prism4j.token("string", Prism4j.pattern(Pattern.compile("([\"'`])(\\\\[\\s\\S]|(?!\\1)[^\\\\])*\\1"), false, true));
        go = GrammarUtils.extend(go, "go", anonymousClass1, r2);
        Token[] tokenArr = new Token[1];
        tokenArr[0] = Prism4j.token("builtin", Prism4j.pattern(Pattern.compile("\\b(?:bool|byte|complex(?:64|128)|error|float(?:32|64)|rune|string|u?int(?:8|16|32|64)?|uintptr|append|cap|close|complex|copy|delete|imag|len|make|new|panic|print(?:ln)?|real|recover)\\b")));
        GrammarUtils.insertBeforeToken(go, str, tokenArr);
        return go;
    }
}
