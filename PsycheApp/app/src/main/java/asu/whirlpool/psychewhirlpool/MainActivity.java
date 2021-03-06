package asu.whirlpool.psychewhirlpool;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
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
import asu.whirlpool.psychewhirlpool.home.HelpActivity;
import asu.whirlpool.psychewhirlpool.timeline.TimelineTab;

/**
 * Displays the home page of the app which contains a navigation bar and buttons for
 * accessing the countdown clock, mission facts, NASA website, psyche news website,
 * navigation help, and a toggle for night mode coloring of the app.
 *
 * @author      Natalie Fleischaker
 * @version     4/8/2018
 *
 */
public class MainActivity extends AppCompatActivity
{
    private TextView mTextMessage;
    private BottomNavigationView navigation;
    private ConstraintLayout mBackground;
    private ImageView mImageView;
    final int sdk = android.os.Build.VERSION.SDK_INT;

    private final String NEWS_URI = "https://psyche.asu.edu/category/news/";

    /**
     * Handles navigation between different sections of the Psyche App.
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

        // Set up navigation bar
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) navigation.getChildAt(0);
        for (int i = 0; i < menuView.getChildCount(); i++) {
            final View iconView = menuView.getChildAt(i).findViewById(android.support.design.R.id.icon);
            final ViewGroup.LayoutParams layoutParams = iconView.getLayoutParams();
            final DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
            // navigation icon height set here
            layoutParams.height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 32, displayMetrics);
            // navigation icon width set here
            layoutParams.width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 32, displayMetrics);
            iconView.setLayoutParams(layoutParams);
        }
        BottomNavigationViewHelper.disableAnimation(navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);
    }

    @Override
    protected void onStart() {
        super.onStart();

        // if the app is in night mode, change all icons to gold versions and switch background
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            mBackground = (ConstraintLayout) findViewById(R.id.container);
            if(sdk >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                mBackground.setBackground(ContextCompat.getDrawable(this, R.drawable.home_bg_dark));
            } else {
                mBackground.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.home_bg_dark));
            }
            mImageView = (ImageView) findViewById(R.id.homeClockIcon);
            mImageView.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.home_countdownclock_mustard_solid_300));
            mImageView = (ImageView) findViewById(R.id.homeHelpIcon);
            mImageView.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.home_help_mustard_solid_300));
            mImageView = (ImageView) findViewById(R.id.homeNewsIcon);
            mImageView.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.home_news_mustard_solid_psyche_300));
            mImageView = (ImageView) findViewById(R.id.homeFactsIcon);
            mImageView.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.home_missionfacts_mustard_solid_300));
            mImageView = (ImageView) findViewById(R.id.homeMoonIcon);
            mImageView.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.home_nightmode_mustard_solid_300));
        }
        // if not in night mode, make sure icons and background are changed back to defaults
        else {
            mBackground = (ConstraintLayout) findViewById(R.id.container);
            if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                mBackground.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.home_bg_light));
            } else {
                mBackground.setBackground(ContextCompat.getDrawable(this, R.drawable.home_bg_light));
            }
            mImageView = (ImageView) findViewById(R.id.homeClockIcon);
            mImageView.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.countdown_clock_300));
            mImageView = (ImageView) findViewById(R.id.homeHelpIcon);
            mImageView.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.home_help_darkpurple_solid_300));
            mImageView = (ImageView) findViewById(R.id.homeNewsIcon);
            mImageView.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.home_news_darkpurple_solid_psyche_300));
            mImageView = (ImageView) findViewById(R.id.homeFactsIcon);
            mImageView.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.home_missionfacts_darkpurple_solid_300));
            mImageView = (ImageView) findViewById(R.id.homeMoonIcon);
            mImageView.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.night_mode_300));
        }
    }

    /**
     * Navigates to {@link CountdownActivity}
     * @param view  View from which the method is called.
     */
    public void displayCountdown(View view)
    {
        Intent intent = new Intent(this, CountdownActivity.class);
        startActivity(intent);
    }

    /**
     * Navigates to {@link FactsActivity}
     * @param view  View from which the method is called.
     */
    public void displayFacts(View view)
    {
        Intent intent = new Intent(this, FactsActivity.class);
        startActivity(intent);
    }

    /**
     * Closes the app and displays Psyche news website in chosen browser.
     * @param view  View from which the method is called.
     */
    public void displayNews(View view)
    {
        Uri newsUrl = Uri.parse(NEWS_URI);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, newsUrl);
        startActivity(launchBrowser);
    }

    /**
     * Displays help activity for help with navigation icon titles.
     * @param view  View from which the method is called.
     */
    public void displayHelp(View view)
    {
        Intent intent = new Intent(this, HelpActivity.class);
        startActivity(intent);
    }

    /**
     * Closes the app and displays NASA website in chosen browser.
     * @param view  View from which the method is called.
     */
    public void displayNASAWebsite(View view)
    {
        Uri nasaUrl = Uri.parse("https://www.nasa.gov/psyche");
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, nasaUrl);
        startActivity(launchBrowser);
    }

    /**
     * Toggles color scheme on home page.
     * @param view  View from which the method is called.
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
     *
     * A version of the intro for use after a major update has not been
     * implemented since the app is new and has had no major updates.
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
            // TODO Add activity for info upon upgrade if that functionality is desired
            return;
        }

        // Update the shared preferences with the current version code
        prefs.edit().putInt(PREF_VERSION_NUMBER, currentVersionCode).apply();
    }
}
