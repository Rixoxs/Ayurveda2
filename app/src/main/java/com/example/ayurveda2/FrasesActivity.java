package com.example.ayurveda2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class FrasesActivity extends AppCompatActivity {

private ImageView fraseimageView;
private ImageButton fraseimageButton;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    int num_aleatorio = (int) (Math.random() * 58);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frases);

        fraseimageButton = (ImageButton) findViewById(R.id.fraseimageButton);
        fraseimageView = (ImageView) findViewById(R.id.fraseimageView);


        int id;
        if (num_aleatorio == 0 || num_aleatorio == 58) {
            id = getResources().getIdentifier("fondofrases1","drawable", getPackageName());
            fraseimageView.setImageResource(id);
        } else if (num_aleatorio == 1 || num_aleatorio == 57) {
            id = getResources().getIdentifier("fondofrases2", "drawable", getPackageName());
            fraseimageView.setImageResource(id);
        } else if (num_aleatorio == 2 || num_aleatorio == 56) {
            id = getResources().getIdentifier("fondofrases3", "drawable", getPackageName());
            fraseimageView.setImageResource(id);
        } else if (num_aleatorio == 3 || num_aleatorio == 55) {
            id = getResources().getIdentifier("fondofrases4", "drawable", getPackageName());
            fraseimageView.setImageResource(id);
        } else if (num_aleatorio == 4 || num_aleatorio == 54) {
            id = getResources().getIdentifier("fondofrases5", "drawable", getPackageName());
            fraseimageView.setImageResource(id);
        } else if (num_aleatorio == 5 || num_aleatorio == 53) {
            id = getResources().getIdentifier("fondofrases6", "drawable", getPackageName());
            fraseimageView.setImageResource(id);
        } else if (num_aleatorio == 6 || num_aleatorio == 52) {
            id = getResources().getIdentifier("fondofrases7", "drawable", getPackageName());
            fraseimageView.setImageResource(id);
        } else if (num_aleatorio == 7 || num_aleatorio == 51) {
            id = getResources().getIdentifier("fondofrases8", "drawable", getPackageName());
            fraseimageView.setImageResource(id);
        } else if (num_aleatorio == 8 || num_aleatorio == 50) {
            id = getResources().getIdentifier("fondofrases9", "drawable", getPackageName());
            fraseimageView.setImageResource(id);
        } else if (num_aleatorio == 9 || num_aleatorio == 49) {
            id = getResources().getIdentifier("fondofrases10", "drawable", getPackageName());
            fraseimageView.setImageResource(id);
        } else if (num_aleatorio == 10 || num_aleatorio == 48) {
            id = getResources().getIdentifier("fondofrases11", "drawable", getPackageName());
            fraseimageView.setImageResource(id);
        } else if (num_aleatorio == 11 || num_aleatorio == 47) {
            id = getResources().getIdentifier("fondofrases12", "drawable", getPackageName());
            fraseimageView.setImageResource(id);
        } else if (num_aleatorio == 12 || num_aleatorio == 46) {
            id = getResources().getIdentifier("fondofrases13", "drawable", getPackageName());
            fraseimageView.setImageResource(id);
        } else if (num_aleatorio == 13 || num_aleatorio == 45) {
            id = getResources().getIdentifier("fondofrases14", "drawable", getPackageName());
            fraseimageView.setImageResource(id);
        } else if (num_aleatorio == 14 || num_aleatorio == 44) {
            id = getResources().getIdentifier("fondofrases15", "drawable", getPackageName());
            fraseimageView.setImageResource(id);
        } else if (num_aleatorio == 15 || num_aleatorio == 43) {
            id = getResources().getIdentifier("fondofrases16", "drawable", getPackageName());
            fraseimageView.setImageResource(id);
        } else if (num_aleatorio == 16 || num_aleatorio == 42) {
            id = getResources().getIdentifier("fondofrases17", "drawable", getPackageName());
            fraseimageView.setImageResource(id);
        } else if (num_aleatorio == 17 || num_aleatorio == 41) {
            id = getResources().getIdentifier("fondofrases18", "drawable", getPackageName());
            fraseimageView.setImageResource(id);
        } else if (num_aleatorio == 18 || num_aleatorio == 40) {
            id = getResources().getIdentifier("fondofrases19", "drawable", getPackageName());
            fraseimageView.setImageResource(id);
        } else if (num_aleatorio == 19 || num_aleatorio == 39) {
            id = getResources().getIdentifier("fondofrases20", "drawable", getPackageName());
            fraseimageView.setImageResource(id);
        } else if (num_aleatorio == 20 || num_aleatorio == 38) {
            id = getResources().getIdentifier("fondofrases21", "drawable", getPackageName());
            fraseimageView.setImageResource(id);
        } else if (num_aleatorio == 21 || num_aleatorio == 37) {
            id = getResources().getIdentifier("fondofrases22", "drawable", getPackageName());
            fraseimageView.setImageResource(id);
        } else if (num_aleatorio == 22 || num_aleatorio == 36) {
            id = getResources().getIdentifier("fondofrases23", "drawable", getPackageName());
            fraseimageView.setImageResource(id);
        } else if (num_aleatorio == 23 || num_aleatorio == 35) {
            id = getResources().getIdentifier("fondofrases24", "drawable", getPackageName());
            fraseimageView.setImageResource(id);
        } else if (num_aleatorio == 24 || num_aleatorio == 34) {
            id = getResources().getIdentifier("fondofrases25", "drawable", getPackageName());
            fraseimageView.setImageResource(id);
        } else if (num_aleatorio == 25 || num_aleatorio == 33) {
            id = getResources().getIdentifier("fondofrases26", "drawable", getPackageName());
            fraseimageView.setImageResource(id);
        } else if (num_aleatorio == 26 || num_aleatorio == 32) {
            id = getResources().getIdentifier("fondofrases27", "drawable", getPackageName());
            fraseimageView.setImageResource(id);
        } else if (num_aleatorio == 27 || num_aleatorio == 31) {
            id = getResources().getIdentifier("fondofrases28", "drawable", getPackageName());
            fraseimageView.setImageResource(id);
        }

        fraseimageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(FrasesActivity.this, IniciarSesion.class);
                startActivity(i);
                finish();
            }
        });
    }
}
