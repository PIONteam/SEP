package com.example.android.sep;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class cetak2Activity extends AppCompatActivity {
private Button button5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cetak2);

        button5=(Button)findViewById(R.id.button5);

        // function tombol
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cetak = new Intent(getApplicationContext(),syaratketentuanActivity.class);
                startActivity(cetak);
            }
        });
    }
}
