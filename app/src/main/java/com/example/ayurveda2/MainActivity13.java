package com.example.ayurveda2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity13 extends AppCompatActivity {
    TextView txt31, txt32, txtScore1, txtScore2,txtScore3;

    RadioButton radioButton31, radioButton32, radioButton33, radioButton34, radioButton35, radioButton36;

    String string_score1, string_score2, string_score3;

    Button calcularButton;

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
                startActivity(new Intent(MainActivity13.this, MainActivity14.class));
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
        setContentView(R.layout.activity_main13);

        txt31=findViewById(R.id.txt31);
        txt32=findViewById(R.id.txt32);

        txtScore1= findViewById(R.id.txtscore13);
        txtScore1.setVisibility(View.INVISIBLE);
        txtScore2= findViewById(R.id.txtscore14);
        txtScore2.setVisibility(View.INVISIBLE);
        txtScore3= findViewById(R.id.txtscore15);
        txtScore3.setVisibility(View.INVISIBLE);

        radioButton31=findViewById(R.id.radioButton31);
        radioButton32=findViewById(R.id.radioButton32);
        radioButton33=findViewById(R.id.radioButton33);
        radioButton34=findViewById(R.id.radioButton34);
        radioButton35=findViewById(R.id.radioButton35);
        radioButton36=findViewById(R.id.radioButton36);

        calcularButton=findViewById(R.id.calcularButton);

        string_score1 = getIntent().getStringExtra("score1");
        score1 = Integer.parseInt(string_score1);
        txtScore1.setText("score1: " + score1);

        string_score2 = getIntent().getStringExtra("score2");
        score2 = Integer.parseInt(string_score2);
        txtScore2.setText("score2: " + score2);

        string_score3 = getIntent().getStringExtra("score3");
        score3 = Integer.parseInt(string_score3);
        txtScore3.setText("score3: " + score3);

        calcularButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (radioButton33.isChecked() == true) {
                    score1++;
                    txtScore1.setText("score1:" + score1);
                }
                if (radioButton32.isChecked() == true) {
                    score2++;
                    txtScore2.setText("score2:" + score2);
                }
                if (radioButton31.isChecked() == true) {
                    score3++;
                    txtScore3.setText("score3:" + score3);
                }
                if (radioButton36.isChecked() == true) {
                    score1++;
                    txtScore1.setText("score1:" + score1);
                }
                if (radioButton35.isChecked() == true) {
                    score2++;
                    txtScore2.setText("score2:" + score2);
                }
                if (radioButton34.isChecked() == true) {
                    score3++;
                    txtScore3.setText("score3:" + score3);
                }

                if (score3 >= score2 && score3 >= score1) {
                    Intent intent3 = new Intent(MainActivity13.this, MainActivity17.class);
                    startActivity(intent3);
                    finish();
                }
                else if (score2 >= score3 && score2 >= score1) {
                    Intent intent2 = new Intent(MainActivity13.this, MainActivity16.class);
                    startActivity(intent2);
                    finish();
                }
                else if (score1 >= score3 && score1 >= score2) {
                    Intent intent1 = new Intent(MainActivity13.this, MainActivity15.class);
                    startActivity(intent1);
                    finish();
                }
            }
        });
    }
}