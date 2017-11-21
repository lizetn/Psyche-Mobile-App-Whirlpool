package asu.whirlpool.psychewhirlpool.timeline;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import asu.whirlpool.psychewhirlpool.R;

import static asu.whirlpool.psychewhirlpool.timeline.TimelineActivity.PHASE_SELECTED;

public class TimePhaseActivity extends AppCompatActivity
{
    private int phase;
    private int phaseColor;
    private LinearLayout buttonLayout;
    private int nodeCount;
    private ArrayList<String> phaseInfo;

    private TextView mTextMessage;

    // Constants
    private final String DEBUG_TAG = TimePhaseActivity.class.getSimpleName();
    private final String ERROR_MSG = "ERROR!";
    private final String PHASE_FILE = "SamplePhaseInfo.txt";

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener()
    {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item)
        {
            switch (item.getItemId())
            {
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
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_phase);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        buttonLayout = (LinearLayout) findViewById(R.id.nodeLayout);
        nodeCount = buttonLayout.getChildCount();
        phaseInfo = new ArrayList<String>();

        initPhaseButtons();
        initPhaseInfo();
    }

    /**
     * Initializes the image buttons used in {@link TimePhaseActivity}.
     */
    private void initPhaseButtons()
    {
        // Get phase selected from intent
        Intent intent = getIntent();
        phase = intent.getIntExtra(PHASE_SELECTED, 0);
        //Log.d(DEBUG_TAG, "Phase found: " + phase);

        switch (phase)
        {
            case 1:
                phaseColor = R.color.psyche_mustard;
                break;
            case 2:
                phaseColor = R.color.psyche_gold;
                break;
            case 3:
                phaseColor = R.color.psyche_coral;
                break;
            case 4:
                phaseColor = R.color.psyche_magenta;
                break;
            case 5:
                phaseColor = R.color.psyche_purple;
                break;
            case 6:
                phaseColor = R.color.psyche_dark_purple;
                break;
            case 7:
                phaseColor = R.color.psyche_black;
                break;
            default:
                phaseColor = R.color.psyche_black;
                break;
        }

        // Change color of nodes to match phase color
        Button phaseButton = null;

        for(int index = 0; index < nodeCount; index++)
        {
            phaseButton = (Button) buttonLayout.getChildAt(index);
            phaseButton.setBackgroundColor(getResources().getColor(phaseColor));
        }
    }

    /**
     * Fetches information about the phase selected and initializes ArrayList holding
     * facts.
     */
    private void initPhaseInfo()
    {
        String phaseText;
        try
        {
            InputStreamReader streamReader = new InputStreamReader(getAssets().open(PHASE_FILE));
            BufferedReader bufferedReader = new BufferedReader(streamReader);

            while ((phaseText = bufferedReader.readLine()) != null)
            {
                phaseInfo.add(phaseText + "\n");
            }
        }
        catch (IOException e)
        {
            mTextMessage.setText(ERROR_MSG);
            Log.d(DEBUG_TAG, e.getMessage());
        }
    }

    /**
     *
     * @param view
     */
    public void onClick(View view)
    {
        try
        {
            switch(view.getId())
            {
                case R.id.timeNode0:
                    mTextMessage.setText(phaseInfo.get(0));
                    break;
                case R.id.timeNode1:
                    mTextMessage.setText(phaseInfo.get(1));
                    break;
                case R.id.timeNode2:
                    mTextMessage.setText(phaseInfo.get(2));
                    break;
                case R.id.timeNode3:
                    mTextMessage.setText(phaseInfo.get(3));
                    break;
                case R.id.timeNode4:
                    mTextMessage.setText(phaseInfo.get(4));
                    break;
            }
        }
        catch(Exception e)
        {
            mTextMessage.setText(ERROR_MSG);
        }
    }
}
