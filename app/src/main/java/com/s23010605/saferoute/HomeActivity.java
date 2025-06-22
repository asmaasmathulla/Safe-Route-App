package com.s23010605.saferoute;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Live Location Button
        Button liveLocationBtn = findViewById(R.id.liveLocationBtn);
        liveLocationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, LiveLocationActivity.class);
                startActivity(intent);
            }
        });

        // Contacts Button
        Button contactsBtn = findViewById(R.id.contactsBtn);
        contactsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ContactsActivity.class);
                startActivity(intent);
            }
        });

        // Media Button
        Button mediaBtn = findViewById(R.id.mediaBtn);
        mediaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, MediaActivity.class);
                startActivity(intent);
            }
        });

        // Settings Icon
        ImageButton settingsIcon = findViewById(R.id.settingsIcon);
        settingsIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });

        // Notification Icon
        ImageButton notificationsIcon = findViewById(R.id.notificationsIcon);
        notificationsIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, NotificationsActivity.class);
                startActivity(intent);
            }
        });

        //SOS Button
        ImageView sosBtn = findViewById(R.id.sosBtn);
        sosBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, LocationSentActivity.class);
                startActivity(intent);
            }
        });


    }
}