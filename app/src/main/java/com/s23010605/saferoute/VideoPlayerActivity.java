package com.s23010605.saferoute;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class VideoPlayerActivity extends AppCompatActivity {

    VideoView videoView;
    String videoName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_video_player);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.videoView), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        videoView = findViewById(R.id.videoView);
        videoName = getIntent().getStringExtra("VIDEO_NAME");

        int videoResId = getResources().getIdentifier(videoName, "raw", getPackageName());
        Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + videoResId);

        videoView.setVideoURI(videoUri);
        videoView.setMediaController(new MediaController(this));
        videoView.start();
    }
}