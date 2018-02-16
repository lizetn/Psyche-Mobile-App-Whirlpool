package asu.whirlpool.psychewhirlpool.gallery;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import asu.whirlpool.psychewhirlpool.R;

/**
 * ImageAdapter handles the initialization of all images for the Gallery.
 *
 * Created by Diana on 11/20/2017.
 *
 * Fixed a bug where the activity would freeze when too many images were loaded.
 * Bug: Images will overlap on smaller screens due to fixed image size
 * @author      Erick Ramirez Cordero
 * @version     1/10/2018
 */
public class ImageAdapter extends BaseAdapter
{
    private final int PADDING = 8;
    private Context mContext;

    public int[] images = {
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

    public ImageAdapter(Context c)
    {
        mContext = c;
    }

    public int getCount()
    {
        return images.length;
    }

    public Object getItem(int position)
    {
        return images[position];
    }

    public long getItemId(int position)
    {
        return 0;
    }

    /**
     * Create a new ImageView for each item referenced by the Adapter
     * @param position          The index of the image in the images array
     * @return                  New ImageView with image set
     */
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ImageView imageView;

        if (convertView == null)
        {
            imageView = new ImageView(mContext);

            // Params should NOT be fixed or images will overlap on smaller screens
            imageView.setLayoutParams(new GridView.LayoutParams(250, 250));

            // Set padding to prevent images from overlapping
            imageView.setPadding(PADDING, PADDING, PADDING, PADDING);
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        }
        else
        {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(images[position]);
        return imageView;
    }
}
