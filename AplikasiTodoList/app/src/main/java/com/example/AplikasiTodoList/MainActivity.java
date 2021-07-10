package com.example.AplikasiTodoList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.example.AplikasiTodoList.model.Kandang;
import com.example.AplikasiTodoList.model.RelasiKandangKeHewan;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private DataTotal dataTotal = DataTotal.getInstance();
    private ArrayList<String> daftarNamaKandangBernomorUrut = new ArrayList<String>();
    private ListView listViewDaftarNamaKandang;
    private ArrayAdapter adapter;
    //    AppDatabase database;
    //KandangDAO kandangDAO;

    private Spinner spinnerJenisHewan;
    private TextView textViewHeaderDaftarKandang;
    private String namaKandangDicari;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnTambah = findViewById(R.id.btnTambah);
        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rekamDataKandangBaru();
            }
        });

        //2. Menyiapkan Spinner dan Button untuk Menerima Hewan
        //2a. Menentukan komponen GUI dengan membaca file XML
        spinnerJenisHewan = findViewById(R.id.spinnerJenisHewan);
        Button btnCariKandang = findViewById(R.id.btnCariKandang);
        //2b. Menambahkan pilihan untuk Spinner
        ArrayList<String> pilihanJenisHewan = new ArrayList<String>
                (Arrays.asList("Harimau", "Burung", "Gajah"));
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getApplicationContext(),
                R.layout.baris_pada_spinner_jenis_hewan,pilihanJenisHewan);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.baris_pilihan_pada_spinner_jenis_hewan);
        spinnerJenisHewan.setAdapter(adapter);
        //2c. Menambahkan listener untuk event click button Cari Kandang
        btnCariKandang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                char jenisHewan = spinnerJenisHewan.getSelectedItemPosition()==0 ?
                        'H' : spinnerJenisHewan.getSelectedItemPosition()==1 ? 'B' : 'G';
                cariKandangUntukHewanBaru(jenisHewan);
            }
        });


        //2. Membuat database
        DataTotal.getInstance().database = Room.databaseBuilder(this, AppDatabase.class,
                "kebunbinatangdb")
                .allowMainThreadQueries()
                .build();

        //bacaDataTanpaDatabase();
        bacaDataDenganDatabase();

        textViewHeaderDaftarKandang = findViewById(R.id.textViewHeadingDaftarKandang);
        textViewHeaderDaftarKandang.requestFocus();
        /*
        Button btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tambahKandangBaru();
            }
        });
        database = Room.databaseBuilder(this, AppDatabase.class,
                "kebunbinatangdb")
                .allowMainThreadQueries()
                .build();
        bacaDataDenganDatabase();

         */
    }

    /*
    @Override
    protected void onStart() {
        super.onStart();
        updateListViewKandang();
    }

     */


    /*
    public void tambahKandangBaru(){
        Kandang kandangBaru = new Kandang();
        EditText editTextKandang = findViewById(R.id.editTextKandang);
        String namaKandangBaru = editTextKandang.getText().toString();
        kandangBaru.setNamaKandang(namaKandangBaru);
        dataKandang.daftarKandang.add(kandangBaru);
        updateListViewKandang();
        tambahDataDenganDatabase(kandangBaru);
    }

     */

    //udh
    public void tambahDataDenganDatabase(Kandang kandangBaru) {
        DataTotal.getInstance().database.getKandangDAO().insert(kandangBaru);
        RelasiKandangKeHewan kandangBaruDariDatabase = DataTotal.getInstance().database
                .getKandangDAO().getKandangBaruByNamaKandang(kandangBaru.getNamaKandang());
        dataTotal.kandangDipilih = kandangBaruDariDatabase;
        Intent formRincianDataKandang = new Intent(this,
                RincianDataKandang.class);
        startActivityForResult(formRincianDataKandang,0);
    }

    //udh
    public void updateTampilanListView(){
        listViewDaftarNamaKandang = findViewById(R.id.listViewKandang);
        daftarNamaKandangBernomorUrut.clear();
        int nomorUrut = 1;
        ArrayList<RelasiKandangKeHewan> daftarKandang =
                (ArrayList<RelasiKandangKeHewan>) DataTotal
                        .getInstance().database.getKandangDAO().getDaftarKandang();
        for (RelasiKandangKeHewan t : daftarKandang) {
            String tampilanNamaKandang = nomorUrut + ". " + t.kandang.getNamaKandang();
            nomorUrut++;
            daftarNamaKandangBernomorUrut.add(tampilanNamaKandang);
        }
        adapter = new ArrayAdapter(this, R.layout.baris_pada_daftar_nama_kandang,
                daftarNamaKandangBernomorUrut.toArray());
        listViewDaftarNamaKandang.setAdapter(adapter);
    }

    //udh
    public void hapusDataKandang(String namaKandangDicari){
        int indeksKandangDicari = daftarNamaKandangBernomorUrut.indexOf(namaKandangDicari);
        /*
        Kandang kandangDicari = dataKandang.daftarKandang.get(indeksKandangDicari);
        hapusDenganDatabase(kandangDicari);
        dataKandang.daftarKandang.remove(indeksKandangDicari);
         */
        updateTampilanListView();
    }

    //udh
    public void ubahDataKandang(View v){
        namaKandangDicari = ((TextView) v).getText().toString();
        lengkapiDataKandang(namaKandangDicari);
        //1. Beri konfirmasi ke pengguna untuk hapus data atau ingin mengubah data/rincian data
        //1b. buat alert dialog
        /*
        new AlertDialog.Builder(this)
                .setTitle("Hapus/Lengkapi Data")
                .setMessage("Silakan pilih opsi untuk menghapus atau melengkapi data")

                // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.
                .setPositiveButton("Hapus", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        hapusDataKandang(namaKandangDicari);
                    }
                })
                .setNeutralButton("Lengkapi", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        lengkapiDataKandang(namaKandangDicari);
                    }
                })

                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();

         */
    }


    //udh
    public void lengkapiDataKandang(String namaKandangDicari) {
        Intent formRincianDataKandang = new Intent(this, RincianDataKandang.class);
        //1. Cari Objek Kandang di dalam memori
        int indeksKandangDicari = daftarNamaKandangBernomorUrut.indexOf(namaKandangDicari);
        ArrayList<RelasiKandangKeHewan> daftarKandang = (ArrayList<RelasiKandangKeHewan>)
                DataTotal.getInstance().database.getKandangDAO().getDaftarKandang();
        RelasiKandangKeHewan kandangDipilih = daftarKandang.get(indeksKandangDicari);
        DataTotal.getInstance().kandangDipilih = kandangDipilih;
        /*
        //2. manyimpan objek Kandang dalam data kandang
        dataKandang.kandangDipilih = dataKandang.daftarKandang.get(indeksKandangDicari);

         */
        //3. Mengaktifkanactivity form rincian data kandang
        startActivityForResult(formRincianDataKandang, 0);
    }

    //udh
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //ubahDataDenganDatabase(dataKandang.kandangDipilih);
        if (resultCode == Activity.RESULT_OK && data.getStringExtra("result")
                .equals("delete")){
            hapusDataKandang(namaKandangDicari);
        }
        updateTampilanListView();
    }
    /* Udah Gk dipake
    private void rekamDataTanpaDatabase(){
        try {
            FileOutputStream fos = openFileOutput("kebunbinatang",
                    Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(DataKandang.getInstance().daftarKandang);
            oos.close();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void bacaDataTanpaDatabase(){
        try {
            FileInputStream fis = openFileInput("kebunbinatang");
            ObjectInputStream ois = new ObjectInputStream(fis);
            DataKandang.getInstance().daftarKandang = (ArrayList<Kandang>) ois.readObject();
            ois.close();
            fis.close();
            updateListViewKandang();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    */

    //udh
    public void bacaDataDenganDatabase(){
        /*
        kandangDAO = database.getKandangDAO();
        DataKandang.getInstance().daftarKandang = (ArrayList<Kandang>) kandangDAO.getDaftarKandang();

         */
        updateTampilanListView();
    }


    /*
    private void hapusDenganDatabase(Kandang kandangDicari) {
        kandangDAO.delete(kandangDicari);
    }

    private void ubahDataDenganDatabase(Kandang kandangDipilih){
        kandangDAO.update(kandangDipilih);
    }

     */

    //udh
    public void cariKandangUntukHewanBaru(char jenisHewan){
        RelasiKandangKeHewan[] daftarKandang = DataTotal.getInstance()
                .database.getKandangDAO().getDaftarKandangMasihTersisaKandang(jenisHewan);
        String tampilan = "";
        if (null!=daftarKandang && daftarKandang.length>0){
            Intent formRincianDataHewan = new Intent(this,
                    RincianDataHewan.class);
            dataTotal.daftarKandangDenganSisaTempat = daftarKandang;
            dataTotal.apakahTambahHewanBaru = true;
            startActivity(formRincianDataHewan);
        } else {
            Toast.makeText(this, "Kandang sudah penuh " +
                            "pada jenis hewan " + spinnerJenisHewan.getSelectedItem(),
                    Toast.LENGTH_LONG).show();

        }
    }

    //udh
    public void rekamDataKandangBaru(){
        EditText editTextNamaKandangBaru = findViewById(R.id
                .editTextNamaKandangBaru);
        String namaKandangBaru = editTextNamaKandangBaru.getText().toString();
        RelasiKandangKeHewan kandangBaru = new RelasiKandangKeHewan();
        kandangBaru.kandang = new Kandang();
        kandangBaru.kandang.setNamaKandang(namaKandangBaru);
        updateTampilanListView();
        tambahDataDenganDatabase(kandangBaru.kandang);
    }
}