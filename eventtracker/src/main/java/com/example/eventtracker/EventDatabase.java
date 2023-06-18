package com.example.eventtracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class EventDatabase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "event.db";
    private static final String DATABASE_TABLE = "EventTable";

    private static final int DATABASE_VERSION = 2;

    private static final String COLUMN_DETAILS = "EventDetails";
    private static final String COLUMN_ID = "EventId";
    private static final String COLUMN_TITLE = "EventTitle";
    private static final String COLUMN_DATE = "EventDate";
    private static final String COLUMN_PINNED = "Pinned"; // Added pinned column

    public EventDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + DATABASE_TABLE + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITLE + " TEXT, " +
                COLUMN_DETAILS + " TEXT, " +
                COLUMN_DATE + " TEXT, " +
                COLUMN_PINNED + " INTEGER DEFAULT 0)"; // Added pinned column
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
            onCreate(db);
        }
    }

    public long addNote(EventModel noteModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TITLE, noteModel.getEventTitle());
        contentValues.put(COLUMN_DETAILS, noteModel.getEventDetails());
        contentValues.put(COLUMN_DATE, noteModel.getEventDate());
        contentValues.put(COLUMN_PINNED, noteModel.isPinned() ? 1 : 0); // Store pinned state as 1 or 0

        long ID = db.insert(DATABASE_TABLE, null, contentValues);
        Log.d("Inserted", String.valueOf(ID));

        return ID;
    }

    public List<EventModel> getNote() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<EventModel> allNote = new ArrayList<>();
        String queryStatement = "SELECT * FROM " + DATABASE_TABLE + " ORDER BY " + COLUMN_ID + " DESC";
        Cursor cursor = db.rawQuery(queryStatement, null);
        if (cursor.moveToFirst()) {
            do {
                EventModel noteModel = new EventModel();
                noteModel.setId(cursor.getInt(0));
                noteModel.setEventTitle(cursor.getString(1));
                noteModel.setEventDetails(cursor.getString(2));
                noteModel.setEventDate(cursor.getString(3));
                noteModel.setPinned(cursor.getInt(4) == 1); // Retrieve pinned state from the database
                allNote.add(noteModel);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return allNote;
    }


    public EventModel getNotes(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] query = new String[]{COLUMN_ID, COLUMN_TITLE, COLUMN_DETAILS, COLUMN_DATE, COLUMN_PINNED};
        Cursor cursor = db.query(DATABASE_TABLE, query, COLUMN_ID + "=?", new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        EventModel noteModel = new EventModel();
        noteModel.setId(cursor.getInt(0));
        noteModel.setEventTitle(cursor.getString(1));
        noteModel.setEventDetails(cursor.getString(2));
        noteModel.setEventDate(cursor.getString(3));
        noteModel.setPinned(cursor.getInt(4) == 1); // Retrieve pinned state from the database
        cursor.close();
        return noteModel;
    }

    public void updateNote(EventModel noteModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TITLE, noteModel.getEventTitle());
        contentValues.put(COLUMN_DETAILS, noteModel.getEventDetails());
        contentValues.put(COLUMN_DATE, noteModel.getEventDate());
        contentValues.put(COLUMN_PINNED, noteModel.isPinned() ? 1 : 0); // Store pinned state as 1 or 0

        db.update(DATABASE_TABLE, contentValues, COLUMN_ID + "=?", new String[]{String.valueOf(noteModel.getId())});
        db.close();
    }

    public void deleteNote(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DATABASE_TABLE, COLUMN_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
    }
}