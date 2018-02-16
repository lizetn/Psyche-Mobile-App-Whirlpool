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
 * {@link ImageViewHolder} is an implementation of {@link RecyclerView.Adapter}.
 *
 * @author      Erick Ramirez Cordero
 * @date        2/8/2018
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

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = mLayoutInflater.inflate(R.layout.recyclerview_image, parent, false);
        return new ImageViewHolder(view);
    }

    /**
     * ???
     *
     * @param holder        An {@link ImageViewHolder}
     * @param position      Position in image resources array
     */
    @Override
    public void onBindViewHolder(ImageViewHolder holder, final int position)
    {
        final int resId = imageData[position];

        holder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), FullImageActivity.class);
                intent.putExtra(IMAGE_KEY, position);
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
     * Custom implementation of {@link RecyclerView.ViewHolder} with a {@link View.OnClickListener}.
     *
     * @author  Erick Ramirez Cordero
     * @date    2/14/2018.
     */
    public class ImageViewHolder extends RecyclerView.ViewHolder
    {
        ImageView imageView;

        ImageViewHolder(View itemView)
        {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
