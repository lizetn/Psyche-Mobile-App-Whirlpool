package asu.whirlpool.psychewhirlpool.gallery.videoClasses;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import asu.whirlpool.psychewhirlpool.R;
import asu.whirlpool.psychewhirlpool.gallery.ImageViewHolder;

/**
 * {@link VideoRecycleAdapter} is an implementation of {@link RecyclerView.Adapter}.
 * This adapter handles initialization of video thumbnails displayed in the Video section of
 * the Gallery.
 *
 * Video thumbnails are retrieved from YouTube.
 *
 * @author      Erick Ramirez Cordero
 * @version     3/26/2018
 */

public class VideoRecycleAdapter extends RecyclerView.Adapter<ImageViewHolder>
{
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public static final String VIDEO_KEY = "VIDEO_KEY";
    private final String THUMBNAIL_URI_START = "http://img.youtube.com/vi/";
    private final String THUMBNAIL_URI_END = "/hqdefault.jpg";


    private String[] videoIDs = {
            "cSMhurC_fm0", "h2pm1RPY6Bc",
            "jOvNzYMfkJo", "bxhcddsmA3o",
            "jOZNIrxI3MI", "JX8m2UJClsw",
            "6i4UbDUOq70", "7B8-mwJLulw"
    };

    public VideoRecycleAdapter(Context context)
    {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = mLayoutInflater.inflate(R.layout.recyclerview_image, parent, false);
        return new ImageViewHolder(view);
    }

    /**
     * Sets an {@link ImageViewHolder} to a specific YouTube thumbnail.
     *
     * Also sets an {@link android.view.View.OnClickListener} that will display the related
     * full screen video when selected.
     *
     * @param holder        An {@link ImageViewHolder}
     * @param position      Position in video resources array
     *
     */
    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position)
    {
        final String videoID = videoIDs[position];
        holder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent youTubeIntent = new Intent(view.getContext(), YouTubeActivity.class);
                youTubeIntent.putExtra(VIDEO_KEY, videoID);
                view.getContext().startActivity(youTubeIntent);
            }
        });

        String videoUri = THUMBNAIL_URI_START + videoID + THUMBNAIL_URI_END;
        Picasso.with(mContext).load(videoUri).into(holder.imageView);
    }

    /**
     * @return  Amount of image ids stored
     */
    @Override
    public int getItemCount()
    {
        return videoIDs.length;
    }
}
