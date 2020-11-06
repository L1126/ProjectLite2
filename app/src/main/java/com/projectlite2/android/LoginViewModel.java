package com.projectlite2.android;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import android.widget.Toast;

import cn.leancloud.AVUser;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public class LoginViewModel extends ViewModel {

    private SavedStateHandle handle;

    public MutableLiveData<String> userName;
    public MutableLiveData<String> phoneNumber;
    public MutableLiveData<String> password;


    public LoginViewModel(SavedStateHandle handle) {
        this.handle = handle;
    }

    public MutableLiveData<String> getUserName() {
        if(userName == null) {
            userName = new MutableLiveData<String>();
            userName.setValue("");
        }
        return userName;
    }

    public MutableLiveData<String> getPhoneNumber() {
        if(phoneNumber == null) {
            phoneNumber = new MutableLiveData<String>();
            phoneNumber.setValue("");
        }
        return phoneNumber;
    }

    public MutableLiveData<String> getPassword() {
        if(password == null) {
            password = new MutableLiveData<String>();
            password.setValue("");
        }
        return password;
    }

    public void setPassword(String password) {
        this.password.setValue(password);
    }

    public void Login() {

    }
}
