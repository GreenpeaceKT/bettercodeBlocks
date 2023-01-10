package io.github.greenpeacekt.acplugins;

import com.android.tools.r8.annotations.SynthesizedClass;
import de.robv.android.xposed.XC_MethodHook.MethodHookParam;
import rx.functions.Action1;

@SynthesizedClass(kind = "Lambda", value = BetterCodeBlocks.class)
public final /* synthetic */ class BetterCodeBlocks$$ExternalSyntheticLambda1 implements Action1 {
    public final /* synthetic */ BetterCodeBlocks f$0;

    public /* synthetic */ BetterCodeBlocks$$ExternalSyntheticLambda1(BetterCodeBlocks betterCodeBlocks) {
        this.f$0 = betterCodeBlocks;
    }

    public final void call(Object obj) {
        this.f$0.lambda$start$2$io-github-juby210-acplugins-BetterCodeBlocks((MethodHookParam) obj);
    }
}
