package com.projectlite2.android.activity

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.projectlite2.android.ui.GuideFirstFragment
import com.projectlite2.android.ui.GuideSecondFragment
import com.projectlite2.android.ui.GuideThirdFragment
import com.projectlite2.android.R
import kotlinx.android.synthetic.main.activity_guide.*
import kotlin.concurrent.thread


class GuideActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guide)

//        设置顶部状态栏透明
        window.statusBarColor= Color.TRANSPARENT

        viewPager.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount() = 3
            override fun createFragment(position: Int): Fragment {
                return when (position) {
                    0 -> GuideFirstFragment()
                    1 -> GuideSecondFragment()
                    else -> GuideThirdFragment()
                }
            }
        }
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "1"
                1 -> tab.text = "2"
                else -> tab.text = "3"
            }
        }.attach()


        viewPager?.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                indicatorView.selection=position
            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
            }

            override fun onPageScrolled(position: Int,
                                        positionOffset: Float,
                                        positionOffsetPixels: Int) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }
        })


    }


}

