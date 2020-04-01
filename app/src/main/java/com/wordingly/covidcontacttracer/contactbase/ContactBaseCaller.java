package com.wordingly.covidcontacttracer.contactbase;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ContactBaseCaller {

    @POST("/api/v1/devices")
    Call<TokenResponse> registerDevice(@Body Device device);

    @POST("/api/v1/contacts")
    Call<Void> storeContact(@Body List<Contact> contacts, @Header("Authorization") String authorizationHeader);
}
