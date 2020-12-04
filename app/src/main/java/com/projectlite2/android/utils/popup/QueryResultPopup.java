package com.projectlite2.android.utils.popup;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.BottomPopupView;
import com.lxj.xpopup.interfaces.OnConfirmListener;
import com.lxj.xpopup.util.XPopupUtils;
import com.lxj.xpopup.widget.VerticalRecyclerView;
import com.projectlite2.android.R;
import com.projectlite2.android.app.MyApplication;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.leancloud.AVObject;
import cn.leancloud.AVQuery;
import cn.leancloud.AVUser;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static com.projectlite2.android.utils.ProjectUtil.RELATION_FIELD_LEADER;
import static com.projectlite2.android.utils.ProjectUtil.RELATION_FILED_PROJECT;
import static com.projectlite2.android.utils.ProjectUtil.RELATION_NAME_USER_PROJECT_LEADER_MAP;
import static com.projectlite2.android.utils.ProjectUtil.TABLE_NAME_PROJECT;

public class QueryResultPopup extends BottomPopupView {

    @BindView(R.id.txtThisProjectName)
    TextView txtProjectName;

    @BindView(R.id.txtThisProjectNum)
    TextView txtProjectId;

    @BindView(R.id.txtThisProjectBrief)
    TextView txtProjectBrief;

    @BindView(R.id.btnApplyToJoin)
    Button btnApplyToJoin;

    String projectName,projectBrief,projectId,objectId;
    Date dateStart, dateClosing;
    AVUser projectLeader;
    Boolean isMeLeader=false;

    public QueryResultPopup(@NonNull Context context, String pjId, String pjName, AVUser leader, String pjBrief, Date dStart, Date dEnd) {
        super(context);
        projectId=pjId;
        projectName=pjName;
        projectBrief=pjBrief;
        dateStart=dStart;
        dateClosing=dEnd;
        projectLeader=leader;
    }

    public QueryResultPopup(@NonNull Context context, String pjId, String pjName, AVUser leader, Date dStart, Date dEnd) {
        super(context);
        projectId=pjId;
        projectName=pjName;
        dateStart=dStart;
        dateClosing=dEnd;
        projectLeader=leader;
    }
    public QueryResultPopup(@NonNull Context context, String pjId, String pjName,String pjBrief,Date dStart, Date dEnd) {
        super(context);
        projectId=pjId;
        projectName=pjName;
        dateStart=dStart;
        dateClosing=dEnd;
        projectBrief=pjBrief;

    }
    public QueryResultPopup(@NonNull Context context, String pjId, String pjName,String pjBrief,String objID) {
        super(context);
        projectId=pjId;
        projectName=pjName;
        projectBrief=pjBrief;
        objectId=objID;

    }
    public QueryResultPopup(@NonNull Context context) {
        super(context);
    }


    @Override
    protected int getImplLayoutId() {
        return R.layout.custom_bottom_popup;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        ButterKnife.bind(this);



        if(!projectBrief.equals("")){
            txtProjectBrief.setText(projectBrief);
        }
        txtProjectId.setText(projectId);
        txtProjectName.setText(projectName);

    }

    // 最大高度为Window的0.85
    @Override
    protected int getMaxHeight() {

        return (int) (XPopupUtils.getAppHeight(getContext()) * .85f);
    }


    @OnClick({R.id.btnApplyToJoin})
    void OnClick(View v) {
        switch (v.getId()){
            case R.id.btnApplyToJoin:

                //  判断本人是否为负责人
                GetLeader();
                //  若该项目的leader为当前用户，则提示不可重复加入
               if (isMeLeader){
                   MyApplication.ToastyInfo("你已经是该项目的负责人啦，不需要重复加入");
                   return;
               }
               //   弹窗确认
                new XPopup.Builder(getContext()).asConfirm("提示", "你还未加入该项目，确定要加入吗？",
                        new OnConfirmListener() {
                            @Override
                            public void onConfirm() {
                                MyApplication.ToastyInfo("click confirm");
                                //  建立中间表，加入该项目
                                

                            }
                        })
                        .show();

                break;
        }
    }



    /**
     * 使用该方法判断本用户是否是该项目的负责人，结果存储在isMeLeader中
     */
    private void GetLeader(){

        AVObject project = AVObject.createWithoutData(TABLE_NAME_PROJECT, objectId);
        // 构建多对多关系的查询
        AVQuery<AVObject> query = new AVQuery<>(RELATION_NAME_USER_PROJECT_LEADER_MAP);
        // 查询所有为该项目的leader（实际上只有一位）
        query.whereEqualTo(RELATION_FILED_PROJECT, project);
        // 执行查询
        query.findInBackground().subscribe(new Observer<List<AVObject>>() {
            @Override
            public void onSubscribe(Disposable d) {
            }
            @Override
            public void onNext(List<AVObject> list) {
                // list 是所有 项目 等于 project 的leader
                //  获取当前用户
                AVUser currentUser = AVUser.getCurrentUser();
                // 然后遍历过程中可以访问每一个的leader
                for (AVObject projectLeaderMap : list) {

                    AVObject leader = projectLeaderMap.getAVObject(RELATION_FIELD_LEADER);

                    if (leader.getObjectId().equals(currentUser.getObjectId())){
                        isMeLeader=true;
                    }else{isMeLeader=false;}

                    Log.d("mytest", "onNext: "+isMeLeader);
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
