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
        token("30-m", pattern(compile("\\[0;30m.*?\\[|\\[0;30m.*", MULTILINE))),
        token("31-m", pattern(compile("^[-–].*", MULTILINE))),
        token("32-m", pattern(compile("\\[0;32m.*?\\[|\\[0;32m.*", MULTILINE))),
        token("33-m", pattern(compile("\\[0;33m.*?\\[|\\[0;33m.*", MULTILINE))),
        token("34-m", pattern(compile("\\[0;34m.*?\\[|\\[0;34m.*", MULTILINE))),
        token("35-m", pattern(compile("\\[0;35m.*?\\[|\\[0;35m.*", MULTILINE))),
        token("36-m", pattern(compile("\\[0;36m.*?\\[|\\[0;36m.*", MULTILINE))),
        token("37-m", pattern(compile("\\[0;37m.*?\\[|\\[0;37m.*", MULTILINE))),
        token("38-m", pattern(compile("\\[0;38m.*?\\[|\\[0;38m.*", MULTILINE)))
     );
    }
}
