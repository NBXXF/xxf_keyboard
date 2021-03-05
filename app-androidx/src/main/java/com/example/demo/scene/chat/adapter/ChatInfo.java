package com.example.demo.scene.chat.adapter;

/**
 * the information of chatting item
 * Created by XXF on 18-7-11
 *
 * blog: XXF.com
 */
public class ChatInfo {

    public String message;
    public boolean owner;
    public static boolean sBoolean = true;

    public ChatInfo(String message, boolean owner) {
        this.message = message;
        this.owner = owner;
    }

    public static ChatInfo CREATE(String message) {
        ChatInfo chatInfo = new ChatInfo(message, sBoolean);
        sBoolean = !sBoolean;
        return chatInfo;
    }
}
