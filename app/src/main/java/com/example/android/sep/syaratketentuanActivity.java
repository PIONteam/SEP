package com.example.android.sep;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class syaratketentuanActivity extends AppCompatActivity {
private Button button19, button20;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syaratketentuan);

        button19=(Button)findViewById(R.id.button19);
        button20=(Button)findViewById(R.id.button20);

        // function tombol
        button19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sep = new Intent(getApplicationContext(),cetak2Activity.class);
                startActivity(sep);
            }
        }); // function tombol
        button20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sep = new Intent(getApplicationContext(),berkasActivity.class);
                startActivity(sep);
            }
        });
    }
}
