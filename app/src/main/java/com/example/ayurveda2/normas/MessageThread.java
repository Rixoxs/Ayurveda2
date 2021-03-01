package com.example.ayurveda2.normas;

import java.io.Serializable;

public class MessageThread implements Serializable {
    public String title;
    public String user_id;
    public String user_name;
    public String thread_id;
    public String time_thread;
    private String numComments;



    public MessageThread(String title, String user_id,String user_name, String time_thread) {
        this.title = title;
        this.user_id = user_id;
        this.user_name=user_name;
        this.time_thread=time_thread;
        this.numComments = numComments;
    }

    public MessageThread() {
    }

    @Override
    public String toString() {
        return "MessageThread{" +
                "title='" + title + '\'' +
                ", user_id='" + user_id + '\'' +
                ", user_name='" + user_name + '\'' +
                ", time_thread='" + time_thread + '\'' +
                ", numComments='" + numComments + '\'' +
                '}';
    }
}