package com.example.kridho.aplikasivoting;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Sreen_Awal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sreen__awal);
        Thread myThread = new Thread(){
            @Override
            public void run(){
                try {
                    sleep(4000);

                    SharedPreferences prefs = getSharedPreferences(Constant.KEY_SHAREDPREFS, MODE_PRIVATE);
                    Boolean isLogin = prefs.getBoolean(Constant.KEY_SHAREDPREFS_LOGIN_STATUS, false);
                    if (isLogin) {
                        startActivity(new Intent(Sreen_Awal.this,MenuUtama.class));
                    } else {
                        startActivity(new Intent(Sreen_Awal.this,MainActivity.class));
                    }

                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        myThread.start();
    }
}


