package asu.whirlpool.psychewhirlpool.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import asu.whirlpool.psychewhirlpool.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * Use the {@link SecondIntroFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SecondIntroFragment extends Fragment {
    private static final String TAG_BEGIN = "beginning";
    private static final String TAG_END = "ending";

    private String mTagBegin;
    private String mTagEnd;

    public SecondIntroFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param tagBeginning Parameter 1.
     * @param tagEnding Parameter 2.
     * @return A new instance of fragment SecondIntroFragment.
     */
    public static SecondIntroFragment newInstance(String tagBeginning, String tagEnding) {
        SecondIntroFragment fragment = new SecondIntroFragment();
        Bundle args = new Bundle();
        args.putString(TAG_BEGIN, tagBeginning);
        args.putString(TAG_END, tagEnding);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTagBegin = getArguments().getString(TAG_BEGIN);
            mTagEnd = getArguments().getString(TAG_END);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_second_intro, container, false);

        TextView beginTextView = (TextView) view.findViewById(R.id.tagLineBeginning);
        beginTextView.setText(getArguments().getString(TAG_BEGIN));

        TextView endTextView = (TextView) view.findViewById(R.id.tagLineEnding);
        endTextView.setText(getArguments().getString(TAG_END));

        return view;
    }

}
