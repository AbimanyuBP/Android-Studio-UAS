package com.devting.wombat.aplikasi_todo_list.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

/**
 * Created by enyason on 5/28/18.
 */

@Entity(tableName = "task")
public class Task {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String description;
    private String priority;

    @ColumnInfo(name = "updated_at")
    private Date updatedAt;


    public Task(String description, String priority, Date updatedAt) {
        this.description = description;
        this.priority = priority;
        this.updatedAt = updatedAt;
    }


    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getPriority() {
        return priority;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
