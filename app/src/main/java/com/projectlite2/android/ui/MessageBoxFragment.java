package com.projectlite2.android.ui;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.projectlite2.android.R;
import com.projectlite2.android.activity.ChatRoomActivity;
import com.projectlite2.android.activity.SearchActivity;
import com.projectlite2.android.activity.WorkPlaceActivity;
import com.projectlite2.android.app.MyApplication;

import java.util.ArrayList;

public class MessageBoxFragment extends Fragment {

    private Toolbar toolBar;
    private TextView txtTitle;

    private View contextView;// 总视图
    private TabLayout tabLayout;
    private ViewPager viewpager;
    ArrayList fragmentList = new ArrayList<Fragment>();
    String[] temp = {
//            this.getResources().getString(R.string.string_tab_massage_new_message),
//           this. getResources().getString(R.string.string_tab_massage_project_invite)
            "123","456"
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        contextView = inflater.inflate(R.layout.message_box_fragment, container, false);

        toolBar = contextView.findViewById(R.id.toolBar);
        txtTitle = contextView.findViewById(R.id.txtPageTitle);

        txtTitle.setText(R.string.string_menu_message_box);
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

        setHasOptionsMenu(true);


        tabLayout = contextView.findViewById(R.id.tabLayoutMessage);
        viewpager = contextView.findViewById(R.id.viewPagerMessage);
        return contextView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MessageBoxFragment.MPagerAdapter mPagerAdapter = new MessageBoxFragment.MPagerAdapter(getChildFragmentManager());
        initFragment();
        tabLayout.setupWithViewPager(viewpager);
        viewpager.setAdapter(mPagerAdapter);
    }

    private void initFragment() {
        fragmentList.add(new MessageNewsListFragment());
        fragmentList.add(new InviteListFragment());
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
            if (position == 0) {
                return getActivity().getResources().getString(R.string.string_tab_massage_new_message);
            }else{
                return getActivity().getResources().getString(R.string.string_tab_massage_project_invite);
            }

        }
    }


}