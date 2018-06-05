package com.example.android.sep;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class berkasActivity extends AppCompatActivity {
Button btnSelesai;
TextView berkas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_berkas);

        berkas = (TextView) findViewById(R.id.berkas);

        Bundle bundle = getIntent().getExtras();
        berkas.setText(bundle.getString("parse_berkas"));

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
