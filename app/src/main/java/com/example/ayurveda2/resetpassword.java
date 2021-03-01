package com.example.ayurveda2;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class resetpassword extends AppCompatActivity {


    private Button boton8;
    private EditText edittxt88;
    TextView textopassword;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resetpassword);

        boton8 = findViewById(R.id.boton8);
        edittxt88 = findViewById(R.id.edittxt88);
        textopassword = findViewById(R.id.textopassword);

        mAuth = FirebaseAuth.getInstance();

        boton8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
             String usermail = edittxt88.getText().toString();

             if (TextUtils.isEmpty(usermail))
             {
                 Toast.makeText(resetpassword.this, "Por favor, introduce una direccion de correo electronico valida", Toast.LENGTH_SHORT).show();
             }
             else{
                 mAuth.sendPasswordResetEmail(usermail).addOnCompleteListener(new OnCompleteListener<Void>() {
                     @Override
                     public void onComplete(@NonNull Task<Void> task)
                     {
                      if (task.isSuccessful())
                      {
                          startActivity(new Intent(resetpassword.this, IniciarSesion.class));
                      }
                      else {
                          String message = task.getException().getMessage();
                          Toast.makeText(resetpassword.this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show();
                      }
                     }
                 });
             }
            }
        });


    }
}
