package com.example.newnotesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class note_edit extends AppCompatActivity {
    TextView updateText_title, updateText_notes;
    int id;
    private static final int EDIT_NOTE_REQUEST = 102;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_edit);

        updateText_title = findViewById(R.id.updateText_title);
        updateText_notes = findViewById(R.id.updateText_notes);

        NotesDatabase db = new NotesDatabase(this);
        Intent intent = getIntent();
        id = intent.getIntExtra("ID", 0);
        NoteModel noteModel = db.getNotes(id);

        if (noteModel != null) {
            updateText_title.setText(noteModel.getNoteTitle());
            updateText_notes.setText(noteModel.getNoteDetails());
        } else {
            Toast.makeText(getApplicationContext(), "Note not found", Toast.LENGTH_SHORT).show();
        }

        ImageView image_save = findViewById(R.id.image_save);
        image_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(updateText_title.getText()) || TextUtils.isEmpty(updateText_notes.getText())) {
                    Toast.makeText(getApplicationContext(), "Please fill everything", Toast.LENGTH_SHORT).show();
                    return;
                }

                String updatedTitle = updateText_title.getText().toString();
                String updatedDetails = updateText_notes.getText().toString();

                // Create a new NoteModel object with the updated values
                NoteModel updatedNote = new NoteModel(id, updatedTitle, updatedDetails, noteModel.getNoteDate(), noteModel.getNoteTime());

                NotesDatabase db = new NotesDatabase(note_edit.this);
                db.updateNote(updatedNote);

                Toast.makeText(getApplicationContext(), "Note updated", Toast.LENGTH_SHORT).show();

                // Return the updated note to the MainActivity
                Intent resultIntent = new Intent();
                resultIntent.putExtra("UpdatedNote", updatedNote);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}
