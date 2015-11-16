package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.coderbloc.aparnasridhar.androidjokeslib.DisplayJokesActivity;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

//Project Number 878058630724
/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements JokesAsyncTaskCompletionListener,View.OnClickListener {

    //Based on http://www.tutorialspoint.com/android/android_loading_spinner.htm
    ProgressBar progressBar;

    //Based on https://developers.google.com/admob/android/interstitial
    InterstitialAd mInterstitialAd;

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

        //Interstetial Ad
        mInterstitialAd = new InterstitialAd(getActivity());
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
                progressBar.setVisibility(View.VISIBLE);
                if(task.getStatus() !=  AsyncTask.Status.RUNNING) {
                    task.execute();
                }
            }
        });

        //Launch the GCE when the jokes button is clicked
        Button jokeButton = (Button)root.findViewById(R.id.jokeButton);
        jokeButton.setOnClickListener(this);
        requestNewInterstitial();

        AdView mAdView = (AdView) root.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);
        return root;
    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("SEE_YOUR_LOGCAT_TO_GET_YOUR_DEVICE_ID")
                .build();

        mInterstitialAd.loadAd(adRequest);
    }

    public void onResult(String joke){
        if(progressBar !=null){
            progressBar.setVisibility(View.GONE);
        }
        Intent mIntent = new Intent(getActivity(), DisplayJokesActivity.class);
        mIntent.putExtra("joke", joke);
        getActivity().startActivity(mIntent);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.jokeButton){
            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();

            } else {
                progressBar.setVisibility(View.VISIBLE);
                new JokesAsyncTask(this).execute();
            }
        }
    }
}
