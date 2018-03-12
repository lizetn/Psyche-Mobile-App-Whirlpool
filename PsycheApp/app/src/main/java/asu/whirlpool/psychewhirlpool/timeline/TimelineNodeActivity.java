package asu.whirlpool.psychewhirlpool.timeline;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import asu.whirlpool.psychewhirlpool.R;

/**
 * {@link TimelineNodeActivity} displays detailed information about a node selected
 * in {@link TimelineTab}.
 *
 * @author      Erick Ramirez Cordero
 * @version     3/7/2018
 */
public class TimelineNodeActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline_node);

        // Initialize details about the node selected
        Intent intent = getIntent();
        TextView titleTextView = findViewById(R.id.titleTextView);
        TextView contentTextView = findViewById(R.id.contentTextView);
        ImageView imageView = findViewById(R.id.nodeImageView);

        try
        {
            String title = intent.getExtras().getString(TimelineRecycleAdapter.TITLE_KEY);
            String data = intent.getExtras().getString(TimelineRecycleAdapter.DATA_KEY);
            int imageId = intent.getExtras().getInt(TimelineRecycleAdapter.IMAGE_KEY);

            titleTextView.setText(title);
            contentTextView.setText(data);
            imageView.setImageResource(imageId);
        }
        catch (Exception ex)
        {
            // Display error message
            titleTextView.setText(R.string.error_message);
            contentTextView.setText(R.string.error_message);
            imageView.setImageResource(R.drawable.psyche_help_purple);
        }
    }
}
