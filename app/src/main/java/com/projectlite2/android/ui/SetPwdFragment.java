package com.projectlite2.android.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.interfaces.OnSelectListener;
import com.projectlite2.android.LoginViewModel;
import com.projectlite2.android.R;
import com.projectlite2.android.activity.MainActivity;
import com.projectlite2.android.app.MyApplication;
import com.projectlite2.android.databinding.FragmentSetPwdBinding;
import com.projectlite2.android.utils.BitmapUtils;
import com.projectlite2.android.utils.GetImagePathUtil;
import com.projectlite2.android.utils.StoragePermissionUtil;

<<<<<<< HEAD
import java.io.FileNotFoundException;
import java.util.HashMap;

import cn.leancloud.AVFile;
import cn.leancloud.AVObject;
=======
>>>>>>> 2b65d275b8abc085741fbf892a09bc5366e41c3d
import cn.leancloud.AVUser;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static android.app.Activity.RESULT_OK;
import static com.projectlite2.android.utils.CloudUtil.CLASS_USER.TABLE_FIELD_USER_AVATAR;
import static com.projectlite2.android.utils.CloudUtil.CLASS_USER.TABLE_FIELD_USER_ID;

/**
 * A simple {@link Fragment} subclass.
 */
public class SetPwdFragment extends Fragment {

    private String _phoneNumber;
    private String _smsCode;

    private View mView;

    private Fragment thisFragment;

    public ImageButton imgAvatar;

    public String mAvatarPath;

    public Boolean hasSetAvatar = false;

    public Bitmap avatarBitmap = null;
    public Uri uri= null;

    public SetPwdFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        thisFragment = this;
        LoginViewModel viewModel = new ViewModelProvider(getActivity()).get(LoginViewModel.class);
        FragmentSetPwdBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_set_pwd, container, false);


        binding.setModel(viewModel);
        binding.setLifecycleOwner(getActivity());


        binding.btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 显示手机号是否正确传递
                MyApplication.ToastyInfo(_phoneNumber);

                String password = binding.txtPwd.getText().toString();
                String confirm = binding.txtConfirmPwd.getText().toString();
                String userName = binding.txtUserName.getText().toString();

                if (userName.isEmpty()) {
                    MyApplication.ToastyWarning("请输入用户名");
                    return;
                }
                if (password.isEmpty()) {
                    MyApplication.ToastyWarning("请输入密码");
                    return;
                }
                if (confirm.isEmpty()) {
                    MyApplication.ToastyWarning("请确认密码");
                    return;
                }

                //  如果两次密码输入不同则重新输入
                if (!password.equals(confirm)) {
                    MyApplication.ToastyWarning("两次密码输入不同，请检查输入。");
                    return;
                } else {
<<<<<<< HEAD

                    try {
                        SetPassword(userName, password);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }


=======
                    SignUp(userName, _phoneNumber, password);
>>>>>>> 2b65d275b8abc085741fbf892a09bc5366e41c3d
                }
            }
        });

        return binding.getRoot();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @androidx.annotation.NonNull String[] permissions, @androidx.annotation.NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1: {
                //  申请相机权限
                if (grantResults.length != 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    MyApplication.TakePicture(getActivity(), thisFragment);
                } else {
                    MyApplication.ToastyWarning("拒绝授予权限");
                }


                break;
            }
            default:
                break;
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
<<<<<<< HEAD


=======
>>>>>>> 2b65d275b8abc085741fbf892a09bc5366e41c3d
        //  接收参数
        _phoneNumber = getArguments().getString("PHONE_NUMBER");
        _smsCode = getArguments().getString("SMS_CODE");

        mView = getView();
        // 头像的点击事件
        imgAvatar = mView.findViewById(R.id.btnProfilePicture);
        imgAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new XPopup.Builder(getContext())
                        .asBottomList("设置头像", new String[]{"从本地相册选择", "使用相机拍摄"},
                                new OnSelectListener() {
                                    @Override
                                    public void onSelect(int position, String text) {

                                        StoragePermissionUtil.verifyStoragePermissions(getActivity());

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


                                                break;
                                            default:
                                                break;

                                        }
                                    }
                                })
                        .show();
            }
        });
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
                        avatarBitmap = BitmapUtils.createCircleBitmap(avatarBitmap, 0, false, imgAvatar.getWidth(), imgAvatar.getHeight());
                        //加载显示
                        imgAvatar.setImageBitmap(avatarBitmap);
                        hasSetAvatar = true;

                        mAvatarPath= MyApplication.saveImageReturnPath(getActivity(), avatarBitmap);
                        //保存裁剪后的图像

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
                    avatarBitmap = BitmapUtils.createCircleBitmap(avatarBitmap, 0, false, imgAvatar.getWidth(), imgAvatar.getHeight());

                    //加载显示
                    imgAvatar.setImageBitmap(avatarBitmap);
                    hasSetAvatar = true;


                    Log.d("mytest", "setImageBitmap: done");


                    //saveImage(bitmap2);
                    //保存裁剪后的图像

                    break;
                }
        }

    }

    /**
     * 使用手机号码和密码注册
     *
     * @param userName 用户名
     * @param phone    手机号
     * @param password 密码
     */
<<<<<<< HEAD
    public void SetPassword(String userName, String password) throws FileNotFoundException {

        AVUser currentUser = AVUser.getCurrentUser();
        if (currentUser != null) {

            //  构建头像文件
            AVFile file = AVFile.withAbsoluteLocalPath("avatar.jpg", mAvatarPath);
            //  保存到云
            file.saveInBackground().subscribe(new Observer<AVFile>() {
                public void onSubscribe(Disposable disposable) {
                }

                public void onNext(AVFile file) {

                    Log.d("mytest", "头像保存云端。objectId：" + file.getObjectId());

                    currentUser.setUsername(userName);
                    currentUser.setPassword(password);
                    currentUser.put(TABLE_FIELD_USER_AVATAR, file);
                    currentUser.put(TABLE_FIELD_USER_ID, MyApplication.BuildRandomID());
                    currentUser.saveInBackground().subscribe(new Observer<AVObject>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {
                        }

                        @Override
                        public void onNext(@NonNull AVObject avObject) {
                            MyApplication.ToastySuccess("注册成功，正在跳转到登录界面...");

//                            //  进入主页
//                            Intent it = new Intent(MyApplication.getContext(), MainActivity.class);
//                            it.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//                            startActivity(it);

                            MyApplication.navJump(mView,R.id.action_setPwdFragment_to_loginFragment2);

                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            MyApplication.ToastyError("注册失败");
                        }

                        @Override
                        public void onComplete() {
                        }
                    });
                }

                public void onError(Throwable throwable) {
                    // 保存失败，可能是文件无法被读取，或者上传过程中出现问题
                }

                public void onComplete() {
                }
            });


        } else {
            MyApplication.ToastyError("当前无用户");
        }
=======
    public void SignUp(String userName, String phone, String password) {
        // 创建实例
        AVUser user = new AVUser();
        user.setUsername(userName);
        user.setPassword(password);

        // 可选
        user.setMobilePhoneNumber(phone);

        // 设置其他属性的方法跟 AVObject 一样
        user.put("gender", "secret");

        user.signUpInBackground().subscribe(new Observer<AVUser>() {
            public void onSubscribe(Disposable disposable) {
            }

            public void onNext(AVUser user) {
                // 注册成功
                System.out.println("注册成功。objectId：" + user.getObjectId());
                MyApplication.ToastySuccess("注册成功。objectId：" + user.getObjectId());
            }

            public void onError(Throwable throwable) {
                // 注册失败（通常是因为用户名已被使用）
                MyApplication.ToastyError("ERROR");
            }

            public void onComplete() {
            }
        });
>>>>>>> 2b65d275b8abc085741fbf892a09bc5366e41c3d

    }
}