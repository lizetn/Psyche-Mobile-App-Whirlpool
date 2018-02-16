package asu.whirlpool.psychewhirlpool.gallery;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

import asu.whirlpool.psychewhirlpool.R;

/**
 * GalleryImageFragment displays the Gallery images.
 *
 * @author Erick Ramirez Cordero
 * @date 1/25/2018.
 */
public class GalleryImageFragment extends Fragment
{
    private ImageRecycleAdapter imageAdapter;
    private View view;
    //public static final String IMAGE_KEY = "image_key";
    private final int NUMBER_OF_COLUMNS = 2;

    /**
     * Initializes the Fragment.
     *
     * @return          Instantiated Gallery Image Fragment
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fragment_gallery_images_recycle, container, false);

        // Set up ImageAdapter
        RecyclerView recyclerView = view.findViewById(R.id.ImageRecyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this.getContext(), NUMBER_OF_COLUMNS));

        imageAdapter = new ImageRecycleAdapter(view.getContext());
        recyclerView.setAdapter(imageAdapter);

        /*
        recyclerView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {
                Intent intent = new Intent(view.getContext().getApplicationContext(), FullImageActivity.class);
                intent.putExtra(IMAGE_KEY, position);
                startActivity(intent);
            }
        });
        */

        return view;
    }
}
