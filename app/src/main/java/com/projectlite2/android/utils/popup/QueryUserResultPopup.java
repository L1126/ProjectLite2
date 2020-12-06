package com.projectlite2.android.utils.popup;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.lxj.xpopup.core.BottomPopupView;
import com.lxj.xpopup.util.XPopupUtils;
import com.projectlite2.android.R;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.leancloud.AVUser;

public class QueryUserResultPopup extends BottomPopupView {

//    @BindView(R.id.txtThisProjectName)
//    TextView txtProjectName;
//
//    @BindView(R.id.txtThisProjectNum)
//    TextView txtProjectId;
//
//    @BindView(R.id.txtThisProjectBrief)
//    TextView txtProjectBrief;
//
//    @BindView(R.id.btnApplyToJoin)
//    Button btnApplyToJoin;

    String projectName, projectBrief, projectId, objectId;
    Date dateStart, dateClosing;
    AVUser projectLeader;
    Boolean isMeLeader = false;
    public static Boolean joinSuccess = false;
    String thisLeaderUserId="";
    Activity parentActivity;


//
//    public QueryUserResultPopup(@NonNull Context context, String pjId, String pjName, String pjBrief, String objID) {
//        super(context);
//        projectId = pjId;
//        projectName = pjName;
//        projectBrief = pjBrief;
//        objectId = objID;
//        parentActivity= (Activity) context;
//
//    }
    //

    public QueryUserResultPopup(@NonNull Context context) {
        super(context);
    }


    @Override
    protected int getImplLayoutId() {
        return R.layout.user_card_bottom_popup;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        ButterKnife.bind(this);


//        if (!projectBrief.equals("")) {
//            txtProjectBrief.setText(projectBrief);
//        }
//        txtProjectId.setText(projectId);
//        txtProjectName.setText(projectName);

    }

    // 最大高度为Window的0.85
    @Override
    protected int getMaxHeight() {

        return (int) (XPopupUtils.getAppHeight(getContext()) * .85f);
    }


//    @OnClick({R.id.btnApplyToJoin})
//    void OnClick(View v) {
//        switch (v.getId()) {
//            case R.id.btnApplyToJoin:
//
//                //  判断本人是否为负责人
//                GetLeader();
//                //  若该项目的leader为当前用户，则提示不可重复加入
//                if (isMeLeader) {
//                    MyApplication.ToastyInfo("你已经是该项目的负责人啦，不需要重复加入");
//                    return;
//                }
//                //   弹窗确认
//                new XPopup.Builder(getContext()).asConfirm("提示", "你还未加入该项目，确定要加入吗？",
//                        new OnConfirmListener() {
//                            @Override
//                            public void onConfirm() {
//
//                                //  实际是要发送加入申请->leader
//
//                                //  加入方法的测试 成功***********************
//                                Log.d("mytest", "onConfirm: pj objectId  "+objectId);
//                                Log.d("mytest", "onConfirm: user objectId  "+AVUser.getCurrentUser().getObjectId());
//                                CloudUtil.CLASS_PROJECT.JoinProject(objectId,AVUser.getCurrentUser().getObjectId());
//
//                                //  耗时操作，延时执行
//                                Handler handler = new Handler();
//                                handler.postDelayed(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        if(joinSuccess){
//
//                                            dismiss();
//                                            MyApplication.ToastySuccess("success");
//                                            joinSuccess=false;
//
//                                            //  成功加入项目，向上个activity返回请求码，使其刷新
//                                            Intent it=new Intent();
//                                            it.putExtra("data_return","create success");
//                                            parentActivity.setResult(RESULT_OK, it);
//
//                                            parentActivity.finish();
//                                        }
//                                    }
//                                }, 500);//1秒后执行Runnable中的run方法
//
//                                //*****************************************
//
//                                //  测试消息推送
////                                AVQuery pushQuery = AVInstallation.getQuery();
////                                // 假设 THE_INSTALLATION_ID 是保存在用户表里的 installationId，
////                                pushQuery.whereEqualTo(TABLE_FIELD_USER_ID,thisLeaderUserId);
////                                AVPush.sendMessageInBackground("申请加入项目",pushQuery).subscribe(new Observer() {
////                                    @Override
////                                    public void onSubscribe(Disposable d) {
////                                    }
////                                    @Override
////                                    public void onNext(Object object) {
////                                        Log.d("mytest", "推送成功" + object );
////                                    }
////                                    @Override
////                                    public void onError(Throwable e) {
////                                        Log.d("mytest", "推送失败，错误信息：" + e.getMessage());
////                                    }
////                                    @Override
////                                    public void onComplete() {
////                                    }
////                                }); // 可以发送推送消息到leader，但是有个小bug是也会发送推送消息到当前用户，怀疑installationId
//
//
//
//                            }
//                        })
//                        .show();
//
//                break;
//        }
    }


    /**
     * 使用该方法判断本用户是否是该项目的负责人，结果存储在isMeLeader中，并且把项目负责人的 userId 储存在 thisLeaderUserId 中
     */
//    private void GetLeader() {
//
//        AVObject project = AVObject.createWithoutData(TABLE_NAME_PROJECT, objectId);
//        // 构建多对多关系的查询
//        AVQuery<AVObject> query = new AVQuery<>(RELATION_NAME_PROJECT_LEADER_MAP);
//        // 查询所有为该项目的leader（实际上只有一位）
//        query.whereEqualTo(RELATION_FILED_PROJECT, project);
//        // 执行查询
//        query.findInBackground().subscribe(new Observer<List<AVObject>>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//            }
//
//            @Override
//            public void onNext(List<AVObject> list) {
//                // list 是所有 项目 等于 project 的leader
//                //  获取当前用户
//                AVUser currentUser = AVUser.getCurrentUser();
//                // 然后遍历过程中可以访问每一个的leader
//                for (AVObject projectLeaderMap : list) {
//
//                    AVObject leader = projectLeaderMap.getAVObject(RELATION_FIELD_LEADER);
//
//                    thisLeaderUserId=leader.getString(TABLE_FIELD_USER_ID);
//
//                    if (leader.getObjectId().equals(currentUser.getObjectId())) {
//                        isMeLeader = true;
//                    } else {
//                        isMeLeader = false;
//                    }
//
//                    Log.d("mytest", "is leader?: " + isMeLeader);
//                }
//            }
//
//            @Override
//            public void onError(Throwable e) {
//            }
//
//            @Override
//            public void onComplete() {
//            }
//        });
//    }

