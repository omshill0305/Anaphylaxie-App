package com.example.olga.aa_app.utility;

import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Log;
import android.widget.MultiAutoCompleteTextView;

// https://www.journaldev.com/22333/android-multiautocompletetextview
public class SpaceTokenizer implements MultiAutoCompleteTextView.Tokenizer {
    @Override
    public int findTokenStart(CharSequence text, int cursor) {
        Log.d("Start", text.toString());
        if (text.length() == 0) {
            return 0;
        }
        int i = cursor;

        while (i > 0 && text.charAt(i - 1) != ' ') {
            i--;
        }
        while (i < cursor && text.charAt(i) == ' ') {
            i++;
        }
        Log.d("Start", Integer.toString(i));
        return i;
    }

    @Override
    public int findTokenEnd(CharSequence text, int cursor) {
        Log.d("End", text.toString());
        int i = cursor;
        int len = text.length();

        while (i < len) {
            if (text.charAt(i) == ' ') {
                Log.d("End", Integer.toString(i));
                return i;
            } else {
                i++;
            }
        }
        Log.d("End", Integer.toString(len));
        return len;
    }

    @Override
    public CharSequence terminateToken(CharSequence text) {
        int i = text.length();

        while (i > 0 && text.charAt(i - 1) == ' ') {
            i--;
        }

        if (i > 0 && text.charAt(i - 1) == ' ') {
            return text;
        } else {
            if (text instanceof Spanned) {
                SpannableStringBuilder sp = new SpannableStringBuilder(text + " ");
                TextUtils.copySpansFrom((Spanned) text, 0, text.length(), Object.class, sp, 0);
                return sp;
            } else {
                return text + " ";
            }
        }
    }
}