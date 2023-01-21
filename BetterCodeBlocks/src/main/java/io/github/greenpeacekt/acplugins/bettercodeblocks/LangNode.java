package io.github.greenpeacekt.acplugins.bettercodeblocks;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.style.RelativeSizeSpan;
import com.discord.simpleast.core.node.Node;
import com.discord.simpleast.core.node.Node.a;
import com.discord.utilities.textprocessing.node.BasicRenderContext;

public final class LangNode<RC extends BasicRenderContext> extends a<RC> {
    private final String lang;

    @SafeVarargs
    public LangNode(String lang, Node<RC>... child) {
        super(child);
        this.lang = lang;
    }

    public void render(SpannableStringBuilder builder, RC rc) {
        if (!(builder == null || this.lang == null)) {
            Utils.ensureEndsWithNewline(builder);
            renderLang(builder, rc.getContext(), this.lang, builder.length());
        }
        super.render(builder, rc);
    }

    public static void renderLang(SpannableStringBuilder builder, Context ctx, String lang, int a) {
        /*builder.append(lang).append("\n");
        builder.setSpan(new RelativeSizeSpan(0.85f), a, builder.length(), 33);
        Utils.fixColor(builder, ctx, a);
    */}
}
