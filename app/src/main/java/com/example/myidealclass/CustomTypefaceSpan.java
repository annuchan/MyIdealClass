package com.example.myidealclass;

import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.style.TypefaceSpan;

public class CustomTypefaceSpan extends TypefaceSpan {
    private final Typeface newTypeface;

    public CustomTypefaceSpan(String family, Typeface typeface) {
        super(family);
        this.newTypeface = typeface;
    }

    @Override
    public void updateDrawState(TextPaint paint) {
        applyCustomTypeface(paint, newTypeface);
    }

    @Override
    public void updateMeasureState(TextPaint paint) {
        applyCustomTypeface(paint, newTypeface);
    }

    private static void applyCustomTypeface(TextPaint paint, Typeface typeface) {
        int oldStyle = paint.getTypeface() != null ? paint.getTypeface().getStyle() : 0;
        Typeface newTypeface = typeface != null ? typeface : Typeface.DEFAULT;
        paint.setTypeface(newTypeface);
        paint.setFakeBoldText((oldStyle & Typeface.BOLD) != 0 || newTypeface.getStyle() == Typeface.BOLD);
    }
}