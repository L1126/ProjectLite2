package com.projectlite2.android.activity;

import android.app.Activity;
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
import com.jaeger.library.StatusBarUtil;
import com.projectlite2.android.R;
import com.projectlite2.android.app.MyApplication;
import com.projectlite2.android.ui.CardcaseFragment;
import com.projectlite2.android.ui.HomePageFragment;
import com.projectlite2.android.ui.MessageBoxFragment;
import com.projectlite2.android.ui.MyProfileFragment;
import com.projectlite2.android.utils.CloudUtil;

import java.util.List;

import cn.leancloud.AVInstallation;
import cn.leancloud.AVObject;
import cn.leancloud.AVUser;
import cn.leancloud.im.v2.AVIMClient;
import cn.leancloud.im.v2.AVIMException;
import cn.leancloud.im.v2.callback.AVIMClientCallback;
import cn.leancloud.push.PushService;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import nl.joery.animatedbottombar.AnimatedBottomBar;

import static com.projectlite2.android.utils.CloudUtil.CLASS_USER.TABLE_FIELD_USER_ID;
import static com.projectlite2.android.utils.CloudUtil.CLASS_USER.TABLE_FIELD_USER_NAME;
import static com.projectlite2.android.utils.CloudUtil.CURRENT_USER.ConfigImClinet;

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

    /**
     * activity实例
     */
    Activity thisActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //  检查登陆状态，连接服务器，配置installationId
        CloudUtil.CURRENT_USER.ConfigCurrentUserAndInstallationId();

        //如果有的话，隐藏actionbar
//        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);


        thisActivity=this;

        //  导航栏跳转配置
//        BottomNavigationView bottomNav=findViewById(R.id.navBottom);
//        NavController navController=findNavController(this,R.id.navCtrlFragment);
//        AppBarConfiguration appBarConfig=new AppBarConfiguration.Builder(bottomNav.getMenu()).build();
//        NavigationUI.setupActionBarWithNavController(this,navController,appBarConfig);
//        NavigationUI.setupWithNavController(bottomNav,navController);




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
     * 解决Fragment中的onActivityResult()方法无响应问题。
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /**
         * 1.使用getSupportFragmentManager().getFragments()获取到当前Activity中添加的Fragment集合
         * 2.遍历Fragment集合，手动调用在当前Activity中的Fragment中的onActivityResult()方法。
         */

        Log.d("mytest", "onActivityResult: main activity");

        if (getSupportFragmentManager().getFragments() != null && getSupportFragmentManager().getFragments().size() > 0) {
            List<Fragment> fragments = getSupportFragmentManager().getFragments();
            for (Fragment mFragment : fragments) {
                mFragment.onActivityResult(requestCode, resultCode, data);
            }
        }

    }


}