package io.github.greenpeacekt.acplugins.bettercodeblocks;

import android.text.SpannableStringBuilder;
import android.text.style.RelativeSizeSpan;
import android.text.style.TypefaceSpan;
import com.discord.simpleast.core.node.Node;
import com.discord.utilities.textprocessing.node.BasicRenderContext;

import java.util.*;
import java.util.regex.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class BCBNode<RC extends BasicRenderContext> extends Node<RC> {
    private final CharSequence content;
    private final String lang;

    public BCBNode(String lang, CharSequence content) {
        this.lang = lang;
        this.content = content;
    }

    public final void render(SpannableStringBuilder builder, RC rc) {
        if (builder != null) {
            Util.ensureEndsWithNewline(builder);
            int a = builder.length();
            if (this.lang != null) {
                LangNode.renderLang(builder, rc.getContext(), this.lang, a);
            }
            StringBuffer sb = new StringBuffer();
            Pattern pattern = Pattern.compile("\\[\\d+;\\d+m");
            Matcher matcher = pattern.matcher(this.content);
            if (this.lang == "ansi" ) {
                while(matcher.find()){
                    sb.setLength(0);
                    String matched = matcher.group(1);
                    String replaced = matched.replaceAll("\\[\\d+;\\d+m", "");
                    matcher.appendReplacement(sb, replaced);

                }
                sb.setLength(0);
                matcher.appendTail(sb);
                //builder.append(sb.toString());
            } else {
                builder.append(this.content);
            }
            int b = builder.length();
            builder.setSpan(new TypefaceSpan("monospace"), a, b, 33);
            builder.setSpan(new RelativeSizeSpan(0.85f), a, b, 33);
        }
    }
}
