package com.projectlite2.android.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.projectlite2.android.R;
import com.projectlite2.android.activity.MainActivity;
import com.projectlite2.android.adapter.TimeLineAdapter;
import com.projectlite2.android.app.MyApplication;
import com.projectlite2.android.model.OrderStatus;
import com.projectlite2.android.model.TimeLineModel;
import com.projectlite2.android.utils.CloudUtil;

import java.util.ArrayList;
import java.util.Calendar;

public class NodeDetailFragment extends Fragment implements View.OnClickListener {

    private View mActivityView;
    private TimeLineAdapter mCommentAdapter;
    private final ArrayList mCommentDataList = new ArrayList();
    private RecyclerView mRvTimeLineComment;
    private LinearLayoutManager mCommentLayoutManager;
    private Button mBtnSend;
    private TextView mCommentInput;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mActivityView=getActivity().getLayoutInflater().inflate(R.layout.fragment_node_detail, container, false);
        mBtnSend=mActivityView.findViewById(R.id.btnCommentSend);
        mCommentInput=mActivityView.findViewById(R.id.txtCommentInput);

        mBtnSend.setOnClickListener(this);
        return mActivityView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initRvTimeLineComment();


    }

    /**
     * 初始化留言区的RV
     */
    private void initRvTimeLineComment() {
        addDataListItems();
         mCommentLayoutManager = new LinearLayoutManager(MyApplication.getContext(),
                LinearLayoutManager.VERTICAL, false) {
//            @Override
//            public boolean canScrollVertically() {
//                return false;
//            }
        };
        mCommentAdapter=new TimeLineAdapter(mCommentDataList);
        mRvTimeLineComment=mActivityView.findViewById(R.id.rvTimeLineComment);
        mRvTimeLineComment.setLayoutManager(mCommentLayoutManager);
        mRvTimeLineComment.setAdapter(mCommentAdapter);
    }

    /**
     * 添加留言区初始数据
     */
    private void addDataListItems() {
        this.mCommentDataList.add(new TimeLineModel("KKang:  项目群聊，工作空间，群聊设置完成", "2020-12-08 11:49", OrderStatus.INACTIVE));
        this.mCommentDataList.add(new TimeLineModel("Linwc:  更新向导界面的小标题和描述", "2020-12-08 09:54", OrderStatus.ACTIVE));
        this.mCommentDataList.add(new TimeLineModel("Linwc:  基本完成主题颜色的配置，并且修正了黑夜模式下的控件的颜色。(刷新控件的颜色不兼容，系统设置页面的夜间模式未完全修正)", "2020-12-08 02:32", OrderStatus.COMPLETED));
        this.mCommentDataList.add(new TimeLineModel("KKang:  群聊布局，发送消息，侧边抽屉工作空间跳转", "2020-12-08 00:58", OrderStatus.COMPLETED));
        this.mCommentDataList.add(new TimeLineModel("Linwc:  实现主页项目进度刷新", "2020-12-07 20:58", OrderStatus.COMPLETED));
        this.mCommentDataList.add(new TimeLineModel("Linwc:  1.实现新用户注册设置头像，以及在个人中心设置头像；2.能够检索用户投递名片，但名片接收(通知)还未实现", "2020-12-07 20:16", OrderStatus.COMPLETED));
        this.mCommentDataList.add(new TimeLineModel("KKang:  聊天界面第一阶段", "2020-12-07 18:48", OrderStatus.COMPLETED));
        this.mCommentDataList.add(new TimeLineModel("Linwc:  注册新用户可以从相册设置头像同步到云端，调用相机回会闪退", "2020-12-07 11:36", OrderStatus.COMPLETED));
        this.mCommentDataList.add(new TimeLineModel("Linwc:  1. 为收件箱页面和名片夹页面增加下拉刷新组件 2. 名片夹页面增加搜索入口", "2020-12-06 16:03", OrderStatus.COMPLETED));
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnCommentSend:
            {  Calendar now = Calendar.getInstance();

                mCommentDataList.add(new TimeLineModel(CloudUtil.CURRENT_USER.name+":  "+mCommentInput.getText().toString(), "2020-12-10 15:46", OrderStatus.INACTIVE));
                mCommentAdapter=new TimeLineAdapter(mCommentDataList);
                mRvTimeLineComment.setAdapter(mCommentAdapter);
                break;
            }
        }
    }
}