package com.example.AplikasiTodoList.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.AplikasiTodoList.model.Kandang;
import com.example.AplikasiTodoList.model.RelasiKandangKeHewan;

import java.util.List;

@Dao

public interface KandangDAO {
        @Insert
        public void insert(Kandang... kandang);
        @Update
        public void update(Kandang... kandang);
        @Delete
        public void delete(Kandang kandang);

        /* Queri select yang lama
        @Query("SELECT * FROM kandang")
        public List<Kandang> getDaftarKandang();

        @Query("SELECT * FROM kandang WHERE id = :id")
        public Kandang getKandangById(Long id);
         */

        @Transaction
        @Query("SELECT * FROM Kandang")
        public List<RelasiKandangKeHewan> getDaftarKandang();

        @Transaction
        @Query("SELECT * FROM Kandang WHERE id = :id")
        public RelasiKandangKeHewan getKandangById(Long id);

        // Outta Script
        @Transaction
        @Query("SELECT * FROM Kandang WHERE nama_kandang = :namaKandang")
        public RelasiKandangKeHewan[] getKandangByNamaKandang(String namaKandang);

        @Transaction
        @Query("SELECT * FROM kandang WHERE nama_kandang = :namaKandang ORDER BY id DESC LIMIT 1")
        public RelasiKandangKeHewan getKandangBaruByNamaKandang(String namaKandang);

        @Transaction
        @Query("SELECT * FROM kandang WHERE jenis_hewan = :jenisHewan AND " +
                "sisa_kapasitas>0 ORDER BY sisa_kapasitas DESC")
        public RelasiKandangKeHewan[] getDaftarKandangMasihTersisaKandang(char jenisHewan);


}
