package com.example.demoandroidapp;

import android.os.Bundle;

import com.google.gson.JsonObject;

import okhttp3.MultipartBody;

import okhttp3.RequestBody;

public class Methods {

    public RequestBody getRequestBody(String method_name, Bundle bundle){

        JsonObject postObj = new JsonObject();

        postObj.addProperty("method_name", method_name);

        if(method_name.equals("method_login")){
            String username = bundle.getString("username");
            String password = bundle.getString("password");

            postObj.addProperty("username", username);
            postObj.addProperty("password", password);
        }

        String post_data = postObj.toString();

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("data", post_data)
                .build();

        return requestBody;

    }
}
