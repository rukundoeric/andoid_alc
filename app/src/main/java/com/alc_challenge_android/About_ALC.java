package com.alc_challenge_android;

import android.annotation.SuppressLint;
import android.net.http.SslError;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class About_ALC extends AppCompatActivity {
    private WebView about_ALC_webview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about__alc);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        loadPage();
    }
    @SuppressLint("SetJavaScriptEnabled")
    private void loadPage(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Access_Permission_Request.hasPremissions(this, Access_Permission_Request.INTERNET_PERMISSION)) {
            ActivityCompat.requestPermissions(About_ALC.this, Access_Permission_Request.INTERNET_PERMISSION, Access_Permission_Request.INTERNET_PERMISSION_CONDE);
        } else {
            about_ALC_webview = (WebView) findViewById(R.id.alc_web_view);
            about_ALC_webview.getSettings().setJavaScriptEnabled(true);
            about_ALC_webview.setWebViewClient(new WebViewClient());
            about_ALC_webview.getSettings().setUseWideViewPort(true);
            about_ALC_webview.getSettings().setDomStorageEnabled(true);
            about_ALC_webview.setWebViewClient(new WebViewClient() {
                public void onReceivedSslError (WebView view, SslErrorHandler handler, SslError error) {
                    handler.proceed() ;
                }
            });
            about_ALC_webview.loadUrl("https://andela.com/alc");
            new CheckConnection().execute();
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    @SuppressLint("StaticFieldLeak")
    private class CheckConnection extends AsyncTask<String, Void, Boolean> {
        protected Boolean doInBackground(String... args) {
            return ConnectionDetector.isInternetAvailable();
        }
        protected void onPostExecute(Boolean result) {
            if (!result){
                Toast.makeText(About_ALC.this,String.valueOf(R.string.connection_error),Toast.LENGTH_LONG).show();
            }
        }
    }

}
