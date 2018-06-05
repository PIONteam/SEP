package com.example.android.sep;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

import android.net.Uri;
import android.os.SharedMemory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class cetakActivity extends AppCompatActivity implements View.OnClickListener{
private Button btnCetak;
private Button button2;
TextView textView, textView23;
EditText EditText02, EditText03, EditText0;
private Spinner aaa, aaa1, aaa2, aaa3;
RadioButton radioButton3, radioButton4;
RadioGroup radioGroup;

    ProgressDialog dialog;
    private String selectedFilePath;
    private static final int PICK_FILE_REQUEST = 1;

    int success;
    ConnectivityManager conMgr;
    public int warna;
    public int orientasi;
    public int kertas;
    public int layanan;
    String radio, id_pengguna1, isiSpinner3, isiSpinner, isiSpinner1, isiSpinner2;
    String warna1, kertas1, orientasi1, cetak1, layanan1;


    private static final String TAG_MESSAGE = "message";
    private static final String TAG = daftarActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cetak);

       // requestStoragePermission();

        aaa = (Spinner) findViewById(R.id.spinner1);
        aaa1 = (Spinner) findViewById(R.id.spinner2);
        aaa2 = (Spinner) findViewById(R.id.spinner3);
        aaa3 = (Spinner) findViewById(R.id.spinner4);
        EditText02 = (EditText) findViewById(R.id.EditText02);
        textView23 =(TextView) findViewById(R.id.textView23);
        radioButton3 = (RadioButton) findViewById(R.id.radioButton3);
        radioButton4 = (RadioButton) findViewById(R.id.radioButton4);
        EditText0=(EditText) findViewById(R.id.EditText0);
        EditText03=(EditText)findViewById(R.id.EditText03);
        radioGroup =(RadioGroup)findViewById(R.id.radioGroup);

        int selected = radioGroup.getCheckedRadioButtonId();
        if (selected == radioButton3.getId()){
            EditText03.setEnabled(false);
        }


        final String[] list1=getResources().getStringArray(R.array.list1);
       final ArrayAdapter<String> adapter= new ArrayAdapter<String>(this,R.layout.item_spin,R.id.txItemSpin,list1);
       aaa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

               if (adapter.getItem(position).equals("Berwarna")){
                    warna = 1;
               }
               else if (adapter.getItem(position).equals("Hitam putih")){
                   warna = 2;
               }
               Toast.makeText(getApplicationContext(), "Selected "+ warna,Toast.LENGTH_SHORT).show();
               isiSpinner =String.valueOf(warna).toString();
               warna1= String.valueOf(adapter.getItem(position));

           }
           @Override
           public void onNothingSelected(AdapterView<?> parent) {
           }
       });

        final String[] list2=getResources().getStringArray(R.array.list2);
        final ArrayAdapter<String> adapter1= new ArrayAdapter<String>(this,R.layout.item_spin,R.id.txItemSpin,list2);
        aaa1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (adapter1.getItem(position).equals("Portrait")){
                    orientasi = 1;
                }
                else if (adapter1.getItem(position).equals("Landscape")){
                    orientasi = 2;
                }
                Toast.makeText(getApplicationContext(), "Selected "+ orientasi,Toast.LENGTH_SHORT).show();
                isiSpinner1 =String.valueOf(orientasi).toString();
                orientasi1= String.valueOf(adapter1.getItem(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        final String[] list3=getResources().getStringArray(R.array.list3);
        final ArrayAdapter<String> adapter2= new ArrayAdapter<String>(this,R.layout.item_spin,R.id.txItemSpin,list3);
        aaa2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (adapter2.getItem(position).equals("A4")){
                    kertas = 1;
                }
                else if (adapter2.getItem(position).equals("F4")){
                    kertas = 2;
                }
                Toast.makeText(getApplicationContext(), "Selected "+ kertas,Toast.LENGTH_SHORT).show();
                isiSpinner2 =String.valueOf(kertas).toString();
                kertas1= String.valueOf(adapter2.getItem(position));
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        final String[] list4=getResources().getStringArray(R.array.list4);
        final ArrayAdapter<String> adapter3= new ArrayAdapter<String>(this,R.layout.item_spin,R.id.txItemSpin,list4);
        aaa3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (adapter3.getItem(position).equals("Staples saja")){
                    layanan = 1;
                }
                else if (adapter3.getItem(position).equals("Jilid Mika")){
                    layanan = 2;
                }
                else if (adapter3.getItem(position).equals("Jilid Jepang")){
                    layanan = 3;
                }
                else if (adapter3.getItem(position).equals("Jilid Spiral")){
                    layanan = 4;
                }
                Toast.makeText(getApplicationContext(), "Selected "+ layanan,Toast.LENGTH_SHORT).show();
                isiSpinner3 = String.valueOf(layanan).toString();
                layanan1= String.valueOf(adapter3.getItem(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnCetak=(Button)findViewById(R.id.btnCetak);
        button2 = (Button)findViewById(R.id.button2);
        textView = (TextView)findViewById(R.id.textView23);
        button2.setOnClickListener((View.OnClickListener) this);

    }

    @Override
    public void onClick(View v) {
        if(v== button2){

            //on attachment icon click
            showFileChooser();
        }
        if(v== btnCetak){

            //filepath
            if(selectedFilePath != null){
                dialog = ProgressDialog.show(cetakActivity.this,"","Uploading File...",true);
            }else{
                Toast.makeText(cetakActivity.this,"Please choose a File First",Toast.LENGTH_SHORT).show();
            }

            Bundle bundle = new Bundle();
            bundle.putString("parse_warna", String.valueOf(warna1).toString());
            bundle.putString("parse_orientasi", String.valueOf(orientasi1).toString());
            bundle.putString("parse_kertas", String.valueOf(kertas1).toString());
            bundle.putString("parse_layanan", String.valueOf(layanan1).toString());
            bundle.putString("parse_radio", pilihradio());
            bundle.putString("parse_salinan", EditText0.getText().toString());
            bundle.putString("parse_komentar", EditText02.getText().toString());


            Intent intent = new Intent(cetakActivity.this, cetak2Activity.class);
            intent.putExtras(bundle);
            startActivity(intent);

        }
    }


    //file chooser
    private void showFileChooser() {
        Intent intent = new Intent();
        //sets the select file to all types of files
        intent.setType("*/*");
        //allows to select data and return it
        intent.setAction(Intent.ACTION_GET_CONTENT);
        //starts new activity to select file and return data
        startActivityForResult(Intent.createChooser(intent,"Choose File to Upload.."),PICK_FILE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == PICK_FILE_REQUEST) {
                if (data == null) {
                    //no data present
                    return;
                }


                Uri selectedFileUri = data.getData();
                selectedFilePath = FilePath.getPath(this, selectedFileUri);
                Log.i(TAG, "Selected File Path:" + selectedFilePath);

                if (selectedFilePath != null && !selectedFilePath.equals("")) {
                    textView.setText(selectedFilePath);
                } else {
                    Toast.makeText(this, "Cannot upload file to server", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }


    public String pilihradio(){

        int selectedId = radioGroup.getCheckedRadioButtonId();

        if (selectedId == radioButton3.getId()){
            radio = radioButton3.getText().toString();

        }
        else if (selectedId == radioButton4.getId()){
            radio = "Cetak Halaman "+EditText03.getText().toString();

        }
        return radio;
    }


}
