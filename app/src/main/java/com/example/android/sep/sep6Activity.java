package com.example.android.sep;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class sep6Activity extends AppCompatActivity {
    private Button button23;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sep6);

        button23=(Button)findViewById(R.id.button23);

        // function tombol
        button23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sep6 = new Intent(getApplicationContext(),loginActivity.class);
                startActivity(sep6);
            }
        });
    }
}
