package com.projectlite2.android.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;

import com.projectlite2.android.LoginViewModel;
import com.projectlite2.android.R;
import com.projectlite2.android.activity.MainActivity;
import com.projectlite2.android.app.MyApplication;
import com.projectlite2.android.databinding.FragmentLoginBinding;
import com.projectlite2.android.utils.BitmapUtils;
import com.projectlite2.android.utils.CloudUtil;

import org.jetbrains.annotations.NotNull;

import java.io.InputStream;
import java.util.List;

import cn.leancloud.AVFile;
import cn.leancloud.AVQuery;
import cn.leancloud.AVUser;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static androidx.navigation.Navigation.findNavController;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        LoginViewModel viewModel = new ViewModelProvider(getActivity()).get(LoginViewModel.class);
        FragmentLoginBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);
        binding.setModel(viewModel);
        binding.setLifecycleOwner(getActivity());

        // 登录按钮
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
<<<<<<< HEAD
                String phone = binding.txtPhone.getText().toString();
                String password = binding.txtPassword.getText().toString();


                if (!MyApplication.isMobilePhoneNum(phone)) {
                    MyApplication.ToastyWarning("请输入合法的手机号");
                    return;
                }
                if (password.equals("")) {
                    MyApplication.ToastyWarning("请输入密码");
                    return;
                }

                LoginWithPhoneAndPwd(phone, password, binding);

            }
        });


//          获取手机号码文本框的焦点监听
        binding.txtPhone.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                Log.d("mytest", "login: avatar start");
                if (hasFocus) {
                    binding.imgProfilePicture.setImageResource(R.drawable.ic_baseline_account_circle_24);
                } else {
                    AVQuery<AVUser> query = new AVQuery<>(CloudUtil.CLASS_USER.TABLE_NAME_USER);
                    query.whereEqualTo(CloudUtil.CLASS_USER.TABLE_FIELD_USER_PHONE, "+86" + binding.txtPhone.getText().toString());
                    query.findInBackground().subscribe(new Observer<List<AVUser>>() {
                        public void onSubscribe(Disposable disposable) {
                        }

                        public void onNext(List<AVUser> users) {
                            // students 是包含满足条件的 Student 对象的数组
                            if (users.size() > 0) {
                                Log.d("mytest", "login: avatar continue" + users.get(0).getObjectId());

                                AVFile avatar = (AVFile) users.get(0).get(CloudUtil.CLASS_USER.TABLE_FIELD_USER_AVATAR);
                                if (avatar != null) {

                                    avatar.getDataStreamInBackground().subscribe(new Observer<InputStream>() {
                                        @Override
                                        public void onSubscribe(Disposable d) {
                                        }

                                        @Override
                                        public void onNext(InputStream inputStream) {
                                            try {
                                                Bitmap decodeBit = BitmapFactory.decodeStream(inputStream);
                                                inputStream.close();
                                                decodeBit = BitmapUtils.createCircleBitmap(decodeBit, 0, false, binding.imgProfilePicture.getWidth(), binding.imgProfilePicture.getHeight());
                                                binding.imgProfilePicture.setImageBitmap(decodeBit);


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


                        }

                        public void onError(Throwable throwable) {
                            Log.d("mytest", "login: avatar query " + throwable);
                        }

                        public void onComplete() {
                        }
                    });
                }
            }
        });


//         使用短信验证码登录-跳转
        binding.linkSMSLogin.setOnClickListener(new View.OnClickListener() {
=======
                String phone=binding.txtPhone.getText().toString();
                String password=binding.txtPassword.getText().toString();
                AVUser.loginByMobilePhoneNumber(phone,password).subscribe(new Observer<AVUser>() {
                    public void onSubscribe(Disposable disposable) {
                    }

                    public void onNext(AVUser user) {

                        // 登录成功
                        // MyApplication.showToast(phone+" "+password+" 登陆成功");
                        MyApplication.ToastySuccess(phone+" "+password+" 登陆成功");
                        // 若勾选了记住密码
                        if(binding.chkRememberPwd.isChecked()){
                            // 保存sharedPreferences
                            UserInfoSaveSharedPreference.setUserPhone(MyApplication.getContext(),phone);
                            UserInfoSaveSharedPreference.setUserPwd(MyApplication.getContext(),password);
                        }
                        else{
                            // 保存sharedPreferences
                            UserInfoSaveSharedPreference.setUserPhone(MyApplication.getContext(),UserInfoSaveSharedPreference.PREF_NULL_VALUE);
                            UserInfoSaveSharedPreference.setUserPwd(MyApplication.getContext(),UserInfoSaveSharedPreference.PREF_NULL_VALUE);
                        }

                        // 跳转到主页
                        Intent intent = new Intent(MyApplication.getContext(), MainActivity.class);
                        startActivity(intent);
                        // 销毁LoginActivity
                        getActivity().finish();
                    }

                    public void onError(@NotNull Throwable throwable) {
                        // 登录失败（可能是密码错误）
                       // MyApplication.showToast(phone+" "+password+" 登陆失败");
                        MyApplication.ToastyError("登录失败！");
                    }

                    public void onComplete() {
                    }
                });
            }
        });
        // 使用短信验证码登录-跳转
        binding.linkSMSLogin.setOnClickListener(new View.OnClickListener(){
>>>>>>> 2b65d275b8abc085741fbf892a09bc5366e41c3d
            @Override
            public void onClick(View v) {
                NavController navController = findNavController(v);
                navController.navigate(R.id.action_loginFragment2_to_loginSMSFragment);
            }
        });
        // 注册新账户-跳转-
        binding.linkSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = findNavController(v);
                // 传递参数，跳转到注册
                Bundle bundle = new Bundle();
                bundle.putString("JUMP_TO", "SIGN_UP");
                navController.navigate(R.id.action_loginFragment2_to_varifyPhoneFragment, bundle);

            }
        });
        // 忘记密码-跳转
        binding.linkForgetpwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = findNavController(v);
                // 传递参数，跳转到修改密码
                Bundle bundle = new Bundle();
                bundle.putString("JUMP_TO", "MODIFY_PASSWORD");
                navController.navigate(R.id.action_loginFragment2_to_varifyPhoneFragment, bundle);
            }
        });


        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }


<<<<<<< HEAD
    /**
     * 使用 手机号码 和 密码 登录客户端
     *
     * @param phone    手机号码，不加86
     * @param password 密码
     * @param binding  databinding类，监听checkbox。如果AVUSER可以检测客户端是否存在当前账户，则可舍弃shp
     */
    public void LoginWithPhoneAndPwd(String phone, String password, FragmentLoginBinding binding) {
        AVUser.loginByMobilePhoneNumber("+86" + phone, password).subscribe(new Observer<AVUser>() {
            public void onSubscribe(Disposable disposable) {
            }

            public void onNext(AVUser user) {

                // 登录成功
                MyApplication.ToastySuccess(phone + " " + password + " 登陆成功");

                //  进入主页
                Intent it = new Intent(MyApplication.getContext(), MainActivity.class);
                it.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(it);

            }

            public void onError(@NotNull Throwable throwable) {
                // 登录失败（可能是密码错误）
                // MyApplication.showToast(phone+" "+password+" 登陆失败");
                MyApplication.ToastyError("登录失败！");
            }

            public void onComplete() {
            }
        });

    }

=======
>>>>>>> 2b65d275b8abc085741fbf892a09bc5366e41c3d

}