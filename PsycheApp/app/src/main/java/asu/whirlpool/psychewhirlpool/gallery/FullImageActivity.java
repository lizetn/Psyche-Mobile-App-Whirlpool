package asu.whirlpool.psychewhirlpool.gallery;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import asu.whirlpool.psychewhirlpool.R;

/**
 * {@link FullImageActivity} displays a full-screen version of the image selected in
 * the {@link GalleryActivity}.
 *
 * @author      Diana Chavez
 *
 * Revision:    Changed variables and constants. Changed image key to work with new
 *              version of Gallery.
 *
 * @author      Erick Ramirez Cordero
 * @version     2/15/2018
 */
public class FullImageActivity extends AppCompatActivity
{
    /**
     * Display a full screen version of an image based on the resource
     * ID passed by the {@link ImageRecycleAdapter}.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_image);

        Intent intent = getIntent();
        ImageView imageView = findViewById(R.id.imageView);

        try
        {
            int resId = intent.getExtras().getInt(ImageRecycleAdapter.IMAGE_KEY);
            imageView.setImageResource(resId);
        }
        catch (Exception ex)
        {
            // Display error image

        }
    }
}
