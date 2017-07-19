package com.apkfuns.jsbridge.sample;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

public class MainActivity extends ActionBarActivity {
    private WebView webView;

    @SuppressLint({"AddJavascriptInterface", "SetJavaScriptEnabled"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView = (WebView) findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("file:///android_asset/index.html");
        webView.addJavascriptInterface(new DownloadButton(), "downlaodBtn");
    }

    class DownloadButton {
        @JavascriptInterface
        public void startDownload(int downloadUrl) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int i=0;i<=100;i++){
                        upateProgress(i);
                        try{
                            Thread.sleep(200);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }

        private void upateProgress(final int pro) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    webView.loadUrl("javascript:updateProgerss('"+pro+"%');", null);
                }
            });
        }
    }


}
