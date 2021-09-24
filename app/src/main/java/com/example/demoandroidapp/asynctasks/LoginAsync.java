package com.example.demoandroidapp.asynctasks;

import android.os.AsyncTask;

import com.example.demoandroidapp.listeners.LoginAsyncListener;
import com.example.demoandroidapp.utils.JsonUtils;

import okhttp3.RequestBody;

public class LoginAsync extends AsyncTask<Void, String, Boolean> {

    private RequestBody requestBody;
    private LoginAsyncListener listener;


    public LoginAsync(LoginAsyncListener listener, RequestBody requestBody){
        this.listener = listener;
        this.requestBody = requestBody;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {

        try {

            String link = "http://192.168.1.10/demoandroid/api.php";

            String result = JsonUtils.okhttpPost(link, requestBody);

            if(result.equals("true")){
                return true;
            }else {
                return false;
            }

        }catch (Exception e){
            return false;
        }

    }

    @Override
    protected void onPostExecute(Boolean status) {
        listener.onEnd(status);
        super.onPostExecute(status);
    }
}
