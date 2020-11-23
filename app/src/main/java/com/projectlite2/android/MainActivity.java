package com.projectlite2.android;

<<<<<<< HEAD
import android.content.Intent;
=======
import android.graphics.Color;
>>>>>>> origin/dev-c
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

<<<<<<< HEAD
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import cn.leancloud.chatkit.LCChatKit;
import cn.leancloud.chatkit.LCChatKitUser;
import cn.leancloud.chatkit.activity.LCIMConversationActivity;
import cn.leancloud.chatkit.utils.LCIMConstants;
import cn.leancloud.im.v2.AVIMChatRoom;
import cn.leancloud.im.v2.AVIMClient;
import cn.leancloud.im.v2.AVIMConversation;
import cn.leancloud.im.v2.AVIMException;
import cn.leancloud.im.v2.callback.AVIMClientCallback;
import cn.leancloud.im.v2.callback.AVIMConversationCreatedCallback;
=======
import java.util.Objects;
>>>>>>> origin/dev-c

import static androidx.navigation.Navigation.findNavController;

public class MainActivity extends AppCompatActivity{
    private static Logger logger = Logger.getLogger(MainActivity.class.getSimpleName());
    TextView textName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //应该保留标题栏 标题栏自定义美化
        Objects.requireNonNull(getSupportActionBar()).hide();

        //  设置顶部状态栏透明
       //VUI getWindow().setStatusBarColor(Color.TRANSPARENT);


        setContentView(R.layout.activity_main);
        //  导航栏跳转配置
        BottomNavigationView bottomNav=findViewById(R.id.navBottom);
        NavController navController=findNavController(this,R.id.navCtrlFragment);
        AppBarConfiguration appBarConfig=new AppBarConfiguration.Builder(bottomNav.getMenu()).build();
        NavigationUI.setupActionBarWithNavController(this,navController,appBarConfig);
        NavigationUI.setupWithNavController(bottomNav,navController);

        //获取项目卡片按钮组件
        View cardView = View.inflate(getApplicationContext(),R.layout.project_card_item,null);
    }

    //项目树点击事件
    public void TreeClick(View cardView){
        switch (cardView.getId()){
            case R.id.btnProjectTree:
                Log.d("TAG","ProjectTree");
                break;
        }
    }

    //聊天点击事件
    public void ChatClick(View cardView){
        textName = cardView.findViewById(R.id.txtProjectName);
        String sNameChat = textName.getText().toString();
        switch (sNameChat){
            case "信息与交互设计":
                Log.d("Chat","信息与交互设计");
                gotoSquareConversation();
                break;
            case "用户体验设计":
                Log.d("Chat","用户体验设计");
                break;
            case "产品设计方法学":
                Log.d("Chat","产品设计方法学");
                break;
            case "交互设计专题（一）":
                Log.d("Chat","交互设计专题（一）");
                break;
            case "产品设计专题":
                Log.d("Chat","产品设计专题");
                break;
        }
    }

    //进入聊天界面
    private void gotoSquareConversation() {

        AVIMClient tom = AVIMClient.getInstance("Tom");
        tom.createConversation(Arrays.asList("Jerry","Mary"), "Tom & Jerry & friends", null,
        new AVIMConversationCreatedCallback() {
            @Override
            public void done(AVIMConversation conversation, AVIMException e) {
                if (e == null) {
                    // 创建成功
                    Log.d("TAG","012100");
                    Intent intent = new Intent(MainActivity.this, LCIMConversationActivity.class);
                    intent.putExtra(LCIMConstants.CONVERSATION_ID, conversation.getConversationId());
                    startActivity(intent);
                }
            }
        });
    }

}