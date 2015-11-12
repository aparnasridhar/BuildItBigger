package com.udacity.gradle.builditbigger;

import android.app.Application;
import android.test.ApplicationTestCase;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {

    //Based on a CountDownLatch as illustrated in
    //http://stackoverflow.com/questions/2321829/android-asynctask-testing-with-android-test-framework
    public ApplicationTest()
    {
        super(Application.class);

    }


}