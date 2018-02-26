package asu.whirlpool.psychewhirlpool.InstagramClasses;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;
import asu.whirlpool.psychewhirlpool.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        View view = inflater.inflate(R.layout.instagramactivity_main, container, false);
        super.onCreate(savedInstanceState);

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
        rv.setHasFixedSize(true);
        rv.addOnScrollListener(listener);
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
                try {
                    JSONObject obj = new JSONObject(responseString);
                    JSONObject user = obj.getJSONObject("user");
                    JSONObject media = user.getJSONObject("media");
                    JSONArray nodes = media.getJSONArray("nodes");

                    profile_name = user.getString("username");
                    profile_img = user.getString("profile_pic_url");
                    for (int i=0;i<nodes.length();i++)
                    {
                        Model m = new Model();
                        m.setImage_url(nodes.getJSONObject(i).getString("thumbnail_src"));
                        m.setContent(nodes.getJSONObject(i).getString("caption"));
                        m.setComments(nodes.getJSONObject(i).getJSONObject("comments").getString("count"));
                        m.setLikes(nodes.getJSONObject(i).getJSONObject("likes").getString("count"));
                        list.add(m);
                    }
                    isnext = Boolean.parseBoolean(media.getJSONObject("page_info").getString("has_next_page"));
                    nextid = media.getJSONObject("page_info").getString("end_cursor");

                    a = new Adapter(getActivity(),list);

                    rv.setAdapter(a);

                    prog.setVisibility(View.GONE);

                }catch (Exception e){

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
                    JSONObject user = obj.getJSONObject("user");
                    JSONObject media = user.getJSONObject("media");
                    JSONArray nodes = media.getJSONArray("nodes");
                    for (int i=0;i<nodes.length();i++)
                    {
                        Model m = new Model();
                        m.setImage_url(nodes.getJSONObject(i).getString("thumbnail_src"));
                        m.setContent(nodes.getJSONObject(i).getString("caption"));
                        m.setComments(nodes.getJSONObject(i).getJSONObject("comments").getString("count"));
                        m.setLikes(nodes.getJSONObject(i).getJSONObject("likes").getString("count"));
                        list.add(m);

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
