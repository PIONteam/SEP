package com.example.android.sep;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class loginActivity extends AppCompatActivity {
private TextView textView33;
private Button button6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);

        textView33=(TextView)findViewById(R.id.textView33);
        button6=(Button)findViewById(R.id.button6);

        textView33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sep = new Intent(getApplicationContext(),daftarActivity.class);
                startActivity(sep);
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sep = new Intent(getApplicationContext(),cetakActivity.class);
                startActivity(sep);
            }
        });
    }
}
