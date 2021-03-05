package com.xxf.keyboard.emotion.component;

import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.xxf.keyboard.emotion.EmotionEngine;
import com.xxf.keyboard.emotion.EmojiSpanBuilder;
import com.xxf.keyboard.emotion.IEmotion;

import java.util.List;

/**
 * @Author: XGod  xuanyouwu@163.com  17611639080
 * Date: 3/5/21 11:12 AM
 * Description: emoji 表情 recyclerView 适配器
 */
public class EmojiAdapter extends RecyclerView.Adapter<EmojiAdapter.EmotionViewHolder> {
    List<IEmotion> emotionList;
    @Nullable
    EditText editText;

    public EmojiAdapter(@Nullable final EditText editText, List<IEmotion> emotionList) {
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
        EmotionEngine.INSTANCE.getLoader().load(holder.imageView, emotionList.get(position));
    }

    @Override
    public int getItemCount() {
        return emotionList.size();
    }

    public class EmotionViewHolder extends RecyclerView.ViewHolder {
        public SquareImageView imageView;

        public EmotionViewHolder(EditText editText, @NonNull ViewGroup parent) {
            super(new SquareImageView(parent.getContext()));
            imageView = (SquareImageView) super.itemView;
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    IEmotion emotion = emotionList.get(getAdapterPosition());
                    EmojiSpanBuilder.insert(editText, emotion.getText());
                }
            });
        }
    }
}
