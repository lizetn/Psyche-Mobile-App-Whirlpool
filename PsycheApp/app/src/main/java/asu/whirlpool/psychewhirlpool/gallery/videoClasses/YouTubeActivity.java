package asu.whirlpool.psychewhirlpool.gallery.videoClasses;

import android.os.Bundle;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import asu.whirlpool.psychewhirlpool.R;

/**
 * Created by rami7 on 3/25/2018.
 */

public class YouTubeActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener
{
    String API_KEY = "AIzaSyBoKvNxMCzN4gUqYaLe84W538HUqDB2cH8";

    @Override
    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(R.layout.activity_youtube_player);
        YouTubePlayerView playerView = findViewById(R.id.YoutubePlayerView);
        playerView.initialize(API_KEY, this);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b)
    {

    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult)
    {

    }
}
