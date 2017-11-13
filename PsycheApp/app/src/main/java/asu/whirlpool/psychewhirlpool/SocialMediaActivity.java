package asu.whirlpool.psychewhirlpool;

import android.app.ListActivity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
//import io.fabric.sdk.android.Fabric;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.Twitter;
//import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;
import com.twitter.sdk.android.tweetui.UserTimeline;
import android.support.v4.widget.SwipeRefreshLayout;

public class SocialMediaActivity extends ListFragment
{
    private TextView message;
    ListView myList;
    private static final String TWITTER_KEY = "6Gekxk012j0RkDVr9x6wwornQ";
    private static final String TWITTER_SECRET = "L9vdf2MOhyBrbW53C7Q6WaPH3dVW4bG24X096iwxnP4EOR0PD5";
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener()
    {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item)
        {
            switch (item.getItemId())
            {
                case R.id.navigation_home:
                    message.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    message.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    message.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_socialmedia, container, false);
        Twitter.initialize(v.getContext());

        final UserTimeline userTimeline = new UserTimeline.Builder()
                .screenName("NASAPsyche")
                .build();
        final TweetTimelineListAdapter adapter = new TweetTimelineListAdapter.Builder(v.getContext())
                .setTimeline(userTimeline)
                .build();

        setListAdapter(adapter);


        return v;
    }
/*
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Twitter.initialize(this);
        //final SwipeRefreshLayout swipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_layout);
        setContentView(R.layout.activity_socialmedia);

        message = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);




        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);



        final UserTimeline userTimeline = new UserTimeline.Builder()
                .screenName("NASAPsyche")
                .build();
        final TweetTimelineListAdapter adapter = new TweetTimelineListAdapter.Builder(this)
                .setTimeline(userTimeline)
                .build();

       setListAdapter(adapter);


        //myList.setEmptyView();
        //myList.setAdapter(adapter);
    }*/

}
