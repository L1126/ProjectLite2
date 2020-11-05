package com.projectlite2.android;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import android.widget.Toast;



public class LoginViewModel extends ViewModel {
    public MutableLiveData<String> userName;
    public MutableLiveData<String> phoneNumber;
    public MutableLiveData<String> password;

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
        Toast.makeText(MyApplication.getContext(),getPassword().getValue(),Toast.LENGTH_SHORT).show();

    }
}
