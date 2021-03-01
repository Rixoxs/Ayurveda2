package com.example.ayurveda2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity10 extends AppCompatActivity {
    
    TextView txt23, txt24, txt25, txtScore1, txtScore2, txtScore3;
    ImageButton test2imageButton;
    RadioButton radioButton7, radioButton8, radioButton9, radioButton10, radioButton11, radioButton12, radioButton13, radioButton14, radioButton15;
    RadioGroup radioGroup3, radioGroup4, radioGroup5;
    String string_score1, string_score2, string_score3;
    private int score1;
    private int score2;
    private int score3;

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("Quieres reiniciar del test?");
        builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(MainActivity10.this, MainActivity14.class));
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user select "No", just cancel this dialog and continue with app
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main10);
        
        txt23=findViewById(R.id.txt23);
        txt24= findViewById(R.id.txt24);
        txt25= findViewById(R.id.txt25);
        
        test2imageButton= findViewById(R.id.testimageButton2);

        radioGroup3 = findViewById(R.id.radioGroup3);
        radioGroup4 = findViewById(R.id.radioGroup4);
        radioGroup5 = findViewById(R.id.radioGroup5);
        
        radioButton7= findViewById(R.id.radioButton7);
        radioButton8= findViewById(R.id.radioButton8);
        radioButton9= findViewById(R.id.radioButton9);
        radioButton10= findViewById(R.id.radioButton10);
        radioButton11= findViewById(R.id.radioButton11);
        radioButton12= findViewById(R.id.radioButton12);
        radioButton13= findViewById(R.id.radioButton13);
        radioButton14= findViewById(R.id.radioButton14);
        radioButton15= findViewById(R.id.radioButton15);

        txtScore1= findViewById(R.id.txtscore4);
        txtScore1.setVisibility(View.INVISIBLE);
        txtScore2= findViewById(R.id.txtscore5);
        txtScore2.setVisibility(View.INVISIBLE);
        txtScore3= findViewById(R.id.txtscore6);
        txtScore3.setVisibility(View.INVISIBLE);

        string_score1 = getIntent().getStringExtra("score1");
        score1 = Integer.parseInt(string_score1);
        txtScore1.setText("score1: " + score1);

        string_score2 = getIntent().getStringExtra("score2");
        score2 = Integer.parseInt(string_score2);
        txtScore2.setText("score2: " + score2);

        string_score3 = getIntent().getStringExtra("score3");
        score3 = Integer.parseInt(string_score3);
        txtScore3.setText("score3: " + score3);


        test2imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (radioButton7.isChecked() == true) {
                    score3++;
                    txtScore3.setText("score3:" + score3);}
                if (radioButton8.isChecked() == true) {
                    score2++;
                    txtScore2.setText("score2:" + score2);}
                if (radioButton9.isChecked() == true) {
                    score1++;
                    txtScore1.setText("score1:" + score1);}
                if (radioButton10.isChecked() == true) {
                    score3++;
                    txtScore3.setText("score3:" + score3);}
                if (radioButton11.isChecked() == true) {
                    score2++;
                    txtScore2.setText("score2:" + score2);}
                if (radioButton12.isChecked() == true) {
                    score1++;
                    txtScore1.setText("score1:" + score1);}
                if (radioButton13.isChecked() == true) {
                    score3++;
                    txtScore3.setText("score3:" + score3);}
                if (radioButton14.isChecked() == true) {
                    score2++;
                    txtScore2.setText("score2:" + score2);}
                if (radioButton15.isChecked() == true) {
                    score1++;
                    txtScore1.setText("score1:" + score1);}

                Intent intent = new Intent(MainActivity10.this, MainActivity11.class);

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