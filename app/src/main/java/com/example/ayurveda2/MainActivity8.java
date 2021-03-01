package com.example.ayurveda2;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity8 extends AppCompatActivity {

    TextView txt29;

    EditText txtv1, txtv2, txtv3, txtmulti1;
    TextView txtmenu;

    private CircleImageView fotoperfilmenu;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private FirebaseAuth mAuth;
    private DatabaseReference UsersRef;
    private StorageReference UserProfileImageRef;

    String currentUserID;

    Button button1;

    String user= "diegogonzalez80456@gmail.com";
    String password = "dagvlb08";
    String email = "diegogonzalez804@hotmail.com";
    String Nombre, Correo, Asunto, Texto;
    GmailSender sender;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Ayurveda");
        drawer = findViewById(R.id.drawer);

        mAuth = FirebaseAuth.getInstance();
        currentUserID = mAuth.getCurrentUser().getUid();
        UsersRef = FirebaseDatabase.getInstance().getReference().child("Usuarios");
        UserProfileImageRef = FirebaseStorage.getInstance().getReference().child("profileimage");

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        navigationView = findViewById(R.id.nav_view);
        View naView = navigationView.getHeaderView(0);
        fotoperfilmenu = naView.findViewById(R.id.fotoperfilmenu);
        txtmenu = naView.findViewById(R.id.txtmenu);

        UsersRef.child(currentUserID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NotNull DataSnapshot dataSnapshot)
            {
                if(dataSnapshot.exists())
                {
                    if(dataSnapshot.hasChild("Nombres"))
                    {
                        String fullname = dataSnapshot.child("Nombres").getValue().toString();
                        txtmenu.setText(fullname);
                    }
                    if(dataSnapshot.hasChild("profileimage"))
                    {
                        String image = dataSnapshot.child("profileimage").getValue().toString();
                        Picasso.with(MainActivity8.this).load(image).placeholder(R.drawable.profilefoto).into(fotoperfilmenu);
                    }
                    else
                    {
                        Toast.makeText(MainActivity8.this, "Profile name do not exists...", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onCancelled(@NotNull DatabaseError error) {

            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                UserMenuSelector(item);
                return false;
            }
        });

        txt29=findViewById(R.id.txt29);

        txtv1=findViewById(R.id.txtv1);
        txtv2= findViewById(R.id.txtv2);
        txtv3=findViewById(R.id.txtv3);
        txtmulti1=findViewById(R.id.txtmulti1);

        button1=findViewById(R.id.button1);



        sender = new GmailSender(user, password);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View v) {
                Nombre = txtv1.getText().toString();
                Correo = txtv2.getText().toString();
                Asunto = txtv3.getText().toString();
                Texto = txtmulti1.getText().toString();
                new MyAsyncClass().execute();

            }
        });
    }

    @SuppressLint("StaticFieldLeak")
    class MyAsyncClass extends AsyncTask<Void, Void, Void> {

        ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
            pDialog = new ProgressDialog(MainActivity8.this);
            pDialog.setMessage("Por favor espere...");
            pDialog.show();

        }

        @Override

        protected Void doInBackground(Void... mApi) {
            try {

                // Add subject, Body, your mail Id, and receiver mail Id.
                sender.sendMail(Texto, Asunto, user, email);
                Log.d("send", "done");
            }
            catch (Exception ex) {
                Log.d("exceptionsending", ex.toString());
            }
            return null;
        }

        @Override

        protected void onPostExecute(Void result) {

            super.onPostExecute(result);
            pDialog.cancel();

            Toast.makeText(MainActivity8.this, "Correo enviado", Toast.LENGTH_SHORT).show();

        }
    }
    private void UserMenuSelector(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.perfil:
                enviaraperfil();
                Toast.makeText(this, "Perfil", Toast.LENGTH_SHORT).show();
                break;

            case R.id.mensajes:
                Toast.makeText(this, "Mensajes", Toast.LENGTH_SHORT).show();
                break;

            case R.id.sugerencias:
                Intent intent2 = new Intent(this, MainActivity8.class);
                startActivity(intent2);
                Toast.makeText(this, "Sugerencias", Toast.LENGTH_SHORT).show();
                break;

            case R.id.cerrarsesion:
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(this, IniciarSesion.class);
                startActivity(intent);
                break;

            case R.id.foro:
                Intent forointent = new Intent (this, ForoMain.class);
                startActivity(forointent);
                Toast.makeText(this, "Foro", Toast.LENGTH_SHORT).show();
                break;

            case R.id.inicio:
                Toast.makeText(this, "Inicio", Toast.LENGTH_SHORT).show();
                Intent intent3 = new Intent(this, PrincipalActivity.class);
                startActivity(intent3);
                break;
        }


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        if (actionBarDrawerToggle.onOptionsItemSelected(item) ) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private void enviaraperfil()
    {
        Intent perfilintent = new Intent (this, Perfil.class);
        startActivity(perfilintent);

    }
}
