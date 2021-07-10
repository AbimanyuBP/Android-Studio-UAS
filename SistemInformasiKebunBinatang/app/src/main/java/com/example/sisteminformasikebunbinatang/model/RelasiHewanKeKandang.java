package com.example.sisteminformasikebunbinatang.model;

import androidx.room.Embedded;
import androidx.room.Relation;

public class RelasiHewanKeKandang {
    @Embedded
    public Hewan hewan;

    @Relation(parentColumn = "id_tempat_kandang", entityColumn = "id",
            entity = Kandang.class)
    public Kandang kandang;
}
