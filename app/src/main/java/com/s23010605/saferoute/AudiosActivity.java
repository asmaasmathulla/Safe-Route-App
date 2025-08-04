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

public class AudiosActivity extends AppCompatActivity {

    ImageButton audio1, audio2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_audios);
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


        audio1 = findViewById(R.id.audio1);
        audio2 = findViewById(R.id.audio2);

        audio1.setOnClickListener(view -> openAudio(R.raw.audio1));
        audio2.setOnClickListener(view -> openAudio(R.raw.audio2));
    }

    private void openAudio(int audioResId) {
        Intent intent = new Intent(AudiosActivity.this, AudioPlayerActivity.class);
        intent.putExtra("AUDIO_RES_ID", audioResId);
        startActivity(intent);
    }

}