package com.example.ph625p.webserviceapp;

import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import java.net.*;
import java.io.*;
import android.util.*;
import java.util.*;


public class APIDataTask extends AsyncTask<APIRequest,Void,APIResponse> {

    HttpURLConnection urlConnection;
    int responseCode = HttpURLConnection.HTTP_OK;
    URL requestUrl;

    interface OnCompleteListener {
        void onComplete(APIResponse result);
    }

    public OnCompleteListener delegate = null;

    //These are the different constructors that can be used depending on the request.
//    APIDataTask(APIDataTask request, OnCompleteListener delegate) {
//        this.delegate = delegate;
//    }

    APIDataTask(APIRequest request, OnCompleteListener delegate){
        this.delegate = delegate;
        try {
            this.requestUrl = new URL(request.url);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(APIResponse result) {
        delegate.onComplete(result);
        super.onPostExecute(result);
    }





    @Override
    protected APIResponse doInBackground(APIRequest... requests) {

        APIRequest request = (APIRequest)requests[0];
        Log.d("debug", "url = "+ request.url); try {
            urlConnection = (HttpURLConnection)requestUrl.openConnection();
            if(request.headerData != null) {
                for (Pair pair : request.headerData) {
                    urlConnection.setRequestProperty(pair.first.toString(),pair.second.toString()); }
            }
            urlConnection.setDoInput(true);
            urlConnection.setChunkedStreamingMode(0);
            urlConnection.setRequestMethod(request.method);
            urlConnection.connect();
            StringBuilder sb = new StringBuilder();
            if(!(request.method.equals("GET"))) {
                OutputStream out = new BufferedOutputStream(urlConnection.getOutputStream());
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));
                writer.write(getPostDataString(request.postData));
                writer.flush();
                writer.close();
                out.close();
            }
            urlConnection.connect();
            responseCode = urlConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
                String line;
                while ((line = reader.readLine()) != null) { sb.append(line);
                } }
            return new APIResponse(responseCode, sb.toString());
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    private String getPostDataString(List<Pair<String, String>> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        if (params!= null){
            for(Pair<String,String> pair : params){
                if (first)
                    first = false;
                else
                    result.append("&");
                result.append(URLEncoder.encode(pair.first,"UTF-8"));
                result.append("=");
                result.append(URLEncoder.encode(pair.second, "UTF-8"));
            }
        }
        return result.toString();
    }

}
