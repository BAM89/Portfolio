package com.example.ph625p.webserviceapp;

public class APIResponse {
    int httpResponseCode;
    String responseData;

    APIResponse(int httpResponseCode, String responseData) {
        this.httpResponseCode = httpResponseCode;
        this.responseData = responseData;
    }
}
