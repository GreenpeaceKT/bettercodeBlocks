package io.noties.prism4j.languages;

import androidx.annotation.NonNull;
import io.noties.prism4j.GrammarUtils;
import io.noties.prism4j.Prism4j;
import io.noties.prism4j.Prism4j.Grammar;
import io.noties.prism4j.Prism4j.Token;
import java.util.regex.Pattern;

public class Prism_csharp {
    @NonNull
    public static Grammar create(@NonNull Prism4j prism4j) {
        Token[] classNameInsidePunctuation = new Token[1];
        String str = "punctuation";
        classNameInsidePunctuation[0] = Prism4j.token(str, Prism4j.pattern(Pattern.compile("\\.")));
        String str2 = "inside";
        Grammar classNameInsidePunctuation2 = Prism4j.grammar(str2, classNameInsidePunctuation);
        Grammar csharp = GrammarUtils.require(prism4j, "clike");
        r8 = new Token[4];
        String str3 = "keyword";
        r8[0] = Prism4j.token(str3, Prism4j.pattern(Pattern.compile("\\b(?:abstract|add|alias|as|ascending|async|await|base|bool|break|byte|case|catch|char|checked|class|const|continue|decimal|default|delegate|descending|do|double|dynamic|else|enum|event|explicit|extern|false|finally|fixed|float|for|foreach|from|get|global|goto|group|if|implicit|in|int|interface|internal|into|is|join|let|lock|long|namespace|new|null|object|operator|orderby|out|override|params|partial|private|protected|public|readonly|ref|remove|return|sbyte|sealed|select|set|short|sizeof|stackalloc|static|string|struct|switch|this|throw|true|try|typeof|uint|ulong|unchecked|unsafe|ushort|using|value|var|virtual|void|volatile|where|while|yield)\\b")));
        r8[1] = Prism4j.token("string", Prism4j.pattern(Pattern.compile("@(\"|')(?:\\1\\1|\\\\[\\s\\S]|(?!\\1)[^\\\\])*\\1"), false, true), Prism4j.pattern(Pattern.compile("(\"|')(?:\\\\.|(?!\\1)[^\\\\\\r\\n])*?\\1"), false, true));
        String str4 = "class-name";
        r8[2] = Prism4j.token(str4, Prism4j.pattern(Pattern.compile("\\b[A-Z]\\w*(?:\\.\\w+)*\\b(?=\\s+\\w+)"), false, false, null, classNameInsidePunctuation2), Prism4j.pattern(Pattern.compile("(\\[)[A-Z]\\w*(?:\\.\\w+)*\\b"), true, false, null, classNameInsidePunctuation2), Prism4j.pattern(Pattern.compile("(\\b(?:class|interface)\\s+[A-Z]\\w*(?:\\.\\w+)*\\s*:\\s*)[A-Z]\\w*(?:\\.\\w+)*\\b"), true, false, null, classNameInsidePunctuation2), Prism4j.pattern(Pattern.compile("((?:\\b(?:class|interface|new)\\s+)|(?:catch\\s+\\())[A-Z]\\w*(?:\\.\\w+)*\\b"), true, false, null, classNameInsidePunctuation2));
        r8[3] = Prism4j.token("number", Prism4j.pattern(Pattern.compile("\\b0x[\\da-f]+\\b|(?:\\b\\d+\\.?\\d*|\\B\\.\\d+)f?", 2)));
        csharp = GrammarUtils.extend(csharp, "csharp", r8);
        r8 = new Token[2];
        Prism4j.Pattern[] patternArr = new Prism4j.Pattern[1];
        Pattern compile = Pattern.compile("\\w+\\s*<[^>\\r\\n]+?>\\s*(?=\\()");
        Token[] tokenArr = new Token[4];
        tokenArr[0] = Prism4j.token("function", Prism4j.pattern(Pattern.compile("^\\w+")));
        tokenArr[1] = Prism4j.token(str4, Prism4j.pattern(Pattern.compile("\\b[A-Z]\\w*(?:\\.\\w+)*\\b"), false, false, null, classNameInsidePunctuation2));
        tokenArr[2] = GrammarUtils.findToken(csharp, str3);
        tokenArr[3] = Prism4j.token(str, Prism4j.pattern(Pattern.compile("[<>(),.:]")));
        patternArr[0] = Prism4j.pattern(compile, false, false, null, Prism4j.grammar(str2, tokenArr));
        r8[0] = Prism4j.token("generic-method", patternArr);
        Prism4j.Pattern[] patternArr2 = new Prism4j.Pattern[1];
        Pattern compile2 = Pattern.compile("(^\\s*)#.*", 8);
        Token[] tokenArr2 = new Token[1];
        tokenArr2[0] = Prism4j.token("directive", Prism4j.pattern(Pattern.compile("(\\s*#)\\b(?:define|elif|else|endif|endregion|error|if|line|pragma|region|undef|warning)\\b"), true, false, str3));
        patternArr2[0] = Prism4j.pattern(compile2, true, false, "property", Prism4j.grammar(str2, tokenArr2));
        r8[1] = Prism4j.token("preprocessor", patternArr2);
        GrammarUtils.insertBeforeToken(csharp, str4, r8);
        return csharp;
    }
}
