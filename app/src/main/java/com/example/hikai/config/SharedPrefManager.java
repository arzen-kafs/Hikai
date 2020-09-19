package com.example.hikai.config;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.hikai.User;

public class SharedPrefManager {

    private static final String SHARED_PREF_NAME = "participant";

    private static final String ID = "keyid";
    private static final String USERNAME = "keyUsername";
    private static final String PASSWORD="password";
    private static final String USER_CLASS="class";


    private static SharedPrefManager mInstance;// Shared Preference Instance
    private static Context ctx;

    private SharedPrefManager(Context context) {
        ctx = context;
    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }

    //this method will store the user data in shared preferences
    public void userLogin(User user) {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(ID, user.getId());
        editor.putString(USERNAME, user.getName());
        editor.putString(PASSWORD,user.getPassword());
        editor.putString(USER_CLASS,user.getStandard());
        editor.apply();
    }

    //this method will check whether user is already logged in or not
    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(USERNAME, null) != null;
}

    //this method will give the logged in user
    public User getUser() {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new User(
                sharedPreferences.getInt(ID, -1),
                sharedPreferences.getString(USERNAME, "Dependra"),
                sharedPreferences.getString(USER_CLASS,null),
                sharedPreferences.getString(PASSWORD,null)
        );
    }
}
