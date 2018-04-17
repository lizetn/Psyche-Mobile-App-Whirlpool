package asu.whirlpool.psychewhirlpool.timeline;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import asu.whirlpool.psychewhirlpool.R;

/**
 * Custom implementation of {@link RecyclerView.ViewHolder} that handles a {@link TextView} and
 * node image. This view holder is meant to be used in the Timeline section of the Psyche App.
 *
 * @author      Erick Ramirez Cordero
 * @version     4/17/2018
 */
public class TextViewHolder extends RecyclerView.ViewHolder
{
    public TextView textView;
    public ImageView nodeImageView;

    public TextViewHolder(View itemView)
    {
        super(itemView);
        textView = itemView.findViewById(R.id.textView);
        nodeImageView = itemView.findViewById(R.id.nodeImageView);
    }
}
