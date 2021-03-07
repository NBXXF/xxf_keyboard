package com.xxf.keyboard.emotion;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.widget.EditText;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by XXF on 18-7-11
 * <p>
 * blog: XXF.com
 */
public class EmojiSpanBuilder {

    private static Pattern sPatternEmotion =
            Pattern.compile("\\[([\u4e00-\u9fa5\\w])+\\]|[\\ud83c\\udc00-\\ud83c\\udfff]|[\\ud83d\\udc00-\\ud83d\\udfff]|[\\u2600-\\u27ff]");

    private static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }


    public static Spannable buildEmotionSpannable(Context context, CharSequence text) {
        if (text == null) {
            return new SpannableString("");
        }
        Matcher matcherEmotion = sPatternEmotion.matcher(text);
        SpannableString spannableString = new SpannableString(text);
        while (matcherEmotion.find()) {
            /**
             * 避免重复运算
             */
            CenterImageSpan[] spans = spannableString.getSpans(matcherEmotion.start(), matcherEmotion.end(), CenterImageSpan.class);
            if (spans != null && spans.length > 0) {
                continue;
            }
            String key = matcherEmotion.group();
            IEmotion iEmotion = EmotionEngine.INSTANCE.getEmotion(key);
            Drawable emotion = EmotionEngine.INSTANCE.getLoader().load(context, iEmotion);
            if (emotion != null) {
                int start = matcherEmotion.start();
                emotion.setBounds(0, 0, dip2px(context, 20f), dip2px(context, 20f));
                CenterImageSpan span = new CenterImageSpan(emotion);
                spannableString.setSpan(span, start, start + key.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }

        return spannableString;
    }

    /**
     * 插入到当前焦点
     *
     * @param editText
     * @param text
     */
    public static void insert(EditText editText, CharSequence text) {
        if (editText == null || TextUtils.isEmpty(text)) {
            return;
        }
        int start = editText.getSelectionStart();
        Editable editable = editText.getEditableText();
        Spannable emotionSpannable = EmojiSpanBuilder.buildEmotionSpannable(editText.getContext(), text);
        editable.insert(start, emotionSpannable);
    }
}
