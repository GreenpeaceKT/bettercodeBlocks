package io.noties.prism4j.languages;

import androidx.annotation.NonNull;
import io.noties.prism4j.GrammarUtils;
import io.noties.prism4j.Prism4j;
import io.noties.prism4j.Prism4j.Grammar;
import io.noties.prism4j.Prism4j.Token;
import java.util.regex.Pattern;

public class Prism_dart {
    @NonNull
    public static Grammar create(@NonNull Prism4j prism4j) {
        Grammar dart = GrammarUtils.require(prism4j, "clike");
        r1 = new Token[3];
        r1[0] = Prism4j.token("string", Prism4j.pattern(Pattern.compile("r?(\"\"\"|''')[\\s\\S]*?\\1"), false, true), Prism4j.pattern(Pattern.compile("r?(\"|')(?:\\\\.|(?!\\1)[^\\\\\\r\\n])*\\1"), false, true));
        r1[1] = Prism4j.token("keyword", Prism4j.pattern(Pattern.compile("\\b(?:async|sync|yield)\\*")), Prism4j.pattern(Pattern.compile("\\b(?:abstract|assert|async|await|break|case|catch|class|const|continue|default|deferred|do|dynamic|else|enum|export|external|extends|factory|final|finally|for|get|if|implements|import|in|library|new|null|operator|part|rethrow|return|set|static|super|switch|this|throw|try|typedef|var|void|while|with|yield)\\b")));
        r1[2] = Prism4j.token("operator", Prism4j.pattern(Pattern.compile("\\bis!|\\b(?:as|is)\\b|\\+\\+|--|&&|\\|\\||<<=?|>>=?|~(?:\\/=?)?|[+\\-*\\/%&^|=!<>]=?|\\?")));
        dart = GrammarUtils.extend(dart, "dart", r1);
        r1 = new Token[1];
        r1[0] = Prism4j.token("metadata", Prism4j.pattern(Pattern.compile("@\\w+"), false, false, "symbol"));
        GrammarUtils.insertBeforeToken(dart, "function", r1);
        return dart;
    }
}
