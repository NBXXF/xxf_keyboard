# 微信表情 与键盘
1. 支持项目文件 和sd卡文件
```
    /**
         * 方式1：加载项目文件
         */
        EmotionEngine.INSTANCE.init(new DrawableEmotionLoader());
        EmotionEngine.INSTANCE.putEmotion(Emotions.getEmotionMap());

        /**
         * 方式2:加载本地文件
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
```