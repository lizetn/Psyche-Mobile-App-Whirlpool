package asu.whirlpool.psychewhirlpool.gallery;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import asu.whirlpool.psychewhirlpool.R;

/**
 * {@link FullImageActivity} displays a full-screen version of the image selected in
 * the {@link GalleryTab}. Additionally, the User can toggle between a description-mode
 * and the full-screen mode.
 *
 * @author      Diana Chavez, Erick Ramirez Cordero
 * @version     4/9/2018
 */
public class FullImageActivity extends AppCompatActivity
{
    ImageView fullImageView;
    ImageView desImageView;
    TextView desTextView;
    ScrollView desScrollView;

    /**
     * Display a full screen version of an image based on the resource
     * ID passed by the {@link ImageRecycleAdapter}.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES)
        {
            setTheme(R.style.PsycheDarkTheme);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_image);

        Intent intent = getIntent();
        fullImageView = findViewById(R.id.fullImageView);
        desImageView = findViewById(R.id.desImageView);
        desTextView = findViewById(R.id.desTextView);
        desScrollView = findViewById(R.id.desScrollView);

        try
        {
            int resId = intent.getIntExtra(ImageRecycleAdapter.IMAGE_KEY, ImageRecycleAdapter.RES_ERROR);
            CharSequence descriptionText = intent.getCharSequenceExtra(ImageRecycleAdapter.DESCRIPTION_KEY);

            fullImageView.setImageResource(resId);
            desImageView.setImageResource(resId);
            desTextView.setText(descriptionText);
        }
        catch (Exception ex)
        {
            // Display error image
            desImageView.setImageResource(R.drawable.psyche_icon_bw);
        }
    }

    /**
     * Toggles the visibility of the image description.
     */
    public void toggleDescription(View view)
    {
        if (fullImageView.getVisibility() == View.VISIBLE)
        {
            fullImageView.setVisibility(View.INVISIBLE);
            desImageView.setVisibility(View.VISIBLE);
            desScrollView.setVisibility(View.VISIBLE);
        }
        else
        {
            fullImageView.setVisibility(View.VISIBLE);
            desImageView.setVisibility(View.INVISIBLE);
            desScrollView.setVisibility(View.INVISIBLE);
        }
    }
}
