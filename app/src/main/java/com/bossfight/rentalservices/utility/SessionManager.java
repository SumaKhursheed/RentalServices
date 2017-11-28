package com.bossfight.rentalservices.utility;

/**
 * Created by divyankithaRaghavaUrs on 4/14/17.
 */

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.bossfight.rentalservices.startup.LoginActivity;

import java.util.HashMap;

public class SessionManager
{

    SharedPreferences pref;
    Editor editor;
    Context _context;
    int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "AndroidHivePref";
    private static final String IS_LOGIN = "IsLoggedIn";
    public static final String KEY_NAME = "name";
    public static final String KEY_ID = "userId";
    public static final String KEY_TYPE = "type";


    public SessionManager(Context context)
    {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }


    public void createLoginSession(String Username, String ID, String type)
    {

        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_NAME, Username);
        editor.putString(KEY_ID,ID);
        editor.putString(KEY_TYPE,type);
        editor.commit();
    }


    public void checkLogin()
    {
        System.out.println("Inside checklogin");
        if(!this.isLoggedIn())
        {
            System.out.println("Inside checklogin if statement");
            Intent i = new Intent(_context, LoginActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            _context.startActivity(i);
        }

    }


    public HashMap<String, String> getUserDetails()
    {
        HashMap<String, String> user = new HashMap<String, String>();

        user.put(KEY_NAME, pref.getString(KEY_NAME, null));
        user.put(KEY_ID, pref.getString(KEY_ID, "000"));
        user.put(KEY_TYPE, pref.getString(KEY_TYPE, null));

        return user;
    }


    public void logoutUser()
    {
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();
        Intent i = new Intent(_context, LoginActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        _context.startActivity(i);
    }


    public boolean isLoggedIn()
    {
        return pref.getBoolean(IS_LOGIN, false);
    }

}