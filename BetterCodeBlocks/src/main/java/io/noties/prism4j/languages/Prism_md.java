package io.noties.prism4j.languages;

import static androidx.annotation.NonNull;
import static io.noties.prism4j.Prism4j;
import static io.noties.prism4j.Prism4j.Grammar;
import static io.noties.prism4j.Prism4j.Token;
import static java.util.regex.Pattern;

import androidx.annotation.NonNull;

import io.noties.prism4j.Prism4j;

@SuppressWarnings("unused")
public class Prism_md {
    @NonNull
    public static Grammar create(@NonNull Prism4j prism4j) {
      return grammar("md",
      token("md-sharp", pattern(compile("^\\#.*", MULTILINE)))
      );
    }
}
