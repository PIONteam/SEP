package com.example.android.sep;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class sep4Activity extends AppCompatActivity {
    private Button button21;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sep4);

        button21=(Button)findViewById(R.id.button21);

        // function tombol
        button21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sep4 = new Intent(getApplicationContext(),loginActivity.class);
                startActivity(sep4);
            }
        });
    }
}
