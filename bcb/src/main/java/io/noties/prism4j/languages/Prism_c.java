package io.noties.prism4j.languages;

import androidx.annotation.NonNull;
import io.noties.prism4j.GrammarUtils;
import io.noties.prism4j.GrammarUtils.TokenFilter;
import io.noties.prism4j.Prism4j;
import io.noties.prism4j.Prism4j.Grammar;
import io.noties.prism4j.Prism4j.Token;
import java.util.regex.Pattern;

public class Prism_c {
    @NonNull
    public static Grammar create(@NonNull Prism4j prism4j) {
        Grammar c = GrammarUtils.require(prism4j, "clike");
        AnonymousClass1 anonymousClass1 = new TokenFilter() {
            public boolean test(@NonNull Token token) {
                String name = token.name();
                return ("class-name".equals(name) || "boolean".equals(name)) ? false : true;
            }
        };
        Token[] tokenArr = new Token[3];
        tokenArr[0] = Prism4j.token(r5, Prism4j.pattern(Pattern.compile("\\b(?:_Alignas|_Alignof|_Atomic|_Bool|_Complex|_Generic|_Imaginary|_Noreturn|_Static_assert|_Thread_local|asm|typeof|inline|auto|break|case|char|const|continue|default|do|double|else|enum|extern|float|for|goto|if|int|long|register|return|short|signed|sizeof|static|struct|switch|typedef|union|unsigned|void|volatile|while)\\b")));
        tokenArr[1] = Prism4j.token("operator", Prism4j.pattern(Pattern.compile("-[>-]?|\\+\\+?|!=?|<<?=?|>>?=?|==?|&&?|\\|\\|?|[~^%?*\\/]")));
        tokenArr[2] = Prism4j.token("number", Prism4j.pattern(Pattern.compile("(?:\\b0x[\\da-f]+|(?:\\b\\d+\\.?\\d*|\\B\\.\\d+)(?:e[+-]?\\d+)?)[ful]*", 2)));
        c = GrammarUtils.extend(c, "c", anonymousClass1, tokenArr);
        Token[] tokenArr2 = new Token[2];
        Prism4j.Pattern[] patternArr = new Prism4j.Pattern[1];
        Pattern compile = Pattern.compile("(^\\s*)#\\s*[a-z]+(?:[^\\r\\n\\\\]|\\\\(?:\\r\\n|[\\s\\S]))*", 10);
        r7 = new Token[2];
        String str = "string";
        r7[0] = Prism4j.token(str, Prism4j.pattern(Pattern.compile("(#\\s*include\\s*)(?:<.+?>|(\"|')(?:\\\\?.)+?\\2)"), true));
        r7[1] = Prism4j.token("directive", Prism4j.pattern(Pattern.compile("(#\\s*)\\b(?:define|defined|elif|else|endif|error|ifdef|ifndef|if|import|include|line|pragma|undef|using)\\b"), true, false, "keyword"));
        patternArr[0] = Prism4j.pattern(compile, true, false, "property", Prism4j.grammar("inside", r7));
        tokenArr2[0] = Prism4j.token("macro", patternArr);
        tokenArr2[1] = Prism4j.token("constant", Prism4j.pattern(Pattern.compile("\\b(?:__FILE__|__LINE__|__DATE__|__TIME__|__TIMESTAMP__|__func__|EOF|NULL|SEEK_CUR|SEEK_END|SEEK_SET|stdin|stdout|stderr)\\b")));
        GrammarUtils.insertBeforeToken(c, str, tokenArr2);
        return c;
    }
}
