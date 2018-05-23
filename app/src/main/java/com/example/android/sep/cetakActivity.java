package com.example.android.sep;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.nbsp.materialfilepicker.MaterialFilePicker;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;

import java.util.regex.Pattern;

public class cetakActivity extends AppCompatActivity {
private Button btnCetak;
private Button button2;
TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cetak);

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1001);
        }

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
        textView = (TextView)findViewById(R.id.textView23);

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
                new MaterialFilePicker()
                        .withActivity(cetakActivity.this)
                        .withRequestCode(1000)
                        .withHiddenFiles(true) // Show hidden files and folders
                        .start();
            }
        });


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1000 && resultCode == RESULT_OK) {
            String filePath = data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH);
            // Do anything with file
            textView.setText(filePath);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1001:{
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this, "Permission granted!", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this, "Permission no granted!", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }

    }
}
