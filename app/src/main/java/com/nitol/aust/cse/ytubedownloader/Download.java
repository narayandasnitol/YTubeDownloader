package com.nitol.aust.cse.ytubedownloader;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.DownloadListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class Download extends Fragment{

    View v;
    WebView webView2;
    SwipeRefreshLayout mySwipeRefreshLayout;
    DownloadManager downloadManager;

    public String currentUrl = "";
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
        webView2.setVerticalScrollBarEnabled(false);
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

        Bundle bundle = getArguments();
        if(bundle!= null)
        {
            String value = getArguments().getString("link");
            myLink = value;
            webView2.loadUrl(myLink);
        }

        webView2.setDownloadListener(new DownloadListener() {
            public void onDownloadStart(String url, String userAgent,
                                        String contentDisposition, String mimetype,
                                        long contentLength) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));


                downloadManager = (DownloadManager) getContext().getSystemService(Context.DOWNLOAD_SERVICE);
                DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "Youtube_Video"+".mp4");
                request.allowScanningByMediaScanner();
                Long reference = downloadManager.enqueue(request);

                Toast.makeText(getContext(), "Downloading...", Toast.LENGTH_LONG).show();


            }
        });


        return v;
    }


}