package io.github.greenpeacekt.acplugins.bettercodeblocks;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.style.RelativeSizeSpan;
import com.aliucord.Main;
import com.aliucord.annotations.AliucordPlugin;
import com.aliucord.entities.Plugin;
import com.aliucord.patcher.PreHook;
import com.aliucord.utils.MDUtils;
import com.aliucord.api.SettingsAPI;
import com.aliucord.fragments.SettingsPage;
import com.discord.simpleast.core.node.Node;
import com.discord.simpleast.core.node.Node.a;
import com.discord.utilities.textprocessing.node.BasicRenderContext;

import java.util.regex.Matcher;
import io.github.greenpeacekt.acplugins.BetterCodeBlocks;
import io.github.greenpeacekt.acplugins.bettercodeblocks.*;

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
        if (BetterCodeBlocks.Devop()) {
            builder.append(lang).append("\n");
            builder.setSpan(new RelativeSizeSpan(0.85f), a, builder.length(), 33);
            Utils.fixColor(builder, ctx, a);
        }
    }
}
