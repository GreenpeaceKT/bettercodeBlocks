package io.noties.prism4j;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import io.noties.prism4j.Prism4j.Grammar;

public interface GrammarLocator {
    @Nullable
    Grammar grammar(@NonNull Prism4j prism4j, @NonNull String str);
}
