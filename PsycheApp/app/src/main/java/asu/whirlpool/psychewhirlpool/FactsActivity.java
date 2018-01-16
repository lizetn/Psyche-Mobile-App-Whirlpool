package asu.whirlpool.psychewhirlpool;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import asu.whirlpool.psychewhirlpool.timeline.TimelineTab;

/**
 * FactsActivity handles the loading of information for the FAQ / Mission Facts section of
 * the Psyche App.
 *
 * @author      Erick Ramirez Cordero
 * @date        11/10/2017
 * @updated     1/15/2018
 */
public class FactsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener
{
    private TextView mTextMessage;
    private TextView faqTextView;
    private TypedArray faqArray;

    /**
     * Handles navigation between different sections of the Psyche App.
     */
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener()
    {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item)
        {
            Intent intent;

            switch (item.getItemId())
            {
                case R.id.navigation_home:
                    intent = new Intent(FactsActivity.this, MainActivity.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_timeline:
                    intent = new Intent(FactsActivity.this, TimelineTab.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_gallery:
                    intent = new Intent(FactsActivity.this, GalleryActivity.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_social_media:
                    intent = new Intent(FactsActivity.this, SocialMediaTabs.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_game:
                    intent = new Intent(FactsActivity.this, GameActivity.class);
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
        setContentView(R.layout.activity_facts);

        mTextMessage = findViewById(R.id.message);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        BottomNavigationViewHelper.disableAnimation(navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        // Instantiate array of FAQ String references
        faqArray = getResources().obtainTypedArray(R.array.faq_array);

        // Instantiate Spinner
        Spinner faqSpinner = findViewById(R.id.faqSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.faq_options,
                android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        faqSpinner.setAdapter(adapter);
        faqSpinner.setOnItemSelectedListener(this);

        // Instantiate FAQ Information
        faqTextView = findViewById(R.id.faqTextView);
        faqTextView.setText(R.string.faq_intro);
    }

    /**
     * When an item is selected, the information displayed changes to match the selected option.
     * Check facts.xml to view/edit the arrays used for the options.
     * @param parent
     * @param view
     * @param pos       The position of the item selected
     * @param id
     */
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id)
    {
        try
        {
            faqTextView.setText(faqArray.getText(pos));
        }
        catch(Exception exception)
        {
            faqTextView.setText(R.string.error_message);
        }
    }

    /**
     * Handles behavior for when no option is selected.
     * @param parent
     */
    public void onNothingSelected(AdapterView<?> parent)
    {
        // Another interface callback
    }
}
