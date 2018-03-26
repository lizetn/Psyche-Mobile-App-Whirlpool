package asu.whirlpool.psychewhirlpool.facts;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
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

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fact_content);

        // Get the FAQ key which denotes which section of the FAQ was selected.
        Intent intent = getIntent();
        int position = intent.getExtras().getInt(FactsActivity.FAQ_KEY);

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
