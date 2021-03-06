package com.example.android.miwok.adapters;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.android.miwok.R;
import com.example.android.miwok.model.Word;

import java.util.List;

/**
 * Created by htenjo on 7/28/16.
 */
public class GraphicWordAdapter extends ArrayAdapter<Word>{
    private int colorId;


    public GraphicWordAdapter(Context context, List<Word> objects, int colorId) {
        super(context, 0, objects);
        this.colorId = colorId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;

        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item_with_icon, parent, false);
        }

        final Word currentWord = getItem(position);
        TextView englishTextView = (TextView) listItemView.findViewById(R.id.default_text_view);
        englishTextView.setText(currentWord.getEnglishTranslation());

        TextView miwokTextView = (TextView) listItemView.findViewById(R.id.miwok_text_view);
        miwokTextView.setText(currentWord.getMiwokTranslation());

        final MediaPlayer player = MediaPlayer.create(getContext(), currentWord.getSoundId());
        LinearLayout textLayout = (LinearLayout)listItemView.findViewById(R.id.text_layout);
        int realColor = ContextCompat.getColor(getContext(), this.colorId);
        textLayout.setBackgroundColor(realColor);

        // Find the ImageView in the list_item.xml layout with the ID list_item_icon
        ImageView iconView = (ImageView) listItemView.findViewById(R.id.miwok_image_view);
        iconView.setImageResource(currentWord.getIconId());
        return listItemView;
    }
}
