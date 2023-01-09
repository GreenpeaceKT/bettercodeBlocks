package io.noties.markwon.core.spans;

import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;

public class StrongEmphasisSpan extends MetricAffectingSpan {
    public void updateMeasureState(TextPaint p) {
        p.setFakeBoldText(true);
    }

    public void updateDrawState(TextPaint tp) {
        tp.setFakeBoldText(true);
    }
}
