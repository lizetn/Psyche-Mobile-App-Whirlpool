package asu.whirlpool.psychewhirlpool.timeline;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.widget.ImageView;
import android.widget.TextView;

import asu.whirlpool.psychewhirlpool.R;

/**
 * {@link TimelineNodeActivity} displays detailed information about a node selected
 * in {@link TimelineTab}.
 *
 * @author      Erick Ramirez Cordero
 * @version     4/17/2018
 */
public class TimelineNodeActivity extends AppCompatActivity
{
    /**
     * Instantiates the activity based on day/night mode and the node that was selected
     * in {@link TimelineFragment}.
     *
     * The node's information is sent in an {@link Intent} from {@link TimelineRecycleAdapter}.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        boolean nightModeEnabled;
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES)
        {
            setTheme(R.style.PsycheDarkTheme);
            nightModeEnabled = true;
        }
        else
        {
            setTheme(R.style.PsycheLightTheme);
            nightModeEnabled = false;
        }

        // Instantiate the layout and background
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline_node);

        if (nightModeEnabled)
        {
            findViewById(R.id.constraintLayout).setBackgroundResource(R.drawable.background_timeline_dark);
        }
        else
        {
            findViewById(R.id.constraintLayout).setBackgroundResource(R.drawable.background_timeline_light);
        }

        // Initialize details about the node selected
        Intent intent = getIntent();
        TextView titleTextView = findViewById(R.id.titleTextView);
        TextView contentTextView = findViewById(R.id.contentTextView);

        try
        {
            String title = intent.getExtras().getString(TimelineRecycleAdapter.TITLE_KEY);
            String data = intent.getExtras().getString(TimelineRecycleAdapter.DATA_KEY);

            titleTextView.setText(title);
            contentTextView.setText(data);
        }
        catch (Exception ex)
        {
            // Display error message
            titleTextView.setText(R.string.error_message);
            contentTextView.setText(R.string.error_message);
        }
    }
}
