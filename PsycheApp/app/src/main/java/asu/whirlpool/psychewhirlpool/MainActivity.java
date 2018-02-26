package asu.whirlpool.psychewhirlpool;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import com.facebook.FacebookSdk;

import asu.whirlpool.psychewhirlpool.facts.FactsActivity;
import asu.whirlpool.psychewhirlpool.gallery.GalleryTab;
import asu.whirlpool.psychewhirlpool.timeline.TimelineTab;

public class MainActivity extends AppCompatActivity
{
    private TextView mTextMessage;
    private BottomNavigationView navigation;
    private ConstraintLayout mHelpBox;

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
                    return true;
                case R.id.navigation_timeline:
                    intent = new Intent(MainActivity.this, TimelineTab.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_gallery:
                    intent = new Intent(MainActivity.this, GalleryTab.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_social_media:
                    intent = new Intent(MainActivity.this, SocialMediaTabs.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_game:
                    intent = new Intent(MainActivity.this, GameActivity.class);
                    startActivity(intent);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.PsycheDarkTheme);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FacebookSdk.sdkInitialize(getApplicationContext());
        mTextMessage = (TextView) findViewById(R.id.message);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        BottomNavigationViewHelper.disableAnimation(navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    /**
     * Navigates to {@link CountdownActivity}
     * @param view
     */
    public void displayCountdown(View view)
    {
        Intent intent = new Intent(this, CountdownActivity.class);
        startActivity(intent);
    }

    /**
     * Navigates to {@link FactsActivity}
     * @param view
     */
    public void displayFacts(View view)
    {
        Intent intent = new Intent(this, FactsActivity.class);
        startActivity(intent);
    }

    /**
     * Displays help box for navigation icon titles.
     */
    public void displayHelp(View view)
    {
        mHelpBox = (ConstraintLayout) findViewById(R.id.homeHelpWindow);
        mHelpBox.setVisibility(View.VISIBLE);
    }

    /**
     * Hides help box for navigation icon titles.
     */
    public void closeHelp(View view)
    {
        mHelpBox = (ConstraintLayout) findViewById(R.id.homeHelpWindow);
        mHelpBox.setVisibility(View.INVISIBLE);
    }

    /**
     * Closes the app and displays NASA website in browser.
     */
    public void displayNASAWebsite(View view)
    {
        Uri nasaUrl = Uri.parse("https://www.nasa.gov/");
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, nasaUrl);
        startActivity(launchBrowser);
    }

    /**
     * Toggles color scheme on home page.
     * @param view
     */
    public void toggleHomeNightMode(View view)
    {
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES)
        {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            finish();
            overridePendingTransition(0,0);
            startActivity(getIntent());
            overridePendingTransition(0,0);
        }
        else
        {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            finish();
            overridePendingTransition(0,0);
            startActivity(getIntent());
            overridePendingTransition(0,0);
        }
    }
}
