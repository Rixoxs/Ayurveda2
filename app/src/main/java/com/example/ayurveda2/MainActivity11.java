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

public class MainActivity11 extends AppCompatActivity {

    TextView txt26, txt30, txtScore1, txtScore2,txtScore3;
    RadioButton radioButton16, radioButton17, radioButton18, radioButton25, radioButton26, radioButton27;
    ImageButton test3imageButton;
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
                startActivity(new Intent(MainActivity11.this, MainActivity14.class));
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
        setContentView(R.layout.activity_main11);

        txt26= findViewById(R.id.txt26);
        txt30= findViewById(R.id.txt30);

        txtScore1= findViewById(R.id.txtscore7);
        txtScore1.setVisibility(View.INVISIBLE);
        txtScore2= findViewById(R.id.txtscore8);
        txtScore2.setVisibility(View.INVISIBLE);
        txtScore3= findViewById(R.id.txtscore9);
        txtScore3.setVisibility(View.INVISIBLE);

        radioButton16= findViewById(R.id.radioButton16);
        radioButton17= findViewById(R.id.radioButton17);
        radioButton18= findViewById(R.id.radioButton18);
        radioButton25= findViewById(R.id.radioButton25);
        radioButton26= findViewById(R.id.radioButton26);
        radioButton27= findViewById(R.id.radioButton27);

        test3imageButton= findViewById(R.id.test3imageButton);

        string_score1 = getIntent().getStringExtra("score1");
        score1 = Integer.parseInt(string_score1);
        txtScore1.setText("score1: " + score1);

        string_score2 = getIntent().getStringExtra("score2");
        score2 = Integer.parseInt(string_score2);
        txtScore2.setText("score2: " + score2);

        string_score3 = getIntent().getStringExtra("score3");
        score3 = Integer.parseInt(string_score3);
        txtScore3.setText("score3: " + score3);

        test3imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (radioButton18.isChecked() == true){
                    score1++;
                    txtScore1.setText("score1:" + score1);}
                if (radioButton17.isChecked() == true){
                    score2++;
                    txtScore2.setText("score2:" + score2);}
                if (radioButton16.isChecked() == true){
                    score3++;
                    txtScore3.setText("score3:" + score3);}
                if (radioButton25.isChecked() == true){
                    score1++;
                    txtScore1.setText("score1:" + score1);}
                if (radioButton26.isChecked() == true){
                    score2++;
                    txtScore2.setText("score2:" + score2);}
                if (radioButton27.isChecked() == true){
                    score3++;
                    txtScore3.setText("score3:" + score3);}

                Intent intent = new Intent(MainActivity11.this, MainActivity12.class);

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