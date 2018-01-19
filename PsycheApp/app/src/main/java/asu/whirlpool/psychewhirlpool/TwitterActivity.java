package asu.whirlpool.psychewhirlpool;

import java.lang.*;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
//import io.fabric.sdk.android.Fabric;
//import com.twitter.sdk.android.core.Callback;
//import com.twitter.sdk.android.core.Result;
//import com.twitter.sdk.android.core.TwitterCore;
//import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.Twitter;
import android.os.CountDownTimer;
import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;
import com.twitter.sdk.android.tweetui.UserTimeline;


public class TwitterActivity extends ListFragment {
    CountDownTimer mCountDownTimer;
    String mp = "";
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.activity_socialmedia, container, false);
        final TextView ls = (TextView) view.findViewById(R.id.android_ls);
        ls.setText("");

        CountdownActivity ms = new CountdownActivity();
        mCountDownTimer = new CountDownTimer(8000, 1000) {
            @Override
            public void onTick(long l) {
                Long countdownSeconds = l / 1000;
                mp = String.format("%d", ((countdownSeconds % 86400) % 3600) % 60);
                if(countdownSeconds == 1 || countdownSeconds == 1000)
                {
                    ls.setText("Check internet connection");
                    mCountDownTimer.cancel();
                }
            }

            @Override
            public void onFinish() {
                mCountDownTimer.cancel();
            }
        };

        mCountDownTimer.start();

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