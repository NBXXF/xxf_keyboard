package com.xxf.keyboard.emotion.component;

import android.content.Context;
import android.text.Spannable;
import android.util.AttributeSet;

import com.xxf.keyboard.emotion.EmojiSpanBuilder;

/**
 * @Author: XGod  xuanyouwu@163.com  17611639080
 * Date: 3/5/21 11:03 AM
 * Description: 适配表情
 */
public class EmotionEditText extends androidx.appcompat.widget.AppCompatEditText {

    public EmotionEditText(Context context) {
        super(context);
    }

    public EmotionEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EmotionEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        Spannable spannable = EmojiSpanBuilder.buildEmotionSpannable(this.getContext(), text);
        super.setText(spannable, type);
    }
}
