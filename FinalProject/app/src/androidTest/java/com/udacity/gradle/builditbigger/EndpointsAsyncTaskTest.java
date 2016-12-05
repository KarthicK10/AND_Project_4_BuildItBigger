package com.udacity.gradle.builditbigger;

import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

/**
 * Created by KarthicK on 12/4/2016.
 */

@RunWith(AndroidJUnit4.class)
public class EndpointsAsyncTaskTest{

    private static final String LOG_TAG = EndpointsAsyncTaskTest.class.getSimpleName();

    @Test
    public void testJoke(){

        EndpointsAsyncTask.JokeCallBack jokeCallBack = new EndpointsAsyncTask.JokeCallBack(){
            @Override @Test
            public void processJoke(String joke) {
                assertNotNull(joke, "joke returned is null");
                assertTrue("empty joke", jokeValid(joke));
            }
        };

        EndpointsAsyncTask asyncTask = new EndpointsAsyncTask(jokeCallBack);
        try{
            String joke = asyncTask.execute().get();
        }
        catch (Exception e){
            Log.e(LOG_TAG, e.getMessage());
        }
    }

    private static boolean jokeValid(String joke){
        if(joke != null && !joke.equals(""))
            return true;
        return false;
    }

}