package com.projectlite2.android.utils;

import com.projectlite2.android.app.MyApplication;

import java.util.Date;
import java.util.List;

import cn.leancloud.AVObject;
import cn.leancloud.AVQuery;
import cn.leancloud.AVUser;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public class ProjectUtil {


    /**
     * 数据表名称：项目
     */
    public static final  String TABLE_NAME_PROJECT ="Project";

    /**
     * 中间表名称：用户、项目、项目负责人中间表
     */
    public static final String RELATION_NAME_USER_PROJECT_LEADER_MAP ="UserProjectLeaderMap";

    /**
     * 中间表名称：用户、项目、项目成员中间表
     */
    public static final String RELATION_NAME_USER_PROJECT_MEMBER_MAP ="UserProjectMemberMap";

    /**
     * 中间表字段：用户
     */
    public static final String RELATION_FIELD_LEADER ="leader";

    /**
     * 中间表字段：项目
     */
    public static final String RELATION_FILED_PROJECT ="project";




    /**
     * 项目数据表字段名称：对象ID
     */
    public static final String TABLE_FIELD_OBJECT_ID = "objectId";

    /**
     * 项目数据表字段名称：项目12位ID
     */
    public static final String TABLE_FIELD_PROJECT_ID = "projectId";
    /**
     * 项目数据表字段名称：项目名称
     */
    public static final String TABLE_FIELD_PROJECT_NAME = "projectName";
    /**
     * 项目数据表字段名称：项目简介
     */
    public static final String TABLE_FIELD_PROJECT_BRIEF = "projectBrief";

    /**
     * 项目数据表字段名称：开始日期
     */
    public static final String TABLE_FIELD_DATE_START = "dateStart";
    /**
     * 项目数据表字段名称：截止日期
     */
    public static final String TABLE_FIELD_DATE_CLOSING = "dateClosing";


    /**
     * 创建项目 v1.0
     * @param pjName    项目名称
     * @param pjBrief   项目摘要
     * @param dateStart 项目开始日期
     * @param dateClosing   项目截止日期
     */
    public static void CreateProject(String pjName, String pjBrief,Date dateStart,Date dateClosing) {

        //  检查当前是否是用户登录的状态
        AVUser currentUser = AVUser.getCurrentUser();
        if (currentUser == null) {
            MyApplication.ToastyWarning("当前无用户登录");
            return; // 直接return 结束创建操作
        }


        // 构建对象
        AVObject project = new AVObject(TABLE_NAME_PROJECT);
        // 为属性赋值
        project.put(TABLE_FIELD_PROJECT_ID,MyApplication.BuildRandomID());
        project.put(TABLE_FIELD_PROJECT_NAME, pjName);
        project.put(TABLE_FIELD_PROJECT_BRIEF, pjBrief);
        project.put(TABLE_FIELD_DATE_START,dateStart);
        project.put(TABLE_FIELD_DATE_CLOSING,dateClosing);

        // 将对象保存到云端
        project.saveInBackground();

        // 当前用户作为project的负责人： 创建中间表，关联用户和项目对象
        AVObject userProjectLeaderMap = new AVObject(RELATION_NAME_USER_PROJECT_LEADER_MAP);// 选课表对象
        // 设置关联
        userProjectLeaderMap.put(RELATION_FIELD_LEADER, currentUser);
        userProjectLeaderMap.put(RELATION_FILED_PROJECT, project);
        // 保存选课表对象
        userProjectLeaderMap.saveInBackground().subscribe(new Observer<AVObject>() {
            public void onSubscribe(Disposable disposable) {
            }

            public void onNext(AVObject todo) {
                // 成功保存之后，执行其他逻辑
                MyApplication.ToastySuccess("Create success!");
                System.out.println("保存成功。objectId：" + todo.getObjectId());
            }

            public void onError(Throwable throwable) {
                MyApplication.ToastyError("error");
                // 异常处理
            }

            public void onComplete() {
            }
        });



    }


//未完成
    public static void JoinProject(String projectId) {

        //  检查当前是否是用户登录的状态
        AVUser currentUser = AVUser.getCurrentUser();
        if (currentUser == null) {
            MyApplication.ToastyWarning("当前无用户登录");
            return; // 直接return 结束创建操作
        }

        AVQuery<AVObject> queryProject = new AVQuery<>(TABLE_NAME_PROJECT);
        queryProject.whereEqualTo("lastName", projectId);
        queryProject.findInBackground().subscribe(new Observer<List<AVObject>>() {
            public void onSubscribe(Disposable disposable) {}
            public void onNext(List<AVObject> projects) {
                // students 是包含满足条件的 Student 对象的数组

            }
            public void onError(Throwable throwable) {}
            public void onComplete() {}
        });


    }







}
