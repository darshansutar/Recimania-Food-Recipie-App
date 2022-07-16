package com.example.myapplication.Listeners;

import com.example.myapplication.Models.RecpieDetailsResponse;

public interface RecipieDetailsListener {
    void didFetch(RecpieDetailsResponse response,String message);
    void didError(String message);
}
