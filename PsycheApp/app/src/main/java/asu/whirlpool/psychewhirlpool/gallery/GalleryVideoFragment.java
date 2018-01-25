package asu.whirlpool.psychewhirlpool.gallery;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import asu.whirlpool.psychewhirlpool.R;

/**
 * GalleryVideoFragment displays the Gallery videos.
 *
 * @author Erick Ramirez Cordero
 * @date 1/25/2018.
 */

public class GalleryVideoFragment extends Fragment
{
    MediaSessionCompat mMediaSession;
    PlaybackStateCompat.Builder mStateBuilder;

    private View view;

    /**
     * Initializes the Fragment.
     *
     * @return          Instantiated Gallery Image Fragment
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fragment_gallery_videos, container, false);

        // Set up videos
        GridView gridView = view.findViewById(R.id.gridView);

        return view;
    }
}
