package com.example.newnotesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Adapter.OnLongClickListener {

    RecyclerView recyclerView;
    Adapter adapter;
    List<NoteModel> notemodellist;
    List<NoteModel> pinnedNotesList;
    List<NoteModel> unpinnedNotesList;
    FloatingActionButton fab_add;
    NoteModel selectedNote;
    androidx.appcompat.widget.SearchView searchView;

    private static final int REQUEST_CODE_UPDATE_NOTE = 102;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity5_main);

        recyclerView = findViewById(R.id.recycler_home);
        fab_add = findViewById(R.id.fab_add);
        searchView = findViewById(R.id.searchView_home);

        NotesDatabase notesDatabase = new NotesDatabase(this);
        notemodellist = notesDatabase.getNote();

        pinnedNotesList = new ArrayList<>();
        unpinnedNotesList = new ArrayList<>();

        for (NoteModel note : notemodellist) {
            if (note.isPinned()) {
                pinnedNotesList.add(note);
            } else {
                unpinnedNotesList.add(note);
            }
        }

        List<NoteModel> combinedNotesList = new ArrayList<>();
        combinedNotesList.addAll(pinnedNotesList);
        combinedNotesList.addAll(unpinnedNotesList);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL));
        adapter = new Adapter(MainActivity.this, combinedNotesList, this);
        recyclerView.setAdapter(adapter);

        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, note_add.class);
                startActivityForResult(intent, 101);
            }
        });

        searchView.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                performSearch(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                performSearch(newText);
                return true;
            }
        });
    }

    private void performSearch(String query) {
        List<NoteModel> filteredList = filterNotes(notemodellist, query);
        adapter.setNotes(filteredList);
        adapter.notifyDataSetChanged();
    }

    private List<NoteModel> filterNotes(List<NoteModel> notes, String query) {
        List<NoteModel> filteredList = new ArrayList<>();

        for (NoteModel note : notes) {
            if (note.getNoteTitle().toLowerCase().contains(query.toLowerCase()) ||
                    note.getNoteDetails().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(note);
            }
        }

        return filteredList;
    }

    @Override
    public void onLongClick(NoteModel noteModel) {
        selectedNote = noteModel;
        showPopup(selectedNote);
    }

    private void showPopup(NoteModel noteModel) {
        View view = recyclerView.findViewWithTag(noteModel);
        if (view != null) {
            PopupMenu popupMenu = new PopupMenu(this, view);
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    if (item.getItemId() == android.R.id.home) {
                        finish();
                        return true;
                    }

                    if (item.getItemId() == R.id.delete) {
                        NotesDatabase db = new NotesDatabase(MainActivity.this);
                        int id = selectedNote.getId();
                        db.deleteNote(id);
                        Toast.makeText(MainActivity.this, "Note Deleted", Toast.LENGTH_SHORT).show();
                        refreshActivity();
                        return true;
                    }

                    if (item.getItemId() == R.id.pin) {
                        selectedNote.setPinned(!selectedNote.isPinned());

                        NotesDatabase db = new NotesDatabase(MainActivity.this);
                        db.updateNote(selectedNote);

                        if (selectedNote.isPinned()) {
                            Toast.makeText(MainActivity.this, "Note Pinned", Toast.LENGTH_SHORT).show();
                            pinnedNotesList.add(selectedNote);
                            unpinnedNotesList.remove(selectedNote);
                        } else {
                            Toast.makeText(MainActivity.this, "Note Unpinned", Toast.LENGTH_SHORT).show();

                            int previousPosition = notemodellist.indexOf(selectedNote);
                            if (previousPosition >= 0 && previousPosition < unpinnedNotesList.size()) {
                                unpinnedNotesList.add(previousPosition, selectedNote);
                            } else {
                                unpinnedNotesList.add(selectedNote);
                            }
                            pinnedNotesList.remove(selectedNote);
                        }


                        refreshRecyclerView();
                        return true;
                    }

                    return false;
                }
            });
            popupMenu.inflate(R.menu.menu_popup);
            popupMenu.show();
        }
    }

    private void refreshActivity() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            recreate(); // Refresh the activity to update the content
        }
    }

    private void refreshRecyclerView() {
        List<NoteModel> combinedList = new ArrayList<>();
        combinedList.addAll(pinnedNotesList);
        combinedList.addAll(unpinnedNotesList);

        // Update your RecyclerView adapter with the combinedList
        // Notify the adapter of any changes

        // For example, if you're using RecyclerView.Adapter:
        adapter.setNotes(combinedList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.setClassName("com.example.pahuncharlogmain", "com.example.pahuncharlogmain.MainActivity");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_UPDATE_NOTE && resultCode == RESULT_OK) {
            if (data != null && data.hasExtra("UpdatedNote")) {
                NoteModel updatedNote = data.getParcelableExtra("UpdatedNote");
                refreshActivity();
            }
        }
    }
}