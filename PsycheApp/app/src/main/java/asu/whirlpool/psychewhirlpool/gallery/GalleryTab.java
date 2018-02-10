package asu.whirlpool.psychewhirlpool.gallery;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

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
 * {@link GalleryImageFragment} and GalleryVideoFragment are used to create the fragments
 * that are displayed when the User navigates through the Gallery.
 *
 * @author  Erick Ramirez Cordero
 * @date    1/25/2018
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

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
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

        //noinspection SimplifiableIfStatement
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
         * Gets tab name from tabTitles array.
         * @param position
         * @return
         */
        @Override
        public CharSequence getPageTitle(int position)
        {
            return tabTitles[position];
        }
    }
}
