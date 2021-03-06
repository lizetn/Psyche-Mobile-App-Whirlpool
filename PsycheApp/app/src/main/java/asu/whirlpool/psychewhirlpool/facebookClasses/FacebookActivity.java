package asu.whirlpool.psychewhirlpool.facebookClasses;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import asu.whirlpool.psychewhirlpool.CountdownActivity;
import asu.whirlpool.psychewhirlpool.R;

import static com.facebook.FacebookSdk.getApplicationContext;

public class FacebookActivity extends Fragment {

    private TextView mTextMessage;
    private RelativeLayout divider;
    String mp = "";
    ProgressBar prog;
    CountDownTimer mCountDownTimer;
    View view;
    CallbackManager callbackManager;
    //JSON array to hold graph response array
    JSONArray jsons;
    //keeps track of accessToken since accessToken have expiration dates
    AccessTokenTracker accessTokenTracker;
    RecyclerView list1;
    //Arraylist of FacebookfeedList used to store each field in graph response
    ArrayList<FacebookfeedList> list_arr;
    EndlessRecycleOnScrollListener listener;
    LinearLayoutManager layoutManager;
    ListAdapter adapter;
    //Access token is storeed here, will need to get moved somewhere else, possibly to cloud.
    AccessToken accessToken = new AccessToken(
            "1499176063510395|ZI2GBERxLbDOoC1keO1AMsi5TmU",
            "1499176063510395","1598743977091187",
            null,
            null,
            null,
            null,
            null);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.activity_facebook, container, false);
        super.onCreate(savedInstanceState);
        mTextMessage = (TextView) view.findViewById(R.id.facebook_loading);
        mTextMessage.setText("");
        prog = (ProgressBar) view.findViewById(R.id.prog2);

        CountdownActivity ms = new CountdownActivity();
        mCountDownTimer = new CountDownTimer(8000, 1000) {
            @Override
            public void onTick(long l) {
                Long countdownSeconds = l / 1000;
                mp = String.format("%d", ((countdownSeconds % 86400) % 3600) % 60);
                if (countdownSeconds == 1 || countdownSeconds == 1000) {
                    mTextMessage.setText("Check internet connection");
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
        list1 = (RecyclerView) view.findViewById(android.R.id.list);
        list_arr = new ArrayList<>();
        layoutManager = new LinearLayoutManager(this.getActivity());
        listener = new EndlessRecycleOnScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int current_page) {
                //setLoadMoreEnable(true);

                if (list_arr.size() >= 20){

                }

            }
        };
        list1.setLayoutManager(layoutManager);
        list1.setHasFixedSize(false);
        list1.addOnScrollListener(listener);
        adapter = new ListAdapter(getActivity(),list_arr);
        list1.setAdapter(adapter);
        //try catch required for callbackManager, graph API methods
        try {
            callbackManager = CallbackManager.Factory.create();
            accessTokenTracker = new AccessTokenTracker() {
                @Override
                protected void onCurrentAccessTokenChanged(
                        AccessToken oldAccessToken,
                        AccessToken currentAccessToken) {
                }
            };
            getUserData(accessToken);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return view;
    }
    /*
    *   retrieves JSON form of the facebook feed, Graph request method used to retrieve this information
    *   information is than stored into FacebookfeedList fields, id, message,story, and time
     */
    public void getUserData(AccessToken accessToken)
    {
        new GraphRequest(
                accessToken,
                "/1598743977091187/feed?fields=id,message,full_picture,story,created_time", null,
                HttpMethod.GET,
                new GraphRequest.Callback() {

                    @Override
                    public void onCompleted(GraphResponse graphResponse) {
                        try
                        {
                            final JSONObject jason = graphResponse.getJSONObject();
                            JSONArray jsons = jason.getJSONArray("data");
                            for(int i = 0; i < jsons.length(); i++)
                            {
                                FacebookfeedList fbFeedList = new FacebookfeedList();
                                JSONObject objectdata = jsons.getJSONObject(i);
                                String message = "";
                                String id = objectdata.getString("id");
                                if (objectdata.has("message"))
                                {
                                    message = objectdata.getString("message");
                                }
                                String pic;
                                String story;
                                if (objectdata.has("full_picture"))
                                {
                                    pic = objectdata.getString("full_picture");
                                }
                                else
                                {
                                    pic = "";
                                }
                                if (objectdata.has("story"))
                                {
                                    story = objectdata.getString("story");
                                }
                                else
                                {
                                    story = "";
                                }
                                String created_time = objectdata.getString("created_time");

                                fbFeedList.setId(id);
                                fbFeedList.setMessage(message);
                                fbFeedList.setStory(story);
                                fbFeedList.setTime(created_time);
                                fbFeedList.setPicture(pic);
                                list_arr.add(fbFeedList);
                            }
                            adapter = new ListAdapter(getApplicationContext(),list_arr);
                            list1.setNestedScrollingEnabled(false);

                            list1.setAdapter(adapter);
                        }catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
        ).executeAsync();
    }
    //methods required for callback manager and accessTokentracker
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
    @Override
    public void onDestroy()
    {
        super.onDestroy();
        try{
            accessTokenTracker.stopTracking();}
        catch (Exception e){
            //Toast.makeText(FacebookActivity.this, "error is: "+e.toString(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
}
