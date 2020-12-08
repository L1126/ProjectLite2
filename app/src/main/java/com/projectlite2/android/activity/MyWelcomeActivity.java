package com.projectlite2.android.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.projectlite2.android.R;
import com.projectlite2.android.app.MyApplication;
import com.projectlite2.android.ui.GuideFirstFragment;
import com.projectlite2.android.ui.GuideSecondFragment;
import com.projectlite2.android.ui.GuideThirdFragment;
import com.projectlite2.android.utils.UserInfoSaveSharedPreference;
import com.stephentuso.welcome.BasicPage;
import com.stephentuso.welcome.FragmentWelcomePage;
import com.stephentuso.welcome.ParallaxPage;
import com.stephentuso.welcome.TitlePage;
import com.stephentuso.welcome.WelcomeActivity;
import com.stephentuso.welcome.WelcomeConfiguration;
import com.stephentuso.welcome.WelcomeHelper;
import com.stephentuso.welcome.WelcomePage;
import com.stephentuso.welcome.WelcomeUtils;

import static com.stephentuso.welcome.WelcomeConfiguration.BottomLayout.STANDARD;
import static com.stephentuso.welcome.WelcomeConfiguration.BottomLayout.STANDARD_DONE_IMAGE;

public class MyWelcomeActivity extends WelcomeActivity  {

    @Override
    protected WelcomeConfiguration configuration() {
        return new WelcomeConfiguration.Builder(this)
                .defaultTitleTypefacePath("Montserrat-Bold.ttf")
                .defaultHeaderTypefacePath("Montserrat-Bold.ttf")


                .page(new BasicPage(R.drawable.img_guide_1_min,
                        "项目讯息，触手可及",
                        "日程安排、人员变动、任务进度，随时随地轻松点击。")
                        .background(R.color.white)
                )

                .page(new BasicPage(R.drawable.img_guide_2_min,
                        "创意迸发，尽在规划",
                        "看似偶然的灵感，得益于项目如大树般牢牢扎根，蓬勃生长。")
                        .background(R.color.white)
                )


                .page(new BasicPage(R.drawable.img_guide_3_min,
                        "资源人才，不再错过",
                        "云端平台共享小组资源，名片夹标记重要伙伴，不再担心遗忘和丢失，专注初心更好地实施。")
                        .background(R.color.white)
                )

//
//                .page(new FragmentWelcomePage() {
//                    @Override
//                    protected GuideFirstFragment fragment() {
//                        return new GuideFirstFragment();
//                    }
//                }.background(R.color.white))
//                .page(new FragmentWelcomePage() {
//                    @Override
//                    protected GuideSecondFragment fragment() {
//                        return new GuideSecondFragment();
//                    }
//                }.background(R.color.white))
//                .page(new FragmentWelcomePage() {
//                    @Override
//                    protected GuideThirdFragment fragment() {
//                        return new GuideThirdFragment();
//                    }
//                }.background(R.color.white))


                .bottomLayout(STANDARD_DONE_IMAGE)
                .swipeToDismiss(true)
                .exitAnimation(android.R.anim.fade_out)
                .build();
    }

    public static String welcomeKey() {
        return "WelcomeScreen";
    }


    @Override
    protected void completeWelcomeScreen() {
        super.completeWelcomeScreen();
        UserInfoSaveSharedPreference.setNotFirstUse(MyApplication.getContext(),true);
        Intent it=new Intent(MyApplication.getContext(),LoginActivity.class);
        startActivity(it);

    }





}