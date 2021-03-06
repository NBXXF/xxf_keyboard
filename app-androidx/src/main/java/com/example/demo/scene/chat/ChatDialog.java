package com.example.demo.scene.chat;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;

import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.effective.R;
import com.effective.android.panel.PanelSwitchHelper;
import com.effective.android.panel.interfaces.listener.OnPanelChangeListener;
import com.effective.android.panel.view.panel.IPanelView;
import com.effective.android.panel.view.panel.PanelView;
import com.effective.android.panel.window.PanelDialog;
import com.effective.databinding.CommonChatWithTitlebarLayoutBinding;
import com.example.demo.scene.chat.adapter.ChatAdapter;
import com.example.demo.scene.chat.adapter.ChatInfo;
import com.example.demo.scene.chat.emotion.EmotionPagerView;
import com.example.demo.scene.chat.emotion.Emotions;
import com.example.demo.util.DisplayUtils;
import com.rd.PageIndicatorView;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ChatDialog extends PanelDialog implements DialogInterface.OnKeyListener {

    private CommonChatWithTitlebarLayoutBinding mBinding;
    private PanelSwitchHelper mHelper;
    private ChatAdapter mAdapter;
    private LinearLayoutManager mLinearLayoutManager;
    private static final String TAG = "ChatDialog";
    private Activity activity;

    @Override
    public int getDialogLayout() {
        return R.layout.common_chat_with_titlebar_layout;
    }

    public ChatDialog(Activity context) {
        super(context);
        this.activity = context;
        mBinding = DataBindingUtil.bind(rootView);
        mBinding.statusBar.setVisibility(View.GONE);
        getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(activity, R.color.common_page_bg_color)));
        setOnKeyListener(this);
        initView();
    }

    @Override
    public void show() {
        if (mHelper == null) {
            mHelper = new PanelSwitchHelper.Builder(activity.getWindow(), mBinding.getRoot())
                    //??????
                    .addKeyboardStateListener((visible, height) -> Log.d(TAG, "???????????????????????? : " + visible + " ????????????" + height))
                    //??????
                    .addEditTextFocusChangeListener((view, hasFocus) -> {
                        Log.d(TAG, "??????????????????????????? : " + hasFocus);
                        if (hasFocus) {
                            scrollToBottom();
                        }
                    })
                    //??????
                    .addViewClickListener(view -> {
                        switch (view.getId()) {
                            case R.id.edit_text:
                            case R.id.add_btn:
                            case R.id.emotion_btn: {
                                scrollToBottom();
                            }
                        }
                        Log.d(TAG, "?????????View : " + view);
                    })
                    //??????
                    .addPanelChangeListener(new OnPanelChangeListener() {

                        @Override
                        public void onKeyboard() {
                            Log.d(TAG, "?????????????????????");
                            mBinding.emotionBtn.setSelected(false);
                        }

                        @Override
                        public void onNone() {
                            Log.d(TAG, "??????????????????");
                            mBinding.emotionBtn.setSelected(false);
                        }

                        @Override
                        public void onPanel(IPanelView view) {
                            Log.d(TAG, "???????????? : " + view);
                            if (view instanceof PanelView) {
                                mBinding.emotionBtn.setSelected(((PanelView) view).getId() == R.id.panel_emotion ? true : false);
                            }
                        }


                        @Override
                        public void onPanelSizeChange(IPanelView panelView, boolean portrait, int oldWidth, int oldHeight, int width, int height) {
                            if (panelView instanceof PanelView) {
                                switch (((PanelView) panelView).getId()) {
                                    case R.id.panel_emotion: {
                                        EmotionPagerView pagerView = mBinding.getRoot().findViewById(R.id.view_pager);
                                        int viewPagerSize = height - DisplayUtils.dip2px(getContext(), 30f);
                                        pagerView.buildEmotionViews(
                                                (PageIndicatorView) mBinding.getRoot().findViewById(R.id.pageIndicatorView),
                                                mBinding.editText,
                                                Emotions.getEmotions(), width, viewPagerSize);
                                        break;
                                    }
                                    case R.id.panel_addition: {
                                        //auto center,nothing to do
                                        break;
                                    }
                                }
                            }
                        }
                    })
                    .logTrack(true)             //output log
                    .build();
        }
        mBinding.recyclerView.setPanelSwitchHelper(mHelper);
        super.show();
    }


    private void initView() {
        mBinding.title.setText("????????????dialog");
        mLinearLayoutManager = new LinearLayoutManager(getContext());
        mBinding.recyclerView.setLayoutManager(mLinearLayoutManager);
        mAdapter = new ChatAdapter(getContext(), 50);
        mBinding.recyclerView.setAdapter(mAdapter);
        mBinding.send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = mBinding.editText.getText().toString();
                if (TextUtils.isEmpty(content)) {
                    Toast.makeText(getContext(), "??????????????????", Toast.LENGTH_SHORT).show();
                    return;
                }
                mAdapter.insertInfo(ChatInfo.CREATE(content));
                mBinding.editText.setText(null);
                scrollToBottom();
            }
        });
    }

    private void scrollToBottom() {
        mLinearLayoutManager.scrollToPosition(mAdapter.getItemCount() - 1);
    }

    @Override
    public boolean onKey(@Nullable DialogInterface dialog, int keyCode, @NotNull KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
            if (mHelper != null && mHelper.hookSystemBackByPanelSwitcher()) {
                return true;
            }
        }
        return false;
    }
}
