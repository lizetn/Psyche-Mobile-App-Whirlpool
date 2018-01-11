package asu.whirlpool.psychewhirlpool;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import asu.whirlpool.psychewhirlpool.timeline.TimelineActivity;

public class CountdownActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private CountDownTimer mCountDownTimer1;
    private CountDownTimer mCountDownTimer2;
    private TextView textViewYears;
    private TextView textViewMonths;
    private TextView textViewDays;
    private TextView textViewHours;
    private TextView textViewMins;
    private TextView textViewSecs;
    private ImageView mTitleImage;
    private ConstraintLayout mConstraint;
    private boolean nightMode;

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
                    intent = new Intent(CountdownActivity.this, TimelineActivity.class);
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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countdown);

        nightMode = getIntent().getExtras().getBoolean("nightMode");
        mTitleImage = (ImageView) findViewById(R.id.countdownTitle);
        mConstraint = (ConstraintLayout) findViewById(R.id.container);

        if (nightMode) {
            mTitleImage.setImageResource(R.drawable.night_countdown_title);
            mConstraint.setBackgroundResource(R.color.psyche_dark_purple);
        }
        else {
            mTitleImage.setImageResource(R.drawable.white_countdown_title);
            mConstraint.setBackgroundResource(R.color.tw__composer_white);
        }

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
        if (VERBOSE) Log.v(TAG, "++ ON START ++");
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
     * Starts the countdown.
     */
    private void startCountdown()
    {
        SimpleDateFormat formatter = new SimpleDateFormat("MM.dd.yyyy, HH:mm:ss");
        formatter.setLenient(false);

        String endTime1 = "06.01.2022, 13:00:00";
        String endTime2 = "01.01.2026, 06:00:00";
        long milliseconds1 = 0;
        long milliseconds2 = 0;

        Date endDate1;
        Date endDate2;

        try {
            endDate1 = formatter.parse(endTime1);
            milliseconds1 = endDate1.getTime();
            endDate2 = formatter.parse(endTime2);
            milliseconds2 = endDate2.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        /**
         * This needs to be refactored and encapsulated, but the basic functionality is
         * still being figured out, so that will be done later.
         */
        mCountDownTimer1 = new CountDownTimer(milliseconds1, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                startDate = startDate - 1;
                Long countdownSeconds = (millisUntilFinished - startDate) / 1000;

                initTextViews(R.id.textViewYearsA, R.id.textViewMonthsA, R.id.textViewDaysA,
                        R.id.textViewHoursA, R.id.textViewMinutesA, R.id.textViewSecondsA);

                /*String daysLeft = String.format("%d", countdownSeconds / 86400);
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
                if (VERBOSE) Log.d("secondsLeft",secondsLeft);*/

                Calendar calendar = GregorianCalendar.getInstance();
                calendar.setTimeInMillis(countdownSeconds * 1000);

                textViewYears.setText(String.format("%d", calendar.get(Calendar.YEAR) - 1970));
                textViewMonths.setText(String.format("%d", calendar.get(Calendar.MONTH)));
                textViewDays.setText(String.format("%d", calendar.get(Calendar.DAY_OF_MONTH)));
                textViewHours.setText(String.format("%d", calendar.get(Calendar.HOUR_OF_DAY)));
                textViewMins.setText(String.format("%d", calendar.get(Calendar.MINUTE)));
                textViewSecs.setText(String.format("%d",calendar.get(Calendar.SECOND)));

                /*Calendar startCalendar = GregorianCalendar.getInstance();
                Calendar endCalendar = GregorianCalendar.getInstance();
                endCalendar.setTimeInMillis(millisUntilFinished);*/

                /*String yearsLeft = String.format("%d", );
                textViewYears.setText();

                textViewMonths.setText();
                textViewDays.setText();
                textViewHours.setText();
                textViewMins.setText();
                textViewSecs.setText();*/
            }

            @Override
            public void onFinish() {

            }
        };
        mCountDownTimer2 = new CountDownTimer(milliseconds2, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                startDate = startDate - 1;
                Long countdownSeconds = (millisUntilFinished - startDate) / 1000;

                initTextViews(R.id.textViewYears2, R.id.textViewMonths2, R.id.textViewDays2,
                        R.id.textViewHours2, R.id.textViewMinutes2, R.id.textViewSeconds2);

                Calendar calendar = GregorianCalendar.getInstance();
                calendar.setTimeInMillis(countdownSeconds * 1000);

                textViewYears.setText(String.format("%d", calendar.get(Calendar.YEAR) - 1970));
                textViewMonths.setText(String.format("%d", calendar.get(Calendar.MONTH)));
                textViewDays.setText(String.format("%d", calendar.get(Calendar.DAY_OF_MONTH)));
                textViewHours.setText(String.format("%d", calendar.get(Calendar.HOUR_OF_DAY)));
                textViewMins.setText(String.format("%d", calendar.get(Calendar.MINUTE)));
                textViewSecs.setText(String.format("%d",calendar.get(Calendar.SECOND)));
            }

            @Override
            public void onFinish() {

            }
        };
        mCountDownTimer1.start();
        mCountDownTimer2.start();
    }

    /**
     * Cancels the timer to avoid memory leaks. Should be used whenever the
     * class is destroyed.
     */
    void cancelTimer() {
        if(mCountDownTimer1 != null)
            mCountDownTimer1.cancel();
    }

    @Override
    public void onPause() {
        cancelTimer();
        super.onPause();
        if (VERBOSE) Log.v(TAG, "- ON PAUSE -");
    }

    @Override
    public void onStop() {
        cancelTimer();
        super.onStop();
        Log.v(TAG, "-- ON STOP --");
    }

    @Override
    public void onDestroy() {
        cancelTimer();
        super.onDestroy();
        if (VERBOSE) Log.v(TAG, "- ON DESTROY -");
    }
}
