package com.example.android.sep;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class cetakActivity extends AppCompatActivity {
private Button btnCetak;
private Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cetak);


        String list[]={"--Pilih warna--","Berwarna","Hitam putih"};
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> AdapterList = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item,list);
        spinner.setAdapter(AdapterList);

        String list1[]={"--Pilih Orientasi--","Portrait","Landscape"};
        Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<String> AdapterList1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item,list1);
        spinner1.setAdapter(AdapterList1);

        String list2[]={"--Pilih Kertas--","A4 70 gr","A4 80 gr","F4 70 gr","F4 80 gr"};
        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<String> AdapterList2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item,list2);
        spinner2.setAdapter(AdapterList2);

        String list3[]={"--Pilih Layanan--","Staples saja","Jilid Mika","Jilid Jepang","Jilid Spiral"};
        Spinner spinner3 = (Spinner) findViewById(R.id.spinner3);
        ArrayAdapter<String> AdapterList3 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item,list3);
        spinner3.setAdapter(AdapterList3);

    btnCetak=(Button)findViewById(R.id.btnCetak);
    button2 = (Button)findViewById(R.id.button2);
        // function tombol
        btnCetak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sep = new Intent(getApplicationContext(),cetak2Activity.class);
                startActivity(sep);
            }
        });
        // function tombol
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sep = new Intent(getApplicationContext(),cetak2Activity.class);
                startActivity(sep);
            }
        });


    }
}
