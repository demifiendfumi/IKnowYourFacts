package com.myapplicationdev.android.iknowyourfacts;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import com.crazyhitty.chdev.ks.rssmanager.RSS;
import com.crazyhitty.chdev.ks.rssmanager.RssReader;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Frag5 extends Fragment implements RssReader.RssCallback{

    RssReader rssReader = new RssReader(this);
    TextView txtTitle, txtTitle1, txtTitle2;
    WebView wv1, wv2;

    public Frag5() {
        // Required empty public constructor
    }

    //RssReader.RssCallback lk;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

//        lk = new RssReader.RssCallback() {
//            @Override
//            public void rssFeedsLoaded(List<RSS> rssList) {
//
//            }
//
//            @Override
//            public void unableToReadRssFeeds(String errorMessage) {
//
//            }
//        };

        //RssReader reader = new RssReader(lk);

        View view = inflater.inflate(R.layout.fragment_5, container, false);
        txtTitle = view.findViewById(R.id. txtTitle);
        txtTitle1 = view.findViewById(R.id. txtItemTitle);
        txtTitle2 = view.findViewById(R.id. txtItemTitle2);
        wv1 =view.findViewById(R.id. wv1);
        wv2 = view.findViewById(R.id. wv2);
        rssReader.loadFeeds("https://www.gov.sg/rss/factuallyrss");
        return view;
    }

    @Override
    public void rssFeedsLoaded(List<RSS> rssList) {
        txtTitle.setText(rssList.get(0).getChannel().getTitle());
        Log.d("titleheader", rssList.get(0).getChannel().getTitle());

        txtTitle1.setText(rssList.get(0).getChannel().getItems().get(0).getTitle());
        Log.d("title1", rssList.get(0).getChannel().getItems().get(0).getTitle());

        String url1 = rssList.get(0).getChannel().getItems().get(0).getDescription();
        wv1.loadData(url1,"text/html; charset=utf-8", "utf-8");

        txtTitle2.setText(rssList.get(0).getChannel().getItems().get(1).getTitle());
        Log.d("title1", rssList.get(0).getChannel().getItems().get(1).getTitle());

        String url2 = rssList.get(0).getChannel().getItems().get(1).getDescription();
        wv2.loadData(url2,"text/html; charset=utf-8", "utf-8");
    }

    @Override
    public void unableToReadRssFeeds(String errorMessage) {

    }

    private void loadFeeds(String urls) {
        rssReader.loadFeeds(urls);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        rssReader.destroy();
    }

//    @Override
//    public void rssFeedsLoaded(List<RSS> rssList) {
//        // Feeds loaded, do whatever you want to do with them.
//    }
//
//    @Override
//    public void unableToReadRssFeeds(String errorMessage) {
//        // Oops, library was unable to parse your feed url.
//    }

}
