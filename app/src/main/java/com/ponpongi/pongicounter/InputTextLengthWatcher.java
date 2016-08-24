package com.ponpongi.pongicounter;

import android.text.Editable;
import android.text.TextWatcher;

/**
 * Created by chyikwei on 8/23/2016.
 */
public class InputTextLengthWatcher implements TextWatcher {

    private int maxLength;
    private int currentEnd = 0;

    public InputTextLengthWatcher(final int maxLength) {
        assert maxLength > 0;
        this.maxLength = maxLength;
    }

    @Override
    public void beforeTextChanged(final CharSequence s, final int start, final int count, final int after) {

    }

    @Override
    public void onTextChanged(final CharSequence s, final int start, final int before, final int count) {
        currentEnd = start + count;
    }

    @Override
    public void afterTextChanged(final Editable s) {
        while (calculateLength(s) > maxLength) {
            currentEnd--;
            s.delete(currentEnd, currentEnd + 1);
        }
    }

    protected int calculateLength(final CharSequence c) {
        int len = 0;
        final int l = c.length();
        for (int i = 0; i < l; i++) {
            final char tmp = c.charAt(i);
            if (tmp >= 0x20 && tmp <= 0x7E) {
                len++;
            } else {
                len += 2;
            }
        }
        return len;
    }
}
