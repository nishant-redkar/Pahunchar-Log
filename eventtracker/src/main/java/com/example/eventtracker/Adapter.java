package com.example.eventtracker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    LayoutInflater inflater;
    List<EventModel> eventModels;
    private OnLongClickListener longClickListener;

    Adapter(Context context, List<EventModel> eventModels, OnLongClickListener longClickListener) {
        this.inflater = LayoutInflater.from(context);
        this.eventModels = eventModels;
        this.longClickListener = longClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.activity_event_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        EventModel eventModel = eventModels.get(position);
        String title = eventModel.getEventTitle();
        String notes = eventModel.getEventDetails();
        String date = eventModel.getEventDate();
        boolean pinned = eventModel.isPinned(); // Get the pinned state

        holder.textview_title.setText(title);
        holder.textview_date.setText(date);

        if (pinned) {
            holder.imageView_pin.setImageResource(R.drawable.ic_pin);
        } else {
            holder.imageView_pin.setImageResource(0);
        }
        holder.itemView.setTag(eventModel);

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (longClickListener != null) {
                    longClickListener.onLongClick(eventModel);
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return eventModels.size();
    }

    public void setNotes(List<EventModel> notes) {
        this.eventModels = notes;
    }

    public void setOnLongClickListener(OnLongClickListener longClickListener) {
        this.longClickListener = longClickListener;
    }

    public interface OnLongClickListener {
        void onLongClick(EventModel eventModel);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textview_title, textview_date, textview_time, textview_notes;
        ImageView imageView_pin;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textview_title = itemView.findViewById(R.id.textview_title);
            textview_date = itemView.findViewById(R.id.textview_date);
            imageView_pin = itemView.findViewById(R.id.imageview_pin);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int adapterPosition = getAdapterPosition();
                    if (adapterPosition != RecyclerView.NO_POSITION) {
                        Intent intent = new Intent(view.getContext(), event_edit.class);
                        intent.putExtra("ID", eventModels.get(adapterPosition).getId());
                        ((Activity) view.getContext()).startActivityForResult(intent, 102);
                    }
                }

            });
        }
    }
}

