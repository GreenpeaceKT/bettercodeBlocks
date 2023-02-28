package io.noties.prism4j;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import io.noties.prism4j.Prism4j.Grammar;
import io.noties.prism4j.Prism4j.Pattern;

public class PatternImpl implements Pattern {
    private final String alias;
    private final boolean greedy;
    private final Grammar inside;
    private final boolean lookbehind;
    private final java.util.regex.Pattern regex;

    public PatternImpl(@NonNull java.util.regex.Pattern regex, boolean lookbehind, boolean greedy, @Nullable String alias, @Nullable Grammar inside) {
        this.regex = regex;
        this.lookbehind = lookbehind;
        this.greedy = greedy;
        this.alias = alias;
        this.inside = inside;
    }

    @NonNull
    public java.util.regex.Pattern regex() {
        return this.regex;
    }

    public boolean lookbehind() {
        return this.lookbehind;
    }

    public boolean greedy() {
        return this.greedy;
    }

    @Nullable
    public String alias() {
        return this.alias;
    }

    @Nullable
    public Grammar inside() {
        return this.inside;
    }

    public String toString() {
        return ToString.toString((Pattern) this);
    }
}
