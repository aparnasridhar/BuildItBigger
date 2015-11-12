package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.coderbloc.aparnasridhar.androidjokeslib.DisplayJokesActivity;

//Project Number 878058630724
//API Key AIzaSyAYHPM-VUe8MqZ4vMJ3TKvSrzSaM3y1CFE
/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements JokesAsyncTaskCompletionListener {

    //Based on http://www.tutorialspoint.com/android/android_loading_spinner.htm
    ProgressBar progressBar;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        //Progress bar when the joke loads
        progressBar = (ProgressBar) root.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
        final JokesAsyncTask task = new JokesAsyncTask(this);

        //Launch the GCE when the jokes button is clicked
        Button jokeButton = (Button)root.findViewById(R.id.jokeButton);
        jokeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                task.execute();
            }
        });

        return root;
    }

    public void onResult(String joke){
        if(progressBar != null){
            progressBar.setVisibility(View.GONE);
        }
        Intent mIntent = new Intent(getActivity(), DisplayJokesActivity.class);
        mIntent.putExtra("joke", joke);
        getActivity().startActivity(mIntent);
    }

}
