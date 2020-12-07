package com.projectlite2.android.utils;

import android.content.Intent;
import android.util.Log;

import com.projectlite2.android.activity.LoginActivity;
import com.projectlite2.android.activity.PushTestActivity;
import com.projectlite2.android.app.MyApplication;
import com.projectlite2.android.utils.popup.QueryProjectResultPopup;

import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.List;

import cn.leancloud.AVInstallation;
import cn.leancloud.AVObject;
import cn.leancloud.AVQuery;
import cn.leancloud.AVUser;
import cn.leancloud.im.v2.AVIMClient;
import cn.leancloud.im.v2.AVIMException;
import cn.leancloud.im.v2.callback.AVIMClientCallback;
import cn.leancloud.push.PushService;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static com.projectlite2.android.utils.CloudUtil.CLASS_USER.TABLE_FIELD_USER_ID;
import static com.projectlite2.android.utils.CloudUtil.CLASS_USER.TABLE_FIELD_USER_NAME;
import static com.projectlite2.android.utils.CloudUtil.RELATION_PROJECT_MEMBER_MAP.RELATION_FIELD_MEMBER;
import static com.projectlite2.android.utils.CloudUtil.RELATION_PROJECT_MEMBER_MAP.RELATION_FILED_PROJECT;


public class CloudUtil {


    /**
     * 当前登录用户
     */
    public static class CURRENT_USER {

        /**
         * 当前用户实例引用
         */
        public static AVUser user;

        /**
         * 当前用户名
         */
        public static String name;

        /**
         * 当前用户UserId
         */
        public static String userId;

        /**
         * 当前用户objectId
         */
        public static String objId;

        /**
         * 当前用户的 IM CLIENT
         */
        public static AVIMClient imClient;


        /**
         * 用户连接服务器
         * @param currentUser
         */
        public static void ConfigImClinet(@NotNull AVUser currentUser){

            user=currentUser;
            name =currentUser.getString(TABLE_FIELD_USER_NAME);
            userId=currentUser.getString(TABLE_FIELD_USER_ID);
            objId=currentUser.getObjectId();


            // 与服务器连接
            imClient = AVIMClient.getInstance(currentUser);
            imClient.open(new AVIMClientCallback() {
                @Override
                public void done(final AVIMClient avimClient, AVIMException e) {
                    if (e == null) {
                        // 成功打开连接
                        // 执行其他逻辑
                        Log.d("mytest", "im client: connected!");
                    }

                }
            });



        }


        /**
         *  检查登陆状态，链接服务器，配置installationId
         */
        public static void ConfigCurrentUserAndInstallationId() {
            AVUser currentUser = AVUser.getCurrentUser();


            if (currentUser != null) {


                //  当前为用户登录状态，配置该用户的installationId

                Log.d("mytest", "ConfigCurrentUserAndInstallationId: " + "当前用户：" + currentUser.getMobilePhoneNumber());

                //  配置installationId
                AVInstallation.getCurrentInstallation().saveInBackground().subscribe(new Observer<AVObject>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(AVObject avObject) {
                        // 关联 installationId 到用户表等操作。
                        MyApplication.MY_INSTALLATION_ID = AVInstallation.getCurrentInstallation().getInstallationId();
                        Log.d("mytest", "关联 installationId   保存成功：" + MyApplication.MY_INSTALLATION_ID);
                        currentUser.put("installationId", MyApplication.MY_INSTALLATION_ID);
                        currentUser.saveInBackground().subscribe(new Observer<AVObject>() {
                            public void onSubscribe(Disposable disposable) {
                            }

                            public void onNext(AVObject todo) {
                                Log.d("mytest", "currentUser save installationId success");

                                // 启动推送服务 设置默认打开的 Activity
                                PushService.setDefaultPushCallback(MyApplication.getContext(), PushTestActivity.class);
                                //  配置登录通讯服务器
                                ConfigImClinet(currentUser);
                            }

                            public void onError(Throwable throwable) {
                                MyApplication.ToastyError("error");
                                Log.d("mytest", "currentUser save installationId  Error: " + throwable);
                                // 异常处理
                            }

                            public void onComplete() {
                            }
                        });

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("mytest", "关联 installationId   保存失败，错误信息：" + e.getMessage());

                    }

                    @Override
                    public void onComplete() {
                    }
                });


            } else {
                //  若当前为无用户登陆状态，则跳转到登录界面
                MyApplication.ToastyError("当前无用户");

            }

        }


    }


    /**
     * 项目表 字段与方法
     */
    public static class CLASS_PROJECT {

        /**
         * 数据表名称：项目
         */
        public static final String TABLE_NAME_PROJECT = "Project";

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
         *
         * @param pjName      项目名称
         * @param pjBrief     项目摘要
         * @param dateStart   项目开始日期
         * @param dateClosing 项目截止日期
         */
        public static void CreateProject(String pjName, String pjBrief, Date dateStart, Date dateClosing) {

            //  检查当前是否是用户登录的状态
            AVUser currentUser = AVUser.getCurrentUser();
            if (currentUser == null) {
                MyApplication.ToastyWarning("当前无用户登录");
                return; // 直接return 结束创建操作
            }


            // 构建对象
            AVObject project = new AVObject(TABLE_NAME_PROJECT);
            // 为属性赋值
            project.put(TABLE_FIELD_PROJECT_ID, MyApplication.BuildRandomID());
            project.put(TABLE_FIELD_PROJECT_NAME, pjName);
            project.put(TABLE_FIELD_PROJECT_BRIEF, pjBrief);
            project.put(TABLE_FIELD_DATE_START, dateStart);
            project.put(TABLE_FIELD_DATE_CLOSING, dateClosing);

            // 将对象保存到云端
            project.saveInBackground();

            // 当前用户作为project的负责人： 创建中间表，关联用户和项目对象
            AVObject userProjectLeaderMap = new AVObject(RELATION_PROJECT_LEADER_MAP.RELATION_NAME_PROJECT_LEADER_MAP);// 选课表对象
            // 设置关联
            userProjectLeaderMap.put(RELATION_PROJECT_LEADER_MAP.RELATION_FIELD_LEADER, currentUser);
            userProjectLeaderMap.put(RELATION_PROJECT_LEADER_MAP.RELATION_FILED_PROJECT, project);
            // 保存中间表对象
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

        /**
         * 加入项目 v1.0
         *
         * @param projectObjId 目标项目objectID
         * @param userObjId    加入成员objectID
         */
        public static void JoinProject(String projectObjId, String userObjId) {

            //  构建对象
            final AVObject[] instanceProject = new AVObject[1];
            final AVUser[] instanceMember = new AVUser[1];

            //  获取对象
            AVQuery<AVObject> query1 = new AVQuery<>(TABLE_NAME_PROJECT);
            query1.whereEqualTo(TABLE_FIELD_OBJECT_ID, projectObjId);
            AVQuery<AVUser> query2 = new AVQuery<>(CLASS_USER.TABLE_NAME_USER);
            query2.whereEqualTo(TABLE_FIELD_OBJECT_ID, userObjId);


            query1.findInBackground().subscribe(new Observer<List<AVObject>>() {
                public void onSubscribe(Disposable disposable) {
                }

                public void onNext(List<AVObject> projects) {
                    instanceProject[0] = projects.get(0);


                    query2.findInBackground().subscribe(new Observer<List<AVUser>>() {
                        public void onSubscribe(Disposable disposable) {
                        }

                        public void onNext(List<AVUser> users) {
                            instanceMember[0] = users.get(0);

                            //  构建新的project-member-map中间表
                            AVObject map = new AVObject(RELATION_PROJECT_MEMBER_MAP.RELATION_NAME_PROJECT_MEMBER_MAP);

                            // 设置关联
                            map.put(RELATION_FILED_PROJECT, instanceProject[0]);
                            map.put(RELATION_FIELD_MEMBER, instanceMember[0]);

                            // 保存中间表
                            map.saveInBackground().subscribe(new Observer<AVObject>() {
                                public void onSubscribe(Disposable disposable) {
                                }

                                public void onNext(AVObject todo) {
                                    // 成功保存之后，执行其他逻辑
                                    Log.d("mytest", "join project: 中间表保存成功。");
                                    QueryProjectResultPopup.joinSuccess = true;

                                }

                                public void onError(Throwable throwable) {
                                    MyApplication.ToastyError("error");
                                    // 异常处理
                                    Log.d("mytest", "join project: " + throwable);
                                }

                                public void onComplete() {
                                }
                            });

                        }

                        public void onError(Throwable throwable) {
                        }

                        public void onComplete() {
                        }
                    });

                }

                public void onError(Throwable throwable) {
                }

                public void onComplete() {
                }
            });


        }

    }

    /**
     * 用户表 字段与方法
     */
    public static class CLASS_USER {

        /**
         * 用户表名称：项目
         */
        public static final String TABLE_NAME_USER = "_User";

        /**
         * 用户数据表字段名称：对象ID
         */
        public static final String TABLE_FIELD_OBJECT_ID = "objectId";

        /**
         * 用户数据表字段名称：用户12位ID
         */
        public static final String TABLE_FIELD_USER_ID = "userId";

        /**
         * 用户数据表字段名称：用户名
         */
        public static final String TABLE_FIELD_USER_NAME = "username";

        /**
         * 用户数据表字段名称：用户手机
         */
        public static final String TABLE_FIELD_USER_PHONE = "mobilePhoneNumber";


        /**
         * 用户数据表字段名称：用户密码
         */
        public static final String TABLE_FIELD_USER_PWD = "password";


        /**
         * 用户数据表字段名称：用户installationId
         */
        public static final String TABLE_FIELD_USER_INSTALLATION_ID = "installationId";


        /**
         * 用户数据表字段名称：用户头像
         */
        public static final String TABLE_FIELD_USER_AVATAR = "avatar";

    }

    /**
     * 中间表 字段与方法
     */
    public static class RELATION_PROJECT_LEADER_MAP {
        /**
         * 中间表名称：用户、项目、项目负责人中间表
         */
        public static final String RELATION_NAME_PROJECT_LEADER_MAP = "UserProjectLeaderMap";
        /**
         * 中间表字段：leader
         */
        public static final String RELATION_FIELD_LEADER = "leader";

        /**
         * 中间表字段：项目
         */
        public static final String RELATION_FILED_PROJECT = "project";
    }

    /**
     * 中间表 字段与方法
     */
    public static class RELATION_PROJECT_MEMBER_MAP {

        /**
         * 中间表名称：用户、项目、项目成员中间表
         */
        public static final String RELATION_NAME_PROJECT_MEMBER_MAP = "UserProjectMemberMap";
        /**
         * 中间表字段：leader
         */
        public static final String RELATION_FIELD_MEMBER = "member";

        /**
         * 中间表字段：项目
         */
        public static final String RELATION_FILED_PROJECT = "project";

    }


//    //未完成
//    public static void JoinProject(String projectId) {
//
//        //  检查当前是否是用户登录的状态
//        AVUser currentUser = AVUser.getCurrentUser();
//        if (currentUser == null) {
//            MyApplication.ToastyWarning("当前无用户登录");
//            return; // 直接return 结束创建操作
//        }
//
//        AVQuery<AVObject> queryProject = new AVQuery<>(TABLE_NAME_PROJECT);
//        queryProject.whereEqualTo("lastName", projectId);
//        queryProject.findInBackground().subscribe(new Observer<List<AVObject>>() {
//            public void onSubscribe(Disposable disposable) {
//            }
//
//            public void onNext(List<AVObject> projects) {
//                // students 是包含满足条件的 Student 对象的数组
//
//            }
//
//            public void onError(Throwable throwable) {
//            }
//
//            public void onComplete() {
//            }
//        });
//
//
//    }


}
