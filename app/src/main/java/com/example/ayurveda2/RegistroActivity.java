package com.example.ayurveda2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class RegistroActivity extends AppCompatActivity {

    private EditText edittxt1;
    private EditText edittxt2;
    private EditText edittxt3;
    private EditText edittxt6;
    private EditText edittxt7;
    private EditText edittxt8;
    private EditText edittxt9;
    private CircleImageView FotoPerfil;
    private String name = "" ;
    private String apellidos = "" ;
    private String password = "" ;
    private String password2 = "" ;
    private String nacimiento = "" ;
    private String email = "" ;
    private String pais = "" ;
    private String hombre;
    private String mujer;

    FirebaseAuth mAuth;
    DatabaseReference mDatabase;

    RadioButton radioButton49, radioButton50;
    RadioGroup radioGroup48;
    Button registrarbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        mAuth = FirebaseAuth.getInstance();

        mDatabase = FirebaseDatabase.getInstance().getReference();

        edittxt1 = findViewById(R.id.edittxt1);
        edittxt2 = findViewById(R.id.edittxt2);
        edittxt3 = findViewById(R.id.edittxt3);
        edittxt6 = findViewById(R.id.edittxt6);
        edittxt7 = findViewById(R.id.edittxt7);
        edittxt8 = findViewById(R.id.edittxt8);
        edittxt9 = findViewById(R.id.edittxt9);

        FotoPerfil = findViewById(R.id.FotoPerfil);

        radioButton49=findViewById(R.id.radioButton49);
        radioButton50=findViewById(R.id.radioButton50);

        radioGroup48=findViewById(R.id.radioGroup48);


        registrarbutton = findViewById(R.id.registrarbutton);



        registrarbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name = edittxt1.getText().toString();
                apellidos = edittxt6.getText().toString();
                password = edittxt3.getText().toString();
                password2 = edittxt7.getText().toString();
                nacimiento = edittxt8.getText().toString();
                email = edittxt2.getText().toString();
                pais = edittxt9.getText().toString();
                hombre = radioButton49.getText().toString();
                mujer = radioButton50.getText().toString();

                if (!name.isEmpty() && !apellidos.isEmpty() && !password.isEmpty() && !password2.isEmpty() && !nacimiento.isEmpty() && !email.isEmpty() && !pais.isEmpty()) {

                    if (password.length() >= 6) {
                        if (password.equals(password2)) {
                            if (password2.equals(password)) {
                                if (radioButton49.isChecked() || radioButton50.isChecked()) {
                                    registerUser();
                                }
                            } else {
                                Toast.makeText(RegistroActivity.this, "Las contraseñas deben ser iguales", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(RegistroActivity.this, "Las contraseñas deben ser iguales", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(RegistroActivity.this, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show();
                    }

                }
                if (name.isEmpty()) {
                    edittxt1.setError("Por favor introduzca su nombre");
                    edittxt1.requestFocus();}
                if (apellidos.isEmpty()) {
                    edittxt6.setError("Por favor introduzca su apellido");
                    edittxt6.requestFocus();}
                if (password.isEmpty()) {
                    edittxt3.setError("Por favor introduzca su contraseña");
                    edittxt3.requestFocus();}
                if (password2.isEmpty()) {
                    edittxt7.setError("Por favor introduzca su contraseña");
                    edittxt7.requestFocus();}
                if (nacimiento.isEmpty()) {
                    edittxt8.setError("Por favor introduzca su fecha de nacimiento");
                    edittxt8.requestFocus();}
                if (email.isEmpty()) {
                    edittxt2.setError("Por favor introduzca su Email");
                    edittxt2.requestFocus();}
                if (pais.isEmpty()) {
                    edittxt9.setError("Por favor introduzca su pais");
                    edittxt9.requestFocus();}
            }
        });

    }

    private void registerUser() {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    Map<String, Object> map = new HashMap<>();
                    map.put("Nombres", name);
                    map.put("Apellidos", apellidos);
                    map.put("Contraseña", password);
                    map.put("Contraseña 2", password2);
                    map.put("Fecha de nacimiento", nacimiento);
                    map.put("Correo electronico", email);
                    map.put("Pais", pais);
                    map.put("Sexo", hombre);
                    map.put("Sexo2", mujer);

                    String id = mAuth.getCurrentUser().getUid();

                    mDatabase.child("Usuarios").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task2) {
                            if (task2.isSuccessful()) {
                                startActivity(new Intent(RegistroActivity.this, IniciarSesion.class));
                                finish();
                            } else {
                                Toast.makeText(RegistroActivity.this, "No se pudieron crear los datos correctamente", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else {
                    Toast.makeText(RegistroActivity.this, "No se pudo registrar este usuario", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}