package com.anjon.manager;

import android.content.Context;

import com.anjon.model.User;

/**
 * Created by anjon on 5/22/2016.
 */
public class UserManager {
    private static DatabaseManager DBManager;

    public UserManager(Context context) {
        DBManager = new DatabaseManager(context);
    }

    public void InsertUser(User user) {
        DBManager.InsertUser(user);
    }

    public User GetUserByEmail(String email) {
        return DBManager.GetUserByEmail(email);
    }

    public User GetUserByToken(String token) {
        return DBManager.GetUserByToken(token);
    }
}
