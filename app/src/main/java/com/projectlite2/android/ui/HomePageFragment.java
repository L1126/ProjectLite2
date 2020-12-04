package com.projectlite2.android.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.interfaces.OnSelectListener;
import com.projectlite2.android.R;
import com.projectlite2.android.activity.CreateProjectActivity;
import com.projectlite2.android.activity.ProjectDetailActivity;
import com.projectlite2.android.activity.SearchActivity;
import com.projectlite2.android.adapter.ProjectCardAdapter;
import com.projectlite2.android.app.MyApplication;
import com.projectlite2.android.model.ProjectCard;
import com.projectlite2.android.utils.OnItemClickListenerPlus;

import java.util.ArrayList;

import cn.leancloud.AVUser;
import cn.leancloud.chatkit.LCChatKit;
import cn.leancloud.chatkit.activity.LCIMConversationActivity;
import cn.leancloud.chatkit.utils.LCIMConstants;
import cn.leancloud.im.v2.AVIMClient;
import cn.leancloud.im.v2.AVIMException;
import cn.leancloud.im.v2.callback.AVIMClientCallback;

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
        View mNewProjectView = toolBar.findViewById(R.id.btnNewProject);
        //  标题栏菜单点击
        toolBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {

            String[] mMenuTitles = new String[]{
                    getResources().getString(R.string.string_btn_menu_create_project),
                    getResources().getString(R.string.string_btn_menu_join_project)
            };

            int[] mMenuIcons = new int[]{
                    R.drawable.ic_outline_create_new_folder_24,
                    R.drawable.ic_outline_add_box_24
            };

            @SuppressLint("ResourceAsColor")
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    //  点击搜索
                    case R.id.btnSearch:
                        Intent intent1 = new Intent(MyApplication.getContext(), SearchActivity.class);
                        startActivity(intent1);
                        break;
                    //  点击创建项目
                    case R.id.btnNewProject:

                        //  弹出新项目菜单POPUP
                        new XPopup.Builder(getContext())
                                .atView(mNewProjectView)
                                // 依附于所点击的View，内部会自动判断在上方或者下方显示
                                .asAttachList(
                                        mMenuTitles,
                                        mMenuIcons,
                                        new OnSelectListener() {
                                            @Override
                                            public void onSelect(int position, String text) {
                                                switch (position) {
                                                    case 0:
                                                        Intent it = new Intent(MyApplication.getContext(), CreateProjectActivity.class);
                                                        startActivity(it);
                                                        break;
                                                    case 2:
                                                        MyApplication.ToastyInfo("2");
                                                        break;
                                                    default:
                                                        break;
                                                }
                                            }
                                        })
                                .show();


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


        //点击事件监听
        mAdapter.setOnKotlinItemClickListener(new OnItemClickListenerPlus() {
            @Override
            public void onClick(@org.jetbrains.annotations.Nullable View item, int position, int which) {
                switch (which) {
                    case R.id.btnProjectTree: {
                        MyApplication.ToastyInfo("tree");
                        //跳转activity
                        Intent it;
                        it = new Intent(getContext(), ProjectDetailActivity.class);//启动ProjectDetailActivity
                        startActivity(it);
                        break;
                    }
                    default: {
                        MyApplication.ToastyInfo("card");

                        //    可以打开私聊界面，但是打开私聊会结束Home的activity，在私聊点击返回就会退出程序
                        LCChatKit.getInstance().open("Tom", new AVIMClientCallback() {
                            @Override
                            public void done(AVIMClient avimClient, AVIMException e) {
                                if (null == e) {
                                    getActivity().finish();
                                    Intent intent = new Intent(MyApplication.getContext(), LCIMConversationActivity.class);
                                    intent.putExtra(LCIMConstants.PEER_ID, "Jerry");
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(MyApplication.getContext(), e.toString(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                        break;
                    }
                }
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

    private void OnMenuItemClick(int position) {
        switch (position) {
            case 1:
                MyApplication.ToastyInfo("1");
                break;
            case 2:
                MyApplication.ToastyInfo("2");
                break;
            default:
                break;
        }
    }
}





