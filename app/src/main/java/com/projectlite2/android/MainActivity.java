package com.projectlite2.android;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import cn.leancloud.chatkit.LCChatKit;
import cn.leancloud.chatkit.LCChatKitUser;
import cn.leancloud.chatkit.activity.LCIMConversationActivity;
import cn.leancloud.chatkit.utils.LCIMConstants;
import cn.leancloud.im.v2.AVIMChatRoom;
import cn.leancloud.im.v2.AVIMConversation;
import cn.leancloud.im.v2.AVIMException;
import cn.leancloud.im.v2.callback.AVIMConversationCreatedCallback;

import static androidx.navigation.Navigation.findNavController;

public class MainActivity extends AppCompatActivity{
    private static Logger logger = Logger.getLogger(MainActivity.class.getSimpleName());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //  getSupportActionBar().hide();

        setContentView(R.layout.activity_main);
        //  导航栏跳转配置
        BottomNavigationView bottomNav=findViewById(R.id.navBottom);
        NavController navController=findNavController(this,R.id.navCtrlFragment);
        AppBarConfiguration appBarConfig=new AppBarConfiguration.Builder(bottomNav.getMenu()).build();
        NavigationUI.setupActionBarWithNavController(this,navController,appBarConfig);
        NavigationUI.setupWithNavController(bottomNav,navController);

        //获取项目卡片按钮组件
        View cardView = View.inflate(getApplicationContext(),R.layout.project_card_item,null);
        ImageButton btn = (ImageButton) cardView.findViewById(R.id.btnProjectTree);
    }

    //项目树点击事件
    public void otherClick(View cardView){
        switch (cardView.getId()){
            case R.id.btnProjectTree:
                Log.d("TAG","000");
                break;
        }
    }

    private void gotoSquareConversation() {
        List<LCChatKitUser> userList = CustomUserProvider.getInstance().getAllUsers();
        List<String> idList = new ArrayList<>();
        for (LCChatKitUser user : userList) {
            idList.add(user.getUserId());
        }

        //进入聊天界面
        LCChatKit.getInstance().getClient().createChatRoom(
                idList, "", null, true, new AVIMConversationCreatedCallback() {
                    @Override
                    public void done(AVIMConversation avimConversation, AVIMException e) {
                        if (avimConversation instanceof AVIMChatRoom) {
                            Intent intent = new Intent(MainActivity.this, LCIMConversationActivity.class);
                            intent.putExtra(LCIMConstants.CONVERSATION_ID, avimConversation.getConversationId());
                            startActivity(intent);
                        } else {
                            logger.log(Level.WARNING, "createChatRoom is wrong!");
                        }
                    }
                });
    }

}