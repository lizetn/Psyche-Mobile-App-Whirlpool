package asu.whirlpool.psychewhirlpool;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.BaseAdapter;
import android.widget.ImageView;

/**
 * ImageAdapter handles the initialization of all images for the Gallery.
 *
 * Created by Diana on 11/20/2017.
 *
 * Fixed a bug where the activity would freeze when too many images were loaded.
 * Bug: Images will overlap on smaller screens due to fixed image size
 * @revision    Erick Ramirez Cordero
 * @date        1/10/2018
 */
public class ImageAdapter extends BaseAdapter
{
    private final int PADDING = 8;
    private Context mContext;

    public Integer[] images = {
            R.drawable.thumbnail_1, R.drawable.thumbnail_2,
            R.drawable.thumbnail_3, R.drawable.thumbnail_4,
            R.drawable.thumbnail_5, R.drawable.image_1,
            R.drawable.image_2, R.drawable.image_3,
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

    // Create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ImageView imageView = new ImageView(mContext);
        imageView.setImageResource(images[position]);

        // Params should NOT be fixed or images will overlap on smaller screens
        imageView.setLayoutParams(new GridView.LayoutParams(250, 250));

        // Set padding to prevent images from overlapping
        imageView.setPadding(PADDING, PADDING, PADDING, PADDING);
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        return imageView;
    }
}
