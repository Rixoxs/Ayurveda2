package com.example.ayurveda2.normas;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.request.target.ViewTarget;

import de.hdodenhof.circleimageview.CircleImageView;

public class Message {

    public String message_id, message, user_id, user_name, created_time, created_date, profileimage;


    public Message() {
    }

    public Message(String message, String user_id, String user_name, String created_time) {
        this.message = message;
        this.user_id = user_id;
        this.user_name = user_name;
        this.created_time = created_time;
        this.profileimage = profileimage;
    }

    public String getMessage_id() {
        return message_id;
    }

    public void setMessage_id(String message_id) {
        this.message_id = message_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getCreated_time() {
        return created_time;
    }

    public void setCreated_time(String created_time) {
        this.created_time = created_time;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public String getProfileimage() {
        return profileimage;
    }

    public void setProfileimage(String profileimage) {
        this.profileimage = profileimage;
    }


    @Override
    public String toString() {
        return "Message{" +
                "message_id='" + message_id + '\'' +
                ", message='" + message + '\'' +
                ", user_id='" + user_id + '\'' +
                ", user_name='" + user_name + '\'' +
                ", created_time='" + created_time + '\'' +
                ", created_date='" + profileimage + '\'' +
                '}';
    }
}
