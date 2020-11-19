package com.projectlite2.android;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class UserInfoSaveSharedPreference
{
    static final String PREF_USER_NAME= "username";
    static final String PREF_USER_PASSWORD= "password";
    static final String PREF_USER_PHONE= "phone";

    static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }


    public static void setUserName(Context ctx, String userName)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_NAME, userName);
        editor.apply();
    }
    public static void setUserPhone(Context ctx, String userName)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_PHONE, userName);
        editor.apply();
    }
    public static void setUserPwd(Context ctx, String userPwd)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_PASSWORD, userPwd);
        editor.apply();
    }




    public static String getUserName(Context ctx)
    {
        return getSharedPreferences(ctx).getString(PREF_USER_NAME, "");
    }
    public static String getUserPhone(Context ctx)
    {
        return getSharedPreferences(ctx).getString(PREF_USER_PHONE, "");
    }
    public static String getUserPwd(Context ctx)
    {
        return getSharedPreferences(ctx).getString(PREF_USER_PASSWORD, "");
    }
}