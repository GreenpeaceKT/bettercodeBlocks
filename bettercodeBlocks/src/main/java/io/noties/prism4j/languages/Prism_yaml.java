package io.noties.prism4j.languages;

import androidx.annotation.NonNull;
import io.noties.prism4j.Prism4j;
import io.noties.prism4j.Prism4j.Grammar;
import io.noties.prism4j.Prism4j.Pattern;
import io.noties.prism4j.Prism4j.Token;

public class Prism_yaml {
    @NonNull
    public static Grammar create(@NonNull Prism4j prism4j) {
        Token[] tokenArr = new Token[12];
        Pattern[] patternArr = new Pattern[1];
        String str = "string";
        patternArr[0] = Prism4j.pattern(java.util.regex.Pattern.compile("([\\-:]\\s*(?:![^\\s]+)?[ \\t]*[|>])[ \\t]*(?:((?:\\r?\\n|\\r)[ \\t]+)[^\\r\\n]+(?:\\2[^\\r\\n]+)*)"), true, false, str);
        tokenArr[0] = Prism4j.token("scalar", patternArr);
        tokenArr[1] = Prism4j.token("comment", Prism4j.pattern(java.util.regex.Pattern.compile("#.*")));
        tokenArr[2] = Prism4j.token("key", Prism4j.pattern(java.util.regex.Pattern.compile("(\\s*(?:^|[:\\-,\\[{\\r\\n?])[ \\t]*(?:![^\\s]+)?[ \\t]*)[^\\r\\n{\\[\\]},#\\s]+?(?=\\s*:\\s)"), true, false, "atrule"));
        patternArr = new Pattern[1];
        String str2 = "important";
        patternArr[0] = Prism4j.pattern(java.util.regex.Pattern.compile("(^[ \\t]*)%.+", 8), true, false, str2);
        tokenArr[3] = Prism4j.token("directive", patternArr);
        patternArr = new Pattern[1];
        String str3 = "number";
        patternArr[0] = Prism4j.pattern(java.util.regex.Pattern.compile("([:\\-,\\[{]\\s*(?:![^\\s]+)?[ \\t]*)(?:\\d{4}-\\d\\d?-\\d\\d?(?:[tT]|[ \\t]+)\\d\\d?:\\d{2}:\\d{2}(?:\\.\\d*)?[ \\t]*(?:Z|[-+]\\d\\d?(?::\\d{2})?)?|\\d{4}-\\d{2}-\\d{2}|\\d\\d?:\\d{2}(?::\\d{2}(?:\\.\\d*)?)?)(?=[ \\t]*(?:$|,|]|\\}))", 8), true, false, str3);
        tokenArr[4] = Prism4j.token("datetime", patternArr);
        tokenArr[5] = Prism4j.token("boolean", Prism4j.pattern(java.util.regex.Pattern.compile("([:\\-,\\[{]\\s*(?:![^\\s]+)?[ \\t]*)(?:true|false)[ \\t]*(?=$|,|]|\\})", 10), true, false, str2));
        tokenArr[6] = Prism4j.token("null", Prism4j.pattern(java.util.regex.Pattern.compile("([:\\-,\\[{]\\s*(?:![^\\s]+)?[ \\t]*)(?:null|~)[ \\t]*(?=$|,|]|\\})", 10), true, false, str2));
        tokenArr[7] = Prism4j.token(str, Prism4j.pattern(java.util.regex.Pattern.compile("([:\\-,\\[{]\\s*(?:![^\\s]+)?[ \\t]*)(\"|')(?:(?!\\2)[^\\\\\\r\\n]|\\\\.)*\\2(?=[ \\t]*(?:$|,|]|\\}))", 8), true, true));
        tokenArr[8] = Prism4j.token(str3, Prism4j.pattern(java.util.regex.Pattern.compile("([:\\-,\\[{]\\s*(?:![^\\s]+)?[ \\t]*)[+-]?(?:0x[\\da-f]+|0o[0-7]+|(?:\\d+\\.?\\d*|\\.?\\d+)(?:e[+-]?\\d+)?|\\.inf|\\.nan)[ \\t]*(?=$|,|]|\\})", 10), true));
        tokenArr[9] = Prism4j.token("tag", Prism4j.pattern(java.util.regex.Pattern.compile("![^\\s]+")));
        tokenArr[10] = Prism4j.token(str2, Prism4j.pattern(java.util.regex.Pattern.compile("[&*][\\w]+")));
        tokenArr[11] = Prism4j.token("punctuation", Prism4j.pattern(java.util.regex.Pattern.compile("---|[:\\[\\]{}\\-,|>?]|\\.\\.\\.")));
        return Prism4j.grammar("yaml", tokenArr);
    }
}
