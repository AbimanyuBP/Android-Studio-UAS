package com.example.AplikasiTodoList;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.AplikasiTodoList.dao.HewanDAO;
import com.example.AplikasiTodoList.dao.KandangDAO;
import com.example.AplikasiTodoList.model.DateTypeConverter;
import com.example.AplikasiTodoList.model.Hewan;
import com.example.AplikasiTodoList.model.Kandang;

@Database(entities = {Kandang.class, Hewan.class}, version = 1)
@TypeConverters({DateTypeConverter.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract KandangDAO getKandangDAO();
    public abstract HewanDAO getHewanDAO();
}