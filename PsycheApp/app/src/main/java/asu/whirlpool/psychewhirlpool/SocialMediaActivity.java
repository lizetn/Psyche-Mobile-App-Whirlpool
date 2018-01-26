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
import android.widget.ProgressBar;
import android.widget.TextView;
//import io.fabric.sdk.android.Fabric;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.Twitter;
import android.os.CountDownTimer;
//import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;
import com.twitter.sdk.android.tweetui.UserTimeline;
import android.support.v4.widget.SwipeRefreshLayout;

public class SocialMediaActivity extends ListFragment
{
    private TextView message;
    CountDownTimer mCountDownTimer;
    String mp = "";
    ProgressBar prog;
    ListView myList;
    private static final String TWITTER_KEY = "6Gekxk012j0RkDVr9x6wwornQ";
    private static final String TWITTER_SECRET = "L9vdf2MOhyBrbW53C7Q6WaPH3dVW4bG24X096iwxnP4EOR0PD5";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // CharSequence sm = ((CharSequence)mCountDownTimer1);
        //int count = Integer.valueOf(String.valueOf(mCountDownTimer1));
        View v = inflater.inflate(R.layout.activity_socialmedia, container, false);
        final TextView ls = (TextView) v.findViewById(R.id.android_ls);
        int milliseconds = 0;
        prog = (ProgressBar) v.findViewById(R.id.progressBar2);
        CountdownActivity ms = new CountdownActivity();
        mCountDownTimer = new CountDownTimer(8000, 1000)
        {
            @Override
            public void onTick(long l)
            {
                Long countdownSeconds = l / 1000;
                mp = String.format("%d", ((countdownSeconds % 86400) % 3600) % 60);
                if(countdownSeconds == 1 || countdownSeconds == 1000)
                {
                    ls.setText("Check internet connection");
                    mCountDownTimer.cancel();
                }
            }

            @Override
            public void onFinish()
            {
                prog.setVisibility(View.GONE);
                mCountDownTimer.cancel();
            }
        };

        mCountDownTimer.start();
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
}