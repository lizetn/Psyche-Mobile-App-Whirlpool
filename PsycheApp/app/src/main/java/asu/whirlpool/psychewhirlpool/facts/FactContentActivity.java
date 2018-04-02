package asu.whirlpool.psychewhirlpool.facts;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import asu.whirlpool.psychewhirlpool.R;

/**
 * {@link FactContentActivity} sets the content to be displayed when the User
 * views a section of the FAQ.
 *
 * @author      Erick Ramirez Cordero
 * @version     2/15/2018
 */
public class FactContentActivity extends AppCompatActivity
{
    private TypedArray contentArray;
    private TextView contentTextView;
    private ImageView missionBackground;

    private int[] backgroundDayImages = {R.drawable.mission_background_light1, R.drawable.mission_background_light2,
            R.drawable.mission_background_light3, R.drawable.mission_background_light4,
            R.drawable.mission_background_light5, R.drawable.mission_background_light6,
            R.drawable.mission_background_light7, R.drawable.mission_background_light8,
            R.drawable.mission_background_light9, R.drawable.mission_background_light10,
            R.drawable.mission_background_light11, R.drawable.mission_background_light12};
    private int[] backgroundNightImages = {R.drawable.mission_background_dark1, R.drawable.mission_background_dark2,
            R.drawable.mission_background_dark3, R.drawable.mission_background_dark4,
            R.drawable.mission_background_dark5, R.drawable.mission_background_dark6,
            R.drawable.mission_background_dark7, R.drawable.mission_background_dark8,
            R.drawable.mission_background_dark9, R.drawable.mission_background_dark10,
            R.drawable.mission_background_dark11, R.drawable.mission_background_dark12};

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        boolean nightMode;

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES)
        {
            setTheme(R.style.PsycheDarkTheme);
            nightMode = true;
        }
        else
        {
            nightMode = false;
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fact_content);

        // Get the FAQ key which denotes which section of the FAQ was selected.
        Intent intent = getIntent();
        int position = intent.getExtras().getInt(FactsActivity.FAQ_KEY);
        missionBackground = findViewById(R.id.backgroundImageView);

        if (nightMode)
        {
            missionBackground.setImageResource(backgroundNightImages[position]);
        }
        else
        {
            missionBackground.setImageResource(backgroundDayImages[position]);
        }

        contentArray = getResources().obtainTypedArray(R.array.faq_array);
        contentTextView = findViewById(R.id.factTextView);

        try
        {
            contentTextView.setText(contentArray.getText(position));
        }
        catch (Exception ex)
        {
            contentTextView.setText(R.string.error_message);
        }
    }

}
