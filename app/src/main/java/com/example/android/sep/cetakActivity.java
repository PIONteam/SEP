package com.example.android.sep;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.android.sep.app.App_Controller;
import com.nbsp.materialfilepicker.MaterialFilePicker;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class cetakActivity extends AppCompatActivity {
private Button btnCetak;
private Button button2;
TextView textView;
EditText EditText02;
private Spinner aaa, aaa1, aaa2, aaa3;
ProgressDialog pDialog;

    int success;
    ConnectivityManager conMgr;
    public int warna;
    public int orientasi;
    public int kertas;
    public int layanan;


    private String url = Server.URL + "cetak.php";

    private static final String TAG = cetakActivity.class.getSimpleName();

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    String tag_json_obj = "json_obj_req";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cetak);

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1001);
        }
        conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        {
            if (conMgr.getActiveNetworkInfo() != null
                    && conMgr.getActiveNetworkInfo().isAvailable()
                    && conMgr.getActiveNetworkInfo().isConnected()) {
            } else {
                Toast.makeText(getApplicationContext(), "No Internet Connection",
                        Toast.LENGTH_LONG).show();
            }
        }

        aaa = (Spinner) findViewById(R.id.spinner1);
        aaa1 = (Spinner) findViewById(R.id.spinner2);
        aaa2 = (Spinner) findViewById(R.id.spinner3);
        aaa3 = (Spinner) findViewById(R.id.spinner4);
        EditText02 = (EditText) findViewById(R.id.EditText02);

       final String[] list1=getResources().getStringArray(R.array.list1);
       final ArrayAdapter<String> adapter= new ArrayAdapter<String>(this,R.layout.item_spin,R.id.txItemSpin,list1);
       aaa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               //String berwarna = new String("Berwarna");
               //String hitamPutih = new String("Hitam putih");
               if (adapter.getItem(position).equals("Berwarna")){
                    warna = 1;
               }
               else if (adapter.getItem(position).equals("Hitam putih")){
                   warna = 2;
               }
               Toast.makeText(getApplicationContext(), "Selected "+ warna,Toast.LENGTH_SHORT).show();
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
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });





        btnCetak=(Button)findViewById(R.id.btnCetak);
        button2 = (Button)findViewById(R.id.button2);
        textView = (TextView)findViewById(R.id.textView23);

        // function tombol
        btnCetak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String keterangan = EditText02.getText().toString();
                if (conMgr.getActiveNetworkInfo() != null
                        && conMgr.getActiveNetworkInfo().isAvailable()
                        && conMgr.getActiveNetworkInfo().isConnected()) {
                    checkData(keterangan);
                } else {
                    Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
                }
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

    private void checkData(final String keterangan) {
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        pDialog.setMessage("");
        showDialog();
        StringRequest strReq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.e(TAG, "Register Response: " + response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    success = jObj.getInt(TAG_SUCCESS);

                    // Check for error node in json
                    if (success == 1) {

                        Intent sep = new Intent(getApplicationContext(),cetak2Activity.class);
                        startActivity(sep);

                        Toast.makeText(getApplicationContext(),
                                jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(getApplicationContext(),
                                jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();

                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();

                hideDialog();
            }
                 }) {
            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("keterangan", keterangan);

                return params;
            }

        };

        // Adding request to request queue
        App_Controller.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();

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
