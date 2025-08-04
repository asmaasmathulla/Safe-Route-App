package com.s23010605.saferoute;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class VideosActivity extends AppCompatActivity {

    ImageButton video1, video2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_videos);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        video1 = findViewById(R.id.video1);
        video2 = findViewById(R.id.video2);

        video1.setOnClickListener(view -> openVideo("video1"));
        video2.setOnClickListener(view -> openVideo("video2"));
    }

    private void openVideo(String videoName) {
        Intent intent = new Intent(VideosActivity.this, VideoPlayerActivity.class);
        intent.putExtra("VIDEO_NAME", videoName);
        startActivity(intent);
    }
}