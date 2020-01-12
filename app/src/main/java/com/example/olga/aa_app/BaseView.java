package com.example.olga.aa_app;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.olga.aa_app.utility.BackgroundDrawable;

/**
 * Root view for screens with header.
 */
public class BaseView extends LinearLayout {

    private int baseColor = Color.BLACK;
    private String title = "";
    private int icon = 0;
    private int tint = Color.BLACK;
    private TextView textView;

    public BaseView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, @Nullable AttributeSet attrs) {
        final TypedArray styleAttributes = context.obtainStyledAttributes(attrs, R.styleable.BaseView);
        try {
            baseColor = styleAttributes.getColor(R.styleable.BaseView_base_color, baseColor);
            title = styleAttributes.getString(R.styleable.BaseView_title);
            icon = styleAttributes.getResourceId(R.styleable.BaseView_icon, 0);
            tint = styleAttributes.getColor(R.styleable.BaseView_tint, tint);
        } finally {
            styleAttributes.recycle();
        }

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        setOrientation(VERTICAL);
        setBackground(new BackgroundDrawable(new int[] {getResources().getColor(R.color.colorPrimaryDark), baseColor}));

        textView = (TextView) inflater.inflate(R.layout.title, this, false);;
        textView.setText(title);
        textView.setTextColor(tint);
        // 0 is an invalid id!
        if (icon != 0) {
            Drawable drawable = getResources().getDrawable(icon);
            drawable.setTint(tint);
            textView.setCompoundDrawablesWithIntrinsicBounds(null, drawable, null, null);
        }
        addView(textView);
    }

    public int getBaseColor() {
        return baseColor;
    }

    public int getIcon() {
        return icon;
    }

    public String getTitle() {
        return title;
    }

    public void setBaseColor(int baseColor) {
        this.baseColor = baseColor;
        setBackground(new BackgroundDrawable(new int[] {getResources().getColor(R.color.colorPrimaryDark), baseColor}));
        invalidate();
        requestLayout();
    }

    public void setIcon(int icon) {
        this.icon = icon;
        Drawable drawable = getResources().getDrawable(icon);
        drawable.setTint(tint);
        textView.setCompoundDrawablesWithIntrinsicBounds(null, drawable, null, null);
        invalidate();
        requestLayout();
    }

    public void setTitle(String title) {
        this.title = title;
        textView.setText(title);
        invalidate();
        requestLayout();
    }

    public int getTint() {
        return tint;
    }

    public void setTint(int tint) {
        this.tint = tint;
        textView.setTextColor(tint);
        Drawable drawable = getResources().getDrawable(icon);
        drawable.setTint(tint);
        textView.setCompoundDrawablesWithIntrinsicBounds(null, drawable, null, null);
        invalidate();
        requestLayout();
    }
}
