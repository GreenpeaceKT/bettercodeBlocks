package io.github.greenpeacekt.acplugins;

import com.android.tools.r8.annotations.SynthesizedClass;
import de.robv.android.xposed.XC_MethodHook.MethodHookParam;
import rx.functions.Action1;

@SynthesizedClass(kind = "Lambda", value = BetterCodeBlocks.class)
public final /* synthetic */ class BetterCodeBlocks$$ExternalSyntheticLambda2 implements Action1 {
    public static final /* synthetic */ BetterCodeBlocks$$ExternalSyntheticLambda2 INSTANCE = new BetterCodeBlocks$$ExternalSyntheticLambda2();

    private /* synthetic */ BetterCodeBlocks$$ExternalSyntheticLambda2() {
    }

    public final void call(Object obj) {
        BetterCodeBlocks.lambda$start$1((MethodHookParam) obj);
    }
}
