package asu.whirlpool.psychewhirlpool.timeline;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import asu.whirlpool.psychewhirlpool.BottomNavigationViewHelper;
import asu.whirlpool.psychewhirlpool.CountdownActivity;
import asu.whirlpool.psychewhirlpool.GalleryActivity;
import asu.whirlpool.psychewhirlpool.GameActivity;
import asu.whirlpool.psychewhirlpool.MainActivity;
import asu.whirlpool.psychewhirlpool.R;
import asu.whirlpool.psychewhirlpool.SocialMediaTabs;

import static asu.whirlpool.psychewhirlpool.timeline.TimelineActivity.PHASE_SELECTED;

public class TimePhaseActivity extends AppCompatActivity
{
    private int phase;
    private int phaseColor;
    private ConstraintLayout buttonLayout;
    private int nodeCount;
    private ArrayList<String> phaseInfo;

    private TextView mTextMessage;
    private TextView infoText;

    // Constants
    private final String DEBUG_TAG = TimePhaseActivity.class.getSimpleName();
    private final String ERROR_MSG = "ERROR!";
    private final String PHASE_FILE = "SamplePhaseInfo.txt";

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Intent intent;

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    intent = new Intent(TimePhaseActivity.this, MainActivity.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_timeline:
                    intent = new Intent(TimePhaseActivity.this, TimelineActivity.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_gallery:
                    intent = new Intent(TimePhaseActivity.this, GalleryActivity.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_social_media:
                    intent = new Intent(TimePhaseActivity.this, SocialMediaTabs.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_game:
                    intent = new Intent(TimePhaseActivity.this, GameActivity.class);
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
        setContentView(R.layout.activity_time_phase);

        mTextMessage = findViewById(R.id.textView);
        infoText = findViewById(R.id.infoText);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        BottomNavigationViewHelper.disableAnimation(navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        buttonLayout = findViewById(R.id.NodeLayout);
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
        int colorId = getResources().getColor(phaseColor);
        infoText.setBackgroundColor(colorId);
        Button phaseButton = null;

        for(int index = 0; index < nodeCount; index++)
        {
            phaseButton = (Button) buttonLayout.getChildAt(index);
            phaseButton.setBackgroundColor(colorId);
        }
    }

    /**
     * Fetches information about the phase selected and initializes ArrayList holding
     * facts.
     * TODO: Replace text files with XML files
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
     * Upon clicking a timeNode, display the matching information.
     * @param view
     */
    public void onClick(View view)
    {
        try
        {
            switch(view.getId())
            {
                case R.id.timeNode0:
                    infoText.setText(phaseInfo.get(0));
                    break;
                case R.id.timeNode1:
                    infoText.setText(phaseInfo.get(1));
                    break;
                case R.id.timeNode2:
                    infoText.setText(phaseInfo.get(2));
                    break;
                case R.id.timeNode3:
                    infoText.setText(phaseInfo.get(3));
                    break;
                case R.id.timeNode4:
                    infoText.setText(phaseInfo.get(4));
                    break;
                case R.id.timeNode5:
                    infoText.setText(phaseInfo.get(5));
                    break;
            }
        }
        catch(Exception e)
        {
            infoText.setText(ERROR_MSG);
        }
        finally
        {
            infoText.setVisibility(View.VISIBLE);
        }
    }

    /**
     * Hides the information displayed when pressed.
     * @param view
     */
    public void hideInfo(View view)
    {
        if (infoText.getVisibility() == View.VISIBLE)
        {
            infoText.setVisibility(View.INVISIBLE);
        }
    }
}
