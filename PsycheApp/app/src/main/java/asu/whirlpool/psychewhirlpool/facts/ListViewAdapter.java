package asu.whirlpool.psychewhirlpool.facts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import asu.whirlpool.psychewhirlpool.R;

/**
 * {@link ListViewAdapter} sets the images displayed in the FAQ menu.
 *
 * @author      Diana Chavez
 * @version     3/18/2018
 */

public class ListViewAdapter extends BaseAdapter
{
    String[] faqoptions;
    LayoutInflater layoutListView;

    int[] faqImages;

    public ListViewAdapter(Context context)
    {
        layoutListView = LayoutInflater.from(context);
        faqoptions = context.getResources().getStringArray(R.array.faq_options);
    }

    public ListViewAdapter(Context context, int[] data)
    {
        this(context);
        faqImages = data;
    }

    @Override
    public int getCount()
    {
        return faqoptions.length;
    }

    @Override
    public Object getItem(int position)
    {
        return faqImages[position];
    }

    @Override
    public long getItemId(int position)
    {
        return faqImages[position];
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View view = convertView;

        if(view == null)
        {
            view = layoutListView.inflate(R.layout.facts_listview, parent, false);
        }

        TextView faqTitles = view.findViewById(R.id.titleListView);
        ImageView faqImageView = view.findViewById(R.id.background);

        faqTitles.setText(faqoptions[position]);
        faqImageView.setImageResource(faqImages[position]);

        return view;
    }
}
