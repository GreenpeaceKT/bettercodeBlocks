package io.noties.prism4j.languages;

import androidx.annotation.NonNull;
import io.noties.prism4j.Prism4j;
import io.noties.prism4j.Prism4j.Grammar;
import io.noties.prism4j.Prism4j.Token;
import java.util.regex.Pattern;

public abstract class Prism_clike {
    @NonNull
    public static Grammar create(@NonNull Prism4j prism4j) {
        Token[] tokenArr = new Token[9];
        tokenArr[0] = Prism4j.token("comment", Prism4j.pattern(Pattern.compile("(^|[^\\\\])\\/\\*[\\s\\S]*?(?:\\*\\/|$)"), true), Prism4j.pattern(Pattern.compile("(^|[^\\\\:])\\/\\/.*"), true, true));
        tokenArr[1] = Prism4j.token("string", Prism4j.pattern(Pattern.compile("([\"'])(?:\\\\(?:\\r\\n|[\\s\\S])|(?!\\1)[^\\\\\\r\\n])*\\1"), false, true));
        Prism4j.Pattern[] patternArr = new Prism4j.Pattern[1];
        Pattern compile = Pattern.compile("((?:\\b(?:class|interface|extends|implements|trait|instanceof|new)\\s+)|(?:catch\\s+\\())[\\w.\\\\]+");
        Token[] tokenArr2 = new Token[1];
        String str = "punctuation";
        tokenArr2[0] = Prism4j.token(str, Prism4j.pattern(Pattern.compile("[.\\\\]")));
        patternArr[0] = Prism4j.pattern(compile, true, false, null, Prism4j.grammar("inside", tokenArr2));
        tokenArr[2] = Prism4j.token("class-name", patternArr);
        tokenArr[3] = Prism4j.token("keyword", Prism4j.pattern(Pattern.compile("\\b(?:if|else|while|do|for|return|in|instanceof|function|new|try|throw|catch|finally|null|break|continue)\\b")));
        tokenArr[4] = Prism4j.token("boolean", Prism4j.pattern(Pattern.compile("\\b(?:true|false)\\b")));
        tokenArr[5] = Prism4j.token("function", Prism4j.pattern(Pattern.compile("[a-z0-9_]+(?=\\()", 2)));
        tokenArr[6] = Prism4j.token("number", Prism4j.pattern(Pattern.compile("\\b0x[\\da-f]+\\b|(?:\\b\\d+\\.?\\d*|\\B\\.\\d+)(?:e[+-]?\\d+)?", 2)));
        tokenArr[7] = Prism4j.token("operator", Prism4j.pattern(Pattern.compile("--?|\\+\\+?|!=?=?|<=?|>=?|==?=?|&&?|\\|\\|?|\\?|\\*|\\/|~|\\^|%")));
        tokenArr[8] = Prism4j.token(str, Prism4j.pattern(Pattern.compile("[{}\\[\\];(),.:]")));
        return Prism4j.grammar("clike", tokenArr);
    }

    private Prism_clike() {
    }
}
