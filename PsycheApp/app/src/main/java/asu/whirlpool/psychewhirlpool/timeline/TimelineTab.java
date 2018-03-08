package asu.whirlpool.psychewhirlpool.timeline;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import asu.whirlpool.psychewhirlpool.BottomNavigationViewHelper;
import asu.whirlpool.psychewhirlpool.GameActivity;
import asu.whirlpool.psychewhirlpool.MainActivity;
import asu.whirlpool.psychewhirlpool.R;
import asu.whirlpool.psychewhirlpool.SocialMediaTabs;
import asu.whirlpool.psychewhirlpool.gallery.GalleryTab;

/**
 * TimelineTab implements a tab system to sort the timeline into Past-Present-Future
 * sections for easy navigation.
 *
 * {@link TimelineFragment} is used to create the fragments that are displayed when the User
 * changes tabs.
 *
 * @author  Erick Ramirez Cordero
 * @date    1/2/2018
 */
public class TimelineTab extends FragmentActivity
{
    // Constant strings denoting different phases
    public final static String PHASE_KEY = "Phase";
    public final static char PHASE_A = 'A';
    public final static char PHASE_B = 'B';
    public final static char PHASE_C = 'C';
    public final static char PHASE_D = 'D';
    public final static char PHASE_E = 'E';
    public final static char PHASE_F = 'F';
    public final static char ERROR_CHAR = 'n';

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
                    intent = new Intent(TimelineTab.this, MainActivity.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_timeline:
                    return true;
                case R.id.navigation_gallery:
                    intent = new Intent(TimelineTab.this, GalleryTab.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_social_media:
                    intent = new Intent(TimelineTab.this, SocialMediaTabs.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_game:
                    intent = new Intent(TimelineTab.this, GameActivity.class);
                    startActivity(intent);
                    return true;
            }

            return false;
        }
    };

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
        setContentView(R.layout.activity_timeline_tab);

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
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);
    }

    /**
     * Inflate the menu; this adds items to the action bar if it is present.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_timeline_tab, menu);
        return true;
    }

    /**
     * Handles action bar item clicks.
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
        private String tabTitles[];

        public SectionsPagerAdapter(FragmentManager fm)
        {
            super(fm);
            tabTitles = getApplicationContext().getResources().getStringArray(R.array.phase_letters);
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
            TimelineFragment timeFragment = new TimelineFragment();
            Bundle args = new Bundle();

            switch (position)
            {
                case 0:
                    args.putChar(PHASE_KEY, PHASE_A);
                    break;
                case 1:
                    args.putChar(PHASE_KEY, PHASE_B);
                    break;
                case 2:
                    args.putChar(PHASE_KEY, PHASE_C);
                    break;
                case 3:
                    args.putChar(PHASE_KEY, PHASE_D);
                    break;
                case 4:
                    args.putChar(PHASE_KEY, PHASE_E);
                    break;
                case 5:
                    args.putChar(PHASE_KEY, PHASE_F);
                    break;
                default:
                    Log.d("Error:", "Default case reached in TimelineTab");
                    args.putChar(PHASE_KEY, ERROR_CHAR);
                    break;
            }

            timeFragment.setArguments(args);
            return timeFragment;
        }

        /**
         * @return Number of tabs
         */
        @Override
        public int getCount()
        {
            return tabTitles.length;
        }

        /**
         * Gets Tab name from tabTitles array.
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
