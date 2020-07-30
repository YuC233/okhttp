package com.example.okhttp;

import androidx.appcompat.app.AppCompatActivity;

import android.nfc.FormatException;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button bt_get;
    private Button bt_post;

    final OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt_get = (Button) findViewById(R.id.bt_get);
        bt_post = (Button) findViewById(R.id.bt_post);
        bt_get.setOnClickListener(this);
        bt_post.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_get:
                getRquest();
                break;
            case R.id.bt_post:
                postRequest();
                break;
        }

    }

    private void getRquest() {
        final Request request = new Request.Builder()
                .get()
                .tag(this)
                .url("http://www.baidu.com")
                .build();
        //在子线程进行刷新网络
        new Thread(new Runnable() {
            @Override
            public void run() {
                Response response = null;  //
                try {
                    response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        Log.i("hyc", "--------get是否响应" + response.body().toString());
                    } else {
                        throw new IOException("Unexpected code" + response);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void postRequest() {


    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}