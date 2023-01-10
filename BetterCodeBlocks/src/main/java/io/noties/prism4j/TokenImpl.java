package io.noties.prism4j;

import androidx.annotation.NonNull;
import io.noties.prism4j.Prism4j.Pattern;
import io.noties.prism4j.Prism4j.Token;
import java.util.List;

public class TokenImpl implements Token {
    private final String name;
    private final List<Pattern> patterns;

    public TokenImpl(@NonNull String name, @NonNull List<Pattern> patterns) {
        this.name = name;
        this.patterns = patterns;
    }

    @NonNull
    public String name() {
        return this.name;
    }

    @NonNull
    public List<Pattern> patterns() {
        return this.patterns;
    }

    @NonNull
    public String toString() {
        return ToString.toString((Token) this);
    }
}
