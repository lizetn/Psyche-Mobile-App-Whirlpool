package asu.whirlpool.psychewhirlpool.gallery;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import asu.whirlpool.psychewhirlpool.R;

/**
 * GalleryImageFragment handles the Image tab in the Gallery section of the Psyche App.
 * Images are displayed in a {@link RecyclerView} with a {@link GridLayoutManager}.
 * Additionally, the {@link ImageRecycleAdapter} is used to instantiate and display the
 * images.
 *
 * When an image is selected, a {@link FullImageActivity} will display a full screen version
 * of the image.
 *
 * @author      Erick Ramirez Cordero
 * @version     3/7/2018
 */
public class GalleryImageFragment extends Fragment
{
    private final int NUMBER_OF_COLUMNS = 2;

    /**
     * Initializes the Fragment. A {@link GridLayoutManager} is used for the layout and
     * an {@link ImageRecycleAdapter} is set to the {@link RecyclerView} to instantiate
     * and display the images.
     *
     * @return          Instantiated Gallery Image Fragment {@link View}.
     */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_gallery_images_recycle, container, false);

        // Set up the GridLayoutManager and ImageRecycleAdapter
        RecyclerView recyclerView = view.findViewById(R.id.ImageRecyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this.getContext(), NUMBER_OF_COLUMNS));

        ImageRecycleAdapter imageAdapter = new ImageRecycleAdapter(view.getContext());
        recyclerView.setAdapter(imageAdapter);

        return view;
    }
}
