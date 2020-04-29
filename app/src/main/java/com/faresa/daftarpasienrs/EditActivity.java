package com.faresa.daftarpasienrs;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.faresa.daftarpasienrs.connection.Client;
import com.faresa.daftarpasienrs.connection.Service;
import com.faresa.daftarpasienrs.pojo.DataItem;
import com.faresa.daftarpasienrs.pojo.update.UpdateResponse;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditActivity extends AppCompatActivity {
    TextInputLayout Name,Alam,Peny;

    Button submit,cancel;
    DataItem dataGet;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        dataGet = getIntent().getParcelableExtra("data");
        Name = findViewById(R.id.username);
        Objects.requireNonNull(Name.getEditText()).setText(String.valueOf(dataGet.getNama()));

        Alam = findViewById(R.id.alamat);
        Objects.requireNonNull(Alam.getEditText()).setText(String.valueOf(dataGet.getAlamat()));

        Peny = findViewById(R.id.penyakit);
        Objects.requireNonNull(Peny.getEditText()).setText(String.valueOf(dataGet.getKeluhanPenyakit()));




        submit = findViewById(R.id.save);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Username = Name.getEditText().getText().toString().trim();
                String Instansi = Alam.getEditText().getText().toString().trim();
                String Keperluan = Peny.getEditText().getText().toString().trim();

                RequestBody requestName = RequestBody.create(Username, MediaType.parse("multipart/form-data"));
                RequestBody requestAlam = RequestBody.create(Instansi, MediaType.parse("multipart/form-data"));
                RequestBody requestPeny = RequestBody.create(Keperluan, MediaType.parse("multipart/form-data"));
                String id = String.valueOf(dataGet.getPasienId());

                try {
                    Service service = Client.getClient().create(Service.class);
                    Call<UpdateResponse> create = service.update( requestName,requestAlam,requestPeny,id );
                    create.enqueue(new Callback<UpdateResponse>() {
                        @Override
                        public void onResponse(Call<UpdateResponse> call, Response<UpdateResponse> response) {
                            String kode = response.body().getStatusCode();
                            if (kode.equals("0001")) {

                                Toast.makeText(EditActivity.this, getString(R.string.msg_success), Toast.LENGTH_SHORT).show();
                                finish();
                            } else {

                                Toast.makeText(EditActivity.this, getString(R.string.msg_error), Toast.LENGTH_SHORT).show();
                                Log.d("kode:",kode);

                            }
                            finish();
                        }

                        @Override
                        public void onFailure(Call<UpdateResponse> call, Throwable t) {

                        }
                    });
                }catch (Exception e){
                    Log.d("error", String.valueOf(e));
                }


            }

        });
    }
}
