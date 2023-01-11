package io.noties.prism4j.languages;

import static java.util.regex.Pattern.MULTILINE;
import static io.noties.prism4j.Prism4j.Grammar;
import static io.noties.prism4j.Prism4j.Token;
import java.util.regex.Pattern;

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
