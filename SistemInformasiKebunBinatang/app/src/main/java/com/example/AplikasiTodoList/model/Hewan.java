package com.example.AplikasiTodoList.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "hewan")
public class Hewan {

    @PrimaryKey
    @NonNull
    private Long id;
    @NonNull
    public Long getId() {return id;}
    public void setId(@NonNull Long id) {this.id = id;}


    //Atribut tabel hewan no relasi
    @ColumnInfo(name = "nama_hewan")
    private String namaHewan;
    @ColumnInfo(name = "jenis_hewan")
    private char jenisHewan;
    @ColumnInfo(name = "tanggal_lahir")
    private Date tanggalLahir;

    //Atribut hewan yang berelasi (many to one)
    @ColumnInfo(name = "id_tempat_kandang")
    private Long idTempatKandang;

    //getter dan setter atribut hewan semuanya
    public String getNamaHewan() {
        return namaHewan;
    }

    public void setNamaHewan(String namaHewan) {
        this.namaHewan = namaHewan;
    }

    public char getJenisHewan() {
        return jenisHewan;
    }

    public void setJenisHewan(char jenisHewan) {
        this.jenisHewan = jenisHewan;
    }

    public Date getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(Date tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }
    //getter dan setter yang berelasi
    public Long getIdTempatKandang() {
        return idTempatKandang;
    }

    public void setIdTempatKandang(Long idTempatKandang) {
        this.idTempatKandang = idTempatKandang;
    }
}
