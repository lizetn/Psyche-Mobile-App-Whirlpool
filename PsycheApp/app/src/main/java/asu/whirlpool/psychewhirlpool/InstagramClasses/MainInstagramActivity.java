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
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;
import android.support.v4.app.ListFragment;
import org.json.JSONArray;
import org.json.JSONObject;

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
    public void Graphcall()
    {
        final AsyncHttpClient client = new AsyncHttpClient();
        client.get("https://www.instagram.com/nasapsyche/?__a=1", new TextHttpResponseHandler()
        {
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable)
            {
                Log.d("FAILS", "onFailure: ");
              //  Toast.makeText(getActivity(), ""+responseString, Toast.LENGTH_SHORT).show();
            }
            public void onSuccess(int statusCode, Header[] headers, String responseString)
            {
                Log.d(responseString, "onSuccess: ");
                try {
                    JSONObject obj = new JSONObject(responseString);
                    JSONObject graphql = obj.getJSONObject("graphql");
                    JSONObject user = graphql.getJSONObject("user");

                    JSONObject media = user.getJSONObject("edge_owner_to_timeline_media");
                    Log.d( media.getJSONArray("edges").toString(), "onSuccess2121212121: ");
                    JSONArray edges = media.getJSONArray("edges");

                    if(edges.length() > 0) {
                        for (int i = 0; i < edges.length(); i++) {
                            JSONObject edge = edges.getJSONObject(i);
                            JSONObject nodes1 = edge.getJSONObject("node");
                            JSONObject nodeEdges = nodes1.getJSONObject("edge_media_to_caption");
                            Log.d(nodeEdges.getJSONArray("edges").toString(), "onSuccess: ");
                            JSONArray nodeEdgesArray = nodeEdges.getJSONArray("edges");


                            JSONObject edge2 = nodeEdgesArray.getJSONObject(0);
                            JSONObject nodes2 = edge2.getJSONObject("node");


                            Model m = new Model();
                            m.setImage_url(nodes1.get("thumbnail_src").toString());
                            m.setContent(nodes2.getString("text"));
                            m.setComments(nodes1.getJSONObject("edge_media_to_comment").getString("count"));
                            m.setLikes(nodes1.getJSONObject("edge_liked_by").getString("count"));
                            list.add(m);

                        }
                    }
                    profile_name = user.getString("username");
                    profile_img = user.getString("profile_pic_url");
                    isnext = Boolean.parseBoolean(media.getJSONObject("page_info").getString("has_next_page"));
                    nextid = media.getJSONObject("page_info").getString("end_cursor");

                    a = new Adapter(getActivity(),list);

                    rv.setAdapter(a);

                    prog.setVisibility(View.GONE);

                }catch (Exception e){
                    Log.d("NOT WORKING", "onSuccess: ");

                }
            }
        });
    }

    //used for constant updating
    public void loadmoredata(String newid)
    {
        final AsyncHttpClient client = new AsyncHttpClient();
        client.get(EndlessRecycleOnScrollListener.scroll+"&max_id="+newid, new TextHttpResponseHandler()
        {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable)
            {
                if(isnext) {
                    loadmoredata(nextid);
                }

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString)
            {
                try {
                    JSONObject obj = new JSONObject(responseString);
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
        });
    }
}
