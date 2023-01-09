package io.noties.prism4j.languages;

import androidx.annotation.NonNull;
import io.noties.prism4j.Prism4j;
import io.noties.prism4j.Prism4j.Grammar;
import io.noties.prism4j.Prism4j.Token;
import java.util.regex.Pattern;

public class Prism_makefile {
    @NonNull
    public static Grammar create(@NonNull Prism4j prism4j) {
        r1 = new Token[8];
        r1[0] = Prism4j.token("comment", Prism4j.pattern(Pattern.compile("(^|[^\\\\])#(?:\\\\(?:\\r\\n|[\\s\\S])|[^\\\\\\r\\n])*"), true));
        r1[1] = Prism4j.token("string", Prism4j.pattern(Pattern.compile("([\"'])(?:\\\\(?:\\r\\n|[\\s\\S])|(?!\\1)[^\\\\\\r\\n])*\\1"), false, true));
        r1[2] = Prism4j.token("builtin", Prism4j.pattern(Pattern.compile("\\.[A-Z][^:#=\\s]+(?=\\s*:(?!=))")));
        Prism4j.Pattern[] patternArr = new Prism4j.Pattern[1];
        Pattern compile = Pattern.compile("^[^:=\\r\\n]+(?=\\s*:(?!=))", 8);
        Token[] tokenArr = new Token[1];
        String str = "variable";
        tokenArr[0] = Prism4j.token(str, Prism4j.pattern(Pattern.compile("\\$+(?:[^(){}:#=\\s]+|(?=[({]))")));
        patternArr[0] = Prism4j.pattern(compile, false, false, null, Prism4j.grammar("inside", tokenArr));
        r1[3] = Prism4j.token("symbol", patternArr);
        r1[4] = Prism4j.token(str, Prism4j.pattern(Pattern.compile("\\$+(?:[^(){}:#=\\s]+|\\([@*%<^+?][DF]\\)|(?=[({]))")));
        r1[5] = Prism4j.token("keyword", Prism4j.pattern(Pattern.compile("-include\\b|\\b(?:define|else|endef|endif|export|ifn?def|ifn?eq|include|override|private|sinclude|undefine|unexport|vpath)\\b")), Prism4j.pattern(Pattern.compile("(\\()(?:addsuffix|abspath|and|basename|call|dir|error|eval|file|filter(?:-out)?|findstring|firstword|flavor|foreach|guile|if|info|join|lastword|load|notdir|or|origin|patsubst|realpath|shell|sort|strip|subst|suffix|value|warning|wildcard|word(?:s|list)?)(?=[ \\t])"), true));
        r1[6] = Prism4j.token("operator", Prism4j.pattern(Pattern.compile("(?:::|[?:+!])?=|[|@]")));
        r1[7] = Prism4j.token("punctuation", Prism4j.pattern(Pattern.compile("[:;(){}]")));
        return Prism4j.grammar("makefile", r1);
    }
}
