package io.noties.prism4j;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import io.noties.prism4j.Prism4j.Node;
import io.noties.prism4j.Prism4j.Syntax;
import java.util.List;

public class SyntaxImpl implements Syntax {
    private final String alias;
    private final List<? extends Node> children;
    private final boolean greedy;
    private final String matchedString;
    private final boolean tokenized;
    private final String type;

    public SyntaxImpl(@NonNull String type, @NonNull List<? extends Node> children, @Nullable String alias, @NonNull String matchedString, boolean greedy, boolean tokenized) {
        this.type = type;
        this.children = children;
        this.alias = alias;
        this.matchedString = matchedString;
        this.greedy = greedy;
        this.tokenized = tokenized;
    }

    public int textLength() {
        return this.matchedString.length();
    }

    public final boolean isSyntax() {
        return true;
    }

    @NonNull
    public String type() {
        return this.type;
    }

    @NonNull
    public List<? extends Node> children() {
        return this.children;
    }

    @Nullable
    public String alias() {
        return this.alias;
    }

    @NonNull
    public String matchedString() {
        return this.matchedString;
    }

    public boolean greedy() {
        return this.greedy;
    }

    public boolean tokenized() {
        return this.tokenized;
    }

    @NonNull
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SyntaxImpl{type='");
        stringBuilder.append(this.type);
        stringBuilder.append('\'');
        stringBuilder.append(", children=");
        stringBuilder.append(this.children);
        stringBuilder.append(", alias='");
        stringBuilder.append(this.alias);
        stringBuilder.append('\'');
        stringBuilder.append(", matchedString='");
        stringBuilder.append(this.matchedString);
        stringBuilder.append('\'');
        stringBuilder.append(", greedy=");
        stringBuilder.append(this.greedy);
        stringBuilder.append(", tokenized=");
        stringBuilder.append(this.tokenized);
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}
