package com.example.sisteminformasikebunbinatang;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.sisteminformasikebunbinatang.dao.HewanDAO;
import com.example.sisteminformasikebunbinatang.dao.KandangDAO;
import com.example.sisteminformasikebunbinatang.model.DateTypeConverter;
import com.example.sisteminformasikebunbinatang.model.Hewan;
import com.example.sisteminformasikebunbinatang.model.Kandang;

@Database(entities = {Kandang.class, Hewan.class}, version = 1)
@TypeConverters({DateTypeConverter.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract KandangDAO getKandangDAO();
    public abstract HewanDAO getHewanDAO();
}