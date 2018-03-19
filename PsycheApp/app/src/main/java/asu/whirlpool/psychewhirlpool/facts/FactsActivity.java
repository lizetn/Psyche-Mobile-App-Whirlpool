package asu.whirlpool.psychewhirlpool.facts;

import android.content.Intent;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import asu.whirlpool.psychewhirlpool.BottomNavigationViewHelper;
import asu.whirlpool.psychewhirlpool.GameActivity;
import asu.whirlpool.psychewhirlpool.MainActivity;
import asu.whirlpool.psychewhirlpool.R;
import asu.whirlpool.psychewhirlpool.SocialMediaTabs;
import asu.whirlpool.psychewhirlpool.gallery.GalleryTab;
import asu.whirlpool.psychewhirlpool.timeline.TimelineTab;

/**
 * FactsActivity handles the loading of information for the FAQ / Mission Facts section of
 * the Psyche App.
 *
 * @author      Erick Ramirez Cordero
 * @date        11/10/2017
 * @updated     1/15/2018
 */
public class FactsActivity extends AppCompatActivity
{
    private TextView mTextMessage;
    private TextView faqTextView;
    private TypedArray faqArray;
    private ListView mListView;

    public static final String FAQ_KEY = "faq_key";

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
                    intent = new Intent(FactsActivity.this, GalleryTab.class);
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

        //ListView

        //Create ListView object and fills it will text on the options
        mListView = findViewById(R.id.FactsList);
        ListViewAdapter adapter = new ListViewAdapter(this);
        mListView.setAdapter(adapter);

        // Instantiate FAQ Information
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id)
            {
                Intent intent = new Intent(view.getContext(), FactContentActivity.class);
                intent.putExtra(FAQ_KEY, position);
                startActivity(intent);
            }
        });


    }
}
