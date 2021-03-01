package com.example.ayurveda2.normas;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.ayurveda2.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.ocpsoft.prettytime.PrettyTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ThreadsAdapter extends ArrayAdapter<MessageThread> {

    private Context ctx;
    private MessageThread messageThread;
    private DataUpdateAfterDelete dataUpdateAfterDelete;
    private FirebaseAuth mAuth;
    private final String TAG = "demoThreadAdapter";
    private ArrayList<MessageThread> messageThreadObjects;
    private PrettyTime p = new PrettyTime();
    private Date convertedDate;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.US);


    ThreadsAdapter(@NonNull Context context, int resource, @NonNull List<MessageThread> objects, ThreadsActivity threadsActivity) {
        super(context, resource, objects);
        this.ctx = context;
        this.dataUpdateAfterDelete=threadsActivity;
        this.messageThreadObjects = (ArrayList<MessageThread>) objects;
        mAuth = FirebaseAuth.getInstance();
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        messageThread = getItem(position);
        ThreadsAdapter.ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.threads_listview, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.threadTitleTV = convertView.findViewById(R.id.threadTitleTV);
            viewHolder.messagethreadsTimeTV = convertView.findViewById(R.id.messagethreadsTimeTV);
            viewHolder.numCommentsTV = convertView.findViewById(R.id.numCommentsTV);
            viewHolder.deleteThreadButton = convertView.findViewById(R.id.deleteThreadButton);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

            if (!messageThread.user_id.equals(mAuth.getCurrentUser().getUid())) {
                viewHolder.deleteThreadButton.setVisibility(View.INVISIBLE);
            } else {
                viewHolder.deleteThreadButton.setVisibility(View.VISIBLE);
            }
        viewHolder.threadTitleTV.setTextColor(Color.parseColor("#000000"));
        viewHolder.threadTitleTV.setText(messageThread.title);

        try {
            convertedDate = dateFormat.parse(messageThread.time_thread);
            p.setReference(new Date());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        viewHolder.messagethreadsTimeTV.setText(p.format(convertedDate));

        viewHolder.deleteThreadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataUpdateAfterDelete.deleteThread(messageThreadObjects.get(position).thread_id);
            }
        });
        return convertView;
    }

    private static class ViewHolder {
        TextView threadTitleTV, messagethreadsTimeTV, numCommentsTV;
        ImageButton deleteThreadButton;
    }

    public interface DataUpdateAfterDelete{
        void deleteThread(String thread_id);
    }

}
