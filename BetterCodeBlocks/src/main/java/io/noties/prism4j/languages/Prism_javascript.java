package io.noties.prism4j.languages;

import androidx.annotation.NonNull;
import io.noties.prism4j.GrammarUtils;
import io.noties.prism4j.Prism4j;
import io.noties.prism4j.Prism4j.Grammar;
import io.noties.prism4j.Prism4j.Token;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Prism_javascript {
    @NonNull
    public static Grammar create(@NonNull Prism4j prism4j) {
        Grammar js = GrammarUtils.require(prism4j, "clike");
        r1 = new Token[4];
        String str = "keyword";
        r1[0] = Prism4j.token(str, Prism4j.pattern(Pattern.compile("\\b(?:as|async|await|break|case|catch|class|const|continue|debugger|default|delete|do|else|enum|export|extends|finally|for|from|function|get|if|implements|import|in|instanceof|interface|let|new|null|of|package|private|protected|public|return|set|static|super|switch|this|throw|try|typeof|var|void|while|with|yield)\\b")));
        r1[1] = Prism4j.token("number", Prism4j.pattern(Pattern.compile("\\b(?:0[xX][\\dA-Fa-f]+|0[bB][01]+|0[oO][0-7]+|NaN|Infinity)\\b|(?:\\b\\d+\\.?\\d*|\\B\\.\\d+)(?:[Ee][+-]?\\d+)?")));
        r1[2] = Prism4j.token(r6, Prism4j.pattern(Pattern.compile("[_$a-z\\xA0-\\uFFFF][$\\w\\xA0-\\uFFFF]*(?=\\s*\\()", 2)));
        r1[3] = Prism4j.token("operator", Prism4j.pattern(Pattern.compile("-[-=]?|\\+[+=]?|!=?=?|<<?=?|>>?>?=?|=(?:==?|>)?|&[&=]?|\\|[|=]?|\\*\\*?=?|\\/=?|~|\\^=?|%=?|\\?|\\.{3}")));
        js = GrammarUtils.extend(js, "javascript", r1);
        r1 = new Token[4];
        r1[0] = Prism4j.token("regex", Prism4j.pattern(Pattern.compile("((?:^|[^$\\w\\xA0-\\uFFFF.\"'\\])\\s])\\s*)\\/(\\[[^\\]\\r\\n]+]|\\\\.|[^/\\\\\\[\\r\\n])+\\/[gimyu]{0,5}(?=\\s*($|[\\r\\n,.;})\\]]))"), true, true));
        r1[1] = Prism4j.token("function-variable", Prism4j.pattern(Pattern.compile("[_$a-z\\xA0-\\uFFFF][$\\w\\xA0-\\uFFFF]*(?=\\s*=\\s*(?:function\\b|(?:\\([^()]*\\)|[_$a-z\\xA0-\\uFFFF][$\\w\\xA0-\\uFFFF]*)\\s*=>))", 2), false, false, "function"));
        r1[2] = Prism4j.token("constant", Prism4j.pattern(Pattern.compile("\\b[A-Z][A-Z\\d_]*\\b")));
        r1[3] = Prism4j.token("property", Prism4j.pattern(Pattern.compile("[-_a-z\\xA0-\\uFFFF][-\\w\\xA0-\\uFFFF]*(?=\\s*:)", 2)));
        GrammarUtils.insertBeforeToken(js, str, r1);
        Token interpolation = Prism4j.token("interpolation", new Prism4j.Pattern[0]);
        Token[] tokenArr = new Token[1];
        Prism4j.Pattern[] patternArr = new Prism4j.Pattern[1];
        Pattern compile = Pattern.compile("`(?:\\\\[\\s\\S]|\\$\\{[^}]+\\}|[^\\\\`])*`");
        Token[] tokenArr2 = new Token[2];
        tokenArr2[0] = interpolation;
        String str2 = "string";
        tokenArr2[1] = Prism4j.token(str2, Prism4j.pattern(Pattern.compile("[\\s\\S]+")));
        String str3 = "inside";
        patternArr[0] = Prism4j.pattern(compile, false, true, null, Prism4j.grammar(str3, tokenArr2));
        tokenArr[0] = Prism4j.token("template-string", patternArr);
        GrammarUtils.insertBeforeToken(js, str2, tokenArr);
        List insideInterpolation = new ArrayList(js.tokens().size() + 1);
        insideInterpolation.add(Prism4j.token("interpolation-punctuation", Prism4j.pattern(Pattern.compile("^\\$\\{|\\}$"), false, false, "punctuation")));
        insideInterpolation.addAll(js.tokens());
        interpolation.patterns().add(Prism4j.pattern(Pattern.compile("\\$\\{[^}]+\\}"), false, false, null, Prism4j.grammar(str3, insideInterpolation)));
        Grammar markup = prism4j.grammar("markup");
        if (markup != null) {
            Token[] tokenArr3 = new Token[1];
            tokenArr3[0] = Prism4j.token("script", Prism4j.pattern(Pattern.compile("(<script[\\s\\S]*?>)[\\s\\S]*?(?=<\\/script>)", 2), true, true, "language-javascript", js));
            GrammarUtils.insertBeforeToken(markup, "tag", tokenArr3);
        }
        return js;
    }
}
