package com.example.newnotesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Calendar;

public class note_add extends AppCompatActivity {

    EditText editText_title, editText_notes;
    ImageView imageView_save;
    String todayDate, currentTime;
    Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_add);

//        getSupportActionBar().setTitle("Add New Note");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        editText_title = findViewById(R.id.editText_title);
        editText_notes = findViewById(R.id.editText_notes);
        imageView_save = findViewById(R.id.imageView_save);
        calendar = Calendar.getInstance();
        todayDate = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)+"/"+(calendar.get(Calendar.MONTH) + 1) + "/"+calendar.get(Calendar.YEAR));
        currentTime = pad(calendar.get(Calendar.HOUR_OF_DAY)) + ":" + pad(calendar.get(Calendar.MINUTE));
        Log.d("Calendar", "Date and Time: " + todayDate + " and " + currentTime);

        imageView_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(editText_title.getText()) || TextUtils.isEmpty(editText_notes.getText())) {
                    Toast.makeText(getApplicationContext(), "Please enter a title and details for the note", Toast.LENGTH_SHORT).show();
                    return;
                }

                NoteModel noteModel = new NoteModel(editText_title.getText().toString(), editText_notes.getText().toString(), todayDate, currentTime); // Assuming NoteModel constructor accepts title, details, and currentTime as parameters
                NotesDatabase db = new NotesDatabase(note_add.this); // Assuming NoteDatabase constructor accepts context as a parameter
                db.addNote(noteModel);

                Intent intent = new Intent(note_add.this, MainActivity.class);
                startActivity(intent);

                Toast.makeText(getApplicationContext(), "Note Saved", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public String pad(int i) {
        if (i < 10)
            return "0" + i;
        return String.valueOf(i);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
