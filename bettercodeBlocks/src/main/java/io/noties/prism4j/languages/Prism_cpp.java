package io.noties.prism4j.languages;

import androidx.annotation.NonNull;
import io.noties.prism4j.GrammarUtils;
import io.noties.prism4j.Prism4j;
import io.noties.prism4j.Prism4j.Grammar;
import io.noties.prism4j.Prism4j.Token;
import java.util.regex.Pattern;

public class Prism_cpp {
    @NonNull
    public static Grammar create(@NonNull Prism4j prism4j) {
        Grammar cpp = GrammarUtils.require(prism4j, "c");
        r2 = new Token[2];
        String str = "keyword";
        r2[0] = Prism4j.token(str, Prism4j.pattern(Pattern.compile("\\b(?:alignas|alignof|asm|auto|bool|break|case|catch|char|char16_t|char32_t|class|compl|const|constexpr|const_cast|continue|decltype|default|delete|do|double|dynamic_cast|else|enum|explicit|export|extern|float|for|friend|goto|if|inline|int|int8_t|int16_t|int32_t|int64_t|uint8_t|uint16_t|uint32_t|uint64_t|long|mutable|namespace|new|noexcept|nullptr|operator|private|protected|public|register|reinterpret_cast|return|short|signed|sizeof|static|static_assert|static_cast|struct|switch|template|this|thread_local|throw|try|typedef|typeid|typename|union|unsigned|using|virtual|void|volatile|wchar_t|while)\\b")));
        r2[1] = Prism4j.token("operator", Prism4j.pattern(Pattern.compile("--?|\\+\\+?|!=?|<{1,2}=?|>{1,2}=?|->|:{1,2}|={1,2}|\\^|~|%|&{1,2}|\\|\\|?|\\?|\\*|\\/|\\b(?:and|and_eq|bitand|bitor|not|not_eq|or|or_eq|xor|xor_eq)\\b")));
        cpp = GrammarUtils.extend(cpp, "cpp", r2);
        r2 = new Token[1];
        r2[0] = Prism4j.token("boolean", Prism4j.pattern(Pattern.compile("\\b(?:true|false)\\b")));
        GrammarUtils.insertBeforeToken(cpp, "function", r2);
        r2 = new Token[1];
        r2[0] = Prism4j.token("class-name", Prism4j.pattern(Pattern.compile("(class\\s+)\\w+", 2), true));
        GrammarUtils.insertBeforeToken(cpp, str, r2);
        Token[] tokenArr = new Token[1];
        Prism4j.Pattern[] patternArr = new Prism4j.Pattern[1];
        str = "string";
        patternArr[0] = Prism4j.pattern(Pattern.compile("R\"([^()\\\\ ]{0,16})\\([\\s\\S]*?\\)\\1\""), false, true, str);
        tokenArr[0] = Prism4j.token("raw-string", patternArr);
        GrammarUtils.insertBeforeToken(cpp, str, tokenArr);
        return cpp;
    }
}
