package asu.whirlpool.psychewhirlpool.gallery;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import asu.whirlpool.psychewhirlpool.R;

/**
 * VideoActivity manages the playback of a video selected from the Gallery.
 * @author  Erick Ramirez Cordero
 * @date    1/25/18
 */
public class VideoActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
    }
}
