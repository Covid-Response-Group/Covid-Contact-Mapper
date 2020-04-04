package com.wordingly.covidcontacttracer.network;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.wordingly.covidcontacttracer.CovidContactTracer;
import com.wordingly.covidcontacttracer.objects.DownloadData;
import com.wordingly.covidcontacttracer.objects.Post;
import com.wordingly.covidcontacttracer.objects.TestPosts;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NetworkCalls {

    static ApiInterface apiInterface;


    public static void fetchAllData() {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<DownloadData> call = apiInterface.getAllData();
        call.enqueue(new Callback<DownloadData>() {

            @Override
            public void onResponse(Call<DownloadData> call, Response<DownloadData> response) {
                List<Post> allPosts = response.body().getPosts();
                Realm realm = Realm.getDefaultInstance();

                realm.executeTransactionAsync(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        realm.copyToRealmOrUpdate(allPosts);
                    }
                }, new Realm.Transaction.OnSuccess() {
                    @Override
                    public void onSuccess() {
                        Log.d("REALM Success", "posts: " + realm.where(Post.class).findAll().get(0).getTitle());

                    }
                }, new Realm.Transaction.OnError() {
                    @Override
                    public void onError(Throwable error) {
                        Log.d("REALM Error", error.getMessage());
                    }
                });



            }

            @Override
            public void onFailure(Call<DownloadData> call, Throwable t) {
                Log.e("NETWORK ERROR", t.getMessage());

            }
        });
    }
}
