package io.noties.prism4j.languages;

import androidx.annotation.NonNull;
import io.noties.prism4j.Prism4j;
import io.noties.prism4j.Prism4j.Grammar;
import io.noties.prism4j.Prism4j.Token;
import java.util.regex.Pattern;

public class Prism_latex {
    @NonNull
    public static Grammar create(@NonNull Prism4j prism4j) {
        Pattern funcPattern = Pattern.compile("\\\\(?:[^a-z()\\[\\]]|[a-z*]+)", 2);
        Token[] insideEqu = new Token[1];
        insideEqu[0] = Prism4j.token("equation-command", Prism4j.pattern(funcPattern, false, false, "regex"));
        Grammar insideEqu2 = Prism4j.grammar("inside", insideEqu);
        Token[] tokenArr = new Token[8];
        tokenArr[0] = Prism4j.token("comment", Prism4j.pattern(Pattern.compile("%.*", 8)));
        tokenArr[1] = Prism4j.token("cdata", Prism4j.pattern(Pattern.compile("(\\\\begin\\{((?:verbatim|lstlisting)\\*?)\\})[\\s\\S]*?(?=\\\\end\\{\\2\\})"), true));
        r4 = new Prism4j.Pattern[2];
        String str = "string";
        r4[0] = Prism4j.pattern(Pattern.compile("\\$(?:\\\\[\\s\\S]|[^\\\\$])*\\$|\\\\\\([\\s\\S]*?\\\\\\)|\\\\\\[[\\s\\S]*?\\\\\\]"), false, false, str, insideEqu2);
        r4[1] = Prism4j.pattern(Pattern.compile("(\\\\begin\\{((?:equation|math|eqnarray|align|multline|gather)\\*?)\\})[\\s\\S]*?(?=\\\\end\\{\\2\\})"), true, false, str, insideEqu2);
        tokenArr[2] = Prism4j.token("equation", r4);
        tokenArr[3] = Prism4j.token("keyword", Prism4j.pattern(Pattern.compile("(\\\\(?:begin|end|ref|cite|label|usepackage|documentclass)(?:\\[[^\\]]+\\])?\\{)[^}]+(?=\\})"), true));
        tokenArr[4] = Prism4j.token("url", Prism4j.pattern(Pattern.compile("(\\\\url\\{)[^}]+(?=\\})"), true));
        tokenArr[5] = Prism4j.token("headline", Prism4j.pattern(Pattern.compile("(\\\\(?:part|chapter|section|subsection|frametitle|subsubsection|paragraph|subparagraph|subsubparagraph|subsubsubparagraph)\\*?(?:\\[[^\\]]+\\])?\\{)[^}]+(?=\\}(?:\\[[^\\]]+\\])?)"), true, false, "class-name"));
        tokenArr[6] = Prism4j.token("function", Prism4j.pattern(funcPattern, false, false, "selector"));
        tokenArr[7] = Prism4j.token("punctuation", Prism4j.pattern(Pattern.compile("[\\[\\]{}&]")));
        return Prism4j.grammar("latex", tokenArr);
    }
}
