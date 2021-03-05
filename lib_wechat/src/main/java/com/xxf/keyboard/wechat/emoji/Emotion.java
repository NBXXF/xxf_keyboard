package com.xxf.keyboard.wechat.emoji;


import androidx.annotation.DrawableRes;

/**
 * Created by XXF on 18-7-11
 *
 * blog: XXF.com
 */
public class Emotion {
    
    public String text;

    @DrawableRes
    public int drawableRes;

    public Emotion(String text, @DrawableRes int drawableRes) {
        this.text = text;
        this.drawableRes = drawableRes;
    }
}
