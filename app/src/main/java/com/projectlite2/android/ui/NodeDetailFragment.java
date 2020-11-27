package com.projectlite2.android.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.projectlite2.android.R;
import com.projectlite2.android.activity.MainActivity;
import com.projectlite2.android.adapter.TimeLineAdapter;
import com.projectlite2.android.app.MyApplication;
import com.projectlite2.android.model.OrderStatus;
import com.projectlite2.android.model.TimeLineModel;

import java.util.ArrayList;

public class NodeDetailFragment extends Fragment {

    private View mActivityView;
    private TimeLineAdapter mCommentAdapter;
    private final ArrayList mCommentDataList = new ArrayList();
    private RecyclerView mRvTimeLineComment;
    private LinearLayoutManager mCommentLayoutManager;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mActivityView=getActivity().getLayoutInflater().inflate(R.layout.fragment_node_detail, container, false);
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
        this.mCommentDataList.add(new TimeLineModel("Item successfully delivered", "", OrderStatus.INACTIVE));
        this.mCommentDataList.add(new TimeLineModel("Courier is out to delivery your order", "2017-02-12 08:00", OrderStatus.ACTIVE));
        this.mCommentDataList.add(new TimeLineModel("Item has reached courier facility at New Delhi", "2017-02-11 21:00", OrderStatus.COMPLETED));
        this.mCommentDataList.add(new TimeLineModel("Item has been given to the courier", "2017-02-11 18:00", OrderStatus.COMPLETED));
        this.mCommentDataList.add(new TimeLineModel("Item is packed and will dispatch soon", "2017-02-11 09:30", OrderStatus.COMPLETED));
        this.mCommentDataList.add(new TimeLineModel("Order is being readied for dispatch", "2017-02-11 08:00", OrderStatus.COMPLETED));
        this.mCommentDataList.add(new TimeLineModel("Order processing initiated", "2017-02-10 15:00", OrderStatus.COMPLETED));
        this.mCommentDataList.add(new TimeLineModel("Order confirmed by seller", "2017-02-10 14:30", OrderStatus.COMPLETED));
        this.mCommentDataList.add(new TimeLineModel("Order placed successfully", "2017-02-10 14:00", OrderStatus.COMPLETED));
    }

}