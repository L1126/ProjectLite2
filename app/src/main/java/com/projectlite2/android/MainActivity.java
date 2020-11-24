package com.projectlite2.android;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.NavController;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

import kotlin.jvm.internal.Intrinsics;
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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //应该保留标题栏 标题栏自定义美化
        Objects.requireNonNull(getSupportActionBar()).hide();
        //  设置顶部状态栏透明
        //VUI getWindow().setStatusBarColor(Color.TRANSPARENT);
        setContentView(R.layout.activity_main);

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
    }

}