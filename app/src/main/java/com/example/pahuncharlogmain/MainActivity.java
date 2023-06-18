package com.example.pahuncharlogmain;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.OpenDrawer, R.string.CloseDrawer);
        drawerLayout.addDrawerListener(toggle);

        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.todo) {
                    // Handle Home menu item click
                    Intent intent = new Intent(MainActivity.this, com.example.newtodolist.MainActivity.class);
                    startActivity(intent);
                    return true;
                } else if (id == R.id.event) {
                    // Handle Event Tracker menu item click
                    Intent intent = new Intent(MainActivity.this, com.example.eventtracker.MainActivity.class);
                    startActivity(intent);
                    return true;
                } else if (id == R.id.bill) {
                    // Handle Bill Generator menu item click
                    Intent intent = new Intent(MainActivity.this, com.example.billgenerator.MainActivity.class);
                    startActivity(intent);
                    return true;
                } else if (id == R.id.notes) {
                    // Handle Notes menu item click
                    Intent intent = new Intent(MainActivity.this, com.example.newnotesapp.MainActivity.class);
                    startActivity(intent);
                    return true;
                } else if (id == R.id.price_calculator) {
                    // Handle Price Calculator menu item click
                    Intent intent = new Intent(MainActivity.this, com.example.pricecalculator.MainActivity.class);
                    startActivity(intent);
                    return true;
                } else if (id == R.id.shareable) {
                    // Handle Shareable menu item click
                    Intent intent = new Intent(MainActivity.this, com.example.shareble.MainActivity.class);
                    startActivity(intent);
                    return true;
                } else if (id == R.id.reminder) {
                    // Handle Reminders menu item click
                    Intent intent = new Intent(MainActivity.this, com.example.reminder.MainActivity.class);
                    startActivity(intent);
                    return true;
                }

                return false;
            }
        });

        FloatingActionButton fabAdd = findViewById(R.id.fab_add);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, com.example.eventtracker.MainActivity.class);
                startActivity(intent);
            }
        });


    }
}



