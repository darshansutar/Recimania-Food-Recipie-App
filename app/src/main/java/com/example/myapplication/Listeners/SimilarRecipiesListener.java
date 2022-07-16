package com.example.myapplication.Listeners;

import com.example.myapplication.Models.SimilarRecipieResponse;

import java.util.List;

public interface SimilarRecipiesListener {
    void didFetch(List<SimilarRecipieResponse> response, String message);
    void didError(String message);
}
