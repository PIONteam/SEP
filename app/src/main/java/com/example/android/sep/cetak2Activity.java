package com.example.android.sep;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class cetak2Activity extends AppCompatActivity {
private Button button5;
TextView textView19, textView39, textView40, textView41, textView42, textView43, textView44, textView47;
String berkas, warna, kertas, orientasi, cetak, layanan, salinan, komentar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cetak2);

        textView19 = (TextView) findViewById(R.id.textView19);
        textView39 = (TextView) findViewById(R.id.textView39);
        textView40 = (TextView) findViewById(R.id.textView40);
        textView41 = (TextView) findViewById(R.id.textView41);
        textView42 = (TextView) findViewById(R.id.textView42);
        textView43 = (TextView) findViewById(R.id.textView43);
        textView44 = (TextView) findViewById(R.id.textView44);
        textView47 = (TextView) findViewById(R.id.textView47);

        Bundle bundle = getIntent().getExtras();
        textView39.setText(bundle.getString("parse_warna"));
        textView40.setText(bundle.getString("parse_kertas"));
        textView41.setText(bundle.getString("parse_orientasi"));
        textView42.setText(bundle.getString("parse_radio"));
        textView43.setText(bundle.getString("parse_layanan"));
        textView44.setText(bundle.getString("parse_salinan"));
        textView47.setText(bundle.getString("parse_komentar"));

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
