package com.projectlite2.android.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.projectlite2.android.R;
import com.projectlite2.android.activity.SearchActivity;
import com.projectlite2.android.app.MyApplication;

import java.util.ArrayList;

public class CardcaseFragment extends Fragment {

    private View contextView;// 总视图
    Toolbar toolBar;
    TextView txtTitle;
    private TabLayout tabLayout;
    private ViewPager viewpager;
    ArrayList fragmentList = new ArrayList<Fragment>();
    String[] temp = {"myCard", "newFriend"};


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        contextView = inflater.inflate(R.layout.cardcase_fragment, container, false);

        toolBar = contextView.findViewById(R.id.toolBar);
        txtTitle = contextView.findViewById(R.id.txtPageTitle);
        txtTitle.setText(R.string.string_menu_cardcase);

        toolBar.inflateMenu(R.menu.menu_card_case_page);

        /**
         * 标题栏搜索按钮点击
         */
        toolBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                Intent intent1 = new Intent(MyApplication.getContext(), SearchActivity.class);
                SearchActivity.SetSearchType(SearchActivity.SearchType.user);
                startActivityForResult(intent1, 2);

                return true;
            }
        });


        tabLayout = contextView.findViewById(R.id.tabLayoutCard);
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
        fragmentList.add(new ContactListFragment(getActivity(),ContactListFragment.setStyleMyContacts()));
        fragmentList.add(new ContactListFragment(getActivity(),ContactListFragment.setStyleNewFriends()));
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
           if(position==0){
               return getActivity().getResources().getString(R.string.string_tab_cardcase_saved_card);
           }else{
               return getActivity().getResources().getString(R.string.string_tab_cardcase_push_card);
           }
        }
    }

}