package com.xxf.keyboard.wechat.component;

import android.text.Editable;
import android.text.Spannable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xxf.keyboard.wechat.emoji.EmojiSpanBuilder;
import com.xxf.keyboard.wechat.emoji.Emotion;
import com.xxf.keyboard.wechat.emoji.Emotions;

import java.util.List;

/**
 * @Author: XGod  xuanyouwu@163.com  17611639080
 * Date: 3/5/21 11:12 AM
 * Description: 表情 recyclerView 适配器
 */
public class EmotionAdapter extends RecyclerView.Adapter<EmotionAdapter.EmotionViewHolder> {
    List<Emotion> emotionList;
    EditText editText;

    public EmotionAdapter(final EditText editText) {
        this.editText = editText;
        this.emotionList = Emotions.getEmotions();

    }

    public EmotionAdapter(final EditText editText, List<Emotion> emotionList) {
        this.editText = editText;
        this.emotionList = emotionList;
    }

    @NonNull
    @Override
    public EmotionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new EmotionViewHolder(editText, parent);
    }

    @Override
    public void onBindViewHolder(@NonNull EmotionViewHolder holder, int position) {
        holder.imageView.setImageResource(emotionList.get(position).drawableRes);
    }

    @Override
    public int getItemCount() {
        return emotionList.size();
    }

    class EmotionViewHolder extends RecyclerView.ViewHolder {
        SquareImageView imageView;

        public EmotionViewHolder(EditText editText, @NonNull ViewGroup parent) {
            super(new SquareImageView(parent.getContext()));
            imageView = (SquareImageView) super.itemView;
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Emotion emotion = emotionList.get(getAdapterPosition());
                    int start = editText.getSelectionStart();
                    Editable editable = editText.getEditableText();
                    Spannable emotionSpannable = EmojiSpanBuilder.buildEmotionSpannable(parent.getContext(), emotion.text);
                    editable.insert(start, emotionSpannable);
                }
            });
        }
    }
}
