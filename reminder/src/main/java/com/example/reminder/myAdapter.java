package com.example.reminder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class myAdapter extends RecyclerView.Adapter<myAdapter.myviewholder> {
    private ArrayList<Model> dataholder;
    private Context mContext; // Add a variable to store the context

    public myAdapter(ArrayList<Model> dataholder, Context context) {
        this.dataholder = dataholder;
        this.mContext = context;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_reminder_file, parent, false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
        holder.mTitle.setText(dataholder.get(position).getTitle());
        holder.mDate.setText(dataholder.get(position).getDate());
        holder.mTime.setText(dataholder.get(position).getTime());

        Model currentModel = dataholder.get(position);
        holder.imageReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Delete the reminder from the database
                int reminderId = currentModel.getId();
                int deleteResult = deleteReminderFromDatabase(reminderId);

                if (deleteResult > 0) {
                    // Reminder deleted successfully
                    Toast.makeText(mContext, "Reminder deleted", Toast.LENGTH_SHORT).show();
                    dataholder.remove(holder.getAdapterPosition());
                    notifyItemRemoved(holder.getAdapterPosition());
                } else {
                    // Failed to delete the reminder
                    Toast.makeText(mContext, "Failed to delete the reminder", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private int deleteReminderFromDatabase(int reminderId) {
        dbManager db = new dbManager(mContext);
        return db.deleteReminder(reminderId);
    }

    @Override
    public int getItemCount() {
        return dataholder.size();
    }

    class myviewholder extends RecyclerView.ViewHolder {

        TextView mTitle, mDate, mTime;
        ImageView imageReminder;

        public myviewholder(@NonNull View itemView) {
            super(itemView);

            mTitle = itemView.findViewById(R.id.txtTitle);
            mDate = itemView.findViewById(R.id.txtDate);
            mTime = itemView.findViewById(R.id.txtTime);
            imageReminder = itemView.findViewById(R.id.imageReminder);
        }
    }
}
