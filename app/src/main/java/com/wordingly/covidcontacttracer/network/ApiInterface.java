package com.wordingly.covidcontacttracer.network;


import com.wordingly.covidcontacttracer.objects.TestPosts;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


interface ApiInterface {

    @GET("/AbhinavDAIICT/MyServerTester/posts")
    Call<List<TestPosts>> getPosts();

}