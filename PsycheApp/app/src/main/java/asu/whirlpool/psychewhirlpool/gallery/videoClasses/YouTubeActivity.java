package asu.whirlpool.psychewhirlpool.gallery.videoClasses;

import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import asu.whirlpool.psychewhirlpool.R;

/**
 * {@link YouTubeActivity} is a basic implementation of the YouTube API.
 *
 * When the User selects a video from the thumbnails displayed by {@link VideoRecycleAdapter},
 * the video ID is passed in the intent.
 *
 * TODO: Store the API key somewhere more secure.
 * @author      Erick Ramirez Cordero
 * @version     3/26/2018
 */

public class YouTubeActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener
{
    private String videoID;
    private final String API_KEY = "AIzaSyBoKvNxMCzN4gUqYaLe84W538HUqDB2cH8";
    private final String YOUTUBE_ERROR = "Initialization error";

    /**
     * Instantiate the {@link YouTubePlayerView} in the UI.
     * Also get the video ID passed by the {@link VideoRecycleAdapter}.
     */
    @Override
    protected void onCreate(Bundle bundle)
    {
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES)
        {
            setTheme(R.style.PsycheDarkTheme);
        }

        super.onCreate(bundle);
        setContentView(R.layout.activity_youtube_player);

        YouTubePlayerView playerView = findViewById(R.id.YoutubePlayerView);
        playerView.initialize(API_KEY, this);

        videoID = getIntent().getStringExtra(VideoRecycleAdapter.VIDEO_KEY);
    }

    /**
     * If the {@link YouTubePlayer} was initialized properly, play the video.
     */
    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored)
    {
        if ((player != null) && (!wasRestored))
        {
            player.cueVideo(videoID);
        }
    }

    /**
     * If there was an error initializing the {@link YouTubePlayer}, display an error message
     * and log the error.
     */
    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult error)
    {
        Toast.makeText(this, YOUTUBE_ERROR, Toast.LENGTH_SHORT).show();
        Log.e(getString(R.string.error_message), error.toString());
    }
}
