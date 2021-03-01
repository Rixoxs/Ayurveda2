package com.example.ayurveda2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class MainActivity1 extends AppCompatActivity {
    TextView txt11, txt42, txt43, txt44, txt45;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        txt11=findViewById(R.id.txt11);
        txt42=findViewById(R.id.txt42);
        txt43=findViewById(R.id.txt43);
        txt44=findViewById(R.id.txt44);
        txt45=findViewById(R.id.txt45);

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(MainActivity1.this, PrincipalActivity.class));
        finish();
    }
}