package asu.whirlpool.psychewhirlpool.timeline;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import asu.whirlpool.psychewhirlpool.R;

/**
 * Custom implementation of {@link RecyclerView.ViewHolder} that handles an {@link TextView}.
 * This view holder is meant to be used in the Timeline section of the Psyche App.
 *
 * @author      Erick Ramirez Cordero
 * @version     3/7/2018
 */
public class TextViewHolder extends RecyclerView.ViewHolder
{
    public TextView textView;

    public TextViewHolder(View itemView)
    {
        super(itemView);
        textView = itemView.findViewById(R.id.textView);
    }
}
