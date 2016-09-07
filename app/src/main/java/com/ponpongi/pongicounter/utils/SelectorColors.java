package com.ponpongi.pongicounter.utils;

import android.graphics.Color;

/**
 * Created by chyikwei on 8/20/2016.
 */
public enum SelectorColors {

    SYAN("#81C5F4"),
    RED("#E985B3"),
    YELLOW("#F3EA79"),
    LIGHTGREEN("#98E6BE"),
    LIGHTPINK("#FFBFB9"),
    PURPLE("#C2A9F1") ;

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
