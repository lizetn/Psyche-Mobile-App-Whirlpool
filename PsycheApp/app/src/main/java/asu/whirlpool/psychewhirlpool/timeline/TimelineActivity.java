package asu.whirlpool.psychewhirlpool.timeline;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import asu.whirlpool.psychewhirlpool.R;

public class TimelineActivity extends AppCompatActivity
{

    private TextView mTextMessage;
    public static final String PHASE_SELECTED = "Phase Selected";

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
        setContentView(R.layout.activity_timeline);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    /**
     * Navigates to {@link TimePhaseActivity}
     * @param view
     */
    public void displayTimePhase(View view)
    {
        Intent intent = new Intent(this, TimePhaseActivity.class);
        int phase = 0;

        switch(view.getId())
        {
            case R.id.phaseButton1:
                phase = 1;
                break;
            case R.id.phaseButton2:
                phase = 2;
                break;
            case R.id.phaseButton3:
                phase = 3;
                break;
            case R.id.phaseButton4:
                phase = 4;
                break;
            case R.id.phaseButton5:
                phase = 5;
                break;
            case R.id.phaseButton6:
                phase = 6;
                break;
            case R.id.phaseButton7:
                phase = 7;
                break;
        }

        intent.putExtra(PHASE_SELECTED, phase);
        startActivity(intent);
    }
}
