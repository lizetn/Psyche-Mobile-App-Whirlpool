package asu.whirlpool.psychewhirlpool.facts;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

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
 * @version     1/15/2018
 */
public class FactsActivity extends AppCompatActivity
{
    private ListView mListView;

    public static final String FAQ_KEY = "faq_key";

    public int[] tabDayImages = {R.drawable.mission_tab_light6, R.drawable.mission_tab_light10,
                                   R.drawable.mission_tab_light2, R.drawable.mission_tab_light11,
                                   R.drawable.mission_tab_light3, R.drawable.mission_tab_light8,
                                   R.drawable.mission_tab_light9, R.drawable.mission_tab_light1,
                                   R.drawable.mission_tab_light7, R.drawable.mission_tab_light12,
                                   R.drawable.mission_tab_light5, R.drawable.mission_tab_light4};
    public int[] tabNightImages = {R.drawable.mission_tab_dark6, R.drawable.mission_tab_dark10,
                                     R.drawable.mission_tab_dark2, R.drawable.mission_tab_dark11,
                                     R.drawable.mission_tab_dark3, R.drawable.mission_tab_dark8,
                                     R.drawable.mission_tab_dark9, R.drawable.mission_tab_dark1,
                                     R.drawable.mission_tab_dark7, R.drawable.mission_tab_dark12,
                                     R.drawable.mission_tab_dark5, R.drawable.mission_tab_dark4};

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
        ListViewAdapter adapter;

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES)
        {
            setTheme(R.style.PsycheDarkTheme);
            adapter = new ListViewAdapter(this, tabNightImages);
        }
        else
        {
            adapter = new ListViewAdapter(this, tabDayImages);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facts);

        // Set up navigation bar
        BottomNavigationView navigation = findViewById(R.id.navigation);
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) navigation.getChildAt(0);
        for (int i = 0; i < menuView.getChildCount(); i++) {
            final View iconView = menuView.getChildAt(i).findViewById(android.support.design.R.id.icon);
            final ViewGroup.LayoutParams layoutParams = iconView.getLayoutParams();
            final DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
            // navigation icon height set here
            layoutParams.height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 32, displayMetrics);
            // navigation icon width set here
            layoutParams.width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 32, displayMetrics);
            iconView.setLayoutParams(layoutParams);
        }
        BottomNavigationViewHelper.disableAnimation(navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //Create ListView object and fills it will text on the options
        mListView = findViewById(R.id.FactsList);
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
