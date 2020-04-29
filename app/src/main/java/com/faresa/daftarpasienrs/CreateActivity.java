package com.faresa.daftarpasienrs;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.faresa.daftarpasienrs.connection.Client;
import com.faresa.daftarpasienrs.connection.Service;
import com.faresa.daftarpasienrs.pojo.GetData;
import com.faresa.daftarpasienrs.pojo.create.CreateResponse;
import com.google.android.material.textfield.TextInputLayout;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateActivity extends AppCompatActivity {
    TextInputLayout Name,nameinstansi,keperluan;

    Button submit;
    GetData dataGet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        Name = findViewById(R.id.username);
        nameinstansi = findViewById(R.id.alamat);
        keperluan = findViewById(R.id.penyakit);

        submit = findViewById(R.id.save);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Username = Name.getEditText().getText().toString().trim();
                String namainstasi = nameinstansi.getEditText().getText().toString().trim();
                String Keperluan= keperluan.getEditText().getText().toString().trim();

                RequestBody requestNama = RequestBody.create(Username, MediaType.parse("multipart/form-data"));
                RequestBody requestAlamat = RequestBody.create(namainstasi, MediaType.parse("multipart/form-data"));
                RequestBody requestPenyakit = RequestBody.create(Keperluan, MediaType.parse("multipart/form-data"));

                try {
                Service service = Client.getClient().create(Service.class);
                    Call<CreateResponse> create = service.createData(requestNama, requestAlamat, requestPenyakit );
                    create.enqueue(new Callback<CreateResponse>() {
                        @Override
                        public void onResponse(Call<CreateResponse> call, Response<CreateResponse> response) {
                            String kode = response.body().getStatusCode();
                            if (kode.equals("0016")) {

                                Toast.makeText(CreateActivity.this, getString(R.string.msg_success), Toast.LENGTH_SHORT).show();
                                finish();
                            } else {

                                Toast.makeText(CreateActivity.this, getString(R.string.msg_error), Toast.LENGTH_SHORT).show();
                                Log.d("kode:",kode);

                            }
                            finish();
                        }

                        @Override
                        public void onFailure(Call<CreateResponse> call, Throwable t) {

                        }
                    });
                }catch (Exception e){
                    Log.d("error", String.valueOf(e));
                }


            }

        });


    }
}
