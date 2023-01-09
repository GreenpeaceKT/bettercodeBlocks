package io.noties.prism4j.languages;

import androidx.annotation.NonNull;
import io.noties.prism4j.Prism4j;
import io.noties.prism4j.Prism4j.Grammar;
import io.noties.prism4j.Prism4j.Token;
import java.util.regex.Pattern;

public class Prism_diff {
    @NonNull
    public static Grammar create(@NonNull Prism4j prism4j) {
        Token[] tokenArr = new Token[7];
        tokenArr[0] = Prism4j.token("comment", Prism4j.pattern(Pattern.compile("^#.*", 8)));
        tokenArr[1] = Prism4j.token("deleted", Prism4j.pattern(Pattern.compile("^[-–].*", 8)));
        tokenArr[2] = Prism4j.token("inserted", Prism4j.pattern(Pattern.compile("^\\+.*", 8)));
        tokenArr[3] = Prism4j.token("string", Prism4j.pattern(Pattern.compile("(\"|')(?:\\\\.|(?!\\1)[^\\\\\\r\\n])*\\1", 8)));
        Prism4j.Pattern[] patternArr = new Prism4j.Pattern[1];
        Pattern compile = Pattern.compile("^.*\\$ git .*$", 8);
        Token[] tokenArr = new Token[7];
        Token[] tokenArr2 = new Token[1];
        tokenArr2[0] = Prism4j.token("parameter", Prism4j.pattern(Pattern.compile("\\s--?\\w+", 8)));
        patternArr[0] = Prism4j.pattern(compile, false, false, null, Prism4j.grammar("inside", tokenArr2));
        tokenArr[4] = Prism4j.token("command", patternArr);
        tokenArr[5] = Prism4j.token("coord", Prism4j.pattern(Pattern.compile("^@@.*@@$", 8)));
        tokenArr[6] = Prism4j.token("commit_sha1", Prism4j.pattern(Pattern.compile("^commit \\w{40}$", 8)));
        return Prism4j.grammar("diff", tokenArr);
    }
}
