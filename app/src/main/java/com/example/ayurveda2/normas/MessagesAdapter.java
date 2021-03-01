package com.example.ayurveda2.normas;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ayurveda2.Perfil;
import com.example.ayurveda2.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
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
import org.ocpsoft.prettytime.PrettyTime;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class MessagesAdapter extends ArrayAdapter<Message> {

    private Context ctx;
    Message message;
    private final String TAG = "demoMessageAdapter";
    private ChatActivity dataUpdateAfterMessageDelete;
    private FirebaseAuth mAuth;
    private ArrayList<Message> messageObjects;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.US);
    private PrettyTime p = new PrettyTime();
    private Date convertedDate;
    private Time convertedTime;
    private DatabaseReference UsersRef;
    private StorageReference UserProfileImageRef;
    private FirebaseUser firebaseUser;
    private GoogleSignInClient googleSignInClient;
    private String currentUserId;


    MessagesAdapter(@NonNull Context context, int resource, @NonNull List<Message> objects, ChatActivity chatActivity) {
        super(context, resource, objects);
        this.ctx = context;
        this.messageObjects = (ArrayList<Message>) objects;
        this.dataUpdateAfterMessageDelete = chatActivity;
        mAuth = FirebaseAuth.getInstance();
        currentUserId = mAuth.getCurrentUser().getUid();
        firebaseUser = mAuth.getCurrentUser();
        UsersRef = FirebaseDatabase.getInstance().getReference().child("Users");
        UserProfileImageRef = FirebaseStorage.getInstance().getReference().child("profileimage");


    }



    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        message = getItem(position);
        MessagesAdapter.ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.messages_listview, parent, false);
            viewHolder = new MessagesAdapter.ViewHolder();
            viewHolder.messageTV = convertView.findViewById(R.id.messageTV);
            viewHolder.messageSenderTV = convertView.findViewById(R.id.messageSenderTV);
            viewHolder.messageTimeTV = convertView.findViewById(R.id.messageTimeTV);
            viewHolder.fotoforos = convertView.findViewById(R.id.fotoforos);
            viewHolder.deleteMessageButton = convertView.findViewById(R.id.deleteMessageButton);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (MessagesAdapter.ViewHolder) convertView.getTag();
        }
        if (!message.user_id.equals(mAuth.getCurrentUser().getUid())) {
            viewHolder.deleteMessageButton.setVisibility(View.INVISIBLE);
        } else {
            viewHolder.deleteMessageButton.setVisibility(View.VISIBLE);
        }
        viewHolder.fotoforos.equals(message.profileimage);
        viewHolder.messageSenderTV.setText(message.user_name);
        viewHolder.messageTV.setText(message.message);
        try {
            convertedDate = dateFormat.parse(message.created_time);
            p.setReference(new Date());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        viewHolder.messageTimeTV.setText(p.format(convertedDate));
        

        viewHolder.deleteMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataUpdateAfterMessageDelete.deleteMessage(messageObjects.get(position).message_id);
            }
        });

        return convertView;
        
        

    }




    private static class ViewHolder {
        TextView messageTV, messageSenderTV, messageTimeTV;
        ImageButton deleteMessageButton;
        CircleImageView fotoforos;

    }

    public interface DataUpdateAfterMessageDelete {
        void deleteMessage(String message_id);
    }


}