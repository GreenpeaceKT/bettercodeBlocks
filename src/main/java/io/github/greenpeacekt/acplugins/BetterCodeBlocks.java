package io.github.greenpeacekt.acplugins;

import android.content.Context;
import android.text.SpannableStringBuilder;
import b.a.t.a.a;
import com.aliucord.Main;
import com.aliucord.annotations.AliucordPlugin;
import com.aliucord.entities.Plugin;
import com.aliucord.entities.Plugin.SettingsTab;
import com.aliucord.patcher.PreHook;
import com.aliucord.utils.MDUtils;
import com.aliucord.utils.MDUtils.RenderContext;
import com.discord.simpleast.code.CodeNode;
import com.discord.simpleast.core.node.Node;
import com.discord.simpleast.core.parser.ParseSpec;
import com.discord.simpleast.core.parser.Parser;
import com.discord.utilities.textprocessing.node.BasicRenderContext;
import com.discord.utilities.textprocessing.node.BlockBackgroundNode;
import de.robv.android.xposed.XC_MethodHook.MethodHookParam;
import io.github.greenpeacekt.acplugins.bettercodeblocks.BCBNode;
import io.github.greenpeacekt.acplugins.bettercodeblocks.GrammarLocatorImpl;
import io.github.greenpeacekt.acplugins.bettercodeblocks.LangNode;
import io.github.greenpeacekt.acplugins.bettercodeblocks.Settings;
import io.github.greenpeacekt.acplugins.bettercodeblocks.SimpleASTUtils;
import io.github.greenpeacekt.acplugins.bettercodeblocks.Utils;
import io.noties.markwon.syntax.Prism4jSyntaxHighlight;
import io.noties.markwon.syntax.Prism4jThemeDarkula;
import io.noties.prism4j.Prism4j;
import java.util.Map;
import java.util.regex.Matcher;

@AliucordPlugin
public final class BetterCodeBlocks extends Plugin {
    public static final GrammarLocatorImpl grammarLocator;
    public static final Prism4j prism4j;
    private Prism4jSyntaxHighlight highlight;

    static {
        GrammarLocatorImpl grammarLocatorImpl = new GrammarLocatorImpl();
        grammarLocator = grammarLocatorImpl;
        prism4j = new Prism4j(grammarLocatorImpl);
    }

    public BetterCodeBlocks() {
        this.settingsTab = new SettingsTab(Settings.class).withArgs(new Object[]{this.settings});
    }

    public void start(Context context) throws Throwable {
        this.highlight = Prism4jSyntaxHighlight.create(prism4j, new Prism4jThemeDarkula());
        this.patcher.patch(a.class, "parse", new Class[]{Matcher.class, Parser.class, Object.class}, new PreHook(new BetterCodeBlocks$$ExternalSyntheticLambda0(this)));
        this.patcher.patch(BlockBackgroundNode.class.getDeclaredConstructor(new Class[]{Boolean.TYPE, Node[].class}), new PreHook(BetterCodeBlocks$$ExternalSyntheticLambda2.INSTANCE));
        this.patcher.patch(MDUtils.class.getDeclaredMethod("renderCodeBlock", new Class[]{Context.class, SpannableStringBuilder.class, String.class, String.class}), new PreHook(new BetterCodeBlocks$$ExternalSyntheticLambda1(this)));
    }

    public /* synthetic */ void lambda$start$0$io-github-juby210-acplugins-BetterCodeBlocks(MethodHookParam param) {
        Map<String, Object> langMap = ((a) param.thisObject).a;
        if (!langMap.containsKey("go")) {
            try {
                SimpleASTUtils.addLanguages(langMap);
            } catch (Throwable e) {
                Main.logger.error("Failed to add languages", e);
            }
        }
        Matcher matcher = param.args[0];
        if (matcher != null) {
            String lang = matcher.group(1);
            if (Settings.Companion.get(this.settings, lang)) {
                param.setResult(new ParseSpec(renderCodeBlock(lang, matcher.group(3)), param.args[2]));
            }
        }
    }

    static /* synthetic */ void lambda$start$1(MethodHookParam param) {
        Node[] nodes = (Node[]) param.args[1];
        if (nodes.length == 1 && (nodes[0] instanceof CodeNode)) {
            nodes[0] = new LangNode(((CodeNode) nodes[0]).a, nodes[0]);
            param.args[1] = nodes;
        }
    }

    public /* synthetic */ void lambda$start$2$io-github-juby210-acplugins-BetterCodeBlocks(MethodHookParam param) {
        String lang = param.args[2];
        if (Settings.Companion.get(this.settings, lang)) {
            SpannableStringBuilder builder = param.args[1];
            int a = builder.length();
            CharSequence rendered = render(lang, (String) param.args[3]);
            Context ctx = param.args[0];
            wrapInNodes(lang, rendered).render(builder, new RenderContext(ctx));
            if (rendered instanceof String) {
                Utils.fixColor(builder, ctx, a);
            }
            param.setResult(builder);
        }
    }

    public void stop(Context context) {
        this.patcher.unpatchAll();
    }

    public CharSequence render(String lang, String content) {
        return this.highlight.highlight(lang, content == null ? "" : content);
    }

    public Node<BasicRenderContext> wrapInNodes(String lang, CharSequence content) {
        return new BlockBackgroundNode(false, new Node[]{new BCBNode(lang, content)});
    }

    public Node<BasicRenderContext> renderCodeBlock(String lang, String content) {
        return wrapInNodes(lang, render(lang, content));
    }
}
