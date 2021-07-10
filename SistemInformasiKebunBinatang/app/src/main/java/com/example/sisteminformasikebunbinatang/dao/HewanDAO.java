package com.example.sisteminformasikebunbinatang.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.sisteminformasikebunbinatang.model.Hewan;
import com.example.sisteminformasikebunbinatang.model.Kandang;
import com.example.sisteminformasikebunbinatang.model.RelasiHewanKeKandang;

import java.util.List;


@Dao
public interface HewanDAO {
    @Insert
    public void insert(Hewan... hewan);

    @Update
    public void update(Hewan... hewan);

    @Delete
    public void delete(Hewan hewan);

    @Transaction
    @Query("SELECT * FROM Hewan")
    public List<RelasiHewanKeKandang> getDaftarHewan();

    @Transaction
    @Query("SELECT * FROM Hewan WHERE id = :id")
    public RelasiHewanKeKandang getHewanById(Long id);

    @Transaction
    @Query("SELECT * FROM Hewan WHERE nama_hewan = :namaHewan")
    public RelasiHewanKeKandang[] getHewanByNamaHewan(String namaHewan);
}
