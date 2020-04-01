package com.wordingly.covidcontacttracer.contactbase;

import android.util.Log;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ch.hsr.geohash.GeoHash;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ContactBaseClient {

    private static final String BASE_URL = "http://192.168.0.107:3000/";
    private static final String USER_FIELD_EMAIL = "email";
    private static final String TAG = ContactBaseClient.class.getSimpleName();
    private static final Integer GEOHASH_PRECISION = 6;

    private ContactBaseCaller contactBaseCaller;

    private String token;

    public ContactBaseClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        contactBaseCaller = retrofit.create(ContactBaseCaller.class);
    }

    public void push(String googleAccountEmail, Double latitude, Double longitude, String sourceDeviceId, String targetDeviceId) {
        if (token == null) {
            Map<String, Object> user = new HashMap<>();
            user.put(USER_FIELD_EMAIL, googleAccountEmail);

            try {
                TokenResponse tokenResponse = contactBaseCaller.registerDevice(new Device(sourceDeviceId, user)).execute().body();

                if (tokenResponse != null) {
                    this.token = tokenResponse.getToken();
                }
            } catch (IOException e) {
                Log.e(TAG, "Error while getting token " + e);
            }
        }

        if (token != null) {
            List<Contact> contacts = new ArrayList<>();
            List<SpatialTemporalStamp> spatialTemporalStamps = new ArrayList<>();

            Date now = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYYMMdd");
            simpleDateFormat.format(now);

            spatialTemporalStamps.add(new SpatialTemporalStamp(GeoHash.withCharacterPrecision(latitude, longitude, GEOHASH_PRECISION).toBase32(), simpleDateFormat.toString()));
            contacts.add(new Contact(targetDeviceId, spatialTemporalStamps));

            contactBaseCaller.storeContact(contacts, "Bearer " + token).enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    Log.d(TAG, "Call to push contact returned response code " + response.code());
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Log.e(TAG, "Call to push contact returned error " + t.getMessage());
                }
            });
        }
    }
}
