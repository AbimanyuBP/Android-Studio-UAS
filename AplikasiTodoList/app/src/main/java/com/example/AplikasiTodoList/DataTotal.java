package com.example.AplikasiTodoList;

import com.example.AplikasiTodoList.model.Hewan;
import com.example.AplikasiTodoList.model.RelasiKandangKeHewan;

public class DataTotal {
    /*
    public ArrayList<Kandang> daftarKandang = new ArrayList<Kandang>();
    public Kandang kandangDipilih;
    public static DataKandang instance = null;
     */

    public static DataTotal instance;
    public AppDatabase database;

    public RelasiKandangKeHewan kandangDipilih;
    public Hewan hewanBaru;
    public RelasiKandangKeHewan[] daftarKandangDenganSisaTempat;
    public Hewan hewanDipilih;
    public boolean apakahTambahHewanBaru;

    public static DataTotal getInstance(){
        if (instance==null){
            instance = new DataTotal();
        }
        return instance;
    }
}
