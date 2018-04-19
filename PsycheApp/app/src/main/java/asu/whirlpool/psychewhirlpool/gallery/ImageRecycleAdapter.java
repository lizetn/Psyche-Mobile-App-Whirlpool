package asu.whirlpool.psychewhirlpool.gallery;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import asu.whirlpool.psychewhirlpool.R;

/**
 * {@link ImageRecycleAdapter} is an implementation of {@link RecyclerView.Adapter}.
 * This adapter handles initialization of the {@link ImageView} used for images in the Gallery.
 *
 * @author      Erick Ramirez Cordero
 * @version     4/16/2018
 */
public class ImageRecycleAdapter extends RecyclerView.Adapter<ImageViewHolder>
{
    public static final String URL_KEY = "url_key";
    public static final String DESCRIPTION_KEY = "description_key";
    public static final int RES_ERROR = -1;

    private LayoutInflater mLayoutInflater;
    private TypedArray imageThumbnails;
    private String[] imageUrls;
    private TypedArray imageDescriptions;

    /**
     * Instantiate images and descriptions based on information stored in the
     * image_descriptions XML document.
     */
    public ImageRecycleAdapter(Context context)
    {
        mLayoutInflater = LayoutInflater.from(context);

        Resources resources = context.getResources();
        imageThumbnails = resources.obtainTypedArray(R.array.image_thumbnails);
        imageUrls = resources.getStringArray(R.array.image_urls);
        imageDescriptions = resources.obtainTypedArray(R.array.image_description_array);
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
        final int resId = imageThumbnails.getResourceId(position, RES_ERROR);
        final String imageUrl = imageUrls[position];
        final CharSequence desId = imageDescriptions.getText(position);

        holder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(view.getContext(), FullImageActivity.class);
                intent.putExtra(URL_KEY, imageUrl);
                intent.putExtra(DESCRIPTION_KEY, desId);
                view.getContext().startActivity(intent);
            }
        });

        holder.imageView.setImageResource(resId);
    }

    /**
     * Recycle the {@link TypedArray} used for image thumbnails and descriptions
     */
    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView)
    {
        super.onDetachedFromRecyclerView(recyclerView);
        imageThumbnails.recycle();
        imageDescriptions.recycle();
    }

    /**
     * @return  Amount of image ids stored
     */
    @Override
    public int getItemCount()
    {
        return imageThumbnails.length();
    }
}
