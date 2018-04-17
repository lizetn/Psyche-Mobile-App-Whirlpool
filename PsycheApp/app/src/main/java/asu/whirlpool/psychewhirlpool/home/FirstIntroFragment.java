package asu.whirlpool.psychewhirlpool.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import asu.whirlpool.psychewhirlpool.R;

/**
 * A simple {@link Fragment} subclass for the first page of the
 * introduction.
 * Use the {@link FirstIntroFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 * @author      Natalie Fleischaker
 * @version     3/18/2018
 */
public class FirstIntroFragment extends Fragment {
    private static final String TITLE = "title";
    private static final String SUBTITLE = "subtitle";

    private String mTitle;
    private String mSubtitle;

    //private OnFragmentInteractionListener mListener;

    public FirstIntroFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param title Text for the title TextView.
     * @param subtitle Text for the subtitle TextView.
     * @return A new instance of fragment FirstIntroFragment.
     */
    public static FirstIntroFragment newInstance(String title, String subtitle) {
        FirstIntroFragment fragment = new FirstIntroFragment();
        Bundle args = new Bundle();
        args.putString(TITLE, title);
        args.putString(SUBTITLE, subtitle);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Set get strings of the contents of the two TextViews
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTitle = getArguments().getString(TITLE);
            mSubtitle = getArguments().getString(SUBTITLE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_first_intro, container, false);

        TextView titleTextView = (TextView) view.findViewById(R.id.introTitle);
        titleTextView.setText(getArguments().getString(TITLE));

        TextView subtitleTextView = (TextView) view.findViewById(R.id.introSubtitle);
        subtitleTextView.setText(getArguments().getString(SUBTITLE));

        return view;
    }
}
