package com.faresa.daftarpasienrs;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.faresa.daftarpasienrs.pojo.DataItem;


public class DetailActivity extends AppCompatActivity {
    TextView Username,Instasi,Keperluan,created;
    DataItem item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        item = getIntent().getParcelableExtra("data");

        Username = findViewById(R.id.User);
        String User =item.getNama();
        Username.setText(User);

        Instasi = findViewById(R.id.Intansi);
        String Instasis =item.getAlamat();
        Instasi.setText(Instasis);

        Keperluan = findViewById(R.id.Keperluan);
        String Kep =item.getKeluhanPenyakit();
        Keperluan.setText(Kep);

        created = findViewById(R.id.dibuat);
        String dibuat =item.getCreatedAt();
        created.setText(dibuat);



    }
}
