package com.example.olga.aa_app.utility;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;

/**
 * Draws a linear gradient over 1/4 of the screen.
 * <p>
 * This is used for the gradient background in the BaseView class.
 */
public class BackgroundDrawable extends Drawable {
    private int[] color;

    public BackgroundDrawable(int[] color) {
        this.color = color;
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        float width = getBounds().width();
        float height = getBounds().height() * 0.25f;

        paint.setShader(new LinearGradient(0, 0, width, height, color, null, Shader.TileMode.MIRROR));
        canvas.drawRect(0, 0, width, height, paint);
    }

    @Override
    public void setAlpha(int alpha) {
    }

    @Override
    public void setColorFilter(ColorFilter cf) {

    }

    @Override
    public int getOpacity() {
        return PixelFormat.OPAQUE;
    }
}