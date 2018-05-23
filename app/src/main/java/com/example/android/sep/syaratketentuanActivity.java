package com.example.android.sep;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class syaratketentuanActivity extends AppCompatActivity {
private Button button3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syaratketentuan);

        button3=(Button)findViewById(R.id.button3);

        // function tombol
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cetak = new Intent(getApplicationContext(),berkasActivity.class);
                startActivity(cetak);
            }
        });


    }
}
