package com.example.android.sep;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class berkasActivity extends AppCompatActivity {
Button btnSelesai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_berkas);

        btnSelesai = (Button) findViewById(R.id.btnSelesai);

        btnSelesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent selesai = new Intent(berkasActivity.this, homeActivity.class);
                startActivity(selesai);
                finish();
            }
        });

    }
}
