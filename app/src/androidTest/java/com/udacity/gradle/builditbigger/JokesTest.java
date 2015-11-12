package com.udacity.gradle.builditbigger;

import android.test.AndroidTestCase;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by aparnasridhar on 11/4/15.
 */
//Based on the stackoverflow recommendation for AsyncTask Testing
//http://stackoverflow.com/questions/2321829/android-asynctask-testing-with-android-test-framework
public class JokesTest extends AndroidTestCase implements JokesAsyncTaskCompletionListener {
    CountDownLatch signal = null;
    String joke;
    JokesAsyncTask task;

    @Override
    protected void setUp() throws Exception {
        signal = new CountDownLatch(1);
        task = new JokesAsyncTask(this);
    }

    @Override
    protected void tearDown() throws Exception {
        signal.countDown();
    }

    public void testAsync() throws InterruptedException {

        task.execute();
        //Wait for the task to finish before checking output
        signal.await(30, TimeUnit.SECONDS);
        assertTrue("This joke is valid", joke != null);

    }
    //called by AsyncTask once the task completes
    public void onResult(String result){
        joke = result;
        assertTrue("This joke is valid", joke != null);
        signal.countDown();
    }

}