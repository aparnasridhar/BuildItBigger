package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;

import com.example.aparnasridhar.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

/**
 * Created by aparnasridhar on 11/4/15.
 */
public class JokesAsyncTask extends AsyncTask<Void,Void,String> {

    private MyApi myApiService = null;
    JokesAsyncTaskCompletionListener jokesListener;

    public JokesAsyncTask(JokesAsyncTaskCompletionListener listener){
        jokesListener = listener;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
    @Override
    protected String doInBackground(Void... params) {

        //Based on https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
        if(myApiService == null) {
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            myApiService = builder.build();
        }

        try {
            return myApiService.getJoke().execute().getData();
        } catch (Exception e) {
            return e.getMessage();
        }


    }

    @Override
    protected void onPostExecute(String result) {
        //Launch Activity to display joke

        jokesListener.onResult(result);
    }
}