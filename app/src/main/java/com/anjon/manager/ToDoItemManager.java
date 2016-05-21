package com.anjon.manager;

import android.content.Context;

import com.anjon.model.ToDoItem;

import java.util.List;

/**
 * Created by anjon on 5/22/2016.
 */
public class ToDoItemManager {
    private static DatabaseManager DBManager;

    public ToDoItemManager(Context context) {
        DBManager = new DatabaseManager(context);
    }

    public void InsertToDoItem(ToDoItem item) {
        DBManager.InsertToDoItem(item);
    }

    public List<ToDoItem> GetAllToDoItems() {
        return DBManager.GetAllToDoItems();
    }
}
