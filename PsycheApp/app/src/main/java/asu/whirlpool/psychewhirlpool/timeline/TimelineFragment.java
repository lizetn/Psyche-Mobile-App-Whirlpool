package asu.whirlpool.psychewhirlpool.timeline;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import asu.whirlpool.psychewhirlpool.InstagramClasses.LinearLayoutManager;
import asu.whirlpool.psychewhirlpool.R;

/**
 * TimelineFragment handles the display of information in the Timeline section of the Psyche
 * app.
 *
 * @author      Erick Ramirez Cordero
 * @version     3/7/2018
 */
public class TimelineFragment extends Fragment
{
    /**
     * Initializes the Fragment with information about the time currently selected.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_timeline_phase, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.PhaseRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Set timeline information to tab currently selected
        Bundle args = getArguments();
        char phase = args.getChar(TimelineTab.PHASE_KEY);

        TimelineRecycleAdapter adapter = new TimelineRecycleAdapter(view.getContext(), phase);
        recyclerView.setAdapter(adapter);

        String[] titleData = view.getContext().getResources().getStringArray(R.array.phase_titles);
        TextView textView = view.findViewById(R.id.titleTextView);
        
        switch (phase)
        {
            case TimelineTab.PHASE_A:
                textView.setText(titleData[0]);
                break;
            case TimelineTab.PHASE_B:
                textView.setText(titleData[1]);
                break;
            case TimelineTab.PHASE_C:
                textView.setText(titleData[2]);
                break;
            case TimelineTab.PHASE_D:
                textView.setText(titleData[3]);
                break;
            case TimelineTab.PHASE_E:
                textView.setText(titleData[4]);
                break;
            case TimelineTab.PHASE_F:
                textView.setText(titleData[5]);
                break;
            default:
                textView.setText(R.string.error_message);
                break;
        }

        return view;
    }
}
