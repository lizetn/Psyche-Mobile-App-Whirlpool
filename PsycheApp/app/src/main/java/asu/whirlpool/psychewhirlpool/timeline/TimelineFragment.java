package asu.whirlpool.psychewhirlpool.timeline;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import asu.whirlpool.psychewhirlpool.R;

/**
 * TimelineFragment handles the display of information in the Timeline section of the Psyche
 * app.
 *
 * @author  Erick Ramirez Cordero
 * @date    1/2/2018
 */
public class TimelineFragment extends Fragment
{
    private View view;

    /**
     * Initializes the Fragment with information about the time currently selected.
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.activity_timeline_fragment, container, false);

        // Set timeline information to tab currently selected
        Bundle args = getArguments();
        TextView textView = view.findViewById(R.id.timeInfoView);
        String phase = args.getString(TimelineTab.PHASE_KEY);

        switch (phase)
        {
            case TimelineTab.PHASE_PAST:
                textView.setText(R.string.timeline_past_info);
                break;
            case TimelineTab.PHASE_PRESENT:
                textView.setText(R.string.timeline_present_info);
                break;
            case TimelineTab.PHASE_FUTURE:
                textView.setText(R.string.timeline_future_info);
                break;
            default:
                textView.setText(R.string.error_message);
                break;
        }

        return view;
    }
}
