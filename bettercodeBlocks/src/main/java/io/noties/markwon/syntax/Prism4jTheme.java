package io.noties.markwon.syntax;

import android.text.SpannableStringBuilder;
import androidx.annotation.NonNull;
import io.noties.prism4j.Prism4j.Syntax;

public interface Prism4jTheme {
    void apply(@NonNull String str, @NonNull Syntax syntax, @NonNull SpannableStringBuilder spannableStringBuilder, int i, int i2);
}
