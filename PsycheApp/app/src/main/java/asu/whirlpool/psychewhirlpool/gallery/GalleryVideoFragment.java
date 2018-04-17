package asu.whirlpool.psychewhirlpool.gallery;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import asu.whirlpool.psychewhirlpool.R;
import asu.whirlpool.psychewhirlpool.gallery.videoClasses.VideoRecycleAdapter;

/**
 * GalleryVideoFragment handles the Video tab in the Gallery section of the Psyche App.
 * Thumbnails are displayed in a {@link RecyclerView} with a {@link GridLayoutManager}.
 * Additionally, the {@link VideoRecycleAdapter} is used to instantiate and display the
 * thumbnails.
 *
 * @author      Erick Ramirez Cordero
 * @version     3/7/2018
 */
public class GalleryVideoFragment extends Fragment
{
    private final int NUMBER_OF_COLUMNS = 2;

    /**
     * Initializes the Fragment. A {@link GridLayoutManager} is used with the {@link RecyclerView}.
     *
     * @return          Instantiated Gallery Image Fragment
     */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_gallery_videos, container, false);
        ConstraintLayout constraintLayout = view.findViewById(R.id.constraintLayout);

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES)
        {
            constraintLayout.setBackgroundResource(R.drawable.background_timeline_dark);
        }
        else
        {
            constraintLayout.setBackgroundResource(R.drawable.background_timeline_light);
        }

        // Set up the GridLayoutManager and VideoRecycleAdapter
        RecyclerView recyclerView = view.findViewById(R.id.VideoRecyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this.getContext(), NUMBER_OF_COLUMNS));

        VideoRecycleAdapter videoAdapter = new VideoRecycleAdapter(view.getContext());
        recyclerView.setAdapter(videoAdapter);

        return view;
    }
}
