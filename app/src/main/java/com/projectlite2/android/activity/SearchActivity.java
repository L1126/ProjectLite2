package com.projectlite2.android.activity;

<<<<<<< HEAD
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.lxj.xpopup.XPopup;
import com.projectlite2.android.R;
import com.projectlite2.android.app.MyApplication;
import com.projectlite2.android.utils.popup.QueryProjectResultPopup;
import com.projectlite2.android.utils.popup.QueryUserResultPopup;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.leancloud.AVFile;
import cn.leancloud.AVObject;
import cn.leancloud.AVQuery;
import cn.leancloud.AVUser;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static com.projectlite2.android.utils.CloudUtil.CLASS_PROJECT.TABLE_FIELD_DATE_CLOSING;
import static com.projectlite2.android.utils.CloudUtil.CLASS_PROJECT.TABLE_FIELD_DATE_START;
import static com.projectlite2.android.utils.CloudUtil.CLASS_PROJECT.TABLE_FIELD_PROJECT_BRIEF;
import static com.projectlite2.android.utils.CloudUtil.CLASS_PROJECT.TABLE_FIELD_PROJECT_ID;
import static com.projectlite2.android.utils.CloudUtil.CLASS_PROJECT.TABLE_FIELD_PROJECT_NAME;
import static com.projectlite2.android.utils.CloudUtil.CLASS_PROJECT.TABLE_NAME_PROJECT;
import static com.projectlite2.android.utils.CloudUtil.CLASS_USER.TABLE_FIELD_USER_AVATAR;
import static com.projectlite2.android.utils.CloudUtil.CLASS_USER.TABLE_FIELD_USER_ID;
import static com.projectlite2.android.utils.CloudUtil.CLASS_USER.TABLE_FIELD_USER_INSTALLATION_ID;
import static com.projectlite2.android.utils.CloudUtil.CLASS_USER.TABLE_FIELD_USER_NAME;
import static com.projectlite2.android.utils.CloudUtil.CLASS_USER.TABLE_NAME_USER;

public class SearchActivity extends AppCompatActivity {


    private ArrayList<String> arrQueryResult = new ArrayList<String>();
    private ArrayList<String> arrResultId = new ArrayList<String>();
    private List<AVObject> queryProjects;
    private List<AVUser> queryUsers;

    private ListView list;
    private SearchView search;
    private SearchActivity thisActivity;

    public enum SearchType {
        project, user
    }

    private static SearchType searchType;


    /**
     * 公开的设置搜索类型的方法
     *
     * @param t 枚举类型，选择搜索项目或者用户
     */
    public static void SetSearchType(SearchType t) {
        searchType = t;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        list = findViewById(R.id.listView);
        search = findViewById(R.id.searchView);


        if (searchType == SearchType.project) {
            getSupportActionBar().setTitle("搜索项目");
            search.setQueryHint("输入项目唯一Id");
            Log.d("mytest", "SearchActivity: project ");
        } else if (searchType == SearchType.user) {
            getSupportActionBar().setTitle("搜索用户");
            search.setQueryHint("输入用户唯一Id");
            Log.d("mytest", "SearchActivity: user ");
        } else {
            getSupportActionBar().setTitle("error");
            Log.d("mytest", "SearchActivity: error ");
        }


        thisActivity = this;

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrQueryResult);
        list.setAdapter(adapter);
        list.setVisibility(View.GONE);


        //点击事件添加
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // 隐藏键盘
                MyApplication.hideShowKeyboard(MyApplication.getContext());

                if (searchType == SearchType.project) {
                    String pjId = queryProjects.get(position).getString(TABLE_FIELD_PROJECT_ID);
                    String pjName = queryProjects.get(position).getString(TABLE_FIELD_PROJECT_NAME);
                    String pjBrief = queryProjects.get(position).getString(TABLE_FIELD_PROJECT_BRIEF);
                    String objId = queryProjects.get(position).getObjectId();

                    Date dateStart = queryProjects.get(position).getDate(TABLE_FIELD_DATE_START);
                    Date dateClosing = queryProjects.get(position).getDate(TABLE_FIELD_DATE_CLOSING);
                    //  调用自定义的浮窗 卡片显示项目信息
                    new XPopup.Builder(thisActivity)
                            .asCustom(new QueryProjectResultPopup(thisActivity, pjId, pjName, pjBrief, objId))
                            .show();
                }
                else if (searchType == SearchType.user) {

                    String userId = queryUsers.get(position).getString(TABLE_FIELD_USER_ID);
                    String userObjId = queryUsers.get(position).getObjectId();
                    String userName = queryUsers.get(position).getString(TABLE_FIELD_USER_NAME);
                    String installationId = queryUsers.get(position).getString(TABLE_FIELD_USER_INSTALLATION_ID);
                    AVFile userAvatar=queryUsers.get(position).getAVFile(TABLE_FIELD_USER_AVATAR);

                    Log.d("mytest", "SearchActivity: "+userId);

                    //  调用自定义的浮窗 卡片显示项目信息
                    new XPopup.Builder(thisActivity)
                            .asCustom(new QueryUserResultPopup(thisActivity,userName,userId,userAvatar,installationId,userObjId))
                            .show();
                } else {

                }


            }
        });


//        设置ListView启动过滤
        list.setTextFilterEnabled(true);
//        设置该SearchView默认是否自动缩小为图标
        search.setIconifiedByDefault(false);
//        设置该SearchView显示搜索图标
        search.setSubmitButtonEnabled(true);
//        设置该SearchView内默认显示的搜索文字
        search.setQueryHint("请输入项目唯一id");
//        为SearchView组件设置事件的监听器
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            //            单击搜索按钮时激发该方法
            @Override
            public boolean onQueryTextSubmit(String str) {
                //  执行实际查询

                if (searchType == SearchType.project) {
                    AVQuery<AVObject> query = new AVQuery<>(TABLE_NAME_PROJECT);

                    query.whereContains(TABLE_FIELD_PROJECT_ID, str);
                    query.findInBackground().subscribe(new Observer<List<AVObject>>() {
                        public void onSubscribe(Disposable disposable) {
                        }

                        public void onNext(List<AVObject> projects) {
                            //  如果查询成功的话 保存这个结果
                            queryProjects = projects;
                            //  清空查询结果数组
                            arrQueryResult.clear();
                            //  清空结果对象id数组
                            arrResultId.clear();

                            //  遍历查询结果对象数组，取出需要的元素放在指定数组中
                            for (int i = 0; i < projects.size(); i++) {
                                //  获取项目名称
                                arrQueryResult.add(projects.get(i).getString(TABLE_FIELD_PROJECT_NAME));
                                //  获取项目数据对象id
                                arrResultId.add(projects.get(i).getObjectId());
                            }

                            Log.d("mytest", "query size: " + projects.size());

                            //  实例化新的adapter，导入数据，尝试过多种更新list和ui的方法，这是唯一成功的
                            ArrayAdapter newAdapter =
                                    new ArrayAdapter(MyApplication.getContext(), android.R.layout.simple_list_item_1, arrQueryResult);
                            //  为list设置新的adapter
                            list.setAdapter(newAdapter);
                            list.setVisibility(View.VISIBLE);
                        }

                        public void onError(Throwable throwable) {
                        }

                        public void onComplete() {
                        }

                    });
                } else if (searchType == SearchType.user) {
                    AVQuery<AVUser> query = new AVQuery<>(TABLE_NAME_USER);

                    query.whereContains(TABLE_FIELD_USER_ID, str);
                    query.findInBackground().subscribe(new Observer<List<AVUser>>() {
                        public void onSubscribe(Disposable disposable) {
                        }

                        public void onNext(List<AVUser> users) {
                            //  如果查询成功的话 保存这个结果
                            queryUsers = users;
                            //  清空查询结果数组
                            arrQueryResult.clear();
                            //  清空结果对象id数组
                            arrResultId.clear();

                            //  遍历查询结果对象数组，取出需要的元素放在指定数组中
                            for (int i = 0; i < users.size(); i++) {
                                //  获取项目名称
                                arrQueryResult.add(users.get(i).getString(TABLE_FIELD_USER_NAME));
                                //  获取项目数据对象id
                                arrResultId.add(users.get(i).getObjectId());
                            }

                            Log.d("mytest", "query size: " + users.size());

                            //  实例化新的adapter，导入数据，尝试过多种更新list和ui的方法，这是唯一成功的
                            ArrayAdapter newAdapter =
                                    new ArrayAdapter(MyApplication.getContext(), android.R.layout.simple_list_item_1, arrQueryResult);
                            //  为list设置新的adapter
                            list.setAdapter(newAdapter);
                            list.setVisibility(View.VISIBLE);
                        }

                        public void onError(Throwable throwable) {
                        }

                        public void onComplete() {
                        }

                    });
                } else {

                }


                return false;
            }

            //            用户输入时激发该方法
            @Override
            public boolean onQueryTextChange(String newText) {
//                如果newText不是长度为0的字符串
                if (TextUtils.isEmpty(newText)) {
//                    清除ListView的过滤
                    list.clearTextFilter();
                    list.setVisibility(View.GONE);
                } else {
//                    使用用户输入的内容对ListView的列表项进行过滤
                    list.setFilterText(newText);
                    list.setVisibility(View.VISIBLE);
                }
                return true;
            }
        });
    }


=======
public class SearchActivity {
>>>>>>> 2b65d275b8abc085741fbf892a09bc5366e41c3d
}
