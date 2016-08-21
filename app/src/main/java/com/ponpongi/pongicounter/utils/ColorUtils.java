package com.ponpongi.pongicounter.utils;

import android.graphics.Color;

/**
 * Created by chyikwei on 8/20/2016.
 */
public class ColorUtils {
    public static String colorToStr(int intColor) {
        String hexColor = String.format("#%06X", (0xFFFFFF & intColor));
        return  hexColor;
    }

    public static int parseColor(String strColor) {
        return Color.parseColor(strColor);
    }
}
