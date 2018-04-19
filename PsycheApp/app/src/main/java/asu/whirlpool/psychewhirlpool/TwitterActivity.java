package asu.whirlpool.psychewhirlpool;

import java.lang.*;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.twitter.sdk.android.core.Twitter;
import android.os.CountDownTimer;
import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;
import com.twitter.sdk.android.tweetui.UserTimeline;

/**
 *  Javier Perez
 *  Twitter Activity
 *  Class that uses Twitter API to create a Twitter feed
 */
public class TwitterActivity extends ListFragment
{
    CountDownTimer mCountDownTimer;
    String mp = "";
    View view;

    /**
     * ???
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.activity_socialmedia, container, false);
        final TextView ls = view.findViewById(R.id.ls);
        ls.setText("");

        //Progress bar used to check internet connection
        mCountDownTimer = new CountDownTimer(8000, 1000)
        {
            @Override
            public void onTick(long l)
            {
                Long countdownSeconds = l / 1000;
                mp = String.format("%d", ((countdownSeconds % 86400) % 3600) % 60);
                if(countdownSeconds == 1 || countdownSeconds == 1000)
                {
                    ls.setText(R.string.internet_error);
                    mCountDownTimer.cancel();
                }
            }

            @Override
            public void onFinish()
            {
                mCountDownTimer.cancel();
            }
        };

        mCountDownTimer.start();
        //Twitter API call, sets the list adapter to a Twitter adapter with the content containing tweets and images
        Twitter.initialize(view.getContext());

        final UserTimeline userTimeline = new UserTimeline.Builder()
                .screenName("NASAPsyche")
                .build();
        final TweetTimelineListAdapter adapter = new TweetTimelineListAdapter.Builder(view.getContext())
                .setTimeline(userTimeline)
                .build();

        setListAdapter(adapter);

        return view;
    }
}