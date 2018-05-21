package com.example.android.sep;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class sep7Activity extends AppCompatActivity {
    private Button button24;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sep7);

        button24=(Button)findViewById(R.id.button24);

        // function tombol
        button24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sep7 = new Intent(getApplicationContext(),loginActivity.class);
                startActivity(sep7);
            }
        });
    }
}
