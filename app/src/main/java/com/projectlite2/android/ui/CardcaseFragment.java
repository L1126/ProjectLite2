package com.projectlite2.android.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;


import com.projectlite2.android.R;
import com.projectlite2.android.activity.SearchActivity;
import com.projectlite2.android.app.MyApplication;

public class CardcaseFragment extends Fragment {

    private View contextView;// 总视图
    Toolbar toolBar;
    TextView txtTitle;
    private TabLayout tabLayout;
    private ViewPager viewpager;
    ArrayList fragmentList = new ArrayList<Fragment>();
    String[] temp = {"myCard","newFriend"};


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        contextView = inflater.inflate(R.layout.cardcase_fragment, container, false);

        toolBar = contextView.findViewById(R.id.toolBar);
        txtTitle = contextView.findViewById(R.id.txtPageTitle);
        txtTitle.setText(R.string.string_menu_cardcase);

        toolBar.inflateMenu(R.menu.menu_message_card);

        //  标题栏菜单点击
        toolBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {

            @SuppressLint("ResourceAsColor")
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    //  点击搜索
                    case R.id.btnSearch:
                        Intent intent1 = new Intent(MyApplication.getContext(), SearchActivity.class);
                        startActivity(intent1);
                        break;

                    default:
                        break;
                }
                return true;
            }
        });

        tabLayout = contextView.findViewById(R.id.tabLayoutCard);
        viewpager = contextView.findViewById(R.id.viewPagerCard);
        return contextView;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MPagerAdapter mPagerAdapter = new MPagerAdapter(getChildFragmentManager());
        initFragment();
        tabLayout.setupWithViewPager(viewpager);
        viewpager.setAdapter(mPagerAdapter);
    }

    private void initFragment() {
        fragmentList.add(new ContactListFragment(ContactListFragment.setStyleMyContacts()));
        fragmentList.add(new ContactListFragment(ContactListFragment.setStyleNewFriends()));
    }


    class MPagerAdapter extends FragmentPagerAdapter {


        public MPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return (Fragment) fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
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