package com.takfirm.than.androidintern;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.takfirm.than.androidintern.activities.MainActivity;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Thread thread = new Thread(){
            @Override
            public void run() {
                super.run();

                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    startActivity(new Intent(StartActivity.this, MainActivity.class));
                    finish();
                    overridePendingTransition(R.anim.activity_open_translate,R.anim.activity_close_scale);

                }
            }
        };

        thread.start();
    }
}
