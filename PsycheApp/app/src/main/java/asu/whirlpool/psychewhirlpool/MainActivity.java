package asu.whirlpool.psychewhirlpool;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.facebook.FacebookSdk;

import asu.whirlpool.psychewhirlpool.facts.FactsActivity;
import asu.whirlpool.psychewhirlpool.gallery.GalleryTab;
import asu.whirlpool.psychewhirlpool.home.AppCreditsActivity;
import asu.whirlpool.psychewhirlpool.home.FirstRunIntroActivity;
import asu.whirlpool.psychewhirlpool.timeline.TimelineTab;

public class MainActivity extends AppCompatActivity
{
    private TextView mTextMessage;
    private BottomNavigationView navigation;
    private ConstraintLayout mHelpBox, mButtonsBox, mBackground;
    private ImageView mImageView;
    private Button mButton;
    final int sdk = android.os.Build.VERSION.SDK_INT;

    private final String NEWS_URI = "https://psyche.asu.edu/category/news/";

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

        checkLastVersionRun();

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
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            mBackground = (ConstraintLayout) findViewById(R.id.container);
            mButton = (Button) findViewById(R.id.nasaButton);
            if(sdk >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                mBackground.setBackground(ContextCompat.getDrawable(this, R.drawable.home_bg_dark));
                mButton.setBackground(ContextCompat.getDrawable(this, R.drawable.nasa_insignia_mustard_300));
            } else {
                mBackground.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.home_bg_dark));
                mButton.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.nasa_insignia_mustard_300));
            }
            mImageView = (ImageView) findViewById(R.id.homeClockIcon);
            mImageView.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.home_countdownclock_mustard_solid_300));
            mImageView = (ImageView) findViewById(R.id.homeHelpIcon);
            mImageView.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.home_help_mustard_solid_300));
            mImageView = (ImageView) findViewById(R.id.homeNewsIcon);
            mImageView.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.home_news_mustard_solid_300));
            mImageView = (ImageView) findViewById(R.id.homeFactsIcon);
            mImageView.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.home_missionfacts_mustard_solid_300));
            mImageView = (ImageView) findViewById(R.id.homeMoonIcon);
            mImageView.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.home_nightmode_mustard_solid_300));
        } else {
            mBackground = (ConstraintLayout) findViewById(R.id.container);
            mButton = (Button) findViewById(R.id.nasaButton);
            if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                mBackground.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.home_bg_light));
                mButton.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.nasa_insignia_darkpurple_300));
            } else {
                mBackground.setBackground(ContextCompat.getDrawable(this, R.drawable.home_bg_light));
                mButton.setBackground(ContextCompat.getDrawable(this, R.drawable.nasa_insignia_darkpurple_300));
            }
            mImageView = (ImageView) findViewById(R.id.homeClockIcon);
            mImageView.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.countdown_clock_300));
            mImageView = (ImageView) findViewById(R.id.homeHelpIcon);
            mImageView.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.home_help_darkpurple_solid_300));
            mImageView = (ImageView) findViewById(R.id.homeNewsIcon);
            mImageView.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.home_news_darkpurple_solid_300));
            mImageView = (ImageView) findViewById(R.id.homeFactsIcon);
            mImageView.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.home_missionfacts_darkpurple_solid_300));
            mImageView = (ImageView) findViewById(R.id.homeMoonIcon);
            mImageView.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.night_mode_300));
        }
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

    public void displayNews(View view)
    {
        Uri newsUrl = Uri.parse(NEWS_URI);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, newsUrl);
        startActivity(launchBrowser);
    }

    /**
     * Navigates to {@link asu.whirlpool.psychewhirlpool.home.AppCreditsActivity}
     * @param view
     */
    public void displayCredits(View view)
    {
        Intent intent = new Intent(this, AppCreditsActivity.class);
        startActivity(intent);
    }

    /**
     * Displays help box for navigation icon titles.
     */
    public void displayHelp(View view)
    {
        mHelpBox = (ConstraintLayout) findViewById(R.id.homeHelpWindow);
        mHelpBox.setVisibility(View.VISIBLE);

        mButtonsBox = (ConstraintLayout) findViewById(R.id.homeButtonsLayout);
        mButtonsBox.setVisibility(View.GONE);
    }

    /**
     * Hides help box for navigation icon titles.
     */
    public void closeHelp(View view)
    {
        mButtonsBox = (ConstraintLayout) findViewById(R.id.homeButtonsLayout);
        mButtonsBox.setVisibility(View.VISIBLE);

        mHelpBox = (ConstraintLayout) findViewById(R.id.homeHelpWindow);
        mHelpBox.setVisibility(View.GONE);
    }

    /**
     * Closes the app and displays NASA website in chosen browser.
     */
    public void displayNASAWebsite(View view)
    {
        Uri nasaUrl = Uri.parse("https://www.nasa.gov/psyche");
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, nasaUrl);
        startActivity(launchBrowser);
    }

    /**
     * Closes the app and displays ASU Psyche Mission website in chosen browser.
     */
    public void displayASUPsycheWebsite(View view)
    {
        Uri asuPsycheUrl = Uri.parse("https://psyche.asu.edu/");
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, asuPsycheUrl);
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

    /**
     * Checks shared preferences to see if this is the first time the app is
     * being run or if the app has had important updates.
     */
    private void checkLastVersionRun() {

        final String PREFS_NAME = "PsychePrefsFile";
        final String PREF_VERSION_NUMBER = "version_number";
        final int IS_FIRST_RUN = -1;

        // Get current version code
        int currentVersionCode = BuildConfig.VERSION_CODE;

        // Get saved version code
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        int savedVersionCode = prefs.getInt(PREF_VERSION_NUMBER, IS_FIRST_RUN);

        // Check for first run or upgrade
        if (currentVersionCode == savedVersionCode) {
            // This is just a normal run
            return;

        } else if (savedVersionCode == IS_FIRST_RUN) {
            // This is the first run or preferences were cleared
            Intent intent = new Intent(this, FirstRunIntroActivity.class);
            startActivity(intent);

        } else if (currentVersionCode > savedVersionCode) {
            // The app has been upgraded
            // TODO Add activity for info upon upgrade
            return;
        }

        // Update the shared preferences with the current version code
        prefs.edit().putInt(PREF_VERSION_NUMBER, currentVersionCode).apply();
    }
}
