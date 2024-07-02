package io.noties.prism4j.languages;

import static java.util.regex.Pattern.MULTILINE;
import static java.util.regex.Pattern.compile;
import static io.noties.prism4j.Prism4j.grammar;
import static io.noties.prism4j.Prism4j.pattern;
import static io.noties.prism4j.Prism4j.token;

import androidx.annotation.NonNull;

import io.noties.prism4j.Prism4j;

@SuppressWarnings("unused")
public class Prism_ansi {
    @NonNull
    public static Prism4j.Grammar create(@NonNull Prism4j prism4j) {
        return grammar("ansi",
        token("30-m", pattern(compile("\u001B\\[0;30.*?\u001B\\[0;|\\u001B\\[0;30.*", MULTILINE))),
        token("31-m", pattern(compile("\u001B\\[0;31.*?\u001B\\[0;|\\u001B\\[0;31.*", MULTILINE))),
        token("32-m", pattern(compile("\u001B\\[0;32.*?\u001B\\[0;|\\u001B\\[0;32.*", MULTILINE))),
        token("33-m", pattern(compile("\u001B\\[0;33.*?\u001B\\[0;|\\u001B\\[0;33.*", MULTILINE))),
        token("34-m", pattern(compile("\u001B\\[0;34.*?\u001B\\[0;|\\u001B\\[0;34.*", MULTILINE))),
        token("35-m", pattern(compile("\u001B\\[0;35.*?\u001B\\[0;|\\u001B\\[0;35.*", MULTILINE))),
        token("36-m", pattern(compile("\u001B\\[0;36.*?\u001B\\[0;|\\u001B\\[0;36.*", MULTILINE))),
        token("37-m", pattern(compile("\u001B\\[0;37.*?\u001B\\[0;|\\u001B\\[0;37.*", MULTILINE))),
        token("38-m", pattern(compile("\u001B\\[0;38.*?\u001B\\[0;|\\u001B\\[0;38.*", MULTILINE)))
     );
    }
}
