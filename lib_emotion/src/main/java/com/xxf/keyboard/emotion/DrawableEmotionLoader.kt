package com.xxf.keyboard.emotion

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.core.content.ContextCompat

/**
 * @Author: XGod  xuanyouwu@163.com  17611639080
 * Date: 3/5/21 4:04 PM
 * Description: android 资源目录加载
 */
class DrawableEmotionLoader : IEmotionLoader {
    override fun load(imageView: ImageView?, emotion: IEmotion?) {
        if (emotion is Emotion) {
            imageView?.setImageResource(emotion.drawableRes);
        }
    }

    override fun load(context: Context?, emotion: IEmotion?): Drawable? {
        if (emotion is Emotion) {
            return context?.let { ContextCompat.getDrawable(it, emotion.drawableRes) };
        }
        return null;
    }
}