package io.noties.markwon.core.spans;

import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;

public class EmphasisSpan extends MetricAffectingSpan {
    public void updateMeasureState(TextPaint p) {
        p.setTextSkewX(-0.25f);
    }

    public void updateDrawState(TextPaint tp) {
        tp.setTextSkewX(-0.25f);
    }
}
