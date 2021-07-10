package com.example.sisteminformasikebunbinatang.model;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class RelasiKandangKeHewan {

    @Embedded
    public Kandang kandang;

    @Relation(parentColumn = "id", entityColumn = "id_tempat_kandang",
            entity = Hewan.class)
    public List<Hewan> daftarHewan;
}
