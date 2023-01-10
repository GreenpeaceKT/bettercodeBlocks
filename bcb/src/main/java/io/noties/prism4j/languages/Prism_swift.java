package io.noties.prism4j.languages;

import androidx.annotation.NonNull;
import io.noties.prism4j.GrammarUtils;
import io.noties.prism4j.Prism4j;
import io.noties.prism4j.Prism4j.Grammar;
import io.noties.prism4j.Prism4j.Pattern;
import io.noties.prism4j.Prism4j.Token;
import java.util.List;

public class Prism_swift {
    @NonNull
    public static Grammar create(@NonNull Prism4j prism4j) {
        Grammar swift = GrammarUtils.require(prism4j, "clike");
        Token[] tokenArr = new Token[3];
        Pattern[] patternArr = new Pattern[1];
        java.util.regex.Pattern compile = java.util.regex.Pattern.compile("(\"|')(\\\\(?:\\((?:[^()]|\\([^)]+\\))+\\)|\\r\\n|[\\s\\S])|(?!\\1)[^\\\\\\r\\n])*\\1");
        Token[] tokenArr2 = new Token[1];
        Pattern[] patternArr2 = new Pattern[1];
        java.util.regex.Pattern compile2 = java.util.regex.Pattern.compile("\\\\\\((?:[^()]|\\([^)]+\\))+\\)");
        Token[] tokenArr3 = new Token[1];
        tokenArr3[0] = Prism4j.token("delimiter", Prism4j.pattern(java.util.regex.Pattern.compile("^\\\\\\(|\\)$"), false, false, "variable"));
        String str = "inside";
        String str2 = null;
        patternArr2[0] = Prism4j.pattern(compile2, false, false, null, Prism4j.grammar(str, tokenArr3));
        tokenArr2[0] = Prism4j.token("interpolation", patternArr2);
        patternArr[0] = Prism4j.pattern(compile, false, true, null, Prism4j.grammar(str, tokenArr2));
        tokenArr[0] = Prism4j.token("string", patternArr);
        tokenArr[1] = Prism4j.token("keyword", Prism4j.pattern(java.util.regex.Pattern.compile("\\b(?:as|associativity|break|case|catch|class|continue|convenience|default|defer|deinit|didSet|do|dynamic(?:Type)?|else|enum|extension|fallthrough|final|for|func|get|guard|if|import|in|infix|init|inout|internal|is|lazy|left|let|mutating|new|none|nonmutating|operator|optional|override|postfix|precedence|prefix|private|protocol|public|repeat|required|rethrows|return|right|safe|self|Self|set|static|struct|subscript|super|switch|throws?|try|Type|typealias|unowned|unsafe|var|weak|where|while|willSet|__(?:COLUMN__|FILE__|FUNCTION__|LINE__))\\b")));
        tokenArr[2] = Prism4j.token("number", Prism4j.pattern(java.util.regex.Pattern.compile("\\b(?:[\\d_]+(?:\\.[\\de_]+)?|0x[a-f0-9_]+(?:\\.[a-f0-9p_]+)?|0b[01_]+|0o[0-7_]+)\\b", 2)));
        swift = GrammarUtils.extend(swift, "swift", tokenArr);
        List<Token> tokens = swift.tokens();
        tokens.add(Prism4j.token("constant", Prism4j.pattern(java.util.regex.Pattern.compile("\\b(?:nil|[A-Z_]{2,}|k[A-Z][A-Za-z_]+)\\b"))));
        tokens.add(Prism4j.token("atrule", Prism4j.pattern(java.util.regex.Pattern.compile("@\\b(?:IB(?:Outlet|Designable|Action|Inspectable)|class_protocol|exported|noreturn|NS(?:Copying|Managed)|objc|UIApplicationMain|auto_closure)\\b"))));
        tokens.add(Prism4j.token("builtin", Prism4j.pattern(java.util.regex.Pattern.compile("\\b(?:[A-Z]\\S+|abs|advance|alignof(?:Value)?|assert|contains|count(?:Elements)?|debugPrint(?:ln)?|distance|drop(?:First|Last)|dump|enumerate|equal|filter|find|first|getVaList|indices|isEmpty|join|last|lexicographicalCompare|map|max(?:Element)?|min(?:Element)?|numericCast|overlaps|partition|print(?:ln)?|reduce|reflect|reverse|sizeof(?:Value)?|sort(?:ed)?|split|startsWith|stride(?:of(?:Value)?)?|suffix|swap|toDebugString|toString|transcode|underestimateCount|unsafeBitCast|with(?:ExtendedLifetime|Unsafe(?:MutablePointers?|Pointers?)|VaList))\\b"))));
        Token interpolationToken = GrammarUtils.findToken(swift, "string/interpolation");
        if (interpolationToken != null) {
            str2 = GrammarUtils.findFirstInsideGrammar(interpolationToken);
        }
        String interpolationGrammar = str2;
        if (interpolationGrammar != null) {
            interpolationGrammar.tokens().addAll(swift.tokens());
        }
        return swift;
    }
}
