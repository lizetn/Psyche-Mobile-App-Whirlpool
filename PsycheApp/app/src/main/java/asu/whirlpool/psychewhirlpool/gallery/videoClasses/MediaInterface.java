package asu.whirlpool.psychewhirlpool.gallery.videoClasses;

/**
 * MediaInterface defines the methods needed for media playback.
 *
 * @author  Erick Ramirez Cordero
 * @date    1/25/18
 */

public interface MediaInterface
{
    boolean isPlaying();

    void load(int resourceId);
    void pause();
    void play();
    void release();
    void reset();
    void seek(int position);
}
