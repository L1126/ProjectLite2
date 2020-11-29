package com.projectlite2.android.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.projectlite2.android.R;
import com.projectlite2.android.activity.GuideActivity;
import com.projectlite2.android.activity.ProjectDetailActivity;
import com.projectlite2.android.activity.SearchActivity;
import com.projectlite2.android.adapter.ProjectCardAdapter;
import com.projectlite2.android.app.MyApplication;
import com.projectlite2.android.model.ProjectCard;
import com.projectlite2.android.utils.IKotlinItemClickListener;

import java.util.ArrayList;

public class HomePageFragment extends Fragment {

    View mView;
    Toolbar toolBar;
    TextView txtTitle;
    RecyclerView mRecyclerView;
    ProjectCardAdapter mAdapter;

    private ArrayList<ProjectCard> projectList = new ArrayList<ProjectCard>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.home_page_fragment, container, false);

        toolBar = mView.findViewById(R.id.toolBar);
        txtTitle = mView.findViewById(R.id.txtPageTitle);
        toolBar.inflateMenu(R.menu.menu_home_page);
        toolBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.btnSearch:
                         Intent intent = new Intent(MyApplication.getContext(), SearchActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.btnNewProject:
//                        Intent intent = new Intent(MyApplication.getContext(), SearchActivity.class);
//                        startActivity(intent);
                    default:
                        break;
                }
                return true;
            }

        });
        txtTitle.setText(R.string.string_menu_home_page);
        //toolBar.setTitle(R.string.string_menu_home_page);
        setHasOptionsMenu(true);


        return mView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //initRV();

        addProjects();

        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView = mView.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new ProjectCardAdapter(projectList);

        mRecyclerView.setAdapter(mAdapter);


        //项目树的点击事件监听
        mAdapter.setOnKotlinItemClickListener(new IKotlinItemClickListener() {
            //初始化列表数据
            @Override
            public void onItemClickListener(int position) {
                //  MyApplication.showToast(projectList.get(position).getName());
                // 跳转fragment
//                FragmentManager manager=getFragmentManager();
//                FragmentTransaction ft;
//                NodeDetailFragment mNodeDetailFragment = new NodeDetailFragment();
//                ft = manager.beginTransaction();
//                //当前的fragment会被mNodeDetailFragment替换
//                ft.replace(R.id.rootLayout, mNodeDetailFragment);
//                ft.addToBackStack(null);
//                ft.commit();

                //跳转activity
                Intent it;
                it = new Intent(getContext(), ProjectDetailActivity.class);//启动GuideActivity
                startActivity(it);


                //可以打开私聊界面
//                LCChatKit.getInstance().open("Tom", new AVIMClientCallback() {
//                    @Override
//                    public void done(AVIMClient avimClient, AVIMException e) {
//                        if (null == e) {
//                            getActivity().finish();
//                            Intent intent = new Intent(MyApplication.getContext(), LCIMConversationActivity.class);
//                            intent.putExtra(LCIMConstants.PEER_ID, "Jerry");
//                            startActivity(intent);
//                        } else {
//                            Toast.makeText(MyApplication.getContext(), e.toString(), Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
            }
        });


    }

    /**
     * 加载标题栏菜单项
     *
     * @param menu
     * @param inflater
     */
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_home_page, menu);
    }

//    /**
//     * 标题栏菜单按钮点击
//     *
//     * @param item
//     * @return
//     */
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.btnSearch:
//                Log.d("123", "onOptionsItemSelected: ");
//                break;
//            case R.id.btnNewProject:
//                Log.d("123", "onOptionsItemSelected: ");
//                break;
//            default:
//                break;
//        }
//
//
//        return super.onOptionsItemSelected(item);
//    }


    private void addProjects() {
        projectList.add(new ProjectCard("信息与交互设计", true, 25));
        projectList.add(new ProjectCard("用户体验设计", false, 30));
        projectList.add(new ProjectCard("产品设计方法学", false, 90));
        projectList.add(new ProjectCard("交互设计专题（一）", true, 10));
        projectList.add(new ProjectCard("产品设计专题", false, 60));
        projectList.add(new ProjectCard("信息与交互设计", true, 25));
        projectList.add(new ProjectCard("用户体验设计", false, 20));
        projectList.add(new ProjectCard("产品设计方法学", false, 90));
        projectList.add(new ProjectCard("交互设计专题（一）", true, 10));
        projectList.add(new ProjectCard("产品设计专题", false, 60));
    }
}





