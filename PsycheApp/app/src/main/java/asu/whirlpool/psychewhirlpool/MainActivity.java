package asu.whirlpool.psychewhirlpool;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.facebook.FacebookSdk;
import asu.whirlpool.psychewhirlpool.timeline.TimelineTab;

public class MainActivity extends AppCompatActivity
{
    private TextView mTextMessage;
    private ImageView mTitleImage;
    private ImageView mButtonsImage;
    private ConstraintLayout mConstraint;
    private BottomNavigationView mNavView;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener()
    {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item)
        {
            Intent intent;

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    return true;
                case R.id.navigation_timeline:
                    intent = new Intent(MainActivity.this, TimelineTab.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_gallery:
                    intent = new Intent(MainActivity.this, GalleryActivity.class);
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
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        BottomNavigationViewHelper.disableAnimation(navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);
    }

    @Override
    protected void onStart() {
        super.onStart();

        mTitleImage = (ImageView) findViewById(R.id.homePageTitle);
        mButtonsImage = (ImageView) findViewById(R.id.homePageButtons);

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            mTitleImage.setImageResource(R.drawable.night_home_title);
            mButtonsImage.setImageResource(R.drawable.night_title_buttons);
        }
        else
        {
            mTitleImage.setImageResource(R.drawable.home_title);
            mButtonsImage.setImageResource(R.drawable.white_title_buttons);
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

    /**
     * Toggles color scheme on home page.
     * @param view
     */
    public void toggleHomeNightMode(View view)
    {
        mTitleImage = (ImageView) findViewById(R.id.homePageTitle);
        mButtonsImage = (ImageView) findViewById(R.id.homePageButtons);
        mConstraint = (ConstraintLayout) findViewById(R.id.container);
        mNavView = (BottomNavigationView) findViewById(R.id.navigation);

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES)
        {
            /*mTitleImage.setImageResource(R.drawable.home_title);
            mButtonsImage.setImageResource(R.drawable.white_title_buttons);*/
            /*mConstraint.setBackgroundResource(R.color.tw__composer_white);
            mNavView.setItemBackgroundResource(R.color.tw__composer_white);*/
            setTheme(R.style.PsycheLightTheme);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            Intent intent = new Intent(MainActivity.this, MainActivity.class);
            startActivity(intent);
        }
        else
        {
            /*mTitleImage.setImageResource(R.drawable.night_home_title);
            mButtonsImage.setImageResource(R.drawable.night_title_buttons);*/
            /*mConstraint.setBackgroundResource(R.color.psyche_dark_purple);
            mNavView.setItemBackgroundResource(R.color.psyche_purple);*/
            setTheme(R.style.PsycheDarkTheme);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            Intent intent = new Intent(MainActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }
}
