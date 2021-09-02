package com.astrex.bookworms;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;

public class CustomUserAdapter extends ArrayAdapter<Alphabet> {

    private static final String TAG = "MyActivity";

    public CustomUserAdapter(Context context, ArrayList<Alphabet> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_user, parent, false);
        }

        // Get the data item for this position
        Alphabet user = getItem(position);

        // Lookup view for data population
        //TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
        //TextView tvHome = (TextView) convertView.findViewById(R.id.tvHometown);
        ImageView tvImage = (ImageView) convertView.findViewById(R.id.ivUserIcon);
        int id = getContext().getResources().getIdentifier(user.getName(), "drawable", getContext().getPackageName());
        //tvImage.setImageResource(R.drawable.a);
        tvImage.setImageResource(id);
        tvImage.setTag(position);
        //tvImage.setClickable(true);

        /*tvImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = (Integer) view.getTag();
                // Access the row position here to get the correct data item
                Alphabet user = getItem(position);

                Log.d("TTS", "User : "+ user.getName());
                // Do what you want here...
                //Log.v(TAG,"Hello");
                Log.d("== My activity ===","OnClick is called "+ position);
                //Toast.makeText(view.getContext(), // <- Line changed
                //        "The favorite list would appear on clicking this icon",
                //        Toast.LENGTH_LONG).show();
                //MainActivity.speak();
                MainActivity x = (MainActivity) view.getContext();
                x.speakex(user.getText());
            }
        });*/

        Button button = (Button) convertView.findViewById(R.id.button);
        button.setTag(position);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Animation myAnim = AnimationUtils.loadAnimation(view.getContext(), R.anim.bounce);
                BounceInterpolator interpolator = new BounceInterpolator(0.2, 20);
                myAnim.setInterpolator(interpolator);
                button.startAnimation(myAnim);
                MainActivity x = (MainActivity) view.getContext();
                int position = (Integer) view.getTag();
                // Access the row position here to get the correct data item
                Alphabet user = getItem(position);
                x.speakex(user.getText());
            }
        });

        // Populate the data into the template view using the data object
        //tvName.setText(user.getName());
        //tvHome.setText(user.getHometown());
        //tvImage.setImageURI(Uri.parse("a.png"));
        // Return the completed view to render on screen
        return convertView;
    }
}
