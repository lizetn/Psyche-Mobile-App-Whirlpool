package asu.whirlpool.psychewhirlpool.gallery;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;

import asu.whirlpool.psychewhirlpool.R;

/**
 * VideoPlayer implements {@link MediaInterface} and uses {@link MediaPlayer} to
 * play videos stored in the Gallery.
 *
 * Based off the following sample code:
 * https://github.com/googlesamples/android-SimpleMediaPlayer
 *
 * @author  Erick Ramirez Cordero
 * @date    1/25/18
 */
public class VideoPlayer implements MediaInterface
{
    private int mResourceId;
    private Context mContext;
    private MediaPlayer mediaPlayer;

    private final String ERROR_TAG = "Error";

    public VideoPlayer(Context context)
    {
        mContext = context.getApplicationContext();
    }

    /**
     * Initialize the {@link MediaPlayer}
     */
    public void initVideoPlayer()
    {
        if (mediaPlayer == null)
        {
            mediaPlayer = new MediaPlayer();
        }
    }

    /**
     * Returns if a video is currently playing.
     */
    @Override
    public boolean isPlaying()
    {
        if (mediaPlayer != null)
        {
            return mediaPlayer.isPlaying();
        }
        else
        {
            return false;
        }
    }

    /**
     * Initialize the {@link MediaPlayer} with the provided resource.
     * @param resourceId    Resource to load
     */
    public void load(int resourceId)
    {
        mResourceId = resourceId;
        initVideoPlayer();

        try
        {
            //mediaPlayer.setDataSource(); // Can't use AssetFileDescriptor
            mediaPlayer.prepare();
        }
        catch (Exception e)
        {
            Log.d(ERROR_TAG, e.toString());
        }
    }

    /**
     * If the {@link MediaPlayer} is playing, pause the playback.
     */
    @Override
    public void pause()
    {
        if (mediaPlayer != null && mediaPlayer.isPlaying())
        {
            mediaPlayer.pause();
        }
    }

    /**
     * If the {@link MediaPlayer} is initialized but not playing, start the player.
     */
    @Override
    public void play()
    {
        if (mediaPlayer != null && !mediaPlayer.isPlaying())
        {
            mediaPlayer.start();
        }
    }

    /**
     * Releases the {@link MediaPlayer} when not used to release resources
     */
    @Override
    public void release()
    {
        if (mediaPlayer != null)
        {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    /**
     * If the playback is stopped, reset the {@link MediaPlayer}.
     */
    @Override
    public void reset()
    {
        if (mediaPlayer != null)
        {
            mediaPlayer.reset();
        }
    }

    @Override
    public void seek(int position)
    {
        if (mediaPlayer != null)
        {
            mediaPlayer.seekTo(position);
        }
    }
}
