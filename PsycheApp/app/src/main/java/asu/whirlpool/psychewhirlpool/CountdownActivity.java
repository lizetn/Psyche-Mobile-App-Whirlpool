package asu.whirlpool.psychewhirlpool;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CountdownActivity extends AppCompatActivity {

    private TextView mTextMessage;
    CountDownTimer mCountDownTimer;
    private TextView textViewDays;
    private TextView textViewHours;
    private TextView textViewMins;
    private TextView textViewSecs;

    private static final String TAG = "CountdownActivity";

    /**
     * Toggle this boolean constant's value to turn on/off logging
     * within the class.
     */
    private static final boolean VERBOSE = true;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countdown);
        if (VERBOSE) Log.v(TAG, "+++ ON CREATE +++");
        initTextViews();
        startCountdown();

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (VERBOSE) Log.v(TAG, "++ ON START ++");
    }

    long startDate = System.currentTimeMillis();

    /**
     * Initializes all of the TextViews by id so that they can be updated
     * with the correct values once the timer is started.
     */
    private void initTextViews() {
        textViewDays = (TextView) findViewById(R.id.textViewDays);
        textViewHours = (TextView) findViewById(R.id.textViewHours);
        textViewMins = (TextView) findViewById(R.id.textViewMinutes);
        textViewSecs = (TextView) findViewById(R.id.textViewSeconds);
    }


    /**
     * Starts the countdown. initTextViews must be used first for the TextViews
     * to be updated properly.
     */
    private void startCountdown()
    {
        SimpleDateFormat formatter = new SimpleDateFormat("MM.dd.yyyy, HH:mm:ss");
        formatter.setLenient(false);

        String endTime = "01.01.2022, 13:00:00";
        long milliseconds = 0;

        final CountDownTimer mCountDownTimer;

        Date endDate;
        try {
            endDate = formatter.parse(endTime);
            milliseconds = endDate.getTime();

        } catch (ParseException e) {
            e.printStackTrace();
        }

        /**
         * mCountDownTimer currently does not implement years or months. Years could be
         * done easily with the only problem being leap year.
         */
        mCountDownTimer = new CountDownTimer(milliseconds, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                startDate = startDate - 1;
                Long countdownSeconds = (millisUntilFinished - startDate) / 1000;

                String daysLeft = String.format("%d", countdownSeconds / 86400);
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
                if (VERBOSE) Log.d("secondsLeft",secondsLeft);
            }

            @Override
            public void onFinish() {

            }
        };
        mCountDownTimer.start();
    }

    /**
     * Cancels the timer to avoid memory leaks. Should be used whenever the
     * class is destroyed.
     *
     * Currently having an issue with this not being called because the
     * mCountDownTimer pointer is null.
     */
    void cancelTimer() {
        if(mCountDownTimer != null)
            mCountDownTimer.cancel();
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
