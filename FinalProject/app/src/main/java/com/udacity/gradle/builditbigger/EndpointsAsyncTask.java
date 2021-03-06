package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;

import com.example.karthick.builditbigger.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import java.io.IOException;

/**
 * Created by KarthicK on 12/4/2016.
 */

class EndpointsAsyncTask extends AsyncTask<Void, Void, String> {
    private static MyApi myApiService = null;
    private JokeCallBack callBack;

    public EndpointsAsyncTask(JokeCallBack callBack ){
        this.callBack = callBack;
    }

    @Override
    protected String doInBackground(Void... voids) {
        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://builditbiggergce.appspot.com/_ah/api/");
            // end options for devappserver

            myApiService = builder.build();
        }

        try {
            return myApiService.getJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        //Toast.makeText(context, result, Toast.LENGTH_LONG).show();
        callBack.processJoke(result);
    }

    public interface JokeCallBack {
        public void processJoke(String joke);
    }
}
