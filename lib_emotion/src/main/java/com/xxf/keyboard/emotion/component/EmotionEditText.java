package com.xxf.keyboard.emotion.component;

import android.content.Context;
import android.text.Editable;
import android.text.Spannable;
import android.text.TextWatcher;
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
        init();
    }


    public EmotionEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EmotionEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private final TextWatcher emojiTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            try {
                EmotionEditText.this.removeTextChangedListener(emojiTextWatcher);
                int selectionEnd = -1;
                if (!hasSelection()) {
                    selectionEnd = getSelectionEnd();
                }
                setText(s, BufferType.EDITABLE);
                if (selectionEnd >= 0) {
                    setSelection(selectionEnd);
                }
            } catch (Throwable e) {
                e.printStackTrace();
            } finally {
                init();
            }
        }
    };

    private void init() {
        this.removeTextChangedListener(emojiTextWatcher);
        this.addTextChangedListener(emojiTextWatcher);
    }


    @Override
    public void setText(CharSequence text, BufferType type) {
        Spannable spannable = EmojiSpanBuilder.buildEmotionSpannable(this.getContext(), text);
        super.setText(spannable, type);
    }
}
