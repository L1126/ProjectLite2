package com.projectlite2.android

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_guide.*

class GuideActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guide)
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
        TabLayoutMediator(tabLayout,viewPager){tab,position->
            when(position){
                0->tab.text="1"
                1->tab.text="2"
                else -> tab.text="3"
            }
        }.attach()
    }

}