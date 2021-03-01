package com.example.ayurveda2.normas;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.ViewTarget;
import com.example.ayurveda2.IniciarSesion;
import com.example.ayurveda2.Perfil;
import com.example.ayurveda2.PrincipalActivity;
import com.example.ayurveda2.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChatActivity  extends AppCompatActivity implements MessagesAdapter.DataUpdateAfterMessageDelete {

    private final String TAG = "demoChat";

    Context ctx;

    TextView threadNameTV, messageSenderTV;
    EditText newMessageET;
    ImageButton homeButton, sendButton;
    ListView messagesLV;
    CircleImageView fotoforos;

    MessageThread messageThread;
    MessagesAdapter messagesAdapter;
    List<Message> messagesList = new ArrayList<>();

    private FirebaseUser user;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private DatabaseReference UserRef;
    private StorageReference UserProfileImageRef;
    String currentUserID;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        setTitle("Chatroom");

        threadNameTV = findViewById(R.id.threadNameTV);
        newMessageET = findViewById(R.id.newMessageET);
        homeButton = findViewById(R.id.homeButton);
        sendButton = findViewById(R.id.sendButton);
        messagesLV = findViewById(R.id.messagesLV);
        fotoforos = findViewById(R.id.fotoforos);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        threadNameTV.setTextColor(Color.parseColor("#000000"));

        if (getIntent() != null && getIntent().getExtras() != null) {
            if (getIntent().getExtras().containsKey("messageThreadDetails")) {
                messageThread = (MessageThread) getIntent().getSerializableExtra("messageThreadDetails");
                threadNameTV.setText(messageThread.title);
                getMessages(messageThread.thread_id);
            }
        } else {
            Toast.makeText(this, "No data received", Toast.LENGTH_SHORT).show();
        }

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user != null) {
                    user = null;
                    mDatabase = null;
                    mAuth = null;
                    Intent intent = new Intent(ChatActivity.this, ThreadsActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    user = null;
                    mDatabase = null;
                    mAuth = null;
                    Toast.makeText(ChatActivity.this, "You need to login", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ChatActivity.this, IniciarSesion.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = newMessageET.getText().toString();
                String user_name = user.getDisplayName();
                ValueEventListener profile = UserRef.child(currentUserID).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NotNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            if (dataSnapshot.hasChild("profileimage")) {
                                String image = dataSnapshot.child("profileimage").getValue().toString();
                                Glide.with(ctx).load(image).placeholder(R.drawable.profilefoto).into(fotoforos);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NotNull DatabaseError error) {

                    }
                });
                if (message.isEmpty()) {
                    Toast.makeText(ChatActivity.this, "Enter Message", Toast.LENGTH_SHORT).show();
                } else {
                    addMessage(message, user_name, messageThread.thread_id);
                }
            }
        });

    }



    public void addMessage(String message, String user_name, String thread_id) {
        if (user != null) {
            mDatabase.child("Normas").child(thread_id).child("messages").push().setValue(new Message( message, user.getUid(), user_name, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.UK).format(new Date())));
            newMessageET.setText("");
        } else {
            Toast.makeText(this, "No user logged in", Toast.LENGTH_SHORT).show();
        }
    }

    public void getMessages(String thread_id) {
        mDatabase.child("Normas").child(thread_id).child("messages").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                messagesList.clear();
                for (DataSnapshot messageSnapshot : dataSnapshot.getChildren()) {
                    Message message = messageSnapshot.getValue(Message.class);
                    if (message != null) {
                        message.message_id=messageSnapshot.getKey();
                        Log.d(TAG, "onDataChange: " + message.toString());
                    }
                    messagesList.add(message);
                }
                messagesAdapter = new MessagesAdapter(ChatActivity.this, R.layout.threads_listview, messagesList, ChatActivity.this);
                messagesLV.setAdapter(messagesAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(ChatActivity.this, "ChatActivity: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void deleteMessage(String message_id) {
        mDatabase.child("Normas").child(messageThread.thread_id).child("messages").child(message_id).removeValue();
    }
}
