package com.example.ph625p.webserviceapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }

    @Override
    protected void onStart(){
        super.onStart();
        APIRequest request = new APIRequest(getApplicationContext(),"http://www.mocky.io/v2/5b5bb2663200004300426251","POST");
//        request.postData.add(new Pair<String, String>("email","pettish@frootfel.com"));
//        request.headerData.add(new Pair<String, String>("Token","djaieslhttieoshoto"));
        APIDataTask apiDataTask = (APIDataTask) new APIDataTask(request, new APIDataTask.OnCompleteListener(){

            @Override
            public void onComplete(APIResponse result) {
                Log.d("Response",result.responseData);


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
    }
}
