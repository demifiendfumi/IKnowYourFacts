package com.myapplicationdev.android.iknowyourfacts;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.jsibbold.zoomage.ZoomageView;
import com.squareup.picasso.Picasso;

import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class Frag4 extends Fragment {

    Button btnChange;
    ZoomageView zv;
    String url[] = {"https://78.media.tumblr.com/9d401bb26bb82278dbcc3f4dda840360/tumblr_pccg9cDVVp1roqv59o1_500.png",
            "https://78.media.tumblr.com/59174c6ecd883dab416b59676c30b2fe/tumblr_pcaw1cXox31roqv59o1_500.png",
            "https://78.media.tumblr.com/032b8e72d8887fc67b39afe1117ac451/tumblr_pca72zjZjc1roqv59o1_500.png",
            "https://78.media.tumblr.com/b2d4ccd60d68bf9e96390a79d33f48e0/tumblr_pc8c4qTV591roqv59o1_500.png",
            "https://78.media.tumblr.com/ef7e7e925c532e43499b9e0a023292db/tumblr_pc70cfKtzf1roqv59o1_500.png"};

    public Frag4() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_4,
                container, false);
        btnChange = view.findViewById(R.id. btnChange4);
        zv = view.findViewById(R.id.myZoomageView);
        final Random randomNo = new Random();
        int randomLink = randomNo.nextInt(url.length);
        Log.d("random link", url[randomLink]);
        String imageurl = url[randomLink];
        Picasso.with(getActivity()).load(imageurl).into(zv);

        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.setBackgroundColor(Color.rgb(randomNo.nextInt(256), randomNo.nextInt(256), randomNo.nextInt(256)));
            }
        });

        return view;
    }

}
