package com.projectlite2.android.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

public class UserInfoSaveSharedPreference
{
  public static final String PREF_NOT_FIRST_USE="notFirstUse";
  public static final String PREF_USER_NAME= "username";
  public static final String PREF_USER_PASSWORD= "password";
  public static final String PREF_USER_PHONE= "phone";
  public static final String PREF_NULL_VALUE= "null";

    static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }


    public static void setNotFirstUse(Context ctx, Boolean bool)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putBoolean(PREF_NOT_FIRST_USE, bool);
        editor.apply();
    }

    public static void setUserName(Context ctx, String userName)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_NAME, userName);
        editor.apply();
    }
    public static void setUserPhone(Context ctx, String userPhone)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_PHONE, userPhone);
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
        return getSharedPreferences(ctx).getString(PREF_USER_NAME, PREF_NULL_VALUE);
    }
    public static String getUserPhone(Context ctx)
    {
        return getSharedPreferences(ctx).getString(PREF_USER_PHONE, PREF_NULL_VALUE);
    }
    public static String getUserPwd(Context ctx)
    {
        return getSharedPreferences(ctx).getString(PREF_USER_PASSWORD, PREF_NULL_VALUE);
    }
    public static Boolean getPrefNotFirstUse(Context ctx) {
        return getSharedPreferences(ctx).getBoolean(PREF_NOT_FIRST_USE, false);
    }
}