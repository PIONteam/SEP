package com.example.android.sep;

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
    private Spinner combobox;
    private Button btpilih;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_cetak);
        combobox=(Spinner) findViewById(R.id.cbBox);
        btpilih=(Button)findViewById(R.id.btnCetak);
        String[] pilihan_menu=getResources().getStringArray(R.array.pilihan); // ambil menu dari string.xml
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,R.layout.item_spin,R.id.txItemSpin, pilihan_menu);
        combobox.setAdapter(adapter);

        combobox.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                Toast.makeText(cetakActivity.this, combobox.getSelectedItem().toString(), Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });

    }
    public void klikPilih(View v){
        String pilihan=combobox.getSelectedItem().toString();
        Toast.makeText(this,pilihan,Toast.LENGTH_SHORT).show();
    }
}
