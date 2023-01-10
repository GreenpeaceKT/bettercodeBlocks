package io.noties.prism4j.languages;

import androidx.annotation.NonNull;
import io.noties.prism4j.GrammarUtils;
import io.noties.prism4j.Prism4j;
import io.noties.prism4j.Prism4j.Grammar;
import io.noties.prism4j.Prism4j.Token;
import java.util.regex.Pattern;

public abstract class Prism_css {
    @NonNull
    public static Grammar create(@NonNull Prism4j prism4j) {
        grammar = new Token[8];
        grammar[0] = Prism4j.token("comment", Prism4j.pattern(Pattern.compile("\\/\\*[\\s\\S]*?\\*\\/")));
        Prism4j.Pattern[] patternArr = new Prism4j.Pattern[1];
        Pattern compile = Pattern.compile("@[\\w-]+?.*?(?:;|(?=\\s*\\{))", 2);
        Token[] tokenArr = new Token[2];
        tokenArr[0] = Prism4j.token("rule", Prism4j.pattern(Pattern.compile("@[\\w-]+")));
        tokenArr[1] = Prism4j.token("csstext",Prism4j.pattern(Pattern.compile("(?<=\.)(.*)")));
        String str = "inside";
        patternArr[0] = Prism4j.pattern(compile, false, false, null, Prism4j.grammar(str, tokenArr));
        String str2 = "atrule";
        grammar[1] = Prism4j.token(str2, patternArr);
        grammar[2] = Prism4j.token("url", Prism4j.pattern(Pattern.compile("url\\((?:([\"'])(?:\\\\(?:\\r\\n|[\\s\\S])|(?!\\1)[^\\\\\\r\\n])*\\1|.*?)\\)", 2)));
        grammar[3] = Prism4j.token("selector", Prism4j.pattern(Pattern.compile("[^{}\\s][^{};]*?(?=\\s*\\{)")));
        grammar[4] = Prism4j.token("string", Prism4j.pattern(Pattern.compile("(\"|')(?:\\\\(?:\\r\\n|[\\s\\S])|(?!\\1)[^\\\\\\r\\n])*\\1"), false, true));
        //grammar[5] = Prism4j.token("property", Prism4j.pattern(Pattern.compile("[-_a-z\\xA0-\\uFFFF][-\\w\\xA0-\\uFFFF]*(?=\\s*:)", 2)));
        grammar[5] = Prism4j.token("important", Prism4j.pattern(Pattern.compile("\\B!important\\b", 2)));
        grammar[6] = Prism4j.token("function", Prism4j.pattern(Pattern.compile("[-a-z0-9]+(?=\\()", 2)));
        String str3 = "punctuation";
        grammar[7] = Prism4j.token(str3, Prism4j.pattern(Pattern.compile("[(){};:]")));
        Grammar grammar = Prism4j.grammar("css", grammar);
        Token atrule = (Token) grammar.tokens().get(1);
        Grammar inside = GrammarUtils.findFirstInsideGrammar(atrule);
        if (inside != null) {
            for (Token token : grammar.tokens()) {
                if (!str2.equals(token.name())) {
                    inside.tokens().add(token);
                }
            }
        }
        Grammar markup = prism4j.grammar("markup");
        if (markup != null) {
            Token[] tokenArr2 = new Token[1];
            Prism4j.Pattern[] patternArr2 = new Prism4j.Pattern[1];
            String str4 = "language-css";
            patternArr2[0] = Prism4j.pattern(Pattern.compile("(<style[\\s\\S]*?>)[\\s\\S]*?(?=<\\/style>)", 2), true, true, str4, grammar);
            tokenArr2[0] = Prism4j.token("style", patternArr2);
            Token token2 = "tag";
            GrammarUtils.insertBeforeToken(markup, token2, tokenArr2);
            Grammar markupTagInside = null;
            token2 = GrammarUtils.findToken(markup, token2);
            if (token2 != null) {
                markupTagInside = GrammarUtils.findFirstInsideGrammar(token2);
                if (markupTagInside != null) {
                    markupTagInside = GrammarUtils.clone(markupTagInside);
                }
            }
            Token[] tokenArr3 = new Token[1];
            Prism4j.Pattern[] patternArr3 = new Prism4j.Pattern[1];
            Pattern compile2 = Pattern.compile("\\s*style=(\"|')(?:\\\\[\\s\\S]|(?!\\1)[^\\\\])*\\1", 2);
            tokenArr = new Token[3];
            Prism4j.Pattern[] patternArr4 = new Prism4j.Pattern[1];
            patternArr4[0] = Prism4j.pattern(Pattern.compile("^\\s*style", 2), false, false, null, markupTagInside);
            tokenArr[0] = Prism4j.token("attr-name", patternArr4);
            tokenArr[1] = Prism4j.token(str3, Prism4j.pattern(Pattern.compile("^\\s*=\\s*['\"]|['\"]\\s*$")));
            tokenArr[2] = Prism4j.token("attr-value", Prism4j.pattern(Pattern.compile(".+", 2), false, false, null, grammar));
            patternArr3[0] = Prism4j.pattern(compile2, false, false, str4, Prism4j.grammar(str, tokenArr));
            tokenArr3[0] = Prism4j.token("style-attr", patternArr3);
            GrammarUtils.insertBeforeToken(markup, "tag/attr-value", tokenArr3);
        }
        return grammar;
    }

    private Prism_css() {
    }
}
