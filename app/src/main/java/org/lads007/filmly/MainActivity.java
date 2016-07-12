package org.lads007.filmly;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

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
            String basicSearch = search[0].replace(" ", "+");
            String url = IMDB_BASE + basicSearch;
            Log.w("url", url);
            return new WebRequest().makeWebServiceCall(url, WebRequest.POSTRequest);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            ListView resultsShow = (ListView) findViewById(R.id.listView);
            List<String> list = new ArrayList<String>();
            try {
                JSONObject searchResult = new JSONObject(s);
                JSONArray results = searchResult.getJSONArray("Search");
                Log.i("results", results.toString());

                for (int i = 0; i < results.length(); i++) {
                    list.add(results.getJSONObject(i).getString("Title"));
                }
            } catch (JSONException e) {
                Toast toast = Toast.makeText(MainActivity.this, "A problem occured", Toast.LENGTH_SHORT);
                toast.show();
            }
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                    MainActivity.this,
                    android.R.layout.simple_list_item_1,
                    list);
            Log.i("list", list.toString());
            resultsShow.setAdapter(arrayAdapter);
        }

    }
}
