package com.tomato.curry.Font;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.util.LruCache;
import android.util.AttributeSet;
import android.widget.TextView;


public class TcFontIcons2 extends TextView {

    private final static String NAME = "TextTC2";
    private static LruCache<String, Typeface> sTypefaceCache = new LruCache<String, Typeface>(12);

    public TcFontIcons2(Context context) {
        super(context);
        if(!isInEditMode()){
            init();
        }
    }

    public TcFontIcons2(Context context, AttributeSet attrs) {
        super(context, attrs);
        if(!isInEditMode()){
            init();
        }
    }

    public void init() {
        Typeface typeface = sTypefaceCache.get(NAME);
        if (typeface == null) {
            typeface = Typeface.createFromAsset(getContext().getAssets(), "tomota.ttf");
            sTypefaceCache.put(NAME, typeface);
        }
        setTypeface(typeface);
    }
}