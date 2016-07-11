package org.lads007.filmly;

import android.os.AsyncTask;

/**
 * Created by lads on 11/07/16.
 */
public class SearchIMDB extends AsyncTask<String, Void, String> {
    private final String IMDB_BASE = "http://www.omdbapi.com/?s=";
    @Override
    protected String doInBackground(String... search) {
        // params comes from the execute() call: params[0] is the url.
        String url = IMDB_BASE + search[0];
        return new WebRequest().makeWebServiceCall(url, WebRequest.GETRequest);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }

}
