package org.lads007.filmly;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by lads on 12/07/16.
 */
public class SearchResultsAdapter extends ArrayAdapter<String> {
    private Activity context;
    private List<String> title;
    private List<String> imageURL;
    private List<String> year;

    public SearchResultsAdapter(Activity context, List<String> title, List<String> imageURL, List<String> year) {
        super(context, R.layout.searchresults, title);

        this.context = context;
        this.title = title;
        this.imageURL = imageURL;
        this.year = year;

    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.searchresults, null,true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.title);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        TextView extratxt = (TextView) rowView.findViewById(R.id.year);

        txtTitle.setText(title.get(position));
        Picasso.with(context).load(imageURL.get(position)).into(imageView);
        extratxt.setText(year.get(position));
        return rowView;
    }
}
