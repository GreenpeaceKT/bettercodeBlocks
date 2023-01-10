package io.noties.prism4j.languages;

import androidx.annotation.NonNull;
import io.noties.prism4j.GrammarUtils;
import io.noties.prism4j.GrammarUtils.TokenFilter;
import io.noties.prism4j.Prism4j;
import io.noties.prism4j.Prism4j.Grammar;
import io.noties.prism4j.Prism4j.Token;
import java.util.regex.Pattern;

public class Prism_scala {
    @NonNull
    public static Grammar create(@NonNull Prism4j prism4j) {
        Grammar scala = GrammarUtils.require(prism4j, "java");
        AnonymousClass1 anonymousClass1 = new TokenFilter() {
            public boolean test(@NonNull Token token) {
                String name = token.name();
                return ("class-name".equals(name) || "function".equals(name)) ? false : true;
            }
        };
        r2 = new Token[3];
        r2[0] = Prism4j.token("keyword", Prism4j.pattern(Pattern.compile("<-|=>|\\b(?:abstract|case|catch|class|def|do|else|extends|final|finally|for|forSome|if|implicit|import|lazy|match|new|null|object|override|package|private|protected|return|sealed|self|super|this|throw|trait|try|type|val|var|while|with|yield)\\b")));
        r2[1] = Prism4j.token("string", Prism4j.pattern(Pattern.compile("\"\"\"[\\s\\S]*?\"\"\""), false, true), Prism4j.pattern(Pattern.compile("(\"|')(?:\\\\.|(?!\\1)[^\\\\\\r\\n])*\\1"), false, true));
        String str = "number";
        r2[2] = Prism4j.token(str, Prism4j.pattern(Pattern.compile("\\b0x[\\da-f]*\\.?[\\da-f]+|(?:\\b\\d+\\.?\\d*|\\B\\.\\d+)(?:e\\d+)?[dfl]?", 2)));
        scala = GrammarUtils.extend(scala, "scala", anonymousClass1, r2);
        scala.tokens().add(Prism4j.token("symbol", Prism4j.pattern(Pattern.compile("'[^\\d\\s\\\\]\\w*"))));
        Token[] tokenArr = new Token[1];
        tokenArr[0] = Prism4j.token("builtin", Prism4j.pattern(Pattern.compile("\\b(?:String|Int|Long|Short|Byte|Boolean|Double|Float|Char|Any|AnyRef|AnyVal|Unit|Nothing)\\b")));
        GrammarUtils.insertBeforeToken(scala, str, tokenArr);
        return scala;
    }
}
