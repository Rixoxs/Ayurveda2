package com.example.ayurveda2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity12 extends AppCompatActivity {

    TextView txt28, txt30, txt31, txtScore1, txtScore2,txtScore3;

    RadioButton radioButton19, radioButton20, radioButton21, radioButton22, radioButton23, radioButton24, radioButton28, radioButton29, radioButton30;

    String string_score1, string_score2, string_score3;

    ImageButton text4imageButton;
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
                startActivity(new Intent(MainActivity12.this, MainActivity14.class));
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
        setContentView(R.layout.activity_main12);

        txt28=findViewById(R.id.txt28);
        txt30=findViewById(R.id.txt30);
        txt31=findViewById(R.id.txt31);

        txtScore1= findViewById(R.id.txtscore10);
        txtScore1.setVisibility(View.INVISIBLE);
        txtScore2= findViewById(R.id.txtscore11);
        txtScore2.setVisibility(View.INVISIBLE);
        txtScore3= findViewById(R.id.txtscore12);
        txtScore3.setVisibility(View.INVISIBLE);

        radioButton19=findViewById(R.id.radioButton19);
        radioButton20=findViewById(R.id.radioButton20);
        radioButton21=findViewById(R.id.radioButton21);
        radioButton22=findViewById(R.id.radioButton22);
        radioButton23=findViewById(R.id.radioButton23);
        radioButton24=findViewById(R.id.radioButton24);
        radioButton28=findViewById(R.id.radioButton28);
        radioButton29=findViewById(R.id.radioButton29);
        radioButton30=findViewById(R.id.radioButton30);

        text4imageButton=findViewById(R.id.text4imageButton);

        string_score1 = getIntent().getStringExtra("score1");
        score1 = Integer.parseInt(string_score1);
        txtScore1.setText("score1: " + score1);

        string_score2 = getIntent().getStringExtra("score2");
        score2 = Integer.parseInt(string_score2);
        txtScore2.setText("score2: " + score2);

        string_score3 = getIntent().getStringExtra("score3");
        score3 = Integer.parseInt(string_score3);
        txtScore3.setText("score3: " + score3);

        text4imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (radioButton21.isChecked() == true){
                    score1++;
                    txtScore1.setText("score1:" + score1);}
                if (radioButton20.isChecked() == true){
                    score2++;
                    txtScore2.setText("score2:" + score2);}
                if (radioButton19.isChecked() == true){
                    score3++;
                    txtScore3.setText("score3:" + score3);}
                if (radioButton24.isChecked() == true) {
                    score1++;
                    txtScore1.setText("score1:" + score1);}
                if (radioButton23.isChecked() == true) {
                    score2++;
                    txtScore2.setText("score2:" + score2);}
                if (radioButton22.isChecked() == true) {
                    score3++;
                    txtScore3.setText("score3:" + score3);}
                if (radioButton28.isChecked() == true){
                    score1++;
                    txtScore1.setText("score1:" + score1);}
                if (radioButton29.isChecked() == true){
                    score2++;
                    txtScore2.setText("score2:" + score2);}
                if (radioButton30.isChecked() == true){
                    score3++;
                    txtScore3.setText("score3:" + score3);}

                Intent intent = new Intent(MainActivity12.this, MainActivity13.class);

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