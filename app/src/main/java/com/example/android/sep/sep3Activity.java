package com.example.android.sep;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class sep3Activity extends AppCompatActivity {
    private Button button20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sep3);

        button20=(Button)findViewById(R.id.button20);

        // function tombol
        button20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sep3 = new Intent(getApplicationContext(),loginActivity.class);
                startActivity(sep3);
            }
        });
    }
}
