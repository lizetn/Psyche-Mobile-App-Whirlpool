package asu.whirlpool.psychewhirlpool.InstagramClasses;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;
import asu.whirlpool.psychewhirlpool.R;

import com.facebook.AccessToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;
import android.support.v4.app.ListFragment;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import asu.whirlpool.psychewhirlpool.facebookClasses.FacebookfeedList;
import asu.whirlpool.psychewhirlpool.facebookClasses.ListAdapter;
import cz.msebera.android.httpclient.Header;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by jperez60 on 1/4/2018.
 */

public class MainInstagramActivity extends Fragment
{
    RecyclerView rv;
    public static ArrayList<Model> list;
    boolean isnext;
    String nextid;
    public static String profile_img ="";
    public static String profile_name="";
    EndlessRecycleOnScrollListener listener;
    ProgressBar prog;
    LinearLayoutManager layoutManager;
    Adapter a;
    ListView list1;
    //Arraylist of FacebookfeedList used to store each field in graph response
    ArrayList<Model> list_arr;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        /*  Access token is stored here, will need to get moved somewhere else, possibly to cloud.
        *   Once instagram_basic permission has been obtained, use https://developers.facebook.com/tools/explorer/
        *   to obtain new access token. Go to Application->click app account name in our case it was Whirlpool Psyche App -> Get Token ->
        *   chose between page and or user token with instagram_basic checked-> from  there put the application id
        *   and userid (userid will most likely remain the same )from the facebook/developer site
        *   as well as the access token obtained from the graph api explorer.
        */
        AccessToken accessToken = new AccessToken(
                "1499176063510395|ZI2GBERxLbDOoC1keO1AMsi5TmU",
                "1499176063510395","1598743977091187",
                null,
                null,
                null,
                null,
                null);
        View view = inflater.inflate(R.layout.instagramactivity_main, container, false);

        list = new ArrayList<>();

        rv = (RecyclerView) view.findViewById(R.id.rv);
        prog = (ProgressBar) view.findViewById(R.id.prog);
        Graphcall();
        layoutManager = new LinearLayoutManager(this.getActivity());
        listener = new EndlessRecycleOnScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int current_page) {
                //setLoadMoreEnable(true);

                if (list.size() >= 12){

                    if(isnext) {
                        loadmoredata(nextid);
                    }
                }

            }
        };

        rv.setLayoutManager(layoutManager);
        rv.setHasFixedSize(false);
        rv.addOnScrollListener(listener);
        a = new Adapter(getActivity(),list);
        rv.setAdapter(a);
        return view;
    }
    //Retrieves Instagram profile in a JSON format using a web services call
    /*
    *   As of 4/18/2018, Instagram API is currently being modified due to the Facebook data breach
    *   Due to this, Facebook is constantly removing and adding new features to the Instagram API
    *   This WILL cause the current implementation to not work in the near future.
    *   We have currently applied for the new Instagram Graph API permissions through Facebook API, but due to the updates
    *   They are not reviewing at this time.Once they review the permissions, utilizing the graph call made in the
     */

    //this can be removed once Graph API has been implemented
    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getActivity().getAssets().open("generated.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public void Graphcall()
    {
        //This will need to get removed once the previous has been uncommented
        //start of removal
        try
        {

            JSONObject obj = new JSONObject(loadJSONFromAsset());

            JSONObject obj2 = obj.getJSONObject("data");

            JSONObject user = obj2.getJSONObject("user");

            JSONObject media = user.getJSONObject("edge_owner_to_timeline_media");
            JSONArray edges = media.getJSONArray("edges");

            if(edges.length() > 0) {
                for (int i = 0; i < edges.length(); i++) {
                    JSONObject edge = edges.getJSONObject(i);
                    JSONObject nodes1 = edge.getJSONObject("node");
                    JSONObject nodeEdges = nodes1.getJSONObject("edge_media_to_caption");
                    JSONArray nodeEdgesArray = nodeEdges.getJSONArray("edges");


                    JSONObject edge2 = nodeEdgesArray.getJSONObject(0);
                    JSONObject nodes2 = edge2.getJSONObject("node");

                    Model m = new Model();
                    m.setImage_url(nodes1.get("thumbnail_src").toString());
                    m.setContent(nodes2.getString("text"));
                    m.setComments(nodes1.getJSONObject("edge_media_to_comment").getString("count"));
                    m.setLikes(nodes1.getJSONObject("edge_media_preview_like").getString("count"));
                    list.add(m);

                }
            }
            profile_name = "nasapsyche";
            profile_img = "https://scontent-lax3-2.cdninstagram.com/vp/f511eb1fcd7e7a95bf4705a431480014/5B6E96D3/t51.2885-19/s150x150/19436619_321461971613655_167766018196766720_a.jpg";
            isnext = Boolean.parseBoolean(media.getJSONObject("page_info").getString("has_next_page"));
            nextid = media.getJSONObject("page_info").getString("end_cursor");

            a = new Adapter(getActivity(),list);

            rv.setAdapter(a);

            prog.setVisibility(View.GONE);

        }catch (Exception e){
            Log.d("Caught Exception", "onSuccess: ");

        }
        //end of removal

        /*
        uncomment this once Instagram_Basic Permission has been reviewed
    {
        new GraphRequest(
                accessToken,
                "/1598743977091187/feed?fields=instagram_business_account,media", null,
                HttpMethod.GET,
                new GraphRequest.Callback() {

                    @Override
                    public void onCompleted(GraphResponse graphResponse) {
                        try
                        {
                            final JSONObject jason = graphResponse.getJSONObject();
                            //Facebook is still updating the API so these JSON objects may or may not work anymore
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
     */
    }

    //used for constant updating, This will also need to get replaced with the previous Facebook graph API call, as you can see its almost identical
    public void loadmoredata(String newid)
    {

                try {
                    JSONObject obj = new JSONObject(loadJSONFromAsset());
                    JSONObject graphql = obj.getJSONObject("graphql");
                    Log.d(graphql.getJSONObject("user").toString(), "onSuccess1111: ");
                    JSONObject user = graphql.getJSONObject("user");
                    Log.d(user.getJSONObject("edge_owner_to_timeline_media").toString(), "onSuccess121212121: ");


                    JSONObject media = user.getJSONObject("edge_owner_to_timeline_media");
                    Log.d( media.getJSONArray("edges").toString(), "onSuccess2121212121: ");
                    JSONArray edges = media.getJSONArray("edges");

                    if(edges.length() > 0) {
                        for (int i = 0; i < edges.length(); i++) {
                            JSONObject edge = edges.getJSONObject(i);
                            JSONObject nodes1 = edge.getJSONObject("node");
                            //JSONArray nodes2 = nodes1.getJSONArray("");
                            // Log.d(nodes1.getJSONObject("edge_media_to_caption").toString(), "onSuccess: ");
                            JSONObject nodeEdges = nodes1.getJSONObject("edge_media_to_caption");
                            Log.d(nodeEdges.getJSONArray("edges").toString(), "onSuccess: ");
                            JSONArray nodeEdgesArray = nodeEdges.getJSONArray("edges");


                            JSONObject edge2 = nodeEdgesArray.getJSONObject(0);
                            JSONObject nodes2 = edge2.getJSONObject("node");
                            //text is "caption"


                            Model m = new Model();
                            m.setImage_url(nodes1.get("thumbnail_src").toString());
                            m.setContent(nodes2.getString("text"));
                            m.setComments(nodes1.getJSONObject("edge_media_to_comment").getString("count"));
                            m.setLikes(nodes1.getJSONObject("edge_liked_by").getString("count"));
                            list.add(m);

                        }
                    }
                    isnext = Boolean.parseBoolean(media.getJSONObject("page_info").getString("has_next_page"));
                    nextid = media.getJSONObject("page_info").getString("end_cursor");

                    a.notifyDataSetChanged();

                }catch (Exception e){
                    Toast.makeText(getActivity(), ""+e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
}
