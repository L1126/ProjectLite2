package com.projectlite2.android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.projectlite2.android.R;
import com.projectlite2.android.app.MyApplication;
import com.projectlite2.android.ui.CardcaseFragment;
import com.projectlite2.android.ui.HomePageFragment;
import com.projectlite2.android.ui.MessageBoxFragment;
import com.projectlite2.android.ui.MyProfileFragment;

import cn.leancloud.AVInstallation;
import cn.leancloud.AVObject;
import cn.leancloud.AVUser;
import cn.leancloud.push.PushService;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import nl.joery.animatedbottombar.AnimatedBottomBar;

public class MainActivity extends AppCompatActivity {

    /**
     * viewPager页面数
     */
    private static final int NUM_PAGES = 4;

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager2 mViewPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private FragmentStateAdapter mPagerAdapter;

    /**
     * animatedBottomBar组件实例
     */
    AnimatedBottomBar mAniBottomBar;

    /**
     * tabLayout组件实例
     */
    TabLayout mTabLayout;

    /**
     * toolbar实例
     */
    Toolbar mToolBar;
    //是否使用特殊的标题栏背景颜色，android5.0以上可以设置状态栏背景色，如果不使用则使用透明色值
    protected boolean useThemeStatusBarColor = false;
    //是否使用状态栏文字和图标为暗色，如果状态栏采用了白色系，则需要使状态栏和图标为暗色，android6.0以上可以设置
    protected boolean useStatusBarColor = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //如果有的话，隐藏actionbar
        //getSupportActionBar().hide();
        //  设置顶部状态栏透明
        //getWindow().setStatusBarColor(Color.TRANSPARENT);

        setContentView(R.layout.activity_main);
//        setStatusBar();

        //  导航栏跳转配置
//        BottomNavigationView bottomNav=findViewById(R.id.navBottom);
//        NavController navController=findNavController(this,R.id.navCtrlFragment);
//        AppBarConfiguration appBarConfig=new AppBarConfiguration.Builder(bottomNav.getMenu()).build();
//        NavigationUI.setupActionBarWithNavController(this,navController,appBarConfig);
//        NavigationUI.setupWithNavController(bottomNav,navController);

        //  检查登陆状态，配置installationId
        ConfigCurrentUserAndInstallationId();


        mAniBottomBar = findViewById(R.id.bottomBar);
        mTabLayout = findViewById(R.id.tabLayout);
        mViewPager = findViewById(R.id.viewPager);

        mViewPager.setAdapter(new FragmentStateAdapter(this) {

            @Override
            public int getItemCount() {
                return 4;
            }

            @NonNull
            @Override
            public Fragment createFragment(int position) {
                Fragment fa;
                switch (position) {
                    case 0:
                        fa = (Fragment) (new HomePageFragment());
                        break;
                    case 1:
                        fa = (Fragment) (new MessageBoxFragment());
                        break;
                    case 2:
                        fa = (Fragment) (new CardcaseFragment());
                        break;
                    default:
                        fa = (Fragment) (new MyProfileFragment());
                }
                return fa;
            }
        });

        //  关联tabLayout和viewPager
        new TabLayoutMediator(mTabLayout, mViewPager,
                (tab, position) -> tab.setText("OBJECT " + (position + 1))
        ).attach();

        //  关联animatedBottomBar和viewPager
        mAniBottomBar.setupWithViewPager2(mViewPager);

        //  关闭viewPager滑动
        mViewPager.setUserInputEnabled(false);


    }

    /**
     *  检查登陆状态，配置installationId
     */
    private void ConfigCurrentUserAndInstallationId() {
        AVUser currentUser = AVUser.getCurrentUser();
        if (currentUser != null) {
            //  当前为用户登录状态，配置该用户的installationId

            Log.d("mytest", "ConfigCurrentUserAndInstallationId: " + "当前用户：" + currentUser.getMobilePhoneNumber());

            //  配置installationId
            AVInstallation.getCurrentInstallation().saveInBackground().subscribe(new Observer<AVObject>() {
                @Override
                public void onSubscribe(Disposable d) {
                }

                @Override
                public void onNext(AVObject avObject) {
                    // 关联 installationId 到用户表等操作。
                    MyApplication.MY_INSTALLATION_ID = AVInstallation.getCurrentInstallation().getInstallationId();
                    Log.d("mytest", "关联 installationId   保存成功：" + MyApplication.MY_INSTALLATION_ID);
                    currentUser.put("installationId", MyApplication.MY_INSTALLATION_ID);
                    currentUser.saveInBackground().subscribe(new Observer<AVObject>() {
                        public void onSubscribe(Disposable disposable) {
                        }

                        public void onNext(AVObject todo) {
                            Log.d("mytest", "currentUser save installationId success");
                        }

                        public void onError(Throwable throwable) {
                            MyApplication.ToastyError("error");
                            Log.d("mytest", "currentUser save installationId  Error: " + throwable);
                            // 异常处理
                        }

                        public void onComplete() {
                        }
                    });

                }

                @Override
                public void onError(Throwable e) {
                    Log.d("mytest", "关联 installationId   保存失败，错误信息：" + e.getMessage());

                }

                @Override
                public void onComplete() {
                }
            });

            // 启动推送服务 设置默认打开的 Activity
            PushService.setDefaultPushCallback(this, PushTestActivity.class);

        } else {
            //  若当前为无用户登陆状态，则跳转到登录界面
            MyApplication.ToastyError("当前无用户");
            Intent intent = new Intent(MyApplication.getContext(), LoginActivity.class);
            startActivity(intent);
            finish();
        }

    }


}