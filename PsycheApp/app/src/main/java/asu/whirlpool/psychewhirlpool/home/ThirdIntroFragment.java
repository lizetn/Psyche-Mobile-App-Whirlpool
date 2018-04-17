package asu.whirlpool.psychewhirlpool.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import asu.whirlpool.psychewhirlpool.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * Use the {@link ThirdIntroFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ThirdIntroFragment extends Fragment {

    public ThirdIntroFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ThirdIntroFragment.
     */
    public static ThirdIntroFragment newInstance() {
        ThirdIntroFragment fragment = new ThirdIntroFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_third_intro, container, false);
    }
}
