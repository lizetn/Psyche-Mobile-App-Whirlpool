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
 * @version     4/17/2018
 */
public class TimelineRecycleAdapter extends RecyclerView.Adapter<TextViewHolder>
{
    public static final String TITLE_KEY = "title_key";
    public static final String DATA_KEY = "text_key";

    private LayoutInflater mLayoutInflater;
    private String[] summaryData;
    private String[] detailData;
    private int nodeResource;

    public TimelineRecycleAdapter(Context context, char phaseLetter, int[] nodeData)
    {
        mLayoutInflater = LayoutInflater.from(context);

        switch(phaseLetter)
        {
            case TimelineTab.PHASE_A:
                summaryData = context.getResources().getStringArray(R.array.phase_A_summary);
                detailData = context.getResources().getStringArray(R.array.phase_A_details);
                nodeResource = nodeData[0];
                break;
            case TimelineTab.PHASE_B:
                summaryData = context.getResources().getStringArray(R.array.phase_B_summary);
                detailData = context.getResources().getStringArray(R.array.phase_B_details);
                nodeResource = nodeData[1];
                break;
            case TimelineTab.PHASE_C:
                summaryData = context.getResources().getStringArray(R.array.phase_C_summary);
                detailData = context.getResources().getStringArray(R.array.phase_C_details);
                nodeResource = nodeData[2];
                break;
            case TimelineTab.PHASE_D:
                summaryData = context.getResources().getStringArray(R.array.phase_D_summary);
                detailData = context.getResources().getStringArray(R.array.phase_D_details);
                nodeResource = nodeData[2];
                break;
            case TimelineTab.PHASE_E:
                summaryData = context.getResources().getStringArray(R.array.phase_E_summary);
                detailData = context.getResources().getStringArray(R.array.phase_E_details);
                nodeResource = nodeData[2];
                break;
            case TimelineTab.PHASE_F:
                summaryData = context.getResources().getStringArray(R.array.phase_F_summary);
                detailData = context.getResources().getStringArray(R.array.phase_F_details);
                nodeResource = nodeData[2];
                break;
            default:
                summaryData = new String[]{context.getString(R.string.error_message)};
                nodeResource = nodeData[0];
                break;
        }
    }

    /**
     * Instantiates a new {@link TextViewHolder} with a {@link TextView}
     */
    @Override
    public TextViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = mLayoutInflater.inflate(R.layout.recyclerview_timeline_node, parent, false);
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
                view.getContext().startActivity(intent);
            }
        });

        holder.textView.setText(phaseInfo);
        holder.nodeImageView.setImageResource(nodeResource);
    }

    @Override
    public int getItemCount()
    {
        return summaryData.length;
    }
}
