package com.faresa.daftarpasienrs.ui.login;

import android.app.Activity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.faresa.daftarpasienrs.MainActivity;
import com.faresa.daftarpasienrs.R;
import com.faresa.daftarpasienrs.Register;
import com.faresa.daftarpasienrs.connection.Client;
import com.faresa.daftarpasienrs.connection.Service;
import com.faresa.daftarpasienrs.pojo.login.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText etEmail,etPass;
    Button loginButton,regisButton;
    ProgressBar loadingProgressBar;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


          etEmail = findViewById(R.id.username);
          etPass = findViewById(R.id.password);
          loginButton = findViewById(R.id.login);
          regisButton = findViewById(R.id.regist);
          loadingProgressBar = findViewById(R.id.loading);

        regisButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, Register.class);
                startActivity(intent);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgressBar.setVisibility(View.VISIBLE);

                requestLogin();
            }
        });

    }

    private void requestLogin() {

        final Service service = Client.getClient().create(Service.class);
        Call<LoginResponse> login = service.loginRequest(etEmail.getText().toString(),etPass.getText().toString());
        login.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                String kode = response.body().getStatusCode();
                if (kode.equals("0022")) {

                    Toast.makeText(LoginActivity.this, getString(R.string.msg_success), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {

                    Toast.makeText(LoginActivity.this, getString(R.string.msg_error), Toast.LENGTH_SHORT).show();
                    Log.d("kode:", kode);

                }
                finish();
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

            }
        });
    }
}
