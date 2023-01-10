package io.noties.prism4j.languages;

import androidx.annotation.NonNull;
import io.noties.prism4j.Prism4j;
import io.noties.prism4j.Prism4j.Grammar;
import io.noties.prism4j.Prism4j.Token;
import java.util.regex.Pattern;

public class Prism_python {
    @NonNull
    public static Grammar create(@NonNull Prism4j prism4j) {
        Token[] tokenArr = new Token[11];
        tokenArr[0] = Prism4j.token("comment", Prism4j.pattern(Pattern.compile("(^|[^\\\\])#.*"), true));
        Prism4j.Pattern[] patternArr = new Prism4j.Pattern[1];
        String str = "string";
        patternArr[0] = Prism4j.pattern(Pattern.compile("(\"\"\"|''')[\\s\\S]+?\\1"), false, true, str);
        tokenArr[1] = Prism4j.token("triple-quoted-string", patternArr);
        tokenArr[2] = Prism4j.token(str, Prism4j.pattern(Pattern.compile("(\"|')(?:\\\\.|(?!\\1)[^\\\\\\r\\n])*\\1"), false, true));
        tokenArr[3] = Prism4j.token("function", Prism4j.pattern(Pattern.compile("((?:^|\\s)def[ \\t]+)[a-zA-Z_]\\w*(?=\\s*\\()"), true));
        tokenArr[4] = Prism4j.token("class-name", Prism4j.pattern(Pattern.compile("(\\bclass\\s+)\\w+", 2), true));
        tokenArr[5] = Prism4j.token("keyword", Prism4j.pattern(Pattern.compile("\\b(?:as|assert|async|await|break|class|continue|def|del|elif|else|except|exec|finally|for|from|global|if|import|in|is|lambda|nonlocal|pass|print|raise|return|try|while|with|yield)\\b")));
        tokenArr[6] = Prism4j.token("builtin", Prism4j.pattern(Pattern.compile("\\b(?:__import__|abs|all|any|apply|ascii|basestring|bin|bool|buffer|bytearray|bytes|callable|chr|classmethod|cmp|coerce|compile|complex|delattr|dict|dir|divmod|enumerate|eval|execfile|file|filter|float|format|frozenset|getattr|globals|hasattr|hash|help|hex|id|input|int|intern|isinstance|issubclass|iter|len|list|locals|long|map|max|memoryview|min|next|object|oct|open|ord|pow|property|range|raw_input|reduce|reload|repr|reversed|round|set|setattr|slice|sorted|staticmethod|str|sum|super|tuple|type|unichr|unicode|vars|xrange|zip)\\b")));
        tokenArr[7] = Prism4j.token("boolean", Prism4j.pattern(Pattern.compile("\\b(?:True|False|None)\\b")));
        tokenArr[8] = Prism4j.token("number", Prism4j.pattern(Pattern.compile("(?:\\b(?=\\d)|\\B(?=\\.))(?:0[bo])?(?:(?:\\d|0x[\\da-f])[\\da-f]*\\.?\\d*|\\.\\d+)(?:e[+-]?\\d+)?j?\\b", 2)));
        tokenArr[9] = Prism4j.token("operator", Prism4j.pattern(Pattern.compile("[-+%=]=?|!=|\\*\\*?=?|\\/\\/?=?|<[<=>]?|>[=>]?|[&|^~]|\\b(?:or|and|not)\\b")));
        tokenArr[10] = Prism4j.token("punctuation", Prism4j.pattern(Pattern.compile("[{}\\[\\];(),.:]")));
        return Prism4j.grammar("python", tokenArr);
    }
}
