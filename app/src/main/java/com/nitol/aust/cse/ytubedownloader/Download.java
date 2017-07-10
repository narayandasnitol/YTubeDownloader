package com.nitol.aust.cse.ytubedownloader;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Download extends Fragment{

    View v;
    WebView webView2;
    SwipeRefreshLayout mySwipeRefreshLayout;

    public String currentUrl = "http://youtube.com";
    String myLink = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.download, container,false);

        mySwipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.swiperefresh);
        webView2 = (WebView) v.findViewById(R.id.webView_download);


        webView2.setInitialScale(1);
        webView2.getSettings().setJavaScriptEnabled(true);
        webView2.getSettings().setLoadWithOverviewMode(true);
        webView2.getSettings().setUseWideViewPort(true);
        webView2.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        webView2.setScrollbarFadingEnabled(false);
        webView2.loadUrl(currentUrl);
        webView2.setWebViewClient(new WebViewClient());
        webView2.getSettings().setBuiltInZoomControls(true);
        webView2.getSettings().setUseWideViewPort(true);
        webView2.getSettings().setLoadWithOverviewMode(true);
        webView2.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webView2.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                return (event.getAction() == MotionEvent.ACTION_MOVE);
            }
        });
        webView2.setVerticalScrollBarEnabled(false);

        Bundle bundle = getArguments();
        if(bundle!= null)
        {
            String value = getArguments().getString("link");
            myLink = value;
            webView2.loadUrl(value);
        }

        Log.e("link = ", webView2.getUrl());

        return v;

    }

}