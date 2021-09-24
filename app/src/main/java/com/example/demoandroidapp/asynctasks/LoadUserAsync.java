package com.example.demoandroidapp.asynctasks;

import android.os.AsyncTask;

import com.example.demoandroidapp.listeners.LoadUserListener;
import com.example.demoandroidapp.model.SinhVien;
import com.example.demoandroidapp.utils.JsonUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import okhttp3.RequestBody;

public class LoadUserAsync extends AsyncTask<Void, String, Boolean> {

    private RequestBody requestBody;
    private LoadUserListener listener;
    private ArrayList<SinhVien> arrayList_sv;

    public LoadUserAsync(RequestBody requestBody, LoadUserListener listener){
        this.requestBody = requestBody;
        this.listener = listener;
        arrayList_sv = new ArrayList<>();
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        try{
            String link = "http://192.168.1.10/demoandroid/api.php";

            String result = JsonUtils.okhttpPost(link, requestBody); // result is json_string

            //json string -> json object

            JSONObject jsonObject = new JSONObject(result);

            JSONArray jsonArray = jsonObject.getJSONArray("array_user");

            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject sinhvien_obj = jsonArray.getJSONObject(i);

                String username = sinhvien_obj.getString("username");

                String password = sinhvien_obj.getString("password");

                String name = sinhvien_obj.getString("name");

                int age = sinhvien_obj.getInt("age");

                String date_string = sinhvien_obj.getString("birthday");

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                Date birthday = sdf.parse(date_string);

                String address = sinhvien_obj.getString("address");

                String image = sinhvien_obj.getString("image");

                SinhVien sinhVien = new SinhVien(username, password, name, age, birthday, address, image);

                arrayList_sv.add(sinhVien);
            }

            return true;

        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    protected void onPostExecute(Boolean status) {
        listener.onEnd(status, arrayList_sv);
        super.onPostExecute(status);
    }
}
