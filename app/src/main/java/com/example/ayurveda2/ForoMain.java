package com.example.ayurveda2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.ayurveda2.normas.ThreadsActivity;

public class ForoMain extends AppCompatActivity {

    TextView txtforo1, txtforo2, txtforo3, txtforo4, txtforo5, txtforo6, txtforo7, txtforo8, txtforo9, txtforo10, txtforo11, txtforo12,
            txtforo13, txtforo14, txtforo15, txtforo16, txtforo17, txtforo18, txtforo19, txtforo20, txtforo21, txtforo22, txtforo23, txtforo24;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foro_main2);

        txtforo1 = findViewById(R.id.txtforo1);
        txtforo2 = findViewById(R.id.txtforo2);
        txtforo3 = findViewById(R.id.txtforo3);
        txtforo4 = findViewById(R.id.txtforo4);
        txtforo5 = findViewById(R.id.txtforo5);
        txtforo6 = findViewById(R.id.txtforo6);
        txtforo7 = findViewById(R.id.txtforo7);
        txtforo8 = findViewById(R.id.txtforo8);
        txtforo9 = findViewById(R.id.txtforo9);
        txtforo10 = findViewById(R.id.txtforo10);
        txtforo11 = findViewById(R.id.txtforo11);
        txtforo12 = findViewById(R.id.txtforo12);
        txtforo13 = findViewById(R.id.txtforo13);
        txtforo14 = findViewById(R.id.txtforo14);
        txtforo15 = findViewById(R.id.txtforo15);
        txtforo16 = findViewById(R.id.txtforo16);
        txtforo17 = findViewById(R.id.txtforo17);
        txtforo18 = findViewById(R.id.txtforo18);
        txtforo19 = findViewById(R.id.txtforo19);
        txtforo20 = findViewById(R.id.txtforo20);
        txtforo21 = findViewById(R.id.txtforo21);
        txtforo22 = findViewById(R.id.txtforo22);
        txtforo23 = findViewById(R.id.txtforo23);
        txtforo24 = findViewById(R.id.txtforo24);

        txtforo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(ForoMain.this, ThreadsActivity.class);
                startActivity(intent1);
            }
        });
    }
}