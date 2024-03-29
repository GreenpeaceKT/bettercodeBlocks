package io.github.greenpeacekt.acplugins;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.graphics.drawable.ColorDrawable.*;
import com.aliucord.Main;
import com.aliucord.annotations.AliucordPlugin;
import com.aliucord.entities.Plugin;
import com.aliucord.patcher.PreHook;
import com.aliucord.utils.MDUtils;
import com.discord.simpleast.code.CodeNode;
import com.discord.simpleast.core.node.Node;
import com.discord.simpleast.core.parser.ParseSpec;
import com.discord.simpleast.core.parser.Parser;
import com.discord.utilities.textprocessing.node.BasicRenderContext;
import com.discord.utilities.textprocessing.node.BlockBackgroundNode;

import java.util.*;
import java.util.regex.Matcher;

import io.github.greenpeacekt.acplugins.bettercodeblocks.*;
import io.noties.markwon.syntax.Prism4jSyntaxHighlight;
import io.noties.markwon.syntax.Prism4jThemeDarkula;
import io.noties.prism4j.Prism4j;

import android.content.Context;
import android.content.res.Resources;



@AliucordPlugin
@SuppressWarnings({ "unchecked", "unused" })
public final class BetterCodeBlocks extends Plugin {
    public static final GrammarLocatorImpl grammarLocator = new GrammarLocatorImpl();
    public static final Prism4j prism4j = new Prism4j(grammarLocator);

    public BetterCodeBlocks() {
        settingsTab = new SettingsTab(Settings.class, SettingsTab.Type.BOTTOM_SHEET).withArgs(settings);
    }


    @Override
    public void start(Context context) throws Throwable {
        highlight = Prism4jSyntaxHighlight.create(prism4j, new Prism4jThemeDarkula());

        patcher.patch(b.a.t.a.a.class, "parse", new Class<?>[]{ Matcher.class, Parser.class, Object.class }, new PreHook(param -> {
            var langMap = ((b.a.t.a.a) param.thisObject).a;
            if (!langMap.containsKey("go")) try {
                SimpleASTUtils.addLanguages(langMap);
            } catch (Throwable e) {
                Main.logger.error("Failed to add languages", e);
            }

            var matcher = (Matcher) param.args[0];
            if (matcher == null) return;
            var lang = (String) matcher.group(1);
            if (!Settings.Companion.getop(settings)){
                param.setResult(new ParseSpec<>(renderCodeBlock(lang, matcher.group(3)), param.args[2]));
            }else{
                param.setResult(new ParseSpec<>(devrenderCodeBlock(lang, matcher.group(3)), param.args[2]));
                }
            
        }));

        patcher.patch(BlockBackgroundNode.class.getDeclaredConstructor(boolean.class, Node[].class), new PreHook(param -> {
            var nodes = (Node<BasicRenderContext>[]) param.args[1];
            if (nodes.length == 1 && nodes[0] instanceof CodeNode) {
                nodes[0] = new LangNode<>(((CodeNode<BasicRenderContext>) nodes[0]).a, nodes[0]);
                param.args[1] = nodes;
            }
        }));
        
        /*patcher.patch(ColorDrawable::class.java.getDeclaredMethod("setColor", Int::class.javaPrimitiveType),
            new PreHook ( param ->{
                var color = param.args[0];
                var a = ResourceManager.INSTANCE.getColorReplacement(color);
                param.args[0] = a;
            
            // Discord has blocked message colours HARDCODED, so this is the only way to theme it :husk:
            // I HATE DISCORD
            
            })
        );*/

        patcher.patch(MDUtils.class.getDeclaredMethod("renderCodeBlock", Context.class, SpannableStringBuilder.class, String.class, String.class),
            new PreHook(param -> {
                var lang = (String) param.args[2];

                var builder = (SpannableStringBuilder) param.args[1];
                
                int a = builder.length();
                var rendered = render(lang, (String) param.args[3]);
                var ctx = (Context) param.args[0];
                wrapInNodes(lang, rendered).render(builder, new MDUtils.RenderContext(ctx));
                if (rendered instanceof String) Util.fixColor(builder, ctx, a);
                param.setResult(builder);
                
                
            })
        );
    }

    @Override
    public void stop(Context context) {
        patcher.unpatchAll();
    }
    
    /*public static Boolean Devop(){
        return settings.getBool("dev",true);
    }*/

    private Prism4jSyntaxHighlight highlight;

    public CharSequence render(String lang, String content) {
        return highlight.highlight(lang, content == null ? "" : content);
    }

    public Node<BasicRenderContext> wrapInNodes(String lang, CharSequence content) {
        return new BlockBackgroundNode<>(false, new BCBNode<>(lang, content));
    }

    public Node<BasicRenderContext> renderCodeBlock(String lang, String content) {
        return wrapInNodes(lang, render(lang, content));
    }
    
    public Node<BasicRenderContext> devwrapInNodes(String lang, CharSequence content){
        return new BlockBackgroundNode<>(false, new DevBCBNode<>(lang, content));
    }
    
    public Node<BasicRenderContext> devrenderCodeBlock(String lang, String content) {
        return devwrapInNodes(lang, render(lang, content));
    }
}