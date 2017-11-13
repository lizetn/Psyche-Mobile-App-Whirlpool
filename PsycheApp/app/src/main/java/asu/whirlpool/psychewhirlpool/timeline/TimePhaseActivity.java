package asu.whirlpool.psychewhirlpool.timeline;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import asu.whirlpool.psychewhirlpool.R;
import asu.whirlpool.psychewhirlpool.timeline.TimelineActivity;
import asu.whirlpool.psychewhirlpool.timeline.TimelineManager;

public class TimePhaseActivity extends AppCompatActivity
{
    public int phase = 0;
    public TimelineManager timelineManager = new TimelineManager();

    //Constants
    private final int ERROR_PHASE = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_phase);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        phase = intent.getIntExtra(TimelineActivity.PHASE_SELECTED, ERROR_PHASE);
        Log.d("Phase Selected: ", String.valueOf(phase));

        int color = 0;

        // Set color of TimeNodes based on Phase selected
        switch(phase)
        {
            case 1:
                color = ResourcesCompat.getColor(getResources(), R.color.psyche_mustard, null);
                break;
            case 2:
                color = ResourcesCompat.getColor(getResources(), R.color.psyche_gold, null);
                break;
            case 3:
                color = ResourcesCompat.getColor(getResources(), R.color.psyche_coral, null);
                break;
            case 4:
                color = ResourcesCompat.getColor(getResources(), R.color.psyche_magenta, null);
                break;
            case 5:
                color = ResourcesCompat.getColor(getResources(), R.color.psyche_purple, null);
                break;
            case 6:
                color = ResourcesCompat.getColor(getResources(), R.color.psyche_dark_purple, null);
                break;
            case 7:
                color = ResourcesCompat.getColor(getResources(), R.color.psyche_black, null);
                break;
        }

        timelineManager.setTimeColor(color);

        // Creating Bitmap to draw TimeNode
        Bitmap bmp = Bitmap.createBitmap(500, 500, Bitmap.Config.ARGB_8888);
        Canvas nodeCanvas = new Canvas(bmp);

        // Draw timeline
        timelineManager.drawTimeline(nodeCanvas);
        ImageView image = (ImageView) findViewById(R.id.timelineImage);
        image.setImageBitmap(bmp);
    }
}
