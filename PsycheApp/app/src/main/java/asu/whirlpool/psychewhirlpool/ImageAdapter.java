package asu.whirlpool.psychewhirlpool;

/**
 * Created by Diana on 11/20/2017.
 */

import android.view.LayoutInflater;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;

    public Integer[] images = {
            R.drawable.thumbnail_1,R.drawable.thumbnail_2,
            R.drawable.thumbnail_3,R.drawable.thumbnail_4,
            R.drawable.thumbnail_5,R.drawable.image_1,
            R.drawable.image_2,R.drawable.image_3};

    public ImageAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return images.length;
    }

    public Object getItem(int position) {
        return images[position];
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = new ImageView(mContext);
        imageView.setImageResource(images[position]);
        imageView.setLayoutParams(new GridView.LayoutParams(350, 350));
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        return imageView;
    }

}
