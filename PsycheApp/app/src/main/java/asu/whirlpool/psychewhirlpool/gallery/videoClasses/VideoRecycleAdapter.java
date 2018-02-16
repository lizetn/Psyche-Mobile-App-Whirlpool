package asu.whirlpool.psychewhirlpool.gallery.videoClasses;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import asu.whirlpool.psychewhirlpool.R;
import asu.whirlpool.psychewhirlpool.gallery.ImageViewHolder;

/**
 * {@link VideoRecycleAdapter} is an implementation of {@link RecyclerView.Adapter}.
 * This adapter handles initialization of video thumbnails displayed in the Video section of
 * the Gallery.
 *
 * @author      Erick Ramirez Cordero
 * @version     2/15/2018
 */

public class VideoRecycleAdapter extends RecyclerView.Adapter<ImageViewHolder>
{
    private LayoutInflater mLayoutInflater;
    private final String VIDEO_KEY = "video_key";

    private int[] thumbnailData = {
            R.drawable.game_icon, R.drawable.game_icon
    };

    public VideoRecycleAdapter(Context context)
    {
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = mLayoutInflater.inflate(R.layout.recyclerview_image, parent, false);
        return new ImageViewHolder(view);
    }

    /**
     * Sets an {@link ImageViewHolder} to a specific image.
     *
     * Also sets an {@link android.view.View.OnClickListener} that will display the related
     * full screen video when selected.
     *
     * @param holder        An {@link ImageViewHolder}
     * @param position      Position in video resources array
     *
     * TODO: The resId passed to {@link VideoActivity} should be the resource ID of a video, NOT an image!
     */
    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position)
    {
        final int resId = thumbnailData[position];

        holder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(view.getContext(), VideoActivity.class);
                intent.putExtra(VIDEO_KEY, resId);
                view.getContext().startActivity(intent);
            }
        });

        holder.imageView.setImageResource(resId);
    }

    /**
     * @return  Amount of image ids stored
     */
    @Override
    public int getItemCount()
    {
        return thumbnailData.length;
    }
}
