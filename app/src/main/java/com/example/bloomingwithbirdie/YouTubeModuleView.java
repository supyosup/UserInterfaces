/**
 * The YouTubeModuleView class represents the first view that a user will see when selecting a Module from the MainActivity.
 * This class will dynamically load the correct YouTube video, based on the Module that is selected from the MainActivity screen.
 */

package com.example.bloomingwithbirdie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

 //FIXME: The YouTube player fullscreen mode does not properly exit.
 // Possible solution: Try using the Official YouTubePlayer that Google has, but the setup is a bit more involved.

public class YouTubeModuleView extends AppCompatActivity {
    private YouTubePlayerView playerView;
    private Module module;
    private Button journalButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.youtube_module_view);
        playerView = findViewById(R.id.youtube_player_view);
        getLifecycle().addObserver(playerView);
        journalButton = findViewById(R.id.journalButton);

        // Verify that the Module was sent from previous Activity
        if (getIntent().getExtras() != null) {
            // Load the module
            module = (Module) getIntent().getSerializableExtra("module");

            // Set appropriate ActionBar color & Title
            configureActionBar(module);

            // FIXME: Buttons are not centered - this is an issue with the text length
            journalButton.setText(module.getName() + " Journal");
            journalButton.setBackgroundColor(module.getColor());

            // Setup the YouTube player & get the videoId to cue up.
            playerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                @Override
                public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                    String videoId = module.getVideoId();
                    youTubePlayer.cueVideo(videoId, 0);
                }
            });
        }
    }

    /** Loads the next view of the Application */
    public void loadJournalView(View view) {
        Intent intent = new Intent(this, JournalView.class);
        intent.putExtra("module", module);
        startActivity(intent);
    }

    /**  This function is used to set the correct title and color for the ActionBar at the top of the application. **/
    void configureActionBar(Module module) {
        // Set the ActionBar title
        getSupportActionBar().setTitle(module.getName() + " Journal");

        // Set the ActionBar color based on the module
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(module.getColor()));
    }
}