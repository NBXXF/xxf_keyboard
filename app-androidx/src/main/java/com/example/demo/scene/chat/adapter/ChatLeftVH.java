package com.example.demo.scene.chat.adapter;


import android.view.View;
import android.widget.Toast;

import com.effective.databinding.VhChatLeftLayoutBinding;
import com.xxf.keyboard.emotion.EmojiSpanBuilder;

/**
 * the left of chatting item
 * Created by XXF on 18-7-11
 *
 * blog: XXF.com
 */
public class ChatLeftVH extends ChatBaseVH<VhChatLeftLayoutBinding, ChatInfo> {

    public ChatLeftVH(VhChatLeftLayoutBinding binding) {
        super(binding);
    }

    @Override
    public void bindData(ChatInfo chatInfo, int position) {
        binding.text.setText(EmojiSpanBuilder.buildEmotionSpannable(binding.getRoot().getContext(), chatInfo.message));
        binding.avatar.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Toast.makeText(view.getContext(),"长按了头像",Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        binding.avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),"点击了头像",Toast.LENGTH_SHORT).show();
            }
        });
        binding.text.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Toast.makeText(view.getContext(),"长按了消息",Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        binding.text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),"点击了消息",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
