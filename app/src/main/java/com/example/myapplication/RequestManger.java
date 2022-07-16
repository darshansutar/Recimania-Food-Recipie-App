package com.example.myapplication;

import android.content.Context;

import com.example.myapplication.Listeners.InstructionsListener;
import com.example.myapplication.Listeners.RandomRecipieResponseListener;
import com.example.myapplication.Listeners.RecipieDetailsListener;
import com.example.myapplication.Listeners.SimilarRecipiesListener;
import com.example.myapplication.Models.InstructionsResponse;
import com.example.myapplication.Models.RandomRecipieApiResponse;
import com.example.myapplication.Models.RecpieDetailsResponse;
import com.example.myapplication.Models.SimilarRecipieResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class RequestManger {
    Context context;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.spoonacular.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public RequestManger(Context context) {
        this.context = context;
    }

    public void getRandomRecipies(RandomRecipieResponseListener listener,List<String> tags){
        CallRandomRecipes callRandomRecipes = retrofit.create(CallRandomRecipes.class);
        Call<RandomRecipieApiResponse> call = callRandomRecipes.callRandomRecipie(context.getString(R.string.api_key),"50",tags);
        call.enqueue(new Callback<RandomRecipieApiResponse>() {
            @Override
            public void onResponse(Call<RandomRecipieApiResponse> call, Response<RandomRecipieApiResponse> response) {
                if(!response.isSuccessful()){
                    listener.didError(response.message());
                    return;
                }

                listener.didFetch(response.body(),response.message());
            }

            @Override
            public void onFailure(Call<RandomRecipieApiResponse> call, Throwable t) {
                    listener.didError(t.getMessage());
            }
        });
    }

    public void getRecipieDetails(RecipieDetailsListener listener,int id){
        CallRecipieDetails callRecipieDetails = retrofit.create(CallRecipieDetails.class);
        Call<RecpieDetailsResponse> call = callRecipieDetails.callRecipieDetails(id,context.getString(R.string.api_key));
        call.enqueue(new Callback<RecpieDetailsResponse>() {
            @Override
            public void onResponse(Call<RecpieDetailsResponse> call, Response<RecpieDetailsResponse> response) {
                if(!response.isSuccessful()){
                    listener.didError(response.message());
                    return;
                }

                listener.didFetch(response.body(),response.message());
            }

            @Override
            public void onFailure(Call<RecpieDetailsResponse> call, Throwable t) {
                    listener.didError(t.getMessage());
            }
        });
    }

    public void getSimilarRecipie(SimilarRecipiesListener listener,int id){
        CallSimilarRecipies callSimilarRecipies = retrofit.create(CallSimilarRecipies.class);
        Call<List<SimilarRecipieResponse>> call = callSimilarRecipies.callSimilarRecipie(id,"5",context.getString(R.string.api_key));
        call.enqueue(new Callback<List<SimilarRecipieResponse>>() {
            @Override
            public void onResponse(Call<List<SimilarRecipieResponse>> call, Response<List<SimilarRecipieResponse>> response) {
                if(!response.isSuccessful()){
                    listener.didError(response.message());
                    return;
                }
                listener.didFetch(response.body(),response.message());
            }

            @Override
            public void onFailure(Call<List<SimilarRecipieResponse>> call, Throwable t) {
                    listener.didError(t.getMessage());
            }
        });
    }

    public void getInstructions(InstructionsListener listener,int id){
        CallInstructions callInstructions = retrofit.create(CallInstructions.class);
        Call<List<InstructionsResponse>> call = callInstructions.callInstructions(id,context.getString(R.string.api_key));
        call.enqueue(new Callback<List<InstructionsResponse>>() {
            @Override
            public void onResponse(Call<List<InstructionsResponse>> call, Response<List<InstructionsResponse>> response) {
                if(!response.isSuccessful()){
                    listener.didError(response.message());
                    return;
                }
                listener.didFetch(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<List<InstructionsResponse>> call, Throwable t) {
                        listener.didError(t.getMessage());
            }
        });
    }

    private interface CallRandomRecipes{
        @GET("recipes/random")
        Call<RandomRecipieApiResponse> callRandomRecipie(

                @Query("apiKey") String apiKey,
                @Query("number") String number,
                @Query("tags")List<String> tags
                );

    }

    private interface  CallRecipieDetails{
        @GET("recipes/{id}/information")
        retrofit2.Call<RecpieDetailsResponse> callRecipieDetails(
                @Path("id") int id,
                @Query("apiKey") String apiKey
        );


    }

    private interface CallSimilarRecipies{
        @GET("recipes/{id}/similar")
        Call<List<SimilarRecipieResponse>> callSimilarRecipie(
                @Path("id") int id,
                @Query("number") String number,
                @Query("apiKey") String apiKey

        );
    }

    private interface CallInstructions{
        @GET("recipes/{id}/analyzedInstructions")
        Call<List<InstructionsResponse>> callInstructions(
                @Path("id") int id,
                @Query("apiKey") String apiKey
        );
    }
}
