package org.lads007.filmly;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void searchFilm(View view) {
        EditText editText = (EditText) findViewById(R.id.find);
        String stringUrl = editText.getText().toString();
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            new SearchIMDB().execute(stringUrl);
        } else {
            Toast toast = Toast.makeText(this, "Network Error", Toast.LENGTH_SHORT);
            toast.show();
        }
    }


    //Creates thread to search IMDB through omdb
    //
    //
    private class SearchIMDB extends AsyncTask<String, Void, String> {
        private final String IMDB_BASE = "http://www.omdbapi.com/?s=";

        @Override
        protected String doInBackground(String... search) {
            // params comes from the execute() call: params[0] is the url.
            String url = IMDB_BASE + search[0];
            Log.w("url", url);
            return new WebRequest().makeWebServiceCall(url, WebRequest.POSTRequest);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                JSONArray jsonArray = new JSONArray(s);
                for (JSONObject jsonObject : jsonArray) {

                }
            } catch (JSONException e) {
                Toast toast = Toast.makeText(getApplicationContext(), "A problem occured", Toast.LENGTH_SHORT);
                toast.show();
            }
        }

    }
}
