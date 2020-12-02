package com.projectlite2.android.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

public class VerifyPhoneViewModel extends ViewModel {

    private SavedStateHandle handle;


    public MutableLiveData<String> phoneNumber;
    public MutableLiveData<String> smsCode;


    public VerifyPhoneViewModel(SavedStateHandle handle) {
        this.handle = handle;
    }


    public MutableLiveData<String> getPhoneNumber() {
        if(phoneNumber == null) {
            phoneNumber = new MutableLiveData<String>();
            phoneNumber.setValue("");
        }
        return phoneNumber;
    }

    public MutableLiveData<String> getSmsCode() {
        if(smsCode == null) {
            smsCode = new MutableLiveData<String>();
            smsCode.setValue("");
        }
        return smsCode;
    }


    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber.setValue(phoneNumber);
    }

    public void setPassword(String password) {
        this.smsCode.setValue(password);
    }

    public void Login() {

    }
}
