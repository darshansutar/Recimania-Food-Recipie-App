package com.example.myapplication.Listeners;

import com.example.myapplication.Models.RandomRecipieApiResponse;

public interface RandomRecipieResponseListener {
    void didFetch(RandomRecipieApiResponse response, String message);
    void didError(String message);

}
