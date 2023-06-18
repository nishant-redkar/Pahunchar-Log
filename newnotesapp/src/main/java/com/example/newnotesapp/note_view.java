package com.example.newnotesapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class note_view extends AppCompatActivity {
    TextView editText_title, editText_notes;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_add);

        editText_title = findViewById(R.id.editText_title);
        editText_notes = findViewById(R.id.editText_notes);

        NotesDatabase db = new NotesDatabase(this);
        Intent intent = getIntent();
        id = intent.getIntExtra("ID", 0);
        NoteModel noteModel = db.getNotes(id);

        if (noteModel != null) {
            editText_title.setText(noteModel.getNoteTitle());
            editText_notes.setText(noteModel.getNoteDetails());
        } else {
            Toast.makeText(getApplicationContext(), "Note not found", Toast.LENGTH_SHORT).show();
        }
    }





    }