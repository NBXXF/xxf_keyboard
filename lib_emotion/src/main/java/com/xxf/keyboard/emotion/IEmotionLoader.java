package com.xxf.keyboard.emotion;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

/**
 * @Author: XGod  xuanyouwu@163.com  17611639080
 * Date: 3/5/21 2:18 PM
 * Description: 加载emotion
 */
public interface IEmotionLoader {
    /**
     * 加载emotion
     *
     * @param imageView
     * @param emotion
     */
    void load(ImageView imageView, IEmotion emotion);


    /**
     * 获取 emotion
     *
     * @param context
     * @param emotion
     * @return
     */
    Drawable load(Context context, IEmotion emotion);
}
