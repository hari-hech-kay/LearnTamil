package com.example.android.miwok;


import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word>{

    private int colorResourceId;

    public WordAdapter(Activity context, ArrayList<Word> words, int colorResId) {

        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, words);
        colorResourceId= colorResId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_items, parent, false);
        }

         Word currentWord = getItem(position);

        TextView miwokTextView = (TextView) listItemView.findViewById(R.id.miwok_text_view);
        miwokTextView.setText(currentWord.getTamilTranslation());

        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.default_text_view);
        defaultTextView.setText(currentWord.getDefaultTranslation());

        ImageView visualImageView = (ImageView) listItemView.findViewById(R.id.image_view);

        if(currentWord.hasImage()) {
            visualImageView.setImageResource(currentWord.getImageResourceId());
            visualImageView.setVisibility(View.VISIBLE);
        }
        else { visualImageView.setVisibility(View.GONE);}

        View itemContainer = listItemView.findViewById(R.id.item_container);
        int color= ContextCompat.getColor(getContext(), colorResourceId);
        itemContainer.setBackgroundColor(color);

        return listItemView;
    }
}
