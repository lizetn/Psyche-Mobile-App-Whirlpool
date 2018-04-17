package asu.whirlpool.psychewhirlpool;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import asu.whirlpool.psychewhirlpool.InstagramClasses.MainInstagramActivity;
import asu.whirlpool.psychewhirlpool.facebookClasses.FacebookActivity;
import asu.whirlpool.psychewhirlpool.gallery.GalleryTab;
import asu.whirlpool.psychewhirlpool.timeline.TimelineTab;

public class SocialMediaTabs extends FragmentActivity implements android.app.ActionBar.TabListener
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
    public static String POSITION = "POSITION";
    private static final String TAG = "SocialMediaTabs";

    // configure icons
    private int[] imageResId = {
            R.drawable.tw__composer_logo_blue,
            R.drawable.com_facebook_button_icon_blue,
            R.drawable.insta };

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
                    intent = new Intent(SocialMediaTabs.this, MainActivity.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_timeline:
                    intent = new Intent(SocialMediaTabs.this, TimelineTab.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_gallery:
                    intent = new Intent(SocialMediaTabs.this, GalleryTab.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_social_media:
                    return true;
                case R.id.navigation_game:
                    intent = new Intent(SocialMediaTabs.this, GameActivity.class);
                    startActivity(intent);
                    return true;
            }
            return false;
        }
    };

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.PsycheDarkTheme);
        } else {
            setTheme(R.style.PsycheLightTheme);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_media_tabs);

        //setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(),
                SocialMediaTabs.this);

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        // Iterate over all tabs and set the tab icons
        for (int i = 0; i < imageResId.length; i++) {
            tabLayout.getTabAt(i).setIcon(imageResId[i]);
        }

        // Implementation of navigation bar, and resizing of icons
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
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
        MenuItem menuItem = menu.getItem(3);
        menuItem.setChecked(true);
    }

    @Override
    public void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putInt(POSITION, tabLayout.getSelectedTabPosition());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState)
    {
        super.onRestoreInstanceState(savedInstanceState);
        mViewPager.setCurrentItem(savedInstanceState.getInt(POSITION));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_social_media_tabs, menu);
        return true;
    }

    /**
     * Handle action bar item clicks here. The action bar will
     * automatically handle clicks on the Home/Up button, so long
     * as you specify a parent activity in AndroidManifest.xml.
     * @param item
     * @return
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

    @Override
    public void onTabSelected(android.app.ActionBar.Tab tab, android.app.FragmentTransaction fragmentTransaction)
    {
//        if(tab.getPosition() == 1)
//        {
//            SocialMediaActivity sm = new SocialMediaActivity();
//            getSupportFragmentManager().beginTransaction().replace(R.id.Container,sm).commit();
//        }
    }

    @Override
    public void onTabUnselected(android.app.ActionBar.Tab tab, android.app.FragmentTransaction fragmentTransaction) {

    }

    @Override
    public void onTabReselected(android.app.ActionBar.Tab tab, android.app.FragmentTransaction fragmentTransaction) {

    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment
    {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        private int mSection;

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            mSection = getArguments().getInt(ARG_SECTION_NUMBER);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_social_media_tabs, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, mSection));
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        final int PAGE_COUNT = 3;
        private String tabTitles[] = new String[] { "Twitter", "Facebook", "Instagram" };
        private Context context;

        public SectionsPagerAdapter(FragmentManager fm, Context context)
        {
            super(fm);
            this.context = context;
        }

        /**
         * getItem is called to instantiate the fragment for the given page.
         * Return a IntroFragment (defined as a static inner class below).
         * @param position
         * @return
         */
        @Override
        public Fragment getItem(int position)
        {
            switch (position)
            {
                case 0:
                    TwitterActivity twitter = new TwitterActivity();
                    return twitter;
//                    return IntroFragment.newInstance(position + 1);
                case 1:
                    FacebookActivity facebook = new FacebookActivity();
                    return facebook;
                case 2:
                    MainInstagramActivity instagramActivity;

                    instagramActivity = new MainInstagramActivity();
                    return instagramActivity;
                default:
                    return null;
            }
        }

        /**
         * Show number of pages
         * @return
         */
        @Override
        public int getCount()
        {
            return PAGE_COUNT;
        }

        @Override
        public CharSequence getPageTitle(int position)
        {
            return tabTitles[position];
        }
    }
}
