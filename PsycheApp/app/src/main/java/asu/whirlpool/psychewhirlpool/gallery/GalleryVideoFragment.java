package asu.whirlpool.psychewhirlpool.gallery;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import asu.whirlpool.psychewhirlpool.R;
import asu.whirlpool.psychewhirlpool.gallery.videoClasses.VideoRecycleAdapter;

/**
 * GalleryVideoFragment handles the Video tab in the Gallery section of the Psyche App.
 * Thumbnails are displayed in a {@link RecyclerView} with a {@link GridLayoutManager}.
 * Additionally, the {@link ???} is used to instantiate and display the
 * images.
 *
 * @author      Erick Ramirez Cordero
 * @version     2/15/2018
 */

public class GalleryVideoFragment extends Fragment
{
    private VideoRecycleAdapter videoAdapter;
    private View view;
    private final int NUMBER_OF_COLUMNS = 3;

    /**
     * Initializes the Fragment.
     *
     * @return          Instantiated Gallery Image Fragment
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fragment_gallery_videos, container, false);

        // Set up the GridLayoutManager and VideoRecycleAdapter
        RecyclerView recyclerView = view.findViewById(R.id.VideoRecyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this.getContext(), NUMBER_OF_COLUMNS));

        videoAdapter = new VideoRecycleAdapter(view.getContext());
        recyclerView.setAdapter(videoAdapter);

        return view;
    }
}
