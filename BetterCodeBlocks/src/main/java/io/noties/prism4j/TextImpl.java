package io.noties.prism4j;

import androidx.annotation.NonNull;
import io.noties.prism4j.Prism4j.Text;

public class TextImpl implements Text {
    private final String literal;

    public TextImpl(@NonNull String literal) {
        this.literal = literal;
    }

    public int textLength() {
        return this.literal.length();
    }

    public final boolean isSyntax() {
        return false;
    }

    @NonNull
    public String literal() {
        return this.literal;
    }

    @NonNull
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("TextImpl{literal='");
        stringBuilder.append(this.literal);
        stringBuilder.append('\'');
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}
