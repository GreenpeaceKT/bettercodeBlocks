package io.github.greenpeacekt.acplugins.bettercodeblocks;

import android.text.SpannableStringBuilder;
import android.text.style.RelativeSizeSpan;
import android.text.style.TypefaceSpan;
import com.discord.simpleast.core.node.Node;
import com.discord.utilities.textprocessing.node.BasicRenderContext;

public final class DevBCBNode<RC extends BasicRenderContext> extends Node<RC> {
    private final CharSequence content;
    private final String lang;

    public DevBCBNode(String lang, CharSequence content) {
        this.lang = lang;
        this.content = content;
    }

    public final void render(SpannableStringBuilder builder, RC rc) {
        if (builder != null) {
            Util.ensureEndsWithNewline(builder);
            int a = builder.length();
            if (this.lang != null) {
                LangNode.devrenderLang(builder, rc.getContext(), this.lang, a);
            }
            
            builder.append(this.content);
            int b = builder.length();
            builder.setSpan(new TypefaceSpan("monospace"), a, b, 33);
            builder.setSpan(new RelativeSizeSpan(0.85f), a, b, 33);
        }
    }
}
