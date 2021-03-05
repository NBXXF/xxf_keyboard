package com.example.demo;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.PopupWindow;
import android.widget.SimpleExpandableListAdapter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;

import com.effective.BuildConfig;
import com.effective.R;
import com.effective.databinding.ActivityMainLayoutBinding;
import com.example.demo.anno.ApiContentType;
import com.example.demo.anno.ApiResetType;
import com.example.demo.anno.ChatPageType;
import com.example.demo.scene.api.ContentActivity;
import com.example.demo.scene.api.CusPanelActivity;
import com.example.demo.scene.api.DefaultHeightPanelActivity;
import com.example.demo.scene.api.ResetActivity;
import com.example.demo.scene.chat.ChatActivity;
import com.example.demo.scene.chat.ChatCusContentScrollActivity;
import com.example.demo.scene.chat.ChatDialog;
import com.example.demo.scene.chat.ChatDialogFragment;
import com.example.demo.scene.chat.ChatFragmentActivity;
import com.example.demo.scene.chat.ChatPopupWindow;
import com.example.demo.scene.chat.ChatSuperActivity;
import com.example.demo.scene.feed.FeedActivity;
import com.example.demo.scene.feed.FeedDialogActivity;
import com.example.demo.scene.live.huya.PcHuyaLiveActivity;
import com.example.demo.scene.live.douyin.PhoneDouyinLiveActivity;
import com.example.demo.scene.video.BiliBiliSampleActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity{

    private ActivityMainLayoutBinding mBinding;

    final String activity_title = "聊天场景 Activity实现";
    final String activity_1 = "activity 无标题栏";
    final String activity_2 = "activity 有标题栏";
    final String activity_3 = "activity 自定义标题栏";
    final String activity_4 = "activity 有标题栏，状态栏着色";
    final String activity_5 = "activity 无标题栏，状态栏透明，不绘制到状态栏";
    final String activity_6 = "activity 无标题栏，状态栏透明，绘制到状态栏";

    final String fragment_title = "聊天场景 Fragment实现";
    final String fragment_1 = "fragment 无标题栏";
    final String fragment_2 = "fragment 自定义标题栏";
    final String fragment_3 = "fragment 自定义标题栏，状态栏着色";
    final String fragment_4 = "fragment 自定义标题栏，状态栏透明";

    final String window_title = "聊天场景 other window 实现";
    final String window_1 = "DialogFragment";
    final String window_2 = "PopupWindow";
    final String window_3 = "Dialog";

    final String scene_title = "扩展场景";
    final String scene_1 = "视频播放(优于b站)";
    final String scene_2 = "信息流评论(同微信朋友圈效果)";
//    final String scene_2_2 = "信息流评论(同微信朋友圈效果-非dialog)";
    final String scene_3 = "PC直播(优于虎牙效果)";
    final String scene_4 = "手机直播(优于抖音效果)";
    final String scene_5 = "复杂聊天场景";

    final String api_content_container_title = "api 内容容器及扩展";
    final String api_content_container_1 = "基于 LinearLayout 实现";
    final String api_content_container_2 = "基于 RelativeLayout 实现";
    final String api_content_container_3 = "基于 FrameLayout 实现";
    final String api_content_container_4 = "自定义布局实现";

    final String api_define_content_container_scroll_title = "api 内容容器内部布局干预滑动";
    final String api_define_content_container_scroll = "内容区域干预子View滑动行为";

    final String api_cus_panel_title = "api 面板扩展及默认高度设置";
    final String api_cus_panel = "自定义PanelView";
    final String api_cus_panel_height = "未获取输入法高度前高度兼容";

    final String api_reset_title = "api 自动隐藏软键盘/面板";
    final String api_reset_1 = "点击内容容器收起面板(默认处理)";
    final String api_reset_2 = "点击空白 View 收起面板";
    final String api_reset_3 = "点击原生 RecyclerView 收起面板";
    final String api_reset_4 = "点击自定义 RecyclerView 收起面板";
    final String api_reset_5 = "关闭点击内容容器收起面板";

    final String[] groupStrings = {
            activity_title,
            fragment_title,
            window_title,
            scene_title,
            api_content_container_title,
            api_define_content_container_scroll_title,
            api_cus_panel_title,
            api_reset_title};

    final String[][] childStrings = {
            {activity_1,activity_2,activity_3,activity_4,activity_5,activity_6},
            {fragment_1,fragment_2,fragment_3,fragment_4},
            {window_1,window_2,window_3},
            {scene_1,scene_2,scene_3,scene_4,scene_5},
            {api_content_container_1,api_content_container_2,api_content_container_3,api_content_container_4},
            {api_define_content_container_scroll},
            {api_cus_panel,api_cus_panel_height},
            {api_reset_1,api_reset_2,api_reset_3,api_reset_4,api_reset_5}
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main_layout);
        //mBinding.version.setText("version : " + BuildConfig.VERSION);

        final String TITLE = "TITLE";

        List<Map<String, String>> groupData = new ArrayList<Map<String, String>>();
        List<List<Map<String, String>>> childData = new ArrayList<List<Map<String, String>>>();
        for (int i = 0; i < groupStrings.length; i++) {
            Map<String, String> curGroupMap = new HashMap<String, String>();
            groupData.add(curGroupMap);
            curGroupMap.put(TITLE, groupStrings[i]);

            List<Map<String, String>> children = new ArrayList<Map<String, String>>();
            for (int j = 0; j < childStrings[i].length; j++) {
                Map<String, String> curChildMap = new HashMap<String, String>();
                children.add(curChildMap);
                curChildMap.put(TITLE, childStrings[i][j]);
            }
            childData.add(children);
        }

        SimpleExpandableListAdapter adapter = new SimpleExpandableListAdapter(this, groupData,
                R.layout.list_parent_title_layout,
                new String[]{TITLE}, new int[]{R.id.title},
                childData, R.layout.list_sub_title_layout,
                new String[]{TITLE}, new int[]{R.id.title});

        mBinding.list.setAdapter(adapter);
        mBinding.list.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                switch (childStrings[groupPosition][childPosition]){
                    case activity_1:{
                        ChatActivity.start(MainActivity.this, ChatPageType.DEFAULT);
                        break;
                    }
                    case activity_2:{
                        ChatActivity.start(MainActivity.this, ChatPageType.TITLE_BAR);
                        break;
                    }
                    case activity_3:{
                        ChatActivity.start(MainActivity.this, ChatPageType.CUS_TITLE_BAR);
                        break;
                    }
                    case activity_4:{
                        ChatActivity.start(MainActivity.this, ChatPageType.COLOR_STATUS_BAR);
                        break;
                    }
                    case activity_5:{
                        ChatActivity.start(MainActivity.this, ChatPageType.TRANSPARENT_STATUS_BAR);
                        break;
                    }
                    case activity_6:{
                        ChatActivity.start(MainActivity.this, ChatPageType.TRANSPARENT_STATUS_BAR_DRAW_UNDER);
                        break;
                    }

                    case fragment_1:{
                        ChatFragmentActivity.startFragment(MainActivity.this, ChatPageType.DEFAULT);
                        break;
                    }
                    case fragment_2:{
                        ChatFragmentActivity.startFragment(MainActivity.this, ChatPageType.TITLE_BAR);
                        break;
                    }
                    case fragment_3:{
                        ChatFragmentActivity.startFragment(MainActivity.this, ChatPageType.COLOR_STATUS_BAR);
                        break;
                    }
                    case fragment_4:{
                        ChatFragmentActivity.startFragment(MainActivity.this, ChatPageType.TRANSPARENT_STATUS_BAR);
                        break;
                    }

                    case window_1:{
                        DialogFragment dialogFragment = new ChatDialogFragment();
                        dialogFragment.showNow(getSupportFragmentManager(), "dialogFragment");
                        break;
                    }
                    case window_2:{
                        PopupWindow popupWindow = new ChatPopupWindow(MainActivity.this);
                        popupWindow.showAtLocation(mBinding.getRoot(), Gravity.NO_GRAVITY, 0, 0);
                        break;
                    }
                    case window_3:{
                        Dialog dialog = new ChatDialog(MainActivity.this);
                        dialog.show();
                        break;
                    }

                    case scene_1:{
                        startActivity(new Intent(MainActivity.this, BiliBiliSampleActivity.class));
                        break;
                    }
                    case scene_2:{
                        startActivity(new Intent(MainActivity.this, FeedDialogActivity.class));
                        break;
                    }
//                    case scene_2_2:{
//                        startActivity(new Intent(MainActivity.this, FeedActivity.class));
//                        break;
//                    }
                    case scene_3:{
                        startActivity(new Intent(MainActivity.this, PcHuyaLiveActivity.class));
                        break;
                    }
                    case scene_4:{
                        startActivity(new Intent(MainActivity.this, PhoneDouyinLiveActivity.class));
                        break;
                    }
                    case scene_5:{
                        startActivity(new Intent(MainActivity.this, ChatSuperActivity.class));
                        break;
                    }

                    case api_cus_panel:{
                        startActivity(new Intent(MainActivity.this, CusPanelActivity.class));
                        break;
                    }

                    case api_cus_panel_height:{
                        startActivity(new Intent(MainActivity.this, DefaultHeightPanelActivity.class));
                        break;
                    }

                    case api_define_content_container_scroll:{
                        ChatCusContentScrollActivity.start(MainActivity.this);
                        break;
                    }

                    case api_content_container_1:{
                        ContentActivity.start(MainActivity.this, ApiContentType.Linear);
                        break;
                    }
                    case api_content_container_2:{
                        ContentActivity.start(MainActivity.this, ApiContentType.Relative);
                        break;
                    }
                    case api_content_container_3:{
                        ContentActivity.start(MainActivity.this, ApiContentType.Frame);
                        break;
                    }
                    case api_content_container_4:{
                        ContentActivity.start(MainActivity.this, ApiContentType.CUS);
                        break;
                    }

                    case api_reset_1:{
                        ResetActivity.start(MainActivity.this, ApiResetType.ENABLE);
                        break;
                    }
                    case api_reset_2:{
                        ResetActivity.start(MainActivity.this, ApiResetType.ENABLE_EmptyView);
                        break;
                    }
                    case api_reset_3:{
                        ResetActivity.start(MainActivity.this, ApiResetType.ENABLE_RecyclerView);
                        break;
                    }
                    case api_reset_4:{
                        ResetActivity.start(MainActivity.this, ApiResetType.ENABLE_HookActionUpRecyclerview);
                        break;
                    }
                    case api_reset_5:{
                        ResetActivity.start(MainActivity.this, ApiResetType.DISABLE);
                        break;
                    }
                }
                return true;
            }
        });
    }
}
