package asu.whirlpool.psychewhirlpool;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Xml;
import android.view.MenuItem;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;

import asu.whirlpool.psychewhirlpool.timeline.TimelineActivity;
import asu.whirlpool.psychewhirlpool.timeline.TimelineTab;

/**
 * FactsActivity handles the loading of information for the FAQ / Mission Facts section of
 * the Psyche App
 *
 * @author  Erick Ramirez Cordero
 * @date    11/10/2017
 */
public class FactsActivity extends AppCompatActivity
{
    private TextView mTextMessage;
    private final String ERROR_MESSAGE = "ERROR!";
    private final String FAQ_TEXT_FILE = "TimelineAssets/faqText.txt";
    private final String FAQ_XML_FILE = "TimelineAssets/FaqFacts.xml";

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Intent intent;

            switch (item.getItemId()) {
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
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        BottomNavigationViewHelper.disableAnimation(navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        // Retrieve FAQ Information
        TextView faqTextView = findViewById(R.id.faqTextView);
        faqTextView.setText(R.string.psyche_faq_info);

        // TODO: Figure out why your own XML Doc isn't properly formatted like strings.xml!
        //faqTextView.setText(initPhaseInfo());
    }

    /**
     * Fetches information about Psyche from the {@file FaqFacts} XML document
     *
     * NOTE: Parsing with this method does not register escape characters such as newline.
     * Figure out why this parser isn't reading them properly!
     */
    private String initPhaseInfo()
    {
        ArrayList<String> factList = new ArrayList<>();

        try
        {
            XmlPullParser parser = Xml.newPullParser();
            parser.setInput(getAssets().open(FAQ_XML_FILE), null);
            parser.nextTag();

            while (parser.getEventType() != XmlPullParser.END_DOCUMENT)
            {
                if (parser.getEventType()== XmlPullParser.TEXT)
                {
                    factList.add(parser.getText());
                }

                parser.next();
            }
        }
        catch (Exception e)
        {
            factList.add(ERROR_MESSAGE);
        }
        finally
        {
            String infoString = "";

            for (int index = 0; index < factList.size(); index++)
            {
                infoString += factList.get(index);
            }

            // TODO: Find another way to format FaqFacts.xml in order to NOT do this!
            //infoString = infoString.replace("\\n", "\n");
            //infoString = infoString.replace("\\t", "\t");
            return infoString;
        }
    }
}
