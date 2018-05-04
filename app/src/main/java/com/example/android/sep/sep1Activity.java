package com.example.android.sep;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class sep1Activity extends AppCompatActivity {
private Button button18;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_sep1);

        button18=(Button)findViewById(R.id.button18);

        // function tombol
        button18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sep1 = new Intent(getApplicationContext(),loginActivity.class);
                startActivity(sep1);
            }
        });
    }
}
