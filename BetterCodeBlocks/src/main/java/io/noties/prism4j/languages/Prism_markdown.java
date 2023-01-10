package io.noties.prism4j.languages;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import io.noties.prism4j.GrammarUtils;
import io.noties.prism4j.Prism4j;
import io.noties.prism4j.Prism4j.Grammar;
import io.noties.prism4j.Prism4j.Pattern;
import io.noties.prism4j.Prism4j.Token;

public class Prism_markdown {
    @NonNull
    public static Grammar create(@NonNull Prism4j prism4j) {
        String str = "markdown";
        Grammar markdown = GrammarUtils.extend(GrammarUtils.require(prism4j, "markup"), str, new Token[0]);
        Pattern[] bold = new Pattern[1];
        java.util.regex.Pattern compile = java.util.regex.Pattern.compile("(^|[^\\\\])(\\*\\*|__)(?:(?:\\r?\\n|\\r)(?!\\r?\\n|\\r)|.)+?\\2");
        Token[] tokenArr = new Token[1];
        String str2 = "punctuation";
        tokenArr[0] = Prism4j.token(str2, Prism4j.pattern(java.util.regex.Pattern.compile("^\\*\\*|^__|\\*\\*$|__$")));
        String str3 = "inside";
        bold[0] = Prism4j.pattern(compile, true, false, null, Prism4j.grammar(str3, tokenArr));
        Token bold2 = Prism4j.token("bold", bold);
        Pattern[] italic = new Pattern[1];
        java.util.regex.Pattern compile2 = java.util.regex.Pattern.compile("(^|[^\\\\])([*_])(?:(?:\\r?\\n|\\r)(?!\\r?\\n|\\r)|.)+?\\2");
        Token[] tokenArr2 = new Token[1];
        tokenArr2[0] = Prism4j.token(str2, Prism4j.pattern(java.util.regex.Pattern.compile("^[*_]|[*_]$")));
        italic[0] = Prism4j.pattern(compile2, true, false, null, Prism4j.grammar(str3, tokenArr2));
        Token italic2 = Prism4j.token("italic", italic);
        Pattern[] url = new Pattern[1];
        java.util.regex.Pattern compile3 = java.util.regex.Pattern.compile("!?\\[[^\\]]+\\](?:\\([^\\s)]+(?:[\\t ]+\"(?:\\\\.|[^\"\\\\])*\")?\\)| ?\\[[^\\]\\n]*\\])");
        r12 = new Token[2];
        String str4 = "variable";
        r12[0] = Prism4j.token(str4, Prism4j.pattern(java.util.regex.Pattern.compile("(!?\\[)[^\\]]+(?=\\]$)"), true));
        String str5 = "string";
        r12[1] = Prism4j.token(str5, Prism4j.pattern(java.util.regex.Pattern.compile("\"(?:\\\\.|[^\"\\\\])*\"(?=\\)$)")));
        url[0] = Prism4j.pattern(compile3, false, false, null, Prism4j.grammar(str3, r12));
        String str6 = "url";
        Token url2 = Prism4j.token(str6, url);
        tokenArr2 = new Token[9];
        tokenArr2[0] = Prism4j.token("blockquote", Prism4j.pattern(java.util.regex.Pattern.compile("^>(?:[\\t ]*>)*", 8)));
        r12 = new Pattern[2];
        String str7 = "keyword";
        r12[0] = Prism4j.pattern(java.util.regex.Pattern.compile("^(?: {4}|\\t).+", 8), false, false, str7);
        r12[1] = Prism4j.pattern(java.util.regex.Pattern.compile("``.+?``|`[^`\\n]+`"), false, false, str7);
        tokenArr2[1] = Prism4j.token("code", r12);
        Pattern[] patternArr = new Pattern[2];
        java.util.regex.Pattern compile4 = java.util.regex.Pattern.compile("\\w+.*(?:\\r?\\n|\\r)(?:==+|--+)");
        Token[] tokenArr3 = new Token[1];
        tokenArr3[0] = Prism4j.token(str2, Prism4j.pattern(java.util.regex.Pattern.compile("==+$|--+$")));
        Grammar grammar = Prism4j.grammar(str3, tokenArr3);
        String str8 = "important";
        patternArr[0] = Prism4j.pattern(compile4, false, false, str8, grammar);
        java.util.regex.Pattern compile5 = java.util.regex.Pattern.compile("(^\\s*)#+.+", 8);
        Token[] tokenArr4 = new Token[1];
        tokenArr4[0] = Prism4j.token(str2, Prism4j.pattern(java.util.regex.Pattern.compile("^#+|#+$")));
        patternArr[1] = Prism4j.pattern(compile5, true, false, str8, Prism4j.grammar(str3, tokenArr4));
        tokenArr2[2] = Prism4j.token("title", patternArr);
        tokenArr2[3] = Prism4j.token("hr", Prism4j.pattern(java.util.regex.Pattern.compile("(^\\s*)([*-])(?:[\\t ]*\\2){2,}(?=\\s*$)", 8), true, false, str2));
        tokenArr2[4] = Prism4j.token("list", Prism4j.pattern(java.util.regex.Pattern.compile("(^\\s*)(?:[*+-]|\\d+\\.)(?=[\\t ].)", 8), true, false, str2));
        Pattern[] patternArr2 = new Pattern[1];
        java.util.regex.Pattern compile6 = java.util.regex.Pattern.compile("!?\\[[^\\]]+\\]:[\\t ]+(?:\\S+|<(?:\\\\.|[^>\\\\])+>)(?:[\\t ]+(?:\"(?:\\\\.|[^\"\\\\])*\"|'(?:\\\\.|[^'\\\\])*'|\\((?:\\\\.|[^)\\\\])*\\)))?");
        r2 = new Token[3];
        r2[0] = Prism4j.token(str4, Prism4j.pattern(java.util.regex.Pattern.compile("^(!?\\[)[^\\]]+"), true));
        r2[1] = Prism4j.token(str5, Prism4j.pattern(java.util.regex.Pattern.compile("(?:\"(?:\\\\.|[^\"\\\\])*\"|'(?:\\\\.|[^'\\\\])*'|\\((?:\\\\.|[^)\\\\])*\\))$")));
        r2[2] = Prism4j.token(str2, Prism4j.pattern(java.util.regex.Pattern.compile("^[\\[\\]!:]|[<>]")));
        patternArr2[0] = Prism4j.pattern(compile6, false, false, str6, Prism4j.grammar(str3, r2));
        tokenArr2[5] = Prism4j.token("url-reference", patternArr2);
        tokenArr2[6] = bold2;
        tokenArr2[7] = italic2;
        tokenArr2[8] = url2;
        GrammarUtils.insertBeforeToken(markdown, "prolog", tokenArr2);
        add(GrammarUtils.findFirstInsideGrammar(bold2), url2, italic2);
        add(GrammarUtils.findFirstInsideGrammar(italic2), url2, bold2);
        return markdown;
    }

    private static void add(@Nullable Grammar grammar, @NonNull Token first, @NonNull Token second) {
        if (grammar != null) {
            grammar.tokens().add(first);
            grammar.tokens().add(second);
        }
    }
}
