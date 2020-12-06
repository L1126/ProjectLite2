package com.projectlite2.android.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
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
import com.projectlite2.android.activity.WorkPlaceActivity;
import com.projectlite2.android.adapter.ProjectCardAdapter;
import com.projectlite2.android.app.MyApplication;
import com.projectlite2.android.model.ProjectCard;
import com.projectlite2.android.utils.CloudUtil;
import com.projectlite2.android.utils.OnItemClickListenerPlus;
import com.scwang.smart.refresh.header.BezierRadarHeader;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import cn.leancloud.AVObject;
import cn.leancloud.AVQuery;
import cn.leancloud.AVUser;
import cn.leancloud.chatkit.LCChatKit;
import cn.leancloud.chatkit.activity.LCIMConversationActivity;
import cn.leancloud.chatkit.utils.LCIMConstants;
import cn.leancloud.im.v2.AVIMClient;
import cn.leancloud.im.v2.AVIMException;
import cn.leancloud.im.v2.callback.AVIMClientCallback;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static android.app.Activity.RESULT_OK;
import static com.projectlite2.android.utils.CloudUtil.CLASS_PROJECT.TABLE_FIELD_PROJECT_NAME;
import static com.projectlite2.android.utils.CloudUtil.CLASS_PROJECT.TABLE_NAME_PROJECT;
import static com.projectlite2.android.utils.CloudUtil.RELATION_PROJECT_LEADER_MAP.RELATION_FIELD_LEADER;
import static com.projectlite2.android.utils.CloudUtil.RELATION_PROJECT_LEADER_MAP.RELATION_FILED_PROJECT;
import static com.projectlite2.android.utils.CloudUtil.RELATION_PROJECT_LEADER_MAP.RELATION_NAME_PROJECT_LEADER_MAP;
import static com.projectlite2.android.utils.CloudUtil.RELATION_PROJECT_MEMBER_MAP.RELATION_FIELD_MEMBER;
import static com.projectlite2.android.utils.CloudUtil.RELATION_PROJECT_MEMBER_MAP.RELATION_NAME_PROJECT_MEMBER_MAP;

public class HomePageFragment extends Fragment {

    View mView;
    Toolbar toolBar;
    TextView txtTitle;
    RecyclerView mRecyclerView;
    ProjectCardAdapter mAdapter;
    //    SwipeRefreshLayout mRefresh;
    RefreshLayout mRefresh;
    private ArrayList<ProjectCard> projectList = new ArrayList<ProjectCard>();

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1: //  用户创建了新的项目，返回resultCode为OK，执行刷新操作
            case 2: {
                //  用户加入了新的项目，返回resultCode为OK，执行刷新操作
                if (resultCode == RESULT_OK) {
                    refreshData(1000);
                }
                break;
            }
            default:
                break;
        }
    }

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
                        SearchActivity.SetSearchType(SearchActivity.SearchType.project);
                        startActivityForResult(intent1,2);
                        break;
                    //  点击新项目
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
                                                        //  请求数据返回，以判断是否需要刷新
                                                        startActivityForResult(it, 1);
                                                        break;
                                                    case 1:

                                                        Intent it2 = new Intent(MyApplication.getContext(), SearchActivity.class);
                                                        SearchActivity.SetSearchType(SearchActivity.SearchType.project);
                                                        startActivity(it2);
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


        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView = mView.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new ProjectCardAdapter(projectList);
        mRecyclerView.setAdapter(mAdapter);

        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(MyApplication.getContext(), R.anim.layout_animation_fall_down);
        mRecyclerView.setLayoutAnimation(animation);


        // 初始化数据
        refreshData();


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


        mRefresh = mView.findViewById(R.id.smartRefresh);
        BezierRadarHeader myHeader = new BezierRadarHeader(MyApplication.getContext());
        myHeader.setAccentColor(Color.BLUE);
        myHeader.setPrimaryColor(Color.RED);
        mRefresh.setRefreshHeader(myHeader);
        mRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(800/*,false*/);//传入false表示刷新失败
                refreshData();
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

    /**
     * 刷新project列表数据 1秒后抓取
     */
    public void refreshData() {
        //  先清空一波
        projectList.clear();
        //  查询 project-leader-map
        queryAllLeaderProjects();
        //  查询 project_member_map
        queryAllMemberProjects();

        //  耗时操作，因而在给定时长后通知adapter刷新数据
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mAdapter.notifyDataSetChanged();
            }
        }, 1000);//1秒后执行Runnable中的run方法

        mRecyclerView.scheduleLayoutAnimation();

    }

    /**
     * 刷新project列表数据
     *
     * @param delayMillis 毫秒数，查询为耗时操作，延迟抓取数据
     */
    public void refreshData(int delayMillis) {
        //  先清空一波
        projectList.clear();
        //  查询 project-leader-map
        queryAllLeaderProjects();
        //  查询 project_member_map
        queryAllMemberProjects();

        //  耗时操作，因而在给定时长后通知adapter刷新数据
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mAdapter.notifyDataSetChanged();
            }
        }, delayMillis);//delayMillis毫秒后执行Runnable中的run方法

    }

    /**
     * 刷新数据，查询所有的leader项目
     */
    private void queryAllLeaderProjects() {


        //  先从 leader map 中查找
        AVQuery<AVObject> query = new AVQuery<>(RELATION_NAME_PROJECT_LEADER_MAP);
        query.whereEqualTo(RELATION_FIELD_LEADER, AVUser.getCurrentUser());
        query.findInBackground().subscribe(new Observer<List<AVObject>>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(List<AVObject> relations) {
                // relations 是所有 leader 等于 currentUser 的map对象
//                Log.d("mytest", "init relations: " + relations.size());
                // 然后遍历
                for (AVObject map : relations) {
                    AVObject project = map.getAVObject(RELATION_FILED_PROJECT);

                    AVQuery<AVObject> query = new AVQuery<>(TABLE_NAME_PROJECT);
                    query.getInBackground(project.getObjectId()).subscribe(new Observer<AVObject>() {
                        public void onSubscribe(Disposable disposable) {
                        }

                        public void onNext(AVObject item) {
                            // item 就是 project 实例
                            String pjName = item.getString(TABLE_FIELD_PROJECT_NAME);
//                            Log.d("mytest", "query: " + pjName);
                            projectList.add(new ProjectCard(pjName, true, 10));
                        }

                        public void onError(Throwable throwable) {
                        }

                        public void onComplete() {
                        }
                    });
                }

            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
            }
        });


    }

    /**
     * 刷新数据，查询所有的member项目
     */
    private void queryAllMemberProjects() {

        //  先从 leader map 中查找
        AVQuery<AVObject> query = new AVQuery<>(RELATION_NAME_PROJECT_MEMBER_MAP);
        query.whereEqualTo(RELATION_FIELD_MEMBER, AVUser.getCurrentUser());
        query.findInBackground().subscribe(new Observer<List<AVObject>>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(List<AVObject> relations) {
                // relations 是所有 leader 等于 currentUser 的map对象
//                Log.d("mytest", "init relations: " + relations.size());
                // 然后遍历
                for (AVObject map : relations) {
                    AVObject project = map.getAVObject(CloudUtil.RELATION_PROJECT_MEMBER_MAP.RELATION_FILED_PROJECT);

                    AVQuery<AVObject> query = new AVQuery<>(TABLE_NAME_PROJECT);
                    query.getInBackground(project.getObjectId()).subscribe(new Observer<AVObject>() {
                        public void onSubscribe(Disposable disposable) {
                        }

                        public void onNext(AVObject item) {
                            // item 就是 project 实例
                            String pjName = item.getString(TABLE_FIELD_PROJECT_NAME);
//                            Log.d("mytest", "query: " + pjName);
                            projectList.add(new ProjectCard(pjName, true, 10));
                        }

                        public void onError(Throwable throwable) {
                        }

                        public void onComplete() {
                        }
                    });
                }

            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
            }
        });


    }

}





