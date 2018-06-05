package com.example.android.sep;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import static com.example.android.sep.loginActivity.TAG_NAMA_PENGGUNA;
import static com.example.android.sep.loginActivity.my_shared_preferences;
import static com.example.android.sep.loginActivity.session_status;

public class syaratketentuanActivity extends AppCompatActivity {
    private Button button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syaratketentuan);

        button3 = (Button) findViewById(R.id.button3);

        // function tombol
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cetak = new Intent(getApplicationContext(), berkasActivity.class);
                startActivity(cetak);
            }
        });
    }
}
