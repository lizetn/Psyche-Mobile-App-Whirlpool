package asu.whirlpool.psychewhirlpool.gallery.videoClasses;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import asu.whirlpool.psychewhirlpool.R;

/**
 * {@link YouTubeActivity} is a basic implementation of the YouTube API.
 *
 * @author      Erick Ramirez Cordero
 * @version     3/26/18
 */

public class YouTubeActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener
{
    final String API_KEY = "AIzaSyBoKvNxMCzN4gUqYaLe84W538HUqDB2cH8";
    final String VIDEO_ID = "cSMhurC_fm0";
    final String YOUTUBE_ERROR = "Initialization error";

    @Override
    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(R.layout.activity_youtube_player);
        YouTubePlayerView playerView = findViewById(R.id.YoutubePlayerView);
        playerView.initialize(API_KEY, this);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored)
    {
        if ((player != null) && (!wasRestored))
        {
            player.cueVideo(VIDEO_ID);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult error)
    {
        Toast.makeText(this, YOUTUBE_ERROR, Toast.LENGTH_SHORT).show();
    }
}
