package asu.whirlpool.psychewhirlpool;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.github.lzyzsd.circleprogress.DonutProgress;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import asu.whirlpool.psychewhirlpool.gallery.GalleryTab;
import asu.whirlpool.psychewhirlpool.timeline.TimelineTab;

public class CountdownActivity extends AppCompatActivity {

    final private int numTimers = 1;  // constant for the desired number of timers

    private TextView mTextMessage;
    private CountDownTimer[] mCountDownTimer = new CountDownTimer[numTimers];
    private int[][] textViewIDs = new int[numTimers][6];
    private DonutProgress textViewYears;
    private DonutProgress textViewMonths;
    private DonutProgress textViewDays;
    private DonutProgress textViewHours;
    private DonutProgress textViewMins;
    private DonutProgress textViewSecs;
    private ImageView mTitleImage;
    private TextView titleMessage;
    private TextView yearMessage;
    private TextView monthMessage;
    private TextView dayMessage;
    private TextView hourMessage;
    private TextView minutesMessage;
    private TextView secondMessage;

    String[] endTimes;

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
                    intent = new Intent(CountdownActivity.this, GalleryTab.class);
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
        textViewYears = (DonutProgress) findViewById(R.id.textViewYearsA);
        textViewMonths = (DonutProgress) findViewById(R.id.textViewMonthsA);
        textViewDays = (DonutProgress) findViewById(R.id.textViewDaysA);
        textViewHours = (DonutProgress) findViewById(R.id.textViewHoursA);
        textViewMins = (DonutProgress) findViewById(R.id.textViewMinutesA);
        textViewSecs = (DonutProgress) findViewById(R.id.textViewSecondsA);

        textViewSecs.setUnfinishedStrokeWidth((float) 10.0);
        textViewSecs.setFinishedStrokeWidth((float) 10.0);

        textViewYears.setFinishedStrokeWidth((float) 10.00);
        textViewYears.setUnfinishedStrokeWidth((float) 10.0);


        textViewMonths.setFinishedStrokeWidth((float) 10.0);
        textViewMonths.setUnfinishedStrokeWidth((float) 10.0);

        textViewDays.setUnfinishedStrokeWidth((float) 10.0);
        textViewDays.setFinishedStrokeWidth((float) 10.0);

        textViewHours.setFinishedStrokeWidth((float) 10.0);
        textViewHours.setUnfinishedStrokeWidth((float) 10.0);

        textViewMins.setFinishedStrokeWidth((float) 10.0);
        textViewMins.setUnfinishedStrokeWidth((float) 10.0);

        endTimes = new String[numTimers];
        Log.d("BEFORE", "onCreate: ");
        endTimes[0] = "11.30.2027, 13:00:00";
        if (VERBOSE) Log.v(TAG, "+++ ON CREATE +++");

        mTextMessage = (TextView) findViewById(R.id.message);
        titleMessage = (TextView) findViewById(R.id.defaultClockTextView);
        titleMessage.setText("Phase F: Mission Closeout");

        yearMessage = (TextView) findViewById(R.id.yearlabel);
        monthMessage = (TextView) findViewById(R.id.monthlabel);
        dayMessage = (TextView) findViewById(R.id.daylabel);
        hourMessage = (TextView) findViewById(R.id.hourslabel);
        minutesMessage = (TextView) findViewById(R.id.minutelabel);
        secondMessage = (TextView) findViewById(R.id.secondlabel);

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            yearMessage.setTextColor(Color.WHITE);
            monthMessage.setTextColor(Color.WHITE);;
            dayMessage.setTextColor(Color.WHITE);;
            hourMessage.setTextColor(Color.WHITE);;
            minutesMessage.setTextColor(Color.WHITE);;
            secondMessage.setTextColor(Color.WHITE);;

        }
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        BottomNavigationViewHelper.disableAnimation(navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Spinner dynamicSpinner = (Spinner) findViewById(R.id.dynamic_spinner);

        String[] items = new String[] { "Phase A: Concept Study", "Phase B:Preliminary Design", "Phase C: Critical Design","Phase D: Instrument & Spacecraft Build","Phase E: Mars Gravity Assist","Phase F: Mission Closeout" };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, items);

        dynamicSpinner.setAdapter(adapter);

        dynamicSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id)
            {

                switch (position)
                {
                    case 0:
                        titleMessage.setText("Phase A: Completed");
                        textViewYears.setText("0");
                        textViewMonths.setText("0");
                        textViewDays.setText("0");
                        textViewHours.setText("0");
                        textViewMins.setText("0");
                        textViewSecs.setText("0");

                        if(mCountDownTimer[0] != null) {
                            mCountDownTimer[0].cancel();
                        }
                        break;
                        /*
                        textViewYears.setVisibility(View.INVISIBLE);
                        textViewMonths.setVisibility(View.INVISIBLE);
                        textViewDays.setVisibility(View.INVISIBLE);
                        textViewHours.setVisibility(View.INVISIBLE);
                        textViewMins.setVisibility(View.INVISIBLE);
                        textViewSecs.setVisibility(View.INVISIBLE);*/

                       /* endTime1 = "01.01.2026, 06:00:00";
                        mCountDownTimer1.cancel();
                        startCountdown();*/

                    case 1:
                        titleMessage.setText("Phase B: Is in progress");
                        textViewYears.setText("0");
                        textViewMonths.setText("0");
                        textViewDays.setText("0");
                        textViewHours.setText("0");
                        textViewMins.setText("0");
                        textViewSecs.setText("0");

                        if(mCountDownTimer[0] != null) {
                            mCountDownTimer[0].cancel();
                        }
                        /*
                        textViewYears.setVisibility(View.INVISIBLE);
                        textViewMonths.setVisibility(View.INVISIBLE);
                        textViewDays.setVisibility(View.INVISIBLE);
                        textViewHours.setVisibility(View.INVISIBLE);
                        textViewMins.setVisibility(View.INVISIBLE);
                        textViewSecs.setVisibility(View.INVISIBLE);*/
                        /*
                        endTime1 = "05.31.2019, 13:00:00";
                        mCountDownTimer1.cancel();
                        startCountdown();*/
                        break;
                    case 2:
                        titleMessage.setText("Phase C: Critical Design & Build");
                        endTimes[0] = "05.30.2019, 13:00:00";
                        if(mCountDownTimer[0] != null) {
                            mCountDownTimer[0].cancel();
                        }
                        startCountdown();
                        break;
                    case 3:
                        titleMessage.setText("Phase D: Instrument & Spacecraft Build, Ship & Launch");
                        endTimes[0] = "01.30.2021, 13:00:00";
                        if(mCountDownTimer[0] != null) {
                            mCountDownTimer[0].cancel();
                        }
                        startCountdown();
                        break;
                    case 4:
                        titleMessage.setText("Phase E: Gravity Assist, Arrival, & Orbit");
                        endTimes[0] = "05.30.2023, 13:00:00";
                        if(mCountDownTimer[0] != null) {
                            mCountDownTimer[0].cancel();
                        }
                        startCountdown();
                        break;
                    case 5:
                        titleMessage.setText("Phase F: Mission Closeout");
                        endTimes[0] = "11.30.2027, 13:00:00";
                        if(mCountDownTimer[0] != null) {
                            mCountDownTimer[0].cancel();
                        }
                        startCountdown();
                        break;
                    case 6:
                        break;
                    default:
                        titleMessage.setText("Phase F: Mission Closeout");
                        endTimes[0] = "11.30.2027, 13:00:00";
                        if(mCountDownTimer[0] != null) {
                            mCountDownTimer[0].cancel();
                        }
                        startCountdown();
                        break;

                }
                Log.v("item", (String) parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            titleMessage.setTextColor(Color.parseColor("#FFFFFF"));
            textViewYears.setFinishedStrokeColor(Color.parseColor("#FF8C00"));
            textViewYears.setInnerBackgroundColor(Color.parseColor("#302144"));
            textViewYears.setTextColor(Color.parseColor("#FFFFFF"));
            textViewYears.setUnfinishedStrokeColor(Color.parseColor("#592651"));

            textViewMonths.setFinishedStrokeColor(Color.parseColor("#FF8C00"));
            textViewMonths.setInnerBackgroundColor(Color.parseColor("#302144"));
            textViewMonths.setTextColor(Color.parseColor("#FFFFFF"));
            textViewMonths.setUnfinishedStrokeColor(Color.parseColor("#592651"));

            textViewHours.setFinishedStrokeColor(Color.parseColor("#FF8C00"));
            textViewHours.setInnerBackgroundColor(Color.parseColor("#302144"));
            textViewHours.setTextColor(Color.parseColor("#FFFFFF"));
            textViewHours.setUnfinishedStrokeColor(Color.parseColor("#592651"));

            textViewDays.setFinishedStrokeColor(Color.parseColor("#FF8C00"));
            textViewDays.setInnerBackgroundColor(Color.parseColor("#302144"));
            textViewDays.setTextColor(Color.parseColor("#FFFFFF"));
            textViewDays.setUnfinishedStrokeColor(Color.parseColor("#592651"));

            textViewMins.setFinishedStrokeColor(Color.parseColor("#FF8C00"));
            textViewMins.setInnerBackgroundColor(Color.parseColor("#302144"));
            textViewMins.setTextColor(Color.parseColor("#FFFFFF"));
            textViewMins.setUnfinishedStrokeColor(Color.parseColor("#592651"));

            textViewSecs.setFinishedStrokeColor(Color.parseColor("#FF8C00"));
            textViewSecs.setInnerBackgroundColor(Color.parseColor("#302144"));
            textViewSecs.setTextColor(Color.parseColor("#FFFFFF"));
            textViewSecs.setUnfinishedStrokeColor(Color.parseColor("#592651"));

        }

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

       // String[] endTimes = new String[numTimers];
        //endTimes[0] = "06.01.2022, 13:00:00";
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

        //fillTextViewIDs();

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

                   // initTextViews(textViewIDs[index][0], textViewIDs[index][1], textViewIDs[index][2],
                         //   textViewIDs[index][3], textViewIDs[index][4], textViewIDs[index][5]);

                    Calendar calendar = GregorianCalendar.getInstance();
                    calendar.setTimeInMillis(countdownSeconds * 1000);

                    textViewYears.setText(String.format("%d", calendar.get(Calendar.YEAR) - 1970));
                    int progs = ((calendar.get(Calendar.YEAR) - 1970));
                    double nm = (1.0 / progs);
                    double sum = (nm * 100.0);
                    int yr = (int) sum;
                    Log.d(String.valueOf(yr), "onTick: YEAR ");
                    textViewYears.setDonut_progress(String.valueOf(yr));

                    int progs2 = ((calendar.get(Calendar.MONTH)));
                    double nm2 = (progs2 / 12.0);
                    double sum2 = (nm2 * 100.0);
                    Log.d(String.valueOf(sum2), "onTick: sss");
                    int mths = (int) sum2;
                    Log.d(String.valueOf(mths), "onTick: ");
                    textViewMonths.setDonut_progress(String.valueOf(mths));


                    int progs3 = ((calendar.get(Calendar.DAY_OF_MONTH)));
                    double nm3 = (progs3 / 31.0);
                    double sum3 = (nm3 * 100.0);
                    Log.d(String.valueOf(sum3), "onTick: sss");
                    int dys = (int) sum3;
                    Log.d(String.valueOf(dys), "onTick: ");
                    textViewDays.setDonut_progress(String.valueOf(dys));

                    int progs4 = ((calendar.get(Calendar.HOUR_OF_DAY)));
                    double nm4 = (progs4 / 24.0);
                    double sum4 = (nm4 * 100.0);
                    Log.d(String.valueOf(sum4), "onTick: sss");
                    int hrs = (int) sum4;
                    Log.d(String.valueOf(hrs), "onTick: ");
                    textViewHours.setDonut_progress(String.valueOf(hrs));

                    int progs5 = ((calendar.get(Calendar.MINUTE)));
                    double nm5 = (progs5 / 60.0);
                    double sum5 = (nm5 * 100.0);
                    Log.d(String.valueOf(sum5), "onTick: sss");
                    int mins = (int) sum5;
                    Log.d(String.valueOf(mins), "onTick: ");
                    textViewMins.setDonut_progress(String.valueOf(mins));


                    int progs6 = ((calendar.get(Calendar.SECOND)));
                    double nm6 = (progs6 / 60.0);
                    double sum6 = (nm6 * 100.0);
                    Log.d(String.valueOf(sum6), "onTick: sss");
                    int secs = (int) sum6;
                    Log.d(String.valueOf(secs), "onTick: ");
                    textViewSecs.setDonut_progress(String.valueOf(secs));
                    Log.d(textViewSecs.getText(), "onTick: SECCCCSSS");

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
        textViewYears = (DonutProgress) findViewById(years);
        textViewMonths = (DonutProgress) findViewById(months);
        textViewDays = (DonutProgress) findViewById(days);
        textViewHours = (DonutProgress) findViewById(hours);
        textViewMins = (DonutProgress) findViewById(minutes);
        textViewSecs = (DonutProgress) findViewById(seconds);
    }

    /**
     * Fills the textViewIDs array with the necessary numbers from all of the TextViews
     */
    /*
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
    }*/
}
