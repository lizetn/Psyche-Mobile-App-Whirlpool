package asu.whirlpool.psychewhirlpool.gallery;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import asu.whirlpool.psychewhirlpool.R;

/**
 * VideoActivity manages the playback of a video selected from the Gallery.
 * @author  Erick Ramirez Cordero
 * @date    1/25/2018
 */
public class VideoActivity extends AppCompatActivity
{
    public int ResourceId;
    private MediaInterface videoPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        // Initialize Video Player
        videoPlayer = new VideoPlayer(this);
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        videoPlayer.load(ResourceId);
    }

    @Override
    protected void onStop()
    {
        super.onStop();

        if (isChangingConfigurations() && videoPlayer.isPlaying())
        {
            //
        }
    }
}
