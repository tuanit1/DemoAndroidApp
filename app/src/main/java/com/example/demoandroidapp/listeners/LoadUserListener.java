package com.example.demoandroidapp.listeners;

import com.example.demoandroidapp.model.SinhVien;

import java.util.ArrayList;

public interface LoadUserListener {
    void onEnd(boolean status, ArrayList<SinhVien> arrayList);
}
