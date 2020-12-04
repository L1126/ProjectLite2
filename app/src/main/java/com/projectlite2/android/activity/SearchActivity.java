package com.projectlite2.android.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.interfaces.OnSelectListener;
import com.projectlite2.android.R;
import com.projectlite2.android.app.MyApplication;
import com.projectlite2.android.utils.popup.QueryResultPopup;

public class SearchActivity extends AppCompatActivity {

    //    自动完成列表
    private String[] strings = new String[]{"111", "222", "333"};
    private ListView list;
    private SearchView search;
    private SearchActivity thisActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("加入项目");


        thisActivity=this;

        list = findViewById(R.id.listView);
        search = findViewById(R.id.searchView);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, strings);
        list.setAdapter(adapter);

        //点击事件添加
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    default:

                        new XPopup.Builder(thisActivity)
                                .asCustom(new QueryResultPopup(thisActivity))
                                .show();

                        break;
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
        search.setQueryHint("查找");
//        为SearchView组件设置事件的监听器
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            //            单击搜索按钮时激发该方法
            @Override
            public boolean onQueryTextSubmit(String query) {
//                实际应用中应该在该方法内执行实际查询
//                此处仅使用Toast显示用户输入的查询内容
                MyApplication.ToastyInfo(query);
                return false;
            }

            //            用户输入时激发该方法
            @Override
            public boolean onQueryTextChange(String newText) {
//                如果newText不是长度为0的字符串
                if (TextUtils.isEmpty(newText)) {
//                    清除ListView的过滤
                    list.clearTextFilter();
                } else {
//                    使用用户输入的内容对ListView的列表项进行过滤
                    list.setFilterText(newText);
                }
                return true;
            }
        });
    }

    /**
     * 点击更多按钮监听中，调用本方法，显示更多菜单的popup window，传入的数组为菜单名称
     */
    private void showCardMenu(View v) {
        new XPopup.Builder(MyApplication.getContext())
                .atView(v)  // 依附于所点击的View，内部会自动判断在上方或者下方显示
                .asAttachList(new String[]{"分享", "编辑", "不带icon"},
                        new int[]{R.mipmap.ic_launcher, R.mipmap.ic_launcher},
                        new OnSelectListener() {
                            @Override
                            public void onSelect(int position, String text) {
                                MyApplication.ToastyInfo("click " + text);
                            }
                        })
                .show();
    }

}

