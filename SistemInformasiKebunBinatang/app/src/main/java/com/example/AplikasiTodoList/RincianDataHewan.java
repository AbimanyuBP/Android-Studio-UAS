package com.example.AplikasiTodoList;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.AplikasiTodoList.model.DateTypeConverter;
import com.example.AplikasiTodoList.model.Hewan;
import com.example.AplikasiTodoList.model.RelasiKandangKeHewan;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class RincianDataHewan extends AppCompatActivity {

    private EditText editTextNamaHewan;
    private EditText editTextTanggalLahir;
    private TextView textViewJenisHewan;
    private Spinner spinnerTempatKandang;
    private RelasiKandangKeHewan tempatKandang;
    private char jenisHewan;
    private Calendar calendar;
    private int year, month, day;
    private ArrayList<String> daftarTempatKandang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rincian_data_hewan);
        textViewJenisHewan= findViewById(R.id.textViewJenisHewan);
        editTextTanggalLahir = findViewById(R.id.editTextTanggalLahir);
        editTextNamaHewan = findViewById(R.id.editTextNamaHewan);
        daftarTempatKandang = new ArrayList<String>();
        spinnerTempatKandang = findViewById(R.id.spinnerTempatKandang);

        if (DataTotal.getInstance().apakahTambahHewanBaru==true){
            jenisHewan = DataTotal.getInstance().daftarKandangDenganSisaTempat[0]
                    .kandang.getJenisHewan();
            textViewJenisHewan.setText(jenisHewan=='H'?"Harimau":jenisHewan=='B'? "Burung" : "Gajah");// fixed
            for (RelasiKandangKeHewan k: DataTotal.getInstance()
                    .daftarKandangDenganSisaTempat){
                daftarTempatKandang.add(k.kandang.getNamaKandang());
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this
                    .getApplicationContext(),
                    R.layout.baris_pada_spinner_jenis_hewan, daftarTempatKandang);
            adapter.setDropDownViewResource(R.layout
                    .baris_pilihan_pada_spinner_jenis_hewan);
            spinnerTempatKandang.setAdapter(adapter);
            findViewById(R.id.btnHapusDataHewan).setVisibility(View.INVISIBLE);
            getSupportActionBar().setTitle("Rincian Data Hewan Baru");
        }
        else {
            tempatKandang = DataTotal.getInstance().kandangDipilih;
            daftarTempatKandang.add(tempatKandang.kandang.getNamaKandang());
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getApplicationContext(),
                    R.layout.baris_pada_spinner_jenis_hewan, daftarTempatKandang);
            adapter.setDropDownViewResource(R.layout.baris_pilihan_pada_spinner_jenis_hewan);
            spinnerTempatKandang.setAdapter(adapter);
            jenisHewan = DataTotal.getInstance().hewanDipilih.getJenisHewan();
            textViewJenisHewan.setText(jenisHewan=='H'?"Harimau": jenisHewan=='B'? "Burung" : "Gajah"); //I fixed it yay
            Date tanggalLahir = DataTotal.getInstance().hewanDipilih.getTanggalLahir();
            editTextTanggalLahir.setText(DateTypeConverter.dateToString(tanggalLahir));
            editTextNamaHewan.setText(DataTotal.getInstance().hewanDipilih.getNamaHewan());
            findViewById(R.id.btnHapusDataHewan).setVisibility(View.VISIBLE);
            getSupportActionBar().setTitle("Rincian Data Hewan");
        }

        Button btnRekamDataHewan = findViewById(R.id.btnRekamDataHewan);
        btnRekamDataHewan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rekamDataHewan();
            }
        });

        Button btnHapusDataHewan = findViewById(R.id.btnHapusDataHewan);
        btnHapusDataHewan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hapusDataHewan();
            }
        });

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        ImageButton btnPopupCalendar = findViewById(R.id.btnPopupCalendar);
        btnPopupCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();
                int yy = calendar.get(Calendar.YEAR);
                int mm = calendar.get(Calendar.MONTH);
                int dd = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePicker = new DatePickerDialog(view.getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                String date = String.valueOf(dayOfMonth) + "-" + String.valueOf(monthOfYear + 1)
                                        + "-" + String.valueOf(year);
                                editTextTanggalLahir.setText(date);
                                if (editTextTanggalLahir.getText().toString().length() != 0) {
                                    editTextTanggalLahir.setError(null);
                                }
                            }
                        }, yy, mm, dd);
                datePicker.show();
            }
        });

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        textViewJenisHewan.requestFocus();
    }

    private void hapusDataHewan() {
        DataTotal.getInstance().database.getHewanDAO().delete(DataTotal.getInstance()
                .hewanDipilih);
        RelasiKandangKeHewan kandangDipilih = DataTotal.getInstance().kandangDipilih;
        kandangDipilih.kandang.setSisaKapasitas(tempatKandang.kandang.getSisaKapasitas()+1);
        DataTotal.getInstance().database.getKandangDAO().update(kandangDipilih.kandang);
        DataTotal.getInstance().kandangDipilih =
                DataTotal.getInstance().database.getKandangDAO()
                        .getKandangById(kandangDipilih.kandang.getId());
        finish();
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

    private void rekamDataHewan(){
        if (DataTotal.getInstance().apakahTambahHewanBaru==true){
            //1. Membuat objek hewan
            //baru dan mengubah atribut nama dan
            // jenis hewan tersebut
            Hewan hewanBaru = new Hewan();
            EditText editTextNamaHewan = findViewById(R.id.editTextNamaHewan);
            String namaHewan = editTextNamaHewan.getText().toString();
            if (namaHewan.equals("")){
                editTextNamaHewan.setError("Nama Hewan harus diisi");
                return;
            }
            hewanBaru.setNamaHewan(namaHewan);
            hewanBaru.setJenisHewan(jenisHewan);
            String stringTanggalLahir = editTextTanggalLahir.getText().toString();
            if (stringTanggalLahir.equals("")){
                editTextTanggalLahir.setError("Tanggal Lahir harus diisi");
                return;
            }
            //2. Validasi tanggal lahir
            Date tanggalLahir = null;
            try {
                tanggalLahir = DateTypeConverter.stringToDate(stringTanggalLahir);
            } catch (Exception e) {
                editTextTanggalLahir.setError("Tanggal tidak valid");
                return;
            }
            hewanBaru.setTanggalLahir(tanggalLahir);
            //3. Menentukan tempat kandang bagi hewan
            //3a. Menentukan kandang yang dipilih dalam antarmuka spinner
            RelasiKandangKeHewan[] daftarKandangDenganSisaTempat = DataTotal.getInstance()
                    .daftarKandangDenganSisaTempat;
            RelasiKandangKeHewan tempatKandangDipilih =
                    daftarKandangDenganSisaTempat[spinnerTempatKandang
                            .getSelectedItemPosition()];
            //3b. Menentukan id tempat kandang
            hewanBaru.setIdTempatKandang(tempatKandangDipilih.kandang.getId());
            //4. Merekam data hewan
            //baru ke dalam database
            DataTotal.getInstance().database.getHewanDAO().insert(hewanBaru);
            //5. Mengurangi sisa tempat dalam kandang yang dipilih dengan 1 (sisaTempat-1)
            tempatKandangDipilih.kandang.setSisaKapasitas(tempatKandangDipilih.kandang.getSisaKapasitas()-1);
            DataTotal.getInstance().database.getKandangDAO().update(tempatKandangDipilih.kandang);
        } else {
            Hewan hewanDipilih = DataTotal.getInstance().hewanDipilih;
            EditText editTextNamaHewan = findViewById(R.id.editTextNamaHewan);
            String namaHewan = editTextNamaHewan.getText().toString();
            if (namaHewan.equals("")){
                editTextNamaHewan.setError("Nama Hewan harus diisi");
                return;
            }
            String stringTanggalLahir = editTextTanggalLahir.getText().toString();
            if (stringTanggalLahir.equals("")){
                editTextTanggalLahir.setError("Tanggal Lahir harus diisi");
                return;
            }
            Date tanggalLahir = null;
            try {
                tanggalLahir = DateTypeConverter.stringToDate(stringTanggalLahir);
            } catch (Exception e) {
                editTextTanggalLahir.setError("Tanggal tidak valid");
                return;
            }
            hewanDipilih.setNamaHewan(namaHewan);
            hewanDipilih.setTanggalLahir(tanggalLahir);
            DataTotal.getInstance().database.getHewanDAO().update(hewanDipilih);

        }
        finish();
    }
}