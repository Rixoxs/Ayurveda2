package com.example.ayurveda2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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

public class PrincipalActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    TextView txt1, txt2, txt3, txt12, txtmenu;

    private CircleImageView fotoperfilmenu;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private FirebaseAuth mAuth;
    FirebaseUser firebaseUser;
    private DatabaseReference UsersRef;
    private StorageReference UserProfileImageRef;
    private GoogleSignInClient googleSignInClient;

    String currentUserID;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Ayurveda");
        drawer = findViewById(R.id.drawer);

        mAuth = FirebaseAuth.getInstance();
        currentUserID = mAuth.getCurrentUser().getUid();
        firebaseUser = mAuth.getCurrentUser();
        UsersRef = FirebaseDatabase.getInstance().getReference().child("Users");
        UserProfileImageRef = FirebaseStorage.getInstance().getReference().child("profileimage");

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);


        navigationView = findViewById(R.id.nav_view);
        View naView = navigationView.getHeaderView(0);
        fotoperfilmenu = naView.findViewById(R.id.fotoperfilmenu);
        txtmenu = naView.findViewById(R.id.txtmenu);

        googleSignInClient = GoogleSignIn.getClient(PrincipalActivity.this, GoogleSignInOptions.DEFAULT_SIGN_IN);

        txtmenu.setText(firebaseUser.getDisplayName());
        Glide.with(PrincipalActivity.this).load(firebaseUser.getPhotoUrl()).into(fotoperfilmenu);

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
                        Picasso.with(PrincipalActivity.this).load(image).placeholder(R.drawable.profilefoto).into(fotoperfilmenu);
                    }
                    else
                    {
                        Toast.makeText(PrincipalActivity.this, "Profile name do not exists...", Toast.LENGTH_SHORT).show();
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


        txt1 = (TextView) findViewById(R.id.txt1);
        txt2 = (TextView) findViewById(R.id.txt2);
        txt3 = (TextView) findViewById(R.id.txt3);
        txt12 = (TextView) findViewById(R.id.txt12);


        txt1.setOnClickListener((v) -> {
            Intent intent = new Intent(this, MainActivity1.class);
            startActivity(intent);
            finish();
        });
        txt2.setOnClickListener((v) -> {
            Intent intent = new Intent(this, MainActivity2.class);
            startActivity(intent);
            finish();
        });
        txt3.setOnClickListener((v) -> {
            Intent intent = new Intent(this, MainActivity3.class);
            startActivity(intent);
            finish();
        });
        txt12.setOnClickListener((v) -> {
            Intent intent = new Intent(this, MainActivity14.class);
            startActivity(intent);
            finish();
        });


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
                    googleSignInClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                mAuth.signOut();
                                Toast.makeText(getApplicationContext(), "Has cerrado sesion", Toast.LENGTH_SHORT).show();
                                SendUserToLoginActivity();
                            }
                        }
                    });

            case R.id.foro:
                Intent forointent = new Intent (this, ForoMain.class);
                startActivity(forointent);
                Toast.makeText(this, "Foro", Toast.LENGTH_SHORT).show();
                break;

            case R.id.inicio:
                Intent mainintent = new Intent (this, PrincipalActivity.class);
                startActivity(mainintent);
                Toast.makeText(this, "Inicio", Toast.LENGTH_SHORT).show();
                break;
        }


    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        if (actionBarDrawerToggle.onOptionsItemSelected(item) ) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser == null) {
            SendUserToLoginActivity();
        } else {
            CheckUserExistence();
        }
    }

    private void CheckUserExistence()
    {
        final String current_user_id = mAuth.getCurrentUser().getUid();

        UsersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if(!dataSnapshot.hasChild(current_user_id))
                {
                    SendUserToSetupActivity();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void enviaraperfil()
    {
        Intent perfilintent = new Intent (this, Perfil.class);
        startActivity(perfilintent);

    }

    private void SendUserToSetupActivity()
    {
        Intent setupIntent = new Intent(PrincipalActivity.this, SetupActivity.class);
        setupIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(setupIntent);
        finish();
    }

    private void SendUserToLoginActivity()
    {
        Intent loginIntent = new Intent(PrincipalActivity.this, IniciarSesion.class);
        loginIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(loginIntent);
        finish();
    }
}