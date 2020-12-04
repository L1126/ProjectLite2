package com.projectlite2.android.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.projectlite2.android.R;

import java.util.ArrayList;

public class WPFileListFragment extends Fragment {

    private View contextView;// 总视图
    private TabLayout tabLayout;
    private ViewPager viewpager;
    ArrayList filesList = new ArrayList<Fragment>();
    String[] temp = {"调研组","原型组","代码组","文档组","公共"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        contextView = inflater.inflate(R.layout.workplace_file_frag, container, false);
        tabLayout = contextView.findViewById(R.id.tabWorkplaceFile);
        viewpager = contextView.findViewById(R.id.viewPagerWorkplaceFile);
        return contextView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        WPFileListFragment.MPagerAdapter mPagerAdapter = new WPFileListFragment.MPagerAdapter(getChildFragmentManager());
        initFragment();
        tabLayout.setupWithViewPager(viewpager);
        viewpager.setAdapter(mPagerAdapter);
    }

    private void initFragment() {
        filesList.add(new WPFileGroupListFragment(0));
        filesList.add(new WPFileGroupListFragment(1));
        filesList.add(new WPFileGroupListFragment(2));
        filesList.add(new WPFileGroupListFragment(3));
        filesList.add(new WPFileGroupListFragment(4));

    }

    class MPagerAdapter extends FragmentPagerAdapter {


        public MPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return (Fragment) filesList.get(position);
        }

        @Override
        public int getCount() {
            return filesList.size();
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            return super.instantiateItem(container, position);
        }

        //返回tablayout的标题文字;
        @Override
        public CharSequence getPageTitle(int position) {
            return temp[position];
        }
    }
}

