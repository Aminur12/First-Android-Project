package com.anjon.manager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.anjon.model.User;

/**
 * Created by anjon on 5/11/2016.
 */
public class DatabaseManager extends SQLiteOpenHelper {
    static final String DB_Name = "MobileAppDb";
    public static final int DbVersion = 11;

    public static final String UserTable = "Users";
    public static final String Name = "Name";
    public static final String Email = "Email";
    public static final String Phone = "Phone";
    public static final String Password = "Password";
    public static final String Token = "Token";
    public static final String UserId = "UserId";

    public DatabaseManager(Context context) {
        super(context, DB_Name, null, DbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            String UserTableQuery = "CREATE TABLE IF NOT EXISTS " + UserTable + " ("
                    + UserId + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                    + Name + " varchar(50) not null, "
                    + Email + " varchar(50) not null, "
                    + Phone + " varchar(50) not null, "
                    + Password + " varchar(50) not null"
                    + Token + " varchar(50) not null"
                    + " );";
            db.execSQL(UserTableQuery);
        } catch (Exception e) {

        }
    }

    public void InsertUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Name, user.getName());
        values.put(Email, user.getEmail());
        values.put(Phone, user.getPhone());
        values.put(Password, user.getPassword());
        values.put(Token, user.getToken());

        db.insert(UserTable, null, values);
        db.close();
    }

    public User GetUserByEmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + UserTable + " where " + Email + " = " + email, null);
        User user = null;
        if (cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                user = new User();
                user.setId(cursor.getLong(cursor.getColumnIndex(UserId)));
                user.setName(cursor.getString(cursor.getColumnIndex(Name)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(Email)));
                user.setPhone(cursor.getString(cursor.getColumnIndex(Phone)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(Password)));
                user.setToken(cursor.getString(cursor.getColumnIndex(Token)));
                cursor.close();
                db.close();
                return user;
            }
        }
        cursor.close();
        db.close();
        return user;
    }

    public User GetUserByToken(String token) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + UserTable + " where " + Token + " = " + token, null);
        User user = null;
        if (cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                user = new User();
                user.setId(cursor.getLong(cursor.getColumnIndex(UserId)));
                user.setName(cursor.getString(cursor.getColumnIndex(Name)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(Email)));
                user.setPhone(cursor.getString(cursor.getColumnIndex(Phone)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(Password)));
                user.setToken(cursor.getString(cursor.getColumnIndex(Token)));
                cursor.close();
                db.close();
                return user;
            }
        }
        cursor.close();
        db.close();
        return user;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
