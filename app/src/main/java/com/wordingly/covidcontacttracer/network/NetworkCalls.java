package com.wordingly.covidcontacttracer.network;

import android.util.Log;

import com.wordingly.covidcontacttracer.objects.TestPosts;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NetworkCalls {

    static ApiInterface apiInterface;

    public static void fetchPosts() {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<TestPosts>> call = apiInterface.getPosts();
        call.enqueue(new Callback<List<TestPosts>>() {
            @Override
            public void onResponse(Call<List<TestPosts>> call, Response<List<TestPosts>> response) {
                List<TestPosts> jsonResponse = response.body();
                Log.d("Posts response", "post ID:" +jsonResponse.get(1).getId());
            }

            @Override
            public void onFailure(Call<List<TestPosts>> call, Throwable t) {
                Log.d("Posts response", t.getMessage());
            }
        });
    }
}
