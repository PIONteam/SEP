package com.example.android.sep;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class daftar2Activity extends AppCompatActivity {
private Button button17;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar2);

        button17=(Button)findViewById(R.id.button17);

        // function tombol
        button17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sep = new Intent(getApplicationContext(),loginActivity.class);
                startActivity(sep);
            }
        });
    }
}
