package io.noties.prism4j.languages;

import static java.util.regex.Pattern.MULTILINE;
import static java.util.regex.Pattern.compile;
import static io.noties.prism4j.Prism4j.grammar;
import static io.noties.prism4j.Prism4j.pattern;
import static io.noties.prism4j.Prism4j.token;

import androidx.annotation.NonNull;

import io.noties.prism4j.Prism4j;

@SuppressWarnings("unused")
public class Prism_fix {
    @NonNull
    public static Grammar create(@NonNull Prism4j prism4j) {
        return grammar("fix",
        token("fixtext", pattern(compile("[\\s\\S\\]*", MULTILINE)))
     );
    }
}
