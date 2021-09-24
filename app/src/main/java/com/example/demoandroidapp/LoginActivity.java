package com.example.demoandroidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.demoandroidapp.asynctasks.LoginAsync;
import com.example.demoandroidapp.listeners.LoginAsyncListener;

import java.lang.reflect.Method;

import okhttp3.RequestBody;

public class LoginActivity extends AppCompatActivity {

    private EditText edt_username, edt_password;
    private Button btn_login;
    private Methods methods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        methods = new Methods();

        AnhXa();
    }

    private void AnhXa() {
        edt_username = findViewById(R.id.edt_username);
        edt_password = findViewById(R.id.edt_password);
        btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DangNhap();
            }
        });
    }

    private void DangNhap(){
        String username = edt_username.getText().toString();
        String password = edt_password.getText().toString();

        if(username.equals("")){
            edt_username.setError("Can not empty!");
            return;
        }

        if(password.equals("")){
            edt_password.setError("Can not empty!");
            return;
        }

        LoginFunction(username, password);
    }

    private void LoginFunction(String username, String password){

        LoginAsyncListener listener = new LoginAsyncListener() {
            @Override
            public void onEnd(Boolean status) {
                if(status){

                    Toast.makeText(LoginActivity.this, "Login success", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);

                    startActivity(intent);

                }else {
                    Toast.makeText(LoginActivity.this, "Login fail", Toast.LENGTH_SHORT).show();
                }
            }
        };

        String method_name = "method_login";

        Bundle bundle = new Bundle();
        bundle.putString("username", username);
        bundle.putString("password", password);

        RequestBody requestBody = methods.getRequestBody(method_name, bundle);

        LoginAsync loginAsync = new LoginAsync(listener, requestBody);

        loginAsync.execute();
    }
}