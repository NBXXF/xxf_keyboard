package com.example.demo;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.demo.scene.chat.emotion.Emotions;
import com.google.gson.Gson;
import com.xxf.keyboard.emotion.DrawableEmotionLoader;
import com.xxf.keyboard.emotion.EmotionEngine;
import com.xxf.keyboard.emotion.FileEmotion;
import com.xxf.keyboard.emotion.IEmotion;
import com.xxf.keyboard.emotion.IEmotionLoader;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Test.test(this);
        /**
         * 方式1：加载项目文件
         */
//        EmotionEngine.INSTANCE.init(new DrawableEmotionLoader());
//        EmotionEngine.INSTANCE.putEmotion(Emotions.getEmotionMap());

        /**
         * 方式2:加载本地文件 如asset sd卡文件 网络文件可以打包下载后使用参考 wechat_emoji.zip
         */
        EmotionEngine.INSTANCE.init(new IEmotionLoader() {
            @Override
            public void load(ImageView imageView, IEmotion emotion) {
                Glide.with(imageView)
                        .load("file:///android_asset/wechat_emoji/pics/" + ((FileEmotion) emotion).getFileName())
                        .into(imageView);
            }

            @Override
            public Drawable load(Context context, IEmotion emotion) {
                return getImageFromAssetsFile("wechat_emoji/pics/" + ((FileEmotion) emotion).getFileName());
            }
        });
        List<FileEmotion> content = convert().content;
        Map<String, IEmotion> map = new HashMap<>();
        for (FileEmotion item : content) {
            map.put(item.getText(), item);
        }
        EmotionEngine.INSTANCE.putEmotion(map);


        this.registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
                Toast.makeText(activity, "ac:" + activity.getClass().getSimpleName(), Toast.LENGTH_SHORT).show();
                Log.d("==========>act:" + activity, "");
                System.out.println("========>act:" + activity);
            }

            @Override
            public void onActivityStarted(@NonNull Activity activity) {

            }

            @Override
            public void onActivityResumed(@NonNull Activity activity) {

            }

            @Override
            public void onActivityPaused(@NonNull Activity activity) {

            }

            @Override
            public void onActivityStopped(@NonNull Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(@NonNull Activity activity) {

            }
        });
    }

    static class EmojiJsonConfig {
        public List<FileEmotion> content;

        @Override
        public String toString() {
            return "EmojiJsonConfig{" +
                    "content=" + content +
                    '}';
        }
    }

    private EmojiJsonConfig convert() {
        AssetManager am = getResources().getAssets();
        try {
            InputStream is = am.open("wechat_emoji/emoji.json");
            StringBuilder sb = new StringBuilder();
            String line;
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            String str = sb.toString();
            System.out.println("=======>json:" + str);
            return new Gson().fromJson(new JSONObject(str).toString(), EmojiJsonConfig.class);
        } catch (Exception e) {
            e.printStackTrace();

            System.out.println("=======>json ex:" + e);
        }
        return null;
    }

    private Drawable getImageFromAssetsFile(String filename) {
        Bitmap mBitmap = null;
        AssetManager mAssetManager = getResources().getAssets();
        try {
            InputStream mInputStream = mAssetManager.open(filename);
            mBitmap = BitmapFactory.decodeStream(mInputStream);
            mInputStream.close();
            return new BitmapDrawable(mBitmap);
        } catch (Exception e) {
            e.printStackTrace();
            mBitmap = null;
        }
        return null;
    }
}
