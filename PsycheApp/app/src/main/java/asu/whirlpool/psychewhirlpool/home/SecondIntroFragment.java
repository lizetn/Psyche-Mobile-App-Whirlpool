package asu.whirlpool.psychewhirlpool.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import asu.whirlpool.psychewhirlpool.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * Use the {@link SecondIntroFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 *
 */
public class SecondIntroFragment extends Fragment {
    private static final String LINE1 = "1";
    private static final String LINE2 = "2";
    private static final String LINE3 = "3";
    private static final String LINE4 = "4";
    private static final String LINE5 = "5";

    private String mLine1;
    private String mLine2;
    private String mLine3;
    private String mLine4;
    private String mLine5;

    public SecondIntroFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param line1 First line of text.
     * @param line2 Second line of text.
     * @return A new instance of fragment SecondIntroFragment.
     */
    public static SecondIntroFragment newInstance(String line1, String line2, String line3,
                                                String line4, String line5) {
        SecondIntroFragment fragment = new SecondIntroFragment();
        Bundle args = new Bundle();
        args.putString(LINE1, line1);
        args.putString(LINE2, line2);
        args.putString(LINE3, line3);
        args.putString(LINE4, line4);
        args.putString(LINE5, line5);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mLine1 = getArguments().getString(LINE1);
            mLine2 = getArguments().getString(LINE2);
            mLine3 = getArguments().getString(LINE3);
            mLine4 = getArguments().getString(LINE4);
            mLine5 = getArguments().getString(LINE5);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_second_intro, container, false);

        TextView mQuote1 = (TextView) view.findViewById(R.id.quote1);
        mQuote1.setText(getArguments().getString(LINE1));

        TextView mQuote2 = (TextView) view.findViewById(R.id.quote2);
        mQuote2.setText(getArguments().getString(LINE2));

        TextView mQuote3 = (TextView) view.findViewById(R.id.quote3);
        mQuote3.setText(getArguments().getString(LINE3));

        TextView mQuote4 = (TextView) view.findViewById(R.id.quote4);
        mQuote4.setText(getArguments().getString(LINE4));

        TextView mQuote5 = (TextView) view.findViewById(R.id.quote5);
        mQuote5.setText(getArguments().getString(LINE5));

        return view;
    }

}
