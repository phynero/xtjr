package com.xtjr.application;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

public class webActivity extends Activity implements View.OnClickListener {
    TextView textView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        ((TextView) findViewById(R.id.tittleTxt)).setText("");
        findViewById(R.id.backButton).setOnClickListener(this);
        WebView webView = (WebView) findViewById(R.id.webview);
        textView = (TextView) findViewById(R.id.webLoading);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        String str = getIntent().getStringExtra("url");
        WebSettings webSettings =   webView .getSettings();
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedError(WebView view, int errorCode,
                                        String description, String failingUrl) {
                // TODO Auto-generated method stub
                textView.setVisibility(View.GONE);
                super.onReceivedError(view, errorCode, description, failingUrl);
                Toast.makeText(webActivity.this, "网页获取失败，请稍候再试", Toast.LENGTH_LONG)
                        .show();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                textView.setVisibility(View.GONE);
                super.onPageFinished(view, url);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                textView.setVisibility(View.VISIBLE);
                view.loadUrl(url);
                return true;
            }
        });
        webView.loadUrl(str);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.backButton:
                onBackPressed();
                break;
        }
    }
}
