package com.projectlite2.android.ui;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.projectlite2.android.viewmodel.MessageBoxViewModel;
import com.projectlite2.android.R;

import java.util.ArrayList;

public class MessageBoxFragment extends Fragment {

//    private MessageBoxViewModel mViewModel;
//    public static MessageBoxFragment newInstance() {
//        return new MessageBoxFragment();
//    }

    private View contextView;// 总视图
    private TabLayout tabLayout;
    private ViewPager viewpager;
    ArrayList fragmentList = new ArrayList<Fragment>();
    String[] temp = {"News","New Projects"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        contextView = inflater.inflate(R.layout.message_box_fragment, container, false);
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