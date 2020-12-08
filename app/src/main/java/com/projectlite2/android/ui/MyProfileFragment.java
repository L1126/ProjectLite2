package com.projectlite2.android.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.BasePopupView;
import com.lxj.xpopup.interfaces.OnSelectListener;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.projectlite2.android.R;
import com.projectlite2.android.activity.ModifyMyProfileCardActivity;
import com.projectlite2.android.activity.SystemSettingActivity;
import com.projectlite2.android.adapter.MyProfileSettingItemAdapter;
import com.projectlite2.android.app.MyApplication;
import com.projectlite2.android.model.MyProfileSettingItem;
import com.projectlite2.android.utils.BitmapUtils;
import com.projectlite2.android.utils.CloudUtil;
import com.projectlite2.android.utils.Constant;
import com.projectlite2.android.utils.GetImagePathUtil;
import com.projectlite2.android.utils.OnItemClickListener;
import com.projectlite2.android.viewmodel.MyProfileViewModel;
import com.yanzhenjie.recyclerview.SwipeRecyclerView;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.leancloud.AVFile;
import cn.leancloud.AVObject;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static android.app.Activity.RESULT_OK;
import static com.projectlite2.android.utils.CloudUtil.CLASS_USER.TABLE_FIELD_USER_AVATAR;
import static com.projectlite2.android.utils.CloudUtil.CLASS_USER.TABLE_FIELD_USER_ID;


public class MyProfileFragment extends Fragment {

    private MyProfileViewModel mViewModel;

    View mView;
    SwipeRecyclerView mRecyclerView;
    CircularImageView mImgAvatar;
    MyProfileSettingItemAdapter mAdapter;
    Toolbar toolBar;
    TextView txtMyName;
    MyProfileFragment thisFragment;

    public Bitmap avatarBitmap = null;
    public String mAvatarPath=null;

    @BindView(R.id.iconStatus)
    ImageView mImgIcStatus;

    @BindView(R.id.imgBg)
    ImageView mImgBackground;


    private ArrayList<MyProfileSettingItem> settingList = new ArrayList<MyProfileSettingItem>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        thisFragment=this;

        mView = inflater.inflate(R.layout.my_profile_fragment, container, false);
        ButterKnife.bind(this, mView);

        toolBar = mView.findViewById(R.id.toolBar);
        mImgAvatar = mView.findViewById(R.id.imgBtnUserAvatar);
        txtMyName = mView.findViewById(R.id.txtMyName);


        initMyProfileInterface();


        return mView;

    }


    /**
     * 初始化界面
     */
    public void initMyProfileInterface() {
        txtMyName.setText(CloudUtil.CURRENT_USER.name);
        if(CloudUtil.CURRENT_USER.avatar!=null){
            CloudUtil.CURRENT_USER.avatar.getDataStreamInBackground().subscribe(new Observer<InputStream>() {
                @Override
                public void onSubscribe(Disposable d) {
                }

                @Override
                public void onNext(InputStream inputStream) {
                    try {
                        Bitmap decodeBit = BitmapFactory.decodeStream(inputStream);
                        inputStream.close();
                        decodeBit = BitmapUtils.createCircleBitmap(decodeBit, 0, false, mImgAvatar.getWidth(), mImgAvatar.getHeight());
                        mImgAvatar.setImageBitmap(decodeBit);


                    } catch (Exception ex) {
                        ex.printStackTrace();
                        Log.d("mytest", " MyProfileFragment   failed to get data. cause: " + ex);

                    }
                }

                @Override
                public void onError(Throwable e) {
                    Log.d("mytest", "MyProfileFragment  failed to get data. cause: " + e);
                }

                @Override
                public void onComplete() {
                }
            });
        }

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mViewModel = new ViewModelProvider(this).get(MyProfileViewModel.class);

        addSettings();
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView = mView.findViewById(R.id.settingsRecyclerView);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new MyProfileSettingItemAdapter(settingList);
        mRecyclerView.setAdapter(mAdapter);


        mAdapter.setOnKotlinItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClickListener(int position) {
                switch (position) {
                    case Constant.MyProfileSettingItemPosition.MY_PROFILE_CARD:

                        Intent modifyMyCardIntent = new Intent(MyApplication.getContext(), ModifyMyProfileCardActivity.class);
                        startActivity(modifyMyCardIntent);
                        break;
                    case Constant.MyProfileSettingItemPosition.SYSTEM_SETTING:
                        Intent systemSettingIntent = new Intent(MyApplication.getContext(), SystemSettingActivity.class);

                        startActivity(systemSettingIntent);
                        break;
                    default:
                        break;
                }
            }
        });


    }

    private void addSettings() {
        settingList.add(new MyProfileSettingItem(R.drawable.ic_baseline_contact_mail_24, "我的名片"));
        settingList.add(new MyProfileSettingItem(R.drawable.ic_baseline_settings_24, "设置"));
    }

    @OnClick({R.id.imgBg, R.id.imgBtnUserAvatar})
    public void OnClick(View v) {
        switch (v.getId()) {
            case R.id.imgBg: {
                // 弹窗修改背景
                new XPopup.Builder(getContext())
                        .asBottomList("修改个人中心背景", new String[]{"从本地相册选择", "使用相机拍摄"},
                                new OnSelectListener() {
                                    @Override
                                    public void onSelect(int position, String text) {
                                        Log.d("mytest", "修改个人中心背景: ");

                                    }
                                })
                        .show();
                break;
            }
            case R.id.imgBtnUserAvatar: {
                // 弹窗修改头像

                new XPopup.Builder(getContext())
                        .asBottomList("修改我的头像", new String[]{"从本地相册选择", "使用相机拍摄"},
                                new OnSelectListener() {
                                    @Override
                                    public void onSelect(int position, String text) {
                                        switch (position) {
                                            case 0:

                                                MyApplication.GetPicFromAlbm(getActivity(), thisFragment);
                                                break;
                                            case 1:

                                                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                                                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                                                } else {
                                                    MyApplication.TakePicture(getActivity(), thisFragment);

                                                }
                                            default:break;
                                        }
                                    }
                                })
                        .show();
                break;
            }
            default:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("mytest", "onActivityResult: fragment");
        switch (requestCode) {
            //调用相机后返回
            case MyApplication.CAMERA_REQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    //用相机返回的照片去调用剪裁也需要对Uri进行处理

                    try {

                        avatarBitmap = BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(MyApplication.contentUri));
                        //mAvatarPath = GetImagePathUtil.getPathFromUri(getActivity(), data.getData());
                        avatarBitmap = BitmapUtils.createCircleBitmap(avatarBitmap, 0, false, mImgAvatar.getWidth(), mImgAvatar.getHeight());
                        //加载显示
                        mImgAvatar.setImageBitmap(avatarBitmap);
                        //保存裁剪后的图像
                        mAvatarPath= MyApplication.saveImageReturnPath(getActivity(), avatarBitmap);

                        //上传到数据库
                        try {
                            CloudUtil.CURRENT_USER.PushAvatarToCloud(mAvatarPath);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                            Log.d("mytest", "onActivityResult: 相机拍照设置头像"+e);
                        }


                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                    break;
                }

                //调用相册后返回
            case MyApplication.ALBUM_REQUEST_CODE:
                if (resultCode == RESULT_OK) {

                    Uri imageData = data.getData();
                    mAvatarPath = GetImagePathUtil.getPathFromUri(getActivity(), imageData);

                    avatarBitmap = BitmapFactory.decodeFile(mAvatarPath);
                    avatarBitmap = BitmapUtils.createCircleBitmap(avatarBitmap, 0, false, mImgAvatar.getWidth(), mImgAvatar.getHeight());

                    //加载显示
                    mImgAvatar.setImageBitmap(avatarBitmap);
                    //保存裁剪后的图像
                    mAvatarPath=MyApplication.saveImageReturnPath(getActivity(), avatarBitmap);

                    //上传到数据库
                    try {
                        CloudUtil.CURRENT_USER.PushAvatarToCloud(mAvatarPath);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                        Log.d("mytest", "onActivityResult: 相册设置头像"+e);
                    }
                    break;
                }
        }

    }

}