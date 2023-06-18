package com.example.eventtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class event_edit extends AppCompatActivity {
    TextView updateText_title, updateText_notes, updateText_date;
    int id;
    private static final int EDIT_NOTE_REQUEST = 102;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_edit);

        updateText_title = findViewById(R.id.updateText_title);
        updateText_notes = findViewById(R.id.updateText_notes);
        updateText_date = findViewById(R.id.updateText_date);

        EventDatabase db = new EventDatabase(this);
        Intent intent = getIntent();
        id = intent.getIntExtra("ID", 0);
        EventModel noteModel = db.getNotes(id);

        if (noteModel != null) {
            updateText_title.setText(noteModel.getEventTitle());
            updateText_notes.setText(noteModel.getEventDetails());
            updateText_date.setText(noteModel.getEventDate());
        } else {
            Toast.makeText(getApplicationContext(), "Note not found", Toast.LENGTH_SHORT).show();
        }

        ImageView image_save = findViewById(R.id.image_save);
        image_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(updateText_title.getText()) || TextUtils.isEmpty(updateText_notes.getText()) || TextUtils.isEmpty(updateText_date.getText())) {
                    Toast.makeText(getApplicationContext(), "Please fill everything", Toast.LENGTH_SHORT).show();
                    return;
                }

                String updatedTitle = updateText_title.getText().toString();
                String updatedDetails = updateText_notes.getText().toString();
                String updatedDate = updateText_date.getText().toString();


                // Create a new NoteModel object with the updated values
                EventModel updatedNote = new EventModel(id, updatedTitle, updatedDetails, updatedDate);

                EventDatabase db = new EventDatabase(event_edit.this);
                db.updateNote(updatedNote);

                Toast.makeText(getApplicationContext(), "Event updated", Toast.LENGTH_SHORT).show();

                // Return the updated note to the MainActivity
                Intent resultIntent = new Intent();
                resultIntent.putExtra("UpdatedNote", updatedNote);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}
