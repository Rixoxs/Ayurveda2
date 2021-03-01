package com.example.ayurveda2;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.material.button.MaterialButtonToggleGroup;


public class MainActivity14 extends AppCompatActivity {

    TextView txt34, txt35, txt36, txt37, txtScore1, txtScore2, txtScore3;
    ImageButton testImageButton0;
    RadioButton radioButton43, radioButton44, radioButton45, radioButton46, radioButton47, radioButton48;
    RadioGroup radioGroup2, radioGroup13;
    String string_score1, string_score2, string_score3;
    int score1;
    int score2;
    int score3;


    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, PrincipalActivity.class));
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main14);

        txt34 = findViewById(R.id.txt34);
        txt35 = findViewById(R.id.txt35);
        txt36 = findViewById(R.id.txt36);
        txt37 = findViewById(R.id.txt37);

        testImageButton0 = findViewById(R.id.testImageButton0);

        radioGroup2 = findViewById(R.id.radioGroup2);
        radioButton43 = findViewById(R.id.radioButton43);
        radioButton44 = findViewById(R.id.radioButton44);
        radioButton45 = findViewById(R.id.radioButton45);

        radioGroup13 = findViewById(R.id.radioGroup13);
        radioButton46 = findViewById(R.id.radioButton46);
        radioButton47 = findViewById(R.id.radioButton47);
        radioButton48 = findViewById(R.id.radioButton48);

        txtScore1 = findViewById(R.id.txtScore1);
        txtScore1.setVisibility(View.INVISIBLE);
        txtScore2 = findViewById(R.id.txtScore2);
        txtScore2.setVisibility(View.INVISIBLE);
        txtScore3 = findViewById(R.id.txtScore3);
        txtScore3.setVisibility(View.INVISIBLE);



        testImageButton0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                        if (radioButton46.isChecked() == true) {
                            score1++;
                            txtScore1.setText("score1 : " + score1);}
                        if (radioButton47.isChecked() == true) {
                            score2++;
                            txtScore2.setText("score2 : " + score2);}
                        if (radioButton48.isChecked() == true) {
                            score3++;
                            txtScore3.setText("score3 : " + score3);}
                        if (radioButton43.isChecked() == true) {
                            score1++;
                            txtScore1.setText("score1 : " + score1);}
                        if (radioButton44.isChecked() == true) {
                            score2++;
                            txtScore2.setText("score2 : " + score2);}
                        if (radioButton45.isChecked() == true) {
                            score3++;
                            txtScore3.setText("score3 : " + score3);}




                Intent intent = new Intent(MainActivity14.this, MainActivity10.class);

                string_score1 = String.valueOf(score1);
                intent.putExtra("score1", string_score1);
                string_score2 = String.valueOf(score2);
                intent.putExtra("score2", string_score2);
                string_score3 = String.valueOf(score3);
                intent.putExtra("score3", string_score3);

                startActivity(intent);
                finish();
            }

        });
    }
}
