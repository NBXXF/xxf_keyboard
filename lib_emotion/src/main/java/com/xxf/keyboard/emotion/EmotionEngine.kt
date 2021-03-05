package com.xxf.keyboard.emotion

import java.util.*

/**
 * @Author: XGod  xuanyouwu@163.com  17611639080
 * Date: 3/5/21 2:56 PM
 * Description: 表情引擎  支持drawable 支持 本地文件 赞不支持网络图片(请下载打包好到本地在使用)
 */
object EmotionEngine {
    private val cache: LinkedHashMap<String, IEmotion> = LinkedHashMap();
    private var loader: IEmotionLoader? = null;

    fun init(loader: IEmotionLoader) {
        EmotionEngine.loader = loader;
    }

    fun getLoader(): IEmotionLoader {
        if (loader == null) {
            throw RuntimeException("you need init EmotionEngine")
        }
        return loader!!;
    }


    fun putEmotion(emotion: Map<String, IEmotion>) {
        cache.putAll(emotion);
    }

    fun getEmotion(): Map<String, IEmotion> {
        return cache;
    }

    fun getEmotion(key: String): IEmotion? {
        return cache.get(key);
    }
}
