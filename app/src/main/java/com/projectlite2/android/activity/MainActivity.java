package com.projectlite2.android.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.projectlite2.android.ui.MessageBoxFragment;
import com.projectlite2.android.ui.MyProfileFragment;
import com.projectlite2.android.R;
import com.projectlite2.android.ui.CardcaseFragment;
import com.projectlite2.android.ui.HomePageFragment;

import java.util.Objects;

import nl.joery.animatedbottombar.AnimatedBottomBar;

import static androidx.navigation.Navigation.findNavController;

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



        mAniBottomBar = findViewById(R.id.bottomBar);
        mTabLayout=findViewById(R.id.tabLayout);
        mViewPager=findViewById(R.id.viewPager);

        mViewPager.setAdapter(new FragmentStateAdapter(this){

            @Override
            public int getItemCount() {
                return 4;
            }

            @NonNull
            @Override
            public Fragment createFragment(int position) {
                Fragment fa;
                switch(position) {
                    case 0:
                        fa = (Fragment)(new HomePageFragment());
                        break;
                    case 1:
                        fa = (Fragment)(new MessageBoxFragment());
                        break;
                    case 2:
                        fa = (Fragment)(new CardcaseFragment());
                        break;
                    default:
                        fa = (Fragment)(new MyProfileFragment());
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

//    protected void setStatusBar() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0及以上
//            View decorView = getWindow().getDecorView();
//            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
//            decorView.setSystemUiVisibility(option);
//            //根据上面设置是否对状态栏单独设置颜色
//            if (useThemeStatusBarColor) {
//                getWindow().setStatusBarColor(getResources().getColor(R.color.black));//设置状态栏背景色
//            } else {
//                getWindow().setStatusBarColor(Color.TRANSPARENT);//透明
//            }
//        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4到5.0
//            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
//            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
//        } else {
//            Toast.makeText(this, "低于4.4的android系统版本不存在沉浸式状态栏", Toast.LENGTH_SHORT).show();
//        }
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && useStatusBarColor) {//android6.0以后可以对状态栏文字颜色和图标进行修改
//            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
//        }
//    }
}