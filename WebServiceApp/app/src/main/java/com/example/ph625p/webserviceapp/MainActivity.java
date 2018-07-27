package com.example.ph625p.webserviceapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        APIRequest request = new APIRequest(getApplicationContext(),"www.google.com","POST");
//        request.postData.add(new Pair<String, String>("email","pettish@frootfel.com"));
//        request.headerData.add(new Pair<String, String>("Token","djaieslhttieoshoto"));

        

    }

    @Override
    protected void onStart(){
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
