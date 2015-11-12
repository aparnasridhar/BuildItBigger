package com.coderbloc.aparnasridhar.androidjokeslib;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class DisplayJokesFragment extends Fragment {

    public DisplayJokesFragment() {
    }

    //Simple fragment to display the joke passed from to it
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_display_jokes, container, false);
        TextView jokeView = (TextView) v.findViewById(R.id.joke);
        jokeView.setText(getActivity().getIntent().getStringExtra("joke"));

        return v;
    }
}
