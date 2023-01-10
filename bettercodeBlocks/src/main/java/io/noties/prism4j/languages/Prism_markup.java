package io.noties.prism4j.languages;

import androidx.annotation.NonNull;
import io.noties.prism4j.Prism4j;
import io.noties.prism4j.Prism4j.Grammar;
import io.noties.prism4j.Prism4j.Token;
import java.util.regex.Pattern;

public abstract class Prism_markup {
    @NonNull
    public static Grammar create(@NonNull Prism4j prism4j) {
        Token entity = Prism4j.token("entity", Prism4j.pattern(Pattern.compile("&#?[\\da-z]{1,8};", 2)));
        r2 = new Token[6];
        r2[0] = Prism4j.token("comment", Prism4j.pattern(Pattern.compile("<!--[\\s\\S]*?-->")));
        r2[1] = Prism4j.token("prolog", Prism4j.pattern(Pattern.compile("<\\?[\\s\\S]+?\\?>")));
        r2[2] = Prism4j.token("doctype", Prism4j.pattern(Pattern.compile("<!DOCTYPE[\\s\\S]+?>", 2)));
        r2[3] = Prism4j.token("cdata", Prism4j.pattern(Pattern.compile("<!\\[CDATA\\[[\\s\\S]*?]]>", 2)));
        Prism4j.Pattern[] patternArr = new Prism4j.Pattern[1];
        Pattern compile = Pattern.compile("<\\/?(?!\\d)[^\\s>\\/=$<%]+(?:\\s+[^\\s>\\/=]+(?:=(?:(\"|')(?:\\\\[\\s\\S]|(?!\\1)[^\\\\])*\\1|[^\\s'\">=]+))?)*\\s*\\/?>", 2);
        Token[] tokenArr = new Token[4];
        Prism4j.Pattern[] patternArr2 = new Prism4j.Pattern[1];
        Pattern compile2 = Pattern.compile("^<\\/?[^\\s>\\/]+", 2);
        r12 = new Token[2];
        String str = "punctuation";
        r12[0] = Prism4j.token(str, Prism4j.pattern(Pattern.compile("^<\\/?")));
        Prism4j.Pattern[] patternArr3 = new Prism4j.Pattern[1];
        patternArr3[0] = Prism4j.pattern(Pattern.compile("^[^\\s>\\/:]+:"));
        String str2 = "namespace";
        r12[1] = Prism4j.token(str2, patternArr3);
        String str3 = "inside";
        patternArr2[0] = Prism4j.pattern(compile2, false, false, null, Prism4j.grammar(str3, r12));
        String str4 = "tag";
        tokenArr[0] = Prism4j.token(str4, patternArr2);
        patternArr2 = new Prism4j.Pattern[1];
        Pattern compile3 = Pattern.compile("=(?:(\"|')(?:\\\\[\\s\\S]|(?!\\1)[^\\\\])*\\1|[^\\s'\">=]+)", 2);
        r6 = new Token[2];
        r6[0] = Prism4j.token(str, Prism4j.pattern(Pattern.compile("^=")), Prism4j.pattern(Pattern.compile("(^|[^\\\\])[\"']"), true));
        r6[1] = entity;
        patternArr2[0] = Prism4j.pattern(compile3, false, false, null, Prism4j.grammar(str3, r6));
        tokenArr[1] = Prism4j.token("attr-value", patternArr2);
        tokenArr[2] = Prism4j.token(str, Prism4j.pattern(Pattern.compile("\\/?>")));
        Prism4j.Pattern[] patternArr4 = new Prism4j.Pattern[1];
        Pattern compile4 = Pattern.compile("[^\\s>\\/]+");
        r6 = new Token[1];
        r6[0] = Prism4j.token(str2, Prism4j.pattern(Pattern.compile("^[^\\s>\\/:]+:")));
        patternArr4[0] = Prism4j.pattern(compile4, false, false, null, Prism4j.grammar(str3, r6));
        tokenArr[3] = Prism4j.token("attr-name", patternArr4);
        patternArr[0] = Prism4j.pattern(compile, false, true, null, Prism4j.grammar(str3, tokenArr));
        r2[4] = Prism4j.token(str4, patternArr);
        r2[5] = entity;
        return Prism4j.grammar("markup", r2);
    }

    private Prism_markup() {
    }
}
