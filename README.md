# 微信表情 与键盘
1. 支持项目文件 和sd卡文件
```
  /**
         * 方式1：加载项目文件
         */
          EmotionEngine.INSTANCE.init(new DrawableEmotionLoader());
          EmotionEngine.INSTANCE.putEmotion(Emotions.getEmotionMap());

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
```