package com.example.ayurveda2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        //Agregar animaciones
        Animation animacion1 = AnimationUtils.loadAnimation( this, R.anim.desplazamiento_arriba);
        Animation animacion2 = AnimationUtils.loadAnimation( this, R.anim.desplazamiento_abajo);

        TextView txt9 = findViewById(R.id.txt9);
        TextView txt10 = findViewById(R.id.txt10);
        ImageView logoImageView = findViewById(R.id.logoImageView);

        txt9.setAnimation(animacion2);
        txt10.setAnimation(animacion2);
        logoImageView.setAnimation(animacion1);



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, FrasesActivity.class);
                startActivity(intent);
                finish();
            }

        },4000);


    }
}