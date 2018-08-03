package com.example.ph625p.webserviceapp;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.TextureView;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;
import android.app.Activity;


public class MainActivity extends AppCompatActivity {
    public TextView dataview;
    public WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.dataview = (TextView)findViewById(R.id.data);
        this.webView = (WebView)findViewById(R.id.webdata);
        //WebView webView = new WebView(this);
       // setContentView(webView);

    }

    @Override
    protected void onStart(){
        super.onStart();

//        APIRequest request = new APIRequest(getApplicationContext(),"http://www.mocky.io/v2/5b5bb2663200004300426251","POST");
//        request.postData.add(new Pair<String, String>("email","pettish@frootfel.com"));
//        request.headerData.add(new Pair<String, String>("Token","djaieslhttieoshoto"));
        APIRequest request = new APIRequest(getApplicationContext(),"https://account.box.com/api/oauth2/authorize","POST");
        request.postData.add(new Pair<String, String>("response_type","code"));
        request.postData.add(new Pair<String, String>("client_id","kaclfbxuw6r4i4oixp3btszww6rp822y"));
        request.postData.add(new Pair<String, String>("redirect_uri","appSchema://webserviceapp.com"));
        request.postData.add(new Pair<String, String>("state","bradley"));
  /*    APIRequest request = new APIRequest(getApplicationContext(),"https://api.spotify.com/v1/me/player/next","POST");
        request.postData.add(new Pair<String, String>("device_id","0d1841b0976bae2a3a310dd74c0f3df354899bc8"));
        request.headerData.add(new Pair<String, String>("Accept:", "application/json"));
        request.headerData.add(new Pair<String, String>("Content-Type:","application/json"));
        request.headerData.add(new Pair<String, String>("Authorization:","Bearer BQCrr_gJOgnqQS0UtWvbCz7QdxFwcbsxr4zuj68YhGeEw6q_Ti3A93EniGfNYxP9eN6jXqYh6mo6nilHxLBh6DM-LbXbqQlN_KEHWVjKa5n5XidgsK5JFRFQyFSwiphFlMptBtSxquQVW6kA7jUqr9yDRQ"));
       */
        APIDataTask apiDataTask = (APIDataTask) new APIDataTask(request, new APIDataTask.OnCompleteListener(){

            @Override
            public void onComplete(APIResponse result) {
                Log.d("Response",result.responseData);
                dataview.setText(result.responseData);
     //           webView.loadUrl(result.responseData);
                webView.loadDataWithBaseURL("", result.responseData, "application/json", "UTF-8", "");

            }
        }).execute(request);

    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        String CALLBACK_URL = getResources().getString(R.string.box_callback);
        Uri uri = getIntent().getData();
        if (uri != null && uri.toString().startsWith(CALLBACK_URL)) {
            String access_token = uri.getQueryParameter("code");
        }
    }
}
