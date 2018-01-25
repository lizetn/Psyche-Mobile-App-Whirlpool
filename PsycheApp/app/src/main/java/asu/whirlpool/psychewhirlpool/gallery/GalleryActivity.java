package asu.whirlpool.psychewhirlpool.gallery;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.AdapterView;
import android.widget.TextView;

import asu.whirlpool.psychewhirlpool.BottomNavigationViewHelper;
import asu.whirlpool.psychewhirlpool.GameActivity;
import asu.whirlpool.psychewhirlpool.ImageAdapter;
import asu.whirlpool.psychewhirlpool.MainActivity;
import asu.whirlpool.psychewhirlpool.R;
import asu.whirlpool.psychewhirlpool.SocialMediaTabs;
import asu.whirlpool.psychewhirlpool.timeline.TimelineTab;

/**
 * GalleryActivity handles the Gallery for the app through the use of the {@link ImageAdapter}.
 * When an image is selected, a full-screen image is displayed with {@link FullImageActivity}.
 *
 * Revised variables and constants.
 * @revision    Erick Ramirez Cordero
 * @date        1/10/2018
 */
public class GalleryActivity extends AppCompatActivity
{
    public static final String IMAGE_KEY = "image_key";
    private final int NAVIGATION_ID = 2;
    private TextView mTextMessage;

    /**
     * Handles navigation between different sections of the app
     */
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener()
    {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item)
        {
            Intent intent;

            switch (item.getItemId())
            {
                case R.id.navigation_home:
                    intent = new Intent(GalleryActivity.this, MainActivity.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_timeline:
                    intent = new Intent(GalleryActivity.this, TimelineTab.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_gallery:
                    return true;
                case R.id.navigation_social_media:
                    intent = new Intent(GalleryActivity.this, SocialMediaTabs.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_game:
                    intent = new Intent(GalleryActivity.this, GameActivity.class);
                    startActivity(intent);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        mTextMessage = findViewById(R.id.message);

        // Set up Navigation Bar
        BottomNavigationView navigation = findViewById(R.id.navigation);
        BottomNavigationViewHelper.disableAnimation(navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(NAVIGATION_ID);
        menuItem.setChecked(true);

        // Set up ImageAdapter
        GridView tryGrid = findViewById(R.id.tryGridView);
        tryGrid.setAdapter(new ImageAdapter(this));

        tryGrid.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {
                Intent intent = new Intent(getApplicationContext(), FullImageActivity.class);
                intent.putExtra(IMAGE_KEY, position);
                startActivity(intent);
            }
        });
    }
}
