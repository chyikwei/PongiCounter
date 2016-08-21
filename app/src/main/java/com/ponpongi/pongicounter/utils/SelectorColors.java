package com.ponpongi.pongicounter.utils;

import android.graphics.Color;

/**
 * Created by chyikwei on 8/20/2016.
 */
public enum SelectorColors {

    SYAN("#4DD0E1"),
    YELLOW("#FFEB3B"),
    LIGHTGREEN("#B8E986"),
    LIGHTPINK("#FDBCDA");

    private String code;

    private SelectorColors(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

    public static SelectorColors getByColorStr(String colorStr) {
        for (SelectorColors s: SelectorColors.values()) {
            if (s.code.equals(colorStr)) {
                return s;
            }
        }
        return null;
    }

    public String toString() {
        return this.code;
    }
}
