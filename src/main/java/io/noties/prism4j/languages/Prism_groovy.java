package io.noties.prism4j.languages;

import androidx.annotation.NonNull;
import io.noties.prism4j.GrammarUtils;
import io.noties.prism4j.Prism4j;
import io.noties.prism4j.Prism4j.Grammar;
import io.noties.prism4j.Prism4j.Token;
import java.util.regex.Pattern;

public class Prism_groovy {
    @NonNull
    public static Grammar create(@NonNull Prism4j prism4j) {
        Grammar groovy = GrammarUtils.require(prism4j, "clike");
        r1 = new Token[5];
        r1[0] = Prism4j.token("keyword", Prism4j.pattern(Pattern.compile("\\b(?:as|def|in|abstract|assert|boolean|break|byte|case|catch|char|class|const|continue|default|do|double|else|enum|extends|final|finally|float|for|goto|if|implements|import|instanceof|int|interface|long|native|new|package|private|protected|public|return|short|static|strictfp|super|switch|synchronized|this|throw|throws|trait|transient|try|void|volatile|while)\\b")));
        String str = "string";
        r1[1] = Prism4j.token(str, Prism4j.pattern(Pattern.compile("(\"\"\"|''')[\\s\\S]*?\\1|(?:\\$\\/)(?:\\$\\/\\$|[\\s\\S])*?\\/\\$"), false, true), Prism4j.pattern(Pattern.compile("([\"'\\/])(?:\\\\.|(?!\\1)[^\\\\\\r\\n])*\\1"), false, true));
        r1[2] = Prism4j.token("number", Prism4j.pattern(Pattern.compile("\\b(?:0b[01_]+|0x[\\da-f_]+(?:\\.[\\da-f_p\\-]+)?|[\\d_]+(?:\\.[\\d_]+)?(?:e[+-]?[\\d]+)?)[glidf]?\\b", 2)));
        r1[3] = Prism4j.token("operator", Prism4j.pattern(Pattern.compile("(^|[^.])(?:~|==?~?|\\?[.:]?|\\*(?:[.=]|\\*=?)?|\\.[@&]|\\.\\.<|\\.{1,2}(?!\\.)|-[-=>]?|\\+[+=]?|!=?|<(?:<=?|=>?)?|>(?:>>?=?|=)?|&[&=]?|\\|[|=]?|\\/=?|\\^=?|%=?)"), true));
        String str2 = "punctuation";
        r1[4] = Prism4j.token(str2, Prism4j.pattern(Pattern.compile("\\.+|[{}\\[\\];(),:$]")));
        groovy = GrammarUtils.extend(groovy, "groovy", r1);
        r1 = new Token[1];
        r1[0] = Prism4j.token("shebang", Prism4j.pattern(Pattern.compile("#!.+"), false, false, "comment"));
        GrammarUtils.insertBeforeToken(groovy, str, r1);
        r1 = new Token[1];
        r1[0] = Prism4j.token("spock-block", Prism4j.pattern(Pattern.compile("\\b(?:setup|given|when|then|and|cleanup|expect|where):")));
        GrammarUtils.insertBeforeToken(groovy, str2, r1);
        r1 = new Token[1];
        r1[0] = Prism4j.token("annotation", Prism4j.pattern(Pattern.compile("(^|[^.])@\\w+"), true, false, str2));
        GrammarUtils.insertBeforeToken(groovy, "function", r1);
        return groovy;
    }
}
