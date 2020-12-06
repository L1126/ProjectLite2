package com.projectlite2.android.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

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

    private LinearLayout linearLayout;
    private View lview;

    ArrayList filesList = new ArrayList<Fragment>();
    String[] temp = {"调研组","原型组","代码组","文档组","公共"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        contextView = inflater.inflate(R.layout.workplace_file_list_frag, container, false);
        tabLayout = contextView.findViewById(R.id.tabWorkplaceFile);
        viewpager = contextView.findViewById(R.id.viewPagerWorkplaceFile);

        return contextView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        WPFileListFragment.MPagerAdapter mPagerAdapter = new WPFileListFragment.MPagerAdapter(getChildFragmentManager());

        initFragment();

        linearLayout = getActivity().findViewById(R.id.toolBtnBar);
        lview = getActivity().findViewById(R.id.view5);

        tabLayout.setupWithViewPager(viewpager);
        viewpager.setAdapter(mPagerAdapter);

        linearLayout.setVisibility(View.VISIBLE);
        lview.setVisibility(View.VISIBLE);
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
        public Fragment getItem(int inposition) {
            return (Fragment) filesList.get(inposition);
        }

        @Override
        public int getCount() {
            return filesList.size();
        }

        @Override
        public Object instantiateItem(ViewGroup container, int inposition) {
            return super.instantiateItem(container, inposition);
        }

        //返回tablayout的标题文字;
        @Override
        public CharSequence getPageTitle(int inposition) {
            return temp[inposition];
        }
    }
}

