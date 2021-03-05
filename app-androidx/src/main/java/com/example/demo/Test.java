package com.example.demo;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: XGod  xuanyouwu@163.com  17611639080
 * Date: 3/5/21 2:04 PM
 * Description: TODO
 */
public class Test {
    public static Map<String, String> EMOTIONS = new LinkedHashMap<String, String>();

    static {
        EMOTIONS.put("[微笑]", "expression_1.png");
        EMOTIONS.put("[撇嘴]", "expression_2.png");
        EMOTIONS.put("[色]", "expression_3.png");
        EMOTIONS.put("[发呆]", "expression_4.png");
        EMOTIONS.put("[得意]", "expression_5.png");
        EMOTIONS.put("[流泪]", "expression_6.png");
        EMOTIONS.put("[害羞]", "expression_7.png");
        EMOTIONS.put("[闭嘴]", "expression_8.png");
        EMOTIONS.put("[睡]", "expression_9.png");
        EMOTIONS.put("[大哭]", "expression_10.png");
        EMOTIONS.put("[尴尬]", "expression_11.png");
        EMOTIONS.put("[发怒]", "expression_12.png");
        EMOTIONS.put("[调皮]", "expression_13.png");
        EMOTIONS.put("[呲牙]", "expression_14.png");
        EMOTIONS.put("[惊讶]", "expression_15.png");
        EMOTIONS.put("[难过]", "expression_16.png");
        EMOTIONS.put("[囧]", "expression_18.png");
        EMOTIONS.put("[抓狂]", "expression_19.png");
        EMOTIONS.put("[吐]", "expression_20.png");
        EMOTIONS.put("[偷笑]", "expression_21.png");
        EMOTIONS.put("[愉快]", "expression_22.png");
        EMOTIONS.put("[白眼]", "expression_23.png");
        EMOTIONS.put("[傲慢]", "expression_24.png");
        EMOTIONS.put("[困]", "expression_26.png");
        EMOTIONS.put("[惊恐]", "expression_27.png");
        EMOTIONS.put("[流汗]", "expression_28.png");
        EMOTIONS.put("[憨笑]", "expression_29.png");
        EMOTIONS.put("[悠闲]", "expression_30.png");
        EMOTIONS.put("[奋斗]", "expression_31.png");
        EMOTIONS.put("[咒骂]", "expression_32.png");
        EMOTIONS.put("[疑问]", "expression_33.png");
        EMOTIONS.put("[嘘]", "expression_34.png");
        EMOTIONS.put("[晕]", "expression_35.png");
        EMOTIONS.put("[衰]", "expression_37.png");
        EMOTIONS.put("[骷髅]", "expression_38.png");
        EMOTIONS.put("[敲打]", "expression_39.png");
        EMOTIONS.put("[再见]", "expression_40.png");
        EMOTIONS.put("[擦汗]", "expression_41.png");
        EMOTIONS.put("[抠鼻]", "expression_42.png");
        EMOTIONS.put("[鼓掌]", "expression_43.png");
        EMOTIONS.put("[坏笑]", "expression_45.png");
        EMOTIONS.put("[左哼哼]", "expression_46.png");
        EMOTIONS.put("[右哼哼]", "expression_47.png");
        EMOTIONS.put("[哈欠]", "expression_48.png");
        EMOTIONS.put("[鄙视]", "expression_49.png");
        EMOTIONS.put("[委屈]", "expression_50.png");
        EMOTIONS.put("[快哭了]", "expression_51.png");
        EMOTIONS.put("[阴险]", "expression_52.png");
        EMOTIONS.put("[亲亲]", "expression_53.png");
        EMOTIONS.put("[可怜]", "expression_55.png");
        EMOTIONS.put("[菜刀]", "expression_56.png");
        EMOTIONS.put("[西瓜]", "expression_57.png");
        EMOTIONS.put("[啤酒]", "expression_58.png");
        EMOTIONS.put("[咖啡]", "expression_61.png");
        EMOTIONS.put("[猪头]", "expression_63.png");
        EMOTIONS.put("[玫瑰]", "expression_64.png");
        EMOTIONS.put("[凋谢]", "expression_65.png");
        EMOTIONS.put("[嘴唇]", "expression_66.png");
        EMOTIONS.put("[爱心]", "expression_67.png");
        EMOTIONS.put("[心碎]", "expression_68.png");
        EMOTIONS.put("[蛋糕]", "expression_69.png");
        EMOTIONS.put("[炸弹]", "expression_71.png");
        EMOTIONS.put("[便便]", "expression_75.png");
        EMOTIONS.put("[月亮]", "expression_76.png");
        EMOTIONS.put("[太阳]", "expression_77.png");
        EMOTIONS.put("[拥抱]", "expression_79.png");
        EMOTIONS.put("[强]", "expression_80.png");
        EMOTIONS.put("[弱]", "expression_81.png");
        EMOTIONS.put("[握手]", "expression_82.png");
        EMOTIONS.put("[胜利]", "expression_83.png");
        EMOTIONS.put("[抱拳]", "expression_84.png");
        EMOTIONS.put("[勾引]", "expression_85.png");
        EMOTIONS.put("[拳头]", "expression_86.png");
        EMOTIONS.put("[OK]", "expression_90.png");
        EMOTIONS.put("[跳跳]", "expression_93.png");
        EMOTIONS.put("[发抖]", "expression_94.png");
        EMOTIONS.put("[怄火]", "expression_95.png");
        EMOTIONS.put("[转圈]", "expression_96.png");
        EMOTIONS.put(emotionCode2String(0x1F604), "expression_97.png");
        EMOTIONS.put(emotionCode2String(0x1F637), "expression_98.png");
        EMOTIONS.put(emotionCode2String(0x1F602), "expression_99.png");
        EMOTIONS.put(emotionCode2String(0x1F61D), "expression_101.png");
        EMOTIONS.put(emotionCode2String(0x1F633), "expression_102.png");
        EMOTIONS.put(emotionCode2String(0x1F631), "expression_103.png");
        EMOTIONS.put(emotionCode2String(0x1F614), "expression_104.png");
        EMOTIONS.put(emotionCode2String(0x1F612), "expression_105.png");
        EMOTIONS.put("[嘿哈]", "expression_107.png");
        EMOTIONS.put("[捂脸]", "expression_108.png");
        EMOTIONS.put("[奸笑]", "expression_106.png");
        EMOTIONS.put("[机智]", "expression_109.png");
        EMOTIONS.put("[皱眉]", "expression_119.png");
        EMOTIONS.put("[耶]", "expression_113.png");
        EMOTIONS.put(emotionCode2String(0x1F47B), "expression_114.png");
        EMOTIONS.put(emotionCode2String(0x1F64F), "expression_115.png");
        EMOTIONS.put(emotionCode2String(0x1F4AA), "expression_116.png");
        EMOTIONS.put(emotionCode2String(0x1F389), "expression_117.png");
        EMOTIONS.put(emotionCode2String(0x1F381), "expression_118.png");
        EMOTIONS.put("[红包]", "expression_111.png");

    }

    private static String emotionCode2String(int code) {
        return new String(Character.toChars(code));
    }

    public static void test(Context context) {
        File file = new File(context.getCacheDir(), "emoji.json");
        try {
            file.createNewFile();
            try (FileWriter fw = new FileWriter(file, false)) {
                fw.write(new Gson().toJson(EMOTIONS));
                fw.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
       // System.out.println("=========>json111:" + new Gson().toJson(EMOTIONS));
    }

}
