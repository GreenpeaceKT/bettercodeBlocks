package io.noties.markwon.syntax;

import android.text.SpannableStringBuilder;
import androidx.annotation.NonNull;
import io.noties.prism4j.AbsVisitor;
import io.noties.prism4j.Prism4j.Syntax;
import io.noties.prism4j.Prism4j.Text;

class Prism4jSyntaxVisitor extends AbsVisitor {
    private final SpannableStringBuilder builder;
    private final String language;
    private final Prism4jTheme theme;

    Prism4jSyntaxVisitor(@NonNull String language, @NonNull Prism4jTheme theme, @NonNull SpannableStringBuilder builder) {
        this.language = language;
        this.theme = theme;
        this.builder = builder;
    }

    /* Access modifiers changed, original: protected */
    public void visitText(@NonNull Text text) {
        this.builder.append(text.literal());
    }

    /* Access modifiers changed, original: protected */
    public void visitSyntax(@NonNull Syntax syntax) {
        int start = this.builder.length();
        visit(syntax.children());
        int end = this.builder.length();
        if (end != start) {
            this.theme.apply(this.language, syntax, this.builder, start, end);
        }
    }
}
