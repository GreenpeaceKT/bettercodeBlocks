package io.noties.markwon.syntax;

import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import io.noties.prism4j.Prism4j;
import io.noties.prism4j.Prism4j.Grammar;

public class Prism4jSyntaxHighlight {
    private final String fallback;
    private final Prism4j prism4j;
    private final Prism4jTheme theme;

    @NonNull
    public static Prism4jSyntaxHighlight create(@NonNull Prism4j prism4j, @NonNull Prism4jTheme theme) {
        return new Prism4jSyntaxHighlight(prism4j, theme, null);
    }

    protected Prism4jSyntaxHighlight(@NonNull Prism4j prism4j, @NonNull Prism4jTheme theme, @Nullable String fallback) {
        this.prism4j = prism4j;
        this.theme = theme;
        this.fallback = fallback;
    }

    @NonNull
    public CharSequence highlight(@Nullable String info, @NonNull String code) {
        if (code.isEmpty()) {
            return code;
        }
        CharSequence highlightNoLanguageInfo;
        if (info == null) {
            highlightNoLanguageInfo = highlightNoLanguageInfo(code);
        } else {
            highlightNoLanguageInfo = highlightWithLanguageInfo(info, code);
        }
        return highlightNoLanguageInfo;
    }

    /* Access modifiers changed, original: protected */
    @NonNull
    public CharSequence highlightNoLanguageInfo(@NonNull String code) {
        return code;
    }

    /* Access modifiers changed, original: protected */
    @NonNull
    public CharSequence highlightWithLanguageInfo(@NonNull String info, @NonNull String code) {
        String _language = info;
        Grammar _grammar = this.prism4j.grammar(info);
        if (_grammar == null && !TextUtils.isEmpty(this.fallback)) {
            _language = this.fallback;
            _grammar = this.prism4j.grammar(this.fallback);
        }
        String language = _language;
        Grammar grammar = _grammar;
        if (grammar != null) {
            return highlight(language, grammar, code);
        }
        return code;
    }

    /* Access modifiers changed, original: protected */
    @NonNull
    public CharSequence highlight(@NonNull String language, @NonNull Grammar grammar, @NonNull String code) {
        SpannableStringBuilder builder = new SpannableStringBuilder();
        new Prism4jSyntaxVisitor(language, this.theme, builder).visit(this.prism4j.tokenize(code, grammar));
        return builder;
    }
}
