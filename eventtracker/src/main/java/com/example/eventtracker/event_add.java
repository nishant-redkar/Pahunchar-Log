package com.example.eventtracker;

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

public class event_add extends AppCompatActivity {

    EditText editText_title, editText_notes, editText_date;
    ImageView imageView_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_add);

//        getSupportActionBar().setTitle("Add New Note");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        editText_title = findViewById(R.id.editText_title);
        editText_notes = findViewById(R.id.editText_notes);
        editText_date = findViewById(R.id.editText_date);

        imageView_save = findViewById(R.id.imageView_save);

        imageView_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(editText_title.getText()) || TextUtils.isEmpty(editText_notes.getText()) || TextUtils.isEmpty(editText_date.getText())) {
                    Toast.makeText(getApplicationContext(), "Please fill everything", Toast.LENGTH_SHORT).show();
                    return;
                }

                String title = editText_title.getText().toString();
                String notes = editText_notes.getText().toString();
                String date = editText_date.getText().toString();

                EventModel eventModel = new EventModel(title, notes, date); // Assuming NoteModel constructor accepts title, details, and currentTime as parameters
                EventDatabase db = new EventDatabase(event_add.this); // Assuming NoteDatabase constructor accepts context as a parameter
                db.addNote(eventModel);

                Intent intent = new Intent(event_add.this, MainActivity.class);
                startActivity(intent);

                Toast.makeText(getApplicationContext(), "Event Added", Toast.LENGTH_SHORT).show();
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
