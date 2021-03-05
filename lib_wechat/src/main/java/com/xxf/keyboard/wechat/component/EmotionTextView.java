package com.xxf.keyboard.wechat.component;

import android.content.Context;
import android.text.Spannable;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

import com.xxf.keyboard.wechat.emoji.EmojiSpanBuilder;

/**
 * @Author: XGod  xuanyouwu@163.com  17611639080
 * Date: 3/5/21 10:59 AM
 * Description: 适配表情
 */
public class EmotionTextView extends AppCompatTextView {

    public EmotionTextView(Context context) {
        super(context);
    }

    public EmotionTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EmotionTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        Spannable spannable = EmojiSpanBuilder.buildEmotionSpannable(this.getContext(), text);
        super.setText(spannable, type);
    }
}
