package com.example.AplikasiTodoList.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Kandang")
public class Kandang {

    @PrimaryKey
    @NonNull private Long id;

    @ColumnInfo(name = "nama_kandang")
    private String namaKandang;

    @ColumnInfo(name = "jenis_hewan")
    private char jenisHewan='H';

    @ColumnInfo(name = "kapasitas")
    private int kapasitasHewan;

    @ColumnInfo(name = "sisa_kapasitas")
    private int sisaKapasitas;

    //method get and set

    public String getNamaKandang() {
        return namaKandang;
    }
    public void setNamaKandang(String namaKandang) {
        this.namaKandang = namaKandang;
    }

    public char getJenisHewan() {
        return jenisHewan;
    }

    public void setJenisHewan(char jenisHewan) {
        this.jenisHewan = jenisHewan;
    }

    public int getKapasitasHewan() {
        return kapasitasHewan;
    }

    public void setKapasitasHewan(int kapasitasHewan) {
        this.kapasitasHewan = kapasitasHewan;
    }

    public int getSisaKapasitas() {
        return sisaKapasitas;
    }

    public void setSisaKapasitas(int sisaKapasitas) {
        this.sisaKapasitas = sisaKapasitas;
    }

    @NonNull
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
}
