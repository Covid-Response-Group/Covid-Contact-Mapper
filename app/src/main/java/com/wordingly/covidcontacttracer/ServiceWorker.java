package com.wordingly.covidcontacttracer;


import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class ServiceWorker extends Worker {

    public static final String TAG = "SERVICE_WORKER";
    public ServiceWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        Intent serviceIntent = new Intent(getApplicationContext(), LocationUpdatesService.class);
        serviceIntent.putExtra("inputExtra", "Foreground Service Example in Android");
        ContextCompat.startForegroundService(getApplicationContext(), serviceIntent);
        Log.d(TAG, "Service from Work Manager");
        return Result.success();
    }
}
