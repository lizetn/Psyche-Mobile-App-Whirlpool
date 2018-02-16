package asu.whirlpool.psychewhirlpool.gallery;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import asu.whirlpool.psychewhirlpool.R;

/**
 * {@link ImageViewHolder} is an implementation of {@link RecyclerView.Adapter}. This adapter
 * handles initialization of the {@link ImageView} used for each image in the Gallery.
 *
 * @author      Erick Ramirez Cordero
 * @version     2/15/2018
 */
public class ImageRecycleAdapter extends RecyclerView.Adapter<ImageRecycleAdapter.ImageViewHolder>
{
    public static final String IMAGE_KEY = "image_key";

    private LayoutInflater mLayoutInflater;

    private int[] imageData = {
            R.drawable.thumbnail_1, R.drawable.thumbnail_2,
            R.drawable.thumbnail_3, R.drawable.thumbnail_4,
            R.drawable.thumbnail_5, R.drawable.game_icon,
            R.drawable.game_icon, R.drawable.game_icon,
            R.drawable.game_icon, R.drawable.game_icon,
            R.drawable.game_icon, R.drawable.game_icon,
            R.drawable.game_icon, R.drawable.game_icon,
            R.drawable.game_icon, R.drawable.game_icon,
            R.drawable.game_icon, R.drawable.game_icon,
            R.drawable.game_icon, R.drawable.game_icon,
            R.drawable.game_icon, R.drawable.game_icon,
            R.drawable.game_icon, R.drawable.game_icon
    };

    public ImageRecycleAdapter(Context context)
    {
        mLayoutInflater = LayoutInflater.from(context);
    }

    /**
     * Instantiates a new {@link ImageViewHolder} with an {@link ImageView}
     */
    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = mLayoutInflater.inflate(R.layout.recyclerview_image, parent, false);
        return new ImageViewHolder(view);
    }

    /**
     * Sets an {@link ImageViewHolder} to a specific image.
     *
     * Also sets an {@link android.view.View.OnClickListener} that will display
     * a full screen version of its respective image when selected.
     *
     * @param holder        An {@link ImageViewHolder}
     * @param position      Position in image resources array
     */
    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position)
    {
        final int resId = imageData[position];

        holder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(view.getContext(), FullImageActivity.class);
                intent.putExtra(IMAGE_KEY, resId);
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
        return imageData.length;
    }

    /**
     * Custom implementation of {@link RecyclerView.ViewHolder} that handles an {@link ImageView}.
     *
     * @author      Erick Ramirez Cordero
     * @version     2/15/2018
     */
    class ImageViewHolder extends RecyclerView.ViewHolder
    {
        ImageView imageView;

        ImageViewHolder(View itemView)
        {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
