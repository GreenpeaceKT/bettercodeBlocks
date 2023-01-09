package io.noties.prism4j.languages;

import androidx.annotation.NonNull;
import io.noties.prism4j.GrammarUtils;
import io.noties.prism4j.GrammarUtils.TokenFilter;
import io.noties.prism4j.Prism4j;
import io.noties.prism4j.Prism4j.Grammar;
import io.noties.prism4j.Prism4j.Token;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Prism_kotlin {
    @NonNull
    public static Grammar create(@NonNull Prism4j prism4j) {
        Grammar kotlin = GrammarUtils.require(prism4j, "clike");
        AnonymousClass1 anonymousClass1 = new TokenFilter() {
            public boolean test(@NonNull Token token) {
                return "class-name".equals(token.name()) ^ 1;
            }
        };
        r2 = new Token[4];
        String str = "keyword";
        r2[0] = Prism4j.token(str, Prism4j.pattern(Pattern.compile("(^|[^.])\\b(?:abstract|actual|annotation|as|break|by|catch|class|companion|const|constructor|continue|crossinline|data|do|dynamic|else|enum|expect|external|final|finally|for|fun|get|if|import|in|infix|init|inline|inner|interface|internal|is|lateinit|noinline|null|object|open|operator|out|override|package|private|protected|public|reified|return|sealed|set|super|suspend|tailrec|this|throw|to|try|typealias|val|var|vararg|when|where|while)\\b"), true));
        String str2 = "function";
        r2[1] = Prism4j.token(str2, Prism4j.pattern(Pattern.compile("\\w+(?=\\s*\\()")), Prism4j.pattern(Pattern.compile("(\\.)\\w+(?=\\s*\\{)"), true));
        r2[2] = Prism4j.token("number", Prism4j.pattern(Pattern.compile("\\b(?:0[xX][\\da-fA-F]+(?:_[\\da-fA-F]+)*|0[bB][01]+(?:_[01]+)*|\\d+(?:_\\d+)*(?:\\.\\d+(?:_\\d+)*)?(?:[eE][+-]?\\d+(?:_\\d+)*)?[fFL]?)\\b")));
        r2[3] = Prism4j.token("operator", Prism4j.pattern(Pattern.compile("\\+[+=]?|-[-=>]?|==?=?|!(?:!|==?)?|[\\/*%<>]=?|[?:]:?|\\.\\.|&&|\\|\\||\\b(?:and|inv|or|shl|shr|ushr|xor)\\b")));
        kotlin = GrammarUtils.extend(kotlin, "kotlin", anonymousClass1, r2);
        Token[] tokenArr = new Token[1];
        Prism4j.Pattern[] patternArr = new Prism4j.Pattern[1];
        String str3 = "string";
        patternArr[0] = Prism4j.pattern(Pattern.compile("(\"\"\"|''')[\\s\\S]*?\\1"), false, false, str3);
        String str4 = "raw-string";
        tokenArr[0] = Prism4j.token(str4, patternArr);
        GrammarUtils.insertBeforeToken(kotlin, str3, tokenArr);
        tokenArr = new Token[1];
        tokenArr[0] = Prism4j.token("annotation", Prism4j.pattern(Pattern.compile("\\B@(?:\\w+:)?(?:[A-Z]\\w*|\\[[^\\]]+\\])"), false, false, "builtin"));
        GrammarUtils.insertBeforeToken(kotlin, str, tokenArr);
        tokenArr = new Token[1];
        tokenArr[0] = Prism4j.token("label", Prism4j.pattern(Pattern.compile("\\w+@|@\\w+"), false, false, "symbol"));
        GrammarUtils.insertBeforeToken(kotlin, str2, tokenArr);
        List tokens = new ArrayList(kotlin.tokens().size() + 1);
        patternArr = new Prism4j.Pattern[1];
        str2 = "variable";
        patternArr[0] = Prism4j.pattern(Pattern.compile("^\\$\\{|\\}$"), false, false, str2);
        tokens.add(Prism4j.token("delimiter", patternArr));
        tokens.addAll(kotlin.tokens());
        r2 = new Token[1];
        r4 = new Prism4j.Pattern[2];
        String str5 = "inside";
        r4[0] = Prism4j.pattern(Pattern.compile("\\$\\{[^}]+\\}"), false, false, null, Prism4j.grammar(str5, tokens));
        r4[1] = Prism4j.pattern(Pattern.compile("\\$\\w+"), false, false, str2);
        r2[0] = Prism4j.token("interpolation", r4);
        Grammar interpolationInside = Prism4j.grammar(str5, r2);
        Token string = GrammarUtils.findToken(kotlin, str3);
        Token rawString = GrammarUtils.findToken(kotlin, str4);
        if (string == null || rawString == null) {
            throw new RuntimeException("Unexpected state, cannot find `string` and/or `raw-string` tokens inside kotlin grammar");
        }
        Prism4j.Pattern stringPattern = (Prism4j.Pattern) string.patterns().get(0);
        Prism4j.Pattern rawStringPattern = (Prism4j.Pattern) rawString.patterns().get(0);
        string.patterns().add(Prism4j.pattern(stringPattern.regex(), stringPattern.lookbehind(), stringPattern.greedy(), stringPattern.alias(), interpolationInside));
        rawString.patterns().add(Prism4j.pattern(rawStringPattern.regex(), rawStringPattern.lookbehind(), rawStringPattern.greedy(), rawStringPattern.alias(), interpolationInside));
        string.patterns().remove(0);
        rawString.patterns().remove(0);
        return kotlin;
    }
}
