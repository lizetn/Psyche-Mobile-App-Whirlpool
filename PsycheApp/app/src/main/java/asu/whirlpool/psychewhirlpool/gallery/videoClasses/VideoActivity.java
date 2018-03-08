package asu.whirlpool.psychewhirlpool.gallery.videoClasses;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import asu.whirlpool.psychewhirlpool.R;

/**
 * VideoActivity manages the playback of a video selected from the Gallery.
 * @author  Erick Ramirez Cordero
 * @date    1/25/2018
 */
public class VideoActivity extends AppCompatActivity
{
    public int ResourceId;
    private VideoView videoView;
    //private MediaInterface videoPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        // Set up Uri
        String psycheUri = "android.resource://" + getPackageName() + "/" + R.raw.psyche_test_video;
        Uri uri = Uri.parse(psycheUri);

        // Set up VideoViewer
        videoView = findViewById(R.id.videoView);
        videoView.setMediaController(new MediaController(this));
        videoView.setVideoURI(uri);
        videoView.requestFocus();

        // Initialize Video Player
        //videoPlayer = new VideoPlayer(this);
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        //videoPlayer.load(ResourceId);
        videoView.start();
    }

    @Override
    protected void onStop()
    {
        super.onStop();

        if (isChangingConfigurations() && videoView.isPlaying())
        {
            videoView.stopPlayback();
        }
    }
}
