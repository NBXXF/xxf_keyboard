package com.xxf.keyboard.emotion;


import androidx.annotation.DrawableRes;

/**
 * Created by XXF on 18-7-11
 * <p>
 * blog: XXF.com
 */
public class Emotion implements IEmotion {

    public String text;

    @DrawableRes
    public int drawableRes;

    public Emotion(String text, @DrawableRes int drawableRes) {
        this.text = text;
        this.drawableRes = drawableRes;
    }

    @Override
    public String getText() {
        return text;
    }
}
