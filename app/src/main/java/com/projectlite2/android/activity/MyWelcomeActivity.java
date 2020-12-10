package com.projectlite2.android.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.projectlite2.android.R;
import com.stephentuso.welcome.BasicPage;
import com.stephentuso.welcome.TitlePage;
import com.stephentuso.welcome.WelcomeConfiguration;

public class WelcomeActivity extends WelcomeActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    @Override
    protected WelcomeConfiguration configuration() {
        return new WelcomeConfiguration.Builder(this)
<<<<<<< HEAD
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
=======
                .defaultBackgroundColor(R.color.background)
                .page(new TitlePage(R.drawable.logo,
                        "Title")
                )
                .page(new BasicPage(R.drawable.image,
                        "Header",
                        "More text.")
                        .background(R.color.red_background)
                )
                .page(new BasicPage(R.drawable.image,
                        "Lorem ipsum",
                        "dolor sit amet.")
>>>>>>> 2b65d275b8abc085741fbf892a09bc5366e41c3d
                )
                .swipeToDismiss(true)
                .build();
    }
}