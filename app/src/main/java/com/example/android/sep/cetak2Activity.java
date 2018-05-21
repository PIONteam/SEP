package com.example.android.sep;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class cetak2Activity extends AppCompatActivity {
private Button btnKonf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cetak2);

        btnKonf=(Button)findViewById(R.id.button7);

        // function tombol
        btnKonf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sep = new Intent(getApplicationContext(),syaratketentuanActivity.class);
                startActivity(sep);
            }
        });
    }
}
