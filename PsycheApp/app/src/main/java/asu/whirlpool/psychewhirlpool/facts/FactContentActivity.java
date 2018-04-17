package asu.whirlpool.psychewhirlpool.facts;

import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
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
 * @author      Diana Chavez
 * @version     4/15/2018
 */
public class FactContentActivity extends AppCompatActivity
{
    private TypedArray contentArray;
    private TextView contentTextView;
    private ConstraintLayout missionBackground;
    private Toolbar contentToolbar;

    private int[] backgroundDayImages = {R.drawable.tab_background_light6, R.drawable.tab_background_light10,
            R.drawable.tab_background_light2, R.drawable.tab_background_light11,
            R.drawable.tab_background_light3, R.drawable.tab_background_light8,
            R.drawable.tab_background_light9, R.drawable.tab_background_light1,
            R.drawable.tab_background_light7, R.drawable.tab_background_light12,
            R.drawable.tab_background_light5, R.drawable.tab_background_light4};
    private int[] backgroundNightImages = {R.drawable.tab_background_dark6, R.drawable.tab_background_dark10,
            R.drawable.tab_background_dark2, R.drawable.tab_background_dark11,
            R.drawable.tab_background_dark3, R.drawable.tab_background_dark8,
            R.drawable.tab_background_dark9, R.drawable.tab_background_dark1,
            R.drawable.tab_background_dark7, R.drawable.tab_background_dark12,
            R.drawable.tab_background_dark5, R.drawable.tab_background_dark4};

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
            setTheme(R.style.PsycheLightTheme);
            nightMode = false;
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fact_content);

        // Get the FAQ key which denotes which section of the FAQ was selected.
        Intent intent = getIntent();
        int position = intent.getExtras().getInt(FactsActivity.FAQ_KEY);
        missionBackground = findViewById(R.id.ConstraintBackground);

        final int sdk = android.os.Build.VERSION.SDK_INT;
        contentArray = getResources().obtainTypedArray(R.array.faq_array);
        contentTextView = findViewById(R.id.factTextView);
        contentToolbar = (Toolbar) findViewById(R.id.toolbar);

        if (nightMode)
        {
            contentTextView.setTextColor(Color.WHITE);
            contentToolbar.setBackgroundColor(getResources().getColor(R.color.psyche_black));

            if(sdk >= android.os.Build.VERSION_CODES.JELLY_BEAN)
            {
                missionBackground.setBackground(ContextCompat.getDrawable(this, backgroundNightImages[position]));
            }
            else
            {
                missionBackground.setBackgroundDrawable(ContextCompat.getDrawable(this, backgroundNightImages[position]));
            }
        }
        else
        {
            contentTextView.setTextColor(getResources().getColor(R.color.psyche_black));
            if(sdk >= android.os.Build.VERSION_CODES.JELLY_BEAN)
            {
                missionBackground.setBackground(ContextCompat.getDrawable(this, backgroundDayImages[position]));
            }
            else
            {
                missionBackground.setBackgroundDrawable(ContextCompat.getDrawable(this, backgroundDayImages[position]));
            }
        }

        try
        {
            contentTextView.setText(contentArray.getText(position));
        }
        catch (Exception ex)
        {
            contentTextView.setText(R.string.error_message);
        }
        finally
        {
            contentArray.recycle();
        }
    }

}
