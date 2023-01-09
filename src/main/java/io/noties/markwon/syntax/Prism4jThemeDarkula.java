package io.noties.markwon.syntax;

import android.text.SpannableStringBuilder;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import io.noties.markwon.core.spans.EmphasisSpan;
import io.noties.markwon.core.spans.StrongEmphasisSpan;
import io.noties.markwon.syntax.Prism4jThemeBase.ColorHashMap;

public class Prism4jThemeDarkula extends Prism4jThemeBase {
    /* Access modifiers changed, original: protected */
    @NonNull
    public ColorHashMap init() {
        return new ColorHashMap()
        .add(-8355712, "comment", "prolog", "cdata")
        .add(-3377102, "delimiter", "boolean", "keyword", "selector", "important", "atrule")
        .add(-5654586, "operator", "punctuation", "attr-name")
        .add(-1523862, "tag", "doctype", "builtin")
        .add(-9922629, "entity", "number", "symbol")
        .add(0xFF9876AA, "property", "constant", "variable")
        .add(0xFF00BFFF, "string", "char")
        .add(-4475848, "annotation")
        .add(-5914015, "attr-value")
        .add(-14124066, "url")
        .add(-14739, "function")
        .add(-13221579, "regex")
        .add(0xFF00FF00, "inserted")
        .add(0xFFFF0000, "deleted")
        .add(0xFF0000FF,"md-sharp")
        .add(0xFF1E90FF,"fixtext","csstext");
    }

    /* Access modifiers changed, original: protected */
    public void applyColor(@NonNull String language, @NonNull String type, @Nullable String alias, int color, @NonNull SpannableStringBuilder builder, int start, int end) {
        super.applyColor(language, type, alias, color, builder, start, end);
        if (Prism4jThemeBase.isOfType("important", type, alias) || Prism4jThemeBase.isOfType("bold", type, alias)) {
            builder.setSpan(new StrongEmphasisSpan(), start, end, 33);
        }
        if (Prism4jThemeBase.isOfType("italic", type, alias)) {
            builder.setSpan(new EmphasisSpan(), start, end, 33);
        }
    }
}
