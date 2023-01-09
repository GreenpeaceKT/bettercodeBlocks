package io.noties.prism4j;

import androidx.annotation.NonNull;
import io.noties.prism4j.Prism4j.Grammar;
import io.noties.prism4j.Prism4j.Token;
import java.util.List;

public class GrammarImpl implements Grammar {
    private final String name;
    private final List<Token> tokens;

    public GrammarImpl(@NonNull String name, @NonNull List<Token> tokens) {
        this.name = name;
        this.tokens = tokens;
    }

    @NonNull
    public String name() {
        return this.name;
    }

    @NonNull
    public List<Token> tokens() {
        return this.tokens;
    }

    @NonNull
    public String toString() {
        return ToString.toString((Grammar) this);
    }
}
