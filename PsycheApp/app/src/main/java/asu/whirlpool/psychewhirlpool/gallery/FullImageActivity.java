package asu.whirlpool.psychewhirlpool.gallery;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import asu.whirlpool.psychewhirlpool.ImageAdapter;
import asu.whirlpool.psychewhirlpool.R;

/**
 * {@link FullImageActivity} displays a full-screen version of the image selected in
 * the {@link GalleryActivity}.
 *
 * Revised variables and constants.
 * @revision    Erick Ramirez Cordero
 * @date        1/10/2018
 */
public class FullImageActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_image);

        Intent intent = getIntent();

        int position = intent.getExtras().getInt(GalleryActivity.IMAGE_KEY);
        ImageAdapter adapter = new ImageAdapter(this);

        ImageView imageView = findViewById(R.id.imageView);
        imageView.setImageResource(adapter.images[position]);
    }
}
