package com.example.olga.aa_app.utility;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.style.ReplacementSpan;

import androidx.core.graphics.ColorUtils;

import com.example.olga.aa_app.R;

// https://stackoverflow.com/questions/19292838/android-spannablestring-set-background-behind-part-of-text
public class TagSpan extends ReplacementSpan {

    private final float CORNER_RADIUS = 50.0f;
    private int backgroundColor;

    public TagSpan(Context context) {
        super();
        backgroundColor = context.getResources().getColor(R.color.accent);
    }

    @Override
    public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, Paint paint) {
        float stroke_width = 6.0f;
        RectF rect = new RectF(x, top, x + measureText(paint, text, start, end), bottom);
        rect.inset(-CORNER_RADIUS / 2, 0);
        rect.offsetTo(x + stroke_width / 2, top);
        float[] hsl = new float[3];
        ColorUtils.colorToHSL(backgroundColor, hsl);
        hsl[1] += 0.075;
        hsl[2] += 0.115;
        paint.setColor(ColorUtils.HSLToColor(hsl));
        canvas.drawRoundRect(rect, CORNER_RADIUS, CORNER_RADIUS, paint);
        paint.setColor(backgroundColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(stroke_width);
        canvas.drawRoundRect(rect, CORNER_RADIUS, CORNER_RADIUS, paint);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        canvas.drawText(text, start, end, x + (stroke_width + CORNER_RADIUS) / 2, y, paint);
    }

    @Override
    public int getSize(Paint paint, CharSequence text, int start, int end, Paint.FontMetricsInt fm) {
        return Math.round(paint.measureText(text, start, end) + CORNER_RADIUS);
    }

    private float measureText(Paint paint, CharSequence text, int start, int end) {
        return paint.measureText(text, start, end);
    }
}
