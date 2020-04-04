package com.wordingly.covidcontacttracer.network;


import com.wordingly.covidcontacttracer.objects.DownloadData;
import com.wordingly.covidcontacttracer.objects.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


interface ApiInterface {

    @GET("/AbhinavDAIICT/MyServerTester/download_data")
    Call<DownloadData> getAllData();





}