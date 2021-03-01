package com.example.ayurveda2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class SetupActivity extends AppCompatActivity {

    private EditText setupfechanacimiento, setupprovincia, setuppais, setupsexo;
    private Button botongrabar;
    private CircleImageView setupfoto;
    private ProgressDialog loadingBar;

    private FirebaseAuth mAuth;
    private DatabaseReference UsersRef;
    private StorageReference UserProfileImageRef;

    String currentUserID;
    final static int Gallery_Pick = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        mAuth = FirebaseAuth.getInstance();
        currentUserID = mAuth.getCurrentUser().getUid();
        UsersRef = FirebaseDatabase.getInstance().getReference().child("Users").child(currentUserID);
        UserProfileImageRef = FirebaseStorage.getInstance().getReference().child("Profile Images");


        setupfechanacimiento = (EditText) findViewById(R.id.setupfechanacimiento);
        setupprovincia = (EditText) findViewById(R.id.setupprovincia);
        setuppais = (EditText) findViewById(R.id.setuppais);
        setupsexo = (EditText) findViewById(R.id.setupsexo);
        botongrabar = (Button) findViewById(R.id.botongrabar);
        setupfoto = (CircleImageView) findViewById(R.id.setupfoto);
        loadingBar = new ProgressDialog(this);

        botongrabar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                SaveAccountSetupInformation();
            }
        });


        setupfoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent galleryIntent = new Intent();
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, Gallery_Pick);
            }
        });


        UsersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if(dataSnapshot.exists())
                {
                    if (dataSnapshot.hasChild("profileimage"))
                    {
                        String image = dataSnapshot.child("profileimage").getValue().toString();
                        Picasso.with(SetupActivity.this).load(image).placeholder(R.drawable.profilefoto).into(setupfoto);
                    }
                    else
                    {
                        Toast.makeText(SetupActivity.this, "Please select profile image first.", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

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
                    Toast.makeText(this, "Su foto se ha actualizado correctamente", Toast.LENGTH_SHORT).show();

                    Uri resultUri = result.getUri();

                    final StorageReference filePath = UserProfileImageRef.child(currentUserID+ ".jpg");
                    filePath.putFile(resultUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            filePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    final String downloadUrl = uri.toString();
                                    UsersRef.child("profileimage").setValue(downloadUrl);
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


    private void SaveAccountSetupInformation()
    {
        String nacimiento = setupfechanacimiento.getText().toString();
        String provincia = setupprovincia.getText().toString();
        String pais = setuppais.getText().toString();
        String sexo = setupsexo.getText().toString();

        if(TextUtils.isEmpty(nacimiento))
        {
            Toast.makeText(this, "Por favor escribe tu fecha de nacimiento...", Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(provincia))
        {
            Toast.makeText(this, "Por favor escribe tu provincia...", Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(pais))
        {
            Toast.makeText(this, "Por favor escribe tu pais...", Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(sexo))
        {
            Toast.makeText(this, "Por favor introduce tu genero...", Toast.LENGTH_SHORT).show();
        }
        else
        {
            loadingBar.setTitle("Guardando información");
            loadingBar.setMessage("Por favor espere, estamos creando su información...");
            loadingBar.show();
            loadingBar.setCanceledOnTouchOutside(true);

            HashMap userMap = new HashMap();
            userMap.put("Fecha de nacimiento", nacimiento);
            userMap.put("Provincia", provincia);
            userMap.put("Pais", pais);
            userMap.put("Sexo", sexo);
            UsersRef.updateChildren(userMap).addOnCompleteListener(new OnCompleteListener() {
                @Override
                public void onComplete(@NonNull Task task)
                {
                    if(task.isSuccessful())
                    {
                        SendUserToMainActivity();
                        Toast.makeText(SetupActivity.this, "su cuenta ha sido creada con exito.", Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        String message =  task.getException().getMessage();
                        Toast.makeText(SetupActivity.this, "Error Occured: " + message, Toast.LENGTH_SHORT).show();
                    }
                    loadingBar.dismiss();
                }
            });
        }
    }



    private void SendUserToMainActivity()
    {
        Intent mainIntent = new Intent(SetupActivity.this, PrincipalActivity.class);
        mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(mainIntent);
        finish();
    }
}