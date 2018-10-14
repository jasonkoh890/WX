package com.example.roshnimd.trojanhackspeechtextv1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import java.lang.Thread;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        try {Thread.sleep(2000);}catch(InterruptedException e){}
        Intent intent = new Intent(this, FirstPage.class);
        startActivity(intent);
        finish();

    }

}
