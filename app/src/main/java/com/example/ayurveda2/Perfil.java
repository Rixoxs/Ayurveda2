package com.example.ayurveda2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
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
import com.google.firebase.storage.StreamDownloadTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class Perfil extends AppCompatActivity {

    private EditText perfil_nombre, perfil_apellidos, perfil_nacimiento, perfil_email, perfil_pais;
    private TextView txtmenu;
    final static int Gallery_Pick = 1;
    private CircleImageView perfil_foto;
    private Button boton_actualizar;
    private DatabaseReference profileUserInfo;
    private StorageReference UserProfileImageRef;
    private FirebaseAuth mAuth;
    private ProgressDialog loadingBar;
    private String currentUserId;

    private CircleImageView fotoperfilmenu;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private DatabaseReference UsersRef;
    private FirebaseUser firebaseUser;
    private GoogleSignInClient googleSignInClient;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.perfil);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Ayurveda");
        drawer = findViewById(R.id.drawer);

        mAuth = FirebaseAuth.getInstance();
        currentUserId = mAuth.getCurrentUser().getUid();
        firebaseUser = mAuth.getCurrentUser();
        UsersRef = FirebaseDatabase.getInstance().getReference().child("Users");
        UserProfileImageRef = FirebaseStorage.getInstance().getReference().child("profileimage");

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.nav_view);
        View naView = navigationView.getHeaderView(0);
        fotoperfilmenu = naView.findViewById(R.id.fotoperfilmenu);
        txtmenu = naView.findViewById(R.id.txtmenu);

        googleSignInClient = GoogleSignIn.getClient(Perfil.this, GoogleSignInOptions.DEFAULT_SIGN_IN);

        txtmenu.setText(firebaseUser.getDisplayName());
        Glide.with(Perfil.this).load(firebaseUser.getPhotoUrl()).into(fotoperfilmenu);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                UserMenuSelector(item);
                return false;
            }
        });

        mAuth = FirebaseAuth.getInstance();
        currentUserId = mAuth.getCurrentUser().getUid();
        profileUserInfo = FirebaseDatabase.getInstance().getReference().child("Users").child(currentUserId);
        UserProfileImageRef = FirebaseStorage.getInstance().getReference().child("profileimage");

        loadingBar = new ProgressDialog(this);


        perfil_foto = findViewById(R.id.perfil_foto);
        perfil_nombre = findViewById(R.id.perfil_nombre);
        perfil_apellidos = findViewById(R.id.perfil_apellidos);
        perfil_nacimiento = findViewById(R.id.perfil_nacimiento);
        perfil_email = findViewById(R.id.perfil_email);
        perfil_pais = findViewById(R.id.perfil_pais);


        perfil_nombre.setText(firebaseUser.getDisplayName());
        perfil_email.setText(firebaseUser.getEmail());


        boton_actualizar = findViewById(R.id.boton_actualizar);

        UsersRef.child(currentUserId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NotNull DataSnapshot dataSnapshot)
            {
                if(dataSnapshot.exists())
                {
                    if(dataSnapshot.hasChild("Fecha de nacimiento"))
                    {
                        String nacimiento = dataSnapshot.child("Fecha de nacimiento").getValue().toString();
                        perfil_nacimiento.setText(nacimiento);
                    }
                    if(dataSnapshot.hasChild("Pais"))
                    {
                        String pais = dataSnapshot.child("Pais").getValue().toString();
                        perfil_pais.setText(pais);
                    }
                    if(dataSnapshot.hasChild("profileimage"))
                    {
                        String image = dataSnapshot.child("profileimage").getValue().toString();
                        Picasso.with(Perfil.this).load(image).placeholder(R.drawable.profilefoto).into(perfil_foto);
                    }
                    if(dataSnapshot.hasChild("profileimage"))
                    {
                        String image2 = dataSnapshot.child("profileimage").getValue().toString();
                        Picasso.with(Perfil.this).load(image2).placeholder(R.drawable.profilefoto).into(fotoperfilmenu);
                }
                    else
                    {
                        Toast.makeText(Perfil.this, "Profile name do not exists...", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onCancelled(@NotNull DatabaseError error) {

            }
        });


        perfil_foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent();
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, Gallery_Pick);
            }
        });
    }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
            super.onActivityResult(requestCode, resultCode, data);

            {
                super.onActivityResult(requestCode, resultCode, data);
                // some conditions for the picture
                if (requestCode == Gallery_Pick && resultCode == RESULT_OK && data != null) {
                    Uri ImageUri = data.getData();
                    // crop the image
                    CropImage.activity(ImageUri)
                            .setGuidelines(CropImageView.Guidelines.ON)
                            .setAspectRatio(1, 1)
                            .start(this);
                }
                // Get the cropped image
                if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {       // store the cropped image into result
                    CropImage.ActivityResult result = CropImage.getActivityResult(data);

                    if (resultCode == RESULT_OK) {
                        loadingBar.setTitle("Profile Image");
                        loadingBar.setMessage("Please wait, while we updating your profile image...");
                        loadingBar.show();
                        loadingBar.setCanceledOnTouchOutside(true);

                        Uri resultUri = result.getUri();

                        final StorageReference filePath = UserProfileImageRef.child(currentUserId + ".jpg");

                        filePath.putFile(resultUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                filePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        final String downloadUrl = uri.toString();
                                        profileUserInfo.child("profileimage").setValue(downloadUrl);
                                    }

                                });

                            }

                        });
                    } else {
                        Toast.makeText(this, "Error Occured: Image can not be cropped. Try Again.", Toast.LENGTH_SHORT).show();
                        loadingBar.dismiss();
                    }
                }
            }
        }



    private void enviarusuariohome() {
        Intent mainintent = new Intent(this, PrincipalActivity.class);
        mainintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(mainintent);
        finish();

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
    private void enviaraperfil() {
        Intent perfilintent = new Intent(this, Perfil.class);
        startActivity(perfilintent);
    }
    private void SendUserToLoginActivity()
    {
        Intent loginIntent = new Intent(Perfil.this, IniciarSesion.class);
        loginIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(loginIntent);
        finish();
    }
}