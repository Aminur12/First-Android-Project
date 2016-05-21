package com.anjon.manager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.anjon.model.ToDoItem;
import com.anjon.model.User;

import java.util.List;

/**
 * Created by anjon on 5/11/2016.
 */
public class DatabaseManager extends SQLiteOpenHelper {
    private static final String DB_Name = "MobileAppDb";
    private static final int DbVersion = 11;

    //region Users table column names
    private static final String UserTable = "Users";
    private static final String Name = "Name";
    private static final String Email = "Email";
    private static final String Phone = "Phone";
    private static final String Password = "Password";
    private static final String Token = "Token";
    private static final String UserId = "UserId";
    //endregion

    //region ToDoItems table column names
    private static final String ToDoItemTable = "ToDoItems";
    private static final String ToDoItemId = "Id";
    private static final String Title = "Title";
    private static final String DueDateTime = "DueDateTime";
    //endregion

    protected DatabaseManager(Context context) {
        super(context, DB_Name, null, DbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            StringBuilder tablesQuery = new StringBuilder();

            //region Users table schema
            tablesQuery.append("CREATE TABLE IF NOT EXISTS ");
            tablesQuery.append(UserTable);
            tablesQuery.append(" (");
            tablesQuery.append(UserId);
            tablesQuery.append(" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, ");
            tablesQuery.append(Name);
            tablesQuery.append(" varchar(50) not null, ");
            tablesQuery.append(Email);
            tablesQuery.append(" varchar(50) not null, ");
            tablesQuery.append(Phone);
            tablesQuery.append(" varchar(50) not null, ");
            tablesQuery.append(Password);
            tablesQuery.append(" varchar(50) not null, ");
            tablesQuery.append(Token);
            tablesQuery.append(" varchar(50) not null");
            tablesQuery.append(" );");
            //endregion

            //region ToDoItems table schema
            tablesQuery.append("CREATE TABLE IF NOT EXISTS ");
            tablesQuery.append(ToDoItemTable);
            tablesQuery.append(" (");
            tablesQuery.append(ToDoItemId);
            tablesQuery.append(" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, ");
            tablesQuery.append(Title);
            tablesQuery.append(" varchar(50) not null, ");
            tablesQuery.append(DueDateTime);
            tablesQuery.append(" varchar(50) not null");
            tablesQuery.append(" );");
            //endregion
            db.execSQL(tablesQuery.toString());
        } catch (Exception e) {

        }
    }

    //region Generic DB operations
    private void Insert(String tableName, ContentValues values) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(tableName, null, values);
        db.close();
    }
    //endregion

    //region User DB operations
    protected void InsertUser(User user) {
        ContentValues values = new ContentValues();
        values.put(Name, user.getName());
        values.put(Email, user.getEmail());
        values.put(Phone, user.getPhone());
        values.put(Password, user.getPassword());
        values.put(Token, user.getToken());
        Insert(UserTable, values);
    }

    protected User GetUserByEmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        StringBuilder query = new StringBuilder();
        query.append("select * from ");
        query.append(UserTable);
        query.append(" where ");
        query.append(Email);
        query.append(" = ");
        query.append(email);
        Cursor cursor = db.rawQuery(query.toString(), null);
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

    protected User GetUserByToken(String token) {
        SQLiteDatabase db = this.getReadableDatabase();
        StringBuilder query = new StringBuilder();
        query.append("select * from ");
        query.append(UserTable);
        query.append(" where ");
        query.append(Token);
        query.append(" = ");
        query.append(token);
        Cursor cursor = db.rawQuery(query.toString(), null);
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
    //endregion

    //region ToDoItem DB operation
    protected void InsertToDoItem(ToDoItem item) {
        ContentValues values = new ContentValues();
        values.put(Title, item.getTitle());
        values.put(DueDateTime, item.getDueDateTime());
        Insert(ToDoItemTable, values);
    }

    protected List<ToDoItem> GetAllToDoItems() {
        List<ToDoItem> list = null;
        SQLiteDatabase db = this.getReadableDatabase();
        StringBuilder query = new StringBuilder();
        query.append("select * from ");
        query.append(ToDoItemTable);
        Cursor cursor = db.rawQuery(query.toString(), null);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                ToDoItem item = new ToDoItem();
                item.setId(cursor.getLong(cursor.getColumnIndex(ToDoItemId)));
                item.setTitle(cursor.getString(cursor.getColumnIndex(Title)));
                item.setDueDateTime(cursor.getString(cursor.getColumnIndex(DueDateTime)));
                list.add(item);
            }
        }
        cursor.close();
        db.close();
        return list;
    }
    //endregion

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
