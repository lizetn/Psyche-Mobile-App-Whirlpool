package asu.whirlpool.psychewhirlpool.timeline;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import asu.whirlpool.psychewhirlpool.R;

/**
 * {@link TimelineRecycleAdapter} is an implementation of {@link RecyclerView.Adapter}.
 * This adapter handles initialization of the {@link TextView} used for images in the Gallery.
 *
 * @author      Erick Ramirez Cordero
 * @version     3/7/2018
 */
public class TimelineRecycleAdapter extends RecyclerView.Adapter<TextViewHolder>
{
    public static final String TITLE_KEY = "title_key";
    public static final String DATA_KEY = "text_key";
    public static final String IMAGE_KEY = "image_key";

    private LayoutInflater mLayoutInflater;
    private String[] summaryData;
    private String[] detailData;

    // TODO: Set up arrays for each phase to have their own set of images
    private int[] imageData = {
            R.drawable.thumbnail_1, R.drawable.thumbnail_2,
            R.drawable.thumbnail_3, R.drawable.thumbnail_4,
            R.drawable.thumbnail_5, R.drawable.nasa_meatball_white,
    };
    private int imageId;

    public TimelineRecycleAdapter(Context context, char phaseLetter)
    {
        mLayoutInflater = LayoutInflater.from(context);

        switch(phaseLetter)
        {
            case TimelineTab.PHASE_A:
                summaryData = context.getResources().getStringArray(R.array.phase_A_summary);
                detailData = context.getResources().getStringArray(R.array.phase_A_details);
                imageId = imageData[0];
                break;
            case TimelineTab.PHASE_B:
                summaryData = context.getResources().getStringArray(R.array.phase_B_summary);
                detailData = context.getResources().getStringArray(R.array.phase_B_details);
                imageId = imageData[1];
                break;
            case TimelineTab.PHASE_C:
                summaryData = context.getResources().getStringArray(R.array.phase_C_summary);
                detailData = context.getResources().getStringArray(R.array.phase_C_details);
                imageId = imageData[2];
                break;
            case TimelineTab.PHASE_D:
                summaryData = context.getResources().getStringArray(R.array.phase_D_summary);
                detailData = context.getResources().getStringArray(R.array.phase_D_details);
                imageId = imageData[3];
                break;
            case TimelineTab.PHASE_E:
                summaryData = context.getResources().getStringArray(R.array.phase_E_summary);
                detailData = context.getResources().getStringArray(R.array.phase_E_details);
                imageId = imageData[4];
                break;
            case TimelineTab.PHASE_F:
                summaryData = context.getResources().getStringArray(R.array.phase_F_summary);
                detailData = context.getResources().getStringArray(R.array.phase_F_details);
                imageId = imageData[5];
                break;
            default:
                summaryData = new String[]{context.getString(R.string.error_message)};
                imageId = R.drawable.psyche_help_purple;
                break;
        }
    }

    /**
     * Instantiates a new {@link TextViewHolder} with a {@link TextView}
     */
    @Override
    public TextViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = mLayoutInflater.inflate(R.layout.recyclerview_text, parent, false);
        return new TextViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TextViewHolder holder, int position)
    {
        final String phaseInfo = summaryData[position];
        final String detailInfo = detailData[position];

        holder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(view.getContext(), TimelineNodeActivity.class);
                intent.putExtra(TITLE_KEY, phaseInfo);
                intent.putExtra(DATA_KEY, detailInfo);
                intent.putExtra(IMAGE_KEY, imageId);
                view.getContext().startActivity(intent);
            }
        });

        holder.textView.setText(phaseInfo);
    }

    @Override
    public int getItemCount()
    {
        return summaryData.length;
    }
}
