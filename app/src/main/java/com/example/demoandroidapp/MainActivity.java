package com.example.demoandroidapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import com.example.demoandroidapp.adapters.SinhVienAdapter;
import com.example.demoandroidapp.asynctasks.LoadUserAsync;
import com.example.demoandroidapp.listeners.LoadUserListener;
import com.example.demoandroidapp.model.SinhVien;

import java.util.ArrayList;

import okhttp3.RequestBody;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<SinhVien> arrayList_sv;
    private Methods methods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arrayList_sv = new ArrayList<>();
        methods = new Methods();

        AnhXa();

        LoadSinhVien();

    }

    private void AnhXa() {
        recyclerView = findViewById(R.id.recyclerview);
    }

    private void LoadSinhVien() {

        RequestBody requestBody = methods.getRequestBody("method_load_sinhvien", null);

        LoadUserListener listener = new LoadUserListener() {
            @Override
            public void onEnd(boolean status, ArrayList<SinhVien> arrayList) {
                if(status){
                    arrayList_sv.addAll(arrayList);

                    SinhVienAdapter adapter = new SinhVienAdapter(arrayList_sv, MainActivity.this);

                    LinearLayoutManager llm = new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false);

                    recyclerView.setLayoutManager(llm);

                    recyclerView.setAdapter(adapter);
                }else {
                    Toast.makeText(MainActivity.this, "ERROR CONNECTION!", Toast.LENGTH_SHORT).show();
                }
            }
        };

        LoadUserAsync loadUserAsync = new LoadUserAsync(requestBody, listener);
        loadUserAsync.execute();
    }
}