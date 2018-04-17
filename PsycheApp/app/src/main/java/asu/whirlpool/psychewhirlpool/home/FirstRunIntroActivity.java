package asu.whirlpool.psychewhirlpool.home;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import asu.whirlpool.psychewhirlpool.R;

/**
 * Displays a ViewPager with three fragments as the introduction to the app.
 * Only runs the first time that the app is run or after app data is cleared.
 *
 * @author      Natalie Fleischaker
 * @version     3/12/2018
 *
 */
public class FirstRunIntroActivity extends AppCompatActivity {

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
    private int page = 0;
    private Button mNextBtn;
    private Button mSkipBtn;
    private ImageView mFirstDot;
    private ImageView mSecondDot;
    private ImageView mThirdDot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_run_intro);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mNextBtn = (Button) findViewById(R.id.intro_btn_next);
        mSkipBtn = (Button) findViewById(R.id.intro_btn_skip);
        mFirstDot = (ImageView) findViewById(R.id.intro_dot_indicator_0);
        mSecondDot = (ImageView) findViewById(R.id.intro_dot_indicator_1);
        mThirdDot = (ImageView) findViewById(R.id.intro_dot_indicator_2);

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setCurrentItem(page);
        updateIndicators(page);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                page = position;
                updateIndicators(page);

                if (position == 2) {
                    mNextBtn.setText(R.string.intro_nav_buttons_finish);
                } else {
                    mNextBtn.setText(R.string.intro_nav_buttons_next);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (page == 2) {
                    finish();
                } else {
                    page += 1;
                    mViewPager.setCurrentItem(page, true);
                }
            }
        });

        mFirstDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                page = 0;
                mViewPager.setCurrentItem(page, true);
            }
        });

        mSecondDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                page = 1;
                mViewPager.setCurrentItem(page, true);
            }
        });

        mThirdDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                page = 2;
                mViewPager.setCurrentItem(page, true);
            }
        });

        mSkipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_first_run_intro, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    void updateIndicators(int position) {
        ImageView[] indicators = {findViewById(R.id.intro_dot_indicator_0),
                findViewById(R.id.intro_dot_indicator_1),
                findViewById(R.id.intro_dot_indicator_2)};

        for (int i = 0; i < indicators.length; i++) {
            indicators[i].setBackgroundResource(
                    i == position ? R.drawable.dot_indicator_selected :
                            R.drawable.dot_indicator_unselected
            );
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class IntroFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public IntroFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static IntroFragment newInstance(int sectionNumber) {
            IntroFragment fragment = new IntroFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_first_run_intro, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch(position) {
                case 0: return FirstIntroFragment.newInstance(getString(R.string.title_home_large),
                        getString(R.string.title_home_small));
                case 1: return SecondIntroFragment.newInstance(getString(R.string.intro_quote_1),
                        getString(R.string.intro_quote_2),getString(R.string.intro_quote_3),
                        getString(R.string.intro_quote_4),getString(R.string.intro_quote_5));
                case 2: return ThirdIntroFragment.newInstance();
                default: return FirstIntroFragment.newInstance(getString(R.string.title_home_large),
                        getString(R.string.title_home_small));
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "FIRST PAGE";
                case 1:
                    return "SECOND PAGE";
                case 2:
                    return "THIRD PAGE";
            }
            return null;
        }
    }

    protected void displayPage(int position) {
        page = position;
        mViewPager.setCurrentItem(position, true);
    }
}
