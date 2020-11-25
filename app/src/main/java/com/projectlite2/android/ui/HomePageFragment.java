package com.projectlite2.android.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.projectlite2.android.R;
import com.projectlite2.android.adapter.ProjectCardAdapter;
import com.projectlite2.android.app.MyApplication;
import com.projectlite2.android.model.ProjectCard;
import com.projectlite2.android.utils.IKotlinItemClickListener;
import com.projectlite2.android.generated.callback.OnClickListener;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import cn.leancloud.chatkit.LCChatKit;
import cn.leancloud.chatkit.LCChatKitUser;
import cn.leancloud.chatkit.activity.LCIMConversationActivity;
import cn.leancloud.chatkit.utils.LCIMConstants;
import cn.leancloud.im.v2.AVIMChatRoom;
import cn.leancloud.im.v2.AVIMConversation;
import cn.leancloud.im.v2.AVIMException;
import cn.leancloud.im.v2.callback.AVIMConversationCreatedCallback;

public class HomePageFragment extends Fragment {

    View mView;
    Toolbar toolBar;
    RecyclerView mRecyclerView;
    ProjectCardAdapter mAdapter;

    private ArrayList<ProjectCard> projectList = new ArrayList<ProjectCard>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.home_page_fragment, container, false);
        toolBar = mView.findViewById(R.id.toolBar);
        toolBar.inflateMenu(R.menu.menu_add_project);
        return mView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
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


        //项目卡片的点击事件监听
        mAdapter.setOnKotlinItemClickListener(new IKotlinItemClickListener() {
        //初始化列表数据
            @Override
            public void onItemClickListener(int position) {
               MyApplication.showToast(projectList.get(position).getName());
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

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_add_project,menu);
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
}