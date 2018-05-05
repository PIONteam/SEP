package com.example.android.sep;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

public class daftarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_daftar);
    }
}
