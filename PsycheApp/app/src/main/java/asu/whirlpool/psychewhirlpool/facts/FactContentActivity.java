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

public class FactContentActivity extends AppCompatActivity
{
    private TypedArray contentArray;
    private TextView contentTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fact_content);

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
