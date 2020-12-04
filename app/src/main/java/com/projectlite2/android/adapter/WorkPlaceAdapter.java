package com.projectlite2.android.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.projectlite2.android.ui.WPFileListFragment;
import com.projectlite2.android.ui.WPListFragment;
import com.projectlite2.android.ui.WPRecycbinFragment;

public class WorkPlaceAdapter extends FragmentPagerAdapter {

    private String[] mTitles = new String[]{"项目文件", "学习资源", "会议记录","回收站"};

    public WorkPlaceAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new WPFileListFragment();
        } else if (position == 1) {
            return new WPListFragment(WPListFragment.workPlaceStudy());
        }else if (position == 2){
            return new WPListFragment(WPListFragment.workPlaceMeeting());
        }
        return new WPRecycbinFragment();
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    //ViewPager与TabLayout绑定后，这里获取到PageTitle就是Tab的Text
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
