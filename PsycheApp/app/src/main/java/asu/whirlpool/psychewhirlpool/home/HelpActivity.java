package asu.whirlpool.psychewhirlpool.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import asu.whirlpool.psychewhirlpool.BottomNavigationViewHelper;
import asu.whirlpool.psychewhirlpool.GameActivity;
import asu.whirlpool.psychewhirlpool.MainActivity;
import asu.whirlpool.psychewhirlpool.R;
import asu.whirlpool.psychewhirlpool.SocialMediaTabs;
import asu.whirlpool.psychewhirlpool.gallery.GalleryTab;
import asu.whirlpool.psychewhirlpool.timeline.TimelineTab;

public class HelpActivity extends AppCompatActivity {

    private BottomNavigationView navigation;
    private ConstraintLayout mBackground;
    private ImageView mImageView;
    private Button mButton;
    final int sdk = android.os.Build.VERSION.SDK_INT;

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
                    intent = new Intent(HelpActivity.this, TimelineTab.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_gallery:
                    intent = new Intent(HelpActivity.this, GalleryTab.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_social_media:
                    intent = new Intent(HelpActivity.this, SocialMediaTabs.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_game:
                    intent = new Intent(HelpActivity.this, GameActivity.class);
                    startActivity(intent);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.PsycheDarkTheme);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

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

        // if the app is in night mode, change button to gold version and switch background
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            mBackground = (ConstraintLayout) findViewById(R.id.container);
            mButton = (Button) findViewById(R.id.homeHelpCreditsButton);
            if(sdk >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                mBackground.setBackground(ContextCompat.getDrawable(this, R.drawable.home_help_background_dark));
                mButton.setBackground(ContextCompat.getDrawable(this, R.drawable.help_credits_button_light_400));
            } else {
                mBackground.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.home_help_background_dark));
                mButton.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.help_credits_button_light_400));
            }
            mImageView = (ImageView) findViewById(R.id.arrow);
            mImageView.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.white_back_arrow));
        }
        // if not in night mode, make sure button and background are changed back to defaults
        else {
            mBackground = (ConstraintLayout) findViewById(R.id.container);
            mButton = (Button) findViewById(R.id.homeHelpCreditsButton);
            if(sdk >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                mBackground.setBackground(ContextCompat.getDrawable(this, R.drawable.home_help_background_light));
                mButton.setBackground(ContextCompat.getDrawable(this, R.drawable.help_credits_button_dark_400));
            } else {
                mBackground.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.home_help_background_light));
                mButton.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.help_credits_button_dark_400));
            }
            mImageView = (ImageView) findViewById(R.id.arrow);
            mImageView.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.purple_back_arrow));
        }
    }

    /**
     * Hides help box for navigation icon titles.
     */
    public void closeHelp(View view)
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
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
}
