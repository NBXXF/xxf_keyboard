package com.xxf.keyboard.emotion;

/**
 * @Author: XGod  xuanyouwu@163.com  17611639080
 * Date: 3/5/21 2:45 PM
 * Description: 图片文件
 */
public class FileEmotion implements IEmotion {
    private String text;
    private String fileName;

    public FileEmotion(String text, String fileName) {
        this.text = text;
        this.fileName = fileName;
    }

    @Override
    public String getText() {
        return text;
    }

    public String getFileName() {
        return fileName;
    }
}
