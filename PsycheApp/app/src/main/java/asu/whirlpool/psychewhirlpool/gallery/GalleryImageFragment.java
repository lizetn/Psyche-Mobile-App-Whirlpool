package asu.whirlpool.psychewhirlpool.gallery;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import asu.whirlpool.psychewhirlpool.ImageAdapter;
import asu.whirlpool.psychewhirlpool.R;

/**
 * GalleryImageFragment displays the Gallery images.
 *
 * @author Erick Ramirez Cordero
 * @date 1/25/2018.
 */

public class GalleryImageFragment extends Fragment
{
    private View view;

    public static final String IMAGE_KEY = "image_key";

    /**
     * Initializes the Fragment.
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return                      Instantiated Gallery Image Fragment
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fragment_gallery_images, container, false);

        // Set up ImageAdapter
        GridView gridView = view.findViewById(R.id.gridView);
        gridView.setAdapter(new ImageAdapter(view.getContext()));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {
                Intent intent = new Intent(view.getContext().getApplicationContext(), FullImageActivity.class);
                intent.putExtra(IMAGE_KEY, position);
                startActivity(intent);
            }
        });
        return view;
    }
}
