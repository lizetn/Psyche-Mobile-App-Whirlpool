package asu.whirlpool.psychewhirlpool;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import asu.whirlpool.psychewhirlpool.timeline.TimelineTab;

public class CountdownActivity extends AppCompatActivity {

    final private int numTimers = 2;  // constant for the desired number of timers

    private TextView mTextMessage;
    private CountDownTimer[] mCountDownTimer = new CountDownTimer[numTimers];
    private int[][] textViewIDs = new int[numTimers][6];
    private TextView textViewYears;
    private TextView textViewMonths;
    private TextView textViewDays;
    private TextView textViewHours;
    private TextView textViewMins;
    private TextView textViewSecs;
    private ImageView mTitleImage;
    //private ConstraintLayout mConstraint;

    private static final String TAG = "CountdownActivity";
    long startDate = System.currentTimeMillis();

    /**
     * Toggle this boolean constant's value to turn on/off logging
     * within the class.
     */
    private static final boolean VERBOSE = true;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Intent intent;

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    intent = new Intent(CountdownActivity.this, MainActivity.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_timeline:
                    intent = new Intent(CountdownActivity.this, TimelineTab.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_gallery:
                    intent = new Intent(CountdownActivity.this, GalleryActivity.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_social_media:
                    intent = new Intent(CountdownActivity.this, SocialMediaTabs.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_game:
                    intent = new Intent(CountdownActivity.this, GameActivity.class);
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
        setContentView(R.layout.activity_countdown);

        if (VERBOSE) Log.v(TAG, "+++ ON CREATE +++");
        startCountdown();

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        BottomNavigationViewHelper.disableAnimation(navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    public void onStart() {
        super.onStart();

        mTitleImage = (ImageView) findViewById(R.id.countdownTitle);
        //mConstraint = (ConstraintLayout) findViewById(R.id.container);

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            mTitleImage.setImageResource(R.drawable.night_countdown_title);
            //mConstraint.setBackgroundResource(R.color.psyche_dark_purple);
        }
        else {
            mTitleImage.setImageResource(R.drawable.white_countdown_title);
            //mConstraint.setBackgroundResource(R.color.tw__composer_white);
        }

        if (VERBOSE) Log.v(TAG, "++ ON START ++");
    }

    /**
     * Starts the countdown.
     */
    private void startCountdown()
    {
        SimpleDateFormat formatter = new SimpleDateFormat("MM.dd.yyyy, HH:mm:ss");
        formatter.setLenient(false);

        String[] endTimes = new String[numTimers];
        endTimes[0] = "06.01.2022, 13:00:00";
        endTimes[1] = "01.01.2026, 06:00:00";
        long[] milliseconds = new long[numTimers];
        Date[] endDates = new Date[numTimers];

        try {
            for (int i = 0; i < milliseconds.length; i++) {
                endDates[i] = formatter.parse(endTimes[i]);
                milliseconds[i] = endDates[i].getTime();
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        fillTextViewIDs();

        /**
         * This needs to be refactored and encapsulated, but the basic functionality is
         * still being figured out, so that will be done later.
         */
        for (int i = 0; i < mCountDownTimer.length; i++) {
            final int index = i;

            mCountDownTimer[i] = new CountDownTimer(milliseconds[i], 1000) {
                @Override
                public void onTick(long millisUntilFinished) {

                    startDate = startDate - 1;
                    Long countdownSeconds = (millisUntilFinished - startDate) / 1000;

                    initTextViews(textViewIDs[index][0], textViewIDs[index][1], textViewIDs[index][2],
                            textViewIDs[index][3], textViewIDs[index][4], textViewIDs[index][5]);

                    Calendar calendar = GregorianCalendar.getInstance();
                    calendar.setTimeInMillis(countdownSeconds * 1000);

                    String yearsLeft = String.format("%d", calendar.get(Calendar.YEAR) - 1970);
                    String monthsLeft = String.format("%d", calendar.get(Calendar.MONTH));

                    /*String daysLeft;
                    long daysLeftNum = countdownSeconds / 86400;
                    if (daysLeftNum > 769)
                        daysLeft = String.format("%d", calendar.get(Calendar.DAY_OF_MONTH) + 1);
                    else
                        daysLeft = String.format("%d", calendar.get(Calendar.DAY_OF_MONTH));*/

                    String daysLeft = String.format("%d", calendar.get(Calendar.DAY_OF_MONTH));
                    String hoursLeft = String.format("%d", (countdownSeconds % 86400) / 3600);
                    String minutesLeft = String.format("%d", ((countdownSeconds % 86400) % 3600) / 60);
                    String secondsLeft = String.format("%d", ((countdownSeconds % 86400) % 3600) % 60);

                    textViewYears.setText(yearsLeft);
                    textViewMonths.setText(monthsLeft);
                    textViewDays.setText(daysLeft);
                    textViewHours.setText(hoursLeft);
                    textViewMins.setText(minutesLeft);
                    textViewSecs.setText(secondsLeft);

                    if (VERBOSE) Log.d("yearsLeft",yearsLeft);
                    if (VERBOSE) Log.d("monthsLeft",monthsLeft);
                    if (VERBOSE) Log.d("daysLeft",daysLeft);
                    if (VERBOSE) Log.d("hoursLeft",hoursLeft);
                    if (VERBOSE) Log.d("minutesLeft",minutesLeft);
                    if (VERBOSE) Log.d("secondsLeft",secondsLeft);
                }

                @Override
                public void onFinish() {

                }
            };
        }
        /* This is being kept so that parts of it may be used to find a better timer method
            if necessary

        mCountDownTimer1 = new CountDownTimer(milliseconds1, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                startDate = startDate - 1;
                Long countdownSeconds = (millisUntilFinished - startDate) / 1000;

                initTextViews(R.id.textViewYearsA, R.id.textViewMonthsA, R.id.textViewDaysA,
                        R.id.textViewHoursA, R.id.textViewMinutesA, R.id.textViewSecondsA);

                *//*String daysLeft = String.format("%d", countdownSeconds / 86400);
                textViewDays.setText(daysLeft);
                if (VERBOSE) Log.d("daysLeft",daysLeft);

                String hoursLeft = String.format("%d", (countdownSeconds % 86400) / 3600);
                textViewHours.setText(hoursLeft);
                if (VERBOSE) Log.d("hoursLeft",hoursLeft);

                String minutesLeft = String.format("%d", ((countdownSeconds % 86400) % 3600) / 60);
                textViewMins.setText(minutesLeft);
                if (VERBOSE) Log.d("minutesLeft",minutesLeft);

                String secondsLeft = String.format("%d", ((countdownSeconds % 86400) % 3600) % 60);
                textViewSecs.setText(secondsLeft);
                if (VERBOSE) Log.d("secondsLeft",secondsLeft);*//*

                Calendar calendar = GregorianCalendar.getInstance();
                calendar.setTimeInMillis(countdownSeconds * 1000);

                textViewYears.setText(String.format("%d", calendar.get(Calendar.YEAR) - 1970));
                textViewMonths.setText(String.format("%d", calendar.get(Calendar.MONTH)));
                textViewDays.setText(String.format("%d", calendar.get(Calendar.DAY_OF_MONTH)));
                textViewHours.setText(String.format("%d", calendar.get(Calendar.HOUR_OF_DAY)));
                textViewMins.setText(String.format("%d", calendar.get(Calendar.MINUTE)));
                textViewSecs.setText(String.format("%d",calendar.get(Calendar.SECOND)));

                *//*Calendar startCalendar = GregorianCalendar.getInstance();
                Calendar endCalendar = GregorianCalendar.getInstance();
                endCalendar.setTimeInMillis(millisUntilFinished);*//*

                *//*String yearsLeft = String.format("%d", );
                textViewYears.setText();

                textViewMonths.setText();
                textViewDays.setText();
                textViewHours.setText();
                textViewMins.setText();
                textViewSecs.setText();*//*
            }

            @Override
            public void onFinish() {

            }
        };
        */

        for (int j = 0; j < mCountDownTimer.length; j++) {
            mCountDownTimer[j].start();
        }
    }

    /**
     * Cancels the timer to avoid memory leaks. Should be used whenever the
     * class is destroyed.
     */
    void cancelTimers() {
        for (int i = 0; i < mCountDownTimer.length; i++) {
            if(mCountDownTimer[i] != null)
                mCountDownTimer[i].cancel();
        }
    }

    @Override
    public void onPause() {
        cancelTimers();
        super.onPause();
        if (VERBOSE) Log.v(TAG, "- ON PAUSE -");
    }

    @Override
    public void onStop() {
        cancelTimers();
        super.onStop();
        Log.v(TAG, "-- ON STOP --");
    }

    @Override
    public void onDestroy() {
        cancelTimers();
        super.onDestroy();
        if (VERBOSE) Log.v(TAG, "- ON DESTROY -");
    }

    /**
     * Initializes all of the TextViews by id so that they can be updated
     * with the correct values once the timer is started.
     */
    private void initTextViews(int years, int months, int days, int hours, int minutes, int seconds) {
        textViewYears = (TextView) findViewById(years);
        textViewMonths = (TextView) findViewById(months);
        textViewDays = (TextView) findViewById(days);
        textViewHours = (TextView) findViewById(hours);
        textViewMins = (TextView) findViewById(minutes);
        textViewSecs = (TextView) findViewById(seconds);
    }

    /**
     * Fills the textViewIDs array with the necessary numbers from all of the TextViews
     */
    private void fillTextViewIDs() {
        textViewIDs[0][0] = R.id.textViewYearsA;
        textViewIDs[0][1] = R.id.textViewMonthsA;
        textViewIDs[0][2] = R.id.textViewDaysA;
        textViewIDs[0][3] = R.id.textViewHoursA;
        textViewIDs[0][4] = R.id.textViewMinutesA;
        textViewIDs[0][5] = R.id.textViewSecondsA;
        textViewIDs[1][0] = R.id.textViewYears2;
        textViewIDs[1][1] = R.id.textViewMonths2;
        textViewIDs[1][2] = R.id.textViewDays2;
        textViewIDs[1][3] = R.id.textViewHours2;
        textViewIDs[1][4] = R.id.textViewMinutes2;
        textViewIDs[1][5] = R.id.textViewSeconds2;
    }
}
