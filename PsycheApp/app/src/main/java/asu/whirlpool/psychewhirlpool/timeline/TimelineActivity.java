package asu.whirlpool.psychewhirlpool.timeline;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import asu.whirlpool.psychewhirlpool.BottomNavigationViewHelper;
import asu.whirlpool.psychewhirlpool.FactsActivity;
import asu.whirlpool.psychewhirlpool.GalleryActivity;
import asu.whirlpool.psychewhirlpool.GameActivity;
import asu.whirlpool.psychewhirlpool.MainActivity;
import asu.whirlpool.psychewhirlpool.R;
import asu.whirlpool.psychewhirlpool.SocialMediaTabs;

public class TimelineActivity extends AppCompatActivity
{
    private TextView mTextMessage;
    public static final String PHASE_SELECTED = "Phase Selected";

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Intent intent;

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    intent = new Intent(TimelineActivity.this, MainActivity.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_timeline:
                    return true;
                case R.id.navigation_gallery:
                    intent = new Intent(TimelineActivity.this, GalleryActivity.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_social_media:
                    intent = new Intent(TimelineActivity.this, SocialMediaTabs.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_game:
                    intent = new Intent(TimelineActivity.this, GameActivity.class);
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
        setContentView(R.layout.activity_timeline);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        BottomNavigationViewHelper.disableAnimation(navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);
    }

    /**
     * Navigates to a phase of the timeline {@link TimePhaseActivity}
     * @param view          Necessary for onClick() methods
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

    /**
     * Navigates to the FAQ section of the Psyche App {@link FactsActivity}
     * @param view          Necessary for onClick() methods
     */
    public void displayFaq(View view)
    {
        Intent intent = new Intent(this, FactsActivity.class);
        startActivity(intent);
    }
}
