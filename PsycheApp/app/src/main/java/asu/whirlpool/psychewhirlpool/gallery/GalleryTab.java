package asu.whirlpool.psychewhirlpool.gallery;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import asu.whirlpool.psychewhirlpool.BottomNavigationViewHelper;
import asu.whirlpool.psychewhirlpool.GameActivity;
import asu.whirlpool.psychewhirlpool.MainActivity;
import asu.whirlpool.psychewhirlpool.R;
import asu.whirlpool.psychewhirlpool.SocialMediaTabs;
import asu.whirlpool.psychewhirlpool.timeline.TimelineFragment;
import asu.whirlpool.psychewhirlpool.timeline.TimelineTab;

/**
 * GalleryTab implements a tab system to divide the Gallery section
 * into two parts: images and videos.
 *
 * {@link GalleryImageFragment} and {@link GalleryVideoFragment} are used to create the fragments
 * that are displayed when the User navigates through the Gallery.
 *
 * @author      Erick Ramirez Cordero
 * @version     4/17/2018
 */
public class GalleryTab extends AppCompatActivity
{
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private TabLayout tabLayout;

    private final int GALLERY_NAV_INDEX = 2;
    private final int TAB_COUNT = 2;

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
                    intent = new Intent(GalleryTab.this, MainActivity.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_timeline:
                    intent = new Intent(GalleryTab.this, TimelineTab.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_gallery:
                    return true;
                case R.id.navigation_social_media:
                    intent = new Intent(GalleryTab.this, SocialMediaTabs.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_game:
                    intent = new Intent(GalleryTab.this, GameActivity.class);
                    startActivity(intent);
                    return true;
            }

            return false;
        }
    };

    /**
     * Instantiates the tab system and navigation bar.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES)
        {
            setTheme(R.style.PsycheDarkTheme);
        }
        else
        {
            setTheme(R.style.PsycheLightTheme);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_tab);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        // Set up navigation bar
        BottomNavigationView navigation = findViewById(R.id.navigation);
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) navigation.getChildAt(0);

        for (int i = 0; i < menuView.getChildCount(); i++)
        {
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
        MenuItem menuItem = menu.getItem(GALLERY_NAV_INDEX);
        menuItem.setChecked(true);
    }

    /**
     * Inflate the menu; this adds items to the action bar if it is present.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_gallery_tab, menu);
        return true;
    }

    /**
     * Handles action bar item clicks.
     *
     * Note: Parent activity must be specified in AndroidManifest.xml.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter
    {
        private String[] tabTitles = {"Images", "Videos"};

        public SectionsPagerAdapter(FragmentManager fm)
        {
            super(fm);
        }

        /**
         * getItem is called to instantiate the fragment for the phase currently selected.
         *
         * @param position      Tab currently selected
         * @return              New {@link TimelineFragment} with information matching position
         */
        @Override
        public Fragment getItem(int position)
        {
            Fragment fragment;

            switch (position)
            {
                case 0:
                    fragment = new GalleryImageFragment();
                    break;
                case 1:
                    fragment = new GalleryVideoFragment();
                    break;
                default:
                    Log.d("Error:", "Default case reached in GalleryTab");
                    fragment = null;
                    break;
            }

            return fragment;
        }

        /**
         * @return Number of tabs
         */
        @Override
        public int getCount()
        {
            return TAB_COUNT;
        }

        /**
         * Gets tab name from tabTitles array
         */
        @Override
        public CharSequence getPageTitle(int position)
        {
            return tabTitles[position];
        }
    }
}
