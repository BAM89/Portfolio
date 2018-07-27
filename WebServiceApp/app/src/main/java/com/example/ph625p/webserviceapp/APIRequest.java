package com.example.ph625p.webserviceapp;

import android.content.*;
import android.util.*;
import java.util.*;

public class APIRequest {
    Context context;
    String method;
    String url;
    List<Pair<String,String>> postData;
    List<Pair<String,String>> headerData;

    APIRequest(Context context, String url, String method) {
        this.context = context;
        this.url = url;
        this.method = method;
    }

}
