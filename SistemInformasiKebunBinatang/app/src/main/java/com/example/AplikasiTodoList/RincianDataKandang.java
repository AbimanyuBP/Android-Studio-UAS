package com.example.AplikasiTodoList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.AplikasiTodoList.model.Hewan;
import com.example.AplikasiTodoList.model.RelasiKandangKeHewan;

import java.util.ArrayList;

public class RincianDataKandang extends AppCompatActivity {
    private RelasiKandangKeHewan kandangDipilih;
    private DataTotal dataTotal = DataTotal.getInstance();
    private EditText isianNamaKandang;
    private EditText isianKapasitas;
    private EditText isianSisaKapasitas;
    private TextView headerListView;
    private RadioButton radioHarimau;
    private RadioButton radioBurung;
    private RadioButton radioGajah;
    private ListView listViewDaftarNamaHewan;
    private ArrayList<String> daftarNamaHewanBernomorUrut = new ArrayList<String>();
    private String namaHewanDicari;
    private RelasiKandangKeHewan relasiKandangKeHewanDipilih = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rincian_data_kandang);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Rincian Data Kandang");

        Button btnRekam = findViewById(R.id.btnRekamDataKandang);
        btnRekam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rekamRincianDataKandang();
            }
        });
        Button btnHapus = findViewById(R.id.btnHapus);
        btnHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (kandangDipilih.daftarHewan.size()!=0){
                    new AlertDialog.Builder(view.getContext())
                            .setTitle("Kandang Tidak Dapat Dihapus")
                            .setMessage("Kandang yang tidak kosong  "
                                    + "tidak dapat dihapus")
                            .setPositiveButton("OK", null)
                            .show();
                    return;
                } else {
                    hapusDataKandang();
                }
            }
        });

        headerListView = findViewById(R.id.textViewHeadingDaftarHewan);
        headerListView.requestFocus();
        updateTampilanIsianDataKandang();
        updateTampilanListView();
        /*
        kandangDipilih = dataKandang.kandangDipilih;
        isianNamaKandang = findViewById(R.id.editTextIsianNamaKandang);
        isianNamaKandang.setText(kandangDipilih.getNamaKandang());
        isianKapasitasKandang = findViewById(R.id.editTextIsianKapasitas);
        isianKapasitasKandang.setText(""+kandangDipilih.getKapasitasHewan());
        radioHarimau = findViewById(R.id.radioButtonHarimau);
        radioBurung = findViewById(R.id.radioButtonBurung);
        radioGajah = findViewById(R.id.radioButtonGajah);
        Button btnRekam = findViewById(R.id.btnRekamDataKandang);
        btnRekam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rekamRincianDataKandang();
            }
        });
        if (kandangDipilih.getJenisHewan()=='H'){
            radioHarimau.setChecked(true);
        }
        else if (kandangDipilih.getJenisHewan()=='B'){
            radioBurung.setChecked(true);
        }
        else {
            radioGajah.setChecked(true);
        }
         */
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void rekamRincianDataKandang(){
        kandangDipilih.kandang.setNamaKandang(isianNamaKandang.getText().toString());
        int kapasitasBaru = Integer.parseInt(isianKapasitas.getText().toString());
        if (kandangDipilih.kandang.getSisaKapasitas()==0 &&
                kandangDipilih.kandang.getSisaKapasitas()==0){
            kandangDipilih.kandang.setKapasitasHewan(kapasitasBaru);
            kandangDipilih.kandang.setSisaKapasitas(kapasitasBaru);
        }
        else if (kapasitasBaru<kandangDipilih.kandang.getKapasitasHewan()){
            if (kandangDipilih.kandang.getSisaKapasitas()-(kandangDipilih.kandang
            .getSisaKapasitas()-kapasitasBaru)>=0){
                int sisaTempatBaru = kandangDipilih.kandang.getSisaKapasitas() -
                        (kandangDipilih.kandang.getKapasitasHewan()-kapasitasBaru);
                kandangDipilih.kandang.setKapasitasHewan(kapasitasBaru);
                kandangDipilih.kandang.setSisaKapasitas(sisaTempatBaru);
            }
            else {
                new AlertDialog.Builder(this)
                        .setTitle("Kapasitas Tidak Bisa Dikurangi")
                        .setMessage("Nilai kapasitas harus lebih besar atau sama dengan " +
                                "dengan jumlah hewan dalam kandang" +
                                "(jumlah hewan =" +
                                (kandangDipilih.kandang.getKapasitasHewan() - kandangDipilih.kandang.getSisaKapasitas() + ")"))
                        .setPositiveButton("OK", null)
                        .show();
                isianKapasitas.setText(""+kandangDipilih.kandang.getKapasitasHewan());
                return;
            }
        }
        else {
            int sisaKandangBaru = kandangDipilih.kandang.getSisaKapasitas() +
                    (kapasitasBaru- kandangDipilih.kandang.getKapasitasHewan());
            kandangDipilih.kandang.setKapasitasHewan(kapasitasBaru);
            kandangDipilih.kandang.setSisaKapasitas(sisaKandangBaru);
        }

        if (radioHarimau.isChecked()){
            kandangDipilih.kandang.setJenisHewan('H');
        }
        else if (radioBurung.isChecked()){
            kandangDipilih.kandang.setJenisHewan('B');
        }
        else {
            kandangDipilih.kandang.setJenisHewan('G');
        }
        DataTotal.getInstance().database.getKandangDAO().update(kandangDipilih.kandang);
        finish();
    }

    private void updateTampilanIsianDataKandang() {
        kandangDipilih = dataTotal.kandangDipilih;
        isianNamaKandang = findViewById(R.id.editTextIsianNamaKandang);
        isianNamaKandang.setText(kandangDipilih.kandang.getNamaKandang());
        isianKapasitas = findViewById(R.id.editTextIsianKapasitas);
        isianKapasitas.setText("" + kandangDipilih.kandang.getKapasitasHewan());
        isianSisaKapasitas = findViewById(R.id.editTextIsianSisaKapasitas);
        isianSisaKapasitas.setText("" + kandangDipilih.kandang.getSisaKapasitas());
        radioHarimau = findViewById(R.id.radioButtonHarimau);
        radioBurung = findViewById(R.id.radioButtonBurung);
        radioGajah = findViewById(R.id.radioButtonGajah);
        if (kandangDipilih.kandang.getJenisHewan()=='H'){
            radioHarimau.setChecked(true);
        }
        else if (kandangDipilih.kandang.getJenisHewan()=='B'){
            radioBurung.setChecked(true);
        }
        else {
            radioGajah.setChecked(true);
        }
    }

    private void hapusDataKandang() {
        DataTotal.getInstance().database.getKandangDAO().delete(kandangDipilih.kandang);
        Intent returnIntent = new Intent();
        returnIntent.putExtra("result", "delete");
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }

    private void updateTampilanListView() {
        listViewDaftarNamaHewan = findViewById(R.id.listViewDaftarHewan);
        daftarNamaHewanBernomorUrut.clear();
        if (kandangDipilih.daftarHewan.size()>0){
            int nomorUrut = 1;
            for (Hewan h : kandangDipilih.daftarHewan) {
                String tampilanNamaHewan = nomorUrut + ". " + h.getNamaHewan();

                nomorUrut++;
                daftarNamaHewanBernomorUrut.add(tampilanNamaHewan);
            }
            //2c. Menentukan adapter (sumber tampilan/data) bagi list view
            ArrayAdapter<String> adapter = new ArrayAdapter(this,
                    R.layout.baris_pada_daftar_nama_hewan, daftarNamaHewanBernomorUrut
                    .toArray());
            //2d. Mengaitkan list view dengan adapter
            listViewDaftarNamaHewan.setAdapter(adapter);
            headerListView.setText("Daftar Hewan di Kandang "
                    + kandangDipilih.kandang.getNamaKandang());
            findViewById(R.id.radioButtonHarimau).setEnabled(false);
            findViewById(R.id.radioButtonBurung).setEnabled(false);
            findViewById(R.id.radioButtonGajah).setEnabled(false);
        } else {
            findViewById(R.id.radioButtonHarimau).setEnabled(true);
            findViewById(R.id.radioButtonBurung).setEnabled(true);
            findViewById(R.id.radioButtonGajah).setEnabled(true);
            ArrayAdapter<String> adapter = new ArrayAdapter(this,
                    R.layout.baris_pada_daftar_nama_hewan, new String[0]);
            listViewDaftarNamaHewan.setAdapter(adapter);
            headerListView.setText("Tidak ada hewan");
        }
    }

    public void ubahDataHewan(View v){
        Intent formRincianDataHewan = new Intent(this,
                RincianDataHewan.class);
        //1. Mencari objek kandang pada memori (struktur data array list)
        namaHewanDicari = ((TextView) v).getText().toString();
        int indeksHewanDicari = daftarNamaHewanBernomorUrut
                .indexOf(namaHewanDicari);
        //2. Menyimpan objek hewan dicari dalam data global
        dataTotal.hewanDipilih = kandangDipilih
                .daftarHewan.get(indeksHewanDicari);
        dataTotal.apakahTambahHewanBaru = false;
        //3. Mengaktifkan activity form rincian data kandang
        startActivityForResult(formRincianDataHewan,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        updateTampilanIsianDataKandang();
        updateTampilanListView();
    }
}