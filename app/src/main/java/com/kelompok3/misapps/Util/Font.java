package com.kelompok3.misapps.Util;

import android.content.Context;
import android.graphics.Typeface;

public class Font {
    private Context context;

    public Font(Context context) {
        this.context = context;
    }

    public Typeface montserratBold() {
        return Typeface.createFromAsset(context.getAssets(), "fonts/Montserrat-Bold.otf");
    }

    public Typeface productSansBold() {
        return Typeface.createFromAsset(context.getAssets(), "fonts/Product-Sans-Bold.ttf");
    }

    public Typeface productSansRegular() {
        return Typeface.createFromAsset(context.getAssets(), "fonts/Product-Sans-Regular.ttf");
    }
}
