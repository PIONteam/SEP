package com.example.android.sep;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class sep5Activity extends AppCompatActivity {
    private Button button22;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sep5);

        button22=(Button)findViewById(R.id.button22);

        // function tombol
        button22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sep5 = new Intent(getApplicationContext(),loginActivity.class);
                startActivity(sep5);
            }
        });
    }
}
