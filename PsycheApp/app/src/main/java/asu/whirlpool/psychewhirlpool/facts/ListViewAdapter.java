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
 * {@link ListViewAdapter} sets the images and text displayed in the Mission Facts menu.
 *
 * @author      Diana Chavez
 * @version     3/18/2018
 */
public class ListViewAdapter extends BaseAdapter
{
    String[] faqoptions;
    LayoutInflater layoutListView;

    int[] faqImages;

    /**
     * Instantiate a ListView adapter that changes to fit the context and gets its information from
     * the a section of resource XML faq
     * @param context   the context of the app
     */
    public ListViewAdapter(Context context)
    {
        layoutListView = LayoutInflater.from(context);
        faqoptions = context.getResources().getStringArray(R.array.faq_options);
    }

    /**
     * Instantiate a ListView adapter that changes to fit the context and gets its information from
     * the a section of resource XML faq. It uses the parameter data to set the background images.
     * @param context   the context of the app
     * @param data      an int array that holds images
     */
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

    /**
     * Instantiate a ListView adapter that changes to fit the context and gets its information from
     * the a section of resource XML faq. It uses the parameter data to set the background images.
     * @param position      the position in of the ListView
     * @param convertView   the view of the app
     * @param parent        the base ViewGroup
     * @return view
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View view = convertView;

        //if nothing is in the view, manually build the view
        if(view == null)
        {
            view = layoutListView.inflate(R.layout.facts_listview, parent, false);
        }

        //Set up the title and the background image to be displayed by the ListView
        TextView faqTitles = view.findViewById(R.id.titleListView);
        ImageView faqImageView = view.findViewById(R.id.background);

        //Load the title of the ListView from the resource XMl faq and the image from a predefined
        //int[]
        faqTitles.setText(faqoptions[position]);
        faqImageView.setImageResource(faqImages[position]);

        //Return the view to the ListView
        return view;
    }
}
