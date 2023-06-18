package com.example.newnotesapp;

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
    List<NoteModel> noteModels;
    private OnLongClickListener longClickListener;

    Adapter(Context context, List<NoteModel> noteModels, OnLongClickListener longClickListener) {
        this.inflater = LayoutInflater.from(context);
        this.noteModels = noteModels;
        this.longClickListener = longClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.activity_note_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NoteModel noteModel = noteModels.get(position);
        String title = noteModel.getNoteTitle();
        String notes = noteModel.getNoteDetails();
        String date = noteModel.getNoteDate();
        String time = noteModel.getNoteTime();
        boolean pinned = noteModel.isPinned(); // Get the pinned state

        holder.textview_title.setText(title);
        holder.textview_notes.setText(notes);
        holder.textview_date.setText(date);
        holder.textview_time.setText(time);

        if (pinned) {
            holder.imageView_pin.setImageResource(R.drawable.ic_pin);
        } else {
            holder.imageView_pin.setImageResource(0);
        }
        holder.itemView.setTag(noteModel);

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (longClickListener != null) {
                    longClickListener.onLongClick(noteModel);
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return noteModels.size();
    }

    public void setNotes(List<NoteModel> notes) {
        this.noteModels = notes;
    }

    public void setOnLongClickListener(OnLongClickListener longClickListener) {
        this.longClickListener = longClickListener;
    }

    public interface OnLongClickListener {
        void onLongClick(NoteModel noteModel);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textview_title, textview_date, textview_time, textview_notes;
        ImageView imageView_pin;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textview_title = itemView.findViewById(R.id.textview_title);
            textview_notes = itemView.findViewById(R.id.textview_notes);
            textview_date = itemView.findViewById(R.id.textview_date);
            textview_time = itemView.findViewById(R.id.textview_time);
            imageView_pin = itemView.findViewById(R.id.imageview_pin);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int adapterPosition = getAdapterPosition();
                    if (adapterPosition != RecyclerView.NO_POSITION) {
                        Intent intent = new Intent(view.getContext(), note_edit.class);
                        intent.putExtra("ID", noteModels.get(adapterPosition).getId());
                        ((Activity) view.getContext()).startActivityForResult(intent, 102);
                    }
                }

            });
        }
    }
}
