package asu.whirlpool.psychewhirlpool.gallery;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import asu.whirlpool.psychewhirlpool.R;

/**
 * Custom implementation of {@link RecyclerView.ViewHolder} that handles an {@link ImageView}.
 * This view holder is meant to be used in the Gallery section of the Psyche App.
 *
 * @author      Erick Ramirez Cordero
 * @version     2/15/2018
 */
public class ImageViewHolder extends RecyclerView.ViewHolder
{
    public ImageView imageView;

    public ImageViewHolder(View itemView)
    {
        super(itemView);
        imageView = itemView.findViewById(R.id.imageView);
    }
}
