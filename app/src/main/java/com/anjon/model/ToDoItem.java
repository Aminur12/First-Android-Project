package com.anjon.model;

/**
 * Created by anjon on 5/22/2016.
 */
public class ToDoItem {
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDueDateTime() {
        return dueDateTime;
    }

    public void setDueDateTime(String dueDateTime) {
        this.dueDateTime = dueDateTime;
    }

    private long id;
    private String title;
    private String dueDateTime;

}
