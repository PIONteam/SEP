package com.example.android.sep;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class sep2Activity extends AppCompatActivity {
    private Button button19;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sep2);

        button19=(Button)findViewById(R.id.button19);

        // function tombol
        button19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sep2 = new Intent(getApplicationContext(),loginActivity.class);
                startActivity(sep2);
            }
        });
    }
}
