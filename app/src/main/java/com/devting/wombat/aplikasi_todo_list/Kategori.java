package com.devting.wombat.aplikasi_todo_list;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "Kategori")
public class Kategori {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "nama_kategori")
    private String namaKategoril;

    public String getNamaKategoril() {
        return namaKategoril;
    }

    public void setNamaKategoril(String namaKategoril) {
        this.namaKategoril = namaKategoril;
    }
}
