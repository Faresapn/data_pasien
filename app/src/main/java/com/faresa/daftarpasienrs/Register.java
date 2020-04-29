package com.faresa.daftarpasienrs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.faresa.daftarpasienrs.connection.Client;
import com.faresa.daftarpasienrs.connection.Service;
import com.faresa.daftarpasienrs.pojo.login.LoginResponse;
import com.faresa.daftarpasienrs.pojo.register.RegisterResponse;
import com.faresa.daftarpasienrs.ui.login.LoginActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity {
    EditText etEmail, etPass, etUser, etPass2;
    Button loginButton, regisButton;
    ProgressBar loadingProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        etUser = findViewById(R.id.user);
        etEmail = findViewById(R.id.email);
        etPass = findViewById(R.id.password);
        etPass2 = findViewById(R.id.password2);
        loginButton = findViewById(R.id.login);
        regisButton = findViewById(R.id.regist);
        loadingProgressBar = findViewById(R.id.loading);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        regisButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgressBar.setVisibility(View.VISIBLE);

                requestLogin();
            }
        });

    }

    private void requestLogin() {

        final Service service = Client.getClient().create(Service.class);
        Call<RegisterResponse> regis = service.registerRequest(etUser.getText().toString(),etEmail.getText().toString(), etPass.getText().toString(),etPass2.getText().toString());
        regis.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                String kode = response.body().getStatusCode();
                if (kode.equals("0022")) {

                    Toast.makeText(Register.this, getString(R.string.msg_success), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Register.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                } else {

                    Toast.makeText(Register.this, getString(R.string.msg_error), Toast.LENGTH_SHORT).show();
                    Log.d("kode:", kode);

                }
                finish();
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {

            }
        });
    }
}