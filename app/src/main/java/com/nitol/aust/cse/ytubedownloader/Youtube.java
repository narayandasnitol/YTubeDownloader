package com.nitol.aust.cse.ytubedownloader;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class Youtube extends Fragment{

    View v;
    WebView webView;
    SwipeRefreshLayout mySwipeRefreshLayout;
    FloatingActionButton fabButton;


    private String currentUrl = "https://m.youtube.com";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.youtube, container,false);

        mySwipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.swiperefresh);
        webView = (WebView) v.findViewById(R.id.webView_youtube);
        fabButton = (FloatingActionButton) v.findViewById(R.id.floating_button);

        webView.setInitialScale(1);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        webView.setScrollbarFadingEnabled(false);
        webView.loadUrl(currentUrl);
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

        fabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s = webView.getUrl();
                String ss = s.replace("m.", "ss");

                Download fragment = new Download();
                Bundle bundle = new Bundle();
                bundle.putString("link",ss);
                fragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().
                        beginTransaction().replace(R.id.webView_download,fragment).commit();

                Toast.makeText(getContext(), "Please go to Download Tab", Toast.LENGTH_SHORT).show();
            }
        });


        mySwipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        webView.loadUrl(currentUrl);
                        webView.setWebViewClient(new MyWebViewClient());
                    }

                }
        );

        return v;

    }

    public class MyWebViewClient extends WebViewClient{

        @Override
        public void onPageFinished(WebView view, String url) {

            mySwipeRefreshLayout.setRefreshing(false);
            currentUrl = url;
            super.onPageFinished(view, url);

        }

    }

}

