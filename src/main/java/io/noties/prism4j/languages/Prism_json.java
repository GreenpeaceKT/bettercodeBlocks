package io.noties.prism4j.languages;

import androidx.annotation.NonNull;
import io.noties.prism4j.Prism4j;
import io.noties.prism4j.Prism4j.Grammar;
import io.noties.prism4j.Prism4j.Token;
import java.util.regex.Pattern;

public class Prism_json {
    @NonNull
    public static Grammar create(@NonNull Prism4j prism4j) {
        Token[] tokenArr = new Token[7];
        tokenArr[0] = Prism4j.token("property", Prism4j.pattern(Pattern.compile("\"(?:\\\\.|[^\\\\\"\\r\\n])*\"(?=\\s*:)", 2)));
        tokenArr[1] = Prism4j.token("string", Prism4j.pattern(Pattern.compile("\"(?:\\\\.|[^\\\\\"\\r\\n])*\"(?!\\s*:)"), false, true));
        tokenArr[2] = Prism4j.token("number", Prism4j.pattern(Pattern.compile("\\b0x[\\dA-Fa-f]+\\b|(?:\\b\\d+\\.?\\d*|\\B\\.\\d+)(?:[Ee][+-]?\\d+)?")));
        tokenArr[3] = Prism4j.token("punctuation", Prism4j.pattern(Pattern.compile("[{}\\[\\]);,]")));
        tokenArr[4] = Prism4j.token("operator", Prism4j.pattern(Pattern.compile(":")));
        tokenArr[5] = Prism4j.token("boolean", Prism4j.pattern(Pattern.compile("\\b(?:true|false)\\b", 2)));
        tokenArr[6] = Prism4j.token("null", Prism4j.pattern(Pattern.compile("\\bnull\\b", 2)));
        return Prism4j.grammar("json", tokenArr);
    }
}
