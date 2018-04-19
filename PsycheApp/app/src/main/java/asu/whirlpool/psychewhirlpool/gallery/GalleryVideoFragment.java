package asu.whirlpool.psychewhirlpool.gallery;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import asu.whirlpool.psychewhirlpool.R;
import asu.whirlpool.psychewhirlpool.gallery.videoClasses.VideoRecycleAdapter;

/**
 * GalleryVideoFragment handles the Video tab in the Gallery section of the Psyche App.
 * Thumbnails are displayed in a {@link RecyclerView} with a {@link GridLayoutManager}.
 * Additionally, the {@link VideoRecycleAdapter} is used to instantiate and display the
 * thumbnails.
 *
 * Videos are obtained and streamed from the YouTube Data API.
 *
 * @author      Erick Ramirez Cordero
 * @version     4/17/2018
 */
public class GalleryVideoFragment extends Fragment
{
    private final int NUMBER_OF_COLUMNS = 2;
    CountDownTimer mCountDownTimer;
    TextView connection;
    ProgressBar prog;
    String temp = "";

    /**
     * Initializes the Fragment. A {@link GridLayoutManager} is used with the {@link RecyclerView}.
     *
     * @return          Instantiated Gallery Image Fragment
     */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_gallery_videos, container, false);

        prog = (ProgressBar) view.findViewById(R.id.prog);
        connection = (TextView) view.findViewById(R.id.connection);
        prog.setVisibility(View.INVISIBLE);
        connection.setVisibility(View.INVISIBLE);

        ConstraintLayout constraintLayout = view.findViewById(R.id.constraintLayout);

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES)
        {
            constraintLayout.setBackgroundResource(R.drawable.background_timeline_dark);
        }
        else
        {
            constraintLayout.setBackgroundResource(R.drawable.background_timeline_light);
        }

        // Set up the GridLayoutManager and VideoRecycleAdapter
        RecyclerView recyclerView = view.findViewById(R.id.VideoRecyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this.getContext(), NUMBER_OF_COLUMNS));

        VideoRecycleAdapter videoAdapter = new VideoRecycleAdapter(view.getContext());
        recyclerView.setAdapter(videoAdapter);


        //Initial set up for connection progress bar
        connection.setText("");

        // Set up the GridLayoutManager and VideoRecycleAdapter
        mCountDownTimer = new CountDownTimer(8000, 1000)
        {
            @Override
            public void onTick(long l)
            {
                Long countdownSeconds = l / 1000;
                temp = String.format("%d", ((countdownSeconds % 86400) % 3600) % 60);
                if(countdownSeconds == 1 || countdownSeconds == 1000)
                {
                    connection.setText(R.string.internet_error);
                    prog.setVisibility(View.GONE);
                    mCountDownTimer.cancel();
                }
            }

            @Override
            public void onFinish()
            {
                mCountDownTimer.cancel();
            }
        };

        boolean connected = false;
        view.getContext();

        //Checks to see if the phone has network connection
        ConnectivityManager connectivityManager = (ConnectivityManager) view.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        assert connectivityManager != null;

        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            connected = true;
            recyclerView.setVisibility(View.VISIBLE);
        }
        else {
            connected = false;
            recyclerView.setVisibility(View.INVISIBLE);
            prog.setVisibility(View.VISIBLE);
            connection.setVisibility(View.VISIBLE);
            mCountDownTimer.start();
        }

        return view;
    }
}
