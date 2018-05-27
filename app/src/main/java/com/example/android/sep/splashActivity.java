package com.example.android.sep;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import static com.example.android.sep.loginActivity.my_shared_preferences;
import static com.example.android.sep.loginActivity.session_status;

public class splashActivity extends AppCompatActivity {
    SharedPreferences sharedpreferences;
    Boolean session = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        sharedpreferences = getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE);
        session = sharedpreferences.getBoolean(session_status, false);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (session) {
                    startActivity(new Intent(getApplicationContext(), homeActivity.class));
                    finish();
                }
                else {
                    Intent intent = new Intent(getApplicationContext(), homesebelumActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

        }, 3000);

    }
}
