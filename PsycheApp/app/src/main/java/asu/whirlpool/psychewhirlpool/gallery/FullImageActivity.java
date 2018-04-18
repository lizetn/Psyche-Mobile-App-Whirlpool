package asu.whirlpool.psychewhirlpool.gallery;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import asu.whirlpool.psychewhirlpool.R;

/**
 * {@link FullImageActivity} displays an image and description selected in
 * the {@link GalleryImageFragment}.
 *
 * @author      Diana Chavez, Erick Ramirez Cordero
 * @version     4/16/2018
 */
public class FullImageActivity extends AppCompatActivity
{
    ImageView desImageView;
    TextView desTextView;
    ScrollView desScrollView;

    /**
     * Displays a larger image and a description based on the information
     * passed by the {@link ImageRecycleAdapter}.
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

        desImageView = findViewById(R.id.desImageView);
        desTextView = findViewById(R.id.desTextView);
        desScrollView = findViewById(R.id.desScrollView);
        Intent intent = getIntent();

        try
        {
            String imageUrl = intent.getStringExtra(ImageRecycleAdapter.URL_KEY);
            CharSequence descriptionText = intent.getCharSequenceExtra(ImageRecycleAdapter.DESCRIPTION_KEY);

            Glide.with(this).load(imageUrl).into(desImageView);
            desTextView.setText(descriptionText);
        }
        catch (Exception ex)
        {
            // Display error if image could not be loaded
            desImageView.setImageResource(R.drawable.psyche_icon_bw);
            desTextView.setText(R.string.internet_connection_error);
        }
    }
}
