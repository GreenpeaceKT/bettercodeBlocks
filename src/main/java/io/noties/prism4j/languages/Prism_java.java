package io.noties.prism4j.languages;

import androidx.annotation.NonNull;
import io.noties.prism4j.GrammarUtils;
import io.noties.prism4j.Prism4j;
import io.noties.prism4j.Prism4j.Grammar;
import io.noties.prism4j.Prism4j.Token;
import java.util.regex.Pattern;

public class Prism_java {
    @NonNull
    public static Grammar create(@NonNull Prism4j prism4j) {
        Token keyword = Prism4j.token("keyword", Prism4j.pattern(Pattern.compile("\\b(?:abstract|continue|for|new|switch|assert|default|goto|package|synchronized|boolean|do|if|private|this|break|double|implements|protected|throw|byte|else|import|public|throws|case|enum|instanceof|return|transient|catch|extends|int|short|try|char|final|interface|static|void|class|finally|long|strictfp|volatile|const|float|native|super|while)\\b")));
        Grammar java = GrammarUtils.require(prism4j, "clike");
        r4 = new Token[3];
        r4[1] = Prism4j.token("number", Prism4j.pattern(Pattern.compile("\\b0b[01]+\\b|\\b0x[\\da-f]*\\.?[\\da-fp-]+\\b|(?:\\b\\d+\\.?\\d*|\\B\\.\\d+)(?:e[+-]?\\d+)?[df]?", 2)));
        r4[2] = Prism4j.token("operator", Prism4j.pattern(Pattern.compile("(^|[^.])(?:\\+[+=]?|-[-=]?|!=?|<<?=?|>>?>?=?|==?|&[&=]?|\\|[|=]?|\\*=?|\\/=?|%=?|\\^=?|[?:~])", 8), true));
        java = GrammarUtils.extend(java, "java", r4);
        r4 = new Token[1];
        Prism4j.Pattern[] patternArr = new Prism4j.Pattern[1];
        String str = "punctuation";
        patternArr[0] = Prism4j.pattern(Pattern.compile("(^|[^.])@\\w+"), true, false, str);
        r4[0] = Prism4j.token("annotation", patternArr);
        String str2 = "function";
        GrammarUtils.insertBeforeToken(java, str2, r4);
        r4 = new Token[1];
        Prism4j.Pattern[] patternArr2 = new Prism4j.Pattern[1];
        Pattern compile = Pattern.compile("<\\s*\\w+(?:\\.\\w+)?(?:\\s*,\\s*\\w+(?:\\.\\w+)?)*>", 2);
        Token[] tokenArr = new Token[2];
        tokenArr[0] = keyword;
        tokenArr[1] = Prism4j.token(str, Prism4j.pattern(Pattern.compile("[<>(),.:]")));
        patternArr2[0] = Prism4j.pattern(compile, false, false, str2, Prism4j.grammar("inside", tokenArr));
        r4[0] = Prism4j.token("generics", patternArr2);
        GrammarUtils.insertBeforeToken(java, "class-name", r4);
        return java;
    }
}
