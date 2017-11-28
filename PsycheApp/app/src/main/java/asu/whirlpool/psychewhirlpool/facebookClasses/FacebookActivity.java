package asu.whirlpool.psychewhirlpool.facebookClasses;

import android.support.v4.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
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
import asu.whirlpool.psychewhirlpool.R;
import static com.facebook.FacebookSdk.getApplicationContext;

public class FacebookActivity extends ListFragment {

    private TextView mTextMessage;
    View view;
    CallbackManager callbackManager;
    //JSON array to hold graph response array
    JSONArray jsons;
    //keeps track of accessToken since accessToken have expiration dates
    AccessTokenTracker accessTokenTracker;
    ListView list1;
    //Arraylist of FacebookfeedList used to store each field in graph response
    ArrayList<FacebookfeedList> list_arr;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.activity_facebook, container, false);
        super.onCreate(savedInstanceState);
        list1 = (ListView) view.findViewById(android.R.id.list);
        list_arr = new ArrayList<>();
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
            getUserData(AccessToken.getCurrentAccessToken());
        }
        catch (Exception e){
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
                AccessToken.getCurrentAccessToken(),
                "/1598743977091187/feed?fields=id,message,picture,story,created_time", null,
                HttpMethod.GET,
                new GraphRequest.Callback() {

                    @Override
                    public void onCompleted(GraphResponse graphResponse) {
                        try
                        {
                            final JSONObject jason = graphResponse.getJSONObject();
                            JSONArray jsons = jason.getJSONArray("data");
                            for(int i=0;i<jsons.length();i++)
                            {
                                FacebookfeedList fbFeedList = new FacebookfeedList();
                                JSONObject objectdata = jsons.getJSONObject(i);
                                String message = "";
                                String id = objectdata.getString("id");
                                if (objectdata.has("message"))
                                {
                                    message = objectdata.getString("message");
                                }
                                String pic="";
                                String story="";
                                if (objectdata.has("picture"))
                                {
                                    pic = objectdata.getString("picture");
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
                            ListAdapter adapter = new ListAdapter(getApplicationContext(),list_arr);
                            list1.setAdapter(adapter);
                        }catch (Exception e) {
                        }
                    }
                }).executeAsync();
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
        }
    }
}
