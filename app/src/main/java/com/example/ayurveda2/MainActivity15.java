package com.example.ayurveda2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity15 extends AppCompatActivity {

    TextView txt38, txt46;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main15);

        txt38 = findViewById(R.id.txt38);
        txt46 = findViewById(R.id.txt46);
    }
}